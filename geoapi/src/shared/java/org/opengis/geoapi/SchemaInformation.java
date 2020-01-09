/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2018-2019 Open Geospatial Consortium, Inc.
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
package org.opengis.geoapi;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.util.Map;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.ArrayDeque;
import java.util.Objects;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Node;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.xml.sax.SAXException;
import org.opengis.annotation.UML;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;


/**
 * Information about types and properties declared in OGC/ISO schemas. This class requires a connection
 * to <a href="https://standards.iso.org/iso/19115/-3/">https://standards.iso.org/iso/19115/-3/</a>
 * or a local copy of those files.
 *
 * <p><b>Limitations:</b></p>
 * Current implementation ignores the XML prefix (e.g. {@code "cit:"} in {@code "cit:CI_Citation"}).
 * We assume that there is no name collision, especially given that {@code "CI_"} prefix in front of
 * most OGC/ISO class names have the effect of a namespace. If a collision nevertheless happen, then
 * an exception will be thrown.
 *
 * <p>Current implementation assumes that XML element name, type name, property name and property type
 * name follow some naming convention. For example type names are suffixed with {@code "_Type"} in OGC
 * schemas, while property type names are suffixed with {@code "_PropertyType"}.  This class throws an
 * exception if a type does not follow the expected naming convention. This requirement makes
 * implementation easier, by reducing the amount of {@link Map}s that we need to manage.</p>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @since   3.1
 * @version 3.1
 */
public class SchemaInformation {
    /**
     * The URL from where to download ISO schema. The complete URL is formed by taking a namespace,
     * replace the {@value #ROOT_NAMESPACE} by this {@value} value, then append {@code ".xsd"} suffix.
     */
    public static final String SCHEMA_ROOT_URL = "https://standards.iso.org/iso/";

    /**
     * The root of ISO namespaces, which is {@value}. This is identical to {@link #SCHEMA_ROOT_URL}
     * but with {@code "http"} protocol instead of {@code "https"} for historical reasons.
     */
    private static final String ROOT_NAMESPACE = "http://standards.iso.org/iso/";

    /**
     * The prefix of XML type names for properties. In ISO/OGC schemas, this prefix does not appear
     * in the definition of class types but may appear in the definition of property types.
     */
    private static final String ABSTRACT_PREFIX = "Abstract_";

    /**
     * The suffix of XML type names for classes.
     * This is used by convention in OGC/ISO standards (but not necessarily in other XSD).
     */
    private static final String TYPE_SUFFIX = "_Type";

    /**
     * The suffix of XML property type names in a given class.
     * This is used by convention in OGC/ISO standards (but not necessarily in other XSD).
     */
    private static final String PROPERTY_TYPE_SUFFIX = "_PropertyType";

    /**
     * XML type to ignore because of key collisions in {@link #typeDefinitions}.
     * Those collisions occur because code lists are defined as links to the same file,
     * with only different anchor positions.
     */
    private static final String CODELIST_TYPE = "gco:CodeListValue_Type";

    /**
     * Separator between XML prefix and the actual name.
     */
    private static final char PREFIX_SEPARATOR = ':';

    /**
     * If the computer contains a local copy of ISO schemas, path to that directory. Otherwise {@code null}.
     * If non-null, the {@value #SCHEMA_ROOT_URL} prefix in URL will be replaced by that path.
     * This field is usually {@code null}, but can be set to a non-null value for making tests faster.
     */
    private final Path schemaRootDirectory;

    /**
     * A temporary buffer for miscellaneous string operations.
     * Valid only in a local scope since the content may change at any time.
     * For making this limitation clear, its length shall bet set to 0 after each usage.
     */
    private final StringBuilder buffer;

    /**
     * The DOM factory used for reading XSD schemas.
     */
    private final DocumentBuilderFactory factory;

    /**
     * URL of schemas loaded, for avoiding loading the same schema many times.
     * The last element on the queue is the schema in process of being loaded,
     * used for resolving relative paths in {@code <xs:include>} elements.
     */
    private final Deque<String> schemaLocations;

    /**
     * The type and namespace of a property or type.
     */
    public static final class Element {
        /** The element type name.                   */ public final String  typeName;
        /** Element namespace as an URI.             */ public final String  namespace;
        /** Whether the property is mandatory.       */ public final boolean isRequired;
        /** Whether the property accepts many items. */ public final boolean isCollection;
        /** Documentation, or {@code null} if none.  */ public final String  documentation;

