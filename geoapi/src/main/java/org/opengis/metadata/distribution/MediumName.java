/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2023 Open Geospatial Consortium, Inc.
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
 * Name of the medium.
 *
 * @deprecated This code list is not anymore part of ISO 19115:2014, since {@code MediumName} has been
 * replaced by {@link org.opengis.metadata.citation.Citation}. This change may be applied in GeoAPI 4.0.
 * See <a href="https://github.com/opengeospatial/geoapi/issues/14">issue #14</a>.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   2.0
 */
@Deprecated(since="3.1")
@Vocabulary(capacity=18)
@UML(identifier="MD_MediumNameCode", specification=ISO_19115, version=2003)
public final class MediumName extends CodeList<MediumName> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = 2634504971646621701L;

    /**
     * Read-only optical disk.
     */
    @UML(identifier="cdRom", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MediumName CD_ROM = new MediumName("CD_ROM");

    /**
     * Digital versatile disk.
     */
    @UML(identifier="dvd", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MediumName DVD = new MediumName("DVD");

    /**
     * Digital versatile disk digital versatile disk, read only.
     */
    @UML(identifier="dvdRom", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MediumName DVD_ROM = new MediumName("DVD_ROM");

    /**
     * 3&frac12; inch magnetic disk.
     */
    @UML(identifier="3halfInchFloppy", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MediumName FLOPPY_3_HALF_INCH = new MediumName("FLOPPY_3_HALF_INCH");

    /**
     * 5&frac14; inch magnetic disk.
     */
    @UML(identifier="5quarterInchFloppy", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MediumName FLOPPY_5_QUARTER_INCH = new MediumName("FLOPPY_5_QUARTER_INCH");

    /**
     * 7 track magnetic tape.
     */
    @UML(identifier="7trackTape", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MediumName TAPE_7_TRACK = new MediumName("TAPE_7_TRACK");

    /**
     * 9 track magnetic tape.
     */
    @UML(identifier="9trackTape", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MediumName TAPE_9_TRACK = new MediumName("TAPE_9_TRACK");

    /**
     * 3480 cartridge tape drive.
     */
    @UML(identifier="3480Cartridge", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MediumName CARTRIDGE_3480 = new MediumName("CARTRIDGE_3480");

    /**
     * 3490 cartridge tape drive.
     */
    @UML(identifier="3490Cartridge", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MediumName CARTRIDGE_3490 = new MediumName("CARTRIDGE_3490");

    /**
     * 3580 cartridge tape drive.
     */
    @UML(identifier="3580Cartridge", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MediumName CARTRIDGE_3580 = new MediumName("CARTRIDGE_3580");

    /**
     * 4 millimetre magnetic tape.
     */
    @UML(identifier="4mmCartridgeTape", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MediumName CARTRIDGE_TAPE_4mm = new MediumName("CARTRIDGE_TAPE_4mm");

    /**
     * 8 millimetre magnetic tape.
     */
    @UML(identifier="8mmCartridgeTape", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MediumName CARTRIDGE_TAPE_8mm = new MediumName("CARTRIDGE_TAPE_8mm");

    /**
     * &frac14; inch magnetic tape.
     */
    @UML(identifier="1quarterInchCartridgeTape", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MediumName CARTRIDGE_TAPE_1_QUARTER_INCH = new MediumName("CARTRIDGE_TAPE_1_QUARTER_INCH");

    /**
     * Half inch cartridge streaming tape drive.
     */
    @UML(identifier="digitalLinearTape", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MediumName DIGITAL_LINEAR_TAPE = new MediumName("DIGITAL_LINEAR_TAPE");

    /**
     * Direct computer linkage.
     */
    @UML(identifier="onLine", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MediumName ON_LINE = new MediumName("ON_LINE");

    /**
     * Linkage through a satellite communication system.
     */
    @UML(identifier="satellite", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MediumName SATELLITE = new MediumName("SATELLITE");

    /**
     * Communication through a telephone network.
     */
    @UML(identifier="telephoneLink", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MediumName TELEPHONE_LINK = new MediumName("TELEPHONE_LINK");

    /**
     * Pamphlet or leaflet giving descriptive information.
     */
    @UML(identifier="hardcopy", obligation=CONDITIONAL, specification=ISO_19115)
    public static final MediumName HARDCOPY = new MediumName("HARDCOPY");

    /**
     * Constructs an element of the given name.
     *
     * @param name  the name of the new element. This name shall not be in use by another element of this type.
     */
    private MediumName(final String name) {
        super(name);
    }

    /**
     * Returns the list of {@code MediumName}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static MediumName[] values() {
        return values(MediumName.class);
    }

    /**
     * Returns the list of codes of the same kind than this code list element.
     * Invoking this method is equivalent to invoking {@link #values()}, except that
     * this method can be invoked on an instance of the parent {@code CodeList} class.
     *
     * @return all code {@linkplain #values() values} for this code list.
     */
    @Override
    public MediumName[] family() {
        return values();
    }

    /**
     * Returns the medium name that matches the given string, or returns a new one if none match it.
     * This methods returns the first instance (in declaration order) for which the {@linkplain #name() name}
     * is {@linkplain String#equalsIgnoreCase(String) equals, ignoring case}, to the given name.
     * If no existing instance is found, then a new one is created for the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static MediumName valueOf(String code) {
        return valueOf(MediumName.class, code, MediumName::new).get();
    }
}
