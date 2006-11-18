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
 * Contains information for mapping {@linkplain GridCoordinates grid coordinates} to a position
 * within the sequence of records of feature attribute values.
 * 
 * @author Wim Koolhoven
 * @author Martin Schouwenburg
 */
@UML(identifier="CV_SequenceRule", specification=ISO_19123)
public interface SequenceRule {
    /**
     * Identifies the type of sequencing method that shall be used.
     * The default value shall be "{@linkplain SequenceType#LINEAR linear}".
     */
    @UML(identifier="type", obligation=MANDATORY, specification=ISO_19123)
    SequenceType getType();

    /**
     * Returns a list of signed {@linkplain Grid#getAxisNames axis names} that indicates the order
     * in which {@linkplain GridPoint grid points} shall be mapped to position within the sequence
     * of records of feature attribute values. An additional element may be included in the list to
     * allow for interleaving of feature attribute values.
     * Example: <code>{"x", "-y"}</code>
     */
    @UML(identifier="scanDirection", obligation=MANDATORY, specification=ISO_19123)
    List<String> getScanDirection();
}