        /** Stores information about a new property or type. */
        Element(final String typeName, final String namespace, final boolean isRequired, final boolean isCollection,
                final String  documentation)
        {
            this.typeName      = typeName;
            this.namespace     = namespace;
            this.isRequired    = isRequired;
            this.isCollection  = isCollection;
            this.documentation = documentation;
        }

        /**
         * Returns the prefix if it can be derived from the {@linkplain #namespace}, or {@code null} otherwise.
         */
        String prefix() {
            if (namespace.startsWith(ROOT_NAMESPACE)) {
                final int end   = namespace.lastIndexOf('/', namespace.length() - 1);
                final int start = namespace.lastIndexOf('/', end - 1);
                return namespace.substring(start + 1, end);
            }
            return null;
        }

        /**
         * Tests if this element has the same type name (including namespace) than given element.
         */
        boolean nameEqual(final Element other) {
            return Objects.equals(typeName,  other.typeName)
                && Objects.equals(namespace, other.namespace);
        }

        /**
         * Returns a string representation for debugging purpose.
         *
         * @return a string representation (may change in any future version).
         */
        @Override
        public String toString() {
            return typeName;
        }
    }

    /**
     * Definitions of XML type for each class. In OGC/ISO schemas, those definitions have the {@value #TYPE_SUFFIX}
     * suffix in their name (which is omitted). The value is another map, where keys are property names and values
     * are their types, having the {@value #PROPERTY_TYPE_SUFFIX} suffix in their name (which is omitted).
     */
    private final Map<String, Map<String,Element>> typeDefinitions;

    /**
     * Notifies that we are about to define the XML type for each property. In OGC/ISO schemas, those definitions
     * have the {@value #PROPERTY_TYPE_SUFFIX} suffix in their name (which is omitted). After this method call,
     * properties can be defined by calls to {@link #addProperty(String, String, boolean, boolean)}.
     */
    private void preparePropertyDefinitions(final String type) throws SchemaException {
        final String k = trim(type, TYPE_SUFFIX).intern();
        if ((currentProperties = typeDefinitions.get(k)) == null) {
            typeDefinitions.put(k, currentProperties = new LinkedHashMap<>());
        }
    }

    /**
     * The properties of the XML type under examination, or {@code null} if none.
     * If non-null, this is one of the values in the {@link #typeDefinitions} map.
     * By convention, the {@code null} key is associated to information about the class.
     */
    private Map<String,Element> currentProperties;

    /**
     * A single property type under examination, or {@code null} if none.
     * If non-null, this is a value ending with the {@value #PROPERTY_TYPE_SUFFIX} suffix.
     */
    private String currentPropertyType;

    /**
     * Default value for the {@code required} attribute of {@link XmlElement}. This default value should
     * be {@code true} for properties declared inside a {@code <sequence>} element, and {@code false} for
     * properties declared inside a {@code <choice>} element.
     */
    private boolean requiredByDefault;

    /**
     * Namespace of the type or properties being defined.
     * This is specified by {@code <xs:schema targetNamespace="(…)">}.
     */
    private String targetNamespace;

    /**
     * Expected departures between XML schemas and GeoAPI annotations.
     */
    private final Departures departures;

    /**
     * Variant of the documentation to store (none, verbatim or sentences).
     */
    private final DocumentationStyle documentationStyle;

    /**
     * Creates a new verifier. If the computer contains a local copy of ISO schemas, then the {@code schemaRootDirectory}
     * argument can be set to that directory for faster schema loadings. If non-null, that directory should contain the
     * same files than <a href="https://standards.iso.org/iso/">https://standards.iso.org/iso/</a> (not necessarily with
     * all sub-directories). In particular, that directory should contain an {@code 19115} sub-directory.
     *
     * <p>The {@link Departures#mergedTypes} entries will be {@linkplain Map#remove removed} as they are found.
     * This allows the caller to verify if the map contains any unnecessary departure declarations.</p>
     *
     * @param schemaRootDirectory  path to local copy of ISO schemas, or {@code null} if none.
     * @param departures           expected departures between XML schemas and GeoAPI annotations.
     * @param style                style of the documentation to store (none, verbatim or sentences).
     */
    public SchemaInformation(final Path schemaRootDirectory, final Departures departures, final DocumentationStyle style) {
        this.schemaRootDirectory = schemaRootDirectory;
        this.departures          = departures;
        this.documentationStyle  = style;
        factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        buffer = new StringBuilder(100);
        typeDefinitions = new HashMap<>();
        schemaLocations = new ArrayDeque<>();
    }

