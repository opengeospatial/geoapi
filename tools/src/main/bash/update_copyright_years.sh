#!/bin/sh
set -o errexit

#
# List in a temporary files all years having at least one commit in the specified file.
# The `--follow` option tries to recognize the cases where the file has been renamed,
# but unfortunately produces a lot of false positives. For reducing those errors,
# we use a high threshold (90%) in deciding how similar the files must be.
#
TMP_FILE=/tmp/commit-years.txt
git log --regexp-ignore-case --invert-grep --grep "Update.*copyright" --follow --find-renames=90% --format=%ad --date=format:%Y -- "$1" > $TMP_FILE

#
# In order to compensate for the high similarity threshold, search for the year where
# a file of the same name has been added in the repository, regardless if related or
# not (according git history) to the specified file. This is okay in GeoAPI case,
# except for "module-info" and "package-info" files.
#
FILENAME=`basename "$1"`
if [[ $FILENAME == Assertions.java ]]; then
    FILENAME="Assert.java"
fi
if [[ $FILENAME == AssertionsTest.java ]]; then
    FILENAME="AssertTest.java"
fi
if [[ $FILENAME == CompatibilityVerifier.java ]]; then
    FILENAME="CompatibilityTest.java"
fi
if [[ $FILENAME == ContentVerifier.java ]]; then
    FILENAME="ContentTest.java"
fi
if ! [[ $FILENAME == *-info.java ]]; then
    git log --regexp-ignore-case --invert-grep --grep "Update.*copyright" --diff-filter=A --format=%ad --date=format:%Y -- "**/$FILENAME" >> $TMP_FILE
fi

#
# Get the lower and upper bounds of the range of years where the file has been modified.
#
TMP_FILE_SORTED=/tmp/commit-years-sorted.txt
sort --unique < $TMP_FILE > $TMP_FILE_SORTED
LOWER=`head --lines=1 $TMP_FILE_SORTED`
UPPER=`tail --lines=1 $TMP_FILE_SORTED`

#
# Search for the copyright notice with any range of years, and replace by the same notice but with the range of years found in git log.
# The modification of source code in the git repository happens here.
#
sed --in-place "s/ Copyright © .* Open Geospatial Consortium, Inc\./ Copyright © $LOWER-$UPPER Open Geospatial Consortium, Inc\./g" "$1"
sed --in-place "s/ Copyright © \([0-9]\+\)-\1/ Copyright © \1/g" "$1"
