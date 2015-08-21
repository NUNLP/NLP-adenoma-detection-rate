package groovy.northwestern
import static edu.northwestern.fsm.ColonConcept.*
import static org.northshore.cbri.UIMAUtil.*

import org.apache.ctakes.typesystem.type.refsem.UmlsConcept
import org.apache.ctakes.typesystem.type.relation.RelationArgument
import org.apache.ctakes.typesystem.type.relation.UMLSRelation
import org.apache.ctakes.typesystem.type.textsem.EntityMention

import edu.northwestern.fsm.type.FindingSegment
import edu.northwestern.fsm.type.FindingSentence


// create relations between anatomical mention and polyp mentions
// within the same finding segment
select (type:FindingSegment).each{ seg ->
    // anatomical sites
    sites = select (type:EntityMention, filter:coveredBy(seg)).grep { EntityMention em ->
        em.ontologyConcepts.any{ UmlsConcept concept -> concept.tui == TUI_BODY_PART }
    }
    // histological findings
    findings = select (type:EntityMention, filter:coveredBy(seg)).grep { EntityMention em ->
        em.ontologyConcepts.any{ UmlsConcept concept -> concept.tui in [TUI_NEOPLASTIC_PROCESS, TUI_DISEASE_OR_SYNDROME] }
    }
    sites.each { site ->
        findings.each { finding ->
            create(type:UMLSRelation,
                arg1:create(type:RelationArgument, role:ROLE_FINDING, argument:finding),
                arg2:create(type:RelationArgument, role:ROLE_LOCATION, argument:site)
            )
        }
    }
}

// create relations between findings and "advance" features
select (type:FindingSentence).each { sent ->
    // histological findings
    findings = select (type:EntityMention, filter:coveredBy(sent)).grep {
        it.ontologyConcepts.any{ UmlsConcept concept -> concept.tui in [TUI_NEOPLASTIC_PROCESS, TUI_DISEASE_OR_SYNDROME] }
    }
    // advanced features
    advanced = select (type:EntityMention, filter:coveredBy(sent)).grep {
        it.ontologyConcepts.any{ UmlsConcept concept -> concept.tui in [TUI_ADVANCED] }
    }
    findings.each { finding ->
        advanced.each { adv ->
            if (adv.ontologyConcepts[0].code == 'HIGH_GRADE_DYSPLASIA') {
                create(type:UMLSRelation,
                    arg1:create(type:RelationArgument, role:ROLE_FINDING, argument:finding),
                    arg2:create(type:RelationArgument, role:ROLE_HIGH_GRADE_DYSPLASIA, argument:adv)
                )
            }
            if (adv.ontologyConcepts[0].code == 'VILLOUS') {
                create(type:UMLSRelation,
                    arg1:create(type:RelationArgument, role:ROLE_FINDING, argument:finding),
                    arg2:create(type:RelationArgument, role:ROLE_VILLOUS, argument:adv)
                )
            }
        }
    }
}