    /**
     * Loads the default set of XSD files. This method invokes {@link #loadSchema(String)}
     * for a pre-defined set of metadata schemas, in approximate dependency order.
     *
     * @throws ParserConfigurationException if the XML parser can not be created.
     * @throws IOException     if an I/O error occurred while reading a file.
     * @throws SAXException    if a file can not be parsed as a XML document.
     * @throws SchemaException if a XML document can not be interpreted as an OGC/ISO schema.
     */
    public void loadDefaultSchemas() throws ParserConfigurationException, IOException, SAXException, SchemaException {
        for (final String p : new String[] {
//              "19115/-3/gco/1.0/gco.xsd",         // Geographic Common — defined in a different way than other modules
                "19115/-3/lan/1.0/lan.xsd",         // Language localization
                "19115/-3/mcc/1.0/mcc.xsd",         // Metadata Common Classes
                "19115/-3/gex/1.0/gex.xsd",         // Geospatial Extent
                "19115/-3/cit/1.0/cit.xsd",         // Citation and responsible party information
                "19115/-3/mmi/1.0/mmi.xsd",         // Metadata for maintenance information
                "19115/-3/mrd/1.0/mrd.xsd",         // Metadata for resource distribution
                "19115/-3/mdt/1.0/mdt.xsd",         // Metadata for data transfer
                "19115/-3/mco/1.0/mco.xsd",         // Metadata for constraints
                "19115/-3/mri/1.0/mri.xsd",         // Metadata for resource identification
                "19115/-3/srv/2.0/srv.xsd",         // Metadata for services
                "19115/-3/mac/1.0/mac.xsd",         // Metadata for acquisition
                "19115/-3/mrc/1.0/mrc.xsd",         // Metadata for resource content
                "19115/-3/mrl/1.0/mrl.xsd",         // Metadata for resource lineage
                "19157/-2/mdq/1.0/mdq.xsd",         // Metadata for data quality
                "19115/-3/mrs/1.0/mrs.xsd",         // Metadata for reference system
                "19115/-3/msr/1.0/msr.xsd",         // Metadata for spatial representation
                "19115/-3/mas/1.0/mas.xsd",         // Metadata for application schema
                "19115/-3/mex/1.0/mex.xsd",         // Metadata with schema extensions
                "19115/-3/mpc/1.0/mpc.xsd",         // Metadata for portrayal catalog
                "19115/-3/mdb/1.0/mdb.xsd"})        // Metadata base
        {
            loadSchema(SCHEMA_ROOT_URL + p);
        }
        /*
         * Hard-coded information from "19115/-3/gco/1.0/gco.xsd". We apply this workaround because current SchemaInformation
         * implementation can not parse most of gco.xsd file because it does not follow the usual pattern found in other files.
         */
        final String namespace = ROOT_NAMESPACE + "19115/-3/gco/1.0";
        addHardCoded("NameSpace",     namespace,
                     "isGlobal",      "Boolean",           Boolean.TRUE, Boolean.FALSE,
                     "name",          "CharacterSequence", Boolean.TRUE, Boolean.FALSE);

        addHardCoded("GenericName",   namespace,
                     "scope",         "NameSpace",         Boolean.TRUE, Boolean.FALSE,
                     "depth",         "Integer",           Boolean.TRUE, Boolean.FALSE,
                     "parsedName",    "LocalName",         Boolean.TRUE, Boolean.TRUE);

        addHardCoded("ScopedName",    namespace,
                     "head",          "LocalName",         Boolean.TRUE, Boolean.FALSE,
                     "tail",          "GenericName",       Boolean.TRUE, Boolean.FALSE,
                     "scopedName",    "CharacterSequence", Boolean.TRUE, Boolean.FALSE);

        addHardCoded("LocalName",     namespace,
                     "aName",         "CharacterSequence", Boolean.TRUE, Boolean.FALSE);

        addHardCoded("MemberName",    namespace,
                     "attributeType", "TypeName",          Boolean.TRUE, Boolean.FALSE,
                     "aName",         "CharacterSequence", Boolean.TRUE, Boolean.FALSE);

        addHardCoded("RecordSchema",  namespace,
                     "schemaName",    "LocalName",         Boolean.TRUE, Boolean.FALSE,
                     "description",   null,                Boolean.TRUE, Boolean.FALSE);

        addHardCoded("RecordType",    namespace,
                     "typeName",      "TypeName",          Boolean.TRUE, Boolean.FALSE,
                     "schema",        "RecordSchema",      Boolean.TRUE, Boolean.FALSE,
                     "memberTypes",   null,                Boolean.TRUE, Boolean.FALSE);

        addHardCoded("Record",        namespace,
                     "recordType",    "RecordType",        Boolean.TRUE, Boolean.FALSE,
                     "memberValue",   null,                Boolean.TRUE, Boolean.FALSE);
    }

