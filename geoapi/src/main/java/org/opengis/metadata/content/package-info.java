/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2023 Open Geospatial Consortium, Inc.
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

/**
 * Identification of the {@linkplain org.opengis.metadata.content.FeatureCatalogueDescription feature catalogue used},
 * or description of the {@linkplain org.opengis.metadata.content.CoverageDescription content of a coverage} dataset.
 * Both description entities are subtypes of the {@linkplain org.opengis.metadata.content.ContentInformation
 * content information} type. The coverage description may be specialized as
 * {@linkplain org.opengis.metadata.content.ImageDescription image description}.
 *
 * <p>Metadata object are described in the {@linkplain org.opengis.annotation.Specification#ISO_19115
 * OpenGIS® Metadata (Topic 11)} specification. The following table shows the class hierarchy,
 * together with a partial view of aggregation hierarchy:</p>
 *
 * <table class="ogc">
 * <caption>Package overview</caption>
 * <tr>
 *   <th>Class hierarchy</th>
 *   <th class="sep">Aggregation hierarchy</th>
 * </tr><tr><td class="hierarchy">
 * <pre> ISO 19115 object
 *  ├─ {@linkplain org.opengis.metadata.content.ContentInformation} «abstract»
 *  │   ├─ FeatureCatalogue
 *  │   ├─ {@linkplain org.opengis.metadata.content.FeatureCatalogueDescription}
 *  │   └─ {@linkplain org.opengis.metadata.content.CoverageDescription}
 *  │       └─ {@linkplain org.opengis.metadata.content.ImageDescription}
 *  ├─ {@linkplain org.opengis.metadata.content.FeatureTypeInfo}
 *  ├─ {@linkplain org.opengis.metadata.content.RangeDimension}
 *  │   └─ {@linkplain org.opengis.metadata.content.SampleDimension}
 *  │       └─ {@linkplain org.opengis.metadata.content.Band}
 *  └─ {@linkplain org.opengis.metadata.content.RangeElementDescription}
 * {@linkplain org.opengis.util.CodeList}
 *  ├─ {@linkplain org.opengis.metadata.content.BandDefinition}
 *  ├─ {@linkplain org.opengis.metadata.content.CoverageContentType}
 *  ├─ {@linkplain org.opengis.metadata.content.ImagingCondition}
 *  ├─ {@linkplain org.opengis.metadata.content.PolarisationOrientation}
 *  └─ {@linkplain org.opengis.metadata.content.TransferFunctionType}</pre>
 * </td><td class="sep hierarchy">
 * <pre> {@linkplain org.opengis.metadata.content.ContentInformation} «abstract»
 * {@linkplain org.opengis.metadata.content.FeatureCatalogueDescription}
 *  └─ {@linkplain org.opengis.metadata.content.FeatureTypeInfo}
 * {@linkplain org.opengis.metadata.content.CoverageDescription}
 *  ├─ {@linkplain org.opengis.metadata.content.AttributeGroup}
 *  │   ├─ {@linkplain org.opengis.metadata.content.CoverageContentType} «code list»
 *  │   └─ {@linkplain org.opengis.metadata.content.RangeDimension}
 *  └─ {@linkplain org.opengis.metadata.content.RangeElementDescription}
 * {@linkplain org.opengis.metadata.content.Band}
 *  ├─ {@linkplain org.opengis.metadata.content.BandDefinition} «code list»
 *  ├─ {@linkplain org.opengis.metadata.content.PolarisationOrientation} «code list»
 *  └─ {@linkplain org.opengis.metadata.content.TransferFunctionType} «code list»
 * {@linkplain org.opengis.metadata.content.ImageDescription}
 *  └─ {@linkplain org.opengis.metadata.content.ImagingCondition} «code list»</pre>
 * </td></tr></table>
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @author  Cédric Briançon (Geomatys)
 * @version 4.0
 * @since   2.0
 */
package org.opengis.metadata.content;
