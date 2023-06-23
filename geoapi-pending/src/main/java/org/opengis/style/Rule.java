/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2023 Open Geospatial Consortium, Inc.
 *    http://www.geoapi.org
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.opengis.style;

import java.util.List;
import org.opengis.filter.Filter;
import org.opengis.annotation.XmlElement;

import org.opengis.metadata.citation.OnlineResource;


/**
 * Rendering instructions grouped by feature-property conditions and map scales.
 * A rule consists of two important parts: a {@linkplain Filter filter} and a list of symbols.
 * When drawing a given feature, the rendering engine examines each rule in the style,
 * first checking its filter. If the feature is accepted by the filter,
 * then all {@link Symbolizer} for that rule are applied to the given feature.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding Implementation Specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @author Chris Dillard (SYS Technologies)
 */
@XmlElement("Rule")
public interface Rule {
    /**
     * Returns a name for this rule.
     * This can be any string that uniquely identifies this rule within a given canvas.
     * It is not meant to be human-friendly.  (The "title" property is meant to be human friendly.)
     *
     * @return a name for this rule.
     */
    @XmlElement("Name")
    String getName();

    /**
     * Returns the description of this rule.
     *
     * @return description with usual information used for user interfaces.
     */
    @XmlElement("Description")
    Description getDescription();

    /**
     * Returns a small graphic that could be used by the rendering engine to draw a legend window.
     * User interfaces may present the user with a legend that indicates how features of a given type are being portrayed.
     * Through its {@code LegendGraphic} property, a {@code Rule} can provide a custom picture to be used in such a legend window.
     *
     * @return small graphic to draw in a legend window.
     */
    @XmlElement("LegendGraphic")
    GraphicLegend getLegend();

    /**
     * Returns the filter that will limit the features for which this {@code Rule} will fire.
     * This can only be non-null if {@link #isElseFilter} returns false.
     * If this value is null and {@code isElseFilter} is false,
     * this means that this {@code Rule} should fire for all features.
     *
     * @return Filter, use Filter.INCLUDES to indicate everything; or Filter.EXCLUDES for an "else" rule
     */
    @XmlElement("Filter")
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
     * Returns the list of Symbolizer objects contained by this {@code Rule}.
     *
     * We use a list of {@code <? extends Symbolizer>} to enable the possibility
     * for an implementation to return a special type of Symbolizer.
     * This doesn't mean a Rule must return a list of PointSymbolizer or TextSymbolizer only.
     * The purpose of this if to offer the solution to return different implementations like
     * MutableSymbolizer or RichSymbolizer and then avoid redundant cast in the code.
     *
     * @return the list of Symbolizer
     */
    @XmlElement("Symbolizer")
    List<? extends Symbolizer> symbolizers();

    /**
     * If the style comes from an external XML file, the original source.
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
