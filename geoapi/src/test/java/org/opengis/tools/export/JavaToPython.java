/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2018-2023 Open Geospatial Consortium, Inc.
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

import java.util.Arrays;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.math.BigInteger;
import java.lang.reflect.Method;
import java.io.Writer;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.xml.namespace.QName;
import javax.xml.parsers.ParserConfigurationException;
import org.opengis.geoapi.Content;
import org.opengis.geoapi.SourceGenerator;
import org.opengis.annotation.UML;
import org.opengis.util.ControlledVocabulary;
import org.opengis.util.InternationalString;
import org.opengis.geoapi.NameSpaces;
import org.opengis.geoapi.Departures;
import org.opengis.geoapi.DocumentationStyle;
import org.opengis.geoapi.SchemaInformation;
import org.opengis.geoapi.SchemaException;
import org.xml.sax.SAXException;
import org.junit.Test;

import static java.util.Collections.singletonMap;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Generates or verifies Python abstract classes from Java interfaces.
 * If Python files exist in the {@code geoapi/src/main/python/opengis/metadata} directory,
 * they will be compared with expected content. If those files do not exist, then they will
 * be generated from {@link UML} annotations given by Java interfaces.
 * This should be used only as a starting point before review by Python developers.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @since   4.0
 * @version 4.0
 */
class JavaToPython extends SourceGenerator {
    /**
     * Suffix of Python files.
     */
    private static final String FILE_SUFFIX = ".py";

    /**
     * The character encoding to use for reading and writing Python files.
     */
    static final String ENCODING = "UTF-8";

    /**
     * Start of the copyright statement to put in headers, before the year.
     */
    static final String COPYRIGHT = "#    Copyright © ";

    /**
     * The current year, for formatting the Copyright header. This is used only for writing files.
     * When comparing existing files with expected content, the year will be ignored since it varies.
     */
    private final int currentYear;

    /**
     * The line separator to use for the Python files to create.
     */
    final String lineSeparator;

    /**
     * Content of python files to write. Keys are module paths
     * (e.g. {@code "metadata/citation"}) and values are file contents.
     */
    private final Map<String,StringBuilder> contents;

    /**
     * The Python modules to create for each package prefixes. The keys are the same than in the {@link #contents} map.
     * Values are Python filenames without {@value #FILE_SUFFIX} suffix.
     */
    private final NameSpaces namespaces;

    /**
     * The types for which we have written the declaration in Python. This map is initially empty and populated during
     * the {@link #createContent(Content)} process. This is used for detecting forward dependencies. Keys are the Java
     * interfaces and values are the Python modules where the interfaces have been written.
     */
    private final Map<Class<?>, String> declaredTypes;

    /**
     * Python primitive types for given Java types.
     */
    private final Map<Class<?>, String> primitiveTypes;

    /**
     * Python keywords associated to replacements.
     * Keys are enclosing interfaces and values are map of properties to rename in that particular interface.
     */
    private final Map<Class<?>, Map<String,String>> keywords;

    /**
     * Information read from the XML schema. This is used for determining property order.
     */
    private final SchemaInformation schema;

    /**
     * A temporary map used for sorting properties in declaration order.
     * Keys are the UML identifiers.
     */
    private final Map<String,PythonProperty> properties;

    /**
     * Creates a new Python classes writer or verifier. If the computer contains a local copy of ISO schemas,
     * then the {@code schemaRootDirectory} argument can be set to that directory for faster schema loadings.
     * If non-null, that directory should contain the same files than
     * <a href="https://schemas.isotc211.org/">https://schemas.isotc211.org/</a> (not necessarily with
     * all sub-directories). In particular, that directory should contain an {@code 19115} sub-directory.
     *
     * @param  schemaRootDirectory  path to local copy of ISO schemas, or {@code null} if none.
     * @throws ParserConfigurationException if the XML parser cannot be created.
     * @throws IOException     if an I/O error occurred while reading a file.
     * @throws SAXException    if a file cannot be parsed as a XML document.
     * @throws SchemaException if a XML document cannot be interpreted as an OGC/ISO schema.
     */
    JavaToPython(final Path schemaRootDirectory)
            throws ParserConfigurationException, IOException, SAXException, SchemaException
    {
        lineSeparator  = System.lineSeparator();
        currentYear    = new GregorianCalendar().get(GregorianCalendar.YEAR);
        contents       = new LinkedHashMap<>(40);
        properties     = new HashMap<>(30);
        declaredTypes  = new HashMap<>(400);
        primitiveTypes = new HashMap<>(25);
        primitiveTypes.put(Void                .TYPE,  "None");
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
        declaredTypes .put(Date                .class, "datetime");     // Module of datetime type.

        keywords = new HashMap<>(4);
        keywords.put(org.opengis.metadata.acquisition.Objective.class,     singletonMap("pass", "platformPass"));
        keywords.put(org.opengis.metadata.quality.ConformanceResult.class, singletonMap("pass", "isConform"));

        namespaces = new NameSpaces();
        namespaces.exclude("lan");          // Because it defines only "FreeText", which is not used here.
        namespaces.exclude("dqm");          // Because merged with "dqc".
        namespaces.exclude("IO", "CD", "CS", "RS", "SC", "PT", "CT", "CC", "TM", "GM", "DQ", "DQM", "GF");      // Not yet supported.
        schema = new SchemaInformation(schemaRootDirectory, new Departures(), DocumentationStyle.SENTENCE);
        schema.loadDefaultSchemas();
    }

