/*
 * Helper taglet for OpenGIS documentation.
 */
package org.opengis.tools;

// J2SE dependencies
import java.util.Map;

// Standard JavaDoc dependencies
import com.sun.javadoc.Tag;
import com.sun.javadoc.Doc;
import com.sun.javadoc.RootDoc;
import com.sun.tools.doclets.Taglet;
import com.sun.tools.doclets.formats.html.HtmlDocletWriter;
import com.sun.tools.doclets.internal.toolkit.taglets.SimpleTaglet;
import com.sun.tools.doclets.internal.toolkit.taglets.TagletOutput;
import com.sun.tools.doclets.internal.toolkit.taglets.TagletWriter;


/**
 * The <code>@revisit</code> tag.
 *
 * @version $Id$
 * @author Martin Desruisseaux
 */
public final class RevisitTaglet extends SimpleTaglet {
    /**
     * Register this taglet.
     *
     * @param tagletMap the map to register this tag to.
     */
    public static void register(final Map tagletMap) {
       final RevisitTaglet tag = new RevisitTaglet();
       tagletMap.put(tag.getName(), tag);
    }

    /**
     * Construct a default <code>@revisit</code> taglet.
     */
    private RevisitTaglet() {
        super("revisit", "<U>REVISIT OPEN ISSUE "+
              "<FONT COLOR='#A01020'>(a GeoAPI comment)</FONT></U>",
              "tmf");
    }

    /**
     * Return the name of this custom tag.
     */
    public String getName() {
        return "revisit";
    }

    /**
     * Returns <code>true</code> since <code>@revisit</code> can be used in overview.
     */
    public boolean inOverview() {
        return true;
    }

    /**
     * Returns <code>true</code> since <code>@revisit</code> can be used in package documentation.
     */
    public boolean inPackage() {
        return true;
    }

    /**
     * Returns <code>true</code> since <code>@revisit</code> can be used in type documentation
     * (classes or interfaces).
     */
    public boolean inType() {
        return true;
    }

    /**
     * Returns <code>true</code> since <code>@revisit</code> can be used in constructor
     */
    public boolean inConstructor() {
        return true;
    }

    /**
     * Returns <code>true</code> since <code>@revisit</code> can be used in method documentation.
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
     * Format the doc.
     */
    public TagletOutput getTagletOutput(final Doc doc, final TagletWriter writer) {
        final Tag[] tags = doc.tags("revisit");
        if (tags.length == 0) {
            return null;
        }
        TagletOutput output = super.getTagletOutput(doc, writer);
        if (output == null) {
            output = writer.commentTagsToOutput(doc, tags);
        }
        output.setOutput("\n<DT><BR><DD><TABLE cellpadding=2 cellspacing=0>" + 
                           "<TR><TD bgcolor=\"yellow\">" + output +
                           "</TD></TR></TABLE></DD>\n");
        return output;
    }

    /**
     * Given the <code>Tag</code> representation of this custom tag, return its string
     * representation.
     *
     * @deprecated This is a legacy code for J2SE 1.4.2 standard doclet.
     */
    public String toString(final Tag tag) {
        return toString(tag, null);
    }

    /**
     * Given the <code>Tag</code> representation of this custom tag, return its string
     * representation.
     *
     * @param tag   The <code>Tag</code> representation of this custom tag.
     * @param html  The HtmlDocletWriter that will output this tag, or <code>null</code>.
     *
     * @deprecated This is a legacy code for J2SE 1.4.2 standard doclet.
     */
    public String toString(final Tag tag, final HtmlDocletWriter html) {
        return toString(new Tag[] {tag}, html);
    }

    /**
     * Given an array of <code>Tag</code>s representing this custom tag, return its string
     * representation.
     *
     * @deprecated This is a legacy code for J2SE 1.4.2 standard doclet.
     */
    public String toString(final Tag[] tags) {
        return toString(tags, null);
    }

    /**
     * Given an array of <code>Tag</code>s representing this custom tag, return its string
     * representation.
     *
     * @param tags  The array of <code>Tag</code>s representing of this custom tag.
     * @param html  The HtmlDocletWriter that will output this tag, or <code>null</code>.
     *
     * @deprecated This is a legacy code for J2SE 1.4.2 standard doclet.
     */
    public String toString(final Tag[] tags, final HtmlDocletWriter html) {
        if (tags.length == 0) {
            return null;
        }
        final StringBuffer buffer = new StringBuffer("\n<DT><BR><B>REVISIT OPEN ISSUE "
              + "<FONT COLOR='#A01020'>(a GeoAPI's comment, <U>not</U> an OpenGIS's one)</FONT></B>\n"
              + "<DD><TABLE cellpadding=2 cellspacing=0>");
        for (int i=0; i<tags.length; i++) {
            final Tag tag = tags[i];
            buffer.append("<TR><TD bgcolor=\"yellow\">");
            final String text;
            if (html != null) {
                text = html.commentTagsToString(null, null, tag.inlineTags(), false);
            } else {
                text = tag.text();
            }
            buffer.append(text);
            buffer.append("</TD></TR>\n");
        }
        buffer.append("</TABLE></DD>\n");
        return buffer.toString();
    }
}
