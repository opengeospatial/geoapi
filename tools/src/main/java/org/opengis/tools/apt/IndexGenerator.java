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

import java.io.IOException;
import java.io.Writer;
import java.util.Set;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Comparator;
import javax.tools.Diagnostic;
import javax.lang.model.SourceVersion;
import javax.lang.model.util.Elements;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeKind;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.SupportedSourceVersion;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedOptions;


/**
 * Generates a list of all type and methods, together with their ISO identifier.
 * The list will be written in the {@code ../javadoc/content.html} file, relative
 * to the current directory. The result is published online at
 * <a href="http://www.geoapi.org/snapshot/javadoc/content.html">http://www.geoapi.org/snapshot/javadoc/content.html</a>
 *
 * <b><u>How to use</u></b><br>
 * Instructions about this processor can be found one the <a href="http://www.geoapi.org/tools/index.html">Tools</a> page.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   2.0
 */
@SupportedSourceVersion(SourceVersion.RELEASE_6)
@SupportedAnnotationTypes(UmlProcessor.UML_CLASSNAME)
@SupportedOptions("output")
public class IndexGenerator extends UmlProcessor implements Comparator<TypeElement> {
    /**
     * Method names that are part of Java specification.
     */
    private final Set<String> javaMethods;

    /**
     * Method names that are part of Java3D vecmath.
     */
    private final Set<String> vecmathMethods;

    /**
     * The file to write.
     */
    private String outputFile;

    /**
     * The writer where to write the list.
     */
    private Writer out;

