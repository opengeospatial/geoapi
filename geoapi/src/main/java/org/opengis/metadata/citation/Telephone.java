/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
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
