// $Header$
/*----------------    FILE HEADER  ------------------------------------------

 This file is part of deegree.
 Copyright (C) 2001 by:
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
 lat/lon Fitzke/Fretter/Poth GbR
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
package org.opengis.webservice.capability;


/**
 * Represents the <code>OperationMetadata</code> part in the capabilities
 * document of an OGC-web service according to the
 * <code>OWS Common Implementation
 * Specification 0.3</code> (and especially
 * <code>owsOperationsMetadata.xsd</code>). As this class is abstract, it
 * only defines the <code>GetCapabilities</code> operation, which all types of
 * <code>OWS Common</code> -compliant web services must implement.
 * <p>
 * It consists of the following elements: <table border="1">
 * <tr>
 * <th>Name</th>
 * <th>Occurences</th>
 * <th>Function</th>
 * </tr>
 * <tr>
 * <td>ows:Operation</td>
 * <td align="center">2-*</td>
 * <td>Metadata for unordered list of all the (requests for) operations that
 * this server interface implements. The list of required and optional
 * operations implemented shall be specified in the Implementation Specification
 * for this service.</td>
 * </tr>
 * <tr>
 * <td>ows:Parameter</td>
 * <td align="center">0-*</td>
 * <td>Optional unordered list of parameter valid domains that each apply to
 * one or more operations which this server interface implements. The list of
 * required and optional parameter domain limitations shall be specified in the
 * Implementation Specification for this service.</td>
 * </tr>
 * <tr>
 * <td>ows:Constraint</td>
 * <td align="center">0-*</td>
 * <td>Optional unordered list of valid domain constraints on non-parameter
 * quantities that each apply to this server. The list of required and optional
 * constraints shall be specified in the Implementation Specification for this
 * service.</td>
 * </tr>
 * <tr>
 * <td>ows:ExtendedCapabilities</td>
 * <td align="center">0 or 1</td>
 * <td>Individual software vendors and servers can use this element to provide
 * metadata about any additional server abilities.</td>
 * </tr>
 * </table>
 * 
 * @author <a href="mailto:poth@lat-lon.de">Andreas Poth </a>
 * @author <a href="mailto:mschneider@lat-lon.de">Markus Schneider </a>
 */
public interface OperationsMetadata {
    /**
     * Returns The configuration for the <code>GetCapabilities</code> operation.
     */
    Operation getGetCapabilitiesOperation();

    /**
     * Returns all <code>Operation</code> configurations.
     */
    Operation[] getOperations();

    /**
     * Returns a list of parameters assigned directly to the OperationsMetadata.
     */
    DomainType[] getParameter();

    /**
     * Returns the constraints.
     */
    DomainType[] getConstraints();
}
