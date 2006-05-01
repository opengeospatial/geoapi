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
import java.nio.charset.Charset;
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
 * Generate a database from a set of GeoAPI interfaces.
 *
 * @author Martin Desruisseaux
 */
public class DatabaseGenerator {
    /**
     * The root package.
     */
    private final String rootPackage = "org.opengis.metadata";

    /**
     * The schema where to create the tables.
     */
    private final String schema = "metadata";

    /**
     * The owner for the database to create, or <code>null</code> for the default one.
     */
    private final String owner = null;

    /**
     * The line separator.
     */
    private final String LINE_SEPARATOR = System.getProperty("line.separator", "\n");

    /**
     * The list of table created in inverse order. Used for the "DROP TABLE" statements.
     */
    private final LinkedList<String> drop = new LinkedList<String>();

    /**
     * The "ALTER TABLE" instructions for foreigner keys.
     */
    private final StringBuilder constraints = new StringBuilder();

    /**
     * <code>true</code> in order to allows tables, or <code>false</code> in order
     * to collapse tables in an ordinary field (i.e. allow only singletons). If we
     * know that we are not going to put more than one element per cell, it allow
     * to enforce foreigner keys.
     */
    private final boolean allowTables;

    /**
     * If <code>true</code>, disable warnings.
     */
    private final boolean quiet;

    /**
     * Constructs an instance of the database generator.
     */
    private DatabaseGenerator(final boolean allowTables, final boolean quiet) {
        this.allowTables = allowTables;
        this.quiet       = quiet;
    }

    /**
     * Scan all classes and members and write an SQL script.
     */
    public static void main(final String[] args) throws Exception {
        boolean allowTables = false;
        boolean quiet = false;
        for (int i=0; i<args.length; i++) {
            if (args[i].equalsIgnoreCase("-tables")) {
                allowTables = true;
            }
            if (args[i].equalsIgnoreCase("-quiet")) {
                quiet = true;
            }
        }
        new DatabaseGenerator(allowTables, quiet).writeScripts();
    }

    /**
     * Scan all classes and members and write an SQL script.
     */
    private void writeScripts() throws Exception {
        Writer sql = new FileWriter(allowTables ? "create.sql" : "create-noarrays.sql");

        sql.write("CREATE TABLE public.\"Locale\"");                          sql.write(LINE_SEPARATOR);
        sql.write('(');                                                       sql.write(LINE_SEPARATOR);
        sql.write("  code CHARACTER VARYING(5) NOT NULL,");                   sql.write(LINE_SEPARATOR);
        sql.write("  CONSTRAINT \"Locale_primaryKey\" PRIMARY KEY (code)");   sql.write(LINE_SEPARATOR);
        sql.write(") WITHOUT OIDS;");                                         sql.write(LINE_SEPARATOR);
        sql.write("INSERT INTO \"Locale\" VALUES ('en');");                   sql.write(LINE_SEPARATOR);
        sql.write("INSERT INTO \"Locale\" VALUES ('fr');");                   sql.write(LINE_SEPARATOR);
        sql.write("INSERT INTO \"Locale\" VALUES ('fr_CA');");                sql.write(LINE_SEPARATOR);
        sql.write("INSERT INTO \"Locale\" VALUES ('es');");                   sql.write(LINE_SEPARATOR);
                                                                              sql.write(LINE_SEPARATOR);
        sql.write("CREATE TABLE public.\"Unit\"");                            sql.write(LINE_SEPARATOR);
        sql.write('(');                                                       sql.write(LINE_SEPARATOR);
        sql.write("  symbol CHARACTER VARYING(10) NOT NULL,");                sql.write(LINE_SEPARATOR);
        sql.write("  CONSTRAINT \"Unit_primaryKey\" PRIMARY KEY (symbol)");   sql.write(LINE_SEPARATOR);
        sql.write(") WITHOUT OIDS;");                                         sql.write(LINE_SEPARATOR);
        sql.write("INSERT INTO \"Unit\" VALUES ('m');");                      sql.write(LINE_SEPARATOR);
        sql.write("INSERT INTO \"Unit\" VALUES ('cm');");                     sql.write(LINE_SEPARATOR);
        sql.write("INSERT INTO \"Unit\" VALUES ('km');");                     sql.write(LINE_SEPARATOR);
                                                                              sql.write(LINE_SEPARATOR);

        final Set<Class> classes = ClassFinder.getClasses(CodeList.class, rootPackage);
        for (final Iterator<Class> it=classes.iterator(); it.hasNext();) {
            final Class classe = it.next();
            if (Throwable.class.isAssignableFrom(classe)) {
                it.remove();
                continue;
            }
            if (CodeList.class.isAssignableFrom(classe)) {
                it.remove();
                final String statement = sqlCodeList(classe);
                if (statement != null) {
                    sql.write(statement);
                }
            }
        }
        while (!classes.isEmpty()) {
            try {
                for (final Iterator<Class> it=classes.iterator(); it.hasNext();) {
                    final Class classe = it.next();
                    if (classe.isInterface()) {
                        it.remove(); // Most be invoked before 'sqlInterface'.
                        final String statement = sqlInterface(classe, classes);
                        if (statement != null) {
                            sql.write(statement);
                        }
                    }
                }
                break; // All interfaces were processed.
            } catch (ConcurrentModificationException ignore) {
                // The Set has been modified (in order to solve dependencies).
                // The iteration must be restarted.
            }
        }
        sql.write(LINE_SEPARATOR);
        sql.write(constraints.toString());
        sql.close();
        sql = new BufferedWriter(new FileWriter("drop.sql"));
        for (final String name : drop) {
            sql.write("DROP TABLE ");
            sql.write(schema);
            sql.write(".\"");
            sql.write(name);
            sql.write("\";");
            sql.write(LINE_SEPARATOR);
        }
        sql.close();
        if (!classes.isEmpty()) {
            Logger.getLogger("org.opengis.tools").warning("Unrecognized classes.");
        }
    }

