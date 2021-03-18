/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2009-2021 Open Geospatial Consortium, Inc.
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
 * {@linkplain org.opengis.metadata.acquisition.AcquisitionInformation Acquisition information}.
 *
 * <p>Metadata objects are described in the {@linkplain org.opengis.annotation.Specification#ISO_19115_2
 * Metadata extension for imagery and gridded data} specification. The following table shows the class
 * hierarchy, together with a partial view of aggregation hierarchy:</p>
 *
 * <table class="ogc">
 * <caption>Package overview</caption>
 * <tr>
 *   <th>Class hierarchy</th>
 *   <th class="sep">Aggregation hierarchy</th>
 * </tr><tr><td class="hierarchy">
 * <pre> ISO 19115 object
 *  ├─ {@linkplain org.opengis.metadata.acquisition.AcquisitionInformation Acquisition}
 *  ├─ {@linkplain org.opengis.metadata.acquisition.Objective}
 *  ├─ {@linkplain org.opengis.metadata.acquisition.PlatformPass}
 *  ├─ {@linkplain org.opengis.metadata.acquisition.Event}
 *  ├─ {@linkplain org.opengis.metadata.acquisition.Requirement}
 *  ├─ {@linkplain org.opengis.metadata.acquisition.RequestedDate}
 *  ├─ {@linkplain org.opengis.metadata.acquisition.Plan}
 *  ├─ {@linkplain org.opengis.metadata.acquisition.Operation}
 *  ├─ {@linkplain org.opengis.metadata.acquisition.Platform}
 *  ├─ {@linkplain org.opengis.metadata.acquisition.Instrument}
 *  └─ {@linkplain org.opengis.metadata.acquisition.EnvironmentalRecord}
 * {@linkplain org.opengis.util.CodeList}
 *  ├─ {@linkplain org.opengis.metadata.acquisition.ObjectiveType}
 *  ├─ {@linkplain org.opengis.metadata.acquisition.Trigger}
 *  ├─ {@linkplain org.opengis.metadata.acquisition.Context}
 *  ├─ {@linkplain org.opengis.metadata.acquisition.Sequence}
 *  ├─ {@linkplain org.opengis.metadata.acquisition.Priority}
 *  ├─ {@linkplain org.opengis.metadata.acquisition.GeometryType}
 *  └─ {@linkplain org.opengis.metadata.acquisition.OperationType}</pre>
 * </td><td class="sep hierarchy">
 * <pre> {@linkplain org.opengis.metadata.acquisition.AcquisitionInformation}
 *  ├─ {@linkplain org.opengis.metadata.acquisition.Requirement}
 *  │   ├─ {@linkplain org.opengis.metadata.acquisition.RequestedDate}
 *  │   └─ {@linkplain org.opengis.metadata.acquisition.Priority} «code list»
 *  ├─ {@linkplain org.opengis.metadata.acquisition.Objective}
 *  │   ├─ {@linkplain org.opengis.metadata.acquisition.ObjectiveType} «code list»
 *  │   └─ {@linkplain org.opengis.metadata.acquisition.PlatformPass}
 *  │       └─ {@linkplain org.opengis.metadata.acquisition.Event}
 *  │           ├─ {@linkplain org.opengis.metadata.acquisition.Trigger} «code list»
 *  │           ├─ {@linkplain org.opengis.metadata.acquisition.Context} «code list»
 *  │           └─ {@linkplain org.opengis.metadata.acquisition.Sequence} «code list»
 *  ├─ {@linkplain org.opengis.metadata.acquisition.Plan}
 *  │   ├─ {@linkplain org.opengis.metadata.acquisition.GeometryType} «code list»
 *  │   └─ {@linkplain org.opengis.metadata.acquisition.Operation}
 *  │       ├─ {@linkplain org.opengis.metadata.acquisition.OperationType} «code list»
 *  │       └─ {@linkplain org.opengis.metadata.acquisition.Platform}
 *  │           └─ {@linkplain org.opengis.metadata.acquisition.Instrument}
 *  └─ {@linkplain org.opengis.metadata.acquisition.EnvironmentalRecord}</pre>
 * </td></tr></table>
 *
 * @author  Cédric Briançon (Geomatys)
 * @version 4.0
 * @since   2.3
 */
package org.opengis.metadata.acquisition;