    /**
     * Adds a hard coded property. Used only for XSD file that we can not parse.
     *
     * @param  type        name of the type.
     * @param  namespace   namespace of all properties.
     * @param  properties  (property name, property type, isRequired, isCollection) tuples.
     */
    private void addHardCoded(final String type, final String namespace, final Object... properties) throws SchemaException {
        final Map<String,Element> pm = new LinkedHashMap<>(properties.length);
        for (int i=0; i<properties.length;) {
            final String p = (String) properties[i++];
            if (pm.put(p, new Element((String) properties[i++], namespace, (Boolean) properties[i++], (Boolean) properties[i++], null)) != null) {
                throw new SchemaException(p);
            }
        }
        if (typeDefinitions.put(type, pm) != null) {
            throw new SchemaException(type);
        }
    }

    /**
     * Loads the XSD file at the given URL.
     * Only information of interest are stored, and we assume that the XSD follows OGC/ISO conventions.
     * This method may be invoked recursively if the XSD contains {@code <xs:include>} elements.
     *
     * @param  location  complete URL to the XSD file to load.
     * @throws ParserConfigurationException if the XML parser can not be created.
     * @throws IOException     if an I/O error occurred while reading the specified file.
     * @throws SAXException    if the specified file can not be parsed as a XML document.
     * @throws SchemaException if the XML document can not be interpreted as an OGC/ISO schema.
     */
    public void loadSchema(String location)
            throws ParserConfigurationException, IOException, SAXException, SchemaException
    {
        if (schemaRootDirectory != null && location.startsWith(SCHEMA_ROOT_URL)) {
            location = schemaRootDirectory.resolve(location.substring(SCHEMA_ROOT_URL.length())).toUri().toString();
        }
        if (!schemaLocations.contains(location)) {
            if (location.startsWith("http")) {
                info("Downloading " + location);
            }
            final Document doc;
            try (final InputStream in = new URL(location).openStream()) {
                doc = factory.newDocumentBuilder().parse(in);
            }
            schemaLocations.addLast(location);
            storeClassDefinition(doc);
        }
    }

