/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.metadata.extent;


/**
 *
 * @UML datatype EX_Extent
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 * @revisit This interface extent {@link org.opengis.spatialschema.geometry.Envelope} for now,
 *          but this is only a temporary patch in order to have some working API. The real API
 *          will be determined later once we will implement the interface from the ISO
 *          specification.
 */
public interface Extent extends org.opengis.spatialschema.geometry.Envelope {
}
