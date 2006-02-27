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
 * locate(name).getType() == getRecordType().locate(name)
 *
 * @author Bryce Nordgren (USDA)
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.1
 */
@UML(identifier="Record", specification=ISO_19103)
public interface Record<T extends Object> {
    /**
     */
    @UML(identifier="recordType", obligation=MANDATORY, specification=ISO_19103)
    RecordType getRecordType();

    /**
     */
    @UML(identifier="attributes", obligation=MANDATORY, specification=ISO_19103)
    Map<AttributeName, T> getAttributes();

    /**
     */
    @UML(identifier="locate", obligation=MANDATORY, specification=ISO_19103)
    T locate(AttributeName name);
}
