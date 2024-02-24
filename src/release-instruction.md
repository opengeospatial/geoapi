# Steps for creating a GeoAPI milestone in a Unix environment


## Prepare environment
Define the version number for the GeoAPI milesone.
The example below uses 4.0. Please edit as needed.

```shell
export GEOAPI_SNAPSHOT=4.0-SNAPSHOT
export GEOAPI_VERSION=4.0
```


## Prepare the code
If not already done, ensure that the `src/main/javadoc/content.html` file is up to date.
The following commands assume that the current directory (`pwd`) is the local checkout of GeoAPI.

```shell
export CLASSPATH=$PWD/geoapi/target/geoapi-$GEOAPI_SNAPSHOT.jar:\
$PWD/geoapi/target/geoapi-$GEOAPI_SNAPSHOT.jar:\
$PWD/tools/target/tools-$GEOAPI_SNAPSHOT.jar

cd geoapi/src/main/java
find . -name "*.java" -print | xargs apt -nocompile -factory org.opengis.tools.apt.IndexGenerator
cd -
git add --update
git commit --message "Updated the list of GeoAPI classes and methods."
```


## Create the tag and deploy
Create a temporary branch. We will delete that branch later, before to push.

```shell
git checkout -b deployment
```

Edit the version number on that branch only:
(**Note:** BSD variant of `sed` expects `-i ''` parameter instead of `--in-place=''`)

```shell
find . -name "pom.xml" -print | xargs sed --in-place='' "s/$GEOAPI_SNAPSHOT/$GEOAPI_VERSION/g"
git clean --force
git diff
git add --update
```


Test the build. If successful, commit, tag, and deploy.
For milestones, we create lightweight tags rather than annotated tags.

```shell
mvn clean install
git status
git commit --message "Set the version number to $GEOAPI_VERSION."
git tag $GEOAPI_VERSION
mvn clean deploy
```

In case of mistake:

* the tag can be deleted with `git tag --delete $GEOAPI_VERSION`
* the last commit can be unstaged with `git reset HEAD^`
* the local changes can be discarded with `git checkout -- .`

If everything is okay, push the tag. Do it only when sure.

```shell
git checkout master
git branch -D deployment
git show $GEOAPI_VERSION
git push origin $GEOAPI_VERSION
```


## Create the javadoc and the ZIP files
Generate javadoc.

```shell
mvn javadoc:javadoc
```

Creates a ZIP file and deploy on the download area
**(todo: which download area?)**

```shell
cd geoapi/target/site/
zip -r9 ../geoapi-$GEOAPI_VERSION.zip apidocs/
cd ..
zip -9 geoapi-$GEOAPI_VERSION.zip geoapi-$GEOAPI_VERSION.jar
```


## Create the list of API changes since previous releases
Edit the version numbers declared in the `ChangeReport.java` file,
then apply the instructions documented in the [tools page](../README.md).


## Final clean
Only at this point, the working directory can be cleaned:

```shell
cd ../..
mvn clean
```

Update the `archive` directory in the GeoAPI web site.
