package org.opengis.filter.capability;

/**
 * Supported comparison operators in a filter capabilities document.
 * <p>
 * <pre>
 * &lt;xsd:complexType name="ComparisonOperatorsType">
 *     &lt;xsd:sequence maxOccurs="unbounded">
 *        &lt;xsd:element name="ComparisonOperator" type="ogc:ComparisonOperatorType"/>
 *     &lt;/xsd:sequence>
 *  &lt;/xsd:complexType>
 * </pre>
 * </p>
 * @author Justin Deoliveira, The Open Planning Project
 *
 */
public interface ComparisonOperators {

    /**
     * Provided comparison operators.
     * <p>
     *  <pre>
     *  &lt;xsd:element name="ComparisonOperator" type="ogc:ComparisonOperatorType"/>
     *  </pre>
     * </p>
     */
    Operator[] getOperators();
    
    /**
     * Returns true if the operator with the specified name is supported.
     *
     */
    boolean hasComparisonOperator(String operatorName);
}
