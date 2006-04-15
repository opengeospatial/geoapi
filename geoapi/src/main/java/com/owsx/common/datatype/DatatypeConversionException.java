/* ====================================================================
 * The ImageMatters LLC Software License, Version 1.1
 *
 * Copyright (c) 1999-2004 ImageMatters LLC.  All rights reserved.
 * Donation to GeoAPI.
 * ====================================================================
 * 
 * $Log$
 * Revision 1.1  2005/03/03 11:54:33  desruisseaux
 * Added Stephane's interfaces proposal for GridCoverage
 *
 * Revision 1.2  2005/01/05 21:05:27  topher
 * rearraging
 *
 * Revision 1.1  2005/01/04 15:08:49  stephanef
 * Change osf to owsx project name
 *
 * Revision 1.2  2004/12/30 22:41:21  stephanef
 * Refactoring of datatype library
 *
 * Revision 1.1  2004/12/29 15:22:47  stephanef
 * Refactoring of class name
 *
 * Revision 1.1  2004/12/28 22:51:51  stephanef
 * Initial revision
 * 
 */
package com.owsx.common.datatype;

/**
 * Exception thrown when conversion between lexical form and value form of a
 * datatype fails.
 * 
 * @author Stephane Fellah
 * @version $Revision$
 * @since Dec 28, 2004
 */
public class DatatypeConversionException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Preferred constructor.
     * 
     * @param lexicalForm
     *            the illegal string discovered
     * @param dtype
     *            the datatype that found the problem
     * @param msg
     *            additional context for the error
     */
    public DatatypeConversionException(String lexicalForm, Datatype dtype, String msg) {
        super("Lexical form '" + lexicalForm + "' is not a legal instance of " + dtype + " " + msg);
    }

    public DatatypeConversionException(String lexicalForm, Datatype dtype, Throwable e) {
        super("Lexical form '" + lexicalForm + "' is not a legal instance of " + dtype + " " + e.getMessage());
    }

    /**
     * Preferred constructor.
     * 
     * @param valueForm
     *            the illegal value form discovered
     * @param dtype
     *            the datatype that found the problem
     * @param msg
     *            additional context for the error
     */
    public DatatypeConversionException(Object valueForm, Datatype dtype, String msg) {
        super("Value form '" + valueForm + "' is not a legal instance of " + dtype + " " + msg);
    }

    public DatatypeConversionException(Object valueForm, Datatype dtype, Throwable e) {
        super("Value form '" + valueForm + "' is not a legal instance of " + dtype + " " + e.getMessage());
    }

    /**
     * Creates a new instance of <code>DatatypeConversionException</code>
     * without detail message.
     */
    public DatatypeConversionException() {
    }

    /**
     * Constructs an instance of <code>DatatypeFormatException</code> with the
     * specified detail message.
     * 
     * @param msg
     *            the detail message.
     */
    public DatatypeConversionException(String msg) {
        super(msg);
    }

}
