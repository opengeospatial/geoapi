package org.opengis.sld;

import java.util.List;

/**
 * Represents a style that applies to the features of a given type.
 */
public interface FeatureStyle {
    /**
     * Returns a name for this style.
     * This can be any string that uniquely identifies this style within a given
     * canvas.  It is not meant to be human-friendly.  (The "title" property is
     * meant to be human friendly.)
     */
    public String getName();

    /**
     * Sets the name for this style.
     * This can be any string that uniquely identifies this style within a given
     * canvas.  It is not meant to be human-friendly.  (The "title" property is
     * meant to be human friendly.)
     */
    public void setName(String name);

    /**
     * Returns the human readable title of this style.
     * This can be any string, but should be fairly short as it is intended to
     * be used in list boxes or drop down menus or other selection interfaces.
     */
    public String getTitle();
    
    /**
     * Sets the human readable title of this style.
     * This can be any string, but should be fairly short as it is intended to
     * be used in list boxes or drop down menus or other selection interfaces.
     */
    public void setTitle(String title);

    /**
     * Returns a human readable, prose description of this style.
     * This can be any string and can consist of any amount of text.
     */
    public String getAbstract();
    
    /**
     * Sets the human readable, prose description of this style.
     * This can be any string and can consist of any amount of text.
     */
    public void setAbstract(String abs);

    /**
     * Returns the name of the feature type that this style is meant to act
     * upon.  This may return null if a style can operate on many different
     * feature types.
     */
    public String getFeatureTypeName();

    /**
     * Sets the name of the feature type that this style is meant to act upon.
     * This may be set to null if the style can operate on many different
     * feature types.
     */
    public void setFeatureTypeName(String featureTypeName);

    /**
     * This returns a string that identifies the more general "type" of geometry
     * that this style is meant to act upon.
     * In the current OGC specifications, this is an experimental element and
     * can take only one of the following values:
     * <ul>
     *   <li><code>generic:point</code></li>
     *   <li><code>generic:line</code></li>
     *   <li><code>generic:polygon</code></li>
     *   <li><code>generic:text</code></li>
     *   <li><code>generic:raster</code></li>
     *   <li><code>generic:any</code></li>
     * </ul>
     */
    public String[] getSemanticTypeIdentifiers();
    
    /**
     * This sets the string array that identifies the more general "type" of geometry
     * that this style is meant to act upon.
     * In the current OGC specifications, this is an experimental element and
     * can take only one of the following values:
     * <ul>
     *   <li><code>generic:point</code></li>
     *   <li><code>generic:line</code></li>
     *   <li><code>generic:polygon</code></li>
     *   <li><code>generic:text</code></li>
     *   <li><code>generic:raster</code></li>
     *   <li><code>generic:any</code></li>
     * </ul>
     */
    public void setSemanticTypeIdentifiers(String sti[]);

    /**
     * Returns the list of Rules contained by this style.  The returned List is
     * the "live" list and can be modified, both by adding and removing Rules.
     * (This is why there is no <code>setRules</code> method.)  All elements of
     * the List must be instance of the class <code>Rule</code> and attempts to
     * add other objects may throw an exception.
     */
    public List getRules();
}
