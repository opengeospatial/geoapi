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
import java.io.StringWriter;
import java.util.Map;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Collection;

// Annotation processing tools
import com.sun.mirror.apt.Filer;
import com.sun.mirror.type.InterfaceType;
import com.sun.mirror.util.Declarations;
import com.sun.mirror.declaration.Modifier;
import com.sun.mirror.declaration.Declaration;
import com.sun.mirror.declaration.TypeDeclaration;
import com.sun.mirror.declaration.ClassDeclaration;
import com.sun.mirror.declaration.FieldDeclaration;
import com.sun.mirror.declaration.MemberDeclaration;
import com.sun.mirror.declaration.MethodDeclaration;

// OpenGIS dependencies
import org.opengis.util.CodeList;
import org.opengis.annotation.UML;
import org.opengis.annotation.XmlElement;
import org.opengis.parameter.ParameterValueGroup;
import org.opengis.parameter.ParameterDescriptorGroup;
import org.opengis.geometry.coordinate.PointGrid;
import org.opengis.geometry.coordinate.PointArray;


/**
 * Checks interfaces and attributes names against the name declared in the annotation tags.
 * All differences are listed in HTML files.
 * <p>
 * <b>How to use</b>
 * {@code chdir} to the root directory of source code. Then invoke the following command,
 * where {@code opengis.txt} is a file containing the path to all java classes to parse
 * in the {@value #ROOT_PACKAGE} package.
 *
 * <blockquote><pre>
 * apt -nocompile -factory org.opengis.tools.ModelComparator @opengis.txt
 * </pre></blockquote>
 *
 * The HTML files will be stored in the <code>{@value #ROOT_PACKAGE}/doc-files</code> package.
 *
 * @author Martin Desruisseaux
 */
public class ModelComparator extends UmlProcessor {
    /**
     * Set to {@code true} for printing some debug information to the standard output.
     */
    private static final boolean DEBUG = false;

    /**
     * The root package.
     */
    public static final String ROOT_PACKAGE = "org.opengis";

    /**
     * The string to use for classes/methods adapted from legacy specifications.
     */
    private static final String LEGACY = "(none - adapted from legacy OGC specification)";

    /**
     * The string to use for classes/methods that are GeoAPI extension.
     */
    private static final String EXTENSION = "(none - this is a GeoAPI extension)";

    /**
     * Comparator used for sorting members according their name.
     */
    private static final Comparator<MemberDeclaration> MEMBER_COMPARATOR = new Comparator<MemberDeclaration>() {
        public int compare(final MemberDeclaration m1, final MemberDeclaration m2) {
            return m1.getSimpleName().compareTo(m2.getSimpleName());
        }
    };

    /**
     * The writer where to write classes informations.
     */
    private PrintWriter classes;

    /**
     * The writer where to write members informations.
     */
    private PrintWriter members;

    /**
     * Last package visited.
     */
    private String lastPackage;

    /**
     * Creates a default processor.
     */
    public ModelComparator() {
    }

