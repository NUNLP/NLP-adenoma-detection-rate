import static org.northshore.cbri.UIMAUtil.*

import java.util.regex.Matcher
import java.util.regex.Pattern

import org.apache.ctakes.typesystem.type.textspan.Segment
import org.northshore.cbri.type.SegmentHeading

// match segment headings

Pattern pat1 = ~/(?m)^[A-Z]+(\s+[A-Z]+)*:/
Matcher matcher = jcas.documentText =~ pat1
//Pattern pat2 = ~/[A-Z]+(\s+[A-Z]+)*:/
//Matcher matcher
//if (jcas.documentText.contains('\n')) {
//    matcher = jcas.documentText =~ pat1
//}
//else {
//    matcher = jcas.documentText =~ pat2
//}
matcher.each {
    create(type:SegmentHeading, begin:matcher.start(0), end:matcher.end(0))
}

// create segments from patterns of segment headings
(~/(?s)(?<S1>@SegmentHeading)(?=(?<S2>@SegmentHeading)|\Z)/).matcher(
        coveringAnn:jcas.documentAnnotationFs,
        types:[SegmentHeading],
        includeText:false).each { Map b ->
            create(type:Segment, begin:b.get('S1').begin, end:(b.get('S2') ? b.get('S2').begin : jcas.documentText.length()))
        }

// categorize sections
patterns = [
    (~/((PREOPERATIVE|PREPROCEDURE)\s+(DIAGNOSIS|DIAGNOSES)):|(INDICATIONS?):/):'INDICATIONS',
    (~/(FINDINGS.*:)/):'FINDINGS',
    (~/((POSTOPERATIVE|FINAL)\s+DIAGNOS(I|E)S:)/):'DIAGNOSIS',
    (~/(IMPRESSION:)/):'IMPRESSION',
    (~/PROCEDURE(\s*NOTE)?:/):'PROCEDURE',
    (~/.*(QUALITY).*/):'PREP_QUALITY'
    ]
select (type:Segment).each { Segment seg ->
    headings = select(type:SegmentHeading, filter:coveredBy(seg))
    patterns.each { pat, val ->
        if (headings.count { it.coveredText ==~ pat } > 0 ) { seg.id = val }
    }
}

// create a segment over entire document if none exists
if (select(type:Segment).size() == 0) {
    create(type:Segment, begin:0, end:jcas.documentText.length())
}
