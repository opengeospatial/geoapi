/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 */
package org.opengis.openoffice;

import java.util.Map;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.LogRecord;
import java.io.PrintWriter;
import java.io.StringWriter;

import com.sun.star.sheet.XAddIn;
import com.sun.star.lang.Locale;
import com.sun.star.lang.XServiceInfo;
import com.sun.star.lang.XServiceName;
import com.sun.star.lib.uno.helper.WeakBase;


/**
 * Base class for methods to export as formulas in the
 * <a href="http://www.openoffice.org">OpenOffice.org</a> spread sheet.
 *
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @version 3.1
 * @since   3.1
 */
public abstract class Formulas extends WeakBase implements XAddIn, XServiceName, XServiceInfo {
    /**
     * The logger to use for all message to log in this package.
     */
    protected static final Logger LOGGER = Logger.getLogger("org.opengis.openoffice");

    /**
     * Sets to {@code true} for formatting full stack trace in cells in case of errors.
     */
    private static final boolean DEBUG = false;

    /**
     * Informations about exported methods.
     */
    final Map<String,MethodInfo> methods = new HashMap<>();

    /**
     * Locale attribute required by {@code com.sun.star.lang.XLocalizable} interface.
     */
    private Locale locale;

    /**
     * The locale as an object from the standard Java SDK.
     * Will be fetched only when first needed.
     */
    private transient java.util.Locale javaLocale;

    /**
     * Default constructor. Subclass constructors need to add entries in the {@link #methods} map.
     */
    protected Formulas() {
    }

    /**
     * Sets the locale to be used by this object.
     *
     * @param locale  t new OpenOffice.org locale.
     */
    @Override
    public final void setLocale(final Locale locale) {
        this.locale = locale;
        javaLocale = null;
    }

    /**
     * Returns the locale, which is used by this object.
     *
     * @return the current OpenOffice.org locale.
     */
    @Override
    public final Locale getLocale() {
        return locale;
    }

    /**
     * Returns the locale as an object from the Java standard SDK.
     *
     * @return the current locale as a Java object.
     */
    protected final java.util.Locale getJavaLocale() {
        if (javaLocale == null) {
            if (locale != null) {
                String language = locale.Language; if (language == null) language = "";
                String country  = locale.Country;  if (country  == null) country  = "";
                String variant  = locale.Variant;  if (variant  == null) variant  = "";
                javaLocale = new java.util.Locale(language, country, variant);
            } else {
                javaLocale = java.util.Locale.getDefault();
            }
        }
        return javaLocale;
    }

    /**
     * The service name that can be used to create such an object by a factory.
     * This is defined as a field in the subclass with exactly the following signature:
     *
     * <blockquote><code>
     * private static final String __serviceName;
     * </code></blockquote>
     *
     * @return the service name.
     */
    @Override
    public abstract String getServiceName();

    /**
     * Provides the implementation name of the service implementation.
     *
     * @return unique name of the implementation.
     */
    @Override
    public final String getImplementationName() {
        return getClass().getName();
    }

    /**
     * Returns the programmatic name of the category the function belongs to.
     * The category name is used to group similar functions together. The programmatic
     * category name should always be in English, it is never shown to the user. It
     * is usually one of the names listed in {@code com.sun.star.sheet.XAddIn} interface.
     *
     * @param  function  the exact name of a method within its interface.
     * @return the category name the specified function belongs to.
     */
    @Override
    public final String getProgrammaticCategoryName(final String function) {
        final MethodInfo info = methods.get(function);
        return (info != null) ? info.category : "Add-In";
    }

    /**
     * Returns the user-visible name of the category the function belongs to.
     * This is used when category names are shown to the user.
     *
     * @param  function  the exact name of a method within its interface.
     * @return the user-visible category name the specified function belongs to.
     */
    @Override
    public final String getDisplayCategoryName(final String function) {
        return getProgrammaticCategoryName(function);
    }

