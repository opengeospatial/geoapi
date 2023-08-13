/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2023 Open Geospatial Consortium, Inc.
 *    http://www.geoapi.org
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.opengis.tools.apt;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.Comparator;
import java.util.Properties;
import javax.tools.Diagnostic;
import java.io.FileInputStream;
import java.io.Writer;
import java.io.IOException;
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
 * Generates a list of all types and methods, together with their ISO identifier. The list will be written in the
 * given output file (usually "{@code geoapi/src/main/javadoc/content.html}"). The result is published online at
 * <a href="http://www.geoapi.org/snapshot/javadoc/content.html">http://www.geoapi.org/snapshot/javadoc/content.html</a>
 *
 * <p><b><u>Usage</u></b></p>
 * Instructions about this processor can be found in the module javadoc.
 * Options are:
 * <ul>
 *   <li>{@code output} (mandatory): where to write the HTML page.</li>
 *   <li>{@code notesList} (optional): path to the "{@code src/release-notes.properties}" file.</li>
 * </ul>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   2.0
 */
@SupportedSourceVersion(SourceVersion.RELEASE_11)
@SupportedAnnotationTypes(UmlProcessor.UML_CLASSNAME)
@SupportedOptions({"output", "notesList"})
public class IndexGenerator extends UmlProcessor implements Comparator<TypeElement> {
    /**
     * The GeoAPI version for which we are generating the content list.
     */
    private static final String VERSION = "3.1-SNAPSHOT";

    /**
     * Method names that are part of Java specification.
     */
    private final Set<String> javaMethods;

    /**
     * Method names that are part of Java3D vecmath.
     */
    private final Set<String> vecmathMethods;