    /**
     * Returns {@code true} if the given type is a member of an excluded namespaces.
     * The excluded namespaces are those for which {@link NameSpaces#exclude(String...)} has been invoked.
     *
     * @param  type  the type to test for exclusion.
     * @return whether the given type is a member of an excluded namespace.
     */
    private boolean isExcluded(final Class<?> type) {
        return namespaces.name(type, schema.getTypeDefinition(type)) == null;
    }

    /**
     * Returns the Python name of the given Java type, or {@code null} if unknown.
     * First, this method checks if the given type can be mapped to a Python primitive.
     * If not, then the UML identifier is returned with the prefix omitted.
     *
     * @param  type  the Java interface for which to get the Python name.
     * @return Python name of the given Java type, or {@code null}.
     */
    private String nameOfClass(final Class<?> type) {
        String name = primitiveTypes.get(type);
        if (name == null) {
            final UML uml = type.getAnnotation(UML.class);
            if (uml != null) {
                name = uml.identifier();
                name = name.substring(name.indexOf('_') + 1);
                if (name.isEmpty()) name = null;
            }
        }
        return name;
    }

    /**
     * Returns the Python name of the given Java property.
     *
     * @param  property  annotation on the Java getter method for which to get the Python name.
     * @return Python name of the given Java property.
     */
    private static String nameOfProperty(final UML property) {
        String name = property.identifier();
        final int s = name.indexOf('+');
        if (s >= 0) {
            /*
             * If a GeoAPI property is a combination of two ISO properties, take only the first property.
             * The main use case is "defaultLocale+otherLocale", in which case we take only "defaultLocale".
             * The second property, if desired, needs to be added manually in the Python file.
             */
            name = name.substring(0, s);
        }
        return name;
    }

