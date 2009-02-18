/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.sld;

import java.io.InputStream;
import org.xml.sax.InputSource;
import org.opengis.annotation.Extension;


/**
 * Class that knows how to parse {@link FeatureStyle} elements from various places.
 *
 * @version <A HREF="http://portal.opengeospatial.org/files/?artifact_id=1188">Implementation specification 1.0</A>
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.0
 * 
 * @deprecated use interfaces from style package
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
