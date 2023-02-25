/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2008-2011 Open Geospatial Consortium, Inc.
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
package org.opengis.test.referencing;

import java.util.Set;
import java.util.List;

import org.opengis.parameter.*;
import org.opengis.test.ValidatorContainer;
import static org.opengis.test.Assert.*;


/**
 * Validates {@link ParameterValue} and related objects from the {@code org.opengis.parameter}
 * package. This class should not be used directly; use the {@link org.opengis.test.Validators}
 * convenience static methods instead.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.0
 * @since   2.2
 */
public class ParameterValidator extends ReferencingValidator {
    /**
     * Creates a new validator.
     *
     * @param container The container of this validator.
     */
    public ParameterValidator(final ValidatorContainer container) {
        super(container, "org.opengis.parameter");
    }

    /**
     * Dispatches the given object to one of {@code validate} methods.
     *
     * @param object The object to dispatch.
     */
    public void dispatch(final GeneralParameterDescriptor object) {
        if (object instanceof ParameterDescriptor<?>) {
            validate((ParameterDescriptor<?>) object);
        } else if (object instanceof ParameterDescriptorGroup) {
            validate((ParameterDescriptorGroup) object);
        } else {
            validateIdentifiedObject(object);
        }
    }

    /**
     * Dispatches the given object to one of {@code validate} methods.
     *
     * @param object The object to dispatch.
     */
    public void dispatch(final GeneralParameterValue object) {
        if (object instanceof ParameterValue<?>) {
            validate((ParameterValue<?>) object);
        } else if (object instanceof ParameterValueGroup) {
            validate((ParameterValueGroup) object);
        }
    }

    /**
     * Validates the given descriptor.
     *
     * @param <T> The class of parameter values.
     * @param object The object to validate, or {@code null}.
     */
    public <T> void validate(final ParameterDescriptor<T> object) {
        if (object == null) {
            return;
        }
        validateIdentifiedObject(object);
        final Class<T> valueClass = object.getValueClass();
        mandatory("ParameterDescriptor: getValueClass() can not return null.", valueClass);
        Set<T> validValues = object.getValidValues();
        if (validValues != null) {
            for (final T value : validValues) {
                if (value != null) {
                    assertInstanceOf("ParameterDescriptor: getValidValues() has unexpected element.", valueClass, value);
                }
            }
        }
        final Comparable<T> min = object.getMinimumValue();
        if (min != null) {
            assertInstanceOf("ParameterDescriptor: getMinimumValue() returns unexpected value.", valueClass, min);
        }
        final Comparable<T> max = object.getMaximumValue();
        if (max != null) {
            assertInstanceOf("ParameterDescriptor: getMaximumValue() returns unexpected value.", valueClass, max);
        }
        assertValidRange("ParameterDescriptor: inconsistent minimum and maximum values.", min, max);
        final T def = object.getDefaultValue();
        if (def != null) {
            assertInstanceOf("ParameterDescriptor: getDefaultValue() returns unexpected value.", valueClass, def);
            assertBetween("ParameterDescriptor: getDefaultValue() out of range.", min, max, def);
        }
        assertBetween("ParameterDescriptor: getMinimumOccurs() shall returns 0 or 1.", 0, 1, object.getMinimumOccurs());
        assertEquals("ParameterDescriptor: getMaximumOccurs() shall returns exactly 1.", 1, object.getMaximumOccurs());
    }

    /**
     * Validates the given descriptor.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final ParameterDescriptorGroup object) {
        if (object == null) {
            return;
        }
        validateIdentifiedObject(object);
        final List<GeneralParameterDescriptor> descriptors = object.descriptors();
        mandatory("ParameterDescriptorGroup: descriptors() should not return null.", descriptors);
        if (descriptors != null) {
            for (final GeneralParameterDescriptor descriptor : descriptors) {
                assertNotNull("ParameterDescriptorGroup: descriptors() can not contain null element.", descriptor);
                dispatch(descriptor);
                final GeneralParameterDescriptor byName = object.descriptor(descriptor.getName().getCode());
                mandatory("ParameterDescriptorGroup: descriptor(String) should returns a value.", byName);
                if (byName != null) {
                    assertSame("ParameterDescriptorGroup: descriptor(String) inconsistent with descriptors().",
                            descriptor, byName);
                }
            }
        }
        final int minOccurs = object.getMinimumOccurs();
        assertPositive("ParameterDescriptor: getMinimumOccurs() can not be negative.", minOccurs);
        assertValidRange("ParameterDescriptor: getMaximumOccurs() gives inconsistent range.",
                minOccurs, object.getMaximumOccurs());
    }

    /**
     * Validates the given parameter value.
     *
     * @param <T> The class of parameter values.
     * @param object The object to validate, or {@code null}.
     */
    public <T> void validate(final ParameterValue<T> object) {
        if (object == null) {
            return;
        }
        final ParameterDescriptor<T> descriptor = object.getDescriptor();
        mandatory("ParameterValue: must have a descriptor.", descriptor);
        validate(descriptor);
        final T value = object.getValue();
        if (value != null) {
            if (descriptor != null) {
                final Class<T> valueClass = descriptor.getValueClass();
                assertInstanceOf("ParameterValue: getValue() returns unexpected value.", valueClass, value);
                final Set<T> validValues = descriptor.getValidValues();
                if (validValues != null) {
                    assertContains("ParameterValue: getValue() not a member of getValidValues() set.",
                            validValues, value);
                }
                assertBetween("ParameterValue: getValue() is out of bounds.",
                        descriptor.getMinimumValue(), descriptor.getMaximumValue(), value);
            }
        }
    }

    /**
     * Validates the given coordinate system.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final ParameterValueGroup object) {
        if (object == null) {
            return;
        }
        final ParameterDescriptorGroup descriptors = object.getDescriptor();
        mandatory("ParameterValueGroup: must have a descriptor.", descriptors);
        validate(descriptors);
        final List<GeneralParameterValue> values = object.values();
        mandatory("ParameterValueGroup: values() should not return null.", values);
        if (values == null) {
            return;
        }
        for (final GeneralParameterValue value : values) {
            assertNotNull("ParameterValueGroup: values() can not contain null element.", value);
            dispatch(value);
            final GeneralParameterDescriptor descriptor = value.getDescriptor();
            mandatory("GeneralParameterValue: expected a descriptor.", descriptor);
            if (descriptor == null) {
                continue;
            }
            final String name = descriptor.getName().getCode();
            mandatory("GeneralParameterDescriptor: expected a name.", name);
            if (name == null) {
                continue;
            }
            if (descriptors != null) {
                final GeneralParameterDescriptor byName = descriptors.descriptor(name);
                mandatory("ParameterDescriptorGroup: should never return null.", byName);
                if (byName != null) {
                    assertSame("ParameterValueGroup: descriptor(String) inconsistent" +
                            " with value.getDescriptor().", descriptor, byName);
                }
            }
            if (value instanceof ParameterValue<?>) {
                final ParameterValue<?> byName = object.parameter(name);
                mandatory("ParameterValueGroup: parameter(String) should returns a value.", byName);
                if (byName != null) {
                    assertSame("ParameterValueGroup: value(String) inconsistent with values().", value, byName);
                }
            }
        }
    }
}
