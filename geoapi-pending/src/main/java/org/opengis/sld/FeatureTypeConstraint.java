/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2008-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.sld;

import java.util.List;
import org.opengis.annotation.XmlElement;
import org.opengis.util.GenericName;
import org.opengis.filter.Filter;

/**
 * A FeatureTypeConstraint element is used to identify a feature type by a well-known
 * name, using the FeatureTypeName element. Any positive number of
 * FeatureTypeConstraints may be used to define the features of a layer, though all
 * FeatureTypeConstraints in a UserLayer must originate from the same WFS source.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/sld">Implementation specification 1.1.0</A>
 *
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
public interface FeatureTypeConstraint extends Constraint{

    /**
     * FeatureType name.
     */
    @XmlElement("FeatureTypeName")
    public GenericName getFeatureTypeName();

    /**
     * Filter to apply on feature collection.
     */
    @XmlElement("Filter")
    public Filter getFilter();

    @XmlElement("Extent")
    public List<Extent> getExtent();

    /**
     * calls the visit method of a SLDVisitor
     *
     * @param visitor the sld visitor
     */
    Object accept(SLDVisitor visitor, Object extraData);

}
