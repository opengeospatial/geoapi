/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */
package org.opengis.metadata.constraint;

import java.util.Collection;
import java.util.Collections;
import org.opengis.util.InternationalString;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Restrictions and legal prerequisites for accessing and using the resource.
 *
 * <p><b>Conditional properties:</b></p>
 * All methods in this interface have default methods. But despite that, at least one of
 * {@linkplain #getAccessConstraints() access constraints},
 * {@linkplain #getUseConstraints() use constraints},
 * {@linkplain #getUseLimitations() use limitations},
 * {@linkplain #getOtherConstraints() other constraints} and
 * {@linkplain #getReleasability() releasibility} shall be provided.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @version 3.1
 * @since   2.0
 */
@UML(identifier="MD_LegalConstraints", specification=ISO_19115)
public interface LegalConstraints extends Constraints {
    /**
     * Access constraints applied to assure the protection of privacy or intellectual property,
     * and any special restrictions or limitations on obtaining the resource or metadata.
     *
     * @return access constraints applied to assure the protection of privacy or intellectual property.
     *
     * @condition Mandatory if
     * {@linkplain #getUseConstraints() use constraints},
     * {@linkplain #getOtherConstraints() other constraints},
     * {@linkplain #getUseLimitations() use limitations} and
     * {@linkplain #getReleasability() releasibility} are null or empty.
     */
    @UML(identifier="accessConstraints", obligation=CONDITIONAL, specification=ISO_19115)
    default Collection<Restriction> getAccessConstraints() {
        return Collections.emptyList();
    }

    /**
     * Constraints applied to assure the protection of privacy or intellectual property, and any
     * special restrictions or limitations or warnings on using the resource or metadata.
     *
     * @return constraints applied to assure the protection of privacy or intellectual property.
     *
     * @condition Mandatory if
     * {@linkplain #getAccessConstraints() access constraints},
     * {@linkplain #getOtherConstraints() other constraints},
     * {@linkplain #getUseLimitations() use limitations} and
     * {@linkplain #getReleasability() releasibility} are null or empty.
     */
    @UML(identifier="useConstraints", obligation=CONDITIONAL, specification=ISO_19115)
    default Collection<Restriction> getUseConstraints() {
        return Collections.emptyList();
    }

    /**
     * Other restrictions and legal prerequisites for accessing and using the resource or metadata.
     *
     * @return other restrictions and legal prerequisites for accessing and using the resource.
     *
     * @condition Mandatory if the {@linkplain #getAccessConstraints() access constraints} or
     *            {@linkplain #getUseConstraints() use constraints} contain {@link Restriction#OTHER_RESTRICTIONS}.
     */
    @UML(identifier="otherConstraints", obligation=CONDITIONAL, specification=ISO_19115)
    default Collection<? extends InternationalString> getOtherConstraints() {
        return Collections.emptyList();
    }
}
