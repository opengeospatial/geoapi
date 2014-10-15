/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2014 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */
package org.opengis.tools.apt;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.Arrays;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Collections;
import java.util.LinkedHashMap;

import com.sun.mirror.apt.Filer;
import com.sun.mirror.type.InterfaceType;
import com.sun.mirror.util.Declarations;
import com.sun.mirror.declaration.Modifier;
import com.sun.mirror.declaration.TypeDeclaration;
import com.sun.mirror.declaration.ClassDeclaration;
import com.sun.mirror.declaration.MemberDeclaration;
import com.sun.mirror.declaration.MethodDeclaration;

import org.opengis.util.CodeList;
import org.opengis.annotation.UML;
import org.opengis.parameter.ParameterValueGroup;
import org.opengis.parameter.ParameterDescriptorGroup;
import org.opengis.geometry.coordinate.PointGrid;
import org.opengis.geometry.coordinate.PointArray;


/**
 * Generates a list of all type and methods, together with their ISO identifier.
 * The list will be written in the {@code ../javadoc/content.html} file, relative
 * to the current directory. The result is published online at
 * <a href="http://www.geoapi.org/snapshot/javadoc/content.html">http://www.geoapi.org/snapshot/javadoc/content.html</a>
 * <p>
 * <b><u>How to use</u></b><br>
 * {@code chdir} to the root directory of source code. Then invoke the {@code apt} command,
 * where {@code content.txt} is a file containing the path to all java classes to parse:
 *
 * <blockquote><pre>
 * cd geoapi/src/main/java
 * find org -name "*.java" &gt; content.txt
 * export CLASSPATH=~/.m2/repository/javax/measure/jsr-275/0.9.3/jsr-275-0.9.3.jar
 * export CLASSPATH=$CLASSPATH:../../../../geoapi-pending/target/geoapi-pending-4.0-M01.jar
 * export CLASSPATH=$CLASSPATH:../../../../tools/target/tools-4.0-M01.jar
 * apt -classpath $CLASSPATH -nocompile -factory org.opengis.tools.apt.IndexGenerator @content.txt
 * rm content.txt
 * </pre></blockquote>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   2.0
 */
public class IndexGenerator extends UmlProcessor implements Comparator<TypeDeclaration> {
    /**
     * Method names that are part of Java specification.
     */
    private final Set<String> javaMethods;

    /**
     * Method names that are part of Java3D vecmath.
     */
    private final Set<String> vecmathMethods;

    /**
     * The list of all declarations. They will be collected before to be processed,
     * in order to sort them by package name before to sort them by class name.
     */
    private final List<TypeDeclaration> declarations;

    /**
     * The writer where to write the list.
     */
    private PrintWriter out;

    /**
     * Last package visited.
     */
    private String lastPackage;

    /**
     * Creates a default processor.
     */
    public IndexGenerator() {
        javaMethods    = new HashSet<String>(Arrays.asList("toString", "clone", "equals", "hashCode", "doubleValue"));
        vecmathMethods = new HashSet<String>(Arrays.asList("getNumRow", "getNumCol", "getElement", "setElement"));
        declarations   = new ArrayList<TypeDeclaration>(512);
    }

