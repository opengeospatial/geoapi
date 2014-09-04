/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2003-2011 Open Geospatial Consortium, Inc.
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
 * A set of base types from {@linkplain org.opengis.annotation.Specification#ISO_19103 ISO 19103}
 * which can not be mapped directly from Java, plus utilities.
 *
 * <h3>Names and Namespaces</h3>
 * <p align="justify">The job of a "name" in the context of ISO 19103 is to associate that name
 * with an {@link java.lang.Object}.  Examples given are <cite>objects</cite>: which form namespaces
 * for their attributes, and <cite>Schema</cite>: which form namespaces for their components.
 * A straightforward and natural use of the namespace structure defined in 19103 is the translation
 * of given names into specific storage formats.  XML has different naming rules than shapefiles,
 * and both are different than NetCDF.  This common framework can easily be harnessed to impose
 * constraints specific to a particular application without requiring that a separate implementation
 * of namespaces be provided for each format.</p>
 *
 * <h3>Records and Schemas</h3>
 * <p align="justify">Records and Schemas are similar to a {@code struct} in C/C++, a table in SQL,
 * a {@code RECORD} in Pascal, or an attribute-only class in Java if it were stripped of all notions
 * of inheritance.  They are organized into named collections called Schemas. Both records and schemas
 * behave as dictionaries for their members and are similar to "packages" in Java.</p>
 *
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @author  Jesse Crossley (SYS Technologies)
 * @author  Bryce Nordgren (USDA)
 * @version 3.0
 * @since   1.0
 */
package org.opengis.util;
