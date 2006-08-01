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
package org.opengis.catalog;

// OpenGIS dependencies
import org.opengis.webservice.WebService;
import org.opengis.webservice.WebServiceException;
import org.opengis.webservice.capability.Capabilities;
import org.opengis.catalog.capability.CatalogGetCapabilities;
import org.opengis.catalog.discovery.Discovery;
import org.opengis.catalog.manager.Manager;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * The Catalogue Service class provides the foundation for an OGC catalogue
 * service. The Catalogue Service class directly includes only the serviceTypeID
 * attribute. In most cases, this attribute will not be directly visible to
 * catalogue clients.
 * <P>
 * The catalog service is an implementation of the OpenGIS Catalogue Service
 * Specification 2.0.
 * 
 * @author <a href="mailto:tfr@users.sourceforge.net">Torsten Friebe </a>
 * @author <a href="mailto:mschneider@lat-lon.de">Markus Schneider </a>
 */
public interface CatalogService extends WebService {
    /**
     * Returns the capabilities of the service.
     *
     * @todo Should the return type be CatalogCapabilities?
     */
    Capabilities getCapabilities();

    /**
     * Returns the capabilities of the service.
     *
     * @todo Should the return type be CatalogCapabilities?
     */
    Capabilities getCapabilities(CatalogGetCapabilities request);

    /**
     * Returns the service type (CSW).
     */
    String getServiceTypeId();

    String getVersion();

    /**
     * Returns the discovery.
     */
    @UML(identifier="discovery", specification=UNSPECIFIED)
    Discovery getDiscovery();

    /**
     * Returns the manager.
     */
    @UML(identifier="manager", specification=UNSPECIFIED)
    Manager getManager();
}
