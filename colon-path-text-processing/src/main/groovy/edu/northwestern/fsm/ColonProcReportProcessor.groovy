package edu.northwestern.fsm

import static edu.northwestern.fsm.ColonConcept.*
import static org.northshore.cbri.UIMAUtil.*

import org.apache.ctakes.typesystem.type.refsem.UmlsConcept
import org.apache.ctakes.typesystem.type.textsem.EntityMention
import org.apache.log4j.BasicConfigurator
import org.apache.uima.UIMAFramework
import org.apache.uima.analysis_engine.AnalysisEngine
import org.apache.uima.analysis_engine.AnalysisEngineDescription
import org.apache.uima.analysis_engine.AnalysisEngineProcessException
import org.apache.uima.fit.factory.AnalysisEngineFactory
import org.apache.uima.jcas.JCas
import org.apache.uima.jcas.cas.FSArray
import org.apache.uima.resource.ResourceInitializationException
import org.apache.uima.util.InvalidXMLException
import org.apache.uima.util.XMLInputSource

import com.google.gson.JsonObject

import edu.northwestern.fsm.type.ColonoscopyProcedure
import groovy.util.logging.Log4j

@Log4j
class ColonProcReportProcessor {
    static AnalysisEngine engine
    static JCas jcas
    static {
        try {
            AnalysisEngineDescription desc = UIMAFramework
                    .getXMLParser()
                    .parseAnalysisEngineDescription(
                    new XMLInputSource(
                    ColonProcReportProcessor.getResource('/descriptors/ColonProcReportPipelineNorthwestern.xml')))
            engine = AnalysisEngineFactory.createEngine(desc)
            jcas = engine.newJCas()
        } catch (InvalidXMLException | IOException
        | ResourceInitializationException e) {
            e.printStackTrace()
        }
        assert engine != null
    }

    public synchronized static String[] processText(String text) {
        List<String> retVals = new ArrayList<String>()

        try {
            jcas.reset()
            ////text = text.replaceAll(/(?m)\S$(\r\n?)/, ' ')
            ////println text
            jcas.setDocumentText(text)
            engine.process(jcas)
            ColonoscopyProcedure proc = select(type:ColonoscopyProcedure).find()
            for (int n = 0; n < proc.getIndications().size(); ++n) {
                JsonObject jobject = new JsonObject()
                jobject.addProperty('indication', proc.getIndications(n))
                retVals.add(jobject.toString())
            }
            if (proc.provider != null) {
                JsonObject jobject = new JsonObject()
                jobject.addProperty('provider', proc.provider)
                retVals.add(jobject.toString())
            }
            if (proc.colon_size_1cm) {
                JsonObject jobject = new JsonObject()
                jobject.addProperty('polyp_size_1cm', 1)
                retVals.add(jobject.toString())
            }
            if (proc.colon_count_3) {
                JsonObject jobject = new JsonObject()
                jobject.addProperty('polyp_count_3', 1)
                retVals.add(jobject.toString())
            }
            if (proc.prep_quality) {
                JsonObject jobject = new JsonObject()
                jobject.addProperty('prep_quality', proc.prep_quality)
                retVals.add(jobject.toString())
            }

            select(type:EntityMention).each { EntityMention mention ->
                UmlsConcept concept = extractSnomedConcept(mention);
                // anatomical mention
                if (concept && concept.tui.equals(TUI_BODY_PART)) {
                    JsonObject jobject = new JsonObject();
                    jobject.addProperty('term_site_code', concept.code);
                    jobject.addProperty('polarity', mention.polarity);
                    retVals.add(jobject.toString());
                }
            }            
        } catch (AnalysisEngineProcessException e) {
            e.printStackTrace()
        }

        return retVals.toArray(new String[retVals.size()])
    }

    private static UmlsConcept extractSnomedConcept(EntityMention mention1) {
        UmlsConcept snomedConcept = null;
        FSArray concepts = mention1.getOntologyConceptArr();
        for (int n = 0; n < concepts.size(); n++) {
            UmlsConcept concept = (UmlsConcept) concepts.get(n);
            if (concept.getCodingScheme().equals('SNOMED')) {
                snomedConcept = concept;
                break;
            }
        }
        return snomedConcept;
    }

    static main(args) {
        BasicConfigurator.configure()
        String text = new File('src/test/resources/data/northwestern/sample-colonoscopy-dictated.txt').text
        ColonProcReportProcessor.processText(text).each {
            log.info it
        }
    }
}
