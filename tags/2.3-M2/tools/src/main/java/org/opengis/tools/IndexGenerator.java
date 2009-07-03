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
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Collection;

// Annotation processing tools
import com.sun.mirror.apt.Filer;
import com.sun.mirror.type.InterfaceType;
import com.sun.mirror.util.Declarations;
import com.sun.mirror.declaration.Modifier;
import com.sun.mirror.declaration.TypeDeclaration;
import com.sun.mirror.declaration.ClassDeclaration;
import com.sun.mirror.declaration.MemberDeclaration;
import com.sun.mirror.declaration.MethodDeclaration;

// OpenGIS dependencies
import org.opengis.util.CodeList;
import org.opengis.annotation.UML;
import org.opengis.parameter.ParameterValueGroup;
import org.opengis.parameter.ParameterDescriptorGroup;
import org.opengis.geometry.coordinate.PointGrid;
import org.opengis.geometry.coordinate.PointArray;


/**
 * Generates a list of all type and methods, together with their ISO identifier.
 * The list will be written in the {@code ../../site/resources/content.html} file.
 * <p>
 * <b>How to use</b>
 * {@code chdir} to the root directory of source code. Then invoke the following command,
 * where {@code content.txt} is a file containing the path to all java classes to parse
 * in the {@value #ROOT_PACKAGE} package.
 *
 * <blockquote><pre>
 * apt -nocompile -factory org.opengis.tools.IndexGenerator @content.txt
 * </pre></blockquote>
 *
 * @author Martin Desruisseaux
 */
public class IndexGenerator extends UmlProcessor {
    /**
     * Set to {@code true} for printing some debug information to the standard output.
     */
    private static final boolean DEBUG = false;

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
    }

    /**
     * Processes all program elements supported by this annotation processor. This method scans
     * all interfaces and their methods (as well as code lists and their fields) and writes the
     * result to a HTML file.
     */
    @Override
    public void process() {
        final Filer filer = environment.getFiler();
        try {
            out = filer.createTextFile(Filer.Location.SOURCE_TREE, "",
                        new File("../../site/resources/content.html"), null);
        } catch (IOException exception) {
            printError(exception);
            return;
        }
        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
        out.println("<HTML>");
        out.println("  <HEAD>");
        out.println("    <TITLE>GeoAPI content</TITLE>");
        out.println("  </HEAD>");
        out.println("  <BODY>");
        out.println("  <H1>GeoAPI content</H1>");
        out.println("  <TABLE cellpadding='0' cellspacing='0'>");
        out.println("  <TR><TH bgcolor=\"#CCCCFF\">GeoAPI identifier</TH>" +
                          "<TH bgcolor=\"#CCCCFF\">ISO identifier</TH>" +
                          "<TH bgcolor=\"#CCCCFF\">Standard</TH></TR>");
        /*
         * Performs the analysis.
         */
        lastPackage = "";
        super.process();
        out.println("  </TABLE>");
        out.println("  </BODY>");
        out.println("</HTML>");
        out.close();
    }

    /**
     * Checks for classes. Will also checks for methods or fields inside each class.
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
        UML    uml                = declaration.getAnnotation(UML.class);
        String identifier         = getDisplayName(uml);
        String classname          = declaration.getSimpleName();
        String qualifiedClassname = declaration.getQualifiedName();
        String pathToClassJavadoc = "apidocs/" + qualifiedClassname.replace('.', '/') + ".html";
        boolean significantChange = !similarClassName(declaration, classname, identifier);
        final boolean isCodeList  = isCodeList(declaration);
        /*
         * Writes the class name. If the class is declared in a new package
         * compared to the previous one, writes the package name first.
         */
        if (true) {
            final int splitIndex = qualifiedClassname.lastIndexOf('.');
            final String packageName = (splitIndex >= 0) ? qualifiedClassname.substring(0, splitIndex) : qualifiedClassname;
            if (!packageName.equals(lastPackage)) {
                out.println("  <TR><TD COLSPAN=3>&nbsp;</TD></TR>");
                out.print  ("  <TR><TD COLSPAN=3 NOWRAP BGCOLOR=\"#DDDDFF\"><STRONG>Package&nbsp; <CODE>");
                out.print  (packageName);
                out.println("</CODE></STRONG></TD></TR>");
                out.println("  <TR><TD COLSPAN=3><HR></TD></TR>");
                lastPackage = packageName;
            }
            out.print("  <TR><TD NOWRAP><STRONG><CODE>&nbsp;&nbsp;</CODE>");
            out.print(isCodeList ? "Code list" : "Interface");
            out.print(" <CODE><A HREF=\"");
            out.print(pathToClassJavadoc);
            out.print("\">");
            printName(classname, significantChange);
            out.print("</A></CODE></STRONG></TD>");
            if (identifier != null) {
                out.print("<TD><CODE>");
                printName(identifier, significantChange);
                out.print("</CODE></TD><TD NOWRAP>");
                out.print(getSpecification(uml));
                out.print("</TD>");
            }
            out.println("</TR>");
        }
        /*
         * Iterates over the attributes.
         */
        final Collection<? extends MemberDeclaration> attributes =
                isCodeList ? declaration.getFields() : declaration.getMethods();
scan:   for (final MemberDeclaration attribute : attributes) {
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
            out.print("  <TR><TD><CODE>&nbsp;&nbsp;&nbsp;&nbsp;");
            printName(name, significantChange);
            out.print("</CODE></TD>");
            if (identifier != null) {
                out.print("<TD><CODE>&nbsp;&nbsp;");
                printName(identifier, significantChange);
                out.print("</CODE></TD><TD><FONT SIZE=-1>");
                out.print(getSpecification(uml));
                out.print("</FONT></TD>");
            }
            out.println("</TR>");
        }
        out.println("  <TR><TD COLSPAN=3><HR></TD></TR>");
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
        return specification.specification().name().replace('_', ' ');
    }

    /**
     * Prints the given name, using the {@code <CITE>} still if it is a significant
     * name change compared to the ISO or OGC specification.
     */
    private void printName(final String name, final boolean significantChange) {
        if (significantChange) {
            out.print("<CITE>");
        }
        out.print(name);
        if (significantChange) {
            out.print("</CITE>");
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
            final Class classe = getClass(declaration);
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
        final Class returnClass = getClass(declaration.getReturnType());
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
        if (DEBUG) {
            // Debug code
            if (!geoapi.equals(ogc)) {
                System.out.print("Member names : GeoAPI=");
                System.out.print(geoapi);
                System.out.print("  OGC=");
                System.out.println(ogc);
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
        if (DEBUG) {
            // Debug code
            if (!geoapi.equals(ogc)) {
                System.out.print("Code names   : GeoAPI=");
                System.out.print(geoapi);
                System.out.print("  OGC=");
                System.out.println(ogc);
            }
        }
        return ogc.equals(geoapi);
    }
}
