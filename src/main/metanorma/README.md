# GeoAPI specification

This directory contains the source files of GeoAPI standard.

* `document.adoc` — the main standard document with references to all sections.
* `sections`      — each section of the standard document in a separate document.
* `requirements`  — requirements and requirement classes referenced in `normative_text.adoc`.
* `tests`         — conformance test suite.
* `code`          — sample code to accompany the standard.
* `figures`       — figures, including figures generated from UML.
* `UML`           — UML diagrams.


## Build locally

Ensure that [Metadatanorma is installed](https://www.metanorma.org/install/).
Then run from the command line
(the `--agree-to-terms` option is for retrieving licensed fonts):

```shell
metanorma --agree-to-terms --type ogc --extensions pdf,html document.adoc
```

Above command will generate the following files and directories.
Those files can be moved or deleted for cleaning the directory:

* Output: `document.pdf` and `document.html`.
* Warnings about the source files: `document.err`.
* Metanorma internal files (to ignore): `_files`, `iev`, `document.presentation.xml`


## References

* [Authoring guide](https://www.metanorma.org/author/ogc/authoring-guide/)
