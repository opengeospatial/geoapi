# Web site for GeoAPI

This branch contains the HTML pages for http://www.geoapi.org/ web site.
The pages are generated by the commands shown below.


## Prerequites

* Java 11 or above
* [Maven 3](http://maven.apache.org/)
* [Python 3](https://www.python.org/)
* [Sphinx](http://www.sphinx-doc.org/) for Python 3
* A checkout of this branch
* A checkout of `3.1.x` branch
* A checkout of `master` branch


## Generating the web site

Execute the following command in the directory containing this branch:

```
export GEOAPI_SITE=`pwd`
```

Execute the following commands in the directory containing the GeoAPI 3.1.x branch:

```
mvn clean install site
mvn site:deploy -DdistMgmtWebSiteUrl=file://$GEOAPI_SITE
```

Execute the following commands in the directory containing the GeoAPI master branch:

```
sphinx-build-3 -b html src/site/sphinx $GEOAPI_SITE/snapshot/python
```

Then:

```
cd $GEOAPI_SITE
mv geoapi/apidocs/* snapshot/javadoc/
rmdir geoapi/apidocs
```

In the `geoapi` subdirectory, replace all occurrences of `apidocs` by `../snapshot/javadoc` in URLs.
Finally verify if there is some old files that should be removed.
The following example list files older than 10 days.
Be careful to not delete files that are not generated by above Maven commands,
in particular all the files in the directories for older versions.

```
find -type f -mtime +10
find -type d -empty -delete
```

Review differences:

```
git diff --ignore-all-space
```

If satisfying, commit and push.
