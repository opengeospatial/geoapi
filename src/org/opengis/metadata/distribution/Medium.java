/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.distribution;

// J2SE direct dependencies and extensions
import java.util.Collection;
import javax.units.Unit;

// OpenGIS direct dependencies
import org.opengis.util.InternationalString;

// Annotations
///import org.opengis.annotation.UML;
///import static org.opengis.annotation.Obligation.*;


/**
 * Information about the media on which the resource can be distributed.
 *
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
///@UML (identifier="MD_Medium")
public interface Medium {
    /**
     * Name of the medium on which the resource can be received.
     */
/// @UML (identifier="name", obligation=OPTIONAL)
    MediumName getName();

    /**
     * Density at which the data is recorded.
     * The numbers should be greater than zero.
     */
/// @UML (identifier="density", obligation=OPTIONAL)
    Collection/*<Number>*/ getDensities();

    /**
     * Units of measure for the recording density.
     */
/// @UML (identifier="densityUnits", obligation=CONDITIONAL)
    Unit getDensityUnits();

    /**
     * Number of items in the media identified.
     * Returns <code>null</code> if unknown.
     */
/// @UML (identifier="volumes", obligation=OPTIONAL)
    Integer getVolumes();

    /**
     * Method used to write to the medium.
     *
     * @revisit This is a list [0..N] in ISO 19115.
     *          Maybe we should returns a Map<MediumFormat,Number> instead?
     */
/// @UML (identifier="mediumFormat", obligation=OPTIONAL)
    MediumFormat getMediumFormat();

    /**
     * Description of other limitations or requirements for using the medium.
     */
/// @UML (identifier="mediumNote", obligation=OPTIONAL)
    InternationalString getMediumNote();
}