    /**
     * Builds the list of properties found in the given Java interface. Only non-synthetic and non-deprecated properties
     * having a {@link UML} annotation are taken in account. The properties are sorted in the order declared in XSD file.
     *
     * @param  type        the Java interface for which to list properties.
     * @param  module      the module in which is defined the given type.
     * @param  definition  the value of {@code schema.getTypeDefinition(type)} (may be {@code null}).
     * @return the properties found in the specified interface.
     */
    private PythonProperty[] listProperties(final Class<?> type, final String module,
            final Map<String, SchemaInformation.Element> definition)
    {
        for (final Method property : type.getDeclaredMethods()) {
            if (!property.isSynthetic() && !property.isAnnotationPresent(Deprecated.class)) {
                final UML def = property.getAnnotation(UML.class);
                if (def != null) {
                    final String name = nameOfProperty(def);
                    if (name.indexOf('.') >= 0) {
                        continue;                                   // TODO: property taken from another OGC/ISO class
                    }
                    if (property.getParameterTypes().length != 0) {
                        continue;                                   // TODO: methods with parameters will need to be supported.
                    }
                    final Class<?> elementType = Content.typeOf(property);
                    String typeName = nameOfClass(elementType);
                    /*
                     * If the property type is a type not yet declared, we cannot reference that type directly.
                     * This situation happen with circular dependencies. For example Responsibility.extent is of
                     * type Extent. But the latter cannot be defined at the time Responsibility type is defined.
                     * Consequently, we have to put the Extent type between quotes, like `Extent`.
                     */
                    String importFrom = null;
                    if (typeName != null) {
                        String dependency = declaredTypes.get(elementType);
                        if (elementType.getName().startsWith("org.opengis.metadata.quality.")) {
                            if (type.getName().startsWith("org.opengis.metadata.spatial.")) {
                                /*
                                 * Do not allow representation.py to depends directly on quality.py because those
                                 * two Python files are incomplete when this circular dependency is established.
                                 */
                                dependency = null;
                            }
                        }
                        if (dependency == null) {
                            if (!primitiveTypes.containsKey(elementType)) {
                                if (isExcluded(elementType)) {
                                    typeName = null;
                                } else {
                                    typeName = '\'' + typeName + '\'';
                                }
                            }
                        } else if (!dependency.equals(module)) {
                            importFrom = dependency;
                        }
                    }
                    /*
                     * If the property accepts multi-occurrences, we have to declare it as a sequence.
                     * Note that it may be a sequence of type between quotes if the above check found
                     * that the element type has not yet been defined.
                     */
                    final Class<?> javaType = property.getReturnType();
                    if (Collection.class.isAssignableFrom(javaType)) {
                        String collection = "Sequence";
                        if (Set.class.isAssignableFrom(javaType)) {
//                          collection = "Set";                     // TODO: needs to manage imports
                        }
                        if (typeName != null) {
                            typeName = collection + '[' + typeName + ']';
                        } else {
                            // TODO
                        }
                    }
                    final PythonProperty p = new PythonProperty(property, def, name, elementType, typeName, importFrom);
                    if (properties.put(name, p) != null) switch (name) {
                        /*
                         * If the same property appears twice, this is theoretically an error.
                         * But we make an exception for properties that have been splitted in
                         * two Java methods because Java can return only one value.
                         */
                        case "cardinality":                         // [minOccurs … maxOccurs]
                        case "signature": {                         // [parameters : result]
                            properties.remove(name);                // TODO
                            break;
                        }
                        default: {
                            throw new CanNotExportException("The same " + p +
                                    " property is defined twice in " + type);
                        }
                    }
                }
            }
        }
        /*
         * At this point we got the list of properties to write in Python interfaces.
         * But Java methods are listed in no particular order. Before to write them,
         * we should sort them in the same order than in the XSD file.
         */
        if (definition != null) {
            int position = 0;
            for (final String name : definition.keySet()) {
                final PythonProperty p = properties.get(name);
                if (p != null) {
                    p.position = position++;
                }
            }
        }
        final PythonProperty[] props = properties.values().toArray(PythonProperty[]::new);
        properties.clear();
        Arrays.sort(props);
        return props;
    }

    /**
     * Adds a {@code "from module import Type"} statement in the given {@code StringBuilder}, if not already present.
     * This method assumes that the given {@code content} buffer uses only the {@link #lineSeparator} value and that
     * import statements appear at the beginning of a new line. Import statements may occur on any line in the file,
     * not necessarily at the beginning, because the imported file may have a circular dependency to the types already
     * defined in the importing file.
     *
     * @param  module  name of the Python module to import.
     * @param  type    Java type of the object to import. The Python type name will be inferred from that type.
     * @param  content where to write the import statement.
     * @return {@code true} if an import statement has been added. If an existing import statement has been modified,
     *         or if there is no change at all, then this method returns {@code false}.
     */
    private boolean addImport(String module, final Class<?> type, final StringBuilder content) {
        if (!module.equals(module = module.replace('/', '.'))) {
            module = "opengis." + module;
        }
        final String typeName = nameOfClass(type);
        final String statement = lineSeparator + "from " + module + " import ";
        int p = content.indexOf(statement);
        if (p >= 0) {
            final int end = content.indexOf(lineSeparator, p += statement.length());
            final String line = content.substring(p, end);
            p = 0;
            while ((p = line.indexOf(typeName, p)) >= 0) {
                final int s = p;
                p += typeName.length();
                if (s == 0 || !Character.isUnicodeIdentifierPart(line.codePointBefore(s))) {
                    if (p >= line.length() || !Character.isUnicodeIdentifierPart(line.codePointAt(p))) {
                        return false;                       // Class already imported.
                    }
                }
            }
            /*
             * If we find a "from module import" statement, add the type to that line instead of adding a new import statement.
             * We do that way because Python reads the whole file on the first "import module" statement, so deferring the other
             * types from the same imported module will not help the circular dependencies issue.
             */
            content.insert(end, ", " + typeName);
            return false;
        } else {
            content.append(statement, lineSeparator.length(), statement.length()).append(typeName).append(lineSeparator);
            return true;
        }
    }

    /**
     * Creates Python files for all supported types, in approximate dependency order.
     * The contents are stored in the {@link #contents} map.
     */
    private void createContent() {
        createContent(Content.CONTROLLED_VOCABULARY);
        createContent(Content.INTERFACES);
    }

