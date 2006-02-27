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
import java.util.Collection;

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
@UML(identifier="RecordSchema", specification=ISO_19103)
public interface RecordSchema {
    /**
     */
    @UML(identifier="schemaName", obligation=MANDATORY, specification=ISO_19103)
    LocalName getSchemaName();
    
    /**
     */
    @UML(identifier="featureClassDescription", obligation=MANDATORY, specification=ISO_19103)
    Map<TypeName, RecordType> getFeatureClassDescription();

    /**
     */
    @UML(identifier="element", obligation=OPTIONAL, specification=ISO_19103)
    Collection<RecordType> getElements();

    /**
     */
    @UML(identifier="locate", obligation=MANDATORY, specification=ISO_19103)
    RecordType locate(TypeName name);
}
