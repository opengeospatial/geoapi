// $Header:
// /cvsroot/deegree/src/org/deegree/ogcwebservices/getcapabilities/Contents.java,v
// 1.1 2004/06/23 11:55:40 mschneider Exp $
/*----------------    FILE HEADER  ------------------------------------------

 This file is part of deegree.
 Copyright (C) 2001-2004 by:
 EXSE, Department of Geography, University of Bonn
 http://www.giub.uni-bonn.de/exse/
 lat/lon Fitzke/Fretter/Poth GbR
 http://www.lat-lon.de

 This library is free software; you can redistribute it and/or
 modify it under the terms of the GNU Lesser General Public
 License as published by the Free Software Foundation; either
 version 2.1 of the License, or (at your option) any later version.

 This library is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 Lesser General Public License for more details.

 You should have received a copy of the GNU Lesser General Public
 License along with this library; if not, write to the Free Software
 Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

 Contact:

 Andreas Poth
 lat/lon GmbH
 Meckenheimer Allee 176
 53115 Bonn
 Germany
 E-Mail: poth@lat-lon.de

 Jens Fitzke
 Department of Geography
 University of Bonn
 Meckenheimer Allee 166
 53115 Bonn
 Germany
 E-Mail: jens.fitzke@uni-bonn.de


 ---------------------------------------------------------------------------*/
package org.opengis.filter.capability;

// Annotations
import static org.opengis.annotation.Specification.UNSPECIFIED;

import org.opengis.annotation.UML;


/**
 * Capabilities used to convey supported scalar operators.
 *
 * <pre>
 * &lt;xsd:complexType name="Scalar_CapabilitiesType"&lt;
      &lt;xsd:sequence&lt;
         &lt;xsd:element ref="ogc:LogicalOperators"
                      minOccurs="0" maxOccurs="1"/&lt;
         &lt;xsd:element name="ComparisonOperators"
                      type="ogc:ComparisonOperatorsType"
                      minOccurs="0" maxOccurs="1"/&lt;
                      minOccurs="0" maxOccurs="1"/&lt;
      &lt;/xsd:sequence&lt;
   &lt;/xsd:complexType&lt;
 * </pre>
 *
 * @author <a href="mailto:tfr@users.sourceforge.net">Torsten Friebe </a>
 * @author <a href="mailto:mschneider@lat-lon.de">Markus Schneider </a>
 * @author Justin Deoliveira, The Open Planning Project
 */
public interface ScalarCapabilities {
    /**
     * Indicates if logical operator support is provided.
     *
     * <pre>
     * &lt;xsd:element ref="ogc:LogicalOperators" minOccurs="0" maxOccurs="1"/&lt;
     * </pre>
     */
    boolean hasLogicalOperators();

    /**
     * Provided comparison operators.
     *
     * <pre>
     * &lt;xsd:element name="ComparisonOperators" type="ogc:ComparisonOperatorsType" minOccurs="0" maxOccurs="1"/&lt;
     * </pre>
     */
    @UML(identifier="comparisonOperators", specification=UNSPECIFIED)
    ComparisonOperators getComparisonOperators();

    /**
     * Provided arithmetic operators.
     *
     * <pre>
     * &lt;xsd:element name="ComparisonOperators" type="ogc:ComparisonOperatorsType" minOccurs="0" maxOccurs="1"/&lt;
     * </pre>
     */
    @UML(identifier="arithmeticOperators", specification=UNSPECIFIED)
    ArithmeticOperators getArithmeticOperators();
}