    /**
     * Processes all program elements supported by this annotation processor. This method scans
     * all interfaces and their methods (as well as code lists and their fields) and writes the
     * result to a HTML file.
     */
    @Override
    public void process() {
        // Collect all type declaration, then sort them.
        super.process();
        Collections.sort(declarations, this);

        // Now write the HTML file.
        final Filer filer = environment.getFiler();
        try {
            out = filer.createTextFile(Filer.Location.SOURCE_TREE, "",
                        new File("../javadoc/content.html"), null);
        } catch (IOException exception) {
            printError(exception);
            return;
        }
        out.println("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        out.println("<!DOCTYPE html>");
        out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
        out.println("  <head>");
        out.println("    <title>GeoAPI content</title>");
        out.println("  </head>");
        out.println("  <body><div>");
        out.println("  <h1>GeoAPI content</h1>");
        out.println("  <table cellpadding='0' cellspacing='0'>");
        out.println("  <tr><th bgcolor=\"#CCCCFF\">GeoAPI identifier</th>" +
                          "<th bgcolor=\"#CCCCFF\">ISO identifier</th>" +
                          "<th bgcolor=\"#CCCCFF\">Standard</th></tr>");
        lastPackage = "";
        for (final TypeDeclaration declaration : declarations) {
            writeTypeDeclaration(declaration);
        }
        out.println("  </table>");
        out.println("  </div></body>");
        out.println("</html>");
        out.close();
    }

    /**
     * Returns the simple name of the given class or interface, completed with the name of the
     * outer class is any. The check for outer interface is mostly for {@code CodeList.Filter}.
     */
    private static String getSimpleName(TypeDeclaration declaration) {
        String classname = declaration.getSimpleName();
        while ((declaration = declaration.getDeclaringType()) != null) {
            classname = declaration.getSimpleName() + '.' + classname;
        }
        return classname;
    }

    /**
     * Compares the given declaration for order.
     *
     * @param  o1 The first declaration.
     * @param  o2 The second declaration.
     * @return A negative number if {@code o1} should be printed before {@code o2}, or
     *         a positive number if {@code o1} should be printed after {@code o2}.
     */
    @Override
    public int compare(final TypeDeclaration o1, final TypeDeclaration o2) {
        int c = o1.getPackage().getQualifiedName().compareToIgnoreCase(o2.getPackage().getQualifiedName());
        if (c == 0) {
            c = getSimpleName(o1).compareToIgnoreCase(getSimpleName(o2));
        }
        return c;
    }

    /**
     * Adds the given declaration to the list of types to be written, except if it
     * is part of a package to be omitted.
     *
     * @param declaration The class to check.
     */
    @Override
    public void visitTypeDeclaration(final TypeDeclaration declaration) {
        super.visitTypeDeclaration(declaration);
        if (declaration.getAnnotation(Deprecated.class) != null) {
            return;
        }
        if (declaration.getPackage().getQualifiedName().equals("org.opengis.annotation")) {
            return;
        }
        declarations.add(declaration);
    }

    /**
     * Write the HTML records for the given type, and for methods and fields inside that class.
     */
    private void writeTypeDeclaration(final TypeDeclaration declaration) {
        UML    uml                = declaration.getAnnotation(UML.class);
        String identifier         = getDisplayName(uml);
        String classname          = getSimpleName(declaration);
        String qualifiedClassname = declaration.getQualifiedName();
        String pathToClassJavadoc = qualifiedClassname.replace('.', '/') + ".html";
        boolean significantChange = !similarClassName(declaration, classname, identifier);
        final boolean isCodeList  = isCodeList(declaration);
        /*
         * Writes the class name. If the class is declared in a new package
         * compared to the previous one, writes the package name first.
         */
        if (true) {
            final String packageName = declaration.getPackage().getQualifiedName();
            if (!packageName.equals(lastPackage)) {
                out.println("  <tr><td colspan=\"3\">&nbsp;</td></tr>");
                out.print  ("  <tr><td colspan=\"3\" nowrap bgcolor=\"#DDDDFF\"><b>Package&nbsp; <code>");
                out.print  (packageName);
                out.println("</code></b></td></tr>");
                out.println("  <tr><td colspan=\"3\"><hr/></td></tr>");
                lastPackage = packageName;
            }
            out.print("  <tr><td nowrap><b><code>&nbsp;&nbsp;</code>");
            out.print(isCodeList ? "Code list" : (declaration instanceof ClassDeclaration) ? "Class" : "Interface");
            out.print(" <code><a href=\"");
            out.print(pathToClassJavadoc);
            out.print("\">");
            printName(classname, significantChange);
            out.print("</a></code></b>");
            if (identifier != null) {
                out.print("<td><code>");
                printName(identifier, significantChange);
                out.print("</code></td><td nowrap>");
                out.print(getSpecification(uml));
                out.print("</td>");
            }
            out.println("</tr>");
        }
        /*
         * Gets the attributes, ignoring deprecated and duplicated attributes.
         * If an attribute is defined twice, the one with a UML annotation is
         * selected (there should be only one).
         */
        final Map<String,MemberDeclaration> attributes = new LinkedHashMap<String,MemberDeclaration>();
scan:   for (final MemberDeclaration attribute : isCodeList ? declaration.getFields() : declaration.getMethods()) {
            /*
             * Skip non-public members, deprecated members and members
             * which are declared in a super-interface.
             */
            if (!attribute.getModifiers().contains(Modifier.PUBLIC)) {
                continue scan;
            }
            if (attribute.getAnnotation(Deprecated.class) != null) {
                continue scan;
            }
            if (attribute instanceof MethodDeclaration) {
                final MethodDeclaration method = (MethodDeclaration) attribute;
                final Declarations util = environment.getDeclarationUtils();
                for (final InterfaceType parent : declaration.getSuperinterfaces()) {
                    for (final MethodDeclaration candidate : parent.getDeclaration().getMethods()) {
                        if (util.overrides(candidate, method)) {
                            continue scan; // Found the method in superclass: don't document it.
                        }
                        // The following check is redundant with the one we just made above, except
                        // that we don't check the return type  (the above check considers that we
                        // don't override the method if the overriding method restricts the return
                        // type). We should perform only one check; performing both of them is
                        // useless but for now we leave it that way as a reminder in case we want
                        // to revisit the policy applied here.
                        if (method.getSimpleName().equals(candidate.getSimpleName())) {
                            continue scan;
                        }
                    }
                }
            }
            final String name = attribute.getSimpleName();
            final MemberDeclaration old = attributes.put(name, attribute);
            if (old != null) {
                // Found a duplicated attribute. Restore the first attribute, unless
                // the new one has a UML annotation while the older one had none.
                final boolean oldHasUML = old      .getAnnotation(UML.class) != null;
                final boolean newHasUML = attribute.getAnnotation(UML.class) != null;
                if (oldHasUML || !newHasUML) {
                    attributes.put(name, old);
                    if (oldHasUML && newHasUML) {
                        // Preserve also the new attribute using a generated key.
                        int n = 0;
                        String key;
                        do key = name + '$' + (++n);
                        while (attributes.containsKey(key));
                        attributes.put(key, attribute);
                    }
                }
            }
        }
        /*
         * Writes the HTML lines for the attributes retained in the above iteration.
         */
scan:   for (final MemberDeclaration attribute : attributes.values()) {
            uml = attribute.getAnnotation(UML.class);
            identifier = getDisplayName(uml);
            final String name = attribute.getSimpleName();
            significantChange = true;
            if (identifier != null) {
                if (attribute instanceof MethodDeclaration) {
                    significantChange = !(similarMethodName((MethodDeclaration) attribute, name, identifier));
                } else {
                    significantChange = !(similarCodeName(name, identifier));
                }
            }
            out.print("  <tr><td><code>&nbsp;&nbsp;&nbsp;&nbsp;");
            printName(name, significantChange);
            out.print("</code></td>");
            if (identifier != null) {
                out.print("<td><code>&nbsp;&nbsp;");
                printName(identifier, significantChange);
                out.print("</code></td><td><font size=-1>");
                out.print(getSpecification(uml));
                out.print("</font></td>");
            } else if (javaMethods.contains(name)) {
                /*
                 * The 'doubleValue()' method is considered a Java method only in the case of
                 * the RepresentativeFraction interface, because the Javadoc of that interface
                 * encourage implementors to extend java.lang.Number.
                 */
                if (!name.equals("doubleValue") || classname.equals("RepresentativeFraction")) {
                    out.print("<td></td><td><font size=-1>Java</font></td>");
                }
            } else if (vecmathMethods.contains(name) && classname.equals("Matrix")) {
                out.print("<td></td><td><font size=-1>Vecmath</font></td>");
            }
            out.println("</tr>");
        }
        out.println("  <tr><td colspan=\"3\"><hr></td></tr>");
    }

    /**
     * Returns the display name for the given UML identifier.
     */
    private static String getDisplayName(final UML uml) {
        if (uml == null) {
            return null;
        }
        String identifier = uml.identifier();
        /*
         * If there is two or more UML identifiers collapsed in only one
         * Java method, keep only the first identifier (which is usually
         * the main attribute).
         */
        final int split = identifier.indexOf(',');
        if (split >= 0) {
            identifier = identifier.substring(0, split);
        }
        return identifier.substring(identifier.lastIndexOf('.') + 1);
    }

    /**
     * Returns the display name of the specification attribute in the given UML.
     */
    private static String getSpecification(final UML specification) {
        return specification.specification().name().replace("ISO_","ISO ").replace("OGC_","OGC ").replace('_', '-');
    }

    /**
     * Prints the given name, using the {@code <em>} style if it is a significant
     * name change compared to the ISO or OGC specification.
     */
    private void printName(final String name, final boolean significantChange) {
        if (significantChange) {
            out.print("<em>");
        }
        out.print(name);
        if (significantChange) {
            out.print("</em>");
        }
    }

    /**
     * Drops the two letter prefix for the given OGC name. This prefix normally appears only
     * in class name. However, it was also used in front of some code list in some pre-ISO
     * specifications.
     */
    private static String dropPrefix(String ogc) {
        if (ogc.length() >= 4 &&
            Character.isUpperCase(ogc.charAt(0)) &&
            Character.isUpperCase(ogc.charAt(1)) &&
            ogc.charAt(2)=='_')
        {
            ogc = ogc.substring(3);
        }
        return ogc;
    }

    /**
     * Changes the first character from an upper-case letter to an lower case one,
     * except if the second character is upper case as well. In this later case,
     * we assume that the name contains an upper-case acronym.
     */
    private static String firstCharAsLowerCase(final String name) {
        final int length = name.length();
        if (length >= 2) {
            final char c = name.charAt(0);
            if (Character.isUpperCase(c) && Character.isLowerCase(name.charAt(1))) {
                final StringBuilder buffer = new StringBuilder(length);
                buffer.append(Character.toLowerCase(c));
                buffer.append(name.substring(1));
                return buffer.toString();
            }
        }
        return name;
    }

    /**
     * Compares a GeoAPI name with a OGC name. Returns {@code true} if the
     * name change is not significant. Returns {@code false} otherwise.
     */
    private boolean similarClassName(final TypeDeclaration declaration, final String geoapi, String ogc) {
        if (ogc == null) {
            return false;
        }
        /*
         * The 2 letters prefix is always omitted.
         */
        ogc = dropPrefix(ogc);
        /*
         * For exceptions, the "Exception" suffix is always added after the class name.
         */
        if (declaration instanceof ClassDeclaration) {
            final Class<?> classe = getClass(declaration);
            if (Exception.class.isAssignableFrom(classe)) {
                ogc += "Exception";
            }
            /*
             * For CodeList, the "Code" suffix is usually omitted
             * (except if there is a name clash with an other class).
             */
            if (CodeList.class.isAssignableFrom(classe)) {
                if (geoapi.equals(ogc)) {
                    return true;
                }
                if (ogc.endsWith("Code")) {
                    ogc = ogc.substring(0, ogc.length()-4);
                }
            }
        }
        return geoapi.equals(ogc);
    }

    /**
     * Compares a GeoAPI name with a OGC name. Returns {@code true} if the
     * name change is not significant (e.g. addition of a {@code get} prefix).
     * Returns {@code false} otherwise.
     */
    @SuppressWarnings("fallthrough")
    private boolean similarMethodName(final MethodDeclaration declaration, String geoapi, String ogc) {
        if (ogc == null) {
            return false;
        }
        /*
         * Omit the "uses" or "includes" prefix used for associations in the UML.
         */
        if (ogc.startsWith("uses")) {
            ogc = firstCharAsLowerCase(ogc.substring(4));
        } else if (ogc.startsWith("includes")) {
            ogc = firstCharAsLowerCase(ogc.substring(8));
        }
        /*
         * GeoAPI usually (but not always) contains a 'get' prefix.
         * This prefix is optional. The case for the first remaining
         * character is adjusted (usually to a lower case).
         */
        final boolean startWithLowerCase = Character.isLowerCase(ogc.charAt(0));
        final Class<?> returnClass = getClass(declaration.getReturnType());
        if (returnClass.equals(Boolean.TYPE) || returnClass.equals(Boolean.class)) {
            if (geoapi.startsWith("is") && !ogc.startsWith("is")) {
                geoapi = geoapi.substring(2);
                if (startWithLowerCase) {
                    geoapi = firstCharAsLowerCase(geoapi);
                }
            }
        } else {
            if ((geoapi.startsWith("get") && !ogc.startsWith("get")) ||
                (geoapi.startsWith("set") && !ogc.startsWith("set")))
            {
                geoapi = geoapi.substring(3);
                if (startWithLowerCase) {
                    geoapi = firstCharAsLowerCase(geoapi);
                }
            }
        }
        /*
         * If the output type is an array or a collection, add a "s" suffix to OGC name.
         * However, not all methods has this 's'. For example 'getPalette' returns an
         * array of colors, but it still only one color palette.
         */
        if (geoapi.equals(ogc)) {
            return true;
        }
        if (returnClass.isArray() ||
            Collection              .class.isAssignableFrom(returnClass) ||
            Map                     .class.isAssignableFrom(returnClass) ||
            PointGrid               .class.isAssignableFrom(returnClass) ||
            PointArray              .class.isAssignableFrom(returnClass) ||
            ParameterValueGroup     .class.isAssignableFrom(returnClass) ||
            ParameterDescriptorGroup.class.isAssignableFrom(returnClass))
        {
            final int length = ogc.length();
            if (length != 0) {
                switch (ogc.charAt(length-1)) {
                    case 'y': ogc = ogc.substring(0, length-1) + "ies"; break;
                    case 's': if (length<2 || ogc.charAt(length-2)!='s') break;
                    case 'h': // fall through
                    case 'x': ogc += "es"; break;
                    default : ogc += 's'; break;
                }
            }
        }
        return geoapi.equals(ogc);
    }

    /**
     * Compares a GeoAPI name with an OGC name. Returns {@code true} if the
     * name change is not significant. Returns {@code false} otherwise.
     */
    private static boolean similarCodeName(String geoapi, String ogc) {
        if (ogc == null) {
            return false;
        }
        // Special cases that we don't want to consider as significant deviation.
        geoapi = geoapi.replace("CODE_LIST",  "CODELIST");
        ogc = firstCharAsLowerCase(dropPrefix(ogc));
        /*
         * GeoAPI constants are always in upper-case.
         */
        final StringBuilder buffer = new StringBuilder(geoapi.length());
        final int length = ogc.length();
        for (int i=0; i<length; i++) {
            final char c = ogc.charAt(i);
            if (Character.isSpaceChar(c)) {
                continue;
            }
            if (i != 0) {
                final char p = ogc.charAt(i-1);
                if (Character.isUpperCase(c) && Character.isLowerCase(p) ||
                    Character.isLetter   (c) != Character.isLetter   (p))
                {
                    // Next condition: special case for "1D", "2D" or "3D" suffixes.
                    if (!(i==length-1 && Character.isDigit(p) && c=='D')) {
                        buffer.append('_');
                    }
                }
            }
            buffer.append(Character.toUpperCase(c));
        }
        ogc = buffer.toString();
        return ogc.equals(geoapi);
    }
}