    /**
     * Process all program elements supported by this annotation processor. This method scan
     * all interfaces and their methods (as well as code lists and their fields) and write
     * the comparaison result to a HTML file.
     */
    @Override
    public void process() {
        final Filer filer = environment.getFiler();
        try {
            classes = filer.createTextFile(Filer.Location.SOURCE_TREE, ROOT_PACKAGE,
                        new File("doc-files/departures-list.html"), null);
        } catch (IOException exception) {
            printError(exception);
            return;
        }
        classes.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
        classes.println("<HTML>");
        classes.println("  <HEAD>");
        classes.println("    <TITLE>Significant name changes between GeoAPI and OGC models</TITLE>");
        classes.println("  </HEAD>");
        classes.println("  <BODY>");
        classes.println("  <H1>Significant name changes for classes</H1>");
        classes.println("  <P>This list do not includes the following changes:</P>");
        classes.println("  <UL>");
        classes.println("    <LI>Omission of 2 letters prefix (<CODE>MD_</CODE>, <CODE>RS_</CODE>, etc...)</LI>");
        classes.println("    <LI>Omission of <CODE>Code</CODE> suffix for code list</LI>");
        classes.println("    <LI>Addition of <CODE>Exception</CODE> suffix for exceptions</LI>");
        classes.println("  </UL>");
        classes.println("  <TABLE cellpadding='0' cellspacing='0'>");
        classes.println("  <TR><TH bgcolor='#CCCCFF'>GeoAPI name</TH><TH bgcolor='#CCCCFF'>ISO name</TH></TR>");
        /*
         * Open the stream where to write attributes differences differences.
         */
        final StringWriter buffer = new StringWriter();
        members = new PrintWriter(buffer);
        members.println("<P>&nbsp;</P>");
        members.println("<HR>");
        members.println("<P>&nbsp;</P>");
        members.println("  <H1>Significant name changes for operations, attributes or codes</H1>");
        members.println("  <P>This list do not includes the following changes:</P>");
        members.println("  <UL>");
        members.println("    <LI>Omission of 2 letters prefix (<CODE>MD_</CODE>, <CODE>RS_</CODE>, etc...)</LI>");
        members.println("    <LI>Omission of <CODE>uses</CODE> or <CODE>includes</CODE> prefixes for associations</LI>");
        members.println("    <LI>Addition of <CODE>get</CODE> or <CODE>is</CODE> prefix</LI>");
        members.println("    <LI>Plural form for methods returning an array or a collection</LI>");
        members.println("    <LI>Code list elements in upper case</LI>");
        members.println("  </UL>");
        members.println("  <TABLE cellpadding='0' cellspacing='0'>");
        members.println("  <TR><TH bgcolor='#CCCCFF'>GeoAPI name</TH bgcolor='#CCCCFF'><TH>ISO name</TH></TR>");
        /*
         * Performs the analysis.
         */
        lastPackage = "";
        super.process();
        members.println("  </TABLE>");
        members.close();
        classes.println("  </TABLE>");
        classes.print(buffer);
        classes.println("  </BODY>");
        classes.println("</HTML>");
        classes.close();
    }

