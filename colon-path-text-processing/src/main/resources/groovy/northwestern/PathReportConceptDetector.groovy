package groovy.northwestern
import static org.northshore.cbri.UIMAUtil.*

import org.apache.ctakes.typesystem.type.refsem.OntologyConcept
import org.apache.ctakes.typesystem.type.refsem.UmlsConcept
import org.apache.ctakes.typesystem.type.textsem.EntityMention

import edu.northwestern.fsm.type.FindingSentence
import edu.northwestern.fsm.type.LocSentence

//---------------------------------------------------------------------------------------------------------------------
// polyp finding mentions
//---------------------------------------------------------------------------------------------------------------------

findingPatterns = [
    (~/(?i)((INFLAMMATORY(\s+\(?RETENTION\)?)?))\s*POLYPS?/):[group:0, type:UmlsConcept, codingScheme:"SNOMED", code:"76235005", cui:"C0032568", tui:"T047"],
    (~/(?i)HYPERPLASTIC/):[group:0, type:UmlsConcept, codingScheme:"SNOMED", code:"62047007", cui:"C0333983", tui:"T191"],
    (~/(?i)TUBULAR\s+ADENOMAS?/):[group:0, type:UmlsConcept, codingScheme:"SNOMED", code:"444408007", cui:"C0334292", tui:"T191"],
    (~/(?i)TUBULO.?VILLOUS\s+ADENOMAS?/):[group:0, type:UmlsConcept, codingScheme:"SNOMED", code:"61722000", cui:"C0334307", tui:"T191"],
    (~/(?i)VILLOUS\s+ADENOMAS?/):[group:0, type:UmlsConcept, codingScheme:"SNOMED", code:"309084001", cui:"C0149862", tui:"T191"],
    (~/(?i)SERRATED\s+(POLYPS?|ADENOMAS?|FEATURES?)/):[group:0, type:UmlsConcept, codingScheme:"SNOMED", code:"128653004", cui:"C1266025", tui:"T191"],
    (~/(?i)(CARCINOMA|ADENO.?CARCINOMA)/):[group:0, type:UmlsConcept, codingScheme:"SNOMED", code:"1701000119104", cui:"C3472669", tui:"T191"],
    (~/(?i)villous/):[group:0, type:UmlsConcept, codingScheme:"ADHOC", code:"VILLOUS", cui:"VILLOUS", tui:"ADVANCED"],
    (~/(?i)(?s)high[\-\s+]grade\s+dysplasia/):[group:0, type:UmlsConcept, codingScheme:"ADHOC", code:"HIGH_GRADE_DYSPLASIA", cui:"HIGH_GRADE_DYSPLASIA", tui:"ADVANCED"]
]
createMentions(
        patterns:findingPatterns,
        searchSet:select(type:FindingSentence),
        mentionType:EntityMention
        )


//---------------------------------------------------------------------------------------------------------------------
// colon landmark mentions
//---------------------------------------------------------------------------------------------------------------------

locPatterns = [
    (~/(?i)(RECTUM|DENTATE|RECTAL\s+POLYPS?)/):[group:0, type:UmlsConcept, codingScheme:"SNOMED", code:"34402009", cui:"C0034896", tui:"T023"],
    (~/(?i)(SIGMOID)/):[group:1, type:UmlsConcept, codingScheme:"SNOMED", code:"60184004", cui:"C0227391", tui:"T023"],
    (~/(?i)(DESCENDING)/):[group:1, type:UmlsConcept, codingScheme:"SNOMED", code:"32622004", cui:"C0227389", tui:"T023"],
    (~/(?i)(SPLENIC\s+FLEXURES?)/):[group:1, type:UmlsConcept, codingScheme:"SNOMED", code:"245428003", cui:"C0227387", tui:"T023"],
    (~/(?i)(TRANSVERSE)/):[group:1, type:UmlsConcept, codingScheme:"SNOMED", code:"485005", cui:"C0227386", tui:"T023"],
    (~/(?i)(HEPATIC(\s+FLEXURES?)?)/):[group:1, type:UmlsConcept, codingScheme:"SNOMED", code:"48338005", cui:"C0227385", tui:"T023"],
    (~/(?i)(ASCENDING)/):[group:1, type:UmlsConcept, codingScheme:"SNOMED", code:"9040008", cui:"C0227375", tui:"T023"],
    (~/(?i)(ILEUM)/):[group:1, type:UmlsConcept, codingScheme:"SNOMED", code:"23153004", cui:"C0020880", tui:"T023"],
    (~/(?i)(CECUM|CECAL)/):[group:1, type:UmlsConcept, codingScheme:"SNOMED", code:"32713005", cui:"C0007531", tui:"T023"]
]
createMentions(
        patterns:locPatterns,
        searchSet:select(type:LocSentence),
        mentionType:EntityMention
        )

//---------------------------------------------------------------------------------------------------------------------
// distance patterns
//---------------------------------------------------------------------------------------------------------------------

distPatterns = [
    (~/(?i)(\s+(?<quant>\d{1,3})\s*(?<unit>CM))/):[group:0, type:UmlsConcept, codingScheme:"UCUM", code:"\\k<quant>CM", cui:"C0475210", tui:"T081"],
    (~/(?i)(LEFT)/):[group:1, type:UmlsConcept, codingScheme:"SNOMED", code:"55572008", cui:"C0227388", tui:"T023"],
    (~/(?i)(RIGHT)/):[group:1, type:UmlsConcept, codingScheme:"SNOMED", code:"51342009", cui:"C1305188", tui:"T023"]
]

// mapping rule from distance to colon proximity (left or right)
distMapRule = { OntologyConcept concept ->
    if (concept.codingScheme != "UCUM") {
        return []
    }
    quantity = Integer.parseInt(concept.code.substring(0, concept.code.length()-2))
    UmlsConcept proxConcept = create(type:UmlsConcept, codingScheme:"SNOMED")
    if (quantity >= 60) { // right colon
        proxConcept.code = "51342009"
        proxConcept.cui = "C1305188"
    }
    else { // left colon
        proxConcept.code = "55572008"
        proxConcept.cui = "C0227388"
    }
    proxConcept.oid = proxConcept.code + "#" + proxConcept.codingScheme
    proxConcept.tui = "T023"
    return [proxConcept]
}

createMentions(
        patterns:distPatterns,
        searchSet:select(type:LocSentence),
        mentionType:EntityMention,
        inferConcepts:distMapRule
        )

//---------------------------------------------------------------------------------------------------------------------
// post-processing
//---------------------------------------------------------------------------------------------------------------------

// remove multiple location mentions -- leads to false positive relations
// TODO: if more than one mention was detected, map to most specific
// region possible (left, right, colon)
select(type:LocSentence).each { ann ->
    List<EntityMention> mentions = select(type:EntityMention, filter:coveredBy(ann))
    if (mentions.size() > 1) {
        mentions.each { em ->
            if (em.ontologyConcepts[0].codingScheme == "UCUM") { em.removeFromIndexes() }
        }
    }
}

// if no specific entity match was found, look for "colon"
colonPatterns = [
    (~/(?i)(COLON)/):[group:0, type:UmlsConcept, codingScheme:"SNOMED", code:302508007, cui:"C1281569" , tui:"T023"]
]
createMentions(
        patterns:colonPatterns,
        searchSet:select(type:LocSentence, filter:not(contains(EntityMention))),
        mentionType:EntityMention
        )

// set default polarity to 1
select(type:EntityMention).each { EntityMention em ->
    em.polarity = 1
}
