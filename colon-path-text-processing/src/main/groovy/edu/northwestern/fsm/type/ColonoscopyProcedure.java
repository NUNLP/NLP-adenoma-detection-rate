

/* First created by JCasGen Wed Mar 05 11:44:59 CST 2014 */
package edu.northwestern.fsm.type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.ctakes.typesystem.type.refsem.Procedure;
import org.apache.uima.jcas.cas.StringArray;


/** 
 * Updated by JCasGen Fri Mar 07 16:43:01 CST 2014
 * XML source: C:/WKT/git/northwestern/colon-polyps/colon-path-text-processing -AJG/src/main/resources/descriptors/ColonPolypTypes.xml
 * @generated */
public class ColonoscopyProcedure extends Procedure {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(ColonoscopyProcedure.class);
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
  protected ColonoscopyProcedure() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public ColonoscopyProcedure(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public ColonoscopyProcedure(JCas jcas) {
    super(jcas);
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
  //* Feature: indications

  /** getter for indications - gets 
   * @generated
   * @return value of the feature 
   */
  public StringArray getIndications() {
    if (ColonoscopyProcedure_Type.featOkTst && ((ColonoscopyProcedure_Type)jcasType).casFeat_indications == null)
      jcasType.jcas.throwFeatMissing("indications", "edu.northwestern.fsm.type.ColonoscopyProcedure");
    return (StringArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((ColonoscopyProcedure_Type)jcasType).casFeatCode_indications)));}
    
  /** setter for indications - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setIndications(StringArray v) {
    if (ColonoscopyProcedure_Type.featOkTst && ((ColonoscopyProcedure_Type)jcasType).casFeat_indications == null)
      jcasType.jcas.throwFeatMissing("indications", "edu.northwestern.fsm.type.ColonoscopyProcedure");
    jcasType.ll_cas.ll_setRefValue(addr, ((ColonoscopyProcedure_Type)jcasType).casFeatCode_indications, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for indications - gets an indexed value - 
   * @generated
   * @param i index in the array to get
   * @return value of the element at index i 
   */
  public String getIndications(int i) {
    if (ColonoscopyProcedure_Type.featOkTst && ((ColonoscopyProcedure_Type)jcasType).casFeat_indications == null)
      jcasType.jcas.throwFeatMissing("indications", "edu.northwestern.fsm.type.ColonoscopyProcedure");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((ColonoscopyProcedure_Type)jcasType).casFeatCode_indications), i);
    return jcasType.ll_cas.ll_getStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((ColonoscopyProcedure_Type)jcasType).casFeatCode_indications), i);}

  /** indexed setter for indications - sets an indexed value - 
   * @generated
   * @param i index in the array to set
   * @param v value to set into the array 
   */
  public void setIndications(int i, String v) { 
    if (ColonoscopyProcedure_Type.featOkTst && ((ColonoscopyProcedure_Type)jcasType).casFeat_indications == null)
      jcasType.jcas.throwFeatMissing("indications", "edu.northwestern.fsm.type.ColonoscopyProcedure");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((ColonoscopyProcedure_Type)jcasType).casFeatCode_indications), i);
    jcasType.ll_cas.ll_setStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((ColonoscopyProcedure_Type)jcasType).casFeatCode_indications), i, v);}
   
    
  //*--------------*
  //* Feature: provider

  /** getter for provider - gets 
   * @generated
   * @return value of the feature 
   */
  public String getProvider() {
    if (ColonoscopyProcedure_Type.featOkTst && ((ColonoscopyProcedure_Type)jcasType).casFeat_provider == null)
      jcasType.jcas.throwFeatMissing("provider", "edu.northwestern.fsm.type.ColonoscopyProcedure");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ColonoscopyProcedure_Type)jcasType).casFeatCode_provider);}
    
  /** setter for provider - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setProvider(String v) {
    if (ColonoscopyProcedure_Type.featOkTst && ((ColonoscopyProcedure_Type)jcasType).casFeat_provider == null)
      jcasType.jcas.throwFeatMissing("provider", "edu.northwestern.fsm.type.ColonoscopyProcedure");
    jcasType.ll_cas.ll_setStringValue(addr, ((ColonoscopyProcedure_Type)jcasType).casFeatCode_provider, v);}    
   
    
  //*--------------*
  //* Feature: colon_size_1cm

  /** getter for colon_size_1cm - gets 
   * @generated
   * @return value of the feature 
   */
  public boolean getColon_size_1cm() {
    if (ColonoscopyProcedure_Type.featOkTst && ((ColonoscopyProcedure_Type)jcasType).casFeat_colon_size_1cm == null)
      jcasType.jcas.throwFeatMissing("colon_size_1cm", "edu.northwestern.fsm.type.ColonoscopyProcedure");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((ColonoscopyProcedure_Type)jcasType).casFeatCode_colon_size_1cm);}
    
  /** setter for colon_size_1cm - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setColon_size_1cm(boolean v) {
    if (ColonoscopyProcedure_Type.featOkTst && ((ColonoscopyProcedure_Type)jcasType).casFeat_colon_size_1cm == null)
      jcasType.jcas.throwFeatMissing("colon_size_1cm", "edu.northwestern.fsm.type.ColonoscopyProcedure");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((ColonoscopyProcedure_Type)jcasType).casFeatCode_colon_size_1cm, v);}    
   
    
  //*--------------*
  //* Feature: colon_count_3

  /** getter for colon_count_3 - gets 
   * @generated
   * @return value of the feature 
   */
  public boolean getColon_count_3() {
    if (ColonoscopyProcedure_Type.featOkTst && ((ColonoscopyProcedure_Type)jcasType).casFeat_colon_count_3 == null)
      jcasType.jcas.throwFeatMissing("colon_count_3", "edu.northwestern.fsm.type.ColonoscopyProcedure");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((ColonoscopyProcedure_Type)jcasType).casFeatCode_colon_count_3);}
    
  /** setter for colon_count_3 - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setColon_count_3(boolean v) {
    if (ColonoscopyProcedure_Type.featOkTst && ((ColonoscopyProcedure_Type)jcasType).casFeat_colon_count_3 == null)
      jcasType.jcas.throwFeatMissing("colon_count_3", "edu.northwestern.fsm.type.ColonoscopyProcedure");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((ColonoscopyProcedure_Type)jcasType).casFeatCode_colon_count_3, v);}    
   
    
  //*--------------*
  //* Feature: prep_quality

  /** getter for prep_quality - gets 
   * @generated
   * @return value of the feature 
   */
  public String getPrep_quality() {
    if (ColonoscopyProcedure_Type.featOkTst && ((ColonoscopyProcedure_Type)jcasType).casFeat_prep_quality == null)
      jcasType.jcas.throwFeatMissing("prep_quality", "edu.northwestern.fsm.type.ColonoscopyProcedure");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ColonoscopyProcedure_Type)jcasType).casFeatCode_prep_quality);}
    
  /** setter for prep_quality - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setPrep_quality(String v) {
    if (ColonoscopyProcedure_Type.featOkTst && ((ColonoscopyProcedure_Type)jcasType).casFeat_prep_quality == null)
      jcasType.jcas.throwFeatMissing("prep_quality", "edu.northwestern.fsm.type.ColonoscopyProcedure");
    jcasType.ll_cas.ll_setStringValue(addr, ((ColonoscopyProcedure_Type)jcasType).casFeatCode_prep_quality, v);}    
  }

    