    /**
     * Creates Python files for all types in the given category.
     * The contents are stored in the {@link #contents} map.
     *
     * @param  category  the category (interfaces, enumerations, etc.) of types for which to create Python files.
     */
    private void createContent(final Content category) {
        for (final Class<?> type : category.types()) {
            /*
             * Skip deprecated types (e.g. types from legacy ISO 19115:2003 specification)
             * or type without UML annotations (e.g. extensions specific to Java platform).
             */
            if (type.isSynthetic() || type.isAnnotationPresent(Deprecated.class)) {
                continue;
            }
            final UML uml = type.getAnnotation(UML.class);
            if (uml == null) {
                continue;
            }
            /*
             * Get the OGC/ISO type name (NOT the Java type name) without its prefix. The prefix will determine
             * the Python module where the class will be declared.   Note that there is not always a one-to-one
             * relationship between the prefix in class name (e.g. "CI" in "CI_Citation") and the XML namespace
             * (e.g. "…/cit/…"). The Java package may also differ for historical reasons. For Python we use the
             * the XML prefixes because they provide a finer modularisation, at least with ISO 19115-3 schemas.
             */
            final Map<String, SchemaInformation.Element> definition = schema.getTypeDefinition(type);
            final QName qn = namespaces.name(type, definition);
            if (qn == null) {
                continue;
            }
            final String typeName = qn.getLocalPart();
            final String module = qn.getNamespaceURI();
            if (module.isEmpty()) {
                throw new CanNotExportException("Cannot choose a module for " + typeName);
            }
            /*
             * For Python language, we create one file per module. Note that OGC/ISO/Java packages
             * map to Python "modules"; we do not use the "package" word in the Python sense here.
             * More than one OGC/ISO packages may map to the same Python module since we may merge
             * some small modules together.
             */
            StringBuilder content = contents.get(module);
            if (content == null) {
                content = new StringBuilder(2048)
                       .append('#').append(lineSeparator)
                       .append("#    GeoAPI - Programming interfaces for OGC/ISO standards").append(lineSeparator)
                       .append(COPYRIGHT).append(currentYear).append(" Open Geospatial Consortium, Inc.").append(lineSeparator)
                       .append("#    http://www.geoapi.org").append(lineSeparator)
                       .append('#').append(lineSeparator)
                       .append(lineSeparator)
                       .append("from abc import ABC, abstractmethod").append(lineSeparator)
                       .append("from typing import Sequence").append(lineSeparator);
                if (category.isControlledVocabulary()) {
                    content.append("from enum import Enum").append(lineSeparator);
                }
                contents.put(module, content);
            }
            content.append(lineSeparator).append(lineSeparator).append(lineSeparator);
            switch (category) {
                /*
                 * Creates a Python enumeration.
                 */
                case CODE_LISTS:
                case ENUMERATIONS:
                case CONTROLLED_VOCABULARY: {
                    Object[] values = type.getEnumConstants();
                    if (values == null) try {
                        values = (Object[]) type.getMethod("values").invoke(null);
                    } catch (ReflectiveOperationException | ClassCastException e) {
                        throw new CanNotExportException(type + " is not a valid controlled vocabulary.", e);
                    }
                    content.append("class ").append(typeName).append("(Enum):").append(lineSeparator);
                    for (int i=0; i<values.length; i++) {
                        final ControlledVocabulary item = (ControlledVocabulary) values[i];
                        final String id = item.identifier();
                        if (id != null) {
                            indent(content, 1).append(item.name()).append(" = \"").append(id).append('"').append(lineSeparator);
                        }
                    }
                    break;
                }
                /*
                 * Create a Python class with all properties defined in the Java interface. We use UML annotations
                 * in Java interfaces instead of elements in XSD file because the latter contains a few misspellings
                 * (e.g. "satisifiedPlan" instead of "satisfiedPlan") or extra properties not in abstract specification.
                 */
                case INTERFACES: {
                    String parent = "ABC";
                    boolean hasDependencies = false;
                    for (final Class<?> parentType : type.getInterfaces()) {
                        final String pythonType = nameOfClass(parentType);
                        if (pythonType != null) {
                            parent = pythonType;
                            final QName parentName = namespaces.name(parentType, schema.getTypeDefinition(parentType));
                            if (parentName != null) {
                                final String parentModule = parentName.getNamespaceURI();
                                if (!module.equals(parentModule)) {
                                    hasDependencies |= addImport(parentModule, parentType, content);
                                }
                            }
                            break;              // No multi-inheritence expected. The first type should be the main one.
                        }
                    }
                    final PythonProperty[] props = listProperties(type, module, definition);
                    for (final PythonProperty property : props) {
                        if (property.importFrom != null) {
                            hasDependencies |= addImport(property.importFrom, property.javaType, content);
                        }
                    }
                    if (hasDependencies) content.append(lineSeparator);
                    content.append("class ").append(typeName).append('(').append(parent).append("):").append(lineSeparator);
                    boolean hasBody = (props.length != 0);
                    if (definition != null) {
                        hasBody |= appendDocumentation(definition.get(null), 1, content, lineSeparator);
                    }
                    /*
                     * Declare properties with "@abstractmethod" for mandatory properties, and "@property" for optional ones.
                     * Optional properties are implemented with {@code "return None"}. Special care is needed for properties
                     * of type not yet declared; we have to declare those types as strings instead.
                     */
                    final Map<String,String> replacements = keywords.remove(type);
                    for (final PythonProperty property : props) {
                        content.append(lineSeparator);
                        property.write(replacements, definition, content, lineSeparator);
                    }
                    /*
                     * Python syntax requires that we have at least one indented like below "class" keyword.
                     * This may happen for types without properties and no documentation.
                     */
                    if (!hasBody) {
                        indent(content, 1).append("\"\"\"TODO\"\"\"");
                    }
                    break;
                }
            }
            /*
             * After we wrote the definition of a type, either an enumeration, code list or interface,
             * we need to keep trace that we did. This information will be needed later for managing
             * forward declarations (because of circular dependencies).
             */
            if (declaredTypes.put(type, module) != null) {
                throw new CanNotExportException(type + " is declared twice.");
            }
        }
    }

