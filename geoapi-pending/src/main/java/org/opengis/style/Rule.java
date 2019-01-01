/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2019 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */
package org.opengis.style;

import java.util.List;
import org.opengis.filter.Filter;
import org.opengis.annotation.UML;
import org.opengis.annotation.XmlElement;

import org.opengis.metadata.citation.OnlineResource;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;

/**
 * A rule consists of two important parts: a {@linkplain Filter filter} and a list of symbols.
 * When it is time to draw a given feature, the rendering
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
@UML(identifier="PF_PortrayalRule", specification=ISO_19117)
public interface Rule {
    /**
     * Returns a name for this rule.
     * This can be any string that uniquely identifies this rule within a given
     * canvas.  It is not meant to be human-friendly.  (The "title" property is
     * meant to be human friendly.)
     * @return a name for this rule.
     */
    @XmlElement("Name")
    @UML(identifier="ruleName", obligation=MANDATORY, specification=ISO_19117)
    String getName();

    /**
     * Returns the description of this rule.
     *
     * @return description with usual informations used
     * for user interfaces.
     */
    @XmlElement("Description")
    @UML(identifier="description", obligation=OPTIONAL, specification=ISO_19117)
    Description getDescription();

    /**
     * Returns a small Graphic that could be used by the rendering engine to
     * draw a legend window.
     *
     * <p>A nice user interface may want to present the user with a legend that
     * indicates how features of a given type are being portrayed.  Through its
     * {@code LegendGraphic} property, a {@code Rule} may provide a custom picture
     * to be used in such a legend window.</p>
     */
    @XmlElement("LegendGraphic")
    GraphicLegend getLegend();

    /**
     * Returns the filter that will limit the features for which this {@code Rule} will
     * fire.  This can only be non-null if {@link #isElseFilter} returns false.  If this
     * value is null and {@code isElseFilter} is false, this means that this {@code Rule}
     * should fire for all features.
     * @return Filter, use Filter.INCLUDES to indicate everything; or Filter.EXCLUDES for an "else" rule
     */
    @XmlElement("Filter")
    @UML(identifier="queryStatement", obligation=MANDATORY, specification=ISO_19117)
    Filter getFilter();

    /**
     * Returns true if this {@code Rule} is to fire only if no other rules in the containing
     * style have fired yet.  If this is true, then the {@linkplain #getFilter filter} must be Filter.EXCLUDES.
     * @return true if the filter is an else filter
     */
    @XmlElement("ElseFilter")
    boolean isElseFilter();

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
     * This method returns the list of Symbolizer objects
     * contained by this {@code Rule}.
     *
     * We use a list of {@code <? extends Symbolizer>} to enable the possibility
     * for an implementation to return a special type of Symbolizer.
     * This doesn't mean a Rule must return a list of PointSymbolizer or
     * TextSymbolizer only, no. The purpose of this if to offer the solution
     * to return different implementations like MutableSymbolizer or RichSymbolizer
     * and then avoid redundant cast in the code.
     * If you don't intend to use a special interface you can override this method
     * by symbolizers();
     *
     * @return the list of Symbolizer
     */
    @XmlElement("Symbolizer")
    @UML(identifier="portrayAction", obligation=MANDATORY, specification=ISO_19117)
    List<? extends Symbolizer> symbolizers();

    /**
     * It is common to have a style coming from a external xml file, this method
     * provide a way to get the original source if there is one.
     * OGC SLD specification can use this method to know if a style must be
     * written completely or if writing the online resource path is enough.
     *
     * @return OnlineResource or null
     */
    OnlineResource getOnlineResource();

    /**
     * calls the visit method of a StyleVisitor
     *
     * @param visitor the style visitor
     */
    Object accept(StyleVisitor visitor, Object extraData);
}
