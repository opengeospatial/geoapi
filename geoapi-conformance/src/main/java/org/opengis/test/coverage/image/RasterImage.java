/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2012-2023 Open Geospatial Consortium, Inc.
 *    http://www.geoapi.org
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
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
@SuppressWarnings("strictfp")   // Because we still target Java 11.
final strictfp class RasterImage implements RenderedImage {
    /**
     * The wrapped raster.
     */
    private final Raster raster;

    /**
     * Creates a new image wrapping the given raster.
     *
     * @param raster  the wrapped raster.
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
     *
     * @param  tileX the X index of the requested tile.
     * @param  tileY the Y index of the requested tile.
     * @return the tile at the specified indices.
     */
    @Override
    public Raster getTile(final int tileX, final int tileY) {
        if (tileX != 0 || tileY != 0) {
            throw new IndexOutOfBoundsException();
        }
        return raster;
    }

    /**
     * {@return the wrapped raster}. This method does <strong>not</strong> copy the raster data,
     * which is a violation of {@link RenderedImage} contract. However, this is not a problem
     * for the purpose of the GeoAPI tests suite.
     */
    @Override
    public Raster getData() {
        return raster;
    }

    /**
     * Unsupported operation (not needed for the GeoAPI tests suite).
     *
     * @param  rect  ignored.
     * @return never return.
     */
    @Override
    public Raster getData(final Rectangle rect) {
        throw new UnsupportedOperationException();
    }

    /**
     * Unsupported operation (not needed for the GeoAPI tests suite).
     *
     * @param  raster  ignored.
     * @return never return.
     */
    @Override
    public WritableRaster copyData(WritableRaster raster) {
        throw new UnsupportedOperationException();
    }
}
