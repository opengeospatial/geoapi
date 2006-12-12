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
import org.opengis.filter.Filter;
import org.opengis.webservice.SortProperty;

// Annotations
// import org.opengis.annotation.UML;
// import static org.opengis.annotation.Obligation.*;
// import static org.opengis.annotation.Specification.*;

/**
 * Main component of a <code>GetRecords</code> request. A
 * <code>GetRecords</code> request may consist of several <code>Query</code>
 * elements.
 * 
 * @author <a href="mailto:mschneider@lat-lon.de">Markus Schneider </a>
 */
public interface Query {
    /**
     * Zero or one (Optional); If <tt>null</tt> then getElementNames may
     * return a list of requested elements. If both methods returns
     * <tt>null</tt> the default action is to present all metadata elements.
     * <p>
     * The ElementName parameter is used to specify one or more metadata record
     * elements that the query should present in the response to the a
     * GetRecords operation. Well known sets of element may be named, in which
     * case the ElementSetName parameter may be used (e. g.brief, summary or
     * full).
     * <p>
     * If neither parameter is specified, then a CSW shall present all metadata
     * record elements
     * <p>
     */
    // @UML(identifier="elementSetName", specification=UNSPECIFIED)
    String getElementSetName();

    /**
     * List of element names returned by a getRecord request.
     */
    String[] getElementsNames();

    /**
     * Zero or one (Optional); Default action is to execute an unconstrained
     * query
     */
    Filter getContraint();

    /**
     * Ordered list of names of metadata elements to use for sorting the
     * response. Format of each list item is metadata_elemen_ name:A indicating
     * an ascending sort or metadata_ element_name:D indicating descending sort
     * <p>
     * The result set may be sorted by specifying one or more metadata record
     * elements upon which to sort.
     * <p>
     * 
     * @todo verify return type URI[] or String
     */
    // @UML(identifier="sortProperties", specification=UNSPECIFIED)
    SortProperty[] getSortProperties();

    /**
     * The typeName parameter specifies the record type name that defines a set
     * of metadata record element names which will be constrained in the
     * predicate of the query. In addition, all or some of the these names may
     * be specified in the query to define which metadata record elements the
     * query should present in the response to the GetRecords operation.
     */
    // @UML(identifier="typeName", specification=UNSPECIFIED)
    String getTypeName();
}