    /**
     * Returns the internal function name for an user-visible name. The user-visible
     * name of a function is the name shown to the user. It may be translated to the
     * {@linkplain #getLocale current language}, so it is never stored in files. It
     * should be a single word and is used when entering or displaying formulas.
     * <p>
     * Attention: The method name contains a spelling error. Due to compatibility
     * reasons the name cannot be changed.
     *
     * @param  display  the user-visible name of a function.
     * @return the exact name of the method within its interface.
     */
    @Override
    public final String getProgrammaticFuntionName(final String display) {
        for (final Map.Entry<String,MethodInfo> entry : methods.entrySet()) {
            if (display.equals(entry.getValue().display)) {
                return entry.getKey();
            }
        }
        return "";
    }

    /**
     * Returns the user-visible function name for an internal name.
     * The user-visible name of a function is the name shown to the user.
     * It may be translated to the {@linkplain #getLocale current language},
     * so it is never stored in files. It should be a single word and is used
     * when entering or displaying formulas.
     *
     * @param  function  the exact name of a method within its interface.
     * @return the user-visible name of the specified function.
     */
    @Override
    public final String getDisplayFunctionName(final String function) {
        final MethodInfo info = methods.get(function);
        return (info != null) ? info.display : "";
    }

    /**
     * Returns the description of a function. The description is shown to the user
     * when selecting functions. It may be translated to the {@linkplain #getLocale
     * current language}.
     *
     * @param  function  the exact name of a method within its interface.
     * @return the description of the specified function.
     */
    @Override
    public final String getFunctionDescription(final String function) {
        final MethodInfo info = methods.get(function);
        return (info != null) ? info.description : "";
    }

    /**
     * Returns the user-visible name of the specified argument. The argument name is
     * shown to the user when prompting for arguments. It should be a single word and
     * may be translated to the {@linkplain #getLocale current language}.
     *
     * @param  function  the exact name of a method within its interface.
     * @param  argument  the index of the argument (0-based).
     * @return the user-visible name of the specified argument.
     */
    @Override
    public final String getDisplayArgumentName(final String function, int argument) {
        final MethodInfo info = methods.get(function);
        if (info != null) {
            argument <<= 1;
            final String[] arguments = info.arguments;
            if (argument>=0 && argument<arguments.length) {
                return arguments[argument];
            }
        }
        return "";
    }

    /**
     * Returns the description of the specified argument. The argument description is
     * shown to the user when prompting for arguments. It may be translated to the
     * {@linkplain #getLocale current language}.
     *
     * @param  function  the exact name of a method within its interface.
     * @param  argument  the index of the argument (0-based).
     * @return the description of the specified argument.
     */
    @Override
    public final String getArgumentDescription(final String function, int argument) {
        final MethodInfo info = methods.get(function);
        if (info != null) {
            argument = (argument << 1) + 1;
            final String[] arguments = info.arguments;
            if (argument>=0 && argument<arguments.length) {
                return arguments[argument];
            }
        }
        return "";
    }

    /**
     * Returns the localized message from the specified exception. If no message is available,
     * returns a default string. This method never returns a null value.
     *
     * @return the localized message of the given exception.
     */
    static String getLocalizedMessage(final Throwable exception) {
        if (DEBUG) {
            final StringWriter buffer = new StringWriter();
            final PrintWriter out = new PrintWriter(buffer);
            exception.printStackTrace(out);
            return buffer.toString();
        }
        final String message = exception.getLocalizedMessage();
        if (message != null) {
            return message;
        }
        return exception.getClass().getName();
    }

    /**
     * Reports an exception. This is used if an exception occurred in a method which can't returns
     * a {@link String} object. This method log the stack trace at the FINE level. We don't use
     * the WARNING level since this is not a program disfunction; the failure is probably caused
     * by wrong user-specified parameters.
     *
     * @param method     the method which is handling the exception.
     * @param exception  the exception to log.
     */
    final void reportException(final String method, final Throwable exception) {
        final LogRecord record = new LogRecord(Level.FINE, getLocalizedMessage(exception));
        record.setLoggerName(LOGGER.getName());
        record.setSourceClassName(getClass().getName());
        record.setSourceMethodName(method);
        record.setThrown(exception);
        LOGGER.log(record);
    }
}
