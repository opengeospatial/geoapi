/*
 * $ Id $
 * $ Source $
 * Created on Nov 23, 2004
 */
package org.opengis.feature;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * DOCUMENT ME.
 * 
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 */
public interface DataStoreFactory {

    DataStore createDataStore(Map params) throws IOException;

    DataStore createNewDataStore(Map params) throws IOException;

    String getDescription();

    Param[] getParametersInfo();

    boolean canProcess(Map params);

    boolean isAvailable();

    class Param {

        /** True if Param is required. */
        final public boolean required;

        /** Key used in Parameter map. */
        final public String key;

        /** Type of information required. */
        final public Class type;

        /** Short description (less then 40 characters). */
        final public String description;

        final public Object sample;

        public Param(final String key) {
            this(key, String.class, null);
        }

        public Param(final String key, final Class type) {
            this(key, type, null);
        }

        public Param(final String key, final Class type, final String description) {
            this(key, type, description, true);
        }

        public Param(
                final String key, 
                final Class type, 
                final String description, 
                final boolean required) {
            this(key, type, description, required, null);
        }

        public Param(
                final String key, 
                final Class type, 
                final String description, 
                final boolean required, 
                final Object sample) {
            this.key = key;
            this.type = type;
            this.description = description;
            this.required = required;
            this.sample = sample;
        }

        public Object lookUp(final Map map) throws IOException {
            if (!map.containsKey(key)) {
                if (required) {
                    throw new IOException("Parameter " + key + " is required:" + description);
                } else {
                    return null;
                }
            }
            Object value = map.get(key);
            if (value == null) {
                return null;
            }
            if (value instanceof String && (type != String.class)) {
                value = handle((String) value);
            }
            if (value == null) {
                return null;
            }
            if (!type.isInstance(value)) {
                throw new IOException(type.getName() + " required for parameter " + key + ": not "
                        + value.getClass().getName());
            }
            return value;
        }

        public String text(final Object value) {
            return value.toString();
        }

        public Object handle(final String text) throws IOException {
            if (text == null) {
                return null;
            }
            if (type == String.class) {
                return text;
            }
            if (text.length() == 0) {
                return null;
            }
            try {
                return parse(text);
            } catch (IOException ioException) {
                throw ioException;
            } catch (Throwable throwable) {
                throw new DataStoreException("Problem creating " + type.getName() + " from '"
                        + text + "'", throwable);
            }
        }

        public Object parse(final String text) throws Throwable {
            Constructor constructor;

            try {
                constructor = type.getConstructor(new Class[] {
                    String.class
                });
            } catch (SecurityException e) {
                //  type( String ) constructor is not public
                throw new IOException("Could not create " + type.getName() + " from text");
            } catch (NoSuchMethodException e) {
                // No type( String ) constructor
                throw new IOException("Could not create " + type.getName() + " from text");
            }

            try {
                return constructor.newInstance(new Object[] {
                    text,
                });
            } catch (IllegalArgumentException illegalArgumentException) {
                throw new DataStoreException("Could not create " + type.getName() + ": from '"
                        + text + "'", illegalArgumentException);
            } catch (InstantiationException instantiaionException) {
                throw new DataStoreException("Could not create " + type.getName() + ": from '"
                        + text + "'", instantiaionException);
            } catch (IllegalAccessException illegalAccessException) {
                throw new DataStoreException("Could not create " + type.getName() + ": from '"
                        + text + "'", illegalAccessException);
            } catch (InvocationTargetException targetException) {
                throw targetException.getCause();
            }
        }
    }
}