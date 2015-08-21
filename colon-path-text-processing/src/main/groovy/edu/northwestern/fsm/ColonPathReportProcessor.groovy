package edu.northwestern.fsm

import static edu.northwestern.fsm.ColonConcept.*
import static org.northshore.cbri.UIMAUtil.*

import java.util.ArrayList
import java.util.Collection
import java.util.List

import org.apache.ctakes.typesystem.type.refsem.UmlsConcept
import org.apache.ctakes.typesystem.type.relation.RelationArgument
import org.apache.ctakes.typesystem.type.relation.UMLSRelation
import org.apache.ctakes.typesystem.type.textsem.EntityMention
import org.apache.ctakes.typesystem.type.textspan.Segment
import org.apache.uima.UIMAFramework
import org.apache.uima.analysis_engine.AnalysisEngine
import org.apache.uima.analysis_engine.AnalysisEngineDescription
import org.apache.uima.analysis_engine.AnalysisEngineProcessException
import org.apache.uima.fit.factory.AnalysisEngineFactory
import org.apache.uima.fit.util.JCasUtil
import org.apache.uima.jcas.JCas
import org.apache.uima.jcas.cas.FSArray
import org.apache.uima.resource.ResourceInitializationException
import org.apache.uima.util.InvalidXMLException
import org.apache.uima.util.XMLInputSource

import com.google.gson.JsonObject

class ColonPathReportProcessor {
    static AnalysisEngine engine
    static JCas jcas
    static {
        try {
            AnalysisEngineDescription desc = UIMAFramework
                    .getXMLParser()
                    .parseAnalysisEngineDescription(
                    new XMLInputSource(
                    ColonPathReportProcessor.class
                    .getResource('/descriptors/ColonPathReportPipelineNorthwestern.xml')))
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
            jcas.setDocumentText(text)
            engine.process(jcas)
            setJCas(jcas)
            select(type:UMLSRelation).each { UMLSRelation relation ->
                RelationArgument arg1 = relation.getArg1()
                RelationArgument arg2 = relation.getArg2()
                EntityMention mention1 = (EntityMention) arg1.argument
                EntityMention mention2 = (EntityMention) arg2.argument

                if (arg1.role == ROLE_FINDING && arg2.role == ROLE_LOCATION) {
                    JsonObject jobject = new JsonObject()
                    UmlsConcept concept1 = mention1.ontologyConcepts.find { it.codingScheme == CODING_SCHEME_SNOMED }
                    UmlsConcept concept2 = mention2.ontologyConcepts.find { it.codingScheme == CODING_SCHEME_SNOMED }
                    jobject.addProperty(arg1.getRole(), concept1.getCode())
                    jobject.addProperty(arg2.getRole(), concept2.getCode())

                    concept2 = mention2.ontologyConcepts.find { it.codingScheme == CODING_SCHEME_UCUM }
                    if (concept2) {
                        jobject.addProperty('Distance', concept2.code)
                    }
                    else {
                        jobject.addProperty('Distance', 'NA')
                    }
                    
                    // villous features
                    def villous = select(type:UMLSRelation, filter: { UMLSRelation rel -> 
                        rel.arg1.argument == mention1 && rel.arg2.role == ROLE_VILLOUS && mention1.polarity == 1 && rel.arg2.argument.polarity == 1
                    })
                    if (villous || concept1.cui in [TUBULO_VILLOUS_ADENOMA.cui, VILLOUS_ADENOMA.cui]) {
                        jobject.addProperty('Villous', 1)
                    }
                    else {
                        jobject.addProperty('Villous', 0)
                    }

                    // high-grade dysplasia
                    def dysplasia = select(type:UMLSRelation, filter: { UMLSRelation rel ->
                        rel.arg1.argument == mention1 && rel.arg2.role == ROLE_HIGH_GRADE_DYSPLASIA && mention1.polarity == 1 && rel.arg2.argument.polarity == 1
                    })
                    if (dysplasia) {
                        jobject.addProperty('HighGradeDysplasia', 1)
                    }
                    else {
                        jobject.addProperty('HighGradeDysplasia', 0)
                    }

                    jobject.addProperty('polarity', mention1.getPolarity())
                    retVals.add(jobject.toString())
                } 
            }
        } catch (AnalysisEngineProcessException e) {
            e.printStackTrace()
        }

        return retVals.toArray(new String[retVals.size()])
    }
    
    
    static void main(args) {
        String text = new File('src/test/resources/data/northwestern/sample-pathology-1.txt').text
        ColonPathReportProcessor.processText(text).each { println it }
    }
}
