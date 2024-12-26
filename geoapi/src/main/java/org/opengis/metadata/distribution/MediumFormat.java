/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2024 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.distribution;

import org.opengis.util.CodeList;
import org.opengis.annotation.UML;
import org.opengis.geoapi.internal.Vocabulary;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Method used to write to the medium.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Rémi Maréchal (Geomatys)
 * @version 3.1
 * @since   2.0
 */
@Vocabulary(capacity=7)
@UML(identifier="MD_MediumFormatCode", specification=ISO_19115)
public final class MediumFormat extends CodeList<MediumFormat> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = 413822250362716958L;

    /**
     * CoPy In / Out (UNIX file format and command).
     */
    @UML(identifier="cpio", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MediumFormat CPIO = new MediumFormat("CPIO");

    /**
     * Tap ARchive.
     */
    @UML(identifier="tar", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MediumFormat TAR = new MediumFormat("TAR");

    /**
     * High Sierra file system.
     */
    @UML(identifier="highSierra", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MediumFormat HIGH_SIERRA = new MediumFormat("HIGH_SIERRA");

    /**
     * Information processing - volume and file structure of CD-ROM.
     */
    @UML(identifier="iso9660", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MediumFormat ISO_9660 = new MediumFormat("ISO_9660");

    /**
     * Rock Ridge interchange protocol (UNIX).
     */
    @UML(identifier="iso9660RockRidge", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MediumFormat ISO_9660_ROCK_RIDGE = new MediumFormat("ISO_9660_ROCK_RIDGE");

    /**
     * Hierarchical File System (Macintosh).
     */
    @UML(identifier="iso9660AppleHFS", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MediumFormat ISO_9660_APPLE_HFS = new MediumFormat("ISO_9660_APPLE_HFS");

    /**
     * Universal Disk Format.
     *
     * @since 3.1
     */
    @UML(identifier="udf", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MediumFormat UDF = new MediumFormat("UDF");

    /**
     * Constructs an element of the given name.
     *
     * @param name  the name of the new element. This name shall not be in use by another element of this type.
     */
    private MediumFormat(final String name) {
        super(name);
    }

    /**
     * Returns the list of {@code MediumFormat}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static MediumFormat[] values() {
        return values(MediumFormat.class);
    }

    /**
     * Returns the list of codes of the same kind as this code list element.
     * Invoking this method is equivalent to invoking {@link #values()}, except that
     * this method can be invoked on an instance of the parent {@code CodeList} class.
     *
     * @return all code {@linkplain #values() values} for this code list.
     */
    @Override
    public MediumFormat[] family() {
        return values();
    }

    /**
     * Returns the medium format that matches the given name, or returns a new value if there is no match.
     * This methods returns the first instance (in declaration order) for which the {@linkplain #name() name}
     * is {@linkplain String#equalsIgnoreCase(String) equals, ignoring case}, to the given name.
     * If no existing instance is found, then a new one is created for the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static MediumFormat valueOf(String code) {
        return valueOf(MediumFormat.class, code, MediumFormat::new).get();
    }
}
