---
layout: default
permalink: "/faq.html"
faqs:
  - question: How did the GeoAPI project get started, and what is its history?
    anchor: 'start'
    answer: |
      <p>
        The GeoAPI project emerged from the collaboration of several free software projects and
        from the work on various specifications at the Open Geospatial Consortium (<abbr>OGC</abbr>).
        You can follow the early, pre-history of GeoAPI by reading the following three posts to the
        DigitalEarth.org website; at this point it had no name, only a goal of bringing together multiple
        Java GIS projects.
      </p>
      <ul class="list-disc ml-4">
        <li class="my-1 text-justify">
          <a href="http://web.archive.org/web/20030509104308/http://digitalearth.org/story/2002/10/10/55046/206">Call for a Geo-Spatial <abbr>API</abbr></a>
          <svg class="w-4 h-4 fill-current text-gray-600 inline-block" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
            <path class="heroicon-ui" d="M19 6.41L8.7 16.71a1 1 0 1 1-1.4-1.42L17.58 5H14a1 1 0 0 1 0-2h6a1 1 0 0 1 1 1v6a1 1 0 0 1-2 0V6.41zM17 14a1 1 0 0 1 2 0v5a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V7c0-1.1.9-2 2-2h5a1 1 0 0 1 0 2H5v12h12v-5z"/>
          </svg>
        </li>
        <li class="my-1 text-justify">
          <a href="http://web.archive.org/web/20030510220525/http://digitalearth.org/story/2002/12/2/195021/503">Java GeoSpatial <abbr>API</abbr> Part II</a>
          <svg class="w-4 h-4 fill-current text-gray-600 inline-block" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
            <path class="heroicon-ui" d="M19 6.41L8.7 16.71a1 1 0 1 1-1.4-1.42L17.58 5H14a1 1 0 0 1 0-2h6a1 1 0 0 1 1 1v6a1 1 0 0 1-2 0V6.41zM17 14a1 1 0 0 1 2 0v5a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V7c0-1.1.9-2 2-2h5a1 1 0 0 1 0 2H5v12h12v-5z"/>
          </svg>
        </li>
        <li class="my-1 text-justify">
          <a href="http://web.archive.org/web/20030501122912/http://digitalearth.org/story/2002/12/12/121814/73">Java GeoSpatial <abbr>API</abbr> Part III</a>
          <svg class="w-4 h-4 fill-current text-gray-600 inline-block" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
            <path class="heroicon-ui" d="M19 6.41L8.7 16.71a1 1 0 1 1-1.4-1.42L17.58 5H14a1 1 0 0 1 0-2h6a1 1 0 0 1 1 1v6a1 1 0 0 1-2 0V6.41zM17 14a1 1 0 0 1 2 0v5a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V7c0-1.1.9-2 2-2h5a1 1 0 0 1 0 2H5v12h12v-5z"/>
          </svg>
        </li>
      </ul>
      <p>
        In part III, the <abbr>OGC</abbr> had just announced a Geographic Objects initiative
        which intended to define Java interfaces for geographic software. This followed earlier work
        on the <abbr>OGC</abbr> Implementation Specification 01-009 <cite>Coordinate Transformation Services</cite>
        which included interfaces defined in the <code>org.opengis</code> namespace ultimately adopted
        by GeoAPI.
      </p>
      <p>
        The GeoAPI project eventually formed to pursue this work. The interfaces defined in the
        <abbr>OGC</abbr> specification 01-009 became GeoAPI version 0.1. GeoAPI 1.0 was released with the draft
        of <abbr>OGC</abbr> specification 03-064 <cite>GO-1 Application Objects</cite>. In May 2005, the final draft
        of the GO-1 specification, which included GeoAPI interfaces, was accepted as an <abbr>OGC</abbr> standard
        and the matching version of GeoAPI was released as version 2.0.
      </p>
      <p>
        The GeoAPI 3.0 working group of the <abbr>OGC</abbr> has formed in January 2009 to formalized and continue
        the work of standardizing the most stable interfaces produced by the GeoAPI project. The GeoAPI specification
        produced by this group became an <abbr>OGC</abbr> standard in April 2011.
      </p>
  - question: What is the relationship between GeoAPI and <abbr>OGC</abbr>?
    anchor: 'OGC'
    answer: |
      <p>
        GeoAPI is closely tied to the <abbr>OGC</abbr> both in its origins and in its ongoing work.
      </p>
      <p>
        The GeoAPI project is a collaboration of participants from various institutions and
        software communities. The GeoAPI project is developing a set of interfaces in programming
        languages to help software projects produce high quality geospatial software. The core
        interfaces follow closely the specifications produced in the 19100 series of the
        International Organization for Standardization (<abbr>ISO</abbr>) and by the <abbr>OGC</abbr>.
        The interfaces use the  <code>org.opengis</code> namespace and copyright to the code
        is assigned to the <abbr>OGC</abbr>.
        The project started with the code produced by the <abbr>OGC</abbr> Implementation Specification 01-009
        <cite>Coordinate Transformation Services</cite> and refactored this code in collaboration
        with the standardization work surrounding the <abbr>OGC</abbr> specification 03-064
        <cite>GO-1 Application Objects</cite>.
      </p>
      <p>
        The GeoAPI working group of the <abbr>OGC</abbr> is a separate effort made up principally of members
        of the <abbr>OGC</abbr> and formed to continue the work of formalizing the interfaces developed by the
        GeoAPI project as ratified standards of the <abbr>OGC</abbr>. The working group decided to start the
        <cite>GeoAPI Implementation Specification</cite> as a new standard focused exclusively on the interfaces
        produced by the GeoAPI project. In acknowledgment to the earlier work and to match the numbering scheme
        of GeoAPI, the first specification released under this name carry the 3.0 version number.
      </p>
  - question: Why a standardized set of programming interfaces? Shouldn't <abbr>OGC</abbr> standards stick to web services only?
    anchor: 'why'
    answer:
      <p>
        We believe that both approaches are complementary. Web services are efficient ways to publish geographic
        information using existing software. But some users need to build their own solution, for example a numerical model.
        Many existing software packages provide sophisticated developer toolkits, but each toolkit has its own learning curve,
        and one can not easily switch from one toolkit to another or mix components from different toolkits.
        Using standardized interfaces, a significant part of the <abbr>API</abbr> can stay constant across different toolkits,
        thus reducing both the learning curve (especially since the interfaces are derived from published abstract UML)
        and the inter-operability pain points.
      </p>
      <p>
        The situation is quite similar to <abbr>JDBC</abbr> (<cite>Java DataBase Connectivity</cite>)'s one. The fact that a high-level
        language already exists for database queries (<abbr>SQL</abbr>) doesn't means that low-level programming interfaces are not needed.
        <abbr>JDBC</abbr> interfaces have been created as a developer tools in complement to <abbr>SQL</abbr>, and they proven to be quite useful.
      </p>
      <p>
        Web services and standard data formats are useful for transferring data to the scientist who processes them.
        But this approach become unpracticable with the very large amount of Earth Observation data.
        Instead, there is a need to do the opposite way - transfer the algorithm to the data in a cloud environment.
        In a situation where both the data and the user (the algorithm) are on the cloud,
        web services and standard data formats may not apply anymore at processing time
        (they are still relevant for fetching the processing results however).
        Instead, cloud environments like Google Earth Engine or Amazon Lambdas allow execution of user-defined algorithms
        in Python, Java or other programming languages, where no data transfer happens from user's perspective
        (whether data transfers happen under the hood when some commands are executed is implementation details).
        But those environments are currently defining their own APIs, at the risk of vendor-lockin.
        Defining a standard <abbr>API</abbr> suitable for cloud environments is part of GeoAPI goals.
      </p>
  - question: With standardization of interfaces, aren't you forcing a particular implementation?
    anchor: 'implementation'
    answer: |
      <p>
        We try to carefully avoid implementation-specific <abbr>API</abbr>.
        Again, <abbr>JDBC</abbr> is a good example of what we try to achieve.
        <abbr>JDBC</abbr> is an example of successful interfaces-only specification implemented by many vendors.
        Four categories of <abbr>JDBC</abbr> drivers exists (pure Java, wrappers around native code, etc.).
        Implementations exist for Access, Derby, HSQL, MySQL, Oracle, PostgreSQL and many others.
      </p>
      <p>
        It is important to stress out that GeoAPI is all about interfaces. Concrete classes must implement all methods
        declared in their interfaces, but those interfaces don't put any constraint on the class hierarchy. For example
        GeoAPI provides a <code>MathTransform2D</code> interface which extends <code>MathTransform</code>. In no way do
        implementation classes need to follow the same hierarchy. Actually, in the particular case of <code>MathTransforms</code>,
        they usually don't! A class implementing <code>MathTransform2D</code> doesn't need to extend a class implementing
        <code>MathTransform</code>. The only constraint is to implement all methods declared in the <code>MathTransform2D</code>
        interface and its parent interfaces.
      </p>
  - question: Why GeoAPI has some departures from <abbr>ISO</abbr> specifications? Shouldn't GeoAPI be strictly <abbr>ISO</abbr>-compliant?
    anchor: 'departures'
    answer: |
      <p>
        The <abbr>ISO</abbr> 19103, 19111 and 19115 specifications define mostly <cite>data structures</cite> convertible
        to XML or database schemas. Indeed, the <a href="http://www.epsg.org">EPSG</a>
        <svg class="w-4 h-4 fill-current text-gray-600 inline-block" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
        <path class="heroicon-ui" d="M19 6.41L8.7 16.71a1 1 0 1 1-1.4-1.42L17.58 5H14a1 1 0 0 1 0-2h6a1 1 0 0 1 1 1v6a1 1 0 0 1-2 0V6.41zM17 14a1 1 0 0 1 2 0v5a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V7c0-1.1.9-2 2-2h5a1 1 0 0 1 0 2H5v12h12v-5z"/>
        </svg> database schema follows
        closely the <abbr>ISO</abbr> 19111 structures. However those <abbr>ISO</abbr> specifications define few operations.
        For example <abbr>ISO</abbr> 19111 provides data structures for describing accurately <cite>Coordinate Reference System</cite>
        objects, but does not said much about how to get instances of them (except from a geodetic dataset).
        In terms of programming languages, the above-cited <abbr>ISO</abbr> specifications define mostly no-argument getter methods.
        Few methods performing calculation based on parameter values are specified.
      </p>
      <p>
        Since GeoAPI targets programming languages rather than data formats,
        we provide some more methods performing operations from programmatic parameters.
        Rather than inventing our own, we fetch those methods from other <abbr>OGC</abbr> specifications -
        including retired specifications - as much as possible.
        Those methods are documented in various places:
      </p>
      <ul class="list-disc ml-4">
        <li class="my-1 text-justify">In the &quot;<cite>Comparison with legacy <abbr>OGC</abbr> specifications</cite>&quot; annex in the
          <a href="https://www.opengeospatial.org/standards/geoapi">GeoAPI specification</a>
          <svg class="w-4 h-4 fill-current text-gray-600 inline-block" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
          <path class="heroicon-ui" d="M19 6.41L8.7 16.71a1 1 0 1 1-1.4-1.42L17.58 5H14a1 1 0 0 1 0-2h6a1 1 0 0 1 1 1v6a1 1 0 0 1-2 0V6.41zM17 14a1 1 0 0 1 2 0v5a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V7c0-1.1.9-2 2-2h5a1 1 0 0 1 0 2H5v12h12v-5z"/>
          </svg>.</li>
        <li class="my-1 text-justify">In the list of <a href="3.0/javadoc/departures.html">departures from the <abbr>ISO</abbr>/<abbr>OGC</abbr> specifications</a>
          (also an annex in the GeoAPI specification).</li>
        <li class="my-1 text-justify">In source code, class files and javadoc using the <a href="3.0/javadoc/org/opengis/annotation/UML.html">UML annotation</a>
          associated with the <a href="3.0/javadoc/org/opengis/annotation/Specification.html#OGC_01009"><abbr>OGC</abbr> 01-009 specification enum</a>
          or an other <abbr>OGC</abbr> enum.</li>
      </ul>
      <p>
        GeoAPI defines also a few convenience methods for frequently used operations
        (for example fetching the Coordinate Reference System of an Envelope)
        and methods for inter-operability with the standard library.
        Those methods are also documented in the departures page.
        Another reason is to adapt to constraints of target programming language
        (e.g. Java has no direct equivalent for the C/C++ <code>enum</code> construct).
      </p>
  - question: Why don't you translate all <abbr>OGC</abbr>'s UML into programmatic interfaces using some automatic script?
    anchor: 'scripts'
    answer: |
      <p>
        We tried that path at the beginning of GeoAPI project, and abandoned it.
        Automatic scripts provide useful starting points, but their output do not alway match the expectations of developers.
        For example a popular approach is to generate Java classes
        from the <a href="http://schemas.opengis.net">XML schemas</a>
        <svg class="w-4 h-4 fill-current text-gray-600 inline-block" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
        <path class="heroicon-ui" d="M19 6.41L8.7 16.71a1 1 0 1 1-1.4-1.42L17.58 5H14a1 1 0 0 1 0-2h6a1 1 0 0 1 1 1v6a1 1 0 0 1-2 0V6.41zM17 14a1 1 0 0 1 2 0v5a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V7c0-1.1.9-2 2-2h5a1 1 0 0 1 0 2H5v12h12v-5z"/>
        </svg>
        using JAXB-related technologies.
        Unfortunately the XML schemas defined by <abbr>ISO</abbr> 19139 are quite unusual, introducing a lot of redundant elements
        (e.g. <code>citation</code> property values wrapped in <code>CI_Citation</code> XML elements,
        and so on for all other properties).
        <abbr>API</abbr> generated from those schemas are twice bigger than the conceptual model.
      </p>
      <p>
        We could derive the <abbr>API</abbr> from the UML in <cite>Rational Rose</cite> format instead than the XML schemas,
        but a lot of human intervention is still essential.
        The relationship between UML and programmatic interfaces is not always straightforward.
        For example:
      </p>
      <ul class="list-disc ml-4">
        <li class="my-1 text-justify">
          Structures of type <code>union</code> are expressed in Java either by rearranging the
          interface hierarchy, by interface multi-inheritance or by omitting the data structure,
          on a case-by-case basis.
        </li>
        <li class="my-1 text-justify">
          Resolution of some specification overlapping require human reading. For example
          <abbr>ISO</abbr> 19111:2007 section 3 specifies &quot;<cite>in this international standard, normative
          reference to <abbr>ISO</abbr> 19115 excludes the <code>MD_CRS</code> class and its components classes</cite>&quot;
          in order to avoid duplication. An automatic script would not have done this exclusion.
        </li>
        <li class="my-1 text-justify">
          Some complexity introduced by historical standardization processes can be avoided.
          For example <abbr>ISO</abbr> 19115-2 defines imagery metadata which were not ready
          in time for the <abbr>ISO</abbr> 19115 schedule.
          Since new attributes could not be added to the existing <abbr>ISO</abbr> 19115 classes,
          they were added in <abbr>ISO</abbr> 19115-2 sub-classes of the same name (e.g. <code>MI_Band extends MD_Band</code>).
          GeoAPI merges those &quot;geological layers&quot;.
        </li>
        <li class="my-1 text-justify">
          Some additional interfaces or methods were introduced (see <a href="#departures">Why
          GeoAPI has some departures from <abbr>ISO</abbr> specifications?</a>).
        </li>
      </ul>
      <p>
        The changes applied by human intervention is documented in the
        <a href="3.0/javadoc/departures.html">departures</a> page.
      </p>
---

<h2 id="top">Frequently Asked Questions</h2>

<!-- questions -->
<ol class="mt-4 mb-8 ml-4 list-decimal">
{%- for faq in page.faqs -%}
<li class="my-1 text-justify"><a href="#{{ faq.anchor }}">{{ faq.question }}</a></li>
{%- endfor -%}
</ol>

<!-- answers -->
{%- for faq in page.faqs -%}
<div class="font-sans">
  <h3 id="{{ faq.anchor }}">{{ faq.question }}</h3>
</div>

<div class="my-2">
{{ faq.answer }}
<a href="#top" class="my-4 text-sm text-gray-600">Back to the top</a>
</div>

{%- if forloop.last == false -%}
<hr class="mb-4" />
{%- endif -%}
{%- endfor -%}
