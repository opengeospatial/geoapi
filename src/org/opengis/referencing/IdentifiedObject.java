/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.referencing;

// OpenGIS direct dependencies
import org.opengis.metadata.Identifier;
import org.opengis.util.GenericName;
import org.opengis.util.InternationalString;

// Annotations
///import org.opengis.annotation.UML;
///import static org.opengis.annotation.Obligation.*;


/**
 * Supplementary identification and remarks information for a CRS or CRS-related object.
 * When {@link org.opengis.referencing.crs.CRSAuthorityFactory} is used to create an object,
 * the {@linkplain Identifier#getAuthority authority} and {@linkplain Identifier#getCode
 * authority code} values should be set to the authority name of the factory object,
 * and the authority code supplied by the client, respectively. The other values may
 * or may not be set. If the authority is EPSG, the implementer may consider using the
 * corresponding metadata values in the EPSG tables.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version Abstract specification 2.0 (revised)
 */
///@UML (identifier="IdentifiedObject")
public interface IdentifiedObject {
    /**
     * The primary name by which this object is identified.
     */
/// @UML (identifier="name", obligation=MANDATORY)
    Identifier getName();

    /**
     * An alternative name by which this object is identified.
     *
     * @return The aliases, or an empty array if there is none.
     */
/// @UML (identifier="alias", obligation=OPTIONAL)
    GenericName[] getAlias();

    /**
     * An identifier which references elsewhere the object's defining information.
     * Alternatively an identifier by which this object can be referenced.
     *
     * @return This object identifiers, or an empty array if there is none.
     */
/// @UML (identifier="identifier", obligation=OPTIONAL)
    Identifier[] getIdentifiers();

    /**
     * Comments on or information about this object, including data source information.
     */
/// @UML (identifier="remarks", obligation=OPTIONAL)
    InternationalString getRemarks();

    /**
     * Returns a <A HREF="doc-files/WKT.html"><cite>Well Known Text</cite> (WKT)</A> for this object.
     * This operation may fails if an object is too complex for the WKT format capability (for
     * example an {@linkplain org.opengis.referencing.crs.EngineeringCRS engineering CRS} with
     * different unit for each axis).
     *
     * @return The Well Know Text for this object.
     * @throws UnsupportedOperationException If this object can't be formatted as WKT.
     */
    String toWKT() throws UnsupportedOperationException;
}
