import static org.northshore.cbri.UIMAUtil.*

import org.apache.ctakes.typesystem.type.refsem.UmlsConcept
import org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation
import org.apache.ctakes.typesystem.type.textsem.Modifier
import org.apache.ctakes.typesystem.type.textspan.Segment
import org.apache.ctakes.typesystem.type.textspan.Sentence

import edu.northwestern.fsm.type.ColonoscopyProcedure

// --------------------------------------------------------------------------------------------------------------------
// prep quality mentions
// --------------------------------------------------------------------------------------------------------------------

prepQualityPatterns = [
    (~/(?i)\bprep\.?\b|preparation/):[group:0, type:UmlsConcept, codingScheme:'SNOMED', code:'262068006', cui:'C0445204', tui:'T079']
]
createMentions(
        patterns:prepQualityPatterns,
        searchSet:select(type:Sentence),
        mentionType:IdentifiedAnnotation
        )

modifierPatterns = [
    (~/(?i)quality/):[group:0, type:UmlsConcept, codingScheme:'SNOMED', code:'263496004', cui:'C0332306', tui:'T080'],
    (~/(?i)adequate|acceptable|satisfactory/):[group:0, type:UmlsConcept, codingScheme:'SNOMED', code:'adequate', cui:'C0205411', tui:'T080'],
    (~/(?i)fair|sub-?optimal/):[group:0, type:UmlsConcept, codingScheme:'SNOMED', code:'fair', cui:'C0205411', tui:'T080'],
    (~/(?i)good/):[group:0, type:UmlsConcept, codingScheme:'SNOMED', code:'good', cui:'C0205411', tui:'T080'],
    (~/(?i)excellent/):[group:0, type:UmlsConcept, codingScheme:'SNOMED', code:'excellent', cui:'C0205411', tui:'T080'],
    (~/(?i)inadequate|sub-?optimal|unprepared|unsatisfactory/):[group:0, type:UmlsConcept, codingScheme:'SNOMED', code:'inadequate', cui:'C0205412', tui:'T080'],
    (~/(?i)poor|unprepared/):[group:0, type:UmlsConcept, codingScheme:'SNOMED', code:'poor', cui:'C0205412', tui:'T080'],
    (~/(?i)unsatisfactory/):[group:0, type:UmlsConcept, codingScheme:'SNOMED', code:'unsatisfactory', cui:'C0205412', tui:'T080']
]
createMentions(
        patterns:modifierPatterns,
        searchSet:select(type:Sentence),
        mentionType:Modifier
        )

// --------------------------------------------------------------------------------------------------------------------
// document level classification of prep quality
// --------------------------------------------------------------------------------------------------------------------

ColonoscopyProcedure proc = select(type:ColonoscopyProcedure).find()

Collection<Segment> segs = select(type:Segment)
segs.each { Segment seg ->
    if (seg.id == 'PREP_QUALITY') {
        select(type:Sentence, filter:coveredBy(seg)).each { Sentence sent ->
            select(type:Modifier, filter:coveredBy(sent)).each { Modifier mod ->
                if (!proc.prep_quality && mod.ontologyConcepts[0].cui in ['C0205411', 'C0205412']) {
                    proc.prep_quality = mod.ontologyConcepts[0].code
                }
                else if (mod.ontologyConcepts[0].cui == 'C0205412') {
                    proc.prep_quality = mod.ontologyConcepts[0].code
                }
            }
        }
    }
}

if (proc.prep_quality == null) {
    select(type:Sentence, filter:contains(Modifier)).each { Sentence sent ->
        boolean quality = false
        boolean preparation = false
        String qualityVal;
        select(type:IdentifiedAnnotation, filter:coveredBy(sent)).each { IdentifiedAnnotation ident ->
            switch (ident.ontologyConcepts[0].cui) {
                case 'C0445204':
                    preparation = true
                    break
                case 'C0332306':
                    quality = true
                    break
                case 'C0205411':
                    qualityVal = ident.ontologyConcepts[0].code
                    break
                case 'C0205412':
                    qualityVal = ident.ontologyConcepts[0].code
                    break
            }
            if ((preparation || quality) && qualityVal) {
                proc.prep_quality = qualityVal
            }
            //println "proc.prep_adequate = ${proc.prep_adequate}"
            //println "quality: ${quality}, preparation: ${preparation}, adequate: ${adequate}, inadedequate: ${inadequate}"
        }
    }
}