/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.util;

// Direct J2SE dependencies
import java.util.Locale;


/**
 * A {@linkplain String string} that has been internationalized into several {@linkplain Locale locales}.
 * This interface is used as a replacement for the {@link String} type whenever an attribute needs to be
 * internationalization capable.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 * @see javax.xml.registry.infomodel.InternationalString
 *
 * @revisit Should extends {@link CharSequence} if we are allowed to depends upon J2SE 1.4.
 */
public interface InternationalString {
    /**
     * Returns this string in the given locale. If no string is available in the given locale,
     * then some default locale is used. The default locale is implementation-dependent. It
     * may or may not be the {@linkplain Locale#getDefault() system default}).
     *
     * @param  locale The desired locale for the string to be returned, or <code>null</code>
     *         for a string in the implementation default locale.
     * @return The string in the given locale if available, or in the default locale otherwise.
     */
    String toString(Locale locale);

    /**
     * Returns this string in the implementation default locale. Invoking this method is
     * equivalent to invoking <code>{@linkplain #toString(Locale) toString}(null)</code>.
     *
     * @return The string in the implementation default locale.
     */
    String toString();
}
