package org.opengis.webservice;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * @author <a href="mailto:poth@lat-lon.de">Andreas Poth</a>
 */
public interface Description extends DescriptionBase {
    /**
     * Returns the label.
     */
    @UML(identifier="label", specification=UNSPECIFIED)
    String getLabel();
}
