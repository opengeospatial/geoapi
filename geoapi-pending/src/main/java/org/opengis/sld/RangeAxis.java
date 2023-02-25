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

import org.opengis.annotation.XmlElement;

/**
 * RangeAxis describes a range subset defined by a constraining parameter. The name of
 * that parameter matches the name of an AxisDescription element in the range set
 * description of the selected coverage offering. The value is one of the acceptable values
 * defined in the corresponding AxisDescription element.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/sld">Implementation specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
@XmlElement("RangeAxis")
public interface RangeAxis {

    @XmlElement("Name")
    String getName();

    @XmlElement("Value")
    String getValue();

    /**
     * calls the visit method of a SLDVisitor
     *
     * @param visitor the sld visitor
     */
    Object accept(SLDVisitor visitor, Object extraData);

}
