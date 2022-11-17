/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */
package org.opengis.metadata.quality;

import javax.measure.Quantity;
import org.opengis.annotation.UML;
import org.opengis.referencing.operation.Matrix;

import static org.opengis.annotation.Specification.*;


/**
 * Closeness of reported coordinate values to values accepted as being true.
 * Can be used for the following measurement types (non-exhaustive list):
 * <ul>
 *   <li>Data quality measures for positional uncertainty in general.</li>
 *   <li>Height measurements as position observations in one dimension.
 *       The height may therefore be treated as a one-dimensional random variable.</li>
 *   <li>Horizontal point locations are defined by a 2D coordinates.
 *       The uncertainty of any point location can be described using the data quality
 *       basic measures for 2D random variables.</li>
 * </ul>
 *
 * <h2>Standardized values</h2>
 * In order to achieve well defined and comparable quality information, it is recommended to
 * report data quality using {@linkplain Measure quality measures} listed in ISO 19157 annex.
 * The following table provides a summary adapted to GeoAPI objects;
 * see ISO 19157 for more complete descriptions and formulas.
 * All identifiers should be in "ISO 19157" namespace.
 *
 * <table class="ogc">
 *   <caption>Standardized values derived from ISO 19157</caption>
 *   <tr>
 *     <th>{@linkplain MeasureReference#getMeasureIdentification() Identifier}</th>
 *     <th>{@linkplain MeasureReference#getNamesOfMeasure() Name of measure}</th>
 *     <th>{@linkplain Measure#getAliases() Aliases}</th>
 *     <th>{@linkplain Measure#getBasicMeasure() Basic measure}</th>
 *     <th>{@linkplain Measure#getParameters() Parameters}</th>
 *     <th>{@linkplain Measure#getValueType() Value type}</th>
 *   </tr><tr>
 *     <td>28</td>
 *     <td>mean value of positional uncertainties (1D, 2D and 3D)</td>
 *     <td></td>
 *     <td></td>
 *     <td></td>
 *     <td>{@link Quantity}</td>
 *   </tr><tr>
 *     <td>128</td>
 *     <td>bias of positions (1D, 2D and 3D)</td>
 *     <td></td>
 *     <td></td>
 *     <td></td>
 *     <td>{@link Quantity}</td>
 *   </tr><tr>
 *     <td>29</td>
 *     <td>mean value of positional uncertainties excluding outliers (2D)</td>
 *     <td></td>
 *     <td></td>
 *     <td>e<sub>max</sub></td>
 *     <td>{@link Quantity}</td>
 *   </tr><tr>
 *     <td>30</td>
 *     <td>number of positional uncertainties above a given threshold</td>
 *     <td></td>
 *     <td>error count</td>
 *     <td>e<sub>max</sub></td>
 *     <td>{@link Quantity}</td>
 *   </tr><tr>
 *     <td>31</td>
 *     <td>rate of positional uncertainties above a given threshold</td>
 *     <td></td>
 *     <td></td>
 *     <td>e<sub>max</sub></td>
 *     <td>{@link Quantity}</td>
 *   </tr><tr>
 *     <td>32</td>
 *     <td>covariance matrix</td>
 *     <td>variance-covariance matrix</td>
 *     <td></td>
 *     <td></td>
 *     <td>{@link Matrix}</td>
 *   </tr><tr>
 *     <td>33</td>
 *     <td>linear error probable</td>
 *     <td>LEP</td>
 *     <td>LE50 or LE50(r)</td>
 *     <td></td>
 *     <td>{@link Quantity}</td>
 *   </tr><tr>
 *     <td>34</td>
 *     <td>standard linear error</td>
 *     <td>SLE</td>
 *     <td>LE68.3 or LE68.3(r)</td>
 *     <td></td>
 *     <td>{@link Quantity}</td>
 *   </tr><tr>
 *     <td>35</td>
 *     <td>linear map accuracy at 90% significance level</td>
 *     <td>LMAS 90%</td>
 *     <td>LE90 or LE90(r)</td>
 *     <td></td>
 *     <td>{@link Quantity}</td>
 *   </tr><tr>
 *     <td>36</td>
 *     <td>linear map accuracy at 95% significance level</td>
 *     <td>LMAS 95%</td>
 *     <td>LE95 or LE95(r)</td>
 *     <td></td>
 *     <td>{@link Quantity}</td>
 *   </tr><tr>
 *     <td>37</td>
 *     <td>linear map accuracy at 99% significance level</td>
 *     <td>LMAS 99%</td>
 *     <td>LE99 or LE99(r)</td>
 *     <td></td>
 *     <td>{@link Quantity}</td>
 *   </tr><tr>
 *     <td>38</td>
 *     <td>near certainty linear error</td>
 *     <td></td>
 *     <td>LE99.8 or LE99.8(r)</td>
 *     <td></td>
 *     <td>{@link Quantity}</td>
 *   </tr><tr>
 *     <td>39</td>
 *     <td>root mean square error</td>
 *     <td>RMS</td>
 *     <td></td>
 *     <td></td>
 *     <td>{@link Quantity}</td>
 *   </tr><tr>
 *     <td>40</td>
 *     <td>absolute linear error at 90% significance level of biased vertical data</td>
 *     <td>LMAS</td>
 *     <td></td>
 *     <td></td>
 *     <td>{@link Quantity}</td>
 *   </tr><tr>
 *     <td>41</td>
 *     <td>absolute linear error at 90% significance level of biased vertical data</td>
 *     <td>ALE</td>
 *     <td></td>
 *     <td>Sample size</td>
 *     <td>{@link Quantity}</td>
 *   </tr><tr>
 *     <td>42</td>
 *     <td>circular standard deviation</td>
 *     <td>Helmert's point error, CSE</td>
 *     <td>CE39.4</td>
 *     <td></td>
 *     <td>{@link Quantity}</td>
 *   </tr><tr>
 *     <td>43</td>
 *     <td>circular error probable</td>
 *     <td>CEP</td>
 *     <td>CE50</td>
 *     <td></td>
 *     <td>{@link Quantity}</td>
 *   </tr><tr>
 *     <td>44</td>
 *     <td>circular error at 90% significant level</td>
 *     <td>circular map accuracy standard</td>
 *     <td>CE90</td>
 *     <td></td>
 *     <td>{@link Quantity}</td>
 *   </tr><tr>
 *     <td>45</td>
 *     <td>circular error at 95% significant level</td>
 *     <td>navigation accuracy</td>
 *     <td>CE95</td>
 *     <td></td>
 *     <td>{@link Quantity}</td>
 *   </tr><tr>
 *     <td>46</td>
 *     <td>circular near certainty error</td>
 *     <td>CNCE</td>
 *     <td>CE99.8</td>
 *     <td></td>
 *     <td>{@link Quantity}</td>
 *   </tr><tr>
 *     <td>47</td>
 *     <td>root mean square error of planimetry</td>
 *     <td>RMSEP</td>
 *     <td></td>
 *     <td></td>
 *     <td>{@link Quantity}</td>
 *   </tr><tr>
 *     <td>48</td>
 *     <td>absolute circular error at 90% significance level of biased data</td>
 *     <td>CMAS</td>
 *     <td></td>
 *     <td></td>
 *     <td>{@link Quantity}</td>
 *   </tr><tr>
 *     <td>49</td>
 *     <td>absolute circular error at 90% significance level of biased data</td>
 *     <td>ACE</td>
 *     <td></td>
 *     <td>Sample size</td>
 *     <td>{@link Quantity}</td>
 *   </tr><tr>
 *     <td>50</td>
 *     <td>uncertainty ellipse</td>
 *     <td>standard point error ellipse</td>
 *     <td></td>
 *     <td></td>
 *     <td>(<var>a</var>, <var>b</var>, <var>φ</var>)</td>
 *   </tr><tr>
 *     <td>51</td>
 *     <td>confidence ellipse</td>
 *     <td>confidence point error ellipse</td>
 *     <td></td>
 *     <td>significance level</td>
 *     <td>(<var>a</var>, <var>b</var>, <var>φ</var>)</td>
 *   </tr>
 * </table>
 *
 * <p><b>Note:</b>
 * ISO 19157 declares the <i>covariance matrix</i> value type as "Measure" associated with {@link ValueStructure#MATRIX}.
 * For an object oriented language like Java, a more natural approach is to use an object of specific type for the value.
 * </p>
 *
 * <p>{@linkplain Measure#getDefinition() Definitions}:</p>
 * <ol start="28">
 *   <li>Mean value of the distance between a measured position and what is considered as the corresponding true position.</li>
 *   <li value="128">Deviation between a measured position and what is considered as the corresponding true position.</li>
 *   <li value="29">For a set of points where the distance does not exceed a defined threshold, the arithmetical average of distances
 *       between their measured positions and what is considered as the corresponding true positions.</li>
 *   <li>Number of positional uncertainties above a given threshold for a set of positions.
 *       The errors are defined as the distance between a measured position and what is considered as the corresponding true position.</li>
 *   <li>Number of positional uncertainties above a given threshold for a set of positions in relation to the total number of measured positions.
 *       The errors are defined as the distance between a measured position and what is considered as the corresponding true position.</li>
 *   <li>Symmetrical square matrix with variances of point coordinates on the main diagonal and covariance between these coordinates as off-diagonal elements.</li>
 *   <li>Half length of the interval defined by an upper and a lower limit, in which the true value lies with probability 50%.</li>
 *   <li>Half length of the interval defined by an upper and a lower limit, in which the true value lies with probability 68.3%.</li>
 *   <li>Half length of the interval defined by an upper and a lower limit, in which the true value lies with probability 90%.</li>
 *   <li>Half length of the interval defined by an upper and a lower limit, in which the true value lies with probability 95%.</li>
 *   <li>Half length of the interval defined by an upper and a lower limit, in which the true value lies with probability 99%.</li>
 *   <li>Half length of the interval defined by an upper and a lower limit, in which the true value lies with probability 99.8%.</li>
 *   <li>Linear root mean square error.</li>
 *   <li>Absolute vertical accuracy of the data’s coordinates, expressed in terms of linear error at 90% probability given that a bias is present.</li>
 *   <li>Absolute vertical accuracy of the data’s coordinates, expressed in terms of linear error at 90% probability given that a bias is present.</li>
 *   <li>Radius describing a circle, in which the true point location lies with the probability of 39.4%.</li>
 *   <li>Radius describing a circle, in which the true point location lies with the probability of 50%.</li>
 *   <li>Radius describing a circle, in which the true point location lies with the probability of 90%.</li>
 *   <li>Radius describing a circle, in which the true point location lies with the probability of 95%.</li>
 *   <li>Radius describing a circle, in which the true point location lies with the probability of 99.8%.</li>
 *   <li>Radius of a circle around the given point, in which the true value lies with probability P.</li>
 *   <li>Absolute horizontal accuracy of the data’s coordinates, expressed in terms of circular error at 90% probability given that a bias is present.</li>
 *   <li>Absolute horizontal accuracy of the data’s coordinates, expressed in terms of circular error at 90% probability given that a bias is present.</li>
 *   <li>2D ellipse with the two main axes indicating the direction and magnitude of the highest and the lowest uncertainty of a 2D point.</li>
 *   <li>2D ellipse with the two main axes indicating the direction and magnitude of the highest and the lowest uncertainty of a 2D point.</li>
 * </ol>
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Alexis Gaillard (Geomatys)
 * @version 3.1
 * @since   2.0
 */
@UML(identifier="DQ_AbsoluteExternalPositionalAccuracy", specification=ISO_19157)
public interface AbsoluteExternalPositionalAccuracy extends PositionalAccuracy {
}
