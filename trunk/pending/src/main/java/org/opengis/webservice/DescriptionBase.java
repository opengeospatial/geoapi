package org.opengis.webservice;


/**
 * @author <a href="mailto:poth@lat-lon.de">Andreas Poth</a>
 */
public interface DescriptionBase {
    /**
     * Returns the name.
     * 
     * @uml.property name="name"
     */
    String getName();

    /**
     * Returns the description.
     * 
     * @uml.property name="description"
     */
    String getDescription();

    /**
     * Returns the metadataLink.
     * 
     * @uml.property name="metadataLink"
     */
    MetadataLink getMetadataLink();
}
