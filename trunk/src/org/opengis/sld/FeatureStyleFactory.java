package org.opengis.sld;

import java.io.InputStream;

import org.xml.sax.InputSource;

/**
 * Class that knows how to parse FeatureStyle elements from various
 * places.
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
