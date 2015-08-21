
/* First created by JCasGen Wed Mar 05 11:44:59 CST 2014 */
package edu.northwestern.fsm.type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.ctakes.typesystem.type.refsem.Procedure_Type;

/** 
 * Updated by JCasGen Fri Mar 07 16:43:01 CST 2014
 * @generated */
public class ColonoscopyProcedure_Type extends Procedure_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (ColonoscopyProcedure_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = ColonoscopyProcedure_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new ColonoscopyProcedure(addr, ColonoscopyProcedure_Type.this);
  			   ColonoscopyProcedure_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new ColonoscopyProcedure(addr, ColonoscopyProcedure_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = ColonoscopyProcedure.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("edu.northwestern.fsm.type.ColonoscopyProcedure");
 
  /** @generated */
  final Feature casFeat_indications;
  /** @generated */
  final int     casFeatCode_indications;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getIndications(int addr) {
        if (featOkTst && casFeat_indications == null)
      jcas.throwFeatMissing("indications", "edu.northwestern.fsm.type.ColonoscopyProcedure");
    return ll_cas.ll_getRefValue(addr, casFeatCode_indications);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setIndications(int addr, int v) {
        if (featOkTst && casFeat_indications == null)
      jcas.throwFeatMissing("indications", "edu.northwestern.fsm.type.ColonoscopyProcedure");
    ll_cas.ll_setRefValue(addr, casFeatCode_indications, v);}
    
   /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @return value at index i in the array 
   */
  public String getIndications(int addr, int i) {
        if (featOkTst && casFeat_indications == null)
      jcas.throwFeatMissing("indications", "edu.northwestern.fsm.type.ColonoscopyProcedure");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_indications), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_indications), i);
  return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_indications), i);
  }
   
  /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @param v value to set
   */ 
  public void setIndications(int addr, int i, String v) {
        if (featOkTst && casFeat_indications == null)
      jcas.throwFeatMissing("indications", "edu.northwestern.fsm.type.ColonoscopyProcedure");
    if (lowLevelTypeChecks)
      ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_indications), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_indications), i);
    ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_indications), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_provider;
  /** @generated */
  final int     casFeatCode_provider;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getProvider(int addr) {
        if (featOkTst && casFeat_provider == null)
      jcas.throwFeatMissing("provider", "edu.northwestern.fsm.type.ColonoscopyProcedure");
    return ll_cas.ll_getStringValue(addr, casFeatCode_provider);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setProvider(int addr, String v) {
        if (featOkTst && casFeat_provider == null)
      jcas.throwFeatMissing("provider", "edu.northwestern.fsm.type.ColonoscopyProcedure");
    ll_cas.ll_setStringValue(addr, casFeatCode_provider, v);}
    
  
 
  /** @generated */
  final Feature casFeat_colon_size_1cm;
  /** @generated */
  final int     casFeatCode_colon_size_1cm;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public boolean getColon_size_1cm(int addr) {
        if (featOkTst && casFeat_colon_size_1cm == null)
      jcas.throwFeatMissing("colon_size_1cm", "edu.northwestern.fsm.type.ColonoscopyProcedure");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_colon_size_1cm);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setColon_size_1cm(int addr, boolean v) {
        if (featOkTst && casFeat_colon_size_1cm == null)
      jcas.throwFeatMissing("colon_size_1cm", "edu.northwestern.fsm.type.ColonoscopyProcedure");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_colon_size_1cm, v);}
    
  
 
  /** @generated */
  final Feature casFeat_colon_count_3;
  /** @generated */
  final int     casFeatCode_colon_count_3;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public boolean getColon_count_3(int addr) {
        if (featOkTst && casFeat_colon_count_3 == null)
      jcas.throwFeatMissing("colon_count_3", "edu.northwestern.fsm.type.ColonoscopyProcedure");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_colon_count_3);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setColon_count_3(int addr, boolean v) {
        if (featOkTst && casFeat_colon_count_3 == null)
      jcas.throwFeatMissing("colon_count_3", "edu.northwestern.fsm.type.ColonoscopyProcedure");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_colon_count_3, v);}
    
  
 
  /** @generated */
  final Feature casFeat_prep_quality;
  /** @generated */
  final int     casFeatCode_prep_quality;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getPrep_quality(int addr) {
        if (featOkTst && casFeat_prep_quality == null)
      jcas.throwFeatMissing("prep_quality", "edu.northwestern.fsm.type.ColonoscopyProcedure");
    return ll_cas.ll_getStringValue(addr, casFeatCode_prep_quality);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setPrep_quality(int addr, String v) {
        if (featOkTst && casFeat_prep_quality == null)
      jcas.throwFeatMissing("prep_quality", "edu.northwestern.fsm.type.ColonoscopyProcedure");
    ll_cas.ll_setStringValue(addr, casFeatCode_prep_quality, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public ColonoscopyProcedure_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_indications = jcas.getRequiredFeatureDE(casType, "indications", "uima.cas.StringArray", featOkTst);
    casFeatCode_indications  = (null == casFeat_indications) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_indications).getCode();

 
    casFeat_provider = jcas.getRequiredFeatureDE(casType, "provider", "uima.cas.String", featOkTst);
    casFeatCode_provider  = (null == casFeat_provider) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_provider).getCode();

 
    casFeat_colon_size_1cm = jcas.getRequiredFeatureDE(casType, "colon_size_1cm", "uima.cas.Boolean", featOkTst);
    casFeatCode_colon_size_1cm  = (null == casFeat_colon_size_1cm) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_colon_size_1cm).getCode();

 
    casFeat_colon_count_3 = jcas.getRequiredFeatureDE(casType, "colon_count_3", "uima.cas.Boolean", featOkTst);
    casFeatCode_colon_count_3  = (null == casFeat_colon_count_3) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_colon_count_3).getCode();

 
    casFeat_prep_quality = jcas.getRequiredFeatureDE(casType, "prep_quality", "uima.cas.String", featOkTst);
    casFeatCode_prep_quality  = (null == casFeat_prep_quality) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_prep_quality).getCode();

  }
}



    