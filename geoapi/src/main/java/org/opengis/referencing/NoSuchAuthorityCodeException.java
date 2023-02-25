/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2023 Open Geospatial Consortium, Inc.
 *    http://www.geoapi.org
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.opengis.referencing;

import org.opengis.util.NoSuchIdentifierException;


/**
 * Thrown when an {@linkplain AuthorityFactory authority factory} cannot find the requested authority code.
 * This is a specialization of {@link NoSuchIdentifierException} with the identifier separated in its authority
 * and code components.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 *
 * @see org.opengis.referencing.crs.CRSAuthorityFactory
 * @see org.opengis.referencing.cs.CSAuthorityFactory
 * @see org.opengis.referencing.datum.DatumAuthorityFactory
 */
public class NoSuchAuthorityCodeException extends NoSuchIdentifierException {
    /**
     * Serial number for inter-operability with different versions.
     */
    private static final long serialVersionUID = -1573748311981746573L;

    /**
     * The authority.
     *
     * @see #getAuthority()
     */
    private final String authority;

    /**
     * The invalid authority code.
     *
     * @see #getAuthorityCode()
     */
    private final String code;

    /**
     * Constructs an exception with the specified detail message and authority code.
     *
     * @param  message    the detail message, saved for later retrieval by the {@link #getMessage()} method.
     * @param  authority  the authority, saved for retrieval by the {@link #getAuthority()} method.
     * @param  code       the invalid authority code, saved for retrieval by the {@link #getAuthorityCode()} method.
     */
    public NoSuchAuthorityCodeException(final String message, final String authority, final String code) {
        this(message, authority, code, (authority == null) ? code : (code == null) ? authority : authority + ':' + code);
    }

    /**
     * Constructs an exception with the specified detail message, authority code and identifier.
     * The identifier argument is optional. If omitted, then "{@code authority:code}" will be used.
     *
     * @param  message     the detail message, saved for later retrieval by the {@link #getMessage()} method.
     * @param  authority   the authority, saved for retrieval by the {@link #getAuthority()} method.
     * @param  code        the invalid authority code, saved for retrieval by the {@link #getAuthorityCode()} method.
     * @param  identifier  the full identifier as a concatenation of the authority and the code,
     *                     saved for retrieval by the {@link #getIdentifierCode()} method.
     */
    public NoSuchAuthorityCodeException(final String message, final String authority, final String code, final String identifier) {
        super(message, identifier);
        this.authority = authority;
        this.code = code;
    }

    /**
     * Returns the authority.
     *
     * @return the authority, or {@code null} if unknown.
     */
    public String getAuthority() {
        return authority;
    }

    /**
     * Returns the invalid authority code.
     *
     * @return the authority code, or {@code null} if unknown.
     */
    public String getAuthorityCode() {
        return code;
    }
}
