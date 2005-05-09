//$Header$
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
import org.opengis.webservice.WebServiceException;


/**
 * Thrown if a given name is not a known <code>Operator</code>.
 * 
 * @author <a href="mailto:mschneider@lat-lon.de">Markus Schneider</a>
 */
public class UnknownOperatorNameException extends WebServiceException {
    /**
     * Serial number for compatibility with different versions.
     */
//    private static final long serialVersionUID = **TODO**;

    public UnknownOperatorNameException(String message) {
        super(message);
    }
}