    /**
     * Stores information about classes in the given node and children. This method invokes itself
     * for scanning children, until we reach sub-nodes about properties (in which case we continue
     * with {@link #storePropertyDefinition(Node)}).
     */
    private void storeClassDefinition(final Node node)
            throws IOException, ParserConfigurationException, SAXException, SchemaException
    {
        if (XMLConstants.W3C_XML_SCHEMA_NS_URI.equals(node.getNamespaceURI())) {
            switch (node.getLocalName()) {
                case "schema": {
                    targetNamespace = getMandatoryAttribute(node, "targetNamespace").intern();
                    break;
                }
                /*
                 * <xs:include schemaLocation="(…).xsd">
                 * Load the schema at the given URL, which is assumed relative.
                 */
                case "include": {
                    final String oldTarget = targetNamespace;
                    final String location = schemaLocations.getLast();
                    final String path = buffer.append(location, 0, location.lastIndexOf('/') + 1)
                            .append(getMandatoryAttribute(node, "schemaLocation")).toString();
                    buffer.setLength(0);
                    loadSchema(path);
                    targetNamespace = oldTarget;
                    return;                             // Skip children (normally, there is none).
                }
                /*
                 * <xs:element name="(…)" type="(…)_Type">
                 * Verify that the names comply with our assumptions.
                 */
                case "element": {
                    final String name = getMandatoryAttribute(node, "name");
                    final String type = getMandatoryAttribute(node, "type");
                    final String doc  = documentation(node);
                    if (CODELIST_TYPE.equals(type)) {
                        final Map<String,Element> properties = new HashMap<>(4);
                        final Element info = new Element(null, targetNamespace, false, false, doc);
                        properties.put(null, info);     // Remember namespace of the code list.
                        properties.put(name, info);     // Pseudo-property used in our CodeList adapters.
                        if (typeDefinitions.put(name, properties) != null) {
                            throw new SchemaException(String.format("Code list \"%s\" is defined twice.", name));
                        }
                    } else {
                        /*
                         * Any type other than code list. Call `addProperty(null, …)` with null as a sentinel value
                         * for class definition. Properties will be added later when reading the `complexType` block.
                         */
                        verifyNamingConvention(schemaLocations.getLast(), name, type, TYPE_SUFFIX);
                        preparePropertyDefinitions(type);
                        addProperty(null, type, false, false, doc);
                        currentProperties = null;
                    }
                    return;                             // Ignore children (they are about documentation).
                }
                /*
                 * <xs:complexType name="(…)_Type">
                 * <xs:complexType name="(…)_PropertyType">
                 */
                case "complexType": {
                    String name = getMandatoryAttribute(node, "name");
                    if (name.endsWith(PROPERTY_TYPE_SUFFIX)) {
                        currentPropertyType = name;
                        verifyPropertyType(node);
                        currentPropertyType = null;
                    } else {
                        /*
                         * In the case of "(…)_Type", we will replace some ISO 19115-2 types by ISO 19115-1 types.
                         * For example "MI_Band_Type" is renamed as "MD_Band_Type". We do that because we use only
                         * one class for representing those two distinct ISO types. Note that not all ISO 19115-2
                         * types extend an ISO 19115-1 type, so we need to apply a case-by-case approach.
                         */
                        requiredByDefault = true;
                        final Departures.MergeInfo info = departures.nameOfMergedType(name);
                        preparePropertyDefinitions(info.typeName);
                        info.beforeAddProperties(currentProperties);
                        storePropertyDefinition(node);
                        info.afterAddProperties(currentProperties);
                        currentProperties = null;
                    }
                    return;                             // Skip children since they have already been examined.
                }
            }
        }
        for (Node child = node.getFirstChild(); child != null; child = child.getNextSibling()) {
            storeClassDefinition(child);
        }
    }

    /**
     * Stores information about properties in the current class. The {@link #currentProperties} field must be
     * set to the map of properties for the class defined by the enclosing {@code <xs:complexType>} element.
     * This method parses elements of the following form:
     *
     * {@preformat xml
     *   <xs:element name="(…)" type="(…)_PropertyType" minOccurs="(…)" maxOccurs="(…)">
     * }
     */
    private void storePropertyDefinition(final Node node) throws SchemaException {
        if (XMLConstants.W3C_XML_SCHEMA_NS_URI.equals(node.getNamespaceURI())) {
            switch (node.getLocalName()) {
                case "sequence": {
                    requiredByDefault = true;
                    break;
                }
                case "choice": {
                    requiredByDefault = false;
                    break;
                }
                case "element": {
                    boolean isRequired = requiredByDefault;
                    boolean isCollection = false;
                    final NamedNodeMap attributes = node.getAttributes();
                    if (attributes != null) {
                        Node attr = attributes.getNamedItem("minOccurs");
                        if (attr != null) {
                            final String value = attr.getNodeValue();
                            if (value != null) {
                                isRequired = Integer.parseInt(value) > 0;
                            }
                        }
                        attr = attributes.getNamedItem("maxOccurs");
                        if (attr != null) {
                            final String value = attr.getNodeValue();
                            if (value != null) {
                                isCollection = value.equals("unbounded") || Integer.parseInt(value) >  1;
                            }
                        }
                    }
                    addProperty(getMandatoryAttribute(node, "name").intern(),
                           trim(getMandatoryAttribute(node, "type"), PROPERTY_TYPE_SUFFIX).intern(),
                           isRequired, isCollection, documentation(node));
                    return;
                }
            }
        }
        for (Node child = node.getFirstChild(); child != null; child = child.getNextSibling()) {
            storePropertyDefinition(child);
        }
    }

