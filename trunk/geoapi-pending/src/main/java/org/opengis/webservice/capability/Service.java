package org.opengis.webservice.capability;

// OpenGIS direct dependencies
import org.opengis.webservice.Description;
import org.opengis.webservice.MetadataLink;
import org.opengis.webservice.WebServiceException;
import org.opengis.metadata.constraint.Constraints;
import org.opengis.metadata.identification.Keywords;
import org.opengis.metadata.citation.ResponsibleParty;
import org.opengis.util.InternationalString;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * @author <a href="mailto:poth@lat-lon.de">Andreas Poth</a>
 */
public interface Service extends Description {
    /**
     * Returns the accessConstraints.
     */
    @UML(identifier="accessConstraints", specification=UNSPECIFIED)
    Constraints getAccessConstraints();

    /**
     * Returns the citedResponsibleParty.
     */
    @UML(identifier="citedResponsibleParty", specification=UNSPECIFIED)
    ResponsibleParty getCitedResponsibleParty();

    /**
     * Returns the fees.
     *
     * @todo Original type was CodeList (which CodeList?), but all others occurences
     *       of this method in the API used a character string.
     */
    @UML(identifier="fees", specification=UNSPECIFIED)
    InternationalString getFees();

    /**
     * Returns the keywords.
     */
    @UML(identifier="keywords", specification=UNSPECIFIED)
    Keywords getKeywords();

    /**
     * Returns the updateSequence.
     */
    @UML(identifier="updateSequence", specification=UNSPECIFIED)
    String getUpdateSequence();

    /**
     * Returns the version.
     */
    @UML(identifier="version", specification=UNSPECIFIED)
    String getVersion();
}
