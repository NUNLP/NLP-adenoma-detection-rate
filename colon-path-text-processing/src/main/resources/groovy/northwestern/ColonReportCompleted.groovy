import static org.northshore.cbri.UIMAUtil.*
import static edu.northwestern.fsm.ColonConcept.*

import org.apache.ctakes.typesystem.type.refsem.UmlsConcept
import org.apache.ctakes.typesystem.type.textsem.EntityMention
import org.apache.ctakes.typesystem.type.textspan.Segment


//---------------------------------------------------------------------------------------------------------------------
// anatomical mentions (for "advanced to" determination
//---------------------------------------------------------------------------------------------------------------------

locPatterns = [
    (~/(?i)((CECUM|CECAL)(\s+ASCENDING)?)/):[group:1, type:UmlsConcept] << CECUM.map,
    (~/(?i)((ILEUM|ILEOCECAL\s+VALVE|ILEO-CECAL\s+VALVE))/):[group:1, type:UmlsConcept] << CECUM.map
]
createMentions(
    patterns:locPatterns,
    searchSet:select(type:Segment),
    mentionType:EntityMention
    )

// remove mentions contained in larger mentions
List<EntityMention> contained = []
select(type:EntityMention).each {
    Collection covered = select (type:EntityMention, filter:coveredBy(it))
    covered.each {
        contained << it
    }
}
contained.each {
    jcas.removeFsFromIndexes it
}

select(type:EntityMention).each { EntityMention em ->
    em.polarity = 1
}



