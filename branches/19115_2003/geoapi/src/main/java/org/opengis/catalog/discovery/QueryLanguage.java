package org.opengis.catalog.discovery;

import java.util.ArrayList;
import java.util.List;

import org.opengis.util.CodeList;

/**
 * QueryLanguage enum para indicar el tipo de codificaci�n del lenguage de
 * restricciones en que se encuentra el predicado de una consulta en un
 * {@linkPlain
 * net.gipuzkoa.csw.catalogservices.delegate.discovery.QueryExpression#getPredicate()}.
 * 
 * <p>
 * Defines type and versions of Query Language available.
 * </p>
 * 
 * @author Mauricio Pazos, Axios Engineering
 * @version $Id$
 */
public class QueryLanguage extends CodeList<QueryLanguage> {
    /** required for interoperability */
    private static final long serialVersionUID = -436252868487351165L;

    /** DOCUMENT ME! */
    public static final List VALUES = new ArrayList(2);

    /**
     * Indica que el lenguage de restricciones en el que est� expresado el
     * filtro de la consulta es <a
     * href="http://portal.opengeospatial.org/files/?artifact_id=8340">Filter
     * 1.1</a>.
     * 
     * <p>
     * Por ejemplo:
     * 
     * <pre>
     * <code>
     *     &lt;ogc:Filter xmlns:ogc=&quot;http://www.opengis.net/ogc&quot; xmlns:gml=&quot;http://www.opengis.net/gml&quot;&gt;
     *      &lt;ogc:And&gt;
     *       &lt;ogc:PropertyIsEqualTo&gt;
     *        &lt;ogc:PropertyName&gt;fileIdentifier&lt;/ogc:PropertyName&gt;
     *        &lt;ogc:Literal&gt;abc-123&lt;/ogc:Literal&gt;
     *       &lt;/ogc:PropertyIsEqualTo&gt;
     *       &lt;ogc:PropertyIsLike wildCard=&quot;%&quot; singleChar=&quot;_&quot; escape=&quot;\&quot;&gt;
     *        &lt;ogc:PropertyName&gt;identificationInfo/abstract&lt;/ogc:PropertyName&gt;
     *        &lt;ogc:Literal&gt;%ABC%&lt;/ogc:Literal&gt;
     *       &lt;/ogc:PropertyIsLike&gt;
     *      &lt;/ogc:And&gt;
     *     &lt;/ogc:Filter&gt;
     * </pre>
     * </p>
     */
    public static final QueryLanguage OGC_FILTER = new QueryLanguage("OGC_Filter");

    /**
     * Indica que el lenguage de restricciones en el que est� expresado el
     * filtro de la consulta es OGC Common Query Language, como est�
     * especificado en <a
     * href="http://portal.opengeospatial.org/files/?artifact_id=5929&version=2">CAT
     * 2.0.1</a>
     * 
     * <p>
     * Por ejemplo:
     * 
     * <pre>
     * <code>
     *    fileIdentifier = &quot;abc-123&quot; AND identificationInfo.abstract LIKE '%ABC%'
     * </code>
     * </pre>
     * 
     * </p>
     */
    public static final QueryLanguage OGC_COMMON = new QueryLanguage("OGC_Common");

    private QueryLanguage(String value) {
        super(value, VALUES);
    }

    /**
     * Equals if they have the same version and type
     * 
     * @param obj
     * 
     * @return boolean
     */
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof QueryLanguage)) {
            return false;
        }

        final QueryLanguage ql = (QueryLanguage) obj;

        return this.name().equals(ql.name());
    }

    /**
     * DOCUMENT ME!
     * 
     * @return DOCUMENT ME!
     */
    public CodeList[] family() {
        return (CodeList[]) VALUES.toArray(new QueryLanguage[VALUES.size()]);
    }

    /**
     * Returns the list of {@code EvaluationMethodType}s.
     */
    public static QueryLanguage[] values() {
        synchronized (VALUES) {
            return (QueryLanguage[]) VALUES.toArray(new QueryLanguage[VALUES.size()]);
        }
    }

}
