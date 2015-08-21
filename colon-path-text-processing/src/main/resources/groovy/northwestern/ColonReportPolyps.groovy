import static org.northshore.cbri.UIMAUtil.*
import static edu.northwestern.fsm.ColonConcept.*

import org.apache.ctakes.typesystem.type.refsem.OntologyConcept
import org.apache.ctakes.typesystem.type.refsem.UmlsConcept
import org.apache.ctakes.typesystem.type.textsem.EntityMention
import org.apache.ctakes.typesystem.type.textsem.MeasurementAnnotation
import org.apache.ctakes.typesystem.type.textspan.Sentence

import edu.northwestern.fsm.type.ColonoscopyProcedure

//---------------------------------------------------------------------------------------------------------------------
// size mentions
//---------------------------------------------------------------------------------------------------------------------

SizePatterns = [
    (~/(?i)(?s)((?<quant>(?:\d{1,2}\.)?\d{1,2})(\s|-)*(?<unit>cm|mm))/):[group:0, type:UmlsConcept, 
        codingScheme:CODING_SCHEME_UCUM, code:'\\k<quant>CM', cui:'C0475210', tui:TUI_QUANTITATIVE_CONCEPT]
]

createMentions(
    patterns:SizePatterns,
    searchSet:[jcas.documentAnnotationFs],
    mentionType:EntityMention
    )

//---------------------------------------------------------------------------------------------------------------------
// polyp mentions
//---------------------------------------------------------------------------------------------------------------------

PolypPatterns = [
    (~/(?i)(?s)(three|four|five|six|seven|eight|nine|ten).+polyps/):[group:0, type:UmlsConcept, codingScheme:'SNOMED', code:'113765010', cui:'C0009376', tui:'T190']
]

countRule = { OntologyConcept concept ->
    ColonoscopyProcedure proc = select(type:ColonoscopyProcedure).find()
    proc.colon_count_3 = true
    return []
}

createMentions(
    patterns:PolypPatterns,
    searchSet:select(type:Sentence),
    mentionType:EntityMention,
    inferConcepts:countRule
    )

// set default polarity to 1
select(type:EntityMention).each { EntityMention em ->
    em.polarity = 1
}
