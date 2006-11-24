/*
 * Helper taglet for OpenGIS documentation.
 */
package org.opengis.tools;

// J2SE dependencies
import java.util.Map;

// Standard JavaDoc dependencies
import com.sun.javadoc.Tag;
import com.sun.javadoc.Doc;
import com.sun.tools.doclets.internal.toolkit.taglets.SimpleTaglet;
import com.sun.tools.doclets.internal.toolkit.taglets.TagletOutput;
import com.sun.tools.doclets.internal.toolkit.taglets.TagletWriter;


/**
 * The <code>@todo</code> tag.
 *
 * @version $Id$
 * @author Martin Desruisseaux
 */
public final class TodoTaglet extends SimpleTaglet {
    /**
     * Register this taglet.
     *
     * @param tagletMap the map to register this tag to.
     */
    public static void register(final Map tagletMap) {
       final TodoTaglet tag = new TodoTaglet();
       tagletMap.put(tag.getName(), tag);
    }

    /**
     * Constructs a default <code>@todo</code> taglet.
     */
    private TodoTaglet() {
        super("todo", "<U>REVISIT OPEN ISSUE "+
              "<FONT COLOR='#A01020'>(a GeoAPI comment)</FONT></U>",
              "tmf");
    }

    /**
     * Return the name of this custom tag.
     */
    public String getName() {
        return "todo";
    }

    /**
     * Returns <code>true</code> since <code>@todo</code> can be used in overview.
     */
    public boolean inOverview() {
        return true;
    }

    /**
     * Returns <code>true</code> since <code>@todo</code> can be used in package documentation.
     */
    public boolean inPackage() {
        return true;
    }

    /**
     * Returns <code>true</code> since <code>@todo</code> can be used in type documentation
     * (classes or interfaces).
     */
    public boolean inType() {
        return true;
    }

    /**
     * Returns <code>true</code> since <code>@todo</code> can be used in constructor
     */
    public boolean inConstructor() {
        return true;
    }

    /**
     * Returns <code>true</code> since <code>@todo</code> can be used in method documentation.
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
        final Tag[] tags = doc.tags("todo");
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
}
