/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2011-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.test.referencing.gigs;


/**
 * Interpretation of the evaluation results of geospatial aspects of the software.
 * Different software packages may have implemented functionality and reference data
 * relating to geospatial integrity in different ways. In order to make a meaningful
 * comparison possible, this package distinguishes four levels of compliance.
 *
 * <blockquote><b>Source:</b> Documentation in this class is derived from the
 * <a href="http://www.iogp.org/pubs/430-1.pdf">GIGS 430-1</a> document, focusing
 * on the requirements that can be verified programmatically by this test suite.
 * See the original GIGS document for the full list of requirements.</blockquote>
 *
 * The {@linkplain #BRONZE bronze}, {@linkplain #SILVER silver} and {@linkplain #GOLD gold}
 * compliance levels are progressively inclusive, in the sense that the silver level implies
 * compliance to the bronze level and gold implies that the silver level is also achieved.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public enum Classification {
    /**
     * Intended for software without the capability of performing coordinate operations.
     * The software does handle spatial data but has no "geodetic engine" that performs
     * coordinate conversions and transformations.
     *
     * <ul>
     *   <li><b>Completeness:</b><ul>
     *     <li>Coordinates are complete, unambiguously defined and are appended with a CRS
     *         identification, specified as a unique reference of the CRS in an (external)
     *         geodetic library or dataset.</li>
     *   </ul></li>
     *   <li><b>Correctness:</b><ul>
     *     <li>Merging and co-visualisation of spatial datasets referenced to different CRSs
     *         in the software’s workspace with imported data are blocked.</li>
     *     <li>The CRS identification is appended to any exported data.</li>
     *   </ul></li>
     *   <li><b>Verifiability:</b><ul>
     *     <li>CRS reference of a geoscience dataset can be inspected at any time during usage
     *         of the software.</li>
     *   </ul></li>
     * </ul>
     */
    ELEMENTARY,

    /**
     * Intended for software with a limited capability to perform coordinate operations.
     * Geospatial integrity is only maintained within the bounds of the software.
     * Integration or interaction with other geosciances software is likely to lead to
     * loss of geospatial integrity.
     *
     * <ul>
     *   <li><b>Completeness:</b><ul>
     *     <li>Coordinates have a complete and correct CRS identification, updated when
     *         transformed/converted within the software.</li>
     *     <li>The software has an internal geodetic library with correct geodetic parameters
     *         within the confines of the software.</li>
     *   </ul></li>
     *   <li><b>Correctness:</b><ul>
     *     <li>Some methods of the test dataset are covered, transformation and conversion
     *         results are correct as per test dataset criteria.</li>
     *   </ul></li>
     *   <li><b>Verifiability:</b><ul>
     *     <li>CRS reference of spatial datasets and the contents of the geodetic library
     *         can be inspected at any time.</li>
     *     <li>The coordinate transformation applied to any data is identifiable and can be
     *         inspected by the user.</li>
     *   </ul></li>
     * </ul>
     */
    BRONZE,

    /**
     * Intended for software with a full capability to perform coordinate operations.
     * This level indicates that the software establishes and maintains geospatial
     * integrity to a fully satisfactory degree, based on industry best practices.
     * The software is suitable for global deployment in the E&amp;P industry.
     *
     * <ul>
     *   <li><b>Completeness:</b><ul>
     *     <li>Geodetic library consists of (a complete and consistent subset of) the EPSG dataset.
     *         Support of other industry standards and best practices may be software scope-dependent.</li>
     *     <li>All methods from test dataset are supported.</li>
     *     <li>EPSG data model has been partly implemented; some variations are acceptable, e.g.
     *         "early binding" model.</li>
     *     <li>Concept of "deprecation" is supported.</li>
     *   </ul></li>
     *   <li><b>Correctness:</b><ul>
     *     <li>User can define new geodetic entities.</li>
     *     <li>Geodetic entities are protected by a system of user privileges.</li>
     *   </ul></li>
     *   <li><b>Verifiability:</b><ul>
     *     <li>Audit trail is available to user enabling verification of Original CRS of data and
     *         coordinate transformation applied to Project CRS.</li>
     *   </ul></li>
     * </ul>
     */
    SILVER,

    /**
     * Intended for software with an extensive capability to perform coordinate operations.
     * This level indicates software performance that exceeds the geospatial integrity capabilities
     * of the silver level by incorporating additional software features that expand the range
     * of applicability and/or reduce the probability of geospatial integrity violations.
     *
     * <ul>
     *   <li><b>Completeness:</b><ul>
     *     <li>Geodetic library consists of (a complete and consistent subset of) the EPSG dataset.</li>
     *     <li>Availability of metadata fields from EPSG dataset other than "deprecation".</li>
     *     <li>More operation methods are supported than are provided in the test dataset.</li>
     *     <li>Late-binding mechanism for coordinate transformations, supported by assistance to
     *         end user to reduce transformation multiplicity, or supported by a software tool
     *         facilitating selection of correct transformation.</li>
     *   </ul></li>
     *   <li><b>Correctness:</b><ul>
     *     <li>EPSG data model has been implemented.</li>
     *     <li>Functionality for following deprecation trail is present.</li>
     *     <li>(Semi-) automatic synchronisation with new releases of EPSG dataset.</li>
     *   </ul></li>
     *   <li><b>Verifiability:</b><ul>
     *     <li>Extensive audit trail available to user enabling verification of Original CRS of
     *         data and coordinate transformation applied to Project CRS.</li>
     *   </ul></li>
     * </ul>
     */
    GOLD
}
