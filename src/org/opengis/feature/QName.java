/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.feature;

/**
 * Represents a qualified name.  Such a name consists of two parts: an optional
 * namespace (which may be null) and a local name.  For presentation or encoding
 * as XML, a QName can be abbreviated by using a prefix which is mapped to the
 * namespace.  This prefix is also optional, and in this class does not
 * contribute to the hashCode or equality comparisons.
 */
public final class QName {
    private String namespace;
    private String localName;
    private String preferredPrefix;

    public QName(final String localName) {
        this(localName, null);
    }

    public QName(final String localName, final String namespace) {
        this(localName, namespace, null);
    }

    public QName(final String localName, final String namespace,
            final String preferredPrefix) {
        if (localName == null)
            throw new IllegalArgumentException("Local name cannot be null");
        this.localName = localName;
        this.namespace = namespace;
        this.preferredPrefix = preferredPrefix;
    }

    public String getLocalName() {
        return localName;
    }

    public String getNamespace() {
        return namespace;
    }

    public String getPreferredPrefix() {
        return preferredPrefix;
    }

    public String toString() {
        if (preferredPrefix != null) {
            return preferredPrefix + ":" + localName;
        }
        else if (namespace != null) {
            return "{" + namespace + "}" + localName;
        }
        else {
            return localName;
        }
    }

    /**
     * Computes a hash code (not including the preferred prefix).
     */
    public int hashCode() {
        return
        	((namespace == null) ? 0 : namespace.hashCode()) ^
        	((localName == null) ? 0 : localName.hashCode());
    }

    /**
     * Does not compare the prefix.
     */
    public boolean equals(final Object obj) {
        if (obj instanceof QName) {
            final QName other = (QName) obj;
            return equals(other);
        }
        else {
            return false;
        }
    }

    /**
     * Does not compare the prefix.
     */
    public boolean equals(final QName other) {
        return
	    	((namespace == null) ? (other.namespace == null) : namespace.equals(other.namespace)) &&
	    	((localName == null) ? (other.localName == null) : localName.equals(other.localName));
    }

    /**
     * Includes the prefix in the comparison.
     */
    public boolean equalsWithPrefix(final QName other) {
        return
        	equals(other) &&
        	(preferredPrefix == null) ? (other.preferredPrefix == null) : preferredPrefix.equals(other.preferredPrefix);
    }
}
