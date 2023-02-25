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
package org.opengis.style;

import org.opengis.annotation.XmlElement;

/**
 * The OverlapBehavior element tells a system how to behave when multiple
 * raster images in a layer  overlap each other, for example with
 * satellite-image scenes. LATEST_ON_TOP and EARLIEST_ON_TOP refer to the
 * time the scene was captured.   AVERAGE means to average multiple scenes
 * together.   This can produce blurry results if the source images are
 * not perfectly aligned in their geo-referencing. RANDOM means to select
 * an image (or piece thereof) randomly and place it on top.  This can
 * produce crisper  results than AVERAGE potentially more efficiently than
 * LATEST_ON_TOP or EARLIEST_ON_TOP.   The default behaviour is
 * system-dependent.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding Implementation Specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
@XmlElement("OverlapBehavior")
public enum OverlapBehavior {
    LATEST_ON_TOP,
    EARLIEST_ON_TOP,
    AVERAGE,
    RANDOM
}