    /**
     * Symbols to write in the "notes" column.
     */
    private final Properties notes;

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
        javaMethods    = Set.of("toString", "clone", "equals", "hashCode", "doubleValue");
        vecmathMethods = Set.of("getNumRow", "getNumCol", "getElement", "setElement");
        lineSeparator  = System.getProperty("line.separator", "\n");
        notes          = new Properties();
    }

    /**
     * Initializes this processor.
     *
     * @param processingEnv Provides access to the tools framework.
     */
    @Override
    public void init(final ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        final Map<String,String> options = processingEnv.getOptions();
        outputFile = options.get("output");
        if (outputFile == null) {
            processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "No 'output' option specified.");
            skip = true;
        }
        final String notesList = options.get("notesList");
        if (notesList != null) {
            try (FileInputStream in = new FileInputStream(notesList)) {
                notes.load(in);
            } catch (IOException e) {
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
                        "Cannot read the \"" + notesList + "\" file:" + lineSeparator + e);
            }
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
     * @return a negative number if {@code t1} should be printed before {@code t2}, or
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
            writeLine("<!DOCTYPE html>");
            writeLine("<html>");
            writeLine("  <head>");
            writeLine("    <title>GeoAPI content</title>");
            writeLine("    <meta charset=\"UTF-8\"/>");
            writeLine("    <link rel=\"stylesheet\" type=\"text/css\" href=\"content.css\"/>");
            writeLine("  </head>");
            writeLine("  <body>");
            writeLine("  <h1>GeoAPI content</h1>");
            writeLine("  <p>This page lists all non-deprecated GeoAPI " + VERSION + " methods and fields,");
            writeLine("     together with the identifiers specified in the international standards published by");
            writeLine("     <abbr title=\"Open Geospatial Consortium\">OGC</abbr> and");
            writeLine("     <abbr title=\"International Organization for Standardization\">ISO</abbr>.");
            writeLine("     Versions of the standard used by GeoAPI are documented in the");
            writeLine("     <a href=\"org/opengis/annotation/Specification.html\"><code>Specification</code></a> javadoc.");
            writeLine("     Version different than the default one are specified by a colon followed by the publication year,");
            writeLine("     for example “ISO 19115:2003”.</p>");
            writeLine("  <p>Differences between the <abbr>OGC</abbr>/<abbr>ISO</abbr> identifiers and the GeoAPI name");
            writeLine("     are mostly for compliance with Java usage, for example by adding the <code>get</code> prefix.");
            writeLine("     More significant name changes are emphasized by italics.</p>");
            writeLine("  <p id=\"notes\">The “Note” column can contain the following values:</p>");
            writeLine("  <table>");
            writeLine("    <tr><td>(N)</td>  <td>for new methods added in GeoAPI " + VERSION + " (sometimes merely renaming of older methods).</td></tr>");
            writeLine("    <tr><td>(I)</td>  <td>for incompatible changes compared to the previous GeoAPI release (should be very rare in minor releases).</td></tr>");
            writeLine("    <tr><td>(MC)</td> <td>for methods that may change in an incompatible way in the next major release.");
            writeLine("    Those changes are <em>not</em> applied in this release, but are flagged for allowing developers to anticipate.</td></tr>");
            writeLine("  </table>");
            out.write(lineSeparator);
            writeLine("  <table>");
            writeLine("  <caption>All non-deprecated GeoAPI " + VERSION + " fields and methods</caption>");
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
            writeLine("  </body>");
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
            out.write("  <tr><th class=\"package\" colspan=\"4\">Package <code>");
            out.write(packageName);
            writeLine("</code></th></tr>");
            writeLine("  <tr><th class=\"header\">GeoAPI type or member</th>" +
                            "<th class=\"header\">OGC/ISO identifier</th>" +
                            "<th class=\"header\">Standard</th>" +
                            "<th class=\"header\">Note</th></tr>");
            lastPackage = packageName;
        }
        out.write("  <tr><td class=\"type\">");
        out.write(isCodeList ? "Code list" : element.getKind().isClass() ? "Class" : "Interface");
        out.write(" <code><a href=\"");
        out.write(pathToClassJavadoc);
        out.write("\">");
        printName(classname, significantChange);
        out.write("</a></code></td>");
        if (identifier != null) {
            out.write("<td id=\"");
            out.write(identifier);
            out.write("\"><code>");
            printName(identifier, significantChange);
            out.write("</code>");
        } else {
            out.write("<td>");
        }
        out.write("</td><td colspan=\"2\">");
        if (uml != null) {
            out.write(getSpecification(uml));
        }
        writeLine("</td></tr>");
        for (final Element member : getMembers(element)) {
            switch (member.getKind()) {
                case METHOD: {
                    if (!overrides((ExecutableElement) member)) {
                        writeMemberElement(classname, member);
                    }
                    break;
                }
                case FIELD:
                case ENUM_CONSTANT: {
                    writeMemberElement(classname, member);
                    break;
                }
            }
        }
        writeLine("  <tr><td class=\"separator\" colspan=\"4\"><hr/></td></tr>");
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
        boolean significantChange = false;
        if (identifier != null) {
            final ElementKind kind = element.getKind();
            if (kind == ElementKind.METHOD) {
                significantChange = !(isEquivalentMethodName((ExecutableElement) element, name, identifier));
            } else if (kind.isField()) {
                significantChange = !(isEquivalentFieldName(name, identifier));
            }
        }
        out.write("  <tr><td class=\"member\"><code>");
        printName(name, significantChange);
        out.write("</code></td><td class=\"member\">");
        if (identifier != null) {
            out.write("<code>");
            printName(identifier, significantChange);
            out.write("</code>");
        }
        out.write("</td><td class=\"spec\">");
        if (uml != null) {
            out.write(getSpecification(uml));
        } else if (javaMethods.contains(name)) {
            /*
             * The 'doubleValue()' method is considered a Java method only in the case of
             * the RepresentativeFraction interface, because the Javadoc of that interface
             * encourage implementers to extend java.lang.Number.
             */
            if (!name.equals("doubleValue") || classname.equals("RepresentativeFraction")) {
                out.write("Java");
            }
        } else if (vecmathMethods.contains(name) && classname.equals("Matrix")) {
            out.write("Vecmath");
        }
        out.write("</td>");
        /*
         * Write the symbols referring to notes.
         */
        String note = notes.getProperty(getQualifiedName(element));
        if (note != null) {
            switch (note) {
                case "N":
                    out.write("<td class=\"new\"><a href=\"#notes\">(N)</a>");
                    break;
                case "I":
                    out.write("<td class=\"incompatible\"><a href=\"#notes\">(I)</a>");
                    break;
                case "MC":
                    out.write("<td class=\"warning\"><a href=\"#notes\">(MC)</a>");
                    break;
                default:
                    processingEnv.getMessager().printMessage(Diagnostic.Kind.WARNING, "Unknown note: " + name + " = " + note);
                    out.write("<td>");
                    out.write(note);
                    break;
            }
        } else {
            out.write("<td>");
        }
        writeLine("</td></tr>");
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
     * except if the second character is upper case as well. In this latter case,
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
             * (except if there is a name clash with another class).
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
        }
        if ((geoapi.startsWith("get") && !ogc.startsWith("get")) ||
            (geoapi.startsWith("set") && !ogc.startsWith("set")))
        {
            geoapi = geoapi.substring(3);
            if (startWithLowerCase) {
                geoapi = firstCharAsLowerCase(geoapi);
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
        if (ogc.equals(geoapi)) {
            return true;
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
            if (!Character.isLetterOrDigit(c)) {
                continue;
            }
            if (i != 0) {
                final char p = ogc.charAt(i-1);
                boolean separateWords = Character.isUpperCase(c) && Character.isLowerCase(p);   // Check for camel case.
                if (!separateWords) {
                    separateWords = Character.isLetter(c) != Character.isLetter(p);             // e.g. "iso9660" → "ISO_9660"
                    if (separateWords) {
                        /*
                         * At this point we have detected that we may need to separate a word from numbers
                         * (or conversely) as in "ISO_9660". However, we need to make an exception for the
                         * "1D", "2D" or "3D" suffixes, as in "COMPOUND_GEOGRAPHIC2D_VERTICAL".  Note that
                         * the "2D" suffix may or may not be at the end of the name, but we will insert an
                         * underscore only if the suffix is at the end of the name.
                         */
                        if (Character.isDigit(p) && c == 'D') {
                            // Prevent insertion of an underscore in "2D" (we do not want "2_D").
                            separateWords = (i+1 < length) && !Character.isLowerCase(ogc.charAt(i+1));
                        } else if (Character.isLowerCase(p) && Character.isDigit(c)) {
                            /*
                             * Prevent insertion of an underscore before "2D" if not at the end of the name
                             * (e.g. "COMPOUND_GEOGRAPHIC2D_VERTICAL"). If we detect such case, we will have
                             * to append the character in a special way in order to insert the underscore at
                             * a position not yet reached by the iteration (between 2 upper-case letters, so
                             * normally not a position where we would insert an underscore).
                             */
                            separateWords = (i+2 >= length) || ogc.charAt(i+1) != 'D' || Character.isLowerCase(ogc.charAt(i+2));
                            if (!separateWords) {
                                buffer.append(c).append("D_");
                                i++;
                                continue;
                            }
                        }
                    }
                }
                if (separateWords) {
                    buffer.append('_');
                }
            }
            buffer.append(Character.toUpperCase(c));
        }
        ogc = buffer.toString();
        return ogc.equals(geoapi);
    }
}
