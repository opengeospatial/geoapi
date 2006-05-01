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
import java.io.*;
import java.util.*;
import java.lang.reflect.*;

// OpenGIS dependencies
import org.opengis.util.CodeList;
import org.opengis.annotation.UML;
import org.opengis.annotation.XmlElement;
import org.opengis.annotation.Specification;
import org.opengis.parameter.ParameterValueGroup;
import org.opengis.parameter.ParameterDescriptorGroup;
import org.opengis.spatialschema.geometry.geometry.PointGrid;
import org.opengis.spatialschema.geometry.geometry.PointArray;


/**
 * Checks interfaces and attributes names against the name declared in the annotation tags.
 * All differences are listed in HTML files.
 *
 * @author Martin Desruisseaux
 */
public class ModelComparator {
    /**
     * The line separator.
     */
    private static final String lineSeparator = System.getProperty("line.separator", "\n");

    /**
     * The string to use for classes/methods adapted from legacy specifications.
     */
    private static final String LEGACY = "(none - adapted from legacy OGC specification)";

    /**
     * The string to use for classes/methods that are GeoAPI extension.
     */
    private static final String EXTENSION = "(none - this is a GeoAPI extension)";

    /**
     * Do not allows instances of this class.
     */
    private ModelComparator() {
    }

    /**
     * Scan all classes and members and write the differences to the stream.
     */
    public static void main(String[] args) throws IOException {
        /*
         * Open the stream where to write class differences.
         */
        final Writer classes = new BufferedWriter(new FileWriter("departures-list.html"));
        classes.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
        classes.write(lineSeparator);
        classes.write("<HTML>");
        classes.write(lineSeparator);
        classes.write("  <HEAD>");
        classes.write(lineSeparator);
        classes.write("    <TITLE>Significant name changes between GeoAPI and OGC models</TITLE>");
        classes.write(lineSeparator);
        classes.write("  </HEAD>");
        classes.write(lineSeparator);
        classes.write("  <BODY>");
        classes.write(lineSeparator);
        classes.write("  <H1>Significant name changes for classes</H1>");
        classes.write(lineSeparator);
        classes.write("  <P>This list do not includes the following changes:</P>");
        classes.write(lineSeparator);
        classes.write("  <UL>");
        classes.write(lineSeparator);
        classes.write("    <LI>Omission of 2 letters prefix (<CODE>MD_</CODE>, <CODE>RS_</CODE>, etc...)</LI>");
        classes.write(lineSeparator);
        classes.write("    <LI>Omission of <CODE>Code</CODE> suffix for code list</LI>");
        classes.write(lineSeparator);
        classes.write("    <LI>Addition of <CODE>Exception</CODE> suffix for exceptions</LI>");
        classes.write(lineSeparator);
        classes.write("  </UL>");
        classes.write(lineSeparator);
        classes.write("  <TABLE cellpadding='0' cellspacing='0'>");
        classes.write(lineSeparator);
        classes.write("  <TR><TH bgcolor='#CCCCFF'>GeoAPI name</TH><TH bgcolor='#CCCCFF'>ISO name</TH></TR>");
        classes.write(lineSeparator);
        /*
         * Open the stream where to write attributes differences differences.
         */
        final Writer members = new StringWriter();
        members.write("<P>&nbsp;</P>");
        members.write(lineSeparator);
        members.write("<HR>");
        members.write(lineSeparator);
        members.write("<P>&nbsp;</P>");
        members.write(lineSeparator);
        members.write("  <H1>Significant name changes for operations, attributes or codes</H1>");
        members.write(lineSeparator);
        members.write("  <P>This list do not includes the following changes:</P>");
        members.write(lineSeparator);
        members.write("  <UL>");
        members.write(lineSeparator);
        members.write("    <LI>Omission of 2 letters prefix (<CODE>MD_</CODE>, <CODE>RS_</CODE>, etc...)</LI>");
        members.write(lineSeparator);
        members.write("    <LI>Omission of <CODE>uses</CODE> or <CODE>includes</CODE> prefixes for associations</LI>");
        members.write(lineSeparator);
        members.write("    <LI>Addition of <CODE>get</CODE> or <CODE>is</CODE> prefix</LI>");
        members.write(lineSeparator);
        members.write("    <LI>Plural form for methods returning an array or a collection</LI>");
        members.write(lineSeparator);
        members.write("    <LI>Code list elements in upper case</LI>");
        members.write(lineSeparator);
        members.write("  </UL>");
        members.write(lineSeparator);
        members.write("  <TABLE cellpadding='0' cellspacing='0'>");
        members.write(lineSeparator);
        members.write("  <TR><TH bgcolor='#CCCCFF'>GeoAPI name</TH bgcolor='#CCCCFF'><TH>ISO name</TH></TR>");
        members.write(lineSeparator);
        /*
         * Process to the analysis.
         */
        String lastPackage = "";
        for (final Class classe : ClassFinder.getClasses(CodeList.class)) {
            /*
             * Check for class changes or additions.
             */
            String identifier = getIdentifier(classe);
            final String classname = ClassFinder.getShortName(classe);
            final String qualifiedClassname = classe.getName();
            String pathToClassJavadoc = qualifiedClassname.replace('.', '/');
            if (pathToClassJavadoc.startsWith("org/opengis/")) {
                pathToClassJavadoc = "../" + pathToClassJavadoc.substring(12) + ".html";
            } else {
                pathToClassJavadoc = "../../../" + pathToClassJavadoc + ".html";
            }
            if (identifier==null || !compareClassName(classe, classname, identifier)) {
                final int splitIndex = qualifiedClassname.lastIndexOf('.');
                final String packageName = (splitIndex >= 0) ? qualifiedClassname.substring(0, splitIndex) : qualifiedClassname;
                if (!packageName.equals(lastPackage)) {
                    classes.write("  <TR><TD>&nbsp;</TD></TR>");
                    classes.write(lineSeparator);
                    classes.write("  <TR><TD bgcolor='#DDDDFF'><STRONG>Package&nbsp; <CODE>");
                    classes.write(packageName);
                    classes.write("</CODE></STRONG></TD></TR>");
                    classes.write(lineSeparator);
                    lastPackage = packageName;
                }
                classes.write("  <TR><TD><CODE><A HREF=\"");
                classes.write(pathToClassJavadoc);
                classes.write("\">");
                classes.write(classname);
                classes.write("</A></CODE></TD>");
                classes.write("<TD><CODE>&nbsp;&nbsp;");
                if (identifier!=LEGACY && identifier!=EXTENSION) {
                    classes.write(identifier);
                    classes.write("</CODE></TD>");
                } else {
                    classes.write("</CODE><FONT SIZE='-1' COLOR='#808080'>");
                    classes.write(identifier);
                    classes.write("</FONT></TD>");
                }
                classes.write("</TR>");
                classes.write(lineSeparator);
                if (identifier == null) {
                    continue;
                }
            }
            /*
             * Check for changes in attributes.
             */
            final Member[] attributes;
            if (CodeList.class.isAssignableFrom(classe)) {
                attributes = classe.getDeclaredFields();
            } else {
                attributes = classe.getDeclaredMethods();
            }
            Arrays.sort(attributes, new Comparator<Member>() {
                public int compare(final Member m1, final Member m2) {
                    return m1.getName().compareTo(m2.getName());
                }
            });
            boolean classHeader = false;
scanMembers:for (final Member attribute : attributes) {
                if (!Modifier.isPublic(attribute.getModifiers())) {
                    continue;
                }
                identifier = getIdentifier((AnnotatedElement) attribute);
                final String name = attribute.getName();
                if (identifier != null) {
                    if (attribute instanceof Method) {
                        if (compareMethodName((Method) attribute, name, identifier)) {
                            continue;
                        }
                    } else {
                        if (compareCodeName((Field) attribute, name, identifier)) {
                            continue;
                        }
                    }
                } else if (attribute instanceof Method) {
                    /*
                     * No UML identifier. Doesn't mind if this method overrides
                     * a method defined in super interface.
                     *
                     * TODO: We shouldn't not! Annotation should contains @Inherit
                     *       if we want to do so.
                     */
                    for (final Class parent : classe.getInterfaces()) {
                        try {
                            parent.getMethod(name, ((Method) attribute).getParameterTypes());
                            continue scanMembers; // Found the method in superclass: don't document it.
                        } catch (NoSuchMethodException ignore) {
                            // No such method in superclass. Checks other interfaces.
                        }
                    }
                }
                if (!classHeader) {
                    members.write("  <TR><TD>&nbsp;</TD></TR>");
                    members.write(lineSeparator);
                    members.write("  <TR><TD bgcolor='#DDDDFF'><STRONG>");
                    if (CodeList.class.isAssignableFrom(classe)) {
                        members.write("Code list");
                    } else {
                        members.write("Interface");
                    }
                    members.write("&nbsp; <CODE><A HREF=\"");
                    members.write(pathToClassJavadoc);
                    members.write("\">");
                    members.write(classname);
                    members.write("</A></CODE></STRONG></TD></TR>");
                    members.write(lineSeparator);
                    classHeader = true;
                }
                members.write("  <TR><TD><CODE>");
                members.write(name);
                members.write("</CODE></TD>");
                members.write("<TD><CODE>&nbsp;&nbsp;");
                if (identifier!=LEGACY && identifier!=EXTENSION) {
                    members.write(identifier);
                    members.write("</CODE>");
                } else {
                    members.write("</CODE><FONT SIZE='-1' COLOR='#808080'>");
                    members.write(identifier);
                    members.write("</FONT>");
                }
                members.write("</TD></TR>");
                members.write(lineSeparator);
            }
        }
        /*
         * Close the streams.
         */
        members.write("  </TABLE>");
        members.write(lineSeparator);
        members.close();
        classes.write("  </TABLE>");
        classes.write(lineSeparator);
        classes.write(members.toString());
        classes.write("  </BODY>");
        classes.write(lineSeparator);
        classes.write("</HTML>");
        classes.write(lineSeparator);
        classes.close();
    }

