/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2005-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.style;

import org.opengis.annotation.XmlElement;
import org.opengis.util.InternationalString;


/**
 * Informative description of a style object being defined.
 * Description values are mostly used in User Interfaces (Lists, trees, ...).
 *
 * <p>Note that most style object also have a name.
 * But the name is not part of the description because a name
 * has a functional use that is more than just descriptive.</p>
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding Implementation Specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 */
@XmlElement("Description")
public interface Description {
    /**
     * Returns the human readable title of the style.
     * This can be any string, but should be fairly short as it is intended to
     * be used in list boxes or drop down menus or other selection interfaces.
     *
     * @return the human readable title of this style.
     */
    @XmlElement("Title")
    InternationalString getTitle();

    /**
     * Returns a human readable, prose description of this style.
     * This can be any string and can consist of any amount of text.
     *
     * @return a human readable, prose description of this style.
     */
    @XmlElement("Abstract")
    InternationalString getAbstract();

    /**
     * calls the visit method of a StyleVisitor
     *
     * @param visitor the style visitor
     */
    Object accept(StyleVisitor visitor, Object extraData);
}
