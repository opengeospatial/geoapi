/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2019 Open Geospatial Consortium, Inc.
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
 * Type for {@linkplain org.opengis.metadata.citation.Citation citing} a resource and information
 * about the {@linkplain org.opengis.metadata.citation.Responsibility responsible party}.
 * The resource can be a dataset, feature, source, publication, <i>etc</i>.
 * The responsible party contains the identity of person(s), and/or position, and/or organization(s)
 * associated with the resource. The {@linkplain org.opengis.metadata.citation.Address address}
 * contains the location of the responsible person or organization.
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
 * <pre> ISO 19115 object
 *  ├─ {@linkplain org.opengis.metadata.citation.Citation}
 *  ├─ {@linkplain org.opengis.metadata.citation.CitationDate}
 *  ├─ {@linkplain org.opengis.metadata.citation.Responsibility}
 *  ├─ {@linkplain org.opengis.metadata.citation.Party} «abstract»
 *  │   ├─ {@linkplain org.opengis.metadata.citation.Individual}
 *  │   └─ {@linkplain org.opengis.metadata.citation.Organisation}
 *  ├─ {@linkplain org.opengis.metadata.citation.Contact}
 *  ├─ {@linkplain org.opengis.metadata.citation.Telephone}
 *  ├─ {@linkplain org.opengis.metadata.citation.Address}
 *  ├─ {@linkplain org.opengis.metadata.citation.OnlineResource}
 *  └─ {@linkplain org.opengis.metadata.citation.Series}
 * {@linkplain org.opengis.util.CodeList}
 *  ├─ {@linkplain org.opengis.metadata.citation.DateType}
 *  ├─ {@linkplain org.opengis.metadata.citation.OnLineFunction}
 *  ├─ {@linkplain org.opengis.metadata.citation.PresentationForm}
 *  └─ {@linkplain org.opengis.metadata.citation.Role}</pre>
 * </td><td class="sep hierarchy">
 * <pre> {@linkplain org.opengis.metadata.citation.Citation}
 *  ├─ {@linkplain org.opengis.metadata.citation.CitationDate}
 *  │   └─ {@linkplain org.opengis.metadata.citation.DateType} «code list»
 *  ├─ {@linkplain org.opengis.metadata.citation.Responsibility}
 *  │   ├─ {@linkplain org.opengis.metadata.citation.Party} «abstract»
 *  │   │   └─ {@linkplain org.opengis.metadata.citation.Contact}
 *  │   │       ├─ {@linkplain org.opengis.metadata.citation.Telephone}
 *  │   │       ├─ {@linkplain org.opengis.metadata.citation.Address}
 *  │   │       └─ {@linkplain org.opengis.metadata.citation.OnlineResource}
 *  │   │           └─ {@linkplain org.opengis.metadata.citation.OnLineFunction} «code list»
 *  │   └─ {@linkplain org.opengis.metadata.citation.Role} «code list»
 *  ├─ {@linkplain org.opengis.metadata.citation.PresentationForm} «code list»
 *  └─ {@linkplain org.opengis.metadata.citation.Series}</pre>
 * </td></tr></table>
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @version 4.0
 * @since   1.0
 */
package org.opengis.metadata.citation;
