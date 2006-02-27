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
package org.opengis.util;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * The name to identify a member of a record.
 *
 * @author Bryce Nordgren (USDA)
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.1
 */
@UML(identifier="AttributeName", specification=ISO_19103)
public interface AttributeName extends LocalName {
    /**
     * Returns the type of the data associated with the record member.
     */
    @UML(identifier="attributeType", obligation=MANDATORY, specification=ISO_19103)
    TypeName getAttributeType();
}
