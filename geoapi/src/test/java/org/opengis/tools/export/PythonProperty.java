/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2019-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.tools.export;

import java.util.Map;
import java.lang.reflect.Method;
import org.opengis.annotation.UML;
import org.opengis.annotation.Obligation;
import org.opengis.geoapi.schema.SchemaInformation;
import org.opengis.util.GenericName;


/**
 * Information about a Python property to declare in a class.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @since   4.0
 * @version 4.0
 */
final class PythonProperty implements Comparable<PythonProperty> {
    /**
     * Comment added at the end of line where an implementation needs to be provided.
     */
    static final String IMPLEMENTATION_PROVIDED = "# TODO: put an implementation here.";

    /**
     * The OGC/ISO name (cannot be null).
     */
    private final String name;

    /**
     * The Java type, or null if none.
     */
    final Class<?> javaType;

    /**
     * The python type, or null if none.
     */
    private final String pythonType;

    /**
     * Module from which to import type.
     */
    final String importFrom;

    /**
     * Whether the property is mandatory.
     */
    private final boolean mandatory;

    /**
     * {@code true} if this property overrides a property in a parent class
     * with a method containing a default implementation.
     */
    private boolean overrideWithDefault;

    /**
     * Declaration order, to be set later. We will sort properties
     * in the order of their declaration in the XSD file.
     */
    int position;

    /**
     * Creates a new set of information about a Python property.
     *
     * @param  property    the Java method defining the property.
     * @param  def         the {@link UML} annotation on the Java method.
     * @param  name        the OGC/ISO name (cannot be null).
     * @param  javaType    the Java type, or null if none.
     * @param  pythonType  the python type, or null if none.
     * @param  importFrom  module from which to import type.
     */
    PythonProperty(final Method property, final UML def, final String name,
            final Class<?> javaType, final String pythonType, final String importFrom)
    {
        this.name       = rename(property.getDeclaringClass(), name);
        this.javaType   = javaType;
        this.pythonType = pythonType;
        this.importFrom = importFrom;
        this.mandatory  = def.obligation() == Obligation.MANDATORY;
        this.position   = Integer.MAX_VALUE / 2;
        /*
         * Verify this this property override a property of the same name and parameters in a parent class.
         * If this is the case, we will assume that the default implementation (if any) is doing something
         * different than just returning "None".
         */
        if (property.isDefault()) {
            for (final Class<?> parent : property.getDeclaringClass().getInterfaces()) try {
                parent.getMethod(property.getName(), property.getParameterTypes());
                overrideWithDefault = true;
                return;
            } catch (NoSuchMethodException e) {
                // Ignore.
            }
        }
    }

    /**
     * Rename properties that can be mapped to a standard Python function name.
     *
     * @param  declaringClass  the Java class declaring the property.
     * @param  name            the OGC/ISO name of the property.
     * @return the name to use in Python code (without conversion from camel case to snake case).
     */
    private static String rename(final Class<?> declaringClass, final String name) {
        if (GenericName.class.isAssignableFrom(declaringClass)) {
            if (name.equals("aName") || name.equals("scopedName")) {
                return "__str__";
            }
        }
        return name;
    }

    /**
     * Writes the property, including its type annotation if possible and its documentation if available.
     *
     * @param  replacements   map of properties to rename. For example, when writing Python properties,
     *                        we need to replace the {@code "pass"} property by something else because
     *                        {@code "pass"} is a Python keyword.
     * @param  definition     information about types and properties declared in OGC/ISO schema.
     *                        Map keys contain the property names. This method use those information
     *                        only for providing documentation, if available.
     * @param  appendTo       where to write the Python property.
     * @param  lineSeparator  Windows or Unix style of line separator.
     */
    final void write(final Map<String,String> replacements,
            final Map<String, SchemaInformation.Element> definition,
            final StringBuilder appendTo, final String lineSeparator)
    {
        /*
         * Example:
         *
         *     @property
         *     @abstractmethod
         *     def contact(self) -> Sequence[Responsibility]:
         *         """Party responsible for the metadata information."""
         *         pass
         */
        String propertyName = null;
        if (replacements != null) {
            propertyName = replacements.get(name);
        }
        if (propertyName == null) {
            propertyName = CharSequences.camelCaseToSnake(name);
        }
        JavaToPython.indent(appendTo, 1).append("@property").append(lineSeparator);
        String implementation = "pass";
        if (mandatory & !overrideWithDefault) {
            JavaToPython.indent(appendTo, 1).append("@abstractmethod").append(lineSeparator);
        } else if (!Void.TYPE.equals(javaType)) {
            implementation = "return None";
        }
        JavaToPython.indent(appendTo, 1).append("def ").append(propertyName).append("(self)");
        if (pythonType != null) {
            appendTo.append(" -> ").append(pythonType);
        }
        appendTo.append(':').append(lineSeparator);
        if (definition != null) {
            JavaToPython.appendDocumentation(definition.get(name), 2, appendTo, lineSeparator);
        }
        JavaToPython.indent(appendTo, 2).append(implementation);
        if (overrideWithDefault) {
            appendTo.append("    ").append(IMPLEMENTATION_PROVIDED);
        }
        appendTo.append(lineSeparator);
    }

    /**
     * For sorting properties in declaration order.
     */
    @Override public int compareTo(final PythonProperty o) {
        return position - o.position;
    }

    /**
     * Returns a string representation for error reporting only.
     */
    @Override public String toString() {
        return name;
    }
}
