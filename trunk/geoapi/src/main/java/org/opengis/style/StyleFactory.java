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
	AnchorPoint createAnchorPoint(Expression x, Expression y);

	ChannelSelection createChannelSelection(SelectedChannelType gray);

	ChannelSelection createChannelSelection(SelectedChannelType red,
			SelectedChannelType green, SelectedChannelType blue);

	/**
	 * Wrap up a "Categorize" function using the provided expressions.
	 * 
	 * @param propertyName
	 *            Property name to categorize, or use "Raster"
	 * @param mapping
	 *            Defined as a series of Expressions
	 * @return ColorMap wrapped around the "Cateogize" function
	 */
	ColorMap createColorMap(Expression propertyName, Expression... mapping);

	/**
	 * Wrap up a replacement function using the provided expressions.
	 * 
	 * @param propertyName
	 *            Property name to categorize, or use "Raster"
	 * @param mapping
	 *            Defined as a series of Expressions
	 * @return ColorReplacement wrapped around a Function
	 */
	ColorReplacement createColorReplacement(Expression propertyName,
			Expression... mapping);

	ContrastEnhancement createContrastEnhancement(Expression gamma,
			ContrastMethod method);

	Description createDescription(InternationalString title,
			InternationalString description);

	Displacement createDisplacement(Expression dx, Expression dy);

	ExternalGraphic createExternalGraphic(OnLineResource resource,
			String format, Collection<ColorReplacement> replacements);

	ExternalGraphic createExternalGraphic(Icon inline,
			Collection<ColorReplacement> replacements);

	ExternalMark createExternalMark(OnLineResource resource, String format,
			int markIndex);

	ExternalMark createExternalMark(Icon inline);

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
	FeatureTypeStyle createFeatureTypeStyle(String name,
			Description description, Id definedFor, Set<Name> featureTypeNames,
			Set<SemanticType> types, List<Rule> rules);

	Fill createFill(GraphicFill fill, Expression color, Expression opacity);

	Font createFont(List<Expression> family, Expression style,
			Expression weight, Expression size);

	GraphicFill createGraphicFill(List<GraphicalSymbol> symbols,
			Expression opacity, Expression size, AnchorPoint anchorPoint,
			Displacement displacement);

	GraphicLegend createGraphicLegend(List<GraphicalSymbol> symbols,
			Expression opacity, Expression size, AnchorPoint anchorPoint,
			Displacement displacement);

	GraphicStroke createGraphicStroke(List<GraphicalSymbol> symbols,
			Expression opacity, Expression size, AnchorPoint anchorPoint,
			Displacement displacement, Expression initialGap, Expression gap);

	Halo createHalo(Fill fill, Expression radius);

	LinePlacement createLinePlacement(Expression offset, Expression initialGap,
			Expression gap, boolean repeated, boolean aligned,
			boolean generalizedLine);

	LineSymbolizer createLineSymbolizer(Expression geometry,
			Description description, Unit unit, Stroke stroke, Expression offset);

	Mark createMark(Expression wellKnownName, Fill fill, Stroke stroke);

	Mark createMark(ExternalMark externalMark, Fill fill, Stroke stroke);

	PointPlacement createPointPlacement(AnchorPoint anchor,
			Displacement displacement, Expression rotation);

	PointSymbolizer createPointSymbolizer(Expression geometry,
			Description description, Unit unit, Graphic graphic);

	PolygonSymbolizer createPolygonSymbolizer(Expression geometry,
			Description description, Unit unit, Stroke stroke, Fill fill,
			Displacement displacement, Expression offset);

	RasterSymbolizer createRasterSymbolizer(Expression geometry,
			Description description, Unit unit, Expression opacity,
			ChannelSelection channelSelection,
			OverlapBehavior overlapsBehaviour, ColorMap colorMap,
			ContrastEnhancement contrast, ShadedRelief shaded,
			LineSymbolizer outline);

	Rule createRule(String name, Description description, GraphicLegend legend,
			double min, double max, List<Symbolizer> symbolizers, Filter filter);

	Rule createElse(String name, Description description, GraphicLegend legend,
			double min, double max, List<Symbolizer> symbolizers);

	SelectedChannelType createSelectedChannelType();

	SemanticType createSemanticType(String name, ContrastEnhancement contrast);

	ShadedRelief createShadedRelief(Expression reliefFactor,
			boolean brightnessOnly);

	Stroke createStroke(GraphicStroke stroke, GraphicFill fill,
			Expression color, Expression opacity, Expression width,
			Expression lineJoin, Expression lineCap, float dashArray[],
			Expression dashOffset);

	Style createStyle(String name, Description description, boolean isDefault,
			List<FeatureTypeStyle> featureTypeStyles,
			Symbolizer defaultSymbolizer);

	TextSymbolizer createTextSymbolizer(Expression geometry,
			Description description, Unit unit, Expression label, Font font,
			LabelPlacement placement, Halo halo, Fill fill);
}
