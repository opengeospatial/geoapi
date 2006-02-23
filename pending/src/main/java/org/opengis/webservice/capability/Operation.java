// $Header$
/*----------------    FILE HEADER  ------------------------------------------

 This file is part of deegree.
 Copyright (C) 2001-2004 by:
 EXSE, Department of Geography, University of Bonn
 http://www.giub.uni-bonn.de/exse/
 lat/lon GmbH
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
 * Represents the definition of an <code>Operation</code> in the capabilities
 * document of an OGC-web service according to the <code>OWS Common
 * Implementation Specification 0.2</code>
 * (and <code>owsOperationsMetadata.xsd</code>).
 * <p>
 * It consists of a mandatory <code>name</code> attribute and the following
 * elements: <table border="1">
 * <tr>
 * <th>Name</th>
 * <th>Occurences</th>
 * <th>Function</th>
 * </tr>
 * <tr>
 * <td>ows:DCP</td>
 * <td align="center">1-*</td>
 * <td>Unordered list of Distributed Computing Platforms (DCPs) supported for
 * this operation. At present, only the HTTP DCP is defined, so this element
 * will appear only once.</td>
 * </tr>
 * <tr>
 * <td>Parameter</td>
 * <td align="center">0-*</td>
 * <td>Optional unordered list of parameter domains that each apply to this
 * operation which this server implements. If one of these Parameter elements
 * has the same "name" attribute as a Parameter element in the
 * OperationsMetadata element, this Parameter element shall override the other
 * one for this operation. The list of required and optional parameter domain
 * limitations for this operation shall be specified in the Implementation
 * Specification for this service.</td>
 * </tr>
 * <tr>
 * <td>ows:Metadata</td>
 * <td align="center">0-*</td>
 * <td>Optional unordered list of additional metadata about this operation and
 * its' implementation. A list of required and optional metadata elements for
 * this operation should be specified in the Implementation Specification for
 * this service. (Informative: This metadata might specify the operation request
 * parameters or provide the XML Schemas for the operation request.)</td>
 * </tr>
 * </table>
 *
 * @author <a href="mailto:poth@lat-lon.de">Andreas Poth </a>
 * @author <a href="mailto:mschneider@lat-lon.de">Markus Schneider </a>
 */
public interface Operation {
    /**
     * Returns the name of the <code>Operation</code>.
     */
    String getName();

    /**
     * Returns the <code>DCP</code> definitions for the <code>Operation</code>.
     */
    DCPType[] getDCPs();

    /**
     * Returns the specified <code>Parameter</code> value for the
     * <code>Operation</code>.
     * 
     * @param name
     */
    DomainType getParameter(String name);

    /**
     * Returns all <code>Parameters</code> of the <code>Operation</code>.
     */
    DomainType[] getParameters();

    /**
     * Returns the metadata.
     */
    Object[] getMetadata();
}
