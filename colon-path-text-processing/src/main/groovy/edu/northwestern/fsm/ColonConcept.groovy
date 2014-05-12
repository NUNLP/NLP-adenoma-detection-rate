package edu.northwestern.fsm

import groovy.transform.Immutable

@Immutable
class ColonConcept {

    String cui
    String tui
    String codingScheme
    String code
    
    Map getMap() {
        return [cui:cui, tui:tui, codingScheme:codingScheme, code:code]
    }
        
    static final String CODING_SCHEME_SNOMED = 'SNOMED'
    static final String CODING_SCHEME_UCUM = 'UCUM'
    
    static final String TUI_NEOPLASTIC_PROCESS = 'T191'
    static final String TUI_DISEASE_OR_SYNDROME = 'T047'
    static final String TUI_BODY_PART = 'T023'
    static final String TUI_QUANTITATIVE_CONCEPT = 'T081'
    static final String TUI_PATHOLOGIC_FUNCTION = 'T046'
    static final String TUI_DIAGNOSTIC_PROCEDURE = 'T060'
    static final String TUI_ADVANCED = 'ADVANCED'
    
    static final String ROLE_FINDING = 'Finding'
    static final String ROLE_LOCATION = 'LocationOf'
    static final String ROLE_NUMBER = 'Number'
    static final String ROLE_HIGH_GRADE_DYSPLASIA = 'HighGradeDysplasia'
    static final String ROLE_VILLOUS = 'Villous'
    
    static final String INDICATION_SCREENING = 'SCREENING'
    static final String INDICATION_OTHER = 'OTHER'
    
    static final String SEGMENT_INDICATIONS = 'INDICATIONS'
    
    //---------------------------------------------------------------------------------------------------------------------
    // Procedure Report Concepts
    //---------------------------------------------------------------------------------------------------------------------
    static final ColonConcept COLONOSCOPY = new ColonConcept(codingScheme:CODING_SCHEME_SNOMED, code:'73761001', cui:'C0009378', tui:TUI_DIAGNOSTIC_PROCEDURE)
    
    //---------------------------------------------------------------------------------------------------------------------
    // Path Report Concepts
    //---------------------------------------------------------------------------------------------------------------------

    static final ColonConcept INFLAMMATORY_POLYP = new ColonConcept(codingScheme:CODING_SCHEME_SNOMED, code:'76235005', cui:'C0032568', tui:TUI_DISEASE_OR_SYNDROME)
    static final ColonConcept HYPERPLASTIC_POLYP = new ColonConcept(codingScheme:CODING_SCHEME_SNOMED, code:'89452002', cui:'C0267364', tui:TUI_NEOPLASTIC_PROCESS)
    static final ColonConcept ADENOMA = new ColonConcept(codingScheme:CODING_SCHEME_SNOMED, code:'428054006', cui:'C0850572', tui:TUI_NEOPLASTIC_PROCESS)
    static final ColonConcept TUBULAR_ADENOMA = new ColonConcept(codingScheme:CODING_SCHEME_SNOMED, code:'444408007', cui:'C0334292', tui:TUI_NEOPLASTIC_PROCESS)
    static final ColonConcept TUBULO_VILLOUS_ADENOMA = new ColonConcept(codingScheme:CODING_SCHEME_SNOMED, code:'61722000', cui:'C0334307', tui:TUI_NEOPLASTIC_PROCESS)
    static final ColonConcept VILLOUS_ADENOMA = new ColonConcept(codingScheme:CODING_SCHEME_SNOMED, code:'309084001', cui:'C0149862', tui:TUI_NEOPLASTIC_PROCESS)
    static final ColonConcept SERRATED_ADENOMA = new ColonConcept(codingScheme:CODING_SCHEME_SNOMED, code:'449855005', cui:'C3266124', tui:TUI_NEOPLASTIC_PROCESS)
    static final ColonConcept ADENOCARCINOMA = new ColonConcept(codingScheme:CODING_SCHEME_SNOMED, code:'443961001', cui:'C0001418', tui:TUI_NEOPLASTIC_PROCESS)
    static final ColonConcept DYSPLASIA = new ColonConcept(codingScheme:CODING_SCHEME_SNOMED, code:'25723000', cui:'C0334044', tui:TUI_PATHOLOGIC_FUNCTION)
    
    static final ColonConcept COLON = new ColonConcept(codingScheme:CODING_SCHEME_SNOMED, code:'71854001', cui:'C0009368', tui:TUI_BODY_PART)
    static final ColonConcept LEFT_COLON = new ColonConcept(codingScheme:CODING_SCHEME_SNOMED, code:'55572008', cui:'C0227388', tui:TUI_BODY_PART)
    static final ColonConcept RECTUM = new ColonConcept(codingScheme:CODING_SCHEME_SNOMED, code:'55572008', cui:'C0227388', tui:TUI_BODY_PART)
    static final ColonConcept SIGMOID_COLON = new ColonConcept(codingScheme:CODING_SCHEME_SNOMED, code:'55572008', cui:'C0227388', tui:TUI_BODY_PART)
    static final ColonConcept DESCENDING_COLON = new ColonConcept(codingScheme:CODING_SCHEME_SNOMED, code:'55572008', cui:'C0227388', tui:TUI_BODY_PART)
    static final ColonConcept SPLENIC_FLEXURE = new ColonConcept(codingScheme:CODING_SCHEME_SNOMED, code:'55572008', cui:'C0227388', tui:TUI_BODY_PART)
    static final ColonConcept RIGHT_COLON = new ColonConcept(codingScheme:CODING_SCHEME_SNOMED, code:'51342009', cui:'C1305188', tui:TUI_BODY_PART)
    static final ColonConcept TRANSVERSE_COLON = new ColonConcept(codingScheme:CODING_SCHEME_SNOMED, code:'51342009', cui:'C1305188', tui:TUI_BODY_PART)
    static final ColonConcept HEPATIC_FLEXURE = new ColonConcept(codingScheme:CODING_SCHEME_SNOMED, code:'51342009', cui:'C1305188', tui:TUI_BODY_PART)
    static final ColonConcept ASCENDING_COLON = new ColonConcept(codingScheme:CODING_SCHEME_SNOMED, code:'51342009', cui:'C1305188', tui:TUI_BODY_PART)
    static final ColonConcept ILEUM = new ColonConcept(codingScheme:CODING_SCHEME_SNOMED, code:'51342009', cui:'C1305188', tui:TUI_BODY_PART)
    static final ColonConcept CECUM = new ColonConcept(codingScheme:CODING_SCHEME_SNOMED, code:'51342009', cui:'C1305188', tui:TUI_BODY_PART)
}