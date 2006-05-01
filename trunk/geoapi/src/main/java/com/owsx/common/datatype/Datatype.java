/* ====================================================================
 * The ImageMatters LLC Software License, Version 1.1
 *
 * Copyright (c) 1999-2004 ImageMatters LLC.  All rights reserved.
 * Donation to GeoAPI.
 * ====================================================================
 * 
 * $Log$
 * Revision 1.1  2005/03/03 11:54:32  desruisseaux
 * Added Stephane's interfaces proposal for GridCoverage
 *
 * Revision 1.1  2005/01/04 15:08:49  stephanef
 * Change osf to owsx project name
 *
 * Revision 1.3  2004/12/30 22:41:21  stephanef
 * Refactoring of datatype library
 *
 * Revision 1.2  2004/12/29 15:22:47  stephanef
 * Refactoring of class name
 *
 * Revision 1.1  2004/12/28 22:51:51  stephanef
 * Initial revision
 * 
 */
package com.owsx.common.datatype;

/**
 * Datatype interface. An instance of this interface is needed 
 * to convert typed literals between lexical and value forms. 
 *
 * @author Stephane Fellah
 * @version $Revision: 658 $
 * @since Dec 28, 2004
 */
public interface Datatype {
    
    /**
     * Return the URI which is the label for this datatype. Use for 
     * example XSD uri. 
     * 
     * @return uri of the datatype
     */
    public String getURI();
    
    /**
     * If this datatype is used as the canonical representation for a 
     * particular java datatype then return that java type, otherwise returns null 
     * (ex. xsd:unsignedInt returns null, but xsd:boolean return Boolean.class)
     *  
     * @return java class used for the datatype
     */
    public Class getJavaClass();
   
    /**
     * Parse a lexical form of this datatype to a value
     * 
     * @param lexicalForm
     * @return value form
     * @throws DatatypeConversionException when conversion fails
     */
    public Object valueOf(String lexicalForm) throws DatatypeConversionException;
   
    /**
     * Convert a value of this datatype out to lexical form. 
     * 
     * @param valueForm value to convert
     * @return lexical form of the value 
     * @throws IllegalArgumentException when conversion to lexical form fails
     */
    public String toLexicalForm(Object valueForm) throws IllegalArgumentException;
    
    /**
     * Test whether the given object is a legal value form of this datatype.
     * 
     * @param valueForm
     * @return true if valid form
     */
    public boolean isValidValue(Object valueForm);
    
    /**
     * Test whether the given string is a legal lexical form of this datatype.
     * 
     * @param lexicalForm
     * @return true if valid lexical form
     */
    public boolean isValid(String lexicalForm);
    
}
