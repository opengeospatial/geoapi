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
package org.opengis.temporal;

// J2SE direct dependencies
import java.util.Collection;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * An aggregation of connected {@linkplain TemporalTopologicalPrimitive temporal topological
 * primitives}. This is the only subclass of {@linkplain TemporalComplex temporal complex}. 
 * 
 * @author Alexander Petkov
 */
@UML(identifier="TM_TopologicalComplex", specification=ISO_19108)
public interface TemporalTopologicalComplex extends TemporalComplex {
    /**
     * The aggregation of connected {@linkplain TemporalTopologicalPrimitive temporal topological
     * primitives}.
     *
     * @todo Missing UML annotation.
     */
    Collection<TemporalTopologicalPrimitive> getTemporalTopologicalPrimitives();
}
