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
 * The purpose of the GetCapabilities operation is described in the Basic
 * CapabilitiesService Elements section, above. In the particular case of a Web
 * Map CapabilitiesService, the response of a GetCapabilities request is general
 * information about the service itself and specific information about the
 * available maps.
 * 
 * @author <a href="mailto:k.lupp@web.de">Katharina Lupp </a>
 * @author <a href="mailto:tfr@users.sourceforge.net">Torsten Friebe </a>
 */
public interface WebServiceCapabilities extends Capabilities {
    /**
     * Returns a general describtion of the service described by the
     * Capabilities XML document.
     */
    @UML(identifier="service", specification=UNSPECIFIED)
    CapabilitiesService getService();
}
