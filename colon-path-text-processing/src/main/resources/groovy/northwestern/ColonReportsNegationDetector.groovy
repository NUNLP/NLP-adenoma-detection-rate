package groovy.northwestern
import static org.northshore.cbri.UIMAUtil.*

import org.apache.ctakes.typesystem.type.textsem.EntityMention
import org.apache.ctakes.typesystem.type.textspan.Sentence
import org.apache.uima.jcas.tcas.Annotation

import org.northshore.cbri.AnnotationMatcher
import org.northshore.cbri.type.NegationScope
import org.northshore.cbri.type.NegationScopeTerminator
import org.northshore.cbri.type.NegationTrigger
import org.northshore.cbri.type.PostNegationTrigger
import org.northshore.cbri.type.PreNegationTrigger
import org.northshore.cbri.type.PseudoNegationTrigger

// ----------------------------------------------------------------------------
// patterns
// ----------------------------------------------------------------------------
trigger_pre_negation = ~/(?i)\b((less\s+than)|(no\s+evidence(\s+of|for)?)|(absence\s+of)|(cannot)|(cannot\s+see)|(checked\s+for)|(declined)|(declines)|(denied)|(denies)|(denying)|(evaluate\s+for)|(fails\s+to\s+reveal)|(free\s+of)|(negative\s+for)|(never\s+developed)|(never\s+had)|(no)|(no\s+abnormal)|(no\s+cause\s+of)|(no\s+complaints\s+of)|(no\s+evidence)|(no\s+new\s+evidence)|(no\s+other\s+evidence)|(no\s+evidence\s+to\s+suggest)|(no\s+findings\s+of)|(no\s+findings\s+to\s+indicate)|(no\s+mammographic\s+evidence\s+of)|(no\s+new)|(no\s+radiographic\s+evidence\s+of)|(no\s+sign\s+of)|(no\s+significant)|(no\s+signs\s+of)|(no\s+suggestion\s+of)|(no\s+suspicious)|(not)|(not\s+appear)|(not\s+appreciate)|(not\s+associated\s+with)|(not\s+complain\s+of)|(not\s+demonstrate)|(not\s+exhibit)|(not\s+feel)|(not\s+had)|(not\s+have)|(not\s+know\s+of)|(not\s+known\s+to\s+have)|(not\s+reveal)|(not\s+see)|(not\s+to\s+be)|(patient\s+was\s+not)|(rather\s+than)|(resolved)|(test\s+for)|(to\s+exclude)|(unremarkable\s+for)|(with\s+no)|(without)|(without\s+any\s+evidence\s+of)|(without\s+evidence)|(without\s+indication\s+of)|(without\s+sign\s+of)|(rules\s+out)|(rules\s+him\s+out)|(rules\s+her\s+out)|(rules\s+the\s+patient\s+out)|(rules\s+out\s+for)|(rules\s+him\s+out\s+for)|(rules\s+her\s+out\s+for)|(rules\s+the\s+patient\s+out\s+for)|(ruled\s+out)|(ruled\s+him\s+out)|(ruled\s+her\s+out)|(ruled\s+the\s+patient\s+out)|(ruled\s+out\s+for)|(ruled\s+him\s+out\s+for)|(ruled\s+her\s+out\s+for)|(ruled\s+the\s+patient\s+out\s+for)|(ruled\s+out\s+against)|(ruled\s+him\s+out\s+against)|(ruled\s+her\s+out\s+against)|(ruled\s+the\s+patient\s+out\s+against)|(did\s+rule\s+out)|(did\s+rule\s+out\s+for)|(did\s+rule\s+out\s+against)|(did\s+rule\s+him\s+out)|(did\s+rule\s+her\s+out)|(did\s+rule\s+the\s+patient\s+out)|(did\s+rule\s+him\s+out\s+for)|(did\s+rule\s+her\s+out\s+for)|(did\s+rule\s+him\s+out\s+against)|(did\s+rule\s+her\s+out\s+against)|(did\s+rule\s+the\s+patient\s+out\s+for)|(did\s+rule\s+the\s+patient\s+out\s+against)|(can\s+rule\s+out)|(can\s+rule\s+out\s+for)|(can\s+rule\s+out\s+against)|(can\s+rule\s+him\s+out)|(can\s+rule\s+her\s+out)|(can\s+rule\s+the\s+patient\s+out)|(can\s+rule\s+him\s+out\s+for)|(can\s+rule\s+her\s+out\s+for)|(can\s+rule\s+the\s+patinet\s+out\s+for)|(can\s+rule\s+him\s+out\s+against)|(can\s+rule\s+her\s+out\s+against)|(can\s+rule\s+the\s+patinet\s+out\s+against)|(adequate\s+to\s+rule\s+out)|(adequate\s+to\s+rule\s+him\s+out)|(adequate\s+to\s+rule\s+her\s+out)|(adequate\s+to\s+rule\s+the\s+patient\s+out)|(adequate\s+to\s+rule\s+out\s+for)|(adequate\s+to\s+rule\s+him\s+out\s+for)|(adequate\s+to\s+rule\s+her\s+out\s+for)|(adequate\s+to\s+rule\s+the\s+patient\s+out\s+for)|(adequate\s+to\s+rule\s+the\s+patient\s+out\s+against)|(sufficient\s+to\s+rule\s+out)|(sufficient\s+to\s+rule\s+him\s+out)|(sufficient\s+to\s+rule\s+her\s+out)|(sufficient\s+to\s+rule\s+the\s+patient\s+out)|(sufficient\s+to\s+rule\s+out\s+for)|(sufficient\s+to\s+rule\s+him\s+out\s+for)|(sufficient\s+to\s+rule\s+her\s+out\s+for)|(sufficient\s+to\s+rule\s+the\s+patient\s+out\s+for)|(sufficient\s+to\s+rule\s+out\s+against)|(sufficient\s+to\s+rule\s+him\s+out\s+against)|(sufficient\s+to\s+rule\s+her\s+out\s+against)|(sufficient\s+to\s+rule\s+the\s+patient\s+out\s+against)|(resolution\s+of))\b/
trigger_possible_pre_negation = ~/(?i)\b((rule\s+out)|(r\/o)|(ro)|(rule\s+him\s+out)|(rule\s+her\s+out)|(rule\s+the\s+patient\s+out)|(rule\s+out\s+for)|(rule\s+him\s+out\s+for)|(rule\s+her\s+out\s+for)|(rule\s+the\s+patinet\s+out\s+for)|(be\s+ruled\s+out\s+for)|(should\s+be\s+ruled\s+out\s+for)|(ought\s+to\s+be\s+ruled\s+out\s+for)|(may\s+be\s+ruled\s+out\s+for)|(might\s+be\s+ruled\s+out\s+for)|(could\s+be\s+ruled\s+out\s+for)|(will\s+be\s+ruled\s+out\s+for)|(can\s+be\s+ruled\s+out\s+for)|(must\s+be\s+ruled\s+out\s+for)|(is\s+to\s+be\s+ruled\s+out\s+for)|(what\s+must\s+be\s+ruled\s+out\s+is)|(suggestive\s+of))\b/
trigger_post_negation = ~/(?i)\b((unlikely)|(was\s+ruled\s+out)|(is\s+ruled\s+out)|(are\s+ruled\s+out)|(have\s+been\s+ruled\s+out)|(has\s+been\s+ruled\s+out)|(is\s+not\s+demonstrated)|(have\s+resolved))\b/
trigger_possible_post_negation = ~/(?i)\b((did\s+not\s+rule\s+out)|(not\s+ruled\s+out)|(not\s+been\s+ruled\s+out)|(being\s+ruled\s+out)|(be\s+ruled\s+out)|(should\s+be\s+ruled\s+out)|(ought\s+to\s+be\s+ruled\s+out)|(may\s+be\s+ruled\s+out)|(might\s+be\s+ruled\s+out)|(could\s+be\s+ruled\s+out)|(will\s+be\s+ruled\s+out)|(can\s+be\s+ruled\s+out)|(must\s+be\s+ruled\s+out)|(is\s+to\s+be\s+ruled\s+out))\b/
trigger_pseudo_negation = ~/(?i)\b((no\s+increase)|(no\s+suspicious\s+change)|(no\s+significant\s+change)|(no\s+change)|(no\s+interval\s+change)|(no\s+definite\s+change)|(no\s+significant\s+interval\s+change)|(not\s+extend)|(not\s+cause)|(not\s+drain)|(not\s+certain\s+if)|(not\s+certain\s+whether)|(gram\s+negative)|(without\s+difficulty)|(not\s+necessarily)|(not\s+only)|(no\s+other))\b/
scope_terminator_presentation = ~/(?i)\b((presented)|(presents)|(presenting)|(complains)|(complaining)|(coming\s+in\s+with)|(comes\s+in\s+with)|(was\s+found)|(states)|(stated)|(reports)|(reported)|(currently)|(today)|(noted))\b/
scope_terminator_cause = ~/(?i)\b((since)|(because)|(cause\s+of)|(source\s+of)|(reason\s+of)|(etiology\s+of)|(origin\s+of)|(cause\s+for)|(source\s+for)|(reason\s+for)|(etiology\s+for)|(origin\s+for)|(cause\s+for)|(causes\s+of)|(sources\s+of)|(reasons\s+of)|(origins\s+of)|(causes\s+for)|(sources\s+for)|(reasons\s+for)|(origins\s+for)|(causes\s+for)|(associated\s+with)|(secondary\s+to)|(trigger\s+event\s+for))\b/
scope_terminator_conj = ~/(?i)\b((and)|(so)|(but)|(however)|(yet)|(though)|(although)|(aside\s+from)|(except)|(apart\s+from)|(remain.*))\b/
scope_terminator_temporal = ~/(?i)\b((before)|(after)|(while)|(recent))\b/
scope_terminator_section_breaks = ~/(?i)\b((\d+\.\D)|(\:))\b/
scope_terminator_ytex = ~/(?i)\b((but)|(however)|(nevertheless)|(yet)|(though)|(although)|(still)|(aside\s+from)|(except)|(apart\s+from)|(secondary\s+to)|(as\s+the\s+cause\s+of)|(as\s+the\s+source\s+of)|(as\s+the\s+reason\s+of)|(as\s+the\s+etiology\s+of)|(as\s+the\s+origin\s+of)|(as\s+the\s+cause\s+for)|(as\s+the\s+source\s+for)|(as\s+the\s+reason\s+for)|(as\s+the\s+etiology\s+for)|(as\s+the\s+origin\s+for)|(as\s+the\s+secondary\s+cause\s+of)|(as\s+the\s+secondary\s+source\s+of)|(as\s+the\s+secondary\s+reason\s+of)|(as\s+the\s+secondary\s+etiology\s+of)|(as\s+the\s+secondary\s+origin\s+of)|(as\s+the\s+secondary\s+cause\s+for)|(as\s+the\s+secondary\s+source\s+for)|(as\s+the\s+secondary\s+reason\s+for)|(as\s+the\s+secondary\s+etiology\s+for)|(as\s+the\s+secondary\s+origin\s+for)|(as\s+a\s+cause\s+of)|(as\s+a\s+source\s+of)|(as\s+a\s+reason\s+of)|(as\s+a\s+etiology\s+of)|(as\s+a\s+cause\s+for)|(as\s+a\s+source\s+for)|(as\s+a\s+reason\s+for)|(as\s+a\s+etiology\s+for)|(as\s+a\s+secondary\s+cause\s+of)|(as\s+a\s+secondary\s+source\s+of)|(as\s+a\s+secondary\s+reason\s+of)|(as\s+a\s+secondary\s+etiology\s+of)|(as\s+a\s+secondary\s+origin\s+of)|(as\s+a\s+secondary\s+cause\s+for)|(as\s+a\s+secondary\s+source\s+for)|(as\s+a\s+secondary\s+reason\s+for)|(as\s+a\s+secondary\s+etiology\s+for)|(as\s+a\s+secondary\s+origin\s+for)|(as\s+an\s+cause\s+of)|(as\s+an\s+source\s+of)|(as\s+an\s+reason\s+of)|(as\s+an\s+etiology\s+of)|(as\s+an\s+origin\s+of)|(as\s+an\s+cause\s+for)|(as\s+an\s+source\s+for)|(as\s+an\s+reason\s+for)|(as\s+an\s+etiology\s+for)|(as\s+an\s+origin\s+for)|(as\s+an\s+secondary\s+cause\s+of)|(as\s+an\s+secondary\s+source\s+of)|(as\s+an\s+secondary\s+reason\s+of)|(as\s+an\s+secondary\s+etiology\s+of)|(as\s+an\s+secondary\s+origin\s+of)|(as\s+an\s+secondary\s+cause\s+for)|(as\s+an\s+secondary\s+source\s+for)|(as\s+an\s+secondary\s+reason\s+for)|(as\s+an\s+secondary\s+etiology\s+for)|(as\s+an\s+secondary\s+origin\s+for)|(cause\s+of)|(cause\s+for)|(causes\s+of)|(causes\s+for)|(source\s+of)|(source\s+for)|(sources\s+of)|(sources\s+for)|(reason\s+of)|(reason\s+for)|(reasons\s+of)|(reasons\s+for)|(etiology\s+of)|(etiology\s+for)|(trigger\s+event\s+for)|(origin\s+of)|(origin\s+for)|(origins\s+of)|(origins\s+for)|(other\s+possibilities\s+of))\b/

