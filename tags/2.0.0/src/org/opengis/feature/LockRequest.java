/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.feature;

// Annotations
import org.opengis.annotation.Extension;
import org.opengis.annotation.XmlElement;


/**
 * Represents the request for a lock.
 * A lock is requested for a duration, except for the special case of a {@code TRANSACTION_LOCK}
 * where the request is until the next commit or rollback.
 * <p>
 * A successful {@linkplain FeatureCollection#lock lock request} will result in an
 * {@linkplain LockResponse#getToken authorization ID} being generated.
 * This is a simple token you can keep until you need to work with the content again.
 * Without this token you are prevented from work (at least until the duration is up).
 *
 * @author Jody Garnett (Refractions Research)
 * @since GeoAPI 2.0
 */
@XmlElement("LockFeature")
public interface LockRequest {
	/**
	 * Returns a duration for which the request is valid.  The special value
	 * of -1 indicates that a lock should be valid only for the duration of a
	 * transaction.
	 */
    @XmlElement("expiry")
    long getDuration();
}
