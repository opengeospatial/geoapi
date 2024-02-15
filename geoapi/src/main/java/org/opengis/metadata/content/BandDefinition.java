/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2009-2011 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.content;

import java.util.ArrayList;
import java.util.List;

import org.opengis.annotation.UML;
import org.opengis.util.CodeList;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Designation of criterion for defining maximum and minimum wavelengths for a spectral band.
 *
 * @author  Cédric Briançon (Geomatys)
 * @version 3.0
 * @since   2.3
 */
@UML(identifier="MI_BandDefinition", specification=ISO_19115_2)
public final class BandDefinition extends CodeList<BandDefinition> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -6673852201803408346L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<BandDefinition> VALUES = new ArrayList<BandDefinition>(5);

    /**
     * Width of a distribution equal to the distance between the outer two points on the
     * distribution having power level half of that at the peak.
     *
     * @todo the same as the documentation for {@link #HALF_MAXIMUM}.
     */
    @UML(identifier="3dB", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final BandDefinition THREE_DB = new BandDefinition("THREE_DB");

    /**
     * Width of a distribution equal to the distance between the outer two points on the
     * distribution having power level half of that at the peak.
     *
     * @todo the same as the documentation for {@link #THREE_DB}.
     */
    @UML(identifier="halfMaximum", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final BandDefinition HALF_MAXIMUM = new BandDefinition("HALF_MAXIMUM");

    /**
     * Full spectral width of a spectral power density measured at 50% of its peak height.
     */
    @UML(identifier="fiftyPercent", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final BandDefinition FIFTY_PERCENT = new BandDefinition("FIFTY_PERCENT");

    /**
     * Width of a distribution equal to the distance between the outer two points on the
     * distribution having power level 1/e that of the peak.
     */
    @UML(identifier="oneOverE", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final BandDefinition ONE_OVER_E = new BandDefinition("ONE_OVER_E");

    /**
     * Width of a band with full sensitivity or absorption at every wavelength that detects
     * or absorbs the same amount of energy as the band described.
     */
    @UML(identifier="equivalentWidth", obligation=CONDITIONAL, specification=ISO_19115_2)
    public static final BandDefinition EQUIVALENT_WIDTH = new BandDefinition("EQUIVALENT_WIDTH");

    /**
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by another enum of this type.
     */
    private BandDefinition(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code BandDefinition}s.
     *
     * @return The list of codes declared in the current JVM.
     */
    public static BandDefinition[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(new BandDefinition[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind as this enum.
     */
    public BandDefinition[] family() {
        return values();
    }

    /**
     * Returns the band definition that matches the given string, or returns a
     * new one if none match it.
     *
     * @param code The name of the code to fetch or to create.
     * @return A code matching the given name.
     */
    public static BandDefinition valueOf(String code) {
        return valueOf(BandDefinition.class, code);
    }
}
