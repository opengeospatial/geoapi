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

// J2SE direct dependencies
import java.util.Map;


/**
 * Base interface for all request on OGC Web Services (OWS). Each
 * class that capsulates a request against an OWS has to implements this
 * interface.
 *
 * @author <a href="mailto:poth@lat-lon.de">Andreas Poth</a>
 */
public interface WebServiceRequest {
    /**
     * Finally, the requests allow for optional vendor-specific parameters (VSPs)
     * that will enhance the results of a request. Typically, these are used for
     * private testing of non-standard functionality prior to possible
     * standardization. A generic client is not required or expected to make use
     * of these VSPs.
     */
    Map<String,String> getVendorSpecificParameters();

    /**
     * Returns the ID of a request.
     */
    String getId();

    /**
     * Returns the requested service version.
     */
    String getVersion();

    /**
     * Returns the name of the service that is tagerted by a request.
     */
    String getServiceName();

    /**
     * Returns the URI of a HTTP GET request. If the request doesn't support
     * HTTP GET a <tt>WebServiceException</tt> will be thrown
     *
     * @deprecated should be replaced by a factory class.
     */
    String getRequestParameter() throws WebServiceException;
}
