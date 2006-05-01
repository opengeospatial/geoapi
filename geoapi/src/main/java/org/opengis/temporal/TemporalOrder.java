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

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Provides an operation for determining the position of this {@link Primitive}
 * relative to another {@link Primitive}.
 *
 * @author Stephane Fellah (Image Matters)
 * @author Alexander Petkov
 *
 * @todo The javadoc suggests that this interface should extends some kind of {@link Primitive}.
 */
@UML(identifier="TM_Order", specification=ISO_19108)
public interface TemporalOrder {
    /**
     * Determines the position of this primitive relative to another primitive.
     */
    @UML(identifier="relativePosition", obligation=MANDATORY, specification=ISO_19108)
    RelativePosition relativePosition(TemporalPrimitive other);
}
