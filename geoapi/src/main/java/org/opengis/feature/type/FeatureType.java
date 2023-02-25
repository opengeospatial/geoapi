/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2006-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.feature.type;


/**
 * <b>Deprecated</b> — {@code FeatureType} is now defined in the {@link org.opengis.feature} package.
 *
 * <div class="warning"><b>Upcoming API change</b><br>
 * This interface will be removed after {@link org.opengis.metadata.maintenance.ScopeDescription} methods
 * changed their return type to {@code CharSequence}, as implied by the new ISO 19115:2014 standard.
 * </div>
 */
public interface FeatureType extends CharSequence {
}
