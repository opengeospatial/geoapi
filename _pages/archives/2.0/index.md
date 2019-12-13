---
layout: default
permalink: "/archives/2.0/index.html"
title: "GeoAPI 2.0 (archived)"
---

<h1>GeoAPI 2.0 (archived)</h1>

<div class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 my-4 rounded relative" role="alert">
  <strong class="font-bold">Note:</strong>
  <span class="block sm:inline">GeoAPI 2.0 is a legacy release. See home page for latest release.</span>
</div>

<p>
  Release 2.0 of GeoAPI contained the interfaces developed by the GeoAPI project and accepted
  by the Open Geospatial Consortium with the final draft of the implementation specification
  <cite>GO-1 Application Objects</cite> and therefore considered by the GeoAPI project as one
  of its official releases.
  This release came in two flavors: <code>geoapi.jar</code> required a Java environment J2SE 1.4 or higher
  while <code>geoapi-tiger.jar</code> requires a Java environment Java 5 or higher.
</p>

<ul>
  <li><a href="https://www.opengeospatial.org/standards/go" class="externalLink">Defining specification</a></li>
  <li><a href="https://sourceforge.net/projects/geoapi/files/GeoAPI-2_0_0.zip/download" class="externalLink">Download from SourceForge</a></li>
</ul>

<h2>Changes since GeoAPI 1.0</h2>

<h3>Changes in Metadata packages</h3>

<ul>
  <li>Completed interfaces and code list derived from ISO 19115.</li>
  <li>Replaced all <code>Set</code> references by <code>Collection</code>, except in the very few places where ISO 19115 defines explicitly the collection type.</li>
  <li>Replaced all <code>URL</code> references by <code>URI</code>.</li>
</ul>

<h3>Changes in Referencing packages</h3>

<ul>
  <li>Added <code>SingleCRS</code> interface. This change reflect a part of ISO 19111 which was omitted in previous release.</li>
  <li>Renamed <code>ObliqueCartesianCS</code> as <code>AffineCS</code>. This reflect a change in OGC Topic 2 voted in July 16, 2004.</li>
  <li>Renamed <code>TemporalCS</code> as <code>TimeCS</code>. This reflect a change in OGC Topic 2 voted in July 16, 2004.</li>
  <li>Renamed <code>Info</code> as <code>IdentifiedObject</code>. This reflect a change in OGC Topic 2 voted in July 16, 2004.</li>
  <li>In <code>AffineCS</code>. This change is required for type safety and was forgotten in previous release.</li>
  <li>In <code>UserDefinedCS</code> creation. This part was forgotten in previous release.</li>
  <li>Added <code>toWKT()</code> method in <code>IdentifiedObject</code>.</li>
  <li>Renamed <code>NoSuchClassificationException</code> as <code>NoSuchIdentifierException</code>.</li>
  <li>Replaced all arrays by collections as the return type of the following methods: <code>IdentifiedObject.getIdentifiers()</code>, <code>IdentifiedObject.getAlias()</code> <code>CompoundCRS.getCoordinateReferenceSystems()</code>, <code>ConcatenatedOperation.getOperations()</code>, <code>CoordinateOperation.getPositionalAccuracy()</code>.</li>
  <li>Removed the <code>org.opengis.referencing.quality</code> package. This package is now replaced by <code>org.opengis.metadata.quality</code>. This reflect a change in OGC Topic 2 voted in July 16, 2004.</li>
  <li>Renamed all <code>OperationParameter</code> interfaces as <code>ParameterDescriptor</code>. This is because many users expect a parameter to contains a value. The <code>ParameterDescriptor</code> name make it clearer that this interface contains metadata about parameter, not the actual parameter value. It also consistent with usage in other Java libraries like <i>cite</i>Java Advanced Imaging<i>/cite</i>.</li>
  <li>In <code>OperationMethod</code>, changed the <code>GeneralParameterDescriptor[]</code> return type to <code>ParameterDescriptorGroup</code>. Generally speaking, this change was applied for most processing methods in GeoAPI returning parameters. This is a slight departure from specifications, which usually returns a sequence of parameter rather than a group.</li>
  <li>For convenience method <code>getParameter(String)</code> in <code>ParameterDescriptor</code>. This change make the convenience method more... convenient.</li>
  <li>For convenience method <code>getValue(String)</code> in <code>ParameterValue</code>. This change make the convenience method more... convenient. Also renamed the method as <code>parameter</code>, which avoid strange expression like <code>getValue(&quot;some_name&quot;).setValue(someValue)</code>. The idiom is now <code>parameter(&quot;some_name&quot;).setValue(someValue)</code> instead.</li>
  <li>For all methods expecting a <code>Locale</code> argument, removed this argument and replaced the <code>String</code> return type by <code>InternationalString</code>. This change was done for consistency with the metadata package.</li>
  <li>In <code>valueFile()</code> method, replaced the <code>URL</code> return type by <code>URI</code>.</li>
</ul>

<h3>Changes in Geometry packages</h3>

<ul>
  <li>Set <code>Point</code> interfaces instead of an union of them. The union type do no exists in Java and this change lead to a more object oriented approach. This change was done after a comment on the GeoAPI mailing list.</li>
  <li>In <code>Envelope</code> interface, added convenience methods <code>getMinimum</code>, <code>getMaximum</code>, <code>getCenter</code> and <code>getLength</code>.</li>
  <li>In <code>GenericCurve</code>, renamed the <code>getParam</code> and <code>getConstructiveParam</code> method as <code>forParam</code> and <code>forConstructiveParam</code> respectively. The former names was misleading since those methods do not return a constructive parameter; they expect it as argument instead.</li>
  <li>In <code>Complex</code>, renamed the <code>getSuperComplexex</code> method as <code>getSuperComplexes</code>. The former was a typo.</li>
  <li>Addition of more geometry interfaces from ISO 19107. The work is not finished however.</li>
</ul>

<h3>Changes in Grid Coverage packages</h3>

<ul>
  <li>In <code>Coverage</code> interface (method <code>getDimensionNames</code>), replaced the <code>String</code> returns type by <code>InternationalString</code>. This change is required for localization support and was forgotten in previous release.</li>
  <li>In <code>Coverage</code> interface, changed the return type from <code>double[]</code> to <code>float[]</code> for the method <code>evaluate(DirectPosition, float[])</code>. Previous return type was wrong.</li>
  <li>In <code>Coverage</code>, merged <code>getNumSources()</code> and <code>getSource(int)</code> methods into a single <code>getSources()</code> method.</li>
  <li>In <code>GridRange</code>, renamed <code>getLower()</code> and <code>getUpper()</code> as <code>getLowers()</code> and <code>getUppers()</code>.</li>
  <li>In <code>GridRange</code>, added <code>getLower(int)</code>, <code>getUpper(int)</code>, <code>getLength(int)</code> and <code>getDimension</code> convenience methods.</li>
  <li>In <code>Operation</code>, changed the <code>GeneralParameterValue[]</code>, return type to <code>ParameterValueGroup</code>. Generally speaking, this change was applied for most processing methods in GeoAPI returning parameters. This is a slight departure from specifications, which usually returns a sequence of parameter values.</li>
  <li>In <code>GridAnalysis</code>, renamed the <code>analyse</code> method as <code>analyze</code>. The former was a typo.</li>
</ul>
