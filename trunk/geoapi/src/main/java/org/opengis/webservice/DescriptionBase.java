package org.opengis.webservice;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * @author <a href="mailto:poth@lat-lon.de">Andreas Poth</a>
 */
public interface DescriptionBase {
    /**
     * Returns the name.
     */
    @UML(identifier="name", specification=UNSPECIFIED)
    String getName();

    /**
     * Returns the description.
     */
    @UML(identifier="description", specification=UNSPECIFIED)
    String getDescription();

    /**
     * Returns the metadataLink.
     */
    @UML(identifier="metadataLink", specification=UNSPECIFIED)
    MetadataLink getMetadataLink();
}
