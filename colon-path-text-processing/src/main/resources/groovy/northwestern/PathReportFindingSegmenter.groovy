package groovy.northwestern
import static org.northshore.cbri.UIMAUtil.*

import org.apache.ctakes.typesystem.type.textspan.Sentence
import org.apache.uima.jcas.tcas.Annotation

import edu.northwestern.fsm.type.FindingSegment
import edu.northwestern.fsm.type.FindingSentence
import edu.northwestern.fsm.type.LocSentence
import edu.northwestern.fsm.type.NoteSentence


// assign sentences to categories based on pattern matching
select(type:Sentence).each { Sentence s ->
    if (s.coveredText ==~ ~/.+(:|;)/) create(type:LocSentence, begin:s.begin, end:s.end)
    if (s.coveredText ==~ ~/-{1,3}.+/) create(type:FindingSentence, begin:s.begin, end:s.end)
    if (s.coveredText ==~ ~/NOTE.+/) create(type:NoteSentence, begin:s.begin, end:s.end)
}

// if a sentence has not yet been assigned a category, then
// assign it the category of finding sentence
select(type:Sentence,
    filter:not(or(contains(LocSentence), contains(FindingSentence), contains(NoteSentence)))
    ).each { Sentence s -> 
        create(type:FindingSentence, begin:s.begin, end:s.end) 
        }

// group location and finding sentences into segments
(~/(?<L1>@LocSentence)(?:@FindingSentence)*(?<F1>@FindingSentence)/).matcher(
        coveringAnn:jcas.documentAnnotationFs, 
        types:[LocSentence, FindingSentence],
        includeText:false).each { Map<String, Annotation> binding ->
            create(type:FindingSegment, begin:binding.get("L1").begin, end:binding.get("F1").end)
        }
