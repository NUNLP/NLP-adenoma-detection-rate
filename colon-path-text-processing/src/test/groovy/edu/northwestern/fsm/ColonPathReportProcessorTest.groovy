package edu.northwestern.fsm

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue
import static org.junit.Assert.fail
import static org.northshore.cbri.UIMAUtil.*
import groovy.util.logging.Log4j

import org.apache.ctakes.typesystem.type.relation.RelationArgument
import org.apache.ctakes.typesystem.type.relation.UMLSRelation
import org.apache.ctakes.typesystem.type.textsem.EntityMention
import org.apache.log4j.BasicConfigurator
import org.apache.log4j.Level
import org.apache.uima.analysis_engine.AnalysisEngine
import org.apache.uima.fit.factory.AggregateBuilder
import org.apache.uima.fit.factory.JCasFactory
import org.apache.uima.fit.pipeline.SimplePipeline
import org.apache.uima.fit.util.JCasUtil
import org.apache.uima.jcas.JCas
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

import com.google.common.base.Charsets
import com.google.common.io.Resources
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParser

@Log4j
class ColonPathReportProcessorTest {

    @BeforeClass
    static void setupClass() {
        BasicConfigurator.configure()
    }

    @Before
    void setUp() {
        log.setLevel(Level.INFO)
    }
    
    @Test
    void smokeTest() {
        AggregateBuilder builder = ColonPathReportPipelineMaker.makeNorthwesternPipeline()
        AnalysisEngine engine = builder.createAggregate()
                
        URL url = Resources.getResource(ColonPathReportProcessorTest, "/data/northwestern/sample-pathology-3.txt");
        String text = Resources.toString(url, Charsets.UTF_8);
        JCas jCas = JCasFactory.createJCas()
        jCas.setDocumentText(text)
        SimplePipeline.runPipeline(jCas, engine);

        Collection entityMentions = JCasUtil.select(jCas, EntityMention);
        assertTrue(entityMentions.size() > 0);

        Collection relations = JCasUtil.select(jCas, UMLSRelation);
        System.out.println("Number of relations: " + relations.size());
        for (UMLSRelation relation : relations) {
            RelationArgument arg1 = relation.getArg1();
            RelationArgument arg2 = relation.getArg2();
            String role1 = arg1.getRole();
            String role2 = arg2.getRole();
            EntityMention mention1 = (EntityMention) arg1.getArgument();
            EntityMention mention2 = (EntityMention) arg2.getArgument();
            System.out.print(role1 + " = " + mention1.getCoveredText() + "; ");
            System.out.println(role2 + " = " + mention2.getCoveredText());
        }
        assertTrue(relations.size() == 4);
    }
    
    @Test
    void smokeTest2() {
        AggregateBuilder builder = ColonPathReportPipelineMaker.makeNorthwesternPipeline()
        AnalysisEngine engine = builder.createAggregate()
                
        URL url = Resources.getResource(ColonPathReportProcessorTest, "/data/northwestern/sample-pathology-4.txt");
        String text = Resources.toString(url, Charsets.UTF_8);
        JCas jCas = JCasFactory.createJCas()
        jCas.setDocumentText(text)
        SimplePipeline.runPipeline(jCas, engine);

        Collection entityMentions = JCasUtil.select(jCas, EntityMention);
        assertTrue(entityMentions.size() > 0);

        Collection relations = JCasUtil.select(jCas, UMLSRelation);
        println "Number of relations: " + relations.size()
        for (UMLSRelation relation : relations) {
            RelationArgument arg1 = relation.getArg1();
            RelationArgument arg2 = relation.getArg2();
            String role1 = arg1.getRole();
            String role2 = arg2.getRole();
            EntityMention mention1 = (EntityMention) arg1.getArgument();
            EntityMention mention2 = (EntityMention) arg2.getArgument();
            System.out.print(role1 + " = " + mention1.getCoveredText() + "; ");
            System.out.println(role2 + " = " + mention2.getCoveredText());
        }
        assertTrue(relations.size() == 6);
    }

    @Test
    void testColonPathReportProcessor() {
        String text = new File('src/test/resources/data/northwestern/sample-pathology-4.txt').text
        String[] nlp_results = ColonPathReportProcessor.processText(text)
        assert nlp_results.length == 5
        
        nlp_results.each { String result ->
            println result
            JsonElement jelement = new JsonParser().parse(result);
            JsonObject jobject = jelement.getAsJsonObject();
            String finding = jobject.get("Finding").getAsString();
            String location = jobject.get("LocationOf").getAsString();
            String distance = jobject.get("Distance").getAsString();
            String villous_features = jobject.get("Villous").getAsString();
            String high_grade_dysplasia = jobject.get("HighGradeDysplasia").getAsString();
            String polarity = jobject.get("polarity").getAsInt();
        }
    }
}
