/*
 *    Geotools2 - OpenSource mapping toolkit
 *    http://geotools.org
 *    (C) 2002, Geotools Project Managment Committee (PMC)
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation;
 *    version 2.1 of the License.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 *
 */
package org.opengis.data;

import java.io.IOException;
import java.util.NoSuchElementException;

import org.opengis.feature.Feature;
import org.opengis.feature.FeatureType;


/**
 * The low-level interface for reading Features. Will use the underlying
 * AttributeReader and the given FeatureType to create new Features.
 * 
 * <p>
 * Typical use is as follows:
 * <pre><code>
 *   FeatureReader reader;
 *   ...
 *   Feature f = null;
 *   while ( reader.hasNext() ) {
 *      f = reader.next();
 * 
 *   }
 * 
 *   reader.close();
 * </code></pre>
 * Questions for Ian:
 * 
 * <ul>
 * <li>
 * Should FeatureReader provide access to the AttributeReaders it uses?
 * </li>
 * <li>
 * FeatureReader has a close method, but no open method? (This is probably by
 * design allowing FeatureReader to encapsulate its InputStream or Rowset). I
 * am going to assume that FeatureReaders are a single use proposition.
 * </li>
 * </ul>
 * </p>
 * 
 * <p>
 * I've modified this to be based on the Iterator pattern as discussed in
 * email. The only question remaining is whether to include skip(int) or not.
 * (SeanG)
 * </p>
 *
 * @author Ian Schneider
 * @author Sean Geoghegan, Defence Science and Technology Organisation.
 * @version $Id$
 */
public interface FeatureReader {
    /**
     * Return the FeatureType this reader has been configured to create.
     *
     * @return the FeatureType of the Features this FeatureReader will create.
     */
    FeatureType getFeatureType();

    /**
     * Reads the next Feature in the FeatureReader.
     *
     * @return The next feature in the reader.
     *
     * @throws IOException If an error occurs reading the Feature.
     * @throws NoSuchElementException If there are no more Features in the
     *         Reader.
     */
    Feature next()
        throws IOException, NoSuchElementException;

    /**
     * Query whether this FeatureReader has another Feature.
     *
     * @return True if there are more Features to be read. In other words, true
     *         if calls to next would return a feature rather than throwing an
     *         exception.
     *
     * @throws IOException If an error occurs determining if there are more
     *         Features.
     */
    boolean hasNext() throws IOException;

    /**
     * Release the underlying resources associated with this stream.
     */
    void close() throws IOException;
}
