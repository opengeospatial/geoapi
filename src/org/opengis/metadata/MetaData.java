/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata;

// J2SE direct dependencies
import java.util.Locale;


/**
 * Root entity which defines metadata about a resource or resources.
 *
 * @UML datatype MD_MetaData
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 *
 * @revisit Interface not completed.
 */
public interface MetaData {
    /**
     * Unique identifier for this metadata file, or <code>null</code> if none.
     *
     * @UML optional fileIdentifier
     */
    String getFileIdentifier();

    /**
     * Language used for documenting metadata.
     *
     * @UML conditional language
     */
    Locale getLanguage();

    /**
     * Full name of the character coding standard used for the metadata set.
     *
     * @UML optional characterSet
     *
     * @revisit We should use {@link java.nio.charset.Charset} if J2SE 1.4 is allowed.
     */
    String getCharacterSet();
}
