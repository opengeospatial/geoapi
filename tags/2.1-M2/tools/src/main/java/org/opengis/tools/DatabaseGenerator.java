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

// J2SE dependencies
import java.awt.Point;
import java.net.URI;
import java.net.URL;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Date;
import java.util.Locale;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collection;
import java.lang.reflect.Field;
import javax.units.Unit;

// Annotation processing tools
import com.sun.mirror.apt.Filer;
import com.sun.mirror.type.ArrayType;
import com.sun.mirror.type.TypeMirror;
import com.sun.mirror.type.DeclaredType;
import com.sun.mirror.type.InterfaceType;
import com.sun.mirror.util.Declarations;
import com.sun.mirror.declaration.Modifier;
import com.sun.mirror.declaration.ClassDeclaration;
import com.sun.mirror.declaration.FieldDeclaration;
import com.sun.mirror.declaration.MethodDeclaration;
import com.sun.mirror.declaration.InterfaceDeclaration;

// OpenGIS dependencies
import org.opengis.annotation.UML;
import org.opengis.annotation.Obligation;
import org.opengis.spatialschema.geometry.Geometry;
import org.opengis.util.CodeList;
import org.opengis.util.GenericName;
import org.opengis.util.InternationalString;


/**
 * Generates a database from a set of GeoAPI interfaces.
 * <p>
 * <b>How to use</b>
 * {@code chdir} to the root directory of source code. Then invoke the following command,
 * where {@code metadata.txt} is a file containing the path to all java classes to parse
 * in the {@value #ROOT_PACKAGE} package.
 *
 * <blockquote><pre>
 * apt -nocompile -factory org.opengis.tools.DatabaseGenerator @metadata.txt
 * </pre></blockquote>
 *
 * The SQL files will be stored in the <code>{@value #ROOT_PACKAGE}/doc-files</code> package.
 *
 * @author Martin Desruisseaux
 */
public class DatabaseGenerator extends UmlProcessor {
    /**
     * The root package.
     */
    public static final String ROOT_PACKAGE = "org.opengis.metadata";

    /**
     * The schema where to create the tables.
     */
    private final String schema = "metadata";

    /**
     * The owner for the database to create, or {@code null} for the default one.
     */
    private final String owner = null;

    /**
     * Interfaces to process. See {@link #visitInterfaceDeclaration}.
     */
    private List<InterfaceDeclaration> interfaces;

    /**
     * The writer where to write SQL statements for table creation with arrays.
     */
    private PrintWriter createWithArrays;

    /**
     * The writer where to write SQL statements for table creation without arrays.
     */
    private PrintWriter createWithoutArrays;

    /**
     * The writer where to write SQL statements, both with and without arrays.
     */
    private PrintWriter create;

    /**
     * The writer where to write SQL statements for foreigner keys, with arrays.
     */
    private StringWriter constraintWithArrays;

    /**
     * The writer where to write SQL statement for foreigner keys, without arrays.
     */
    private StringWriter constraintWithoutArrays;

    /**
     * The writer where to write SQL statement for foreigner keys, both with and without arrays.
     */
    private PrintWriter constraints;

    /**
     * {@link #constraintWithoutArrays} as a print writer.
     */
    private PrintWriter constraints2;

    /**
     * The buffer where to write the "DROP TABLE" statements.
     */
    private StringBuilder drop;

    /**
     * Creates a default processor.
     */
    public DatabaseGenerator() {
    }

