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
package org.opengis.util;

import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * The type definition. Every {@code Type} is {@linkplain #getTypeName identified} by a
 * {@link TypeName}.
 * <p>
 * This class can be think as the equivalent of the Java {@link Class} class.
 *
 * @author Bryce Nordgren (USDA)
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.3
 *
 * @navassoc 1 - - TypeName
 */
public interface Type {
    /**
     * Returns the name that identifies this type.
     * This method can be think as the equivalent of the Java {@link Class#getName()} method.
     *
     * @return The name that identifies this type.
     */
    @UML(identifier="typeName", obligation=MANDATORY, specification=ISO_19103)
    TypeName getTypeName();
}
