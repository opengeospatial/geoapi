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

import java.util.List;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Contains information for mapping {@linkplain GridCoordinates grid coordinates} to a position
 * within the sequence of records of feature attribute values.
 *
 * @version ISO 19123:2004
 * @author  Wim Koolhoven
 * @author  Martin Schouwenburg
 * @since   GeoAPI 2.1
 */
@UML(identifier="CV_SequenceRule", specification=ISO_19123)
public interface SequenceRule {
    /**
     * Identifies the type of sequencing method that shall be used.
     * The default value shall be "{@linkplain SequenceType#LINEAR linear}".
     *
     * @return The type of sequencing method.
     */
    @UML(identifier="type", obligation=MANDATORY, specification=ISO_19123)
    SequenceType getType();

    /**
     * Returns a list of signed {@linkplain Grid#getAxisNames axis names} that indicates the order
     * in which {@linkplain GridPoint grid points} shall be mapped to position within the sequence
     * of records of feature attribute values. An additional element may be included in the list to
     * allow for interleaving of feature attribute values.
     * Example: <code>{"x", "-y"}</code>
     *
     * @return An ordered list of axis names that indicates the scaning direction.
     */
    @UML(identifier="scanDirection", obligation=MANDATORY, specification=ISO_19123)
    List<String> getScanDirection();
}
