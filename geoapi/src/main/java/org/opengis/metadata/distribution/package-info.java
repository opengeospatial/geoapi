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
 * Information concerning the distributor of, and options for obtaining, a resource.
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
 *  ├─ {@linkplain org.opengis.metadata.distribution.Distribution}
 *  ├─ {@linkplain org.opengis.metadata.distribution.Distributor}
 *  ├─ {@linkplain org.opengis.metadata.distribution.Medium}
 *  ├─ {@linkplain org.opengis.metadata.distribution.Format}
 *  ├─ {@linkplain org.opengis.metadata.distribution.StandardOrderProcess}
 *  ├─ {@linkplain org.opengis.metadata.distribution.DigitalTransferOptions}
 *  └─ {@linkplain org.opengis.metadata.distribution.DataFile}
 * {@linkplain org.opengis.util.CodeList}
 *  ├─ {@linkplain org.opengis.metadata.distribution.MediumName}
 *  └─ {@linkplain org.opengis.metadata.distribution.MediumFormat}</pre>
 * </td><td class="sep hierarchy">
 * <pre> {@linkplain org.opengis.metadata.distribution.Distribution}
 *  ├─ {@linkplain org.opengis.metadata.distribution.Format}
 *  ├─ {@linkplain org.opengis.metadata.distribution.Distributor}
 *  │   └─ {@linkplain org.opengis.metadata.distribution.StandardOrderProcess}
 *  └─ {@linkplain org.opengis.metadata.distribution.DigitalTransferOptions}
 *      └─ {@linkplain org.opengis.metadata.distribution.Medium}
 *          ├─ {@linkplain org.opengis.metadata.distribution.MediumName} «code list»
 *          └─ {@linkplain org.opengis.metadata.distribution.MediumFormat} «code list»
 * {@linkplain org.opengis.metadata.distribution.DataFile}</pre>
 * </td></tr></table>
 *
 * <p>The {@link org.opengis.metadata.distribution.Distribution#getDistributionFormats() Distribution.distributionFormat} element is mandatory
 * if the {@link org.opengis.metadata.distribution.Distributor#getDistributorFormats() Distributor.distributorFormat} is empty, and conversely.</p>
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @author  Cédric Briançon (Geomatys)
 * @version 3.1
 * @since   2.0
 */
package org.opengis.metadata.distribution;