    /**
     * Returns the UML identifier for the specified element, or <code>null</code>
     * if the specified element is not part of the UML model.
     */
    private static String getIdentifier(final AnnotatedElement element) {
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
        if (ogc.length()>=4 &&
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
     * Compare a GeoAPI name with a OGC name. Returns <code>true</code> if the
     * name change is not significant. Returns <code>false</code> otherwise.
     */
    private static boolean compareClassName(final Class classe, final String geoapi, String ogc) {
        /*
         * The 2 letters prefix is always omitted.
         */
        ogc = dropPrefix(ogc);
        /*
         * For exceptions, the "Exception" suffix is always added after the class name.
         */
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
        return geoapi.equals(ogc);
    }

    /**
     * Compare a GeoAPI name with a OGC name. Returns <code>true</code> if the
     * name change is not significant (e.g. addition of a <code>get</code> prefix).
     * Returns <code>false</code> otherwise.
     */
    private static boolean compareMethodName(final Method method, String geoapi, String ogc) {
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
        final Class returnType = method.getReturnType();
        if (returnType.equals(Boolean.TYPE) || returnType.equals(Boolean.class)) {
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
        if (returnType.isArray() ||
            Collection              .class.isAssignableFrom(returnType) ||
            Map                     .class.isAssignableFrom(returnType) ||
            PointGrid               .class.isAssignableFrom(returnType) ||
            PointArray              .class.isAssignableFrom(returnType) ||
            ParameterValueGroup     .class.isAssignableFrom(returnType) ||
            ParameterDescriptorGroup.class.isAssignableFrom(returnType))
        {
            final int length = ogc.length();
            if (length!=0) {
                switch (ogc.charAt(length-1)) {
                    case 'y': ogc = ogc.substring(0, length-1) + "ies"; break;
                    case 's': if (length<2 || ogc.charAt(length-2)!='s') break;
                    case 'h': // fall through
                    case 'x': ogc += "es"; break;
                    default : ogc += 's'; break;
                }
            }
        }
        if (false) {
            // Debug code
            if (!geoapi.equals(ogc)) {
                System.out.println(geoapi + "  " + ogc);
            }
        }
        return geoapi.equals(ogc);
    }

    /**
     * Compare a GeoAPI name with a OGC name. Returns <code>true</code> if the
     * name change is not significant. Returns <code>false</code> otherwise.
     */
    private static boolean compareCodeName(final Field method, String geoapi, String ogc) {
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
            if (i!=0) {
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
        if (false) {
            // Debug code
            if (!geoapi.equals(ogc)) {
                System.out.println(geoapi + "  " + ogc);
            }
        }
        return ogc.equals(geoapi);
    }
}
