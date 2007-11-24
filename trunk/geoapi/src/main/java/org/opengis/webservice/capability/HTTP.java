/*----------------    FILE HEADER  ------------------------------------------

 This file is part of deegree.
 Copyright (C) 2001 by:
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
package org.opengis.webservice.capability;

// J2SE direct dependencies
import java.net.URL;


/**
 * The HTTP-Protocol.
 *
 * @author <a href="mailto:k.lupp@web.de">Katharina Lupp </a>
 * @author <a href="mailto:mschneider@lat-lon.de">Markus Schneider </a>
 */
public interface HTTP extends Protocol {
    /**
     * Return the list of online resources for the HTTP GET request. An Online
     * Resource URL intended for HTTP GET requests, is a URL prefix to which
     * additional parameters must be appended in order to construct a valid
     * Operation request. A URL prefix is defined as an opaque string including
     * the protocol, hostname, optional port number, path, a question mark '?',
     * and, optionally, one or more server-specific parameters ending in an
     * ampersand '&'.
     *
     */
    URL[] getGetOnlineResources();

    /**
     * Return the list of online resources for the HTTP GET request. An Online
     * Resource URL intended for HTTP POST requests is a complete and valid URL
     * to which Clients transmit encoded requests in the body of the POST
     * document.
     */
    URL[] getPostOnlineResources();
}
