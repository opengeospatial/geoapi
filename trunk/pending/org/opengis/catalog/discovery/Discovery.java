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
package org.opengis.catalog.discovery;

// OpenGIS direct dependencies
import org.opengis.webservice.WebServiceClient;


/**
 * Allows clients to discover resources registered in a
 * catalogue, by providing four operations named <code>query</code>,
 * <code>present</code>,<code>describeRecordType</code>, and
 * <code>getDomain</code>. This class has a required association from the
 * Catalogue Service class, and is thus always implemented by all Catalogue
 * Service implementations. The Session class can be included with the Discovery
 * class, in associations with the Catalogue Service class. The
 * &quote;query&quote; and &quote;present&quote; operations may be executed in a
 * session or stateful context. If a session context exists, the dynamic model
 * uses internal states of the session and the allowed transitions between
 * states. When the &quote;query&quote; and &quote;present&quote; state does not
 * include a session between a server and a client, any memory or shared
 * information between the client and the server may be based on private
 * understandings or features available in the protocol binding. The
 * describeRecordType and getDomain operations do not require a session context.
 * 
 * @author <a href="mailto:poth@lat-lon.de">Andreas Poth </a>
 * @author <a href="mailto:tfr@users.sourceforge.net">Torsten Friebe </a>
 */
public interface Discovery extends WebServiceClient {
    /**
     * Performs the submitted <code>DescribeRecord</code> -request.
     * 
     * TODO: Check output schema & Co.
     */
//    DescribeRecordResult describeRecordType(DescribeRecord request);

//    DomainValues getDomain(GetDomain request);

//    GetRecordsResult query(GetRecords getRecords);
}
