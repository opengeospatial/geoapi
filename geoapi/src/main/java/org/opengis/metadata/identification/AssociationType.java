/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2006-2024 Open Geospatial Consortium, Inc.
 *    http://www.geoapi.org
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
package org.opengis.metadata.identification;

import org.opengis.util.CodeList;
import org.opengis.annotation.UML;
import org.opengis.geoapi.internal.Vocabulary;

import static org.opengis.annotation.Obligation.CONDITIONAL;
import static org.opengis.annotation.Specification.ISO_19115;


/**
 * Justification for the correlation of two datasets.
 *
 * @author  Ely Conn (Leica Geosystems Geospatial Imaging, LLC)
 * @author  Rémi Maréchal (Geomatys)
 * @version 3.1
 * @since   2.1
 */
@Vocabulary(capacity=10)
@UML(identifier="DS_AssociationTypeCode", specification=ISO_19115)
public final class AssociationType extends CodeList<AssociationType> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = 6031427859661710114L;

    /**
     * Reference from one dataset to another.
     */
    @UML(identifier="crossReference", obligation=CONDITIONAL, specification=ISO_19115)
    public static final AssociationType CROSS_REFERENCE = new AssociationType("CROSS_REFERENCE");

    /**
     * Reference to a master dataset of which this one is a part.
     *
     * @since 3.1
     */
    @UML(identifier="largerWorkCitation", obligation=CONDITIONAL, specification=ISO_19115)
    public static final AssociationType LARGER_WORK_CITATION = new AssociationType("LARGER_WORK_CITATION");

    /**
     * @deprecated Renamed <code>LARGER_WOR<b><u>K</u></b>_CITATION</code>.
     */
    @Deprecated(since="3.1")
    public static final AssociationType LARGER_WORD_CITATION = LARGER_WORK_CITATION;

    /**
     * Part of same structured set of data held in a computer.
     */
    @UML(identifier="partOfSeamlessDatabase", obligation=CONDITIONAL, specification=ISO_19115)
    public static final AssociationType PART_OF_SEAMLESS_DATABASE = new AssociationType("PART_OF_SEAMLESS_DATABASE");

    /**
     * Mapping and charting information from which the dataset content originates.
     *
     * @deprecated Removed in ISO 19115:2014.
     */
    @Deprecated(since="3.1")
    @UML(identifier="source", obligation=CONDITIONAL, specification=ISO_19115, version=2003)
    public static final AssociationType SOURCE = new AssociationType("SOURCE");

    /**
     * Part of a set of imagery that when used together, provides three-dimensional images.
     */
    @UML(identifier="stereoMate", obligation=CONDITIONAL, specification=ISO_19115)
    public static final AssociationType STEREO_MATE = new AssociationType("STEREO_MATE");

    /**
     * Reference to resources that are parts of this resource.
     *
     * @since 3.1
     */
    @UML(identifier="isComposedOf", obligation=CONDITIONAL, specification=ISO_19115)
    public static final AssociationType IS_COMPOSED_OF = new AssociationType("IS_COMPOSED_OF");

    /**
     * Common title for a collection of resources.
     *
     * <div class="note"><b>Note:</b>
     * title identifies elements of a series collectively,
     * combined with information about what volumes are available at the source cite.</div>
     *
     * @since 3.1
     */
    @UML(identifier="collectiveTitle", obligation=CONDITIONAL, specification=ISO_19115)
    public static final AssociationType COLLECTIVE_TITLE = new AssociationType("COLLECTIVE_TITLE");

    /**
     * Associated through a common heritage such as produced to a common product specification.
     *
     * @since 3.1
     */
    @UML(identifier="series", obligation=CONDITIONAL, specification=ISO_19115)
    public static final AssociationType SERIES = new AssociationType("SERIES");

    /**
     * Associated through a dependency.
     *
     * @since 3.1
     */
    @UML(identifier="dependency", obligation=CONDITIONAL, specification=ISO_19115)
    public static final AssociationType DEPENDENCY = new AssociationType("DEPENDENCY");

    /**
     * Resource is a revision of associated resource.
     *
     * @since 3.1
     */
    @UML(identifier="revisionOf", obligation=CONDITIONAL, specification=ISO_19115)
    public static final AssociationType REVISION_OF = new AssociationType("REVISION_OF");

    /**
     * Constructs an element of the given name.
     *
     * @param name  the name of the new element. This name shall not be in use by another element of this type.
     */
    private AssociationType(final String name) {
        super(name);
    }

    /**
     * Returns the list of {@code AssociationType}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static AssociationType[] values() {
        return values(AssociationType.class);
    }

    /**
     * Returns the list of codes of the same kind as this code list element.
     * Invoking this method is equivalent to invoking {@link #values()}, except that
     * this method can be invoked on an instance of the parent {@code CodeList} class.
     *
     * @return all code {@linkplain #values() values} for this code list.
     */
    @Override
    public AssociationType[] family() {
        return values();
    }

    /**
     * Returns the association type that matches the given name, or returns a new value if there is no match.
     * This methods returns the first instance (in declaration order) for which the {@linkplain #name() name}
     * is {@linkplain String#equalsIgnoreCase(String) equals, ignoring case}, to the given name.
     * If no existing instance is found, then a new one is created for the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static AssociationType valueOf(String code) {
        return valueOf(AssociationType.class, code, AssociationType::new).get();
    }
}
