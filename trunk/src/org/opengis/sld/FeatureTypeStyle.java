package org.opengis.sld;

import java.util.List;

/**
 * Represents a style that applies to the features of a given type.
 */
public interface FeatureTypeStyle {
    public String getName();
    public void setName(String name);

    public String getTitle();
    public void setTitle(String title);

    public String getAbstract();
    public void setAbstract(String abs);

    public String getFeatureTypeName();
    public void setFeatureTypeName(String featureTypeName);

    public String getSemanticTypeIdentifier();
    public void setSemanticTypeIdentifier(String sti);

    public List getRules();
}
