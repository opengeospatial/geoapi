package org.opengis.filter.capability;

/**
 * Supported arithmetic operators in a filter capabilities document.
 * <p>
 * <pre>
 * &lt;xsd:complexType name="ArithmeticOperatorsType">
 *     &lt;xsd:choice maxOccurs="unbounded">
 *        &lt;xsd:element ref="ogc:SimpleArithmetic"/>
 *        &lt;xsd:element name="Functions" type="ogc:FunctionsType"/>
 *     &lt;/xsd:choice>
 *  &lt;/xsd:complexType>
 * </pre>
 * </p>
 * @author Justin Deoliveira, The Open Planning Project
 *
 */
public interface ArithmeticOperators {

    /**
     * Indicates if simple arithmetic is provided.
     * <p>
     * <pre>
     * &lt;xsd:element ref="ogc:SimpleArithmetic"/>
     * </pre>
     * </p>
     */
    boolean hasSimpleArithmetic();

    /**
     * Provided functions.
     * <p>
     * <pre>
     * &lt;xsd:element name="Functions" type="ogc:FunctionsType"/>
     * </pre>
     * </p>
     */
    Functions getFunctions();
}
