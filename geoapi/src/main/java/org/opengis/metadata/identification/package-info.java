/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */

/**
 * Information to uniquely identify the data or service.
 * {@linkplain org.opengis.metadata.identification.Identification} information is mandatory
 * and includes information about the citation for the resource, an abstract, the purpose,
 * credit, the status and points of contact. Identification may be specialized as
 * {@linkplain org.opengis.metadata.identification.DataIdentification data identification} when used
 * to identify data, or as {@linkplain org.opengis.metadata.identification.ServiceIdentification service
 * identification} when used to identify a service.
 *
 * <p>Metadata object are described in the {@linkplain org.opengis.annotation.Specification#ISO_19115
 * OpenGIS® Metadata (Topic 11)} specification. The following table shows the class hierarchy,
 * together with a partial view of aggregation hierarchy:</p>
 *
 * <table class="ogc">
 * <caption>Package overview</caption>
 * <tr>
 *   <th>Class hierarchy</th>
 *   <th class="sep">Aggregation hierarchy</th>
 * </tr><tr><td class="hierarchy">
 * <pre>ISO 19115 object
 *  ├─ {@linkplain org.opengis.metadata.identification.Identification} «abstract»
 *  │   ├─ {@linkplain org.opengis.metadata.identification.DataIdentification}
 *  │   └─ {@linkplain org.opengis.metadata.identification.ServiceIdentification}
 *  ├─ {@linkplain org.opengis.metadata.identification.Resolution}
 *  ├─ {@linkplain org.opengis.metadata.identification.BrowseGraphic}
 *  ├─ {@linkplain org.opengis.metadata.identification.Keywords}
 *  ├─ {@linkplain org.opengis.metadata.identification.Usage}
 *  ├─ {@linkplain org.opengis.metadata.identification.AggregateInformation}
 *  ├─ {@linkplain org.opengis.metadata.identification.CoupledResource}
 *  ├─ {@linkplain org.opengis.metadata.identification.OperationMetadata}
 *  └─ {@linkplain org.opengis.metadata.identification.OperationChainMetadata}
 * {@linkplain org.opengis.util.CodeList}
 *  ├─ {@linkplain org.opengis.metadata.identification.Progress}
 *  ├─ {@linkplain org.opengis.metadata.identification.KeywordType}
 *  ├─ {@linkplain org.opengis.metadata.identification.AssociationType}
 *  ├─ {@linkplain org.opengis.metadata.identification.InitiativeType}
 *  ├─ {@linkplain org.opengis.metadata.identification.TopicCategory}
 *  ├─ {@linkplain org.opengis.metadata.identification.CouplingType}
 *  └─ {@linkplain org.opengis.metadata.identification.DistributedComputingPlatform}</pre>
 * </td><td class="sep hierarchy">
 * <pre>{@linkplain org.opengis.metadata.identification.Identification} «abstract»
 *  ├─ {@linkplain org.opengis.metadata.identification.Resolution}
 *  ├─ {@linkplain org.opengis.metadata.identification.TopicCategory} «code list»
 *  ├─ {@linkplain org.opengis.metadata.identification.Progress} «code list»
 *  ├─ {@linkplain org.opengis.metadata.identification.BrowseGraphic}
 *  ├─ {@linkplain org.opengis.metadata.identification.Keywords}
 *  │   └─ {@linkplain org.opengis.metadata.identification.KeywordType} «code list»
 *  ├─ {@linkplain org.opengis.metadata.identification.Usage}
 *  └─ {@linkplain org.opengis.metadata.identification.AssociatedResource}
 *      ├─ {@linkplain org.opengis.metadata.identification.AssociationType} «code list»
 *      └─ {@linkplain org.opengis.metadata.identification.InitiativeType} «code list»
 * {@linkplain org.opengis.metadata.identification.DataIdentification}
 * {@linkplain org.opengis.metadata.identification.ServiceIdentification}
 *  ├─ {@linkplain org.opengis.metadata.identification.CouplingType} «code list»
 *  ├─ {@linkplain org.opengis.metadata.identification.CoupledResource}
 *  ├─ {@linkplain org.opengis.metadata.identification.OperationMetadata}
 *  │   ├─ {@linkplain org.opengis.metadata.identification.DistributedComputingPlatform} «code list»
 *  │   └─ {@linkplain org.opengis.parameter.ParameterDescriptor}
 *  │       └─ {@linkplain org.opengis.parameter.ParameterDirection} «enum»
 *  └─ {@linkplain org.opengis.metadata.identification.OperationChainMetadata}</pre>
 * </td></tr></table>
 *
 * <p>More specifically, {@link org.opengis.metadata.identification.Identification}
 * is an aggregate of the following entities:</p>
 * <ul>
 *   <li>{@link org.opengis.metadata.distribution.Format}
 *       (from the {@linkplain org.opengis.metadata.distribution distribution} package): format of the data.</li>
 *   <li>{@link org.opengis.metadata.identification.BrowseGraphic}: graphic overview of the data.</li>
 *   <li>{@link org.opengis.metadata.identification.Usage}: specific uses of the data.</li>
 *   <li>{@link org.opengis.metadata.constraint.Constraints}
 *       (from the {@linkplain org.opengis.metadata.constraint constraint} package): constraints placed on the resource.</li>
 *   <li>{@link org.opengis.metadata.identification.Keywords}: keywords describing the resource.</li>
 *   <li>{@link org.opengis.metadata.maintenance.MaintenanceInformation}
 *       (from the {@linkplain org.opengis.metadata.maintenance maintenance} package):
 *       how often the data is scheduled to be updated and the scope of the update.</li>
 * </ul>
 *
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @author  Cory Horner (Refractions Research)
 * @author  Ely Conn (Leica Geosystems Geospatial Imaging, LLC)
 * @author  Rémi Maréchal (Geomatys)
 * @version 3.1
 * @since   2.0
 */
package org.opengis.metadata.identification;
