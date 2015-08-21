
/* First created by JCasGen Mon Mar 03 11:40:36 CST 2014 */
package org.apache.ctakes.typesystem.type.temporary.assertion;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** A cue phrase indicating potential negation, uncertainty,
        or conditional
 * Updated by JCasGen Fri Mar 07 16:43:05 CST 2014
 * @generated */
public class AssertionCuePhraseAnnotation_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (AssertionCuePhraseAnnotation_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = AssertionCuePhraseAnnotation_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new AssertionCuePhraseAnnotation(addr, AssertionCuePhraseAnnotation_Type.this);
  			   AssertionCuePhraseAnnotation_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new AssertionCuePhraseAnnotation(addr, AssertionCuePhraseAnnotation_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = AssertionCuePhraseAnnotation.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.apache.ctakes.typesystem.type.temporary.assertion.AssertionCuePhraseAnnotation");
 
  /** @generated */
  final Feature casFeat_id;
  /** @generated */
  final int     casFeatCode_id;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getId(int addr) {
        if (featOkTst && casFeat_id == null)
      jcas.throwFeatMissing("id", "org.apache.ctakes.typesystem.type.temporary.assertion.AssertionCuePhraseAnnotation");
    return ll_cas.ll_getIntValue(addr, casFeatCode_id);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setId(int addr, int v) {
        if (featOkTst && casFeat_id == null)
      jcas.throwFeatMissing("id", "org.apache.ctakes.typesystem.type.temporary.assertion.AssertionCuePhraseAnnotation");
    ll_cas.ll_setIntValue(addr, casFeatCode_id, v);}
    
  
 
  /** @generated */
  final Feature casFeat_cuePhraseCategory;
  /** @generated */
  final int     casFeatCode_cuePhraseCategory;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getCuePhraseCategory(int addr) {
        if (featOkTst && casFeat_cuePhraseCategory == null)
      jcas.throwFeatMissing("cuePhraseCategory", "org.apache.ctakes.typesystem.type.temporary.assertion.AssertionCuePhraseAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_cuePhraseCategory);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setCuePhraseCategory(int addr, String v) {
        if (featOkTst && casFeat_cuePhraseCategory == null)
      jcas.throwFeatMissing("cuePhraseCategory", "org.apache.ctakes.typesystem.type.temporary.assertion.AssertionCuePhraseAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_cuePhraseCategory, v);}
    
  
 
  /** @generated */
  final Feature casFeat_cuePhraseAssertionFamily;
  /** @generated */
  final int     casFeatCode_cuePhraseAssertionFamily;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getCuePhraseAssertionFamily(int addr) {
        if (featOkTst && casFeat_cuePhraseAssertionFamily == null)
      jcas.throwFeatMissing("cuePhraseAssertionFamily", "org.apache.ctakes.typesystem.type.temporary.assertion.AssertionCuePhraseAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_cuePhraseAssertionFamily);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setCuePhraseAssertionFamily(int addr, String v) {
        if (featOkTst && casFeat_cuePhraseAssertionFamily == null)
      jcas.throwFeatMissing("cuePhraseAssertionFamily", "org.apache.ctakes.typesystem.type.temporary.assertion.AssertionCuePhraseAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_cuePhraseAssertionFamily, v);}
    
  
 
  /** @generated */
  final Feature casFeat_cuePhraseFirstWord;
  /** @generated */
  final int     casFeatCode_cuePhraseFirstWord;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getCuePhraseFirstWord(int addr) {
        if (featOkTst && casFeat_cuePhraseFirstWord == null)
      jcas.throwFeatMissing("cuePhraseFirstWord", "org.apache.ctakes.typesystem.type.temporary.assertion.AssertionCuePhraseAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_cuePhraseFirstWord);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setCuePhraseFirstWord(int addr, String v) {
        if (featOkTst && casFeat_cuePhraseFirstWord == null)
      jcas.throwFeatMissing("cuePhraseFirstWord", "org.apache.ctakes.typesystem.type.temporary.assertion.AssertionCuePhraseAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_cuePhraseFirstWord, v);}
    
  
 
  /** @generated */
  final Feature casFeat_cuePhrase;
  /** @generated */
  final int     casFeatCode_cuePhrase;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getCuePhrase(int addr) {
        if (featOkTst && casFeat_cuePhrase == null)
      jcas.throwFeatMissing("cuePhrase", "org.apache.ctakes.typesystem.type.temporary.assertion.AssertionCuePhraseAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_cuePhrase);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setCuePhrase(int addr, String v) {
        if (featOkTst && casFeat_cuePhrase == null)
      jcas.throwFeatMissing("cuePhrase", "org.apache.ctakes.typesystem.type.temporary.assertion.AssertionCuePhraseAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_cuePhrase, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public AssertionCuePhraseAnnotation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_id = jcas.getRequiredFeatureDE(casType, "id", "uima.cas.Integer", featOkTst);
    casFeatCode_id  = (null == casFeat_id) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_id).getCode();

 
    casFeat_cuePhraseCategory = jcas.getRequiredFeatureDE(casType, "cuePhraseCategory", "uima.cas.String", featOkTst);
    casFeatCode_cuePhraseCategory  = (null == casFeat_cuePhraseCategory) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_cuePhraseCategory).getCode();

 
    casFeat_cuePhraseAssertionFamily = jcas.getRequiredFeatureDE(casType, "cuePhraseAssertionFamily", "uima.cas.String", featOkTst);
    casFeatCode_cuePhraseAssertionFamily  = (null == casFeat_cuePhraseAssertionFamily) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_cuePhraseAssertionFamily).getCode();

 
    casFeat_cuePhraseFirstWord = jcas.getRequiredFeatureDE(casType, "cuePhraseFirstWord", "uima.cas.String", featOkTst);
    casFeatCode_cuePhraseFirstWord  = (null == casFeat_cuePhraseFirstWord) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_cuePhraseFirstWord).getCode();

 
    casFeat_cuePhrase = jcas.getRequiredFeatureDE(casType, "cuePhrase", "uima.cas.String", featOkTst);
    casFeatCode_cuePhrase  = (null == casFeat_cuePhrase) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_cuePhrase).getCode();

  }
}



    