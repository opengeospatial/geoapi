//$Header$
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

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * base interface for capabilities of any OGC service instance.
 * 
 * @author <a href="mailto:mschneider@lat-lon.de">Markus Schneider </a>
 * @author <a href="mailto:poth@lat-lon.de">Andreas Poth </a>
 */
public interface Capabilities {
    /**
     * Returns the version.
     */
    @UML(identifier="version", specification=UNSPECIFIED)
    String getVersion();

    /**
     * Returns the updateSequence.
     * The UPDATESEQUENCE parameter is for maintaining cache consistency. Its
     * value can be an integer, a timestamp in [ISO 8601:1988(E)] format, or
     * any other number or string. The server may include an UpdateSequence
     * value in its Capabilities XML. If present, this value should be increased
     * when changes are made to the Capabilities (e.g., when new maps are added
     * to the service). The server is the sole judge of lexical ordering
     * sequence. The client may include this parameter in its GetCapabilities
     * request.
     */
    @UML(identifier="updateSequence", specification=UNSPECIFIED)
    String getUpdateSequence();
}
