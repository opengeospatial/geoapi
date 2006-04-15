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

// OpenGIS direct dependencies
import org.opengis.webservice.Description;
import org.opengis.webservice.MetadataLink;
import org.opengis.webservice.WebServiceException;
import org.opengis.metadata.constraint.Constraints;
import org.opengis.metadata.identification.Keywords;
import org.opengis.metadata.citation.ResponsibleParty;
import org.opengis.util.InternationalString;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Represents the <code>ServiceIdentification</code> section of the
 * capabilities of an OGC compliant web service according to the
 * <code>OGC Common Implementation Specification 0.2</code>. This section
 * corresponds to and expands the SV_ServiceIdentification class in ISO 19119.
 * <p>
 * It consists of the following elements: <table border="1">
 * <tr>
 * <th>Name</th>
 * <th>Occurences</th>
 * <th>Function</th>
 * </tr>
 * <tr>
 * <td>ServiceType</td>
 * <td align="center">1</td>
 * <td>Useful to provide service type name useful for machine-to-machine
 * communication</td>
 * </tr>
 * <tr>
 * <td>ServiceTypeVersion</td>
 * <td align="center">1-*</td>
 * <td>Useful to provide list of server-supported versions.</td>
 * </tr>
 * <tr>
 * <td>Title</td>
 * <td align="center">1</td>
 * <td>Useful to provide a server title for display to a human.</td>
 * </tr>
 * <tr>
 * <td>Abstract</td>
 * <td align="center">0|1</td>
 * <td>Usually useful to provide narrative description of server, useful for
 * display to a human.</td>
 * </tr>
 * <tr>
 * <td>Keywords</td>
 * <td align="center">0-*</td>
 * <td>Often useful to provide keywords useful for server searching.</td>
 * </tr>
 * <tr>
 * <td>Fees</td>
 * <td align="center">0|1</td>
 * <td>Usually useful to specify fees, or NONE if no fees.</td>
 * </tr>
 * <tr>
 * <td>AccessConstraints</td>
 * <td align="center">0-*</td>
 * <td>Usually useful to specify access constraints, or NONE if no access
 * constraints.</td>
 * </tr>
 * </table>
 * 
 * @author <a href="mailto:mschneider@lat-lon.de">Markus Schneider </a>
 */
public interface ServiceIdentification {
    /**
     * Returns the java representation of the ServiceType-element. In the XML
     * document, this element has the type ows:CodeType.
     *
     * @todo Which kind of code exactly (or CodeList?)
     */
    @UML(identifier="serviceType", specification=UNSPECIFIED)
    org.opengis.util.CodeList getServiceType();

    /**
     * Returns the java representation of the ServiceTypeVersion-elements. In
     * the XML document, these elements have the type ows:VersionType.
     */
    @UML(identifier="serviceTypeVersions", specification=UNSPECIFIED)
    String[] getServiceTypeVersions();

    /**
     * Returns the java representation of the Title-element. In the XML
     * document, this element has the type string.
     */
    @UML(identifier="title", specification=UNSPECIFIED)
    InternationalString getTitle();

    /**
     * Returns the java representation of the Abstract-element. In the XML
     * document, this element has the type string.
     */
    InternationalString getAbstract();

    /**
     * Returns the java representation of the Keywords-elements. In the XML
     * document, these elements have the type ows:Keyword.
     */
    @UML(identifier="keywords", specification=UNSPECIFIED)
    Keywords getKeywords();

    /**
     * Returns the java representation of the AccessConstraints-elements. In the
     * XML document, these elements have the type string.
     */
    @UML(identifier="fees", specification=UNSPECIFIED)
    InternationalString getFees();

    /**
     * Returns the java representation of the AccessConstraints-elements. In the
     * XML document, these elements have the type string.
     */
    @UML(identifier="accessConstraints", specification=UNSPECIFIED)
    Constraints getAccessConstraints();
}
