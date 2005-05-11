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


/**
 * Class that knows how to parse FeatureStyle elements from various places.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/02-070.pdf">Implementation specification 1.0</A>
 * @since 1.1
 */
public interface FeatureStyleFactory {
    /**
     *
     */
    public FeatureStyle parse(InputStream is);
    
    /**
     *
     */
    public FeatureStyle parse(InputSource is);
    
    /**
     *
     */
    public FeatureStyle parse(String url);

    /**
     * Must pass in the Class of one of the interfaces in the package
     * <code>org.opengis.sld</code>.  Returns an instance of that interface
     * with no properties set.
     */
    public Object createStyleObject(Class interfaceToMakeAnInstanceOf);
}
