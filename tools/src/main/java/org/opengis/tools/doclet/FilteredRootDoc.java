/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2009-2015 Open Geospatial Consortium, Inc.
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
package org.opengis.tools.doclet;

import com.sun.javadoc.RootDoc;
import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.PackageDoc;
import com.sun.javadoc.Tag;
import com.sun.javadoc.SeeTag;
import com.sun.javadoc.SourcePosition;


/**
 * Delegates all method calls to the original {@code RootDoc} given at construction time,
 * except the options which are replaced by the array given at construction time.
 *
 * <p><b>Source:</b> this class is a copy of Apache SIS doclet, relicensed to OGC by the Doclet author.</p>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @since   3.1
 * @version 3.1
 */
final class FilteredRootDoc implements RootDoc {
    /**
     * The original object where to delegate all (except options) method calls.
     */
    private final RootDoc doc;

    /**
     * The options to be returned by {@link #options()}.
     */
    private final String[][] options;

    /**
     * Creates a new instance which will delegate all method calls to the given object,
     * except for the given options.
     */
    FilteredRootDoc(final RootDoc doc, final String[][] options) {
        this.doc = doc;
        this.options = options;
    }

    @Override public String[][]     options()                   {return options.clone();}
    @Override public PackageDoc[]   specifiedPackages()         {return doc.specifiedPackages();}
    @Override public ClassDoc[]     specifiedClasses()          {return doc.specifiedClasses();}
    @Override public ClassDoc[]     classes()                   {return doc.classes();}
    @Override public PackageDoc     packageNamed(String n)      {return doc.packageNamed(n);}
    @Override public ClassDoc       classNamed(String n)        {return doc.classNamed(n);}
    @Override public String         commentText()               {return doc.commentText();}
    @Override public Tag[]          tags()                      {return doc.tags();}
    @Override public Tag[]          tags(String n)              {return doc.tags(n);}
    @Override public SeeTag[]       seeTags()                   {return doc.seeTags();}
    @Override public Tag[]          inlineTags()                {return doc.inlineTags();}
    @Override public Tag[]          firstSentenceTags()         {return doc.firstSentenceTags();}
    @Override public String         getRawCommentText()         {return doc.getRawCommentText();}
    @Override public void           setRawCommentText(String n) {       doc.setRawCommentText(n);}
    @Override public String         name()                      {return doc.name();}
    @Override public int            compareTo(Object o)         {return doc.compareTo(o);}
    @Override public boolean        isField()                   {return doc.isField();}
    @Override public boolean        isEnumConstant()            {return doc.isEnumConstant();}
    @Override public boolean        isConstructor()             {return doc.isConstructor();}
    @Override public boolean        isMethod()                  {return doc.isMethod();}
    @Override public boolean        isAnnotationTypeElement()   {return doc.isAnnotationTypeElement();}
    @Override public boolean        isInterface()               {return doc.isInterface();}
    @Override public boolean        isException()               {return doc.isException();}
    @Override public boolean        isError()                   {return doc.isError();}
    @Override public boolean        isEnum()                    {return doc.isEnum();}
    @Override public boolean        isAnnotationType()          {return doc.isAnnotationType();}
    @Override public boolean        isOrdinaryClass()           {return doc.isOrdinaryClass();}
    @Override public boolean        isClass()                   {return doc.isClass();}
    @Override public boolean        isIncluded()                {return doc.isIncluded();}
    @Override public SourcePosition position()                  {return doc.position();}

    @Override public void printError  (                    String msg)  {doc.printError(msg);}
    @Override public void printError  (SourcePosition pos, String msg)  {doc.printError(pos, msg);}
    @Override public void printWarning(                    String msg)  {doc.printWarning(msg);}
    @Override public void printWarning(SourcePosition pos, String msg)  {doc.printWarning(pos, msg);}
    @Override public void printNotice (                    String msg)  {doc.printNotice(msg);}
    @Override public void printNotice (SourcePosition pos, String msg)  {doc.printNotice(pos, msg);}
}