// ----------------------------------------------------------------------------
// scope of algorithm is a sentence containing an EntityMention
// ----------------------------------------------------------------------------
Collection<Sentence> sents = select type:Sentence, filter:contains(EntityMention)

// ----------------------------------------------------------------------------
// mark prenegation triggers
// ----------------------------------------------------------------------------
applyPatterns(
    sents,
    [trigger_pre_negation, trigger_possible_pre_negation],
    { AnnotationMatcher m -> 
        create(type:PreNegationTrigger, begin:m.start(0), end:m.end(0)) }
    )

// ----------------------------------------------------------------------------
// mark postnegation triggers
// ----------------------------------------------------------------------------
applyPatterns(
    sents,
    [trigger_post_negation, trigger_possible_post_negation],
    { AnnotationMatcher m -> 
        create(type:PostNegationTrigger, begin:m.start(0), end:m.end(0)) }
    )

// ----------------------------------------------------------------------------
// mark pseudo-negation triggers
// ----------------------------------------------------------------------------
applyPatterns(
    sents,
    [trigger_pseudo_negation],
    { AnnotationMatcher m -> 
        create(type:PseudoNegationTrigger, begin:m.start(0), end:m.end(0)) }
    )

// remove negation triggers contained inside pseudo-negation triggers
select(type:PseudoNegationTrigger).each { PseudoNegationTrigger pseudo ->
    select(type:NegationTrigger, filter:coveredBy(pseudo)).each { NegationTrigger neg ->
        neg.removeFromIndexes()
    }
}