    /**
     * Verifies the naming convention of property defined by the given node. The {@link #currentPropertyType}
     * field must be set to the type of the property defined by the enclosing {@code <xs:complexType>} element.
     * This method parses elements of the following form:
     *
     * {@preformat xml
     *   <xs:element ref="(…)">
     * }
     */
    private void verifyPropertyType(final Node node) throws SchemaException {
        if (XMLConstants.W3C_XML_SCHEMA_NS_URI.equals(node.getNamespaceURI())) {
            switch (node.getLocalName()) {
                case "element": {
                    verifyNamingConvention(schemaLocations.getLast(),
                            getMandatoryAttribute(node, "ref"), currentPropertyType, PROPERTY_TYPE_SUFFIX);
                    return;
                }
                case "choice": {
                    /*
                     * <xs:choice> is used for unions. In those case, many <xs:element> are expected,
                     * and none of them may have the union name. So we have to stop verification here.
                     */
                    return;
                }
            }
        }
        for (Node child = node.getFirstChild(); child != null; child = child.getNextSibling()) {
            verifyPropertyType(child);
        }
    }

    /**
     * Verifies that the relationship between the name of the given entity and its type are consistent with
     * OGC/ISO conventions. This method ignores the prefix (e.g. {@code "mdb:"} in {@code "mdb:MD_Metadata"}).
     *
     * @param  enclosing  schema or other container where the error happened.
     * @param  name       the class or property name. Example: {@code "MD_Metadata"}, {@code "citation"}.
     * @param  type       the type of the above named object. Example: {@code "MD_Metadata_Type"}, {@code "CI_Citation_PropertyType"}.
     * @param  suffix     the expected suffix at the end of {@code type}.
     * @throws SchemaException if the given {@code name} and {@code type} are not compliant with expected convention.
     */
    private static void verifyNamingConvention(final String enclosing,
            final String name, final String type, final String suffix) throws SchemaException
    {
        if (type.endsWith(suffix)) {
            int nameStart = name.indexOf(PREFIX_SEPARATOR) + 1;        // Skip "mdb:" or similar prefix.
            int typeStart = type.indexOf(PREFIX_SEPARATOR) + 1;
            final int plg = ABSTRACT_PREFIX.length();
            if (name.regionMatches(nameStart, ABSTRACT_PREFIX, 0, plg)) nameStart += plg;
            if (type.regionMatches(typeStart, ABSTRACT_PREFIX, 0, plg)) typeStart += plg;
            final int length = name.length() - nameStart;
            if (type.length() - typeStart - suffix.length() == length &&
                    type.regionMatches(typeStart, name, nameStart, length))
            {
                return;
            }
        }
        throw new SchemaException(String.format("Error in %s:%n" +
                "The type name should be the name with \"%s\" suffix, but found name=\"%s\" and type=\"%s\">.",
                enclosing, suffix, name, type));
    }

    /**
     * Adds a property of the current name and type. This method is invoked during schema parsing.
     * The property namespace is assumed to be {@link #targetNamespace}.
     */
    private void addProperty(final String name, final String type, final boolean isRequired, final boolean isCollection,
            final String documentation) throws SchemaException
    {
        final Element info = new Element(type, targetNamespace, isRequired, isCollection, documentation);
        final Element old = currentProperties.put(name, info);
        if (old != null && !old.nameEqual(info)) {
            throw new SchemaException(String.format("Error while parsing %s:%n" +
                    "Property \"%s\" is associated to type \"%s\", but that property was already associated to \"%s\".",
                    schemaLocations.getLast(), name, type, old));
        }
    }

