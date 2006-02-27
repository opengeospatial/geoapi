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

// J2SE direct dependencies
import java.util.Map;

// Annotations
import org.opengis.annotation.UML;
import org.opengis.annotation.Extension;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 *
 * @author Bryce Nordgren (USDA)
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.1
 */
@UML(identifier="RecordType", specification=ISO_19103)
public interface RecordType {
    /**
     */
    @UML(identifier="typeName", obligation=MANDATORY, specification=ISO_19103)
    TypeName getTypeName();

    /**
     */
    @UML(identifier="container", obligation=OPTIONAL, specification=ISO_19103)
    RecordSchema getContainer();

    /**
     */
    @UML(identifier="attributeTypes", obligation=MANDATORY, specification=ISO_19103)
    Map<AttributeName, TypeName> getAttributeTypes();

    /**
     */
    @UML(identifier="locate", obligation=MANDATORY, specification=ISO_19103)
    TypeName locate(AttributeName name);
}