    /**
     * Create an SQL "CREATE TABLE" statement for the specified code list.
     * Returns <code>null</code> if the specified interface doesn't have @UML tags.
     *
     * @param  classe The class to process.
     * @return The SQL string to add to the 'create.sql' file.
     */
    private String sqlCodeList(final Class classe) throws IllegalAccessException {
        final String className = getIdentifier(classe);
        if (className == null) {
            return null;
        }
        final String unprefixedClassName = unprefixed(className);
        final StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE ");
        sql.append(schema);
        sql.append(".\"");
        sql.append(className);
        sql.append('"');
        sql.append(LINE_SEPARATOR);
        sql.append('(');
        sql.append(LINE_SEPARATOR);
        sql.append("  \"code\" INT2 NOT NULL,");
        sql.append(LINE_SEPARATOR);
        sql.append("  \"name\" CHARACTER VARYING(32) NOT NULL,");
        sql.append(LINE_SEPARATOR);
        sql.append("  CONSTRAINT \"");
        sql.append(unprefixedClassName);
        sql.append("_primaryKey\" PRIMARY KEY (\"code\"),");
        sql.append(LINE_SEPARATOR);
        sql.append("  CONSTRAINT \"");
        sql.append(unprefixedClassName);
        sql.append("_uniqueName\" UNIQUE (\"name\")");
        sql.append(LINE_SEPARATOR);
        sql.append(") WITHOUT OIDS;");
        sql.append(LINE_SEPARATOR);
        if (owner != null) {
            sql.append("ALTER TABLE ");
            sql.append(schema);
            sql.append(".\"");
            sql.append(className);
            sql.append("\" OWNER TO \"");
            sql.append(owner);
            sql.append("\";");
            sql.append(LINE_SEPARATOR);
        }        
        final Field[] attributes = classe.getDeclaredFields();
        for (final Field attribute : attributes) {
            if (!Modifier.isPublic(attribute.getModifiers())) {
                continue;
            }
            final String attributeName = getIdentifier(attribute);
            if (attributeName == null) {
                continue;
            }
            final CodeList code = (CodeList) attribute.get(null);
            sql.append("INSERT INTO ");
            sql.append(schema);
            sql.append(".\"");
            sql.append(className);
            sql.append("\" VALUES (");
            sql.append(code.ordinal() + 1);
            sql.append(", '");
            sql.append(attributeName.replace("'", "''"));
            sql.append("');");
            sql.append(LINE_SEPARATOR);
        }
        sql.append(LINE_SEPARATOR);
        sql.append(LINE_SEPARATOR);
        drop.addFirst(className);
        return sql.toString();
    }

