/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.coverage;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Represents an element from the range of the {@linkplain Coverage coverage}.
 *
 * @author Martin Desruisseaux
 *
 * @revisit Do we really need this interface, since it contains only one attribute?
 */
@UML(identifier="CV_AttributeValues", specification=ISO_19123)
public interface AttributeValues {
    /**
     * Returns a record containing one value for each attribute, as specified in the
     * {@linkplain Coverage#getRangeType coverage's range type}.
     * <p>
     * <B>Examples:</B>
     * <ul>
     *   <li>A coverage with a single (scalar) value (such as elevation).</li>
     *   <li>A coverage with a series (array / tensor) of values all defined in the same way
     *       (such as brightness values in different parts of the electromagnetic spectrum).</li>
     * </ul>
     *
     * @revisit The ISO type is {@code Record}, which is not defined in GeoAPI.
     */
    @UML(identifier="values", obligation=MANDATORY, specification=ISO_19123)
    Object getValues();
}
