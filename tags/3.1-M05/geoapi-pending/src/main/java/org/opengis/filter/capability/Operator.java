// $Header:
// /cvsroot/deegree/src/org/deegree/ogcwebservices/getcapabilities/Contents.java,v
// 1.1 2004/06/23 11:55:40 mschneider Exp $
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
package org.opengis.filter.capability;

// Annotations
import static org.opengis.annotation.Specification.UNSPECIFIED;

import org.opengis.annotation.UML;

/**
 * Indicates a supported Operator.
 * <p>
 * The operator that is supported is indicated by the getName() field, these
 * names are formally defined to match:
 * <ul>
 * <li>A subclass of Filter. Examples include "BBOX" and "EqualsTo"
 * <li>A subclass of Expression or Function. Examples include "ADD" and "Length"
 * </ul>
 * Each filter subclass has an associated name (such as BBOX or EqualsTo), you
 * can use this name to determine if a matching Operator is defined as part of
 * FilterCapabilities.
 * 
 * @author <a href="mailto:tfr@users.sourceforge.net">Torsten Friebe</A>
 * @author Jody Garnett (Refractions Research)
 * @todo Which relationship with Filter and expressions?
 */
public interface Operator {	
    /**
     * Name of supported Operator.
     * <p>
     * Each filter subclass has an associated name (such as BBOX or EqualsTo), you
     * can use this name to determine if a matching Operator is defined as part of
     * FilterCapabilities. 
     */
    @UML(identifier="name", specification=UNSPECIFIED)
    String getName();
    
    /**
     * The supported interface enabled by this Operator.
     * <p>
     * The mapping from getName() to supported interface is formally defined; and
     * is must agree with the interfaces defined in org.opengis.filter. Because this
     * binding is formal we should replace Operator here with a CodeList and capture
     * it as part of the GeoAPI project.
     * </p>
     * @return Interface marked as supported by this Operator
     */
    //Class getSupportedType();
    
    /**
     * Equals should be implemented simply in terms of getName()
     */
    @Override
    boolean equals(Object obj);
    /**
     * HashCode should be implemented simply in terms of getName().
     */
    @Override
    int hashCode();
}
