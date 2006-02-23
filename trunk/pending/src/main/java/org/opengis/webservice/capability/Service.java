package org.opengis.webservice.capability;

// OpenGIS direct dependencies
import org.opengis.webservice.Description;
import org.opengis.webservice.MetadataLink;
import org.opengis.webservice.WebServiceException;
import org.opengis.metadata.constraint.Constraints;
import org.opengis.metadata.identification.Keywords;
import org.opengis.metadata.citation.ResponsibleParty;
import org.opengis.util.InternationalString;


/**
 * @version $Revision$
 * @author <a href="mailto:poth@lat-lon.de">Andreas Poth</a>
 */
public interface Service extends Description {
    /**
     * Returns the accessConstraints.
     * 
     * @uml.property name="accessConstraints"
     */
    Constraints getAccessConstraints();

    /**
     * Returns the citedResponsibleParty.
     * 
     * @uml.property name="citedResponsibleParty"
     */
    ResponsibleParty getCitedResponsibleParty();

    /**
     * Returns the fees.
     * 
     * @uml.property name="fees"
     *
     * @revisit Original type was CodeList (which CodeList?), but all others occurences
     *          of this method in the API used a character string.
     */
    InternationalString getFees();

    /**
     * Returns the keywords.
     * 
     * @uml.property name="keywords"
     */
    Keywords getKeywords();

    /**
     * Returns the updateSequence.
     * 
     * @uml.property name="updateSequence"
     */
    String getUpdateSequence();

    /**
     * Returns the version.
     * 
     * @uml.property name="version"
     */
    String getVersion();
}
