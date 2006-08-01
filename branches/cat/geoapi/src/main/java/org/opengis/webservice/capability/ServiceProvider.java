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
import org.opengis.webservice.SimpleLink;
import org.opengis.metadata.citation.Contact;
import org.opengis.metadata.identification.KeywordType;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Represents the ServiceProvider section of the capabilities of an OGC
 * compliant web service according to the OGC Common Implementation
 * Specification 0.3.
 * 
 * This section corresponds to and expands the SV_ServiceProvider class in ISO
 * 19119.
 * 
 * @author <a href="mailto:mschneider@lat-lon.de">Markus Schneider </a>
 */
public interface ServiceProvider {
    /**
     * Returns the contactInfo.
     */
    @UML(identifier="contactInfo", specification=UNSPECIFIED)
    Contact getContactInfo();

    /**
     * Returns the individualName.
     */
    @UML(identifier="individualName", specification=UNSPECIFIED)
    String getIndividualName();

    /**
     * Returns the positionName.
     */
    @UML(identifier="positionName", specification=UNSPECIFIED)
    String getPositionName();

    /**
     * Returns the providerName.
     */
    @UML(identifier="providerName", specification=UNSPECIFIED)
    String getProviderName();

    /**
     * Returns the providerSite.
     */
    @UML(identifier="providerSite", specification=UNSPECIFIED)
    SimpleLink getProviderSite();

    /**
     * Returns the role.
     */
    KeywordType getRole();
}
