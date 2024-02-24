/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2009-2023 Open Geospatial Consortium, Inc.
 *    http://www.geoapi.org
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.opengis.tools.doclet;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
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
import jdk.javadoc.doclet.DocletEnvironment;
import jdk.javadoc.doclet.StandardDoclet;


/**
 * A doclet which delegates the work to the standard doclet and completes by copying resources and generating
 * the list of departures after all Javadoc pages have been generated.
 *
 * <p>The {@code Consumer<Flushable>} interface is used as a callback mechanism for taglet initialization.
 * Taglets cannot invoke {@code FlushableDoclet} methods directly because the doclet initialized by Javadoc
 * tools does not use the same class loader as taglet. {@link BlockTaglet} instances can communicate with
 * the {@code FlushableDoclet} instance only using objects from the standard Java library.</p>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @since   3.1
 * @version 3.1
 */
public final class FlushableDoclet extends StandardDoclet implements Consumer<Flushable> {
    /**
     * Files to excludes when copying {@code src/main/javadoc} resources.
     */
    private static final Set<String> EXCLUDES = Set.of("README.md", "overview.html", "geoapi.css");

    /**
     * The directory where HTML pages will be written.
     */
    private String outputDirectory;

    /**
     * Process to execute after the Javadoc generation has been completed.
     * This is used for writing summary tables.
     */
    private Flushable postProcess;

    /**
     * Invoked by the Javadoc tools for instantiating the custom doclet.
     */
    public FlushableDoclet() {
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
     * Registers an action to execute after doclet finished to generate Javadoc.
     * This initialization is done through an interface of the standard Java API ({@link Flushable})
     * because custom methods cannot be invoked at taglet initialization time, because of different
     * class loaders.
     *
     * @param  finisher  action to execute at the end of javadoc generation.
     */
    @Override
    public void accept(final Flushable finisher) {
        postProcess = finisher;
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
                    input = input.getParent().resolve("src").resolve("main").resolve("javadoc");
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
            final Reporter reporter = getReporter();
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
    private void printError(final String message) {
        final Reporter reporter = getReporter();
        if (reporter != null) {
            reporter.print(Diagnostic.Kind.ERROR, message);
        } else {
            System.err.println(message);
        }
    }
}
