/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.cd;


/**
 * Defines the origin of an image coordinate reference system. An image datum is used in a local
 * context only. For an image datum, the anchor point is usually either the centre of the image
 * or the corner of the image.
 *
 * @UML abstract CD_ImageDatum
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
public interface ImageDatum extends Datum {
    /**
     * Specification of the way the image grid is associated with the image data attributes.
     *
     * @return The way image grid is associated with image data attributes.
     * @UML mandatory pixelinCell
     */
    public PixelinCell getPixelinCell();
}
