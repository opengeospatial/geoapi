/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.annotation;

import java.lang.annotation.Target;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;


/**
 * If a method is part of a UML association, the association name. Annotated methods shall expect
 * no argument. The association source is the enclosing interface. The association target is
 * inferred from the return type.
 *
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.1
 */
@Documented
@Target(METHOD)
@Retention(SOURCE)
public @interface Association {
    /**
     * Association name.
     */
    String value();
}