    /**
     * Returns the documentation for the given node, with the first letter made upper case
     * and a dot added at the end of the sentence. Null or empty texts are ignored.
     */
    private String documentation(Node node) {
        if (documentationStyle != DocumentationStyle.NONE) {
            node = node.getFirstChild();
            while (node != null) {
                final String name = node.getLocalName();
                if (name != null) switch (name) {
                    case "annotation": {
                        node = node.getFirstChild();        // Expect "documentation" as a child of "annotation".
                        continue;
                    }
                    case "documentation": {
                        String doc = node.getTextContent();
                        if (doc != null && documentationStyle == DocumentationStyle.SENTENCE) {
                            doc = DocumentationStyle.sentence(doc, buffer);
                            buffer.setLength(0);
                        }
                        return doc;
                    }
                }
                node = node.getNextSibling();
            }
        }
        return null;
    }

    /**
     * Removes leading and trailing spaces if any, then the prefix and the suffix in the given name.
     * The prefix is anything before the first {@value #PREFIX_SEPARATOR} character.
     * The suffix must be the given string, otherwise an exception is thrown.
     *
     * @param  name     the name from which to remove prefix and suffix.
     * @param  suffix   the suffix to remove.
     * @return the given name without prefix and suffix.
     * @throws SchemaException if the given name does not end with the given suffix.
     */
    private static String trim(String name, final String suffix) throws SchemaException {
        name = name.trim();
        if (name.endsWith(suffix)) {
            return name.substring(name.indexOf(PREFIX_SEPARATOR) + 1, name.length() - suffix.length());
        }
        throw new SchemaException(String.format("Expected a name ending with \"%s\" but got \"%s\".", suffix, name));
    }

    /**
     * Returns the attribute of the given name in the given node,
     * or throws an exception if the attribute is not present.
     */
    private static String getMandatoryAttribute(final Node node, final String name) throws SchemaException {
        final NamedNodeMap attributes = node.getAttributes();
        if (attributes != null) {
            final Node attr = attributes.getNamedItem(name);
            if (attr != null) {
                final String value = attr.getNodeValue();
                if (value != null) {
                    return value;
                }
            }
        }
        throw new SchemaException(String.format("Node \"%s\" should have a '%s' attribute.", node.getNodeName(), name));
    }

    /**
     * Returns the type definitions for a class of the given name.
     * Keys are property names and values are their types, with {@code "_PropertyType"} suffix omitted.
     * The map contains an entry associated to the {@code null} key for the class containing those properties.
     *
     * <p>The given {@code typeName} shall be the XML name, not the OGC/ISO name. They differ for abstract classes.
     * For example the {@link org.opengis.metadata.citation.Party} type is named {@code "CI_Party"} is OGC/ISO models
     * but {@code "AbstractCI_Party"} in XML schemas.</p>
     *
     * @param  typeName  XML name of a type (e.g. {@code "MD_Metadata"}), or {@code null}.
     * @return all properties for the given class in declaration order, or {@code null} if unknown.
     */
    public Map<String,Element> getTypeDefinition(final String typeName) {
        return typeDefinitions.get(typeName);
    }

    /**
     * Returns the type definitions for the given class. This convenience method computes a XML name from
     * the annotations attached to the given type, then delegates to {@link #getTypeDefinition(String)}.
     *
     * @param  type  the GeoAPI interface (e.g. {@link org.opengis.metadata.Metadata}), or {@code null}.
     * @return all properties for the given class in declaration order, or {@code null} if unknown.
     */
    public Map<String,Element> getTypeDefinition(final Class<?> type) {
        if (type != null) {
            final UML uml = type.getAnnotation(UML.class);
            if (uml != null) {
                final Classifier c = type.getAnnotation(Classifier.class);
                boolean applySpellingChange = false;
                do {                                                // Will be executed 1 or 2 times only.
                    String name = uml.identifier();
                    if (applySpellingChange) {
                        name = departures.spellingChanges.get(name);
                        if (name == null) break;
                    }
                    if (c != null && Stereotype.ABSTRACT.equals(c.value())) {
                        name = "Abstract" + name;
                    }
                    Map<String,Element> def = getTypeDefinition(name);
                    if (def != null) return def;
                } while ((applySpellingChange = !applySpellingChange));
            }
        }
        return null;
    }

    /**
     * Prints the given message to standard output stream.
     * This method is used instead of logging for reporting downloading of schemas.
     *
     * @param  message  the message to print.
     */
    @SuppressWarnings("UseOfSystemOutOrSystemErr")
    private static void info(final String message) {
        System.out.println("[GeoAPI] " + message);
    }
}