    /**
     * Checks for class changes or additions. Will also checks for methods or fields
     * inside that class.
     */
    @Override
    public void visitTypeDeclaration(final TypeDeclaration declaration) {
        super.visitTypeDeclaration(declaration);
        String identifier         = getUmlDisplay(declaration);
        String classname          = declaration.getSimpleName();
        String qualifiedClassname = declaration.getQualifiedName();
        String pathToClassJavadoc = qualifiedClassname.replace('.', '/');
        if (pathToClassJavadoc.startsWith("org/opengis/")) {
            pathToClassJavadoc = "../" + pathToClassJavadoc.substring(12) + ".html";
        } else {
            pathToClassJavadoc = "../../../" + pathToClassJavadoc + ".html";
        }
        if (!compareClassName(declaration, classname, identifier)) {
            final int splitIndex = qualifiedClassname.lastIndexOf('.');
            final String packageName = (splitIndex >= 0) ? qualifiedClassname.substring(0, splitIndex) : qualifiedClassname;
            if (!packageName.equals(lastPackage)) {
                classes.println("  <TR><TD>&nbsp;</TD></TR>");
                classes.print  ("  <TR><TD bgcolor='#DDDDFF'><STRONG>Package&nbsp; <CODE>");
                classes.print  (packageName);
                classes.println("</CODE></STRONG></TD></TR>");
                lastPackage = packageName;
            }
            classes.print("  <TR><TD><CODE><A HREF=\"");
            classes.print(pathToClassJavadoc);
            classes.print("\">");
            classes.print(classname);
            classes.print("</A></CODE></TD>");
            classes.print("<TD><CODE>&nbsp;&nbsp;");
            if (identifier!=LEGACY && identifier!=EXTENSION) {
                classes.print(identifier);
                classes.print("</CODE></TD>");
            } else {
                classes.print("</CODE><FONT SIZE='-1' COLOR='#808080'>");
                classes.print(identifier);
                classes.print("</FONT></TD>");
            }
            classes.println("</TR>");
        }
        /*
         * Check for changes in attributes.
         */
        final MemberDeclaration[] attributes;
        final boolean isCodeList = isCodeList(declaration);
        if (isCodeList) {
            final Collection<FieldDeclaration> c = declaration.getFields();
            attributes = c.toArray(new FieldDeclaration[c.size()]);
        } else {
            final Collection<? extends MethodDeclaration> c = declaration.getMethods();
            attributes = c.toArray(new MethodDeclaration[c.size()]);
        }
        Arrays.sort(attributes, MEMBER_COMPARATOR);
        boolean classHeader = false;

scanMembers:
        for (final MemberDeclaration attribute : attributes) {
            if (!attribute.getModifiers().contains(Modifier.PUBLIC)) {
                continue;
            }
            identifier = getUmlDisplay(attribute);
            final String name = attribute.getSimpleName();
            if (identifier != EXTENSION) {
                if (attribute instanceof MethodDeclaration) {
                    if (compareMethodName((MethodDeclaration) attribute, name, identifier)) {
                        continue;
                    }
                } else {
                    if (compareCodeName(name, identifier)) {
                        continue;
                    }
                }
            } else if (attribute instanceof MethodDeclaration) {
                /*
                 * No UML identifier. Doesn't mind if this method overrides
                 * a method defined in super interface.
                 */
                final MethodDeclaration method = (MethodDeclaration) attribute;
                final Declarations util = environment.getDeclarationUtils();
                for (final InterfaceType parent : declaration.getSuperinterfaces()) {
                    for (final MethodDeclaration candidate : parent.getDeclaration().getMethods()) {
                        if (util.overrides(candidate, method)) {
                            continue scanMembers; // Found the method in superclass: don't document it.
                        }
                    }
                }
            }
            if (!classHeader) {
                members.println("  <TR><TD>&nbsp;</TD></TR>");
                members.print  ("  <TR><TD bgcolor='#DDDDFF'><STRONG>");
                members.print  (isCodeList ? "Code list" : "Interface");
                members.print  ("&nbsp; <CODE><A HREF=\"");
                members.print  (pathToClassJavadoc);
                members.print  ("\">");
                members.print  (classname);
                members.println("</A></CODE></STRONG></TD></TR>");
                classHeader = true;
            }
            members.print("  <TR><TD><CODE>");
            members.print(name);
            members.print("</CODE></TD>");
            members.print("<TD><CODE>&nbsp;&nbsp;");
            if (identifier!=LEGACY && identifier!=EXTENSION) {
                members.print(identifier);
                members.print("</CODE>");
            } else {
                members.print("</CODE><FONT SIZE='-1' COLOR='#808080'>");
                members.print(identifier);
                members.print("</FONT>");
            }
            members.println("</TD></TR>");
        }
    }

    /**
     * Returns the UML identifier for the specified element, or {@code null}
     * if the specified element is not part of the UML model.
     */
    private static String getUmlDisplay(final Declaration element) {
        String identifier;
        final UML uml = element.getAnnotation(UML.class);
        if (uml != null) {
            switch (uml.specification()) {
                case OGC_01009: return LEGACY;
            }
            identifier = uml.identifier();
        } else {
            final XmlElement xml = element.getAnnotation(XmlElement.class);
            if (xml != null) {
                identifier = xml.value();
            } else {
                return EXTENSION;
            }
        }
        /*
         * If there is two or more UML identifiers collapsed in only one
         * Java method, keep only the first identifier (which is usually
         * the main attribute).
         */
        final int split = identifier.indexOf(',');
        if (split >= 0) {
            identifier = identifier.substring(0, split);
        }
        return identifier;
    }

    /**
     * Drop the two letter prefix for the given OGC name. This prefix normally appears only
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
    private boolean compareClassName(final TypeDeclaration declaration, final String geoapi, String ogc) {
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
    private boolean compareMethodName(final MethodDeclaration declaration, String geoapi, String ogc) {
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
    private static boolean compareCodeName(String geoapi, String ogc) {
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
