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
package org.opengis.sld;

import org.opengis.filter.expression.ExpressionVisitor;
import org.opengis.style.Style;
import org.opengis.style.StyleVisitor;

/**
 * An interface for classes that want to perform operations on a SLD
 * hierarchy. It forms part of a GoF Visitor Pattern implementation.
 * <p>
 * A call to sld.accept(SLDVisitor) will result in a call to one of the
 * methods in this interface. The responsibility for traversing sub filters is
 * intended to lie with the visitor (this is unusual, but permitted under the
 * Visitor pattern).
 * <p>
 * A typical use would be to transcribe a sld into a specific format, e.g. XML or SQL.
 * Alternatively it may be to extract specific information from the SLD structure, for example a list of all fills.
 * Finally a a sld visitor is often used (in conjunction with a factory) in the production of a
 * copy; or slightly modified copy of the original sld.
 * <p>
 * It is common practice for a SLDVisitor to also implement a StyleVisitor and ExpressionVisitor in
 * order to traverse all data structures.
 * 
 * @see ExpressionVisitor
 * @see StyleVisitor
 * @see StyleFactory
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
public interface SLDVisitor {
    /**
     * Called when accept is called on a StyledLayerDescriptor.
     *
     * @param sld The style layer descriptor to visit
     */
    Object visit(StyledLayerDescriptor sld, Object data );
    
    /**
     * Called when accept is called on a SLDLibrary.
     *
     * @param library The SLD library to visit
     */
    Object visit(SLDLibrary library, Object data );
    
    /**
     * Called when accept is called on a named layer.
     *
     * @param layer The named layer to visit
     */
    Object visit(NamedLayer layer, Object data );
    
    /**
     * Called when accept is called on a user layer.
     *
     * @param layer The user layer to visit
     */
    Object visit(UserLayer layer, Object data );
    
    /**
     * Called when accept is called on a named style.
     *
     * @param style The named style to visit
     */
    Object visit(NamedStyle style, Object data );
    
    /**
     * Called when accept is called on a user style.
     *
     * @param style The user style to visit
     */
    Object visit(Style style, Object data );
    
    /**
     * Called when accept is called on a layer coverage constraints.
     *
     * @param constraints The layer coverage constraints to visit
     */
    Object visit(LayerCoverageConstraints constraints, Object data );
    
    /**
     * Called when accept is called on a layer feature constraints.
     *
     * @param constraints The layer feature constraints to visit
     */
    Object visit(LayerFeatureConstraints constraints, Object data );
    
    /**
     * Called when accept is called on a coverage constraint.
     *
     * @param constraint The coverage constraint to visit
     */
    Object visit(CoverageConstraint constraint, Object data );
    
    /**
     * Called when accept is called on a featrure constraint.
     *
     * @param constraint The feature constraint to visit
     */
    Object visit(FeatureTypeConstraint constraint, Object data );
    
    /**
     * Called when accept is called on a coverage extent.
     *
     * @param extent The coverage extent to visit
     */
    Object visit(CoverageExtent extent, Object data );
    
    /**
     * Called when accept is called on a feature extent.
     *
     * @param extent The feature extent to visit
     */
    Object visit(Extent extent, Object data );
    
    /**
     * Called when accept is called on a range axis.
     *
     * @param axi The range axi to visit
     */
    Object visit(RangeAxis axi, Object data );
    
    /**
     * Called when accept is called on a remoteOWS.
     *
     * @param ows The remoteOWS to visit
     */
    Object visit(RemoteOWS ows, Object data );
    
    /**
     * Called when accept is called on an InlineFeature.
     *
     * @param inline The InlineFeature to visit
     */
    Object visit(InlineFeature inline, Object data );
    
    
}
