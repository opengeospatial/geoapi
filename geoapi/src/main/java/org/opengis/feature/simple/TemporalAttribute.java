/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2004-2007 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.feature.simple;

import java.util.Date;

import org.opengis.feature.Attribute;

/**
 * Attribute bound to a Date class.
 * <p>
 * This class indicates getValue() returns a Date using Java 5
 * type narrowing, for for those working against java 1.4 interfaces
 * the additional methods getDate() and setDate have been
 * introduced.
 * @author Justin Deoliveira (The Open Planning Project)
 */
public interface TemporalAttribute extends Attribute {
    void setValue(Date newValue);
    Date getValue();
    /**
     * Java 1.4 type safe access to getValue
     * @return (Boolean) getValue()
     */
    Date getDate();

    /**
     * Java 1.4 type safe access to setValue
     * @param newValue
     */
    void setDate(Date newValue);
}
