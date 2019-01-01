/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2012-2019 Open Geospatial Consortium, Inc.
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
package org.opengis.test.coverage.image;

import java.util.Vector;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.Raster;
import java.awt.image.ColorModel;
import java.awt.image.SampleModel;
import java.awt.image.RenderedImage;
import java.awt.image.WritableRaster;


/**
 * Wraps a {@link Raster} in a {@link RenderedImage} interface. This image has no color model.
 * While unusual, this is allowed by the {@link RenderedImage} interface.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final strictfp class RasterImage implements RenderedImage {
    /**
     * The wrapped raster.
     */
    private final Raster raster;

    /**
     * Creates a new image wrapping the given raster.
     */
    RasterImage(final Raster raster) {
        this.raster = raster;
    }

    @Override public Vector<RenderedImage> getSources() {return null;}
    @Override public Object      getProperty(String n)  {return Image.UndefinedProperty;}
    @Override public String[]    getPropertyNames()     {return null;}
    @Override public ColorModel  getColorModel()        {return null;}
    @Override public SampleModel getSampleModel()       {return raster.getSampleModel();}
    @Override public int         getWidth()             {return raster.getWidth();}
    @Override public int         getHeight()            {return raster.getHeight();}
    @Override public int         getMinX()              {return raster.getMinX();}
    @Override public int         getMinY()              {return raster.getMinY();}
    @Override public int         getNumXTiles()         {return 1;}
    @Override public int         getNumYTiles()         {return 1;}
    @Override public int         getMinTileX()          {return 0;}
    @Override public int         getMinTileY()          {return 0;}
    @Override public int         getTileWidth()         {return raster.getWidth();}
    @Override public int         getTileHeight()        {return raster.getHeight();}
    @Override public int         getTileGridXOffset()   {return raster.getMinX();}
    @Override public int         getTileGridYOffset()   {return raster.getMinY();}

    /**
     * Returns the raster if the given index is (0,0).
     * For any other index, thrown an exception.
     */
    @Override
    public Raster getTile(final int tileX, final int tileY) {
        if (tileX != 0 || tileY != 0) {
            throw new IndexOutOfBoundsException();
        }
        return raster;
    }

    /**
     * Returns the wrapped raster. This method does <strong>not</strong> copy the raster data,
     * which is a violation of {@link RenderedImage} contract. However this is not a problem
     * for the purpose of the GeoAPI tests suite.
     */
    @Override
    public Raster getData() {
        return raster;
    }

    /**
     * Unsupported operation (not needed for the GeoAPI tests suite).
     */
    @Override
    public Raster getData(final Rectangle rect) {
        throw new UnsupportedOperationException();
    }

    /**
     * Unsupported operation (not needed for the GeoAPI tests suite).
     */
    @Override
    public WritableRaster copyData(WritableRaster raster) {
        throw new UnsupportedOperationException();
    }
}
