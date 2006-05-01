/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.tools;

// J2SE dependencies and extensions
import java.io.*;
import java.util.*;
import java.net.URI;
import java.net.URL;
import java.lang.reflect.*;
import java.util.logging.Logger;
import javax.units.Unit;

// OpenGIS dependencies
import org.opengis.util.CodeList;
import org.opengis.util.GenericName;
import org.opengis.util.InternationalString;
import org.opengis.annotation.UML;
import org.opengis.annotation.Obligation;
import org.opengis.parameter.ParameterValueGroup;
import org.opengis.parameter.ParameterDescriptorGroup;
import org.opengis.spatialschema.geometry.Geometry;
import org.opengis.spatialschema.geometry.primitive.Point;
import org.opengis.spatialschema.geometry.geometry.PointGrid;
import org.opengis.spatialschema.geometry.geometry.PointArray;


/**
 * Generates a name map from a set of GeoAPI interfaces.
 *
 * @author Martin Desruisseaux
 */
public class NameMapGenerator {
    /**
     * The root package.
     */
    private static final String ROOT_PACKAGE = "org.opengis.metadata";

    /**
     * The line separator.
     */
    private static final String LINE_SEPARATOR = System.getProperty("line.separator", "\n");

    /**
     * Do not allows instances of this class.
     */
    private NameMapGenerator() {
    }

    /**
     * Scan all classes and members and write an SQL script.
     */
    public static void main(final String[] args) throws Exception {
        final Properties nameMap = new Properties();
        final Properties typeMap = new Properties();
        final Set<Class> classes = ClassFinder.getClasses(CodeList.class, ROOT_PACKAGE);
        for (final Iterator<Class> it=classes.iterator(); it.hasNext();) {
            final Class classe = it.next();
            if (Throwable.class.isAssignableFrom(classe)) {
                it.remove();
                continue;
            }
            if (CodeList.class.isAssignableFrom(classe)) {
                inspectMembers(classe, nameMap, typeMap);
                it.remove();
                continue;
            }
            if (classe.isInterface()) {
                inspectMembers(classe, nameMap, typeMap);
                it.remove();
                continue;
            }
        }
        if (!classes.isEmpty()) {
            Logger.getLogger("org.opengis.tools").warning("Unrecognized classes.");
        }
        OutputStream out = new FileOutputStream("GeoAPI_to_ISO.properties");
        nameMap.store(out, "This is a temporary file for J2SE 1.4 implementations. " +
                           "Implementations for J2SE 5.0 should inspect annotations instead.");
        out.close();
        out = new FileOutputStream("CollectionTypes.properties");
        typeMap.store(out, "This is a temporary file for J2SE 1.4 implementations. " +
                           "Implementations for J2SE 5.0 should inspect generic types instead.");
        out.close();
    }

    /**
     * Add entries for the specified code list.
     */
    private static void inspectMembers(final Class classe,
                                       final Map<Object, Object> names,
                                       final Map<Object, Object> types)
    {
        /*
         * Add the GeoAPI - ISO name mapping for the CodeList or interface.
         */
        String identifier = getIdentifier(classe);
        if (identifier == null) {
            return;
        }
        final String className = getClassName(classe);
        names.put(className, identifier);
        /*
         * Inspects all public member of this CodeList / interface.
         */
        final Member[] attributes;
        if (classe.isInterface()) {
            attributes = classe.getDeclaredMethods();
        } else {
            attributes = classe.getDeclaredFields();
        }
        for (final Member attribute : attributes) {
            if (!Modifier.isPublic(attribute.getModifiers())) {
                continue;
            }
            identifier = getIdentifier((AccessibleObject) attribute);
            if (identifier == null) {
                continue;
            }
            /*
             * Add the GeoAPI - ISO name mapping for this member.
             * If the member is a method, also check its returns
             * type and add the Collection element type if needed.
             */
            final String fieldName = className+'.'+attribute.getName();
            names.put(fieldName, identifier);
            if (attribute instanceof Method) {
                final Method method = (Method) attribute;
                final Class returnType = method.getReturnType();
                if (Collection.class.isAssignableFrom(returnType)) {
                    final Type rt = method.getGenericReturnType();
                    if (rt instanceof ParameterizedType) {
                        final Type[] genericTypes = ((ParameterizedType) rt).getActualTypeArguments();
                        if (genericTypes.length != 1) {
                            // Should not occurs for collections.
                            throw new MalformedParameterizedTypeException();
                        }
                        final Class genericType = (Class) genericTypes[0];
                        identifier = genericType.getName();
                        types.put(fieldName, identifier);
                    }
                }
            }
        }
    }

    /**
     * Returns the unqualified name of the specified class.
     */
    private static String getClassName(final Class classe) {
        final String name = classe.getName();
        return name.substring(name.lastIndexOf('.')+1);
    }

    /**
     * Returns the UML identifier for the specified element, or {@code null}
     * if the specified element is not part of the UML model.
     */
    private static String getIdentifier(final AnnotatedElement element) {
        final UML uml = element.getAnnotation(UML.class);
        if (uml != null) {
            String identifier = uml.identifier();
            /*
             * If there is two or more UML identifier collapsed in only one
             * Java method, keep only the first identifier (which is usually
             * the main attribute).
             */
            final int split = identifier.indexOf(',');
            if (split >= 0) {
                identifier = identifier.substring(0, split);
            }
            return identifier;
        }
        return null;
    }
}
