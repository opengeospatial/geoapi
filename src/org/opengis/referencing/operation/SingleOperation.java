/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.referencing.operation;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A single (not {@linkplain ConcatenatedOperation concatenated}) coordinate operation.
 *  
 * @version <A HREF="http://portal.opengeospatial.org/files/?artifact_id=6716">Abstract specification 2.0</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 *
 * @revisit OpenGIS is considering to remove this class from UML diagrams. In this case. The
 *          removal of {@code SingleOperation} interface would simplify the API and brings more
 *          flexibility: {@link ConcatenatedOperation} would be a legal component in both
 *          other {@link ConcatenatedOperation} and in {@link PassThroughOperation}.
 */
@UML(identifier="CC_SingleOperation", specification=ISO_19111)
public interface SingleOperation extends CoordinateOperation {
}
