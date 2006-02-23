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


/**
 * Represents a configuration for an OGC-Webservice according to the OWS Common
 * Implementation Specification 0.2, i.e. it consists of the following parts:
 * <ul>
 * <li>ServiceIdentification (corresponds to and expands the
 * SV_ServiceIdentification class in ISO 19119)
 * <li>ServiceProvider (corresponds to and expands the SV_ServiceProvider class
 * in ISO 19119)
 * <li>OperationsMetadata (contains set of Operation elements that each
 * corresponds to and expand the SV_OperationsMetadata class in ISO 19119)
 * <li>Contents (whenever relevant, contains set of elements that each
 * corresponds to the MD_DataIdentification class in ISO 19119 and 19115)
 * </ul>
 * 
 * @author <a href="mailto:mschneider@lat-lon.de">Markus Schneider </a>
 */
public interface CommonCapabilities extends Capabilities {
    /**
     * Returns the contents.
     */
    Contents getContents();

    /**
     * Returns the operationsMetadata.
     */
    OperationsMetadata getOperationsMetadata();

    /**
     * Returns the serviceIdentification.
     */
    ServiceIdentification getServiceIdentification();

    /**
     * Returns the serviceProvider.
     */
    ServiceProvider getServiceProvider();
}
