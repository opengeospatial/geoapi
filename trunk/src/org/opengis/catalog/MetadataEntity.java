/*
 *    Geotools2 - OpenSource mapping toolkit
 *    http://geotools.org
 *    (C) 2002, Geotools Project Managment Committee (PMC)
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation;
 *    version 2.1 of the License.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 *
 */
package org.opengis.catalog;
import java.util.List;


/**
 * Interface for progamatic access to the Metadata content.
 * <p>
 * This API is used by Catalog to query against CatalogEntry Metadata.
 * </p>
 * <p>
 * Conceptually a Metadata is comprised of a series of elements. These elements
 * are described by an associated EntityType & Element schema
 * information.
 * </p>
 * </p>
 * In the best of all possible worlds the EntityType is a member of a
 * well known specification such as ISO19115 (Metadata for spatial data), or
 * ISO19119 (Metadata for Services). GeoAPI kindly provides these abstractions
 * as a series of interfaces, which we are in the process of implementing.
 * </p>
 * <p>
 * The Metadata interface is similar in use to that of the Feature, it has
 * the same relationship with EntityType that Feature has with FeatureType.
 * By extention Element is similar to AttributeType.
 * </p>
 * <p>
 * Aside: Many classes that implement Metadata follow Java Bean property
 * conventions to allow for element access. This is not a requirement.
 * </p>
 * <p>
 * Internationalization: Accounting for internationlization is an interesting
 * problem with metadata.
 * <ul>
 * <li>One way is to provide a language metadata and only query against
 * Metadata that matches the language you are interested in.
 * <li>Another way is to return a "InternationalString" that knows how to write
 * itself out in the Locale you are interested in.
 * <li>Consider use of a elemnts( Locale ) method
 * <li>Finally you can ignore the problem
 * </ul> 
 * </p>
 * <p>
 * This interface is based on the discussion in the Abstract Catalog specification:
 * <pre><code>
 * 4.6 Metadata dataset
 *     Metadta describing a specific dataset [ISO 19101]
 * 
 * 4.7 Metadata entity
 *     Group of metadata elements and other metadata entities describing the same
 *     aspect of data.
 * 
 * NOTE 1  A metadata entity may contain one or more metadata entities
 * NOTE 2  A metadata is equivalent to a class in UML terminology [ISO 19101]
 * 
 * 4.8 Metadata schema
 *     Conceptual schema describing metadata
 * 
 * NOTE ISO 19115 describes a standard for a metadata schema [ISO 19101]
 * </code></pre>
 * </p>
 * <p>
 * 
 * @UML abstract CG_Catalog (Figure 6)
 * @see ISO 19101
 * @see ISO 19115 
 * @author jeichar
 */
public interface MetadataEntity {
    /**
     * Access to the values associated with metadata elements.
     * <p>
     * The list is returned in the same order as described by the EntityType
     * schema information.
     * </p>
     * @return List of Elements
     */
    public List elements();

    /**
     * Access to Metadata element values specified by the xPath expression.
     * <p>
     * Usually used to return individual objects, if the xPath expression
     * matches multiple elements a List will be returned.
     * <p>
     * To be clear xPath expression can be used to:
     * <ul>
     * <li>extract individual members from a List or Array element
     * <li>extract individual members from a Map element
     * <li>extract individual elements from a nested Metadata
     * </p>
     * <p>
     * There is no way at this time to tell the difference between a xPath
     * expression that matches multiple elements, and a xPath expression that
     * matches a single element that happens to be a List. Similarly there
     * is no way to tell if a element had the value null, or if the element
     * did not exist at all. Both these questions may answered by working
     * through the EntityType schema information.
     * </p>
     * @param xPath XPath representation of element location.
     *
     * @return element value, List of element value, or null if xPath did not
     *         match anything.
     */
    Object getElement(String xPath);

    /**
     * Gets the value of the provided Element.
     * 
     * @param Element that indicates the value the caller wishes to obtain
     * @return The value of the element the parameter represents
     * 		null if the current Metadata does not contain the Element
     */
    public Object getElement(Element element);

    /**
     * EntityType describes schema of this Metadata.
     *
     * @return the EntityType that describes the current Metadata object
     */
    public EntityType getEntityType();

    /**
     * Metadata schema description - the "type" of Metadata.
     * <p>
     * Allow for progamatic introspection of the Metadata -
     * provides the list of Elements.
     *
     * @author jeichar
     */
    public static interface EntityType {
        /**
         * The cPath is used to identify Elements in the Metadata data hierarchy
         * If the xpath has wild cards a List of Metadata Elements will be returned.
         * 
         * @param xpath an XPath statement that indicates 0 or more Elements.
         * @return Null if no elements are found to match the xpath 
         * 		A Element if exactly one is found to match the xpath
         * 		A List is many Elements are found to match the xpath.
         */
        public Object getElement(String xpath);

        /**
         * Get a List of all the Elements this Entity contains.
         * <p>
         * Only the elements contained by the current Entity are returned,
         * in other words this method is not recursive, elements in sub-enities
         * are not returned.
         * </p>
         * @return a List of all the Elements this Entity contains
         */
        public List getElements();
    }

    /**
     * Metadata element schema description - the type of a Metadata element.
     * <p>
     * Metadata Elements are unique within a Metadata Entity
     * Used to inspect the type and structure of a metadata element 
     * </p>
     * @author jeichar
     *
     */
    public static interface Element {
        /**
         * Gets the Type (Java Class) of this element.
         *
         * @return Type.
         */
        public Class getType();

        /**
         * Gets the name of this element.
         *
         * @return Name.
         */
        public String getName();

        /**
         * Returns whether nulls are allowed for this element.
         *
         * @return true if nulls are permitted, false otherwise.
         */
        public boolean isNillable();

        /**
         * Whether or not this element is complex in any way.  If it is
         * not nested then the code can just do the default processing, such
         * as printing the element directly, for example.  If it is nested then
         * that indicates there is more to be done, and the actual ElementType
         * should be determined and processed accordingly.
         *
         * @return <code>true</code> if Any
         *
         */
        public boolean isMetadataEntity();

        /**
         * If the current element is an entity then the entity object that describes the 
         * current element is returned.
         *
         * @return Null, if not a metadata entity (isMetadataEntity returns false)
         * 		The EntityType object describing the current element if current element is
         * 		an entity 
         */
        public EntityType getEntityType();
    }
}