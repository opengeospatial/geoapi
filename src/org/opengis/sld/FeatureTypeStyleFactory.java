package org.opengis.sld;

import java.io.InputStream;

import org.xml.sax.InputSource;

/**
 * Class that knows how to parse FeatureTypeStyle elements from various
 * places.  Follows the factory pattern of the Java DocumentBuilder classes.
 * Not finished: factory logic needs to be implemented.
 */
public abstract class FeatureTypeStyleFactory {
    public static FeatureTypeStyleFactory newInstance() {
        // Get some system property containing a class name, make a new instance
        // of that class, and return it.
        return null;
    }

    public abstract FeatureTypeStyle parse(InputStream is);
    public abstract FeatureTypeStyle parse(InputSource is);
    public abstract FeatureTypeStyle parse(String url);

    /**
     * Must pass in the Class of one of the interfaces in the package
     * <code>org.opengis.sld</code>.  Returns an instance of that interface
     * with no properties set.
     */
    public abstract Object createStyleObject(Class interfaceToMakeAnInstanceOf);
}
