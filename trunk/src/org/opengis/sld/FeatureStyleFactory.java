/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.sld;

// J2SE direct dependencies
import java.io.InputStream;
import org.xml.sax.InputSource;

// Annotations
import org.opengis.annotation.Extension;


/**
 * Class that knows how to parse {@link FeatureStyle} elements from various places.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/02-070.pdf">Implementation specification 1.0</A>
 * @since GeoAPI 1.1
 */
@Extension
public interface FeatureStyleFactory {
    /**
     * Parses a feature style from a J2SE input stream.
     */
    FeatureStyle parse(InputStream is);
    
    /**
     * Parses a feature style from a XML input source.
     */
    FeatureStyle parse(InputSource is);
    
    /**
     * Parses a feature style from a character string.
     */
    FeatureStyle parse(String url);

    /**
     * Must pass in the Class of one of the interfaces in the package
     * {@code org.opengis.sld}.  Returns an instance of that interface
     * with no properties set.
     */
    Object createStyleObject(Class interfaceToMakeAnInstanceOf);
}
