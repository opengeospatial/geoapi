/* ====================================================================
 * The ImageMatters LLC Software License, Version 1.1
 *
 * Copyright (c) 1999-2004 ImageMatters LLC.  All rights reserved.
 *
 * ====================================================================
 * 
 * $Log$
 * Revision 1.1  2005/03/03 12:02:46  desruisseaux
 * Added Stephane's interfaces proposal for GridCoverage
 *
 * Revision 1.1  2005/02/28 17:11:05  stephanef
 * *** empty log message ***
 *
 * Revision 1.2  2005/01/20 21:29:06  stephanef
 * Added getShort and getChar
 *
 * Revision 1.1  2005/01/05 21:59:40  stephanef
 * Initial revision
 * 
 */
package com.owsx.common.value;

import com.owsx.common.datatype.Datatype;

/**
 * Value interface
 *
 * @author Stephane Fellah
 * @version $Revision: 658 $
 * @since Jan 4, 2005
 */
public interface Value {
    /**
     * Get the lexical form of Literal
     * 
     * @return lexical form
     */
    public String getLexicalForm();

    /**
     * Indicates if the Value is a plain literal
     * (no datatype set)
     * 
     * @return true if plain literal
     */
    public boolean isPlainLiteral();

    /**
     * Get the datatype of the Literal
     * 
     * @return Datatype instance
     */
    public Datatype getDatatype();

    /**
     * Return the uri of the datatype of the Literal. It returns null for
     * "plain" literals.
     * 
     * @return uri of the datatype or null of plain literal
     */
    public String getDatatypeURI();

    /**
     * Return the Value as an Object
     * 
     * @return Value
     */
    public Object getValue();

    /**
     * Return the Value as an Object
     * 
     * @param dtype
     *            Datatype
     * @return Value
     */
    public Object getValue(Datatype dtype);

    /**
     * Return the Value as an Object with the given datatype URI
     * 
     * @param dtURI
     *            Datatype uri
     * @return Value
     */
    public Object getValue(String dtURI);

    /**
     * If the literal is interpretable as an Boolean return its value.
     * 
     * @return the literal interpreted as an boolean
     * @throws DataConversionException
     */
    public boolean getBoolean();

    /**
     * If the literal is interpretable as an Byte return its value.
     * 
     * @return the literal interpreted as an byte
     * @throws DataConversionException
     */
    public byte getByte();
    
    
    /**
     * If the literal is interpretable as an Character return its value.
     * 
     * @return the literal interpreted as an char
     * @throws DataConversionException
     */
    public char getChar();
    
    
    /**
     * If the literal is interpretable as an Short return its value.
     * 
     * @return the literal interpreted as an short
     * @throws DataConversionException
     */
    public short getShort();
    
    /**
     * If the literal is interpretable as an Integer return its value.
     * 
     * @return the literal interpreted as an int
     * @throws DataConversionException
     */
    public int getInt();

    /**
     * If the literal is interpretable as an Long return its value.
     * 
     * @return the literal interpreted as an long
     * @throws DataConversionException
     */
    public long getLong();

    /**
     * If the literal is interpretable as an Float return its value.
     * 
     * @return the literal interpreted as an float
     * @throws DataConversionException
     */
    public float getFloat();

    /**
     * If the literal is interpretable as an Double return its value.
     * 
     * @return the literal interpreted as an double
     * @throws DataConversionException
     */
    public double getDouble();

    /**
     * Structural comparision operator. Takes datatype and value into account.
     */
    public boolean equals(Object other);
}