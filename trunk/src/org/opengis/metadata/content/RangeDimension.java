/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.content;

// J2SE direct dependencies
import java.util.Locale;


/**
 * Information on the range of each dimension of a cell measurement value.
 *
 * @UML datatype MD_RangeDimension
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
public interface RangeDimension {
    /**
     * Number that uniquely identifies instances of bands of wavelengths on which a sensor
     * operates.
     *
     * @UML optional sequenceIdentifier
     *
     * @revisit Uncomment this method when <code>MemberName</code> will be defined.
     */
//    MemberName getSequenceIdentifier();

    /**
     * Description of the range of a cell measurement value.
     *
     * @param  locale The desired locale for the description to be returned, or <code>null</code>
     *         for a description in some default locale (may or may not be the
     *         {@linkplain Locale#getDefault() system default}).
     * @return The description in the given locale.
     *         If no description is available in the given locale, then some default locale is used.
     * @UML optional descriptor
     */
    String getDescriptor(Locale locale);
}
