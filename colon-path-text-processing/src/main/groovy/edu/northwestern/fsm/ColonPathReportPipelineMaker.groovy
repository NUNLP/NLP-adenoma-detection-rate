package edu.northwestern.fsm

import org.apache.uima.analysis_engine.AnalysisEngineDescription
import org.apache.uima.fit.factory.AggregateBuilder
import org.apache.uima.fit.factory.AnalysisEngineFactory
import org.apache.uima.fit.factory.TypeSystemDescriptionFactory
import org.apache.uima.resource.ResourceInitializationException
import org.apache.uima.resource.metadata.TypeSystemDescription
import org.apache.uima.util.InvalidXMLException
import org.northshore.cbri.GroovyAnnotator
import org.northshore.cbri.SentenceDetector
import org.northshore.cbri.SentenceDetector_cTAKES
import org.xml.sax.SAXException

class ColonPathReportPipelineMaker {
    /**
     * 
     * @return
     * @throws ResourceInitializationException
     * @throws InvalidXMLException
     * @throws IOException
     * @throws SAXException
     */
    static AggregateBuilder makeNorthwesternPipeline() throws ResourceInitializationException,
    InvalidXMLException, IOException, SAXException {
        // Build type system description
        TypeSystemDescription tsd = TypeSystemDescriptionFactory.createTypeSystemDescription()
        tsd.resolveImports()
        

        // Segmenter
        AnalysisEngineDescription segments = AnalysisEngineFactory.createEngineDescription(GroovyAnnotator,
                tsd, GroovyAnnotator.PARAM_SCRIPT_FILE, 'groovy/SimpleSegmenter.groovy')

        // Sentence detector
        AnalysisEngineDescription sentences = AnalysisEngineFactory.createEngineDescription(
                SentenceDetector_cTAKES,
                SentenceDetector_cTAKES.SD_MODEL_FILE_PARAM, 'models/sd-med-model.zip')

        // Finding segmenter
        AnalysisEngineDescription findingSegments = AnalysisEngineFactory.createEngineDescription(GroovyAnnotator,
                tsd, GroovyAnnotator.PARAM_SCRIPT_FILE, 'groovy/northwestern/PathReportFindingSegmenter.groovy')

        // Concept detector
        AnalysisEngineDescription concepts = AnalysisEngineFactory.createEngineDescription(GroovyAnnotator,
                tsd, GroovyAnnotator.PARAM_SCRIPT_FILE, 'groovy/northwestern/PathReportConceptDetector.groovy')

        // Relation detector
        AnalysisEngineDescription relations = AnalysisEngineFactory.createEngineDescription(GroovyAnnotator,
                tsd, GroovyAnnotator.PARAM_SCRIPT_FILE, 'groovy/northwestern/PathReportRelationExtractor.groovy')

        // Negation detector
        AnalysisEngineDescription negation = AnalysisEngineFactory.createEngineDescription(GroovyAnnotator,
            tsd, GroovyAnnotator.PARAM_SCRIPT_FILE, 'groovy/NegationDetector.groovy')
        
        // Build the aggregate
        AggregateBuilder builder = new AggregateBuilder()
		builder.with {
            add(segments)
            add(sentences)
            add(findingSegments)
			add(concepts)
            add(negation)
            add(relations)
		}

        AnalysisEngineDescription desc = builder.createAggregateDescription()
        PrintWriter writer = new PrintWriter(new File('src/main/resources/descriptors/ColonPathReportPipelineNorthwestern.xml'))
        desc.toXML(writer)
        writer.close()

        builder
    }
}
