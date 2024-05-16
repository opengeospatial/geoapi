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

/**
 * Validators and test suites for the {@code org.opengis.referencing} package. The validator classes
 * don't need to be used directly (use the static {@link org.opengis.test.Validators} methods instead),
 * unless implementers want to alter the way that referencing objects are validated.
 *
 * <p>This package provides a {@link org.opengis.test.referencing.TransformTestCase} base class
 * with methods for testing {@link org.opengis.referencing.operation.MathTransform} instances.
 * The various {@code TransformTestCase} fields control different aspects of the test to be run,
 * like the {@linkplain org.opengis.test.referencing.TransformTestCase#tolerance tolerance}
 * threshold for comparing coordinate values or whether
 * {@linkplain org.opengis.test.referencing.TransformTestCase#isDerivativeSupported math transform
 * derivatives are supported}.
 * Implementers can extend this class in order to define their own tests.</p>
 *
 * <p>The {@link org.opengis.test.referencing.ObjectFactoryTest},
 * {@link org.opengis.test.referencing.AuthorityFactoryTest},
 * {@link org.opengis.test.referencing.ParameterizedTransformTest} and other concrete classes
 * can be extended by implementers in order to inherit predefined test cases.
 * Many of those test cases are derived from publications of authoritative sources
 * such as EPSG or national mapping agencies.
 * By extending the test classes directly, implementers can control which factories
 * are used and alter the way the tests are performed as documented in the above-cited
 * {@link org.opengis.test.referencing.TransformTestCase} class.</p>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   2.2
 */
package org.opengis.test.referencing;
