import static org.northshore.cbri.UIMAUtil.*
import static edu.northwestern.fsm.ColonConcept.*

import org.apache.ctakes.typesystem.type.textspan.Segment
import org.apache.uima.jcas.cas.StringArray

import edu.northwestern.fsm.type.ColonoscopyProcedure

screenPattern = ~/(?i)(?s).*(screen|(rule\s+out\s+(colon\s+polyp|polyp|adenoma|cancer|carcinoma))).*/
otherPattern = ~/(?i)(?s).*(history|hx|s\/p|chronic|bleed|diarrhea|increased|transplant|weight|blood|pain|irregular|anemia|diverticul|colitis|pruritis|change|first\s+degree|mother|father|sister|brother|crohn|disease|inflammatory|tumor|irritable|stool|hemato|follow.?up|constipat|heme|positive|past|previous|increased).*/

Collection indications = new TreeSet<String>()
select (type:Segment).grep { Segment s1 ->
    s1.id.equals(SEGMENT_INDICATIONS) }.each { Segment s2 ->
        if (s2.coveredText ==~ screenPattern) { indications.add(INDICATION_SCREENING) }
        if (s2.coveredText ==~ otherPattern) { indications.add(INDICATION_OTHER) }
    }
if (indications.size() == 0) { indications.add(INDICATION_OTHER) }
ColonoscopyProcedure proc = select(type:ColonoscopyProcedure).find()
proc.indications = new StringArray(jcas, indications.size())
int n = 0
for (String ind : indications) {
    proc.setIndications(n, ind)
    n++
}
