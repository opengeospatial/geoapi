/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2009-2023 Open Geospatial Consortium, Inc.
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

import java.util.Map;
import org.opengis.filter.Expression;

/**
 * An symbolizer interface for all unnormalized symbolizers,
 * This interface should be used for vendor specific symbolizers.
 *
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.3
 */
public interface ExtensionSymbolizer extends Symbolizer {
    
    /**
     * Returns the name of the extension, this name should be commun to all 
     * implementation of a given extension symbolizer sub class.
     * 
     * @return the symbolizer extension name
     */
    String getExtensionName();

    /**
     * Returns a map of all expressions used in this symbolizer. It can be used
     * for analyze purpose but shoudl not be used for XML parsing.
     *
     * @return map of all expressions.
     */
    Map<String,Expression> getParameters();

    /**
     * Calls the visit method of a StyleVisitor
     *
     * @param visitor the style visitor
     */
    Object accept(StyleVisitor visitor, Object extraData);

}
