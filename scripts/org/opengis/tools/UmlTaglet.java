/*
 * Helper taglet for OpenGIS documentation.
 */
package org.opengis.tools;

// J2SE dependencies
import java.util.Map;
import java.util.StringTokenizer;

// Standard JavaDoc dependencies
import com.sun.javadoc.Tag;
import com.sun.javadoc.RootDoc;
import com.sun.tools.doclets.Taglet;


/**
 * The <code>@UML</code> tag. This tag should be used as below:
 *
 * <pre>
 *   @UML <var>type</var> <var>name</var>
 * </pre>
 *
 * Where <var>name</var> is the name in the UML schema, and <var>type</var> is one of the
 * fellowing:
 *
 * <ul>
 *   <li><code>optional</code> for optional attribute</li>
 *   <li><code>conditional</code> for conditional attribute</li>
 *   <li><code>mandatory</code> for mandatory attribute</li>
 *   <li><code>association</code> for an association</li>
 *   <li><code>datatype</code> for a datatype</li>
 *   <li><code>abstract</code> for an abstract type</li>
 *   <li><code>codelist</code> for a code list</li>
 * </ul>
 *
 * @version $Id$
 * @author Martin Desruisseaux
 */
public final class UmlTaglet implements Taglet {
    /**
     * Register this taglet.
     *
     * @param tagletMap the map to register this tag to.
     */
    public static void register(final Map tagletMap) {
       final UmlTaglet tag = new UmlTaglet();
       tagletMap.put(tag.getName(), tag);
    }

    /**
     * Construct a default <code>@UML</code> taglet.
     */
    private UmlTaglet() {
    }
    
    /**
     * Return the name of this custom tag.
     */
    public String getName() {
        return "UML";
    }
    
    /**
     * Returns <code>false</code> since <code>@UML</code> can't be used in overview.
     */
    public boolean inOverview() {
        return false;
    }

    /**
     * Returns <code>false</code> since <code>@UML</code> can't be used in package documentation.
     */
    public boolean inPackage() {
        return false;
    }

    /**
     * Returns <code>true</code> since <code>@UML</code> can be used in type documentation
     * (classes or interfaces).
     */
    public boolean inType() {
        return true;
    }

    /**
     * Returns <code>false</code> since <code>@UML</code> is for interface or code list only
     * (no public constructor).
     */
    public boolean inConstructor() {
        return false;
    }
    
    /**
     * Returns <code>true</code> since <code>@UML</code> can be used in method documentation.
     */
    public boolean inMethod() {
        return true;
    }
    
    /**
     * Returns <code>true</code> since <code>@UML</code> can be used in field documentation.
     */
    public boolean inField() {
        return true;
    }
    
    /**
     * Returns <code>false</code> since <code>@UML</code> is not an inline tag.
     */
    public boolean isInlineTag() {
        return false;
    }

    /**
     * Print a warning. This method assumes we are using the standard doclet.
     */
    private static void warning(final Tag tag, final String message) {
        if (false) {
            // TODO: root is null; can't use it.
            com.sun.tools.doclets.standard.Standard.configuration().root.printWarning(tag.position(), message);
        } else {
            System.err.print("UML error: ");
            System.err.println(message);
        }
    }

    /**
     * Given the <code>Tag</code> representation of this custom tag, return its string representation.
     */
    public String toString(final Tag tag) {
        final StringTokenizer tokens = new StringTokenizer(tag.text());
        final StringBuffer buffer = new StringBuffer("\n<DT><FONT COLOR='gray'>UML identifier");
        if (tokens.hasMoreTokens()) {
            String type = tokens.nextToken();
            if (type.equalsIgnoreCase("codelist")) {
                type = "code list";
            } else if (type.equalsIgnoreCase("abstract")) {
                type = "abstract type";
            } else if (type.equalsIgnoreCase("type")) {
                type = "abstract type";
            } else if (type.equalsIgnoreCase("datatype")) {
                type = "data type";
            } else if (type.equalsIgnoreCase("mandatory")) {
                type = "mandatory attribute";
            } else if (type.equalsIgnoreCase("conditional")) {
                type = "conditional attribute";
            } else if (type.equalsIgnoreCase("optional")) {
                type = "optional attribute";
            } else if (type.equalsIgnoreCase("association")) {
                type = "association";
            } else if (type.equalsIgnoreCase("operation")) {
                type = "operation";
            } else if (type.equalsIgnoreCase("constructor")) {
                type = "constructor";
            } else {
                warning(tag, "Unknow UML type: "+type);
            }
            buffer.append(" (<I>");
            buffer.append(type);
            buffer.append("</I>):");
            if (tokens.hasMoreTokens()) {
                buffer.append(" <CODE><B>");
                buffer.append(tokens.nextToken());
                buffer.append("</B></CODE>");
                if (tokens.hasMoreTokens()) {
                    warning(tag, "Extra word in @UML tag: "+tokens.nextToken());
                }
            } else {
                warning(tag, "Missing UML identifier");
            }
        } else {
            buffer.append(": <B>(unknow)</B>");
            warning(tag, "Missing UML type");
        }
        buffer.append("</FONT>\n");
        return buffer.toString();
    }
    
    /**
     * Given an array of <code>Tag</code>s representing this custom tag, return its string
     * representation. The default implementation invokes {@link #toString(Tag)} for each
     * tag.
     */
    public String toString(final Tag[] tags) {
        if (tags.length == 0) {
            return null;
        }
        final StringBuffer buffer = new StringBuffer();
        for (int i=0; i<tags.length; i++) {
            buffer.append(toString(tags[i]));
        }
        return buffer.toString();
    }
}
