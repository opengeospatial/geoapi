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

import java.util.List;
import org.opengis.filter.Filter;
import org.opengis.util.InternationalString;
import org.opengis.annotation.XmlElement;


/**
 * A rule consists of two important parts: a {@linkplain Filter filter} and a list of
 * {@linkplain Symbol symbols}.  When it is time to draw a given feature, the rendering
 * engine examines each rule in the FeatureStyle, first checking its Filter (or ElseFilter).  If the
 * Filter passes, then every Symbol for that rule is applied to the given
 * feature.
 *
 * @version <A HREF="http://portal.opengeospatial.org/files/?artifact_id=1188">Implementation specification 1.0</A>
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.0
 */
@XmlElement("Rule")
public interface Rule {
    /**
     * Returns a name for this rule.
     * This can be any string that uniquely identifies this rule within a given
     * canvas.  It is not meant to be human-friendly.  (The "title" property is
     * meant to be human friendly.)
     */
    @XmlElement("Name")
    String getName();

    /**
     * Sets the name for this rule.
     * See {@link #getName} for details.
     */
    @XmlElement("Name")
    void setName(String name);

    /**
     * Returns the human readable title of this rule.
     * This can be any string, but should be fairly short as it is intended to
     * be used in list boxes or drop down menus or other selection interfaces.
     */
    @XmlElement("Title")
    InternationalString getTitle();

    /**
     * Sets the human readable title of this rule.
     * See {@link #getTitle} for details.
     */
    @XmlElement("Title")
    void setTitle(InternationalString name);

    /**
     * Returns a human readable, prose description of this rule.
     * This can be any string and can consist of any amount of text.
     */
    @XmlElement("Abstract")
    InternationalString getAbstract();

    /**
     * Sets the human readable, prose description of this rule.
     * See {@link #getAbstract} for details.
     */
    @XmlElement("Abstract")
    void setAbstract(InternationalString abs);

    /**
     * Returns a small Graphic that could be used by the rendering engine to
     * draw a legend window.
     * <p>
     * A nice user interface may want to present the user with a legend that
     * indicates how features of a given type are being portrayed.  Through its
     * {@code LegendGraphic} property, a {@code Rule} may provide a custom picture
     * to be used in such a legend window.
     */
    @XmlElement("LegendGraphic")
    Graphic getLegendGraphic();

    /**
     * Sets the small Graphic that may be used by the rendering engine to draw
     * a legend window.
     * See {@link #getLegendGraphic} for details.
     */
    @XmlElement("LegendGraphic")
    void setLegendGraphic(Graphic g);

    /**
     * Returns the filter that will limit the features for which this {@code Rule} will
     * fire.  This can only be non-null if {@link #isElseFilter} returns false.  If this
     * value is null and {@code isElseFilter} is false, this means that this {@code Rule}
     * should fire for all features.
     */
    @XmlElement("Filter")  // TODO: actually a <xs:choice> between Filter and ElseFilter
    Filter getFilter();

    /**
     * Sets the filter that will limit the features for which this {@code Rule} will fire.
     * See {@link #getFilter} for details.
     */
    @XmlElement("Filter")  // TODO: actually a <xs:choice> between Filter and ElseFilter
    void setFilter(Filter filter);

    /**
     * Returns true if this {@code Rule} is to fire only if no other rules in the containing
     * style have fired yet.  If this is true, then the {@linkplain #getFilter filter} must be null.
     */
    @XmlElement("ElseFilter")
    boolean isElseFilter();

    /**
     * Sets the flag that indicates whether this {@code Rule} is an "else filter".
     * A value of true indicates that this rule is to fire only if no other rules
     * in the containing style have fired.
     * See {@link #isElseFilter} for details.
     */
    @XmlElement("ElseFilter")
    void setElseFilter(boolean newValue);

    /**
     * Returns the minimum value (inclusive) in the denominator of the current map scale
     * at which this {@code Rule} will fire.
     * If, for example, the {@code MinScaleDenominator} were 10000, then this rule
     * would only fire at scales of 1:X where X is greater than 10000.
     * A value of zero indicates that there is no minimum.
     */
    @XmlElement("MinScaleDenominator")
    double getMinScaleDenominator();

    /**
     * Sets the minimum value (inclusive) in the denominator of the current map scale at
     * which this {@code Rule} will fire.
     * See {@link #getMinScaleDenominator} for details.
     */
    @XmlElement("MinScaleDenominator")
    void setMinScaleDenominator(double d);

    /**
     * Returns the maximum value (exclusive) in the denominator of the current map scale
     * at which this {@code Rule} will fire.
     * If, for example, the {@code MaxScaleDenominator} were 98765, then this rule
     * would only fire at scales of 1:X where X is less than 98765.
     * A value of {@link Double#POSITIVE_INFINITY} indicates that there is no maximum.
     */
    @XmlElement("MaxScaleDenominator")
    double getMaxScaleDenominator();

    /**
     * Sets the maximum value (exclusive) in the denominator of the current map scale
     * at which this {@code Rule} will fire.
     * See {@link #getMaxScaleDenominator} for details.
     */
    @XmlElement("MaxScaleDenominator")
    void setMaxScaleDenominator(double d);

    /**
     * This method returns a pointer to the "live" list of Symbol objects
     * contained by this {@code Rule}.  This list can be modified by the caller, so
     * there is no need for a {@code setSymbols} method.
     */
    // TODO: @XmlElement actually a <xs:choice> between various Symbol subclasses.
    List<Symbol> getSymbols();
}