    /**
     * Process all program elements supported by this annotation processor. This method scan
     * all interfaces and their methods (as well as code lists and their fields) and write
     * the table creation SQL script.
     */
    @Override
    public void process() {
        drop = new StringBuilder();
        interfaces = new LinkedList<InterfaceDeclaration>();
        final Filer filer = environment.getFiler();
        final PrintWriter dropPrinter;
        try {
            dropPrinter             = filer.createTextFile(Filer.Location.SOURCE_TREE, ROOT_PACKAGE,
                                      new File("doc-files/postgre/drop.sql"), null);
            createWithArrays        = filer.createTextFile(Filer.Location.SOURCE_TREE, ROOT_PACKAGE,
                                      new File("doc-files/postgre/create.sql"), null);
            createWithoutArrays     = filer.createTextFile(Filer.Location.SOURCE_TREE, ROOT_PACKAGE,
                                      new File("doc-files/postgre/create-noarrays.sql"), null);
            create                  = new PrintWriter(new EchoWriter(createWithArrays, createWithoutArrays));
            constraintWithArrays    = new StringWriter();
            constraintWithoutArrays = new StringWriter();
            constraints2            = new PrintWriter(constraintWithoutArrays);
            constraints             = new PrintWriter(new EchoWriter(constraintWithArrays, constraintWithoutArrays));
        } catch (IOException exception) {
            printError(exception);
            return;
        }
        create.println("CREATE TABLE public.\"Locale\"");
        create.println('(');
        create.println("  code CHARACTER VARYING(5) NOT NULL,");
        create.println("  CONSTRAINT \"Locale_primaryKey\" PRIMARY KEY (code)");
        create.println(") WITHOUT OIDS;");
        create.println("INSERT INTO \"Locale\" VALUES ('en');");
        create.println("INSERT INTO \"Locale\" VALUES ('fr');");
        create.println("INSERT INTO \"Locale\" VALUES ('fr_CA');");
        create.println("INSERT INTO \"Locale\" VALUES ('es');");
        create.println();
        create.println("CREATE TABLE public.\"Unit\"");
        create.println('(');
        create.println("  symbol CHARACTER VARYING(10) NOT NULL,");
        create.println("  CONSTRAINT \"Unit_primaryKey\" PRIMARY KEY (symbol)");
        create.println(") WITHOUT OIDS;");
        create.println("INSERT INTO \"Unit\" VALUES ('m');");
        create.println("INSERT INTO \"Unit\" VALUES ('cm');");
        create.println("INSERT INTO \"Unit\" VALUES ('km');");
        create.println();
        super.process();
        sortInterfaceDeclarations();
        for (final InterfaceDeclaration declaration : interfaces) {
            processInterfaceDeclaration(declaration);
        }
        create.println();
        createWithArrays   .print(constraintWithArrays   );
        createWithoutArrays.print(constraintWithoutArrays);
        create.close();
        dropPrinter.print(drop);
        dropPrinter.close();
    }

    /**
     * Add an "DROP TABLE" statement for the specified table name.
     * Statements are added in reverse order.
     */
    private final void drop(final String name) {
        final StringBuilder buffer = new StringBuilder();
        buffer.append("DROP TABLE ");
        buffer.append(schema);
        buffer.append(".\"");
        buffer.append(name);
        buffer.append("\" CASCADE;");
        buffer.append(System.getProperty("line.separator", "\n"));
        drop.insert(0, buffer);
    }

