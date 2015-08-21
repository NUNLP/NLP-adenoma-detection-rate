package groovy.northwestern
import static edu.northwestern.fsm.ColonConcept.*
import static org.northshore.cbri.UIMAUtil.*

import org.apache.ctakes.typesystem.type.refsem.UmlsConcept
import org.apache.ctakes.typesystem.type.textsem.EntityMention

import edu.northwestern.fsm.type.ColonoscopyProcedure


Collection<EntityMention> mentions = select(type:EntityMention, filter:and(
       { (it.ontologyConcepts.grep { concept -> concept.codingScheme == CODING_SCHEME_UCUM }).size() > 0 },
       { it.polarity == 1 }
    )
)


mentions.each { EntityMention em ->
    UmlsConcept concept = em.ontologyConcepts.find { it.codingScheme == CODING_SCHEME_UCUM }
    quantity = Float.parseFloat(concept.code.substring(0, concept.code.length()-2))
    units = concept.code.substring(concept.code.length() - 2, concept.code.length())
    if ((units == 'mm' && quantity >= 10) ||
        (units == 'cm' && quantity >= 1 && quantity <= 3.5)) {
        ColonoscopyProcedure proc = select(type:ColonoscopyProcedure).find()
        proc.colon_size_1cm = true;
    }
}

