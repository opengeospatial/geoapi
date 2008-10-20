/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/

/**
 * Base class for validators and test suites for all GeoAPI objects. The
 * {@link org.opengis.Validators} class provides static {@code validate} methods that can be
 * used for validating existing instances of various kind. Those methods can be conveniently
 * imported in a test class with the following Java statement:
 *
 * <blockquote><code>
 * import static org.opengis.Validators.*;
 * </code></blockquote>
 *
 * <p>No other validator class need to be considered, unless the validation process needs
 * to be customized.</p>
 *
 * @author Martin Desruisseaux (Geomatys)
 * @since GeoAPI 2.2
 */
package org.opengis.test;
