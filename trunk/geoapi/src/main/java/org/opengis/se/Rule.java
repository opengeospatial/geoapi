/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2008 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.se;

import java.util.List;
import org.opengis.filter.Filter;
import org.opengis.annotation.XmlElement;

/**
 * A rule consists of two important parts: a {@linkplain Filter filter} and a list of
 * {@linkplain Symbol symbols}.  When it is time to draw a given feature, the rendering
 * engine examines each rule in the FeatureStyle, first checking its Filter (or ElseFilter).  If the
 * Filter passes, then every Symbolizer for that rule is applied to the given
 * feature.
 * 
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding Implementation Specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.2
 */
@XmlElement("Rule")
public interface Rule {

    /**
     * Returns a name for this rule.
     * This can be any string that uniquely identifies this rule within a given
     * canvas.  It is not meant to be human-friendly.  (The "title" property is
     * meant to be human friendly.)
     * @return a name for this rule.
     */
    @XmlElement("Name")
    String getName();

    /**
     * Sets the name for this rule.
     * See {@link #getName} for details.
     * @param name , new name for the style
     */
    @XmlElement("Name")
    void setName( String name);

    /**
     * Returns the description of this rule.
     * 
     * @return Description with usual informations used
     * for user interfaces.
     */
    @XmlElement("Description")
    Description getDescription();

    /**
     * Sets the description of this rule.
     * See {@link #getDescription} for details.
     * @param description : the new description
     */
    @XmlElement("Description")
    void setDescription( Description description);
    
    /**
     * Returns a small Graphic that could be used by the rendering engine to
     * draw a legend window.
     * <p>
     * A nice user interface may want to present the user with a legend that
     * indicates how features of a given type are being portrayed.  Through its
     * {@code LegendGraphic} property, a {@code Rule} may provide a custom picture
     * to be used in such a legend window.
     * @return 
     */
    @XmlElement("LegendGraphic")
    LegendGraphic getLegendGraphic();

    /**
     * Sets the small Graphic that may be used by the rendering engine to draw
     * a legend window.
     * See {@link #getLegendGraphic} for details.
     * @param graphic 
     */
    @XmlElement("LegendGraphic")
    void setLegendGraphic(LegendGraphic graphic);

    /**
     * Returns the filter that will limit the features for which this {@code Rule} will
     * fire.  This can only be non-null if {@link #isElseFilter} returns false.  If this
     * value is null and {@code isElseFilter} is false, this means that this {@code Rule}
     * should fire for all features.
     * @return Filter, if null the rule is always true
     */
    @XmlElement("Filter")  
    Filter getFilter();

    /**
     * Sets the filter that will limit the features for which this {@code Rule} will fire.
     * See {@link #getFilter} for details.
     * @param filter 
     */
    @XmlElement("Filter")  
    void setFilter(Filter filter);

    /**
     * Returns true if this {@code Rule} is to fire only if no other rules in the containing
     * style have fired yet.  If this is true, then the {@linkplain #getFilter filter} must be null.
     * @return true if the filter is an else filter
     */
    @XmlElement("ElseFilter")
    boolean isElseFilter();

    /**
     * Sets the flag that indicates whether this {@code Rule} is an "else filter".
     * A value of true indicates that this rule is to fire only if no other rules
     * in the containing style have fired.
     * See {@link #isElseFilter} for details.
     * @param newValue 
     */
    @XmlElement("ElseFilter")
    void setElseFilter(boolean newValue);

    /**
     * Returns the minimum value (inclusive) in the denominator of the current map scale
     * at which this {@code Rule} will fire.
     * If, for example, the {@code MinScaleDenominator} were 10000, then this rule
     * would only fire at scales of 1:X where X is greater than 10000.
     * A value of zero indicates that there is no minimum.
     * @return Min scale double value
     */
    @XmlElement("MinScaleDenominator")
    double getMinScaleDenominator();

    /**
     * Sets the minimum value (inclusive) in the denominator of the current map scale at
     * which this {@code Rule} will fire.
     * See {@link #getMinScaleDenominator} for details.
     * @param scale : Min scale double value
     */
    @XmlElement("MinScaleDenominator")
    void setMinScaleDenominator(double scale);

    /**
     * Returns the maximum value (exclusive) in the denominator of the current map scale
     * at which this {@code Rule} will fire.
     * If, for example, the {@code MaxScaleDenominator} were 98765, then this rule
     * would only fire at scales of 1:X where X is less than 98765.
     * A value of {@link Double#POSITIVE_INFINITY} indicates that there is no maximum.
     * @return Max scale double value
     */
    @XmlElement("MaxScaleDenominator")
    double getMaxScaleDenominator();

    /**
     * Sets the maximum value (exclusive) in the denominator of the current map scale
     * at which this {@code Rule} will fire.
     * See {@link #getMaxScaleDenominator} for details.
     * @param scale : Max scale double value 
     */
    @XmlElement("MaxScaleDenominator")
    void setMaxScaleDenominator(double scale);

    /**
     * This method returns a pointer to the "live" list of Symbolizer objects
     * contained by this {@code Rule}.  This list can be modified by the caller, so
     * there is no need for a {@code setSymbols} method.
     * @return the "live" list of Symbolizer
     */
    @XmlElement("Symbolizer")
    List<Symbolizer> getSymbolizers();
}
