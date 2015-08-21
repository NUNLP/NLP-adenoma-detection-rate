

/* First created by JCasGen Mon Mar 03 11:40:36 CST 2014 */
package org.apache.ctakes.typesystem.type.temporary.assertion;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** A cue phrase indicating potential negation, uncertainty,
        or conditional
 * Updated by JCasGen Fri Mar 07 16:43:05 CST 2014
 * XML source: C:/WKT/git/northwestern/colon-polyps/colon-path-text-processing -AJG/src/main/resources/descriptors/ColonPolypTypes.xml
 * @generated */
public class AssertionCuePhraseAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(AssertionCuePhraseAnnotation.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected AssertionCuePhraseAnnotation() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public AssertionCuePhraseAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public AssertionCuePhraseAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public AssertionCuePhraseAnnotation(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: id

  /** getter for id - gets 
   * @generated
   * @return value of the feature 
   */
  public int getId() {
    if (AssertionCuePhraseAnnotation_Type.featOkTst && ((AssertionCuePhraseAnnotation_Type)jcasType).casFeat_id == null)
      jcasType.jcas.throwFeatMissing("id", "org.apache.ctakes.typesystem.type.temporary.assertion.AssertionCuePhraseAnnotation");
    return jcasType.ll_cas.ll_getIntValue(addr, ((AssertionCuePhraseAnnotation_Type)jcasType).casFeatCode_id);}
    
  /** setter for id - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setId(int v) {
    if (AssertionCuePhraseAnnotation_Type.featOkTst && ((AssertionCuePhraseAnnotation_Type)jcasType).casFeat_id == null)
      jcasType.jcas.throwFeatMissing("id", "org.apache.ctakes.typesystem.type.temporary.assertion.AssertionCuePhraseAnnotation");
    jcasType.ll_cas.ll_setIntValue(addr, ((AssertionCuePhraseAnnotation_Type)jcasType).casFeatCode_id, v);}    
   
    
  //*--------------*
  //* Feature: cuePhraseCategory

  /** getter for cuePhraseCategory - gets 
   * @generated
   * @return value of the feature 
   */
  public String getCuePhraseCategory() {
    if (AssertionCuePhraseAnnotation_Type.featOkTst && ((AssertionCuePhraseAnnotation_Type)jcasType).casFeat_cuePhraseCategory == null)
      jcasType.jcas.throwFeatMissing("cuePhraseCategory", "org.apache.ctakes.typesystem.type.temporary.assertion.AssertionCuePhraseAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((AssertionCuePhraseAnnotation_Type)jcasType).casFeatCode_cuePhraseCategory);}
    
  /** setter for cuePhraseCategory - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setCuePhraseCategory(String v) {
    if (AssertionCuePhraseAnnotation_Type.featOkTst && ((AssertionCuePhraseAnnotation_Type)jcasType).casFeat_cuePhraseCategory == null)
      jcasType.jcas.throwFeatMissing("cuePhraseCategory", "org.apache.ctakes.typesystem.type.temporary.assertion.AssertionCuePhraseAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((AssertionCuePhraseAnnotation_Type)jcasType).casFeatCode_cuePhraseCategory, v);}    
   
    
  //*--------------*
  //* Feature: cuePhraseAssertionFamily

  /** getter for cuePhraseAssertionFamily - gets which assertion family this cue phrase belongs to
            (negation, uncertainty, or conditional)
   * @generated
   * @return value of the feature 
   */
  public String getCuePhraseAssertionFamily() {
    if (AssertionCuePhraseAnnotation_Type.featOkTst && ((AssertionCuePhraseAnnotation_Type)jcasType).casFeat_cuePhraseAssertionFamily == null)
      jcasType.jcas.throwFeatMissing("cuePhraseAssertionFamily", "org.apache.ctakes.typesystem.type.temporary.assertion.AssertionCuePhraseAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((AssertionCuePhraseAnnotation_Type)jcasType).casFeatCode_cuePhraseAssertionFamily);}
    
  /** setter for cuePhraseAssertionFamily - sets which assertion family this cue phrase belongs to
            (negation, uncertainty, or conditional) 
   * @generated
   * @param v value to set into the feature 
   */
  public void setCuePhraseAssertionFamily(String v) {
    if (AssertionCuePhraseAnnotation_Type.featOkTst && ((AssertionCuePhraseAnnotation_Type)jcasType).casFeat_cuePhraseAssertionFamily == null)
      jcasType.jcas.throwFeatMissing("cuePhraseAssertionFamily", "org.apache.ctakes.typesystem.type.temporary.assertion.AssertionCuePhraseAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((AssertionCuePhraseAnnotation_Type)jcasType).casFeatCode_cuePhraseAssertionFamily, v);}    
   
    
  //*--------------*
  //* Feature: cuePhraseFirstWord

  /** getter for cuePhraseFirstWord - gets first word of cue phrase
   * @generated
   * @return value of the feature 
   */
  public String getCuePhraseFirstWord() {
    if (AssertionCuePhraseAnnotation_Type.featOkTst && ((AssertionCuePhraseAnnotation_Type)jcasType).casFeat_cuePhraseFirstWord == null)
      jcasType.jcas.throwFeatMissing("cuePhraseFirstWord", "org.apache.ctakes.typesystem.type.temporary.assertion.AssertionCuePhraseAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((AssertionCuePhraseAnnotation_Type)jcasType).casFeatCode_cuePhraseFirstWord);}
    
  /** setter for cuePhraseFirstWord - sets first word of cue phrase 
   * @generated
   * @param v value to set into the feature 
   */
  public void setCuePhraseFirstWord(String v) {
    if (AssertionCuePhraseAnnotation_Type.featOkTst && ((AssertionCuePhraseAnnotation_Type)jcasType).casFeat_cuePhraseFirstWord == null)
      jcasType.jcas.throwFeatMissing("cuePhraseFirstWord", "org.apache.ctakes.typesystem.type.temporary.assertion.AssertionCuePhraseAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((AssertionCuePhraseAnnotation_Type)jcasType).casFeatCode_cuePhraseFirstWord, v);}    
   
    
  //*--------------*
  //* Feature: cuePhrase

  /** getter for cuePhrase - gets cue phrase itself (from lookup list)
   * @generated
   * @return value of the feature 
   */
  public String getCuePhrase() {
    if (AssertionCuePhraseAnnotation_Type.featOkTst && ((AssertionCuePhraseAnnotation_Type)jcasType).casFeat_cuePhrase == null)
      jcasType.jcas.throwFeatMissing("cuePhrase", "org.apache.ctakes.typesystem.type.temporary.assertion.AssertionCuePhraseAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((AssertionCuePhraseAnnotation_Type)jcasType).casFeatCode_cuePhrase);}
    
  /** setter for cuePhrase - sets cue phrase itself (from lookup list) 
   * @generated
   * @param v value to set into the feature 
   */
  public void setCuePhrase(String v) {
    if (AssertionCuePhraseAnnotation_Type.featOkTst && ((AssertionCuePhraseAnnotation_Type)jcasType).casFeat_cuePhrase == null)
      jcasType.jcas.throwFeatMissing("cuePhrase", "org.apache.ctakes.typesystem.type.temporary.assertion.AssertionCuePhraseAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((AssertionCuePhraseAnnotation_Type)jcasType).casFeatCode_cuePhrase, v);}    
  }

    