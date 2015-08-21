package edu.northwestern.fsm

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue
import static org.junit.Assert.fail
import static org.northshore.cbri.UIMAUtil.*
import edu.northwestern.fsm.type.ColonoscopyProcedure
import groovy.util.logging.Log4j

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

@Log4j
class ColonProcReportProcessorTest {

    @BeforeClass
    static void setupClass() {
        BasicConfigurator.configure()
    }

    @Before
    void setUp() {
        log.setLevel(Level.INFO)
    }
    
    @Test
    void testNorthwestern() {
        AggregateBuilder builder = ColonProcReportPipelineMaker.makeNorthwesternPipeline()
        AnalysisEngine engine = builder.createAggregate()
                
        URL url = Resources.getResource(ColonProcReportProcessorTest, "/data/northwestern/sample-colonoscopy-dictated.txt");
        String text = Resources.toString(url, Charsets.UTF_8);
        JCas jCas = JCasFactory.createJCas()
        jCas.setDocumentText(text)
        SimplePipeline.runPipeline(jCas, engine);

        
        ColonoscopyProcedure pm = JCasUtil.select(jCas, ColonoscopyProcedure).find()
        assert pm != null
        
        println pm
    }
    
    @Test
    void testColonProcReportProcessor() {                
        URL url = Resources.getResource(ColonProcReportProcessorTest, "/data/northwestern/sample-colonoscopy-dictated.txt");
        String text = Resources.toString(url, Charsets.UTF_8);
        String[] retval = ColonProcReportProcessor.processText(text)
        print "${retval}"
    }
}