    /**
     * The platform-specific line separator.
     */
    private final String lineSeparator;

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
        lineSeparator  = System.getProperty("line.separator", "\n");
    }

    /**
     * Initializes this processor.
     *
     * @param processingEnv Provides access to the tools framework.
     */
    @Override
    public void init(final ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        outputFile = processingEnv.getOptions().get("output");
        if (outputFile == null) {
            processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "No 'output' option specified.");
            skip = true;
        }
    }

    /**
     * Writes the given string to the {@link #out} writer, followed by a line separator.
     */
    private void writeLine(final String line) throws IOException {
        out.write(line);
        out.write(lineSeparator);
    }

    /**
     * Compares the given elements for order.
     * This will determine the order in which API changes are reported on the HTML page.
     *
     * @param  t1 The first element.
     * @param  t2 The second element.
     * @return A negative number if {@code t1} should be printed before {@code t2}, or
     *         a positive number if {@code t1} should be printed after {@code t2}.
     */
    @Override
    public int compare(final TypeElement t1, final TypeElement t2) {
        int c = getPackageName(t1).compareToIgnoreCase(getPackageName(t2));
        if (c == 0) {
            c = getRelativeName(t1).compareToIgnoreCase(getRelativeName(t2));
        }
        return c;
    }

    /**
     * Processes all program elements supported by this annotation processor. This method gets
     * all interfaces and their methods (as well as code lists and their fields) and writes the
     * result to a HTML file.
     */
    @Override
    final void process(final TypeElement[] elements) throws IOException {
        Arrays.sort(elements, this);

        // Now write the HTML file.
        out = openWriter(outputFile);
        try {
            writeLine("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
            writeLine("<!DOCTYPE html>");
            writeLine("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
            writeLine("  <head>");
            writeLine("    <title>GeoAPI content</title>");
            writeLine("  </head>");
            writeLine("  <body><div>");
            writeLine("  <h1>GeoAPI content</h1>");
            writeLine("  <table cellpadding='0' cellspacing='0'>");
            writeLine("  <tr><th bgcolor=\"#CCCCFF\">GeoAPI identifier</th>" +
                          "<th bgcolor=\"#CCCCFF\">ISO identifier</th>" +
                          "<th bgcolor=\"#CCCCFF\">Standard</th></tr>");
            lastPackage = "";
            final Elements utils = processingEnv.getElementUtils();
            for (final TypeElement element : elements) {
                if (!utils.isDeprecated(element)) {
                    if (!utils.getPackageOf(element).getQualifiedName().toString().equals("org.opengis.annotation")) {
                        writeTypeElement(element);
                    }
                }
            }
            writeLine("  </table>");
            writeLine("  </div></body>");
            writeLine("</html>");
        } finally {
            out.close();
            out = null;
        }
    }

    /**
     * Writes the HTML records for the given class or interface.
     * If the type is declared in a new package compared to the previous type,
     * writes the package name first.
     */
    private void writeTypeElement(final TypeElement element) throws IOException {
        final AnnotationMirror uml      = getUML(element);
        final String identifier         = getDisplayName(uml);
        final String classname          = getRelativeName(element);
        final String qualifiedClassname = element.getQualifiedName().toString();
        final String pathToClassJavadoc = qualifiedClassname.replace('.', '/') + ".html";
        final boolean significantChange = !isEquivalentClassName(element, classname, identifier);
        final boolean isCodeList        = isSubtype(element.asType(), Classes.CODE_LIST);
        final String packageName        = getPackageName(element);
        if (!packageName.equals(lastPackage)) {
            writeLine("  <tr><td colspan=\"3\">&nbsp;</td></tr>");
            out.write("  <tr><td colspan=\"3\" nowrap bgcolor=\"#DDDDFF\"><b>Package&nbsp; <code>");
            out.write(packageName);
            writeLine("</code></b></td></tr>");
            writeLine("  <tr><td colspan=\"3\"><hr/></td></tr>");
            lastPackage = packageName;
        }
        out.write("  <tr><td nowrap><b><code>&nbsp;&nbsp;</code>");
        out.write(isCodeList ? "Code list" : element.getKind().isClass() ? "Class" : "Interface");
        out.write(" <code><a href=\"");
        out.write(pathToClassJavadoc);
        out.write("\">");
        printName(classname, significantChange);
        out.write("</a></code></b>");
        if (identifier != null) {
            out.write("<td><code>");
            printName(identifier, significantChange);
            out.write("</code></td><td nowrap>");
            out.write(getSpecification(uml));
            out.write("</td>");
        }
        writeLine("</tr>");
        for (final Element member : getMembers(element)) {
            if (member.getKind() == ElementKind.METHOD) {
                if (overrides((ExecutableElement) member)) {
                    continue;
                }
            }
            writeMemberElement(classname, member);
        }
        writeLine("  <tr><td colspan=\"3\"><hr></td></tr>");
    }

    /**
     * Writes the HTML records for the given field or method.
     *
     * @param classname The name of the enclosing class.
     * @param element   The member to write.
     */
    private void writeMemberElement(final String classname, final Element element) throws IOException {
        final AnnotationMirror uml = getUML(element);
        final String identifier = getDisplayName(uml);
        final String name = element.getSimpleName().toString();
        boolean significantChange = true;
        if (identifier != null) {
            final ElementKind kind = element.getKind();
            if (kind == ElementKind.METHOD) {
                significantChange = !(isEquivalentMethodName((ExecutableElement) element, name, identifier));
            } else if (kind.isField()) {
                significantChange = !(isEquivalentFieldName(name, identifier));
            }
        }
        out.write("  <tr><td><code>&nbsp;&nbsp;&nbsp;&nbsp;");
        printName(name, significantChange);
        out.write("</code></td>");
        if (identifier != null) {
            out.write("<td><code>&nbsp;&nbsp;");
            printName(identifier, significantChange);
            out.write("</code></td><td><font size=-1>");
            out.write(getSpecification(uml));
            out.write("</font></td>");
        } else if (javaMethods.contains(name)) {
            /*
             * The 'doubleValue()' method is considered a Java method only in the case of
             * the RepresentativeFraction interface, because the Javadoc of that interface
             * encourage implementors to extend java.lang.Number.
             */
            if (!name.equals("doubleValue") || classname.equals("RepresentativeFraction")) {
                out.write("<td></td><td><font size=-1>Java</font></td>");
            }
        } else if (vecmathMethods.contains(name) && classname.equals("Matrix")) {
            out.write("<td></td><td><font size=-1>Vecmath</font></td>");
        }
        writeLine("</tr>");
    }

    /**
     * Prints the given name, using the {@code <em>} style if it is a significant
     * name change compared to the ISO or OGC specification.
     */
    private void printName(final String name, final boolean significantChange) throws IOException {
        if (significantChange) {
            out.write("<em>");
        }
        out.write(name);
        if (significantChange) {
            out.write("</em>");
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
            ogc.charAt(2) == '_')
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
     * Compares a GeoAPI class name with a OGC identifier name.
     * Returns {@code true} if the name change is not significant.
     * Returns {@code false} otherwise.
     */
    private boolean isEquivalentClassName(final Element element, final String geoapi, String ogc) {
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
        if (element.getKind() == ElementKind.CLASS) {
            if (isSubtype(element.asType(), Classes.EXCEPTION)) {
                ogc += "Exception";
            }
            /*
             * For CodeList, the "Code" suffix is usually omitted
             * (except if there is a name clash with an other class).
             */
            if (isSubtype(element.asType(), Classes.CODE_LIST)) {
                if (geoapi.equals(ogc)) {
                    return true;
                }
                if (ogc.endsWith("Code")) {
                    ogc = ogc.substring(0, ogc.length() - 4);
                }
            }
        }
        return geoapi.equals(ogc);
    }

    /**
     * Compares a GeoAPI method name with an OGC identifier name.
     * Returns {@code true} if the name change is not significant (e.g. addition of a {@code get} prefix).
     * Returns {@code false} otherwise.
     */
    @SuppressWarnings("fallthrough")
    private boolean isEquivalentMethodName(final ExecutableElement element, String geoapi, String ogc) {
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
        final TypeMirror returnType = element.getReturnType();
        if (returnType.getKind() == TypeKind.BOOLEAN || isSubtype(returnType, Classes.BOOLEAN)) {
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
        if (!isMultiOccurrence(returnType)) {
            return false;
        }
        final int length = ogc.length();
        if (length != 0) {
            switch (ogc.charAt(length - 1)) {
                case 'y': ogc = ogc.substring(0, length-1) + "ies"; break;
                case 's': if (length < 2 || ogc.charAt(length-2) != 's') break;
                case 'h': // fall through
                case 'x': ogc += "es"; break;
                default : ogc += 's'; break;
            }
        }
        return geoapi.equals(ogc);
    }

    /**
     * Compares a GeoAPI code list or enumeration name with an OGC identifier name.
     * Returns {@code true} if the name change is not significant.
     * Returns {@code false} otherwise.
     */
    private static boolean isEquivalentFieldName(String geoapi, String ogc) {
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
                    if (!(i == length-1 && Character.isDigit(p) && c == 'D')) {
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