    /**
     * Creates an SQL "CREATE TABLE" statement for the specified code list.
     * Do nothing if the specified interface doesn't have @UML tags.
     */
    @Override
    public void visitClassDeclaration(final ClassDeclaration declaration) {
        super.visitClassDeclaration(declaration);
        final String className = getUmlIdentifier(declaration);
        if (className == null) {
            return;
        }
        final Class classe = getClass(declaration);
        if (!CodeList.class.isAssignableFrom(classe)) {
            return;
        }
        final String unprefixedClassName = unprefixed(className);
        create.print  ("CREATE TABLE ");
        create.print  (schema);
        create.print  (".\"");
        create.print  (className);
        create.println('"');
        create.println('(');
        create.println("  \"code\" INT2 NOT NULL,");
        create.println("  \"name\" CHARACTER VARYING(32) NOT NULL,");
        create.print  ("  CONSTRAINT \"");
        create.print  (unprefixedClassName);
        create.println("_primaryKey\" PRIMARY KEY (\"code\"),");
        create.print  ("  CONSTRAINT \"");
        create.print  (unprefixedClassName);
        create.println("_uniqueName\" UNIQUE (\"name\")");
        create.println(") WITHOUT OIDS;");
        if (owner != null) {
            create.print  ("ALTER TABLE ");
            create.print  (schema);
            create.print  (".\"");
            create.print  (className);
            create.print  ("\" OWNER TO \"");
            create.print  (owner);
            create.println("\";");
        }
        for (final FieldDeclaration attribute : declaration.getFields()) {
            if (!attribute.getModifiers().contains(Modifier.PUBLIC)) {
                continue;
            }
            final String attributeName = getUmlIdentifier(attribute);
            if (attributeName == null) {
                continue;
            }
            final Field field;
            try {
                field = classe.getField(attribute.getSimpleName());
            } catch (NoSuchFieldException e) {
                environment.getMessager().printError("No such field: " + e.getLocalizedMessage());
                continue;
            }
            final CodeList code;
            try {
                code = (CodeList) field.get(null);
            } catch (IllegalAccessException e) {
                environment.getMessager().printError("Illegal access: " + e.getLocalizedMessage());
                continue;
            }
            create.print  ("INSERT INTO ");
            create.print  (schema);
            create.print  (".\"");
            create.print  (className);
            create.print  ("\" VALUES (");
            create.print  (code.ordinal() + 1);
            create.print  (", '");
            create.print  (attributeName.replace("'", "''"));
            create.println("');");
        }
        create.println();
        create.println();
        drop(className);
    }

    /**
     * Prepare the creates of SQL "CREATE TABLE" statement for the specified interface.
     * The actual SQL statement creation is done later by {@link #processInterfaceDeclaration}.
     */
    @Override
    public void visitInterfaceDeclaration(final InterfaceDeclaration declaration) {
        super.visitInterfaceDeclaration(declaration);
        interfaces.add(declaration);
    }

    /**
     * Sort the interface declaration before processing, in order to process super interfaces first.
     */
    private void sortInterfaceDeclarations() {
        final List<InterfaceDeclaration> sorted = new ArrayList<InterfaceDeclaration>(interfaces.size());
        while (!interfaces.isEmpty()) {
scan:       for (final Iterator<InterfaceDeclaration> it=interfaces.iterator(); it.hasNext();) {
                final InterfaceDeclaration declaration = it.next();
                for (final InterfaceType parent : declaration.getSuperinterfaces()) {
                    if (interfaces.contains(parent.getDeclaration())) {
                        continue scan;
                    }
                }
                it.remove();
                sorted.add(declaration);
            }
        }
        interfaces = sorted;
    }

