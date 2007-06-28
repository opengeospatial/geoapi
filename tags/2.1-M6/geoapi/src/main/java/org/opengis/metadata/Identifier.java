/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.metadata;

import org.opengis.metadata.citation.Citation;
import org.opengis.annotation.UML;
import org.opengis.annotation.Extension;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Value uniquely identifying an object within a namespace.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as#01-111">ISO 19115</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@UML(identifier="MD_Identifier", specification=ISO_19115)
public interface Identifier {
    /**
     * Key for the <code>{@value}</code> property to be given to the
     * {@linkplain org.opengis.referencing.ObjectFactory CRS factory} <code>createFoo(&hellip;)</code>
     * methods. This is used for setting the value to be returned by {@link #getCode}.
     *
     * @see #getCode
     */
    String CODE_KEY = "code";

    /**
     * Key for the <code>{@value}</code> property to be given to the
     * {@linkplain org.opengis.referencing.ObjectFactory CRS factory} <code>createFoo(&hellip;)</code>
     * methods. This is used for setting the value to be returned by {@link #getAuthority}.
     *
     * @see #getAuthority
     */
    String AUTHORITY_KEY = "authority";

    /**
     * Key for the <code>{@value}</code> property to be given to the
     * {@linkplain org.opengis.referencing.ObjectFactory CRS factory} <code>createFoo(&hellip;)</code>
     * methods. This is used for setting the value to be returned by {@link #getAuthority}.
     * 
     * @deprecated Moved to {@link org.opengis.referencing.ReferenceIdentifier#VERSION_KEY}.
     */
    String VERSION_KEY = org.opengis.referencing.ReferenceIdentifier.VERSION_KEY;
    
    /**
     * Alphanumeric value identifying an instance in the namespace.
     */
    @UML(identifier="code", obligation=MANDATORY, specification=ISO_19115)
    String getCode();

    /**
     * Organization or party responsible for definition and maintenance of the
     * {@linkplain #getCode code}.
     */
    @UML(identifier="authority", obligation=OPTIONAL, specification=ISO_19115)
    Citation getAuthority();

    /**
     * Identifier of the version of the associated code, as specified by the code authority.
     * This version is included only when the {@linkplain #getCode code} uses versions. When
     * appropriate, the edition is identified by the effective date, coded using ISO 8601 date
     * format.
     * 
     * @deprecated Moved to {@link org.opengis.referencing.ReferenceIdentifier#getVersion()}.
     */
    @Extension
    String getVersion();
}
