/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2011 Open Geospatial Consortium, Inc.
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
 * {@linkplain org.opengis.metadata.distribution.Distribution} information.
 * The following is adapted from
 * {@linkplain org.opengis.annotation.Specification#ISO_19115 OpenGIS&reg; Metadata (Topic 11)}
 * specification.
 *
 * <P ALIGN="justify">This package contains information about the distributor of, and options for
 * obtaining, a resource. The optional {@linkplain org.opengis.metadata.distribution.Distribution
 * distribution} entity is an aggregate of the options for the digital distribution of a dataset
 * ({@linkplain org.opengis.metadata.distribution.DigitalTransferOptions digital transfer options}),
 * identification of the {@linkplain org.opengis.metadata.distribution.Distributor distributor} and
 * the {@linkplain org.opengis.metadata.distribution.Format format} of the distribution, which contain
 * mandatory and optional elements. {@linkplain org.opengis.metadata.distribution.DigitalTransferOptions
 * Digital transfer options} contains the {@linkplain org.opengis.metadata.distribution.Medium medium}
 * used for the distribution of a dataset. {@linkplain org.opengis.metadata.distribution.Distributor}
 * is an aggregate of the process for ordering a distribution
 * ({@linkplain org.opengis.metadata.distribution.StandardOrderProcess standard order process}). The
 * {@linkplain org.opengis.metadata.distribution.Distribution#getDistributionFormats distribution format}
 * of {@linkplain org.opengis.metadata.distribution.Distribution distribution} is mandatory if the
 * {@linkplain org.opengis.metadata.distribution.Distributor#getDistributorFormats distribution format}
 * of {@linkplain org.opengis.metadata.distribution.Distributor distributor} is not set.</P>
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @author  Cédric Briançon (Geomatys)
 * @version 3.0
 * @since   2.0
 */
package org.opengis.metadata.distribution;
