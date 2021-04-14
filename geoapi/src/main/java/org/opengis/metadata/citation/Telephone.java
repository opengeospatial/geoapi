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
package org.opengis.metadata.citation;

import java.util.Collection;
import java.util.Collections;
import org.opengis.annotation.UML;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Telephone numbers for contacting the responsible individual or organization.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @author  Rémi Maréchal (Geomatys)
 * @version 3.1
 * @since   1.0
 *
 * @see Contact#getPhones()
 */
@Classifier(Stereotype.DATATYPE)
@UML(identifier="CI_Telephone", specification=ISO_19115)
public interface Telephone {
    /**
     * Telephone number by which individuals can contact responsible organisation or individual.
     *
     * @return telephone number by which individuals can contact responsible organisation or individual.
     *
     * @since 3.1
     */
    @UML(identifier="number", obligation=MANDATORY, specification=ISO_19115)
    String getNumber();

    /**
     * Type of telephone number, or {@code null} if none.
     *
     * @return type of telephone number, or {@code null} if none.
     *
     * @since 3.1
     */
    @UML(identifier="numberType", obligation=OPTIONAL, specification=ISO_19115)
    default TelephoneType getNumberType() {
        return null;
    }

    /**
     * Telephone numbers by which individuals can speak to the responsible organization or individual.
     *
     * @return telephone numbers by which individuals can speak to the responsible organization or individual.
     *
     * @deprecated As of ISO 19115:2014, replaced by a {@linkplain #getNumber() number}
     *             with {@link TelephoneType#VOICE}.
     */
    @Deprecated
    @UML(identifier="voice", obligation=OPTIONAL, specification=ISO_19115, version=2003)
    default Collection<String> getVoices() {
        if (TelephoneType.VOICE.equals(getNumberType())) {
            String number = getNumber();
            if (number != null) {
                return Collections.singleton(number);
            }
        }
        return Collections.emptySet();          // Use Set instead of List for hash-safe final classes.
    }

    /**
     * Telephone numbers of a facsimile machine for the responsible organization or individual.
     *
     * @return telephone numbers of a facsimile machine for the responsible organization or individual.
     *
     * @deprecated As of ISO 19115:2014, replaced by a {@linkplain #getNumber() number}
     *             with {@link TelephoneType#FACSIMILE}.
     */
    @Deprecated
    @UML(identifier="facsimile", obligation=OPTIONAL, specification=ISO_19115, version=2003)
    default Collection<String> getFacsimiles() {
        if (TelephoneType.FACSIMILE.equals(getNumberType())) {
            String number = getNumber();
            if (number != null) {
                return Collections.singleton(number);
            }
        }
        return Collections.emptySet();          // Use Set instead of List for hash-safe final classes.
    }
}