    /**
     * Create an SQL "CREATE TABLE" statement for the specified interface.
     * Returns <code>null</code> if the specified interface doesn't have @UML tags.
     *
     * @param  classe The class to process.
     * @param  classes The remaining classes to process. Element in this set will be
     *         removed if this method determine that it need to create some other classes
     *         before this one, in order to resolve dependencies.
     * @return The SQL string to add to the 'create.sql' file.
     */
    private String sqlInterface(final Class classe, final Set<Class> classes) {
        final String className = getIdentifier(classe);
        if (className == null) {
            return null;
        }
        final String unprefixedClassName = unprefixed(className);
        final StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE ");
        sql.append(schema);
        sql.append(".\"");
        sql.append(className);
        sql.append('"');
        sql.append(LINE_SEPARATOR);
        sql.append('(');
        sql.append(LINE_SEPARATOR);
        sql.append("  \"id\" OID NOT NULL,");
        sql.append(LINE_SEPARATOR);
        final Method[] attributes = classe.getDeclaredMethods();
scan:   for (final Method attribute : attributes) {
            if (!Modifier.isPublic(attribute.getModifiers())) {
                continue;
            }
            final String attributeName = getIdentifier(attribute);
            if (attributeName == null) {
                continue;
            }
            for (final Class parent : classe.getInterfaces()) {
                try {
                    parent.getMethod(attributeName, attribute.getParameterTypes());
                    continue scan; // Found the method in superclass: don't add it again.
                } catch (NoSuchMethodException ignore) {
                    // No such method in superclass. Checks other interfaces.
                }
            }
            Class attributeType = attribute.getReturnType();
            Type genericType = attribute.getGenericReturnType();
            if (!allowTables) {
                if (attributeType.isArray()) {
                    attributeType = attributeType.getComponentType();
                } else if (Collection.class.isAssignableFrom(attributeType)) {
                    if (genericType instanceof ParameterizedType) {
                        final Type[] types = ((ParameterizedType) genericType).getActualTypeArguments();
                        if (types.length == 1) {
                            attributeType = (Class) types[0];
                            genericType = null;
                        }
                    }
                }
            }
            final String sqlType = toSQLType(attributeType, genericType);
            if (sqlType == null) {
                continue;
            }
            sql.append("  \"");
            sql.append(attributeName);
            sql.append("\" ");
            sql.append(sqlType);
            if (Obligation.MANDATORY.equals(attribute.getAnnotation(UML.class).obligation())) {
                sql.append(" NOT NULL");
            }
            sql.append(',');
            sql.append(LINE_SEPARATOR);
            final String foreignKey;
            final String foreignTable;
            final boolean inSchema;
            if (Locale.class.isAssignableFrom(attributeType)) {
                inSchema     = false;
                foreignTable = "Locale";
                foreignKey   = "code";
            } else if (Unit.class.isAssignableFrom(attributeType)) {
                inSchema     = false;
                foreignTable = "Unit";
                foreignKey   = "symbol";
            } else if (CodeList.class.isAssignableFrom(attributeType)) {
                inSchema     = true;
                foreignTable = getIdentifier(attributeType);
                foreignKey   = "code";
            } else if (attributeType.getName().startsWith(rootPackage)) {
                inSchema     = true;
                foreignTable = getIdentifier(attributeType);
                foreignKey   = "id";
            } else {
                continue;
            }
            constraints.append("ALTER TABLE ");
            constraints.append(schema);
            constraints.append(".\"");
            constraints.append(className);
            constraints.append("\" ADD CONSTRAINT \"");
            constraints.append(unprefixedClassName);
            constraints.append('_');
            constraints.append(attributeName);
            constraints.append("\" FOREIGN KEY (\"");
            constraints.append(attributeName);
            constraints.append("\") REFERENCES ");
            if (inSchema) {
                constraints.append(schema);
                constraints.append('.');
            }
            constraints.append('"');
            constraints.append(foreignTable);
            constraints.append("\" (");
            constraints.append(foreignKey);
            constraints.append(") ON UPDATE RESTRICT ON DELETE RESTRICT;");
            constraints.append(LINE_SEPARATOR);
            if (classes.remove(attributeType)) {
                // Resolve dependencies first.
                sql.insert(0, sqlInterface(attributeType, classes));
            }
        }
        sql.append("  CONSTRAINT \"");
        sql.append(unprefixedClassName);
        sql.append("_primaryKey\" PRIMARY KEY (id)");
        sql.append(LINE_SEPARATOR);
        sql.append(')');
        final Class[] parents = classe.getInterfaces();
        if (parents != null) {
            for (int i=0; i<parents.length; i++) {
                final Class parent = parents[i];
                if (classes.remove(parent)) {
                    // Resolve dependencies first.
                    sql.insert(0, sqlInterface(parent, classes));
                }
                sql.append(i==0 ? " INHERITS (" : ", ");
                sql.append(schema);
                sql.append(".\"");
                sql.append(getIdentifier(parent));
                sql.append('"');
            }
            if (parents.length != 0) {
                sql.append(')');
            }
        }
        sql.append(" WITHOUT OIDS;");
        sql.append(LINE_SEPARATOR);
        if (owner != null) {
            sql.append("ALTER TABLE ");
            sql.append(schema);
            sql.append(".\"");
            sql.append(className);
            sql.append("\" OWNER TO \"");
            sql.append(owner);
            sql.append("\";");
            sql.append(LINE_SEPARATOR);
        }        
        sql.append(LINE_SEPARATOR);
        sql.append(LINE_SEPARATOR);
        drop.addFirst(className);
        return sql.toString();
    }

