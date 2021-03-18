/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2009-2021 Open Geospatial Consortium, Inc.
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

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.io.Flushable;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import javax.tools.Diagnostic;
import jdk.javadoc.doclet.Reporter;
import jdk.javadoc.doclet.Doclet.Option;
import jdk.javadoc.doclet.DocletEnvironment;
import jdk.javadoc.doclet.StandardDoclet;


/**
 * A doclet which delegates the work to the standard doclet and completes by copying resources and generating
 * the list of departures.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @since   3.1
 * @version 3.1
 */
public final class Doclet extends StandardDoclet {
    /**
     * Files to excludes when copying {@code src/main/javadoc} resources.
     */
    private static final Set<String> EXCLUDES = Set.of("README.md", "overview.html", "geoapi.css");

    /**
     * The directory where HTML pages will be written.
     */
    private String outputDirectory;

    /**
     * Where to report warnings, or {@code null} if unknown.
     *
     * Have to be static for now because we did not found a better way to transfer this information to the taglet.
     */
    private static Reporter reporter;

    /**
     * Process to execute after the Javadoc generation has been completed.
     * This is used for writing summary tables.
     *
     * <p>Has to be public static for now as a workaround for
     * <a href="https://bugs.openjdk.java.net/browse/JDK-8201817">JDK-8201817</a>.</p>
     */
    public static Flushable postProcess;

    /**
     * Invoked by the Javadoc tools for instantiating the custom doclet.
     */
    public Doclet() {
    }

    /**
     * Returns a name identifying this doclet.
     *
     * @return "GeoAPI".
     */
    @Override
    public String getName() {
        return "GeoAPI";
    }

    /**
     * Invoked by the Javadoc tools for initializing the doclet.
     *
     * @param locale    the locale to use for formatting HTML content.
     * @param reporter  where to report warnings and errors.
     */
    @Override
    public void init(final Locale locale, final Reporter reporter) {
        super.init(locale, reporter);
        Doclet.reporter = reporter;
    }

    /**
     * Returns the object to use for reporting warnings, or {@code null} if unknown. Has to be public static
     * for now as a workaround for <a href="https://bugs.openjdk.java.net/browse/JDK-8201817">JDK-8201817</a>.
     *
     * @return where to report warnings, or {@code null} if unknown.
     */
    public static Reporter reporter() {
        return reporter;
    }

    /**
     * Forwards all method to the given option except {@link #process(String, List)},
     * which is first forwarded to {@link #define(String)}.
     */
    private static abstract class OptionWrapper implements Option {
        private final Option opt;
        OptionWrapper(final Option op) {opt = op;}
        protected abstract void define(String value);

        @Override public final int          getArgumentCount() {return opt.getArgumentCount();}
        @Override public final String       getDescription()   {return opt.getDescription();}
        @Override public final Option.Kind  getKind()          {return opt.getKind();}
        @Override public final List<String> getNames()         {return opt.getNames();}
        @Override public final String       getParameters()    {return opt.getParameters();}
        @Override public final boolean      process(String option, List<String> arguments) {
            define(arguments.get(0));
            return opt.process(option, arguments);
        }
    }

    /**
     * Returns the options supported by the standard doclet.
     *
     * @return all the supported options.
     */
    @Override
    public Set<Option> getSupportedOptions() {
        final Set<Option> options = new LinkedHashSet<>();
        for (Option op : super.getSupportedOptions()) {
            final List<String> names = op.getNames();
            if (names.contains("-d")) {
                op = new OptionWrapper(op) {
                    @Override protected void define(final String value) {
                        outputDirectory = value;
                    }
                };
            }
            options.add(op);
        }
        return options;
    }

    /**
     * Invoked by Javadoc for starting the doclet.
     *
     * @param  environment  the Javadoc environment.
     * @return {@code true} on success, or {@code false} on failure.
     */
    @Override
    @SuppressWarnings("CallToPrintStackTrace")
    public boolean run(final DocletEnvironment environment) {
        boolean status = super.run(environment);
        if (status) try {
            if (outputDirectory != null) {
                final Path output = Paths.get(outputDirectory);
                Path input = parent(output, "apidocs", "site", "target");
                if (input == null) {
                    printError("Not the expected Maven target directory: " + output);
                } else {
                    input = input.resolve("src").resolve("main").resolve("javadoc");
                    if (!Files.isDirectory(input)) {
                        printError("Directory not found: " + input);
                    } else {
                        copyResources(input, output);
                    }
                }
            }
            if (postProcess != null) {
                postProcess.flush();
            }
        } catch (IOException e) {
            if (reporter != null) {
                final StringWriter buffer = new StringWriter();
                final PrintWriter p = new PrintWriter(buffer);
                e.printStackTrace(p);
                reporter.print(Diagnostic.Kind.ERROR, buffer.toString());
            } else {
                e.printStackTrace();
            }
            status = false;
        }
        return status;
    }

    /**
     * Returns the parent directory, which is expected to have the given name.
     * This algorithm is repeated until all given names have been found.
     * If at least one parent directory does not have the expected name,
     * then this method returns {@code null}.
     */
    private static Path parent(Path path, final String... expectedNames) {
        for (final String name : expectedNames) {
            if (!name.equals(path.getFileName().toString())) {
                return null;
            }
            path = path.getParent();
        }
        return path;
    }

    /**
     * Creates links to Javadoc resources in the top-level directory (not from "{@code doc-files}" subdirectories).
     * While the Maven documentation said that the "{@code src/main/javadoc}" directory is copied by default, or a
     * directory can be specified with {@code <javadocDirectory>}, I have been unable to make it work even with
     * absolute paths.
     *
     * @param  inputFile        the directory containing resources.
     * @param  outputDirectory  the directory where to copy the resource files.
     * @throws IOException      if an error occurred while reading or writing.
     */
    private static void copyResources(final Path inputDirectory, final Path outputDirectory) throws IOException {
        Files.walkFileTree(inputDirectory, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs) throws IOException {
                final String filename = file.getFileName().toString();
                if (!filename.startsWith(".") && !EXCLUDES.contains(filename)) {
                    final Path target = outputDirectory.resolve(inputDirectory.relativize(file));
                    Files.copy(file, target, StandardCopyOption.REPLACE_EXISTING);
                }
                return FileVisitResult.CONTINUE;
            }
        });
    }

    /**
     * Prints an error message.
     */
    @SuppressWarnings("UseOfSystemOutOrSystemErr")
    private static void printError(final String message) {
        if (reporter != null) {
            reporter.print(Diagnostic.Kind.ERROR, message);
        } else {
            System.err.println(message);
        }
    }
}
