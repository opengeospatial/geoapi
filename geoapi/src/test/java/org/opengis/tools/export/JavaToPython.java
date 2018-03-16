/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2018 Open Geospatial Consortium, Inc.
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
package org.opengis.tools.export;

import java.util.Map;
import java.util.HashMap;
import java.util.Date;
import java.util.GregorianCalendar;
import java.math.BigInteger;
import java.lang.reflect.Method;
import org.opengis.Content;
import org.opengis.annotation.UML;
import org.opengis.util.InternationalString;


/**
 * Generates Python abstract classes from Java interfaces.
 * This should be used only as a starting point before review by Python developers.
 * This class uses only the {@link UML} annotations, not the Java type or method names.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @since   3.1
 * @version 3.1
 */
public final strictfp class JavaToPython {
    /**
     * Suffix of Python files.
     */
    private static final String FILE_SUFFIX = ".py";

    /**
     * The current year, for formatting the Copyright header.
     */
    private final int currentYear;

    /**
     * Content of python files to write. Keys are ISO prefixes (e.g. {@code "CI"} for "citation") and values are file contents.
     */
    private final Map<String,StringBuilder> contents;

    /**
     * Python primitive types for given Java types.
     */
    private final Map<Class<?>, String> primitiveTypes;

    /**
     * Creates a new Python class writer.
     */
    public JavaToPython() {
        currentYear = new GregorianCalendar().get(GregorianCalendar.YEAR);
        contents = new HashMap<>();
        primitiveTypes = new HashMap<>(20);
        primitiveTypes.put(CharSequence        .class, "str");
        primitiveTypes.put(String              .class, "str");
        primitiveTypes.put(InternationalString .class, "str");
        primitiveTypes.put(Byte                .class, "int");
        primitiveTypes.put(Byte                .TYPE,  "int");
        primitiveTypes.put(Short               .class, "int");
        primitiveTypes.put(Short               .TYPE,  "int");
        primitiveTypes.put(Integer             .class, "int");
        primitiveTypes.put(Integer             .TYPE,  "int");
        primitiveTypes.put(Long                .class, "int");
        primitiveTypes.put(Long                .TYPE,  "int");
        primitiveTypes.put(BigInteger          .class, "int");
        primitiveTypes.put(Float               .class, "float");
        primitiveTypes.put(Float               .TYPE,  "float");
        primitiveTypes.put(Double              .class, "float");
        primitiveTypes.put(Double              .TYPE,  "float");
        primitiveTypes.put(Date                .class, "datetime");
    }

    /**
     * Returns the Python name of the given Java type, or {@code null} if unknown.
     */
    private String nameOf(final Class<?> type) {
        String id = primitiveTypes.get(type);
        if (id == null) {
            final UML uml = type.getAnnotation(UML.class);
            if (uml != null) {
                id = uml.identifier();
                id = id.substring(id.indexOf('_') + 1);
                if (id.isEmpty()) id = null;
            }
        }
        return id;
    }

    /**
     * Creates Python files for all types in the given category.
     * The contents are stored in the {@link #contents} map.
     */
    private void createContent(final Content category) {
        final String lineSeparator = System.lineSeparator();
        for (final Class<?> type : category.types()) {
            if (type.isAnnotationPresent(Deprecated.class)) {
                continue;
            }
            final UML uml = type.getAnnotation(UML.class);
            if (uml == null) {
                continue;
            }
            String identifier = uml.identifier();
            final int splitAt = identifier.indexOf('_');
            final String prefix;
            if (splitAt >= 0) {
                prefix = identifier.substring(0, splitAt);
            } else {
                switch (type.getPackage().getName()) {
                    case "org.opengis.util":    prefix = "util"; break;
                    case "org.opengis.feature": prefix = "FT"; break;
                    default: {
                        switch (identifier) {
                            case "DirectPosition": prefix = "GM"; break;
                            default: throw new CanNotExportException("Can not choose a module for " + identifier);
                        }
                    }
                }
            }
            identifier = identifier.substring(splitAt + 1);
            if (identifier.isEmpty()) {
                continue;
            }
            StringBuilder content = contents.get(prefix);
            if (content == null) {
                content = new StringBuilder(500);
                content.append('#').append(lineSeparator)
                       .append("#    GeoAPI - Programming interfaces for OGC/ISO standards").append(lineSeparator)
                       .append("#    http://www.geoapi.org").append(lineSeparator)
                       .append('#').append(lineSeparator)
                       .append("#    Copyright (C) ").append(currentYear).append(" Open Geospatial Consortium, Inc.").append(lineSeparator)
                       .append("#    All Rights Reserved. http://www.opengeospatial.org/ogc/legal").append(lineSeparator)
                       .append('#').append(lineSeparator)
                       .append(lineSeparator)
                       .append("from abc import ABC, abstractproperty").append(lineSeparator);
                contents.put(prefix, content);
            }
            content.append(lineSeparator);
            switch (category) {
                case INTERFACES: {
                    content.append("class ").append(identifier).append('(');
                    String parent = "ABC";
                    for (final Class<?> p : type.getInterfaces()) {
                        final String pi = nameOf(p);
                        if (pi != null) {
                            parent = pi;
                            break;
                        }
                    }
                    content.append(parent).append("):").append(lineSeparator);
                    // TODO: insert class documentation here.
                    for (final Method property : type.getDeclaredMethods()) {
                        if (property.isAnnotationPresent(Deprecated.class)) {
                            continue;
                        }
                        final UML def = property.getAnnotation(UML.class);
                        if (def != null) {
                            final String pt = nameOf(Content.typeOf(property));
                            if (property.getParameterTypes().length == 0) {
                                content.append(lineSeparator)
                                       .append("    @abstractproperty").append(lineSeparator)
                                       .append("    def ").append(def.identifier()).append("(self)");
                                if (pt != null) {
                                    content.append(" -> ").append(pt);
                                }
                                content.append(':').append(lineSeparator);
                                // TODO: insert property documentation here.
                                content.append("        pass").append(lineSeparator);
                            }
                        }
                    }
                    break;
                }
            }
        }
    }

    /**
     * For testing purpose only.
     */
    public static void main(String[] args) {
        JavaToPython generator = new JavaToPython();
        generator.createContent(Content.INTERFACES);
        System.out.println(generator.contents.get("CI"));
    }
}
