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
package org.opengis.coverage;

import javax.measure.Unit;
import org.opengis.util.InternationalString;
import org.opengis.referencing.operation.MathTransform1D;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Contains information for an individual sample dimension of {@linkplain Coverage coverage}.
 * This interface is applicable to any coverage type.
 * For {@linkplain org.opengis.coverage.grid.GridCoverage grid coverages},
 * the sample dimension refers to an individual band.
 *
 * <div class="warning"><b>Warning — this class will change</b><br>
 * Current API is derived from OGC <a href="http://www.opengis.org/docs/01-004.pdf">Grid Coverages Implementation specification 1.0</a>.
 * We plan to replace it by new interfaces derived from ISO 19123 (<cite>Schema for coverage geometry
 * and functions</cite>). Current interfaces should be considered as legacy and are included in this
 * distribution only because they were part of GeoAPI 1.0 release. We will try to preserve as much
 * compatibility as possible, but no migration plan has been determined yet.
 * </div>
 *
 * @version <A HREF="http://www.opengis.org/docs/01-004.pdf">Grid Coverage specification 1.0</A>
 * @author  Martin Desruisseaux (IRD)
 * @since   GeoAPI 1.0
 */
@UML(identifier="CV_SampleDimension", specification=OGC_01004)
public interface SampleDimension {
    /**
     * Sample dimension title or description.
     * This string may be null or empty if no description is present.
     *
     * @return a description for this sample dimension.
     */
    @UML(identifier="description", obligation=MANDATORY, specification=OGC_01004)
    InternationalString getDescription();

    /**
     * A code value indicating grid value data type.
     * This will also indicate the number of bits for the data type.
     *
     * @return a code value indicating grid value data type.
     */
    @UML(identifier="sampleDimensionType", obligation=MANDATORY, specification=OGC_01004)
    SampleDimensionType getSampleDimensionType();

    /**
     * Sequence of category names for the values contained in a sample dimension.
     * This allows for names to be assigned to numerical values.
     * The first entry in the sequence relates to a cell value of zero.
     * For grid coverages, category names are only valid for a classified grid data.
     *
     * For example:<br>
     *  <UL>
     *    <li>0 Background</li>
     *    <li>1 Water</li>
     *    <li>2 Forest</li>
     *    <li>3 Urban</li>
     *  </UL>
     * Note: If no category names exist, an empty sequence is returned.
     *
     * @return the category names.
     */
    @UML(identifier="categoryNames", obligation=MANDATORY, specification=OGC_01004)
    InternationalString[] getCategoryNames();

    /**
     * Color interpretation of the sample dimension.
     * A sample dimension can be an index into a color palette or be a color model
     * component. If the sample dimension is not assigned a color interpretation the
     * value is {@link ColorInterpretation#UNDEFINED UNDEFINED}.
     *
     * @return the color interpretation of the sample dimension.
     *
     * @deprecated No replacement.
     */
    @UML(identifier="colorInterpretation", obligation=MANDATORY, specification=OGC_01004)
    ColorInterpretation getColorInterpretation();

    /**
     * Indicates the type of color palette entry for sample dimensions which have a
     * palette. If a sample dimension has a palette, the color interpretation must
     * be {@link ColorInterpretation#GRAY_INDEX GRAY_INDEX}
     * or {@link ColorInterpretation#PALETTE_INDEX PALETTE_INDEX}.
     * A palette entry type can be Gray, RGB, CMYK or HLS.
     *
     * @return the type of color palette entry for sample dimensions which have a palette.
     *
     * @deprecated No replacement.
     */
    @UML(identifier="paletteInterpretation", obligation=MANDATORY, specification=OGC_01004)
    PaletteInterpretation getPaletteInterpretation();

    /**
     * Color palette associated with the sample dimension.
     * A color palette can have any number of colors.
     * See palette interpretation for meaning of the palette entries.
     * If the grid coverage has no color palette, {@code null} will be returned.
     *
     * @return the color palette associated with the sample dimension.
     *
     * @see #getPaletteInterpretation
     * @see #getColorInterpretation
     * @see java.awt.image.IndexColorModel
     *
     * @deprecated No replacement.
     */
    @UML(identifier="palette", obligation=MANDATORY, specification=OGC_01004)
    int[][] getPalette();

    /**
     * Values to indicate no data values for the sample dimension.
     * For low precision sample dimensions, this will often be no data values.
     *
     * @return the values to indicate no data values for the sample dimension.
     *
     * @see #getMinimumValue
     * @see #getMaximumValue
     */
    @UML(identifier="noDataValue", obligation=MANDATORY, specification=OGC_01004)
    double[] getNoDataValues();

    /**
     * The minimum value occurring in the sample dimension.
     * This value can be empty if this value is not provided by the implementation.
     *
     * @return the minimum value occurring in the sample dimension.
     *
     * @see #getMaximumValue
     * @see #getNoDataValues
     */
    @UML(identifier="minimumValue", obligation=MANDATORY, specification=OGC_01004)
    double getMinimumValue();

    /**
     * The maximum value occurring in the sample dimension.
     * This value can be empty if this value is not provided by the implementation.
     *
     * @return the maximum value occurring in the sample dimension.
     *
     * @see #getMinimumValue
     * @see #getNoDataValues
     */
    @UML(identifier="maximumValue", obligation=MANDATORY, specification=OGC_01004)
    double getMaximumValue();

    /**
     * The unit information for this sample dimension.
     * This interface typically is provided with grid coverages which represent
     * digital elevation data.
     * This value will be {@code null} if no unit information is available.
     *
     * @return the unit information for this sample dimension.
     */
    @UML(identifier="units", obligation=MANDATORY, specification=OGC_01004)
    Unit<?> getUnits();

    /**
     * Offset is the value to add to grid values for this sample dimension.
     * This attribute is typically used when the sample dimension represents
     * elevation data. The default for this value is 0.
     *
     * @return the offset.
     *
     * @see #getScale
     */
    @UML(identifier="offset", obligation=MANDATORY, specification=OGC_01004)
    double getOffset();

    /**
     * Scale is the value which is multiplied to grid values for this sample dimension.
     * This attribute is typically used when the sample dimension represents elevation
     * data. The default for this value is 1.
     *
     * @return the scale factor.
     *
     * @see #getOffset
     */
    @UML(identifier="scale", obligation=MANDATORY, specification=OGC_01004)
    double getScale();

    /**
     * The transform which is applied to grid values for this sample dimension.
     * This transform is often defined as
     * <var>y</var> = {@linkplain #getOffset offset} + {@link #getScale scale}×<var>x</var> where
     * <var>x</var> is the grid value and <var>y</var> is the geophysics value.
     * However, this transform may also defines more complex relationship, for
     * example a logarithmic one. In order words, this transform is a generalization of
     * {@link #getScale}, {@link #getOffset} and {@link #getNoDataValues} methods.
     *
     * @departure generalization
     *   Added this optional method as a generalization of <code>scale</code> and <code>offset</code>
     *   attributes. Note that ISO 19115-2 refers to a similar function as <cite>"the transfert function"</cite>.
     *
     * @return the transform from sample to geophysics values, or {@code null} if
     *         it doesn't apply.
     *
     * @see #getScale
     * @see #getOffset
     * @see #getNoDataValues
     */
    MathTransform1D getSampleToGeophysics();
}
