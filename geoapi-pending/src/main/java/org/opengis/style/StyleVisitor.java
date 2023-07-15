/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2008-2023 Open Geospatial Consortium, Inc.
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

/**
 * An interface for classes that want to perform operations on a Style
 * hierarchy. It forms part of a GoF Visitor Pattern implementation.
 *
 * <p>A call to style.accept(StyleVisitor) will result in a call to one of the
 * methods in this interface. The responsibility for traversing sub filters is
 * intended to lie with the visitor (this is unusual, but permitted under the
 * Visitor pattern).</p>
 *
 * <p>A typical use would be to transcribe a style into a specific format, e.g. XML or SQL.
 * Alternatively, it may be to extract specific information from the Style structure, for example a list of all fills.
 * Finally a a style visitor is often used (in conjunction with a factory) in the production of a
 * copy; or slightly modified copy of the original style.</p>
 *
 * @see StyleFactory
 * @author Open Geospatial Consortium
 * @author James Macgill
 * @author Ian Turton
 * @author Johann Sorel (Geomatys)
 */
public interface StyleVisitor {
    /**
     * Called when accept is called on a Style.
     *
     * @param style The style to visit
     */
    Object visit(Style style, Object data);

    /**
     * Called when accept is called on a FeatureTypeStyle
     *
     * @param style the feature type styler to visit
     */
    Object visit(FeatureTypeStyle style, Object data);

    /**
     * Called when accept is called on a rule
     *
     * @param rule the rule to visit
     */
    Object visit(Rule rule, Object data);

    /**
     * Called when accept is called on a pointsymbolizer
     *
     * @param symbolizer the point symbolizer to visit
     */
    Object visit(PointSymbolizer symbolizer, Object data);

    /**
     * Called when accept is called on a linesymbolizer
     *
     * @param symbolizer the line symbolizer to visit
     */
    Object visit(LineSymbolizer symbolizer, Object data);

    /**
     * Called when accept is called on a polygon symbolizer
     *
     * @param symbolizer the polygon symbolizer to visit
     */
    Object visit(PolygonSymbolizer symbolizer, Object data);

    /**
     * Called when accept is called on a textsymbolizer
     *
     * @param symbolizer the text symbolizer to visit
     */
    Object visit(TextSymbolizer symbolizer, Object data);

    /**
     * Called when accept is called on a rastersymbolizer
     *
     * @param symbolizer the raster symbolizer to visit
     */
    Object visit(RasterSymbolizer symbolizer, Object data);

    /**
     * Called when accept is called on a extension symbolizer
     *
     * @param symbolizer the extension symbolizer to visit
     */
    Object visit(ExtensionSymbolizer symbolizer, Object data);

    /**
     * Called when accept is called on a description
     *
     * @param description the description to visit
     */
    Object visit(Description description, Object data);

    /**
     * Called when accept is called on a displacement
     *
     * @param displacement the displacement to visit
     */
    Object visit(Displacement displacement, Object data);

    /**
     * Called when accept is called on a fill
     *
     * @param fill the fill to be visited
     */
    Object visit(Fill fill, Object data);

    /**
     * Called when accept is called on a font
     *
     * @param font the font to be visited
     */
    Object visit(Font font, Object data);

    /**
     * Called when accept is called on a stroke
     *
     * @param stroke the stroke to visit
     */
    Object visit(Stroke stroke, Object data);

    /**
     * Called when accept is called on a graphic
     *
     * @param graphic the graphic to visit
     */
    Object visit(Graphic graphic, Object data);

    /**
     * Called when accept is called on a graphic fill
     *
     * @param fill the graphic fill to visit
     */
    Object visit(GraphicFill fill, Object data);

    /**
     * Called when accept is called on a graphic stroke
     *
     * @param stroke the graphic stroke to visit
     */
    Object visit(GraphicStroke stroke, Object data);

    /**
     * Called when accept is called on a mark
     *
     * @param mark the mark to visit
     */
    Object visit(Mark mark, Object data);

    /**
     * Called when accept is called on a external mark
     *
     * @param mark the external mark to visit
     */
    Object visit(ExternalMark mark, Object data);

    /**
     * Called when accept is called on a external graphic
     *
     * @param graphic the external graphic to visit
     */
    Object visit(ExternalGraphic graphic, Object data);

    /**
     * Called when accept is called on a Point Placement
     *
     * @param placement the point placement to visit
     */
    Object visit(PointPlacement placement, Object data);

    /**
     * Called when accept is called on a anchor point
     *
     * @param anchor the anchor point to visit
     */
    Object visit(AnchorPoint anchor, Object data);

    /**
     * Called when accept is called on a Line Placement
     *
     * @param placement the line placement to visit
     */
    Object visit(LinePlacement placement, Object data);

    /**
     * Called when accept is called on a legend graphic
     *
     * @param legend the legend graphic to visit
     */
    Object visit(GraphicLegend legend, Object data);

    /**
     * Called when accept is called on a halo
     *
     * @param halo the halo to visit
     */
    Object visit(Halo halo, Object data);

    /**
     * Called when accept is called on a raster color map
     *
     * @param colors the color map to visit
     */
    Object visit(ColorMap colors, Object data);

    /**
     * Called when accept is called on a color replacement
     *
     * @param colors the color replacement to visit
     */
    Object visit(ColorReplacement colors, Object data);

    /**
     * Called when accept is called on a raster ContrastEnhancement element
     * @param enhancement the {@link ContrastEnhancement} to visit.
     */
    Object visit(ContrastEnhancement enhancement, Object data);

    /**
     * Called when accept is called on a raster {@link ChannelSelection} element
     * @param selection the {@link ChannelSelection} to visit.
     */
    Object visit(ChannelSelection selection, Object data);

    /**
     * Called when accept is called on a raster {@link SelectedChannelType} element
     * @param type the {@link SelectedChannelType} to visit.
     */
    Object visit(SelectedChannelType type, Object data);

    /**
     * Called when accept is called on a raster {@link ShadedRelief} element
     * @param relief the {@link ShadedRelief} to visit.
     */
    Object visit(ShadedRelief relief, Object data);
}
