package edu.northwestern.fsm

import groovy.util.logging.Log4j

import org.apache.log4j.BasicConfigurator
import org.apache.uima.analysis_engine.AnalysisEngineDescription
import org.apache.uima.fit.factory.AggregateBuilder
import org.apache.uima.fit.factory.AnalysisEngineFactory
import org.apache.uima.fit.factory.TypeSystemDescriptionFactory
import org.apache.uima.resource.ResourceInitializationException
import org.apache.uima.resource.metadata.TypeSystemDescription
import org.apache.uima.util.InvalidXMLException
import org.northshore.cbri.GroovyAnnotator
import org.northshore.cbri.SentenceDetector
import org.xml.sax.SAXException

@Log4j
class ColonProcReportPipelineMaker {
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
        
        // Sentence detector
        AnalysisEngineDescription sentDetector = AnalysisEngineFactory.createEngineDescription(
                SentenceDetector,
                SentenceDetector.SD_SPLIT_PATTERN, /:|;|[\r\n]+\s+(?=\-)/,
                SentenceDetector.SD_MODEL_FILE_PARAM, 'models/sd-med-model.zip')
//        AnalysisEngineDescription sentDetector = AnalysisEngineFactory.createEngineDescription(
//                SentenceDetector_cTAKES,
//                SentenceDetector_cTAKES.SD_MODEL_FILE_PARAM, 'models/sd-med-model.zip')

        // Annotators: Groovy
        AnalysisEngineDescription colonReportInit = AnalysisEngineFactory.createEngineDescription(GroovyAnnotator,
                tsd, GroovyAnnotator.PARAM_SCRIPT_FILE, "groovy/northwestern/ColonReportInit.groovy");
        AnalysisEngineDescription colonReportSegmenter = AnalysisEngineFactory.createEngineDescription(GroovyAnnotator,
                tsd, GroovyAnnotator.PARAM_SCRIPT_FILE, "groovy/northwestern/ColonReportSegmenter.groovy");
        AnalysisEngineDescription providerDetector = AnalysisEngineFactory.createEngineDescription(GroovyAnnotator,
                tsd, GroovyAnnotator.PARAM_SCRIPT_FILE, "groovy/northwestern/ColonReportProvider.groovy");
        AnalysisEngineDescription indicationDetector = AnalysisEngineFactory.createEngineDescription(GroovyAnnotator,
                tsd, GroovyAnnotator.PARAM_SCRIPT_FILE, "groovy/northwestern/ColonReportIndication.groovy");
        AnalysisEngineDescription completionDetector = AnalysisEngineFactory.createEngineDescription(GroovyAnnotator,
                tsd, GroovyAnnotator.PARAM_SCRIPT_FILE, "groovy/northwestern/ColonReportCompleted.groovy");
        AnalysisEngineDescription prepQualityDetector = AnalysisEngineFactory.createEngineDescription(GroovyAnnotator,
                tsd, GroovyAnnotator.PARAM_SCRIPT_FILE, "groovy/northwestern/ColonReportPrepQuality.groovy");
        AnalysisEngineDescription polypDetector = AnalysisEngineFactory.createEngineDescription(GroovyAnnotator,
                tsd, GroovyAnnotator.PARAM_SCRIPT_FILE, "groovy/northwestern/ColonReportPolyps.groovy");
        AnalysisEngineDescription negationDetector = AnalysisEngineFactory.createEngineDescription(GroovyAnnotator,
                tsd, GroovyAnnotator.PARAM_SCRIPT_FILE, "groovy/northwestern/ColonReportsNegationDetector.groovy");
            AnalysisEngineDescription postProcessor = AnalysisEngineFactory.createEngineDescription(GroovyAnnotator,
                tsd, GroovyAnnotator.PARAM_SCRIPT_FILE, "groovy/northwestern/ColonReportsPostProcessor.groovy");

        // Build the aggregate
        AggregateBuilder builder = new AggregateBuilder()
        builder.with {
            add(colonReportInit)
            add(colonReportSegmenter)
            add(sentDetector)
            add(providerDetector)
            add(indicationDetector)
            add(completionDetector)
            add(prepQualityDetector)
            add(polypDetector)
            add(negationDetector)
            add(postProcessor)
        }

        // Write out the descriptor
        AnalysisEngineDescription desc = builder.createAggregateDescription()
        PrintWriter writer = new PrintWriter(new File('src/main/resources/descriptors/ColonProcReportPipelineNorthwestern.xml'))
        desc.toXML(writer)
        writer.close()

        builder
    }
    
    static void main(args) {
        BasicConfigurator.configure()
        ColonProcReportPipelineMaker.makeNorthwesternPipeline()
    }
}