// ----------------------------------------------------------------------------
// create negation scopes
// ----------------------------------------------------------------------------
applyPatterns(
    sents,
    [scope_terminator_presentation, scope_terminator_cause, scope_terminator_conj, scope_terminator_temporal, scope_terminator_section_breaks],
    { AnnotationMatcher m -> create(type:NegationScopeTerminator, begin:m.start(0), end:m.end(0)) }
    )

// create a negation scope between each pair of negation scope terminators
pattern = ~/(?<N1>@NegationScopeTerminator)(?=(?<N2>@NegationScopeTerminator))/
sents.each { Sentence sent ->
    create(type:NegationScopeTerminator, begin:sent.begin, end:sent.begin)
    create(type:NegationScopeTerminator, begin:sent.end, end:sent.end)
    pattern.matcher(coveringAnn:sent, 
        types:[NegationScopeTerminator], 
        includeText:false).each { Map<String, Annotation> binding ->
            create(type:NegationScope, begin:binding.get("N1").end, end:binding.get("N2").begin)
        }
}

// ----------------------------------------------------------------------------
// mark selected entities as negated
// ----------------------------------------------------------------------------
select(type:NegationScope, filter:contains(EntityMention)).each { NegationScope scope ->
    select(type:PreNegationTrigger, filter:coveredBy(scope)).each { PreNegationTrigger trigger ->
        select(type:EntityMention,
        filter:and(coveredBy(scope), after(trigger.end))).each { EntityMention mention ->
            mention.polarity = -1
        }
    }
    select(type:PostNegationTrigger, filter:coveredBy(scope)).each { PostNegationTrigger trigger ->
        select(type:EntityMention,
        filter:and(coveredBy(scope), before(trigger.begin))).each { EntityMention mention ->
            mention.polarity = -1
        }
    }
}
