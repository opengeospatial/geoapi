/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2003-2024 Open Geospatial Consortium, Inc.
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
package org.opengis.referencing;

import java.util.List;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.InvocationHandler;
import org.opengis.referencing.cs.AxisDirection;
import org.opengis.referencing.cs.CartesianCS;
import org.opengis.referencing.cs.CoordinateSystem;
import org.opengis.referencing.cs.CoordinateSystemAxis;
import org.opengis.referencing.cs.EllipsoidalCS;
import org.opengis.referencing.cs.ParametricCS;
import org.opengis.referencing.cs.TimeCS;
import org.opengis.referencing.cs.VerticalCS;
import org.opengis.referencing.crs.CompoundCRS;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.crs.EngineeringCRS;
import org.opengis.referencing.crs.GeodeticCRS;
import org.opengis.referencing.crs.ParametricCRS;
import org.opengis.referencing.crs.ProjectedCRS;
import org.opengis.referencing.crs.SingleCRS;
import org.opengis.referencing.crs.TemporalCRS;
import org.opengis.referencing.crs.VerticalCRS;
import org.junit.jupiter.api.Test;

import static org.opengis.geoapi.CodeListTest.IGNORABLE_NAME_SUFFIX;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Tests {@link ReferenceSystemType}.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public final class ReferenceSystemTypeTest implements InvocationHandler {
    /**
     * Creates a new test case.
     */
    public ReferenceSystemTypeTest() {
    }

    /**
     * Compares the names of each enumeration values against its UML identifier.
     */
    @Test
    public void verifyNames() {
        for (ReferenceSystemType type : ReferenceSystemType.values()) {
            type.identifier().ifPresent((expected) -> {
                final String name = type.name();
                assertTrue(expected.equalsIgnoreCase(name.replace("_", "")), name);
            });
        }
    }

    /**
     * Tests {@link ReferenceSystemType#compound(String, ReferenceSystemType)}.
     */
    @Test
    public void testCompound() {
        final ReferenceSystemType base = ReferenceSystemType.ENGINEERING.compound(
                "COMPOUND_ENGINEERING_TEMPORAL", ReferenceSystemType.TEMPORAL);
        assertSame(ReferenceSystemType.COMPOUND_ENGINEERING_TEMPORAL, base);

        final String name = "Compound" + IGNORABLE_NAME_SUFFIX;
        final ReferenceSystemType compound = base.compound(name, ReferenceSystemType.PARAMETRIC);
        assertSame(compound, base.compound(name, ReferenceSystemType.PARAMETRIC));
        assertEquals(name, compound.name());

        RuntimeException e;
        e = assertThrows(IllegalStateException.class, () -> base.compound("Dummy", ReferenceSystemType.ENGINEERING_DESIGN));
        assertEquals("ENGINEERING_DESIGN", e.getMessage());

        e = assertThrows(IllegalArgumentException.class, () -> base.compound(name, ReferenceSystemType.VERTICAL));
        assertTrue(e.getMessage().contains(name));
    }

    /**
     * Prefix in the name of types to handle in a special way.
     */
    private static final String COMPOUND_PREFIX = "COMPOUND_",
                                GEODETIC_PREFIX = "GEODETIC_";

    /**
     * Tests {@link ReferenceSystemType#isInstance(ReferenceSystem)}.
     */
    @Test
    public void testIsInstance() {
        final ReferenceSystemType[] values = ReferenceSystemType.values();
        final var filteredValues = new LinkedHashSet<ReferenceSystemType>(Arrays.asList(values));
        filteredValues.removeIf((type) -> type.identifier().isEmpty());
        assertTrue(filteredValues.remove(ReferenceSystemType.ENGINEERING_IMAGE));
        assertTrue(filteredValues.remove(ReferenceSystemType.ENGINEERING_DESIGN));
        assertTrue(filteredValues.remove(ReferenceSystemType.GEOGRAPHIC_IDENTIFIER));
        assertTrue(filteredValues.remove(ReferenceSystemType.LINEAR));
        assertFalse(filteredValues.isEmpty());
        for (final ReferenceSystemType type : filteredValues) {
            final ReferenceSystem mock;
            String name = type.name();
            if (name.startsWith(COMPOUND_PREFIX)) {
                name = name.substring(COMPOUND_PREFIX.length());
                final String[] componentTypes = name.split("_");
                components = new SingleCRS[componentTypes.length];
                Arrays.setAll(components, (i) -> createComponentMock(componentTypes[i], i == 0));
                mock = createMock(CompoundCRS.class, CoordinateSystem.class);
            } else {
                if (name.startsWith(GEODETIC_PREFIX)) {
                    name = name.substring(GEODETIC_PREFIX.length());
                }
                mock = createComponentMock(name, true);
            }
            for (ReferenceSystemType candidate : values) {
                assertEquals(candidate == type, candidate.isInstance(mock), type.name());
            }
        }
    }

    /**
     * Creates a mock interface for a component.
     *
     * @param  name     the {@link ReferenceSystemType} name.
     * @param  isFirst  whether the component to create is the first one.
     */
    private ReferenceSystem createComponentMock(String name, boolean isFirst) {
        Class<? extends SingleCRS> crs;
        Class<? extends CoordinateSystem> cs;
        int dim;
        switch (name) {
            case "ENGINEERING":  crs = EngineeringCRS.class; cs = CartesianCS.class;   dim = 2; break;
            case "GEOCENTRIC":   crs = GeodeticCRS.class;    cs = CartesianCS.class;   dim = 3; break;
            case "GEOGRAPHIC2D": crs = GeodeticCRS.class;    cs = EllipsoidalCS.class; dim = 2; break;
            case "GEOGRAPHIC3D": crs = GeodeticCRS.class;    cs = EllipsoidalCS.class; dim = 3; break;
            case "PARAMETRIC":   crs = ParametricCRS.class;  cs = ParametricCS.class;  dim = 1; break;
            case "PROJECTED":
            case "PROJECTED2D":  crs = ProjectedCRS.class;   cs = CartesianCS.class;   dim = 2; break;
            case "TEMPORAL":     crs = TemporalCRS.class;    cs = TimeCS.class;        dim = 1; break;
            case "VERTICAL":     crs = VerticalCRS.class;    cs = VerticalCS.class;    dim = 1; break;
            default: throw new AssertionError("Unexpected component: " + name);
        }
        if (isFirst) dimension = dim;
        return createMock(crs, cs);
    }

    /**
     * Creates a mock implementing the two given interfaces.
     *
     * @param  crs  interface of the coordinate reference system.
     * @param  cs   interface of the coordinate system.
     * @return a mock implementing the two given interface.
     */
    private ReferenceSystem createMock(Class<? extends CoordinateReferenceSystem> crs, Class<? extends CoordinateSystem> cs) {
        return (ReferenceSystem) Proxy.newProxyInstance(
                ReferenceSystem.class.getClassLoader(),
                new Class<?>[] {crs, cs, CoordinateSystemAxis.class},
                this);
    }

    /**
     * The number of dimension of the mock coordinate system.
     * This is relevant only to the first {@link SingleCRS}.
     */
    private int dimension;

    /**
     * The components of a {@link CompoundCRS}.
     */
    private SingleCRS[] components;

    /**
     * Provides mock data for a GeoAPI method invoked by the test.
     *
     * @param  proxy   the mock implementing the interfaces.
     * @param  method  the GeoAPI method invoked by the test.
     * @param  args    arguments provided in the method call.
     * @return the value to return.
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        switch (method.getName()) {
            case "getSingleComponents": return List.of(components);
            case "getCoordinateSystem": return proxy;
            case "getDimension":        return dimension;
            case "getAxis":             return proxy;
            case "getDirection":        return AxisDirection.UNSPECIFIED;
            default: throw new UnsupportedOperationException("Unexpected method call: " + method);
        }
    }
}
