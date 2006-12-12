package org.opengis.metadata;

/**
 * <p>
 * This is the super interface for all MetaData elements
 * </p>
 * 
 * @author Axios Engineering S.L.
 * @version $Id$
 */
public interface MetadataEntity {

    /**
     * Returns the identifier for the given concrete type of metadata object.
     * <p>
     * This identity method reflects the fact that most storage strategies deals
     * with simple identity to identify class instances.
     * </p>
     * 
     * @return the object identifier, or <code>null</code> if not set.
     */
    public Long getId();

    /**
     * Calls visitor
     * 
     * @param visitor
     *            an instance of MetadataVisitor
     */
    public void accept(MetadataVisitor visitor);
}
