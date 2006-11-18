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
package org.opengis.coverage.grid;

// J2SE direct dependencies
import java.util.List;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Ties feature attributes values to the a grid geometry. It holds a sequence of records associated
 * with a sequencing rule that specifies an algorithm for assigning records of feature attribute
 * values to grid points.
 * 
 * @author Wim Koolhoven
 * @author Martin Schouwenburg
 */
@UML(identifier="CV_GridValuesMatrix", specification=ISO_19123)
public interface GridValuesMatrix extends Grid {
    /**
     * Returns a sequence of <var>N</var> feature attribute value records where <var>N</var> is the
     * number of {@linkplain GridPoint grid points} within the section of the grid specified by
     * the {@linkplain #getExtent extent}.
     *
     * @todo Comment (Wim): Both extracting an image for display and operation chaining require
     *       memory effiecient and fast access to the data. How should the data be structured per
     *       record? Some use cases:
     *       <table border=1>
     *         <tr><td>DTM<td>double<td>List&lt;double&gt;
     *         <tr><td>TM image<td>7 bytes<td>
     *         <tr><td>Landuse map<td>string<td>{@code List<double>} with look up table for the strings
     *       </table>   
     */
	@UML(identifier="values", obligation=MANDATORY, specification=ISO_19123)
	List/*<Record>*/ getValues();

	/**
	 * Describes how the grid points are ordered for association to the elements of the sequence
     * values.
	 */
	@UML(identifier="sequencingRule", obligation=MANDATORY, specification=ISO_19123)
	SequenceRule getSequencingRule();

	/**
	 * Identifies the grid point to be associated with the first record
     * in the values sequence.
	 */
	@UML(identifier="startSequence", obligation=MANDATORY, specification=ISO_19123)
	GridCoordinates getStartSequence();

	/**
	 * Returns the limits of a section of the grid. This envelope can not be empty.
	 */
    @UML(identifier="extent", obligation=OPTIONAL, specification=ISO_19123)
	GridEnvelope getExtent();
}
