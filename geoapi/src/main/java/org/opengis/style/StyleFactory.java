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
package org.opengis.style;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.measure.unit.Unit;
import javax.swing.Icon;

import org.opengis.feature.type.Name;
import org.opengis.filter.Filter;
import org.opengis.filter.Id;
import org.opengis.filter.expression.Expression;
import org.opengis.metadata.citation.OnLineResource;
import org.opengis.util.InternationalString;

/**
 * Factory used in the production of style objects.
 * <p>
 * This factory is responsible for the production of style objects; where noted
 * these create methods are in agreement with the Symbology Encoding 1.1
 * specification.
 */
public interface StyleFactory {

    AnchorPoint anchorPoint(Expression x, Expression y);

    ChannelSelection channelSelection(SelectedChannelType gray);

    ChannelSelection channelSelection(SelectedChannelType red,
            SelectedChannelType green, SelectedChannelType blue);

    /**
     * Wrap up a "Categorize" function using the provided expressions.
     * <p>
     * The function will be created based on:
	 * <ol>
	 * <li>PropertyName; use "Rasterdata" to indicate this is a color map
	 * <li>Literal: lookup value
	 * <li>Literal: threshold 1
	 * <li>Literal: value 1
	 * <li>Literal: threshold 2
	 * <li>Literal: value 2
	 * <li>Literal: (Optional) succeeding or preceding
	 * </ol>
     * @param propertyName
     *            Property name to categorize, or use "Raster"
     * @param mapping
     *            Defined as a series of Expressions
     * @return ColorMap wrapped around the "Cateogize" function
     */
    ColorMap colorMap(Expression propertyName, Expression... mapping);

    /**
     * Wrap up a replacement function using the provided expressions.
     * 
     * @param propertyName
     *            Property name to categorize, or use "Raster"
     * @param mapping
     *            Defined as a series of Expressions
     * @return ColorReplacement wrapped around a Function
     */
    ColorReplacement colorReplacement(Expression propertyName,
            Expression... mapping);

    ContrastEnhancement contrastEnhancement(Expression gamma,
            ContrastMethod method);

    Description description(InternationalString title,
            InternationalString description);

    Displacement displacement(Expression dx, Expression dy);

    ExternalGraphic externalGraphic(OnLineResource resource,
            String format, Collection<ColorReplacement> replacements);

    ExternalGraphic externalGraphic(Icon inline,
            Collection<ColorReplacement> replacements);

    ExternalMark externalMark(OnLineResource resource, String format,
            int markIndex);

    ExternalMark externalMark(Icon inline);

    /**
     * 
     * @param name
     * @param description
     * @param definedFor
     * @param featureTypeNames
     * @param types
     * @param rules
     *            May not be null or empty
     * @return
     */
    FeatureTypeStyle featureTypeStyle(String name,
            Description description, Id definedFor, Set<Name> featureTypeNames,
            Set<SemanticType> types, List<Rule> rules);

    Fill fill(GraphicFill fill, Expression color, Expression opacity);

    Font font(List<Expression> family, Expression style,
            Expression weight, Expression size);

    GraphicFill graphicFill(List<GraphicalSymbol> symbols,
            Expression opacity, Expression size, AnchorPoint anchorPoint,
            Displacement displacement);

    GraphicLegend graphicLegend(List<GraphicalSymbol> symbols,
            Expression opacity, Expression size, AnchorPoint anchorPoint,
            Displacement displacement);

    GraphicStroke graphicStroke(List<GraphicalSymbol> symbols,
            Expression opacity, Expression size, AnchorPoint anchorPoint,
            Displacement displacement, Expression initialGap, Expression gap);

    Halo halo(Fill fill, Expression radius);

    LinePlacement linePlacement(Expression offset, Expression initialGap,
            Expression gap, boolean repeated, boolean aligned,
            boolean generalizedLine);

    LineSymbolizer lineSymbolizer(Expression geometry,
            Description description, Unit unit, Stroke stroke, Expression offset);

    Mark mark(Expression wellKnownName, Fill fill, Stroke stroke);

    Mark mark(ExternalMark externalMark, Fill fill, Stroke stroke);

    PointPlacement pointPlacement(AnchorPoint anchor,
            Displacement displacement, Expression rotation);

    PointSymbolizer pointSymbolizer(Expression geometry,
            Description description, Unit unit, Graphic graphic);

    PolygonSymbolizer polygonSymbolizer(Expression geometry,
            Description description, Unit unit, Stroke stroke, Fill fill,
            Displacement displacement, Expression offset);

    RasterSymbolizer rasterSymbolizer(Expression geometry,
            Description description, Unit unit, Expression opacity,
            ChannelSelection channelSelection,
            OverlapBehavior overlapsBehaviour, ColorMap colorMap,
            ContrastEnhancement contrast, ShadedRelief shaded,
            LineSymbolizer outline);

    Rule rule(String name, Description description, GraphicLegend legend,
            double min, double max, List<Symbolizer> symbolizers, Filter filter);

    Rule rule(String name, Description description, GraphicLegend legend,
            double min, double max, List<Symbolizer> symbolizers);

    SelectedChannelType selectedChannelType();

    SemanticType semanticType(String name, ContrastEnhancement contrast);

    ShadedRelief shadedRelief(Expression reliefFactor,
            boolean brightnessOnly);

    Stroke stroke(GraphicStroke stroke, GraphicFill fill,
            Expression color, Expression opacity, Expression width,
            Expression lineJoin, Expression lineCap, float dashArray[],
            Expression dashOffset);

    Style style(String name, Description description, boolean isDefault,
            List<FeatureTypeStyle> featureTypeStyles,
            Symbolizer defaultSymbolizer);

    TextSymbolizer textSymbolizer(Expression geometry,
            Description description, Unit unit, Expression label, Font font,
            LabelPlacement placement, Halo halo, Fill fill);
}