    /**
     * Creates a SQL "CREATE TABLE" statement for the specified interface.
     * Do nothing if the specified interface doesn't have @UML tags.
     */
    private void processInterfaceDeclaration(final InterfaceDeclaration declaration) {
        final String className = getUmlIdentifier(declaration);
        if (className == null) {
            return;
        }
        final String unprefixedClassName = unprefixed(className);
        create.print  ("CREATE TABLE ");
        create.print  (schema);
        create.print  (".\"");
        create.print  (className);
        create.println('"');
        create.println('(');
        create.println("  \"id\" OID NOT NULL,");
        final Declarations util = environment.getDeclarationUtils();
scan:   for (final MethodDeclaration attribute : declaration.getMethods()) {
            final String attributeName = getUmlIdentifier(attribute);
            if (attributeName == null) {
                continue;
            }
            for (final InterfaceType parent : declaration.getSuperinterfaces()) {
                for (final MethodDeclaration candidate : parent.getDeclaration().getMethods()) {
                    if (util.overrides(attribute, candidate)) {
                        // Found the method in superclass: don't add it again.
                        continue scan;
                    }
                }
            }
            /*
             * Gets the attribute type in declaration order. If the attribute is an array, gets
             * also the array component type. We don't look futher than one dimensional arrays.
             */
            final TypeMirror attributeType = attribute.getReturnType();
            TypeMirror componentType = attributeType;
            if (attributeType instanceof ArrayType) {
                componentType = ((ArrayType) attributeType).getComponentType();
            } else if (attributeType instanceof DeclaredType) {
                final Class c = getClass(attributeType);
                if (Collection.class.isAssignableFrom(c)) {
                    final Collection<TypeMirror> types = ((DeclaredType) attributeType).getActualTypeArguments();
                    if (types.size() == 1) {
                        componentType = types.iterator().next();
                    }
                }
            }
            /*
             * Writes the SQL statement for a column mapping the attribute type.
             */
            final String sqlType = toSQLType(getClass(componentType));
            if (sqlType == null) {
                continue;
            }
            create.print("  \"");
            create.print(attributeName);
            create.print("\" ");
            create.print(sqlType);
            if (attributeType != componentType) {
                createWithArrays.print("[]");
            }
            if (Obligation.MANDATORY.equals(attribute.getAnnotation(UML.class).obligation())) {
                create.print(" NOT NULL");
            }
            create.println(',');
            /*
             * Create the constraint in a separated buffer.
             */
            PrintWriter constraints = this.constraints;
            if (attributeType != componentType) {
                constraints = constraints2;
            }
            final String foreignKey;
            final String foreignTable;
            final boolean inSchema;
            final Class componentClass = getClass(componentType);
            if (Locale.class.isAssignableFrom(componentClass)) {
                inSchema     = false;
                foreignTable = "Locale";
                foreignKey   = "code";
            } else if (Unit.class.isAssignableFrom(componentClass)) {
                inSchema     = false;
                foreignTable = "Unit";
                foreignKey   = "symbol";
            } else if (CodeList.class.isAssignableFrom(componentClass)) {
                inSchema     = true;
                foreignTable = getUmlIdentifier(componentType);
                foreignKey   = "code";
            } else if (componentClass.getName().startsWith(ROOT_PACKAGE)) {
                inSchema     = true;
                foreignTable = getUmlIdentifier(componentType);
                foreignKey   = "id";
            } else {
                continue;
            }
            constraints.print("ALTER TABLE ");
            constraints.print(schema);
            constraints.print(".\"");
            constraints.print(className);
            constraints.print("\" ADD CONSTRAINT \"");
            constraints.print(unprefixedClassName);
            constraints.print('_');
            constraints.print(attributeName);
            constraints.print("\" FOREIGN KEY (\"");
            constraints.print(attributeName);
            constraints.print("\") REFERENCES ");
            if (inSchema) {
                constraints.print(schema);
                constraints.print('.');
            }
            constraints.print('"');
            constraints.print(foreignTable);
            constraints.print("\" (");
            constraints.print(foreignKey);
            constraints.println(") ON UPDATE CASCADE ON DELETE RESTRICT;");
        }
        create.print  ("  CONSTRAINT \"");
        create.print  (unprefixedClassName);
        create.println("_primaryKey\" PRIMARY KEY (id)");
        create.print  (')');
        final Collection<InterfaceType> parents = declaration.getSuperinterfaces();
        if (!parents.isEmpty()) {
            create.print(" INHERITS (");
            boolean multi = false;
            for (final InterfaceType parent : parents) {
                if (multi) {
                    create.print(", ");
                }
                create.print(schema);
                create.print(".\"");
                create.print(getUmlIdentifier(parent));
                create.print('"');
                multi = true;
            }
            create.print(')');
        }
        create.println(" WITHOUT OIDS;");
        if (owner != null) {
            create.print  ("ALTER TABLE ");
            create.print  (schema);
            create.print  (".\"");
            create.print  (className);
            create.print  ("\" OWNER TO \"");
            create.print  (owner);
            create.println("\";");
        }        
        create.println();
        create.println();
        drop(className);
    }

    /**
     * Returns the SQL type for the specified Java classe.
     */
    private String toSQLType(final Class classe) {
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
        if (classe.getName().startsWith(ROOT_PACKAGE)) {
            return "OID";
        }
        environment.getMessager().printWarning("No mapping to SQL type: "+classe.getName());
        return null;
    }

    /**
     * Returns the classname without the OGC prefix.
     */
    private static String unprefixed(String className) {
        if (className.length() >= 4 && className.charAt(2)=='_') {
            className = className.substring(3);
        }
        return className;
    }
}