    /**
     * Returns the SQL type for the specified Java classe.
     */
    private String toSQLType(final Class classe, final Type type) {
        if (       CharSequence.class.isAssignableFrom(classe) ||
            InternationalString.class.isAssignableFrom(classe) ||
                        Charset.class.isAssignableFrom(classe))
        {
            return "TEXT";
        }
        if (URL.class.isAssignableFrom(classe) ||
            URI.class.isAssignableFrom(classe))
        {
            return "TEXT";
        }
        if (GenericName.class.isAssignableFrom(classe))
        {
            return "TEXT";
        }
        if (Boolean.class.isAssignableFrom(classe) ||
            Boolean.TYPE .isAssignableFrom(classe))
        {
            return "BOOLEAN";
        }
        if (Short.class.isAssignableFrom(classe) ||
            Short.TYPE .isAssignableFrom(classe))
        {
            return "SMALLINT";
        }
        if (Integer.class.isAssignableFrom(classe) ||
            Integer.TYPE .isAssignableFrom(classe))
        {
            return "INTEGER";
        }
        if (Long.class.isAssignableFrom(classe) ||
            Long.TYPE .isAssignableFrom(classe))
        {
            return "BIGINT";
        }
        if (Float.class.isAssignableFrom(classe) ||
            Float.TYPE .isAssignableFrom(classe))
        {
            return "REAL";
        }
        if (Double.class.isAssignableFrom(classe) ||
            Double.TYPE .isAssignableFrom(classe))
        {
            return "DOUBLE PRECISION";
        }
        if (Number.class.isAssignableFrom(classe))
        {
            return "DOUBLE PRECISION";
        }
        if (Date.class.isAssignableFrom(classe))
        {
            return "TIMESTAMP WITH TIME ZONE";
        }
        if (Point.class.isAssignableFrom(classe))
        {
            return "POINT";
        }
        if (Geometry.class.isAssignableFrom(classe))
        {
            return "PATH";
        }
        if (Locale.class.isAssignableFrom(classe))
        {
            return "CHARACTER VARYING(5)";
        }
        if (Unit.class.isAssignableFrom(classe))
        {
            return "CHARACTER VARYING(10)";
        }
        if (CodeList.class.isAssignableFrom(classe))
        {
            return "INT2";
        }
        if (classe.getName().startsWith(rootPackage)) {
            return "OID";
        }
        if (classe.isArray()) {
            final String sql = toSQLType(classe.getComponentType(), null);
            return (sql != null) ? sql+"[]" : null;
        }
        if (Collection.class.isAssignableFrom(classe)) {
            if (type instanceof ParameterizedType) {
                final Type[] types = ((ParameterizedType) type).getActualTypeArguments();
                if (types.length == 1) {
                    final String sql = toSQLType((Class) types[0], null);
                    return (sql != null) ? sql+"[]" : null;
                }
            }
        }
        if (!quiet) {
            Logger.getLogger("org.opengis.tools").warning("No mapping to SQL type: "+classe.getName());
        }
        return null;
    }

    /**
     * Returns the UML identifier for the specified element, or <code>null</code>
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

    /**
     * Returns the classname without the prefix.
     */
    private static String unprefixed(String className) {
        if (className.length() >= 4 && className.charAt(2)=='_') {
            className = className.substring(3);
        }
        return className;
    }
}
