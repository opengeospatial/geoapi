// $Header:
// /cvsroot/deegree/src/org/deegree/ogcwebservices/getcapabilities/Contents.java,v
// 1.1 2004/06/23 11:55:40 mschneider Exp $
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
package org.opengis.filter.capability;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Specification.*;


/**
 * FilterCapabilitiesBean used to represent
 * {@code Filter} expressions according to the
 * 1.0.0 as well as the 1.1.1 <code>Filter Encoding Implementation Specification</code>.
 *
 * @author <a href="mailto:tfr@users.sourceforge.net">Torsten Friebe</a>
 * @author <a href="mailto:mschneider@lat-lon.de">Markus Schneider</a>
 */
public interface FilterCapabilities {

	/** Version String for Filter 1.0 specification */
    public String VERSION_100 = "1.0.0";
	/** Version String for Filter 1.1 specification */
    public String VERSION_110 = "1.1.0";
	/** Version String for Filter 2.0 specification */
    public String VERSION_200 = "2.0.0";

    /**
     *
     */
    @UML(identifier="scalarCapabilities", specification=UNSPECIFIED)
    ScalarCapabilities getScalarCapabilities();

    /**
     *
     */
    @UML(identifier="spatialCapabilities", specification=UNSPECIFIED)
    SpatialCapabilities getSpatialCapabilities();

    /**
     *
     */
    @UML(identifier="temporalCapabilities", specification=UNSPECIFIED)
    TemporalCapabilities getTemporalCapabilities();

    /**
     *
     */
    @UML(identifier="idCapabilities", specification=UNSPECIFIED)
    IdCapabilities getIdCapabilities();

    /**
     * Returns the version.
     */
    String getVersion();
}
