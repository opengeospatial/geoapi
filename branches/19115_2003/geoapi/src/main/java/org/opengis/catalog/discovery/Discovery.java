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
import org.opengis.catalog.CatalogServiceFailureException;
import org.opengis.catalog.InvalidParameterValueException;
import org.opengis.catalog.MissingParameterValueException;
import org.opengis.catalog.NonexistentTypeException;

/**
 * Allows clients to discover resources registered in a catalogue, by providing
 * four operations named <code>query</code>, <code>present</code>,<code>describeRecordType</code>,
 * and <code>getDomain</code>. This class has a required association from the
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
 * 
 * @author <a href="mailto:poth@lat-lon.de">Andreas Poth </a>
 * @author <a href="mailto:tfr@users.sourceforge.net">Torsten Friebe </a>
 * @author <a href="mailto:mauricio.pazos@axios.es">Mauricio Pazos, Axios
 *         Engineering </a>
 * 
 * @version $Id$
 */
public interface Discovery {
    /**
     * Allows clients to retrieve type definition(s) used by metadata of one or
     * more registered resource types.
     * 
     * @param request
     *            instance of DescribeRecordTypeRequest. Optional
     *            identifications of requested record type(s) and of desired
     *            format
     * 
     * @deprecated Eliminar, no tiene sentido en Java
     * @return DescribeRecordTypeResponse. Type definition document containing
     *         definition(s) of type(s) used by the metadata of one or more
     *         registered resource types. This type definition shall include the
     *         structure (schema), queryables, element sets, and formats of the
     *         metadata used for one or more registered resource types. The
     *         contents of the result of this operation depend on the types of
     *         metadata that can currently be used by registered resources.
     */
    DescribeRecordTypeResponse describeRecordType(DescribeRecordTypeRequest request)
            throws MissingParameterValueException, InvalidParameterValueException,
            NonexistentTypeException;

    /**
     * Allows clients to retrieve the domain (allowed values) of a metadata
     * property or request parameter at the time the request is invoked. The
     * returned information may be static domain information, but may also be
     * dynamic in that the allowed values are determined at runtime. The
     * operation does a best attempt at returning information about a metadata
     * property or request parameter.
     * 
     * @param request
     *            DomainRequest
     * 
     * @return DomainResponse
     */
    DomainResponse getDomain(DomainRequest request) throws CatalogServiceFailureException,
            InvalidParameterValueException, MissingParameterValueException;

    /**
     * Allows clients to retrieve selected metadata for some or all of the
     * resources referenced in a specific previous result set or a list of
     * resource identifiers. This operation can be used repetitively to retrieve
     * more of the result set, each time retrieving metadata for a maximum
     * number of the resources listed, starting at a specified position.
     * <p>
     * <b>Pre-contitions:</b> Client has previously performed ?search?
     * operation, and the server has provided a result set identifier that the
     * client can use to perform the present operation.
     * </p>
     * 
     * <p>
     * <b>Post-contitions:</b> Metadata document returned to requesting client,
     * containing selected metadata for some or all of sorted result set.
     * </p>
     * 
     * @param request
     *            PresentRequest
     * 
     * @return PresentResponse
     * 
     * @throws CatalogServiceFailureException
     * @throws InvalidParameterValueException
     * @throws MissingParameterValueException
     * @throws UnrecognizedCollectionIdentifierException
     */
    PresentResponse present(PresentRequest request) throws CatalogServiceFailureException,
            InvalidParameterValueException, MissingParameterValueException,
            UnrecognizedCollectionIdentifierException;

    /**
     * Allows clients to ask a catalogue to execute a query that searches the
     * catalogued metadata and produces a result set containing (zero or more)
     * references to all the registered resources that satisfy the query. The
     * server may maintain the result set for subsequent retrieval requests.
     * 
     * @param request
     *            instance of QueryRequest. Specifications of query constraints
     *            and of metadata to be returned
     * 
     * @return QueryResponse Number of items in result set, and/or selected
     *         metadata for some or all of the result set. Theclient can specify
     *         the maximum number of records for which metadata is returned.
     *         When metadata return is requested, the service implementation
     *         shall first sort the result set as specified by the client. Most
     *         of the metadata returned depends on the metadata requested and on
     *         the types of data defined by the specific Application Profile.
     * 
     * @throws CatalogServiceFailureException
     * @throws InvalidParameterValueException
     * @throws MissingParameterValueException
     */
    QueryResponse query(QueryRequest request) throws CatalogServiceFailureException,
            InvalidParameterValueException, MissingParameterValueException;
}
