package org.opengis.sld;

import java.util.List;

import org.opengis.filter.Filter;

/**
 * A Rule consists of two important parts: a Filter and a list of Symbols.  When
 * it is time to draw a given feature, the rendering engine examines each Rule
 * in the FeatureStyle, first checking its Filter (or ElseFilter).  If the
 * Filter passes, then every Symbol for that rule is applied to the given
 * feature.
 */
public interface Rule {
    /**
     * Returns a name for this rule.
     * This can be any string that uniquely identifies this rule within a given
     * canvas.  It is not meant to be human-friendly.  (The "title" property is
     * meant to be human friendly.)
     */
    public String getName();

    /**
     * Sets the name for this rule.
     * This can be any string that uniquely identifies this rule within a given
     * canvas.  It is not meant to be human-friendly.  (The "title" property is
     * meant to be human friendly.)
     */
    public void setName(String name);

    /**
     * Returns the human readable title of this rule.
     * This can be any string, but should be fairly short as it is intended to
     * be used in list boxes or drop down menus or other selection interfaces.
     */
    public String getTitle();

    /**
     * Sets the human readable title of this rule.
     * This can be any string, but should be fairly short as it is intended to
     * be used in list boxes or drop down menus or other selection interfaces.
     */
    public void setTitle(String name);

    /**
     * Returns a human readable, prose description of this rule.
     * This can be any string and can consist of any amount of text.
     */
    public String getAbstract();

    /**
     * Sets the human readable, prose description of this rule.
     * This can be any string and can consist of any amount of text.
     */
    public void setAbstract(String abs);

    /**
     * Returns a small Graphic that could be used by the rendering engine to
     * draw a legend window.
     * <p>
     * A nice user interface may want to present the user with a legend that
     * indicates how features of a given type are being portrayed.  Through its
     * LegendGraphic property, a Rule may provide a custom picture to be used
     * in such a legend window.
     */
    public Graphic getLegendGraphic();
    
    /**
     * Sets the small Graphic that may be used by the rendering engine to draw
     * a legend window.
     * <p>
     * A nice user interface may want to present the user with a legend that
     * indicates how features of a given type are being portrayed.  Through its
     * LegendGraphic property, a Rule may provide a custom picture to be used
     * in such a legend window.
     */
    public void setLegendGraphic(Graphic g);

    /**
     * Returns the Filter that will limit the features for which this Rule will
     * fire.  This can only be non-null if <code>isElseFilter</code> returns
     * false.  If this value is null and <code>isElseFilter</code> is false,
     * this means that this Rule should fire for all features.
     */
    public Filter getFilter();

    /**
     * Sets the Filter that will limit the features for which this Rule will
     * fire.  This can only be non-null if <code>isElseFilter</code> returns
     * false.  If this value is null and <code>isElseFilter</code> is false,
     * this means that this Rule should fire for all features.
     */
    public void setFilter(Filter filter);

    /**
     * Returns true if this Rule is to fire only if no other Rules in the
     * containing style have fired yet.  If this is true, then the filter must
     * be null.
     */
    public boolean isElseFilter();

    /**
     * Sets the flag that indicates whether this Rule is an "else filter".  A
     * value of true indicates that this rule is to fire only if no other Rules
     * in the containing style have fired.
     */
    public void setElseFilter(boolean newValue);

    /**
     * Returns the minimum value in the denominator of the current map scale
     * at which this Rule will fire.
     * If, for example, the MinScaleDenominator were 10000, then this rule
     * would only fire at scales of 1:X where X is greater than 10000.
     * A value of zero indicates that there is no minimum.
     */
    public double getMinScaleDenominator();

    /**
     * Sets the minimum value in the denominator of the current map scale at
     * which this Rule will fire.
     * If, for example, the MinScaleDenominator were 10000, then this rule
     * would only fire at scales of 1:X where X is greater than 10000.
     * A value of zero indicates that there is no minimum.
     */
    public void setMinScaleDenominator(double d);

    /**
     * Returns the maximum value in the denominator of the current map scale
     * at which this Rule will fire.
     * If, for example, the MaxScaleDenominator were 98765, then this rule
     * would only fire at scales of 1:X where X is less than 98765.
     * A value of zero indicates that there is no minimum.
     */
    public double getMaxScaleDenominator();

    /**
     * Sets the maximum value in the denominator of the current map scale
     * at which this Rule will fire.
     * If, for example, the MaxScaleDenominator were 98765, then this rule
     * would only fire at scales of 1:X where X is less than 98765.
     * A value of zero indicates that there is no minimum.
     */
    public void setMaxScaleDenominator(double d);

    /**
     * This method returns a pointer to the "live" list of Symbol objects
     * contained by this Rule.  This list can be modified by the caller, so
     * there is no need for a setSymbols method.  Only objects of type Symbol
     * are allowed in this list.  Attempts to add other objects may throw
     * IllegalArgumentException or ClassCastException.
     */
    public List getSymbols();
}