    /**
     * Adds spaces for the given indentation level.
     *
     * @param  content  where to append indentation.
     * @param  n        number of spaces to append.
     * @return the given buffer for method call chaining.
     */
    static StringBuilder indent(final StringBuilder content, int n) {
        while (--n >= 0) {
            content.append("    ");
        }
        return content;
    }

    /**
     * Adds documentation for the given element if non-null and if documentation exists.
     *
     * @param  element        the element for which to add documentation, or {@code null}.
     * @param  level          the indentation level to use (1 or 2).
     * @param  content        where to add documentation if it exists.
     * @param  lineSeparator  the Windows or Unix style line separator.
     * @return whether a documentation has been written.
     */
    static boolean appendDocumentation(final SchemaInformation.Element element, final int level,
                                       final StringBuilder content, final String lineSeparator)
    {
        if (element != null) {
            final String doc = element.documentation;
            if (doc != null) {
                indent(content, level).append("\"\"\"").append(doc).append("\"\"\"").append(lineSeparator);
                return true;
            }
        }
        return false;
    }

    /**
     * Verifies existing source Python files against expected content, or writes missing source files.
     * The actual files shall contain at least all expected non-empty lines, but may contain more lines
     * for example if comments have been manually added.
     *
     * @throws IOException if an error occurred while reading or writing the files.
     */
    @Test
    public void verifyOrCreateSourceFiles() throws IOException {
        createContent();
        final Path dir = sourceDirectory("python").resolve("opengis");
        final Set<String> modules = namespaces.packages();
        for (final String path : modules) {
            final StringBuilder content = contents.remove(path);
            if (content == null) {
                fail(String.format("No content found for \"%s\" prefix.", path));
                continue;
            }
            final Path file = dir.resolve(path + FILE_SUFFIX);
            if (Files.exists(file)) {
                /*
                 * Python file exist: compare all expected lines with the actual lines read from the file.
                 * This block does not write anything; if the comparison does not match, we fail the test.
                 */
                verify(file, content);
            } else {
                /*
                 * Python file does not exist: write it.
                 * Note: we do not use Files.newBufferedWriter(Path) because buffering is useless in this case.
                 */
                info("Writing " + file);
                try (Writer out = new OutputStreamWriter(Files.newOutputStream(file), ENCODING)) {
                    out.append(content);
                }
            }
        }
        if (!keywords.isEmpty()) {
            fail("Expected types not found: " + keywords.keySet());
        }
        if (!contents.isEmpty()) {
            fail("No Python modules created for " + contents.keySet());
        }
    }

    /**
     * Invoked by {@link #verifyOrCreateSourceFiles()} for comparing the content of an existing file
     * against the expected content. This method is invoked when the file already exists.
     * Subclasses may do nothing if the given file has been extensively edited,
     * so that automatized verification is likely to fail.
     *
     * <p>The default implementation does nothing.</p>
     *
     * @param  file      the existing file to verify.
     * @param  expected  expected content of the file.
     * @throws IOException if an error occurred while reading the file.
     */
    protected void verify(final Path file, final StringBuilder expected) throws IOException {
    }
}
