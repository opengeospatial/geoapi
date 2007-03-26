/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
// This class was created by Sanjay Jena and Prof. Jackson Roehrig in order to complete
// missing parts of the GeoAPI and submit the results to GeoAPI afterwards as proposal.

package org.opengis.spatialschema.geometry.aggregate;

// J2SE direct dependencies
import java.util.Set;

// OpenGIS direct dependencies
import org.opengis.spatialschema.geometry.primitive.OrientableCurve;
import org.opengis.spatialschema.geometry.primitive.OrientableSurface;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * An aggregate class containing only instances of {@link OrientableCurve}.
 * The association role {@link #getElements element} shall be the set of
 * {@linkplain OrientableCurve orientable curves} contained in this {@code MultiCurve}.
 * 
 * @version <A HREF="http://www.opengis.org/docs/01-101.pdf">Abstract specification 5</A>
 * @author Sanjay Jena
 * @author Prof. Dr. Jackson Roehrig
 * @since GeoAPI 2.1
 */
@UML(identifier="GM_MultiCurve", specification=ISO_19107)
public interface MultiCurve extends MultiPrimitive {
    /**
     * Returns the set containing the {@linkplain OrientableCurve orientable curves}
     * that compose this {@code MultiCurve}. The set may be modified if this geometry
     * {@linkplain #isMutable is mutable}.
     */
    @UML(identifier="element", obligation=MANDATORY, specification=ISO_19107)
    public Set<OrientableCurve> getElements();

    /**
     * Returns the accumulated length of all {@linkplain OrientableCurve orientable curves}
     * contained in this {@code MultiCurve}.
     */
    @UML(identifier="length", obligation=MANDATORY, specification=ISO_19107)
    public double length();
}
