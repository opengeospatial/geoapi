// $Header: /cvsroot/deegree/src/org/deegree/ogcwebservices/OGCWebService.java,v
// 1.7 2004/06/23 13:37:40 mschneider Exp $
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
package org.opengis.webservice;

import org.opengis.webservice.WebServiceEvent;
import org.opengis.webservice.capability.Capabilities;


/**
 * @author <a href="mailto:poth@lat-lon.de">Andreas Poth </a>
 */
public interface WebService {
    /**
     * Returns the capabilities of a web service
     */
    Capabilities getCapabilities();

    /**
     * Performs the handling of the passed WebServiceEvent directly and returns
     * the result to the calling class/method
     *
     * @param request request (WMS, WCS, WFS, CSW, WFS-G) to perform
     *
     * @throws WebServiceException
     */
    Object doService(WebServiceRequest request) throws WebServiceException;

    /**
     * Performs the handling of the passed WebServiceEvent in an new own Thread.
     * The receiver of the response to the request must implement the
     * WebServiceClient interface.
     *
     * @param event event containing request (WMS, WCS, WFS, CSW, WFS-G) to perform
     *
     * @throws WebServiceException
     *
     * @deprecated The WebServiceEvent class is marked as deprecated.
     */
    void doService(WebServiceEvent event) throws WebServiceException;
}
