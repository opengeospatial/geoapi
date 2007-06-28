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
package org.opengis.webservice.feature;

// OpenGIS direct dependencies
import org.opengis.webservice.WebService;
import org.opengis.webservice.WebServiceRequest;
import org.opengis.webservice.WebServiceResponse;
import org.opengis.webservice.capability.WebServiceCapabilities;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Web feature service. A WFS is callable through the {@link #doService doService} method
 * inherited from {@link WebService}. The specification states that only one object of this
 * class is implemented in each server instance, and this object always exists while server
 * is available. (OGC document 03-098, p. 98).
 * 
 * @author Andreas Poth
 * @version $Revision: 708 $ $Date: 2006-03-16 05:12:32 +1100 (jeu., 16 mars 2006) $
 */
public interface WebFeatureService extends WebService {
    /**
     * 
     */
    @UML(identifier="capabilities", specification=UNSPECIFIED)
/// WebServiceCapabilities getCapabilities();

    String getVersion();

    /**
     * Handles a request against an OGC web service.
     */
    void handleRequest(WebServiceRequest request);

    /**
     * Receives the response from the WFSDispatcher. Calling this method an
     * internal flag is set that indicates that the waiting loop can be aborted
     * without an exception.
     */
    void handleResponse(WebServiceResponse response);

    /**
     * Registeres a new DataStore to a WebFeatureService instance
     */
//    void registerDataStore(DataStore dataStore);
}
