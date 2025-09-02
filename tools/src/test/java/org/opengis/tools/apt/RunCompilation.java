/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2014-2024 Open Geospatial Consortium, Inc.
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
package org.opengis.tools.apt;

import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.io.File;
import java.io.FileFilter;
import java.net.URL;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import javax.tools.ToolProvider;
import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Launches the Java annotation processor on the {@code geoapi} module.
 * This launcher is used for running {@link IndexGenerator} step-by-step in a debugger.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public final class RunCompilation implements FileFilter {
    /**
     * Launches the Java annotation processor.
     *
     * @throws Exception if any kind of errors occurred.
     */
    @Test
    @Disabled("Used for debugging purpose only.")
    public void main() throws Exception {
        final JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        final StandardJavaFileManager fm = compiler.getStandardFileManager(null, null, StandardCharsets.UTF_8);
        final JavaCompiler.CompilationTask task = compiler.getTask(null, fm, null, List.of(
                    "-proc:only",             // Perform only annotation processing, no compilation.
                    "-XprintProcessorInfo",   // Prints information about which annotations a processor is asked to process.
                    "-XprintRounds",          // Prints information about initial and subsequent annotation processing rounds.
                    "-Aoutput=content.html"), // Option for our annotation processor: the file to write.
                null,                         // Annotation processor shall process all classes.
                fm.getJavaFileObjectsFromFiles(collectJavaFiles()));
        task.setProcessors(Set.of(new IndexGenerator()));
        assertTrue(task.call());
    }

    /**
     * Returns the {@code geoapi/src} directory.
     */
    private static File getSourceDirectory() throws URISyntaxException {
        final URL t = RunCompilation.class.getResource(RunCompilation.class.getSimpleName() + ".class");
        assertNotNull(t, "Path to class file not found.");
        File file = new File(t.toURI());
        do {
            file = file.getParentFile();
            assertNotNull(file, "Unexpected path to class file.");
        } while (!file.getName().equals("test-classes"));
        assertEquals ((file = file.getParentFile()).getName(), "target", "Unexpected path to class file.");
        assertEquals ((file = file.getParentFile()).getName(), "tools",  "Unexpected path to class file.");
        assertNotNull((file = file.getParentFile()),                     "Unexpected path to class file.");
        assertTrue((file = new File(file, "geoapi/src")).isDirectory(),  "Source directory not found.");
        return file;
    }

    /**
     * Recursively collects all Java source files in the {@code geoapi/src/main/java}
     * and {@code geoapi/src/pending/java} directories.
     */
    private Collection<File> collectJavaFiles() throws URISyntaxException {
        final File src = getSourceDirectory();
        final Collection<File> files = new ArrayList<>();
        collectJavaFiles(new File(src, "main/java"),    files);
        collectJavaFiles(new File(src, "pending/java"), files);
        return files;
    }

    /**
     * Recursively add all Java source files in the given collection.
     */
    private void collectJavaFiles(final File directory, final Collection<File> addTo) {
        for (final File file : directory.listFiles(this)) {
            if (file.isDirectory()) {
                collectJavaFiles(file, addTo);
            } else {
                assertTrue(addTo.add(file));
            }
        }
    }

    /**
     * Returns {@code true} if we accepts the process the given file or directory.
     *
     * @param  pathname the path to test for inclusion.
     * @return {@code true} if the given path should be processed.
     */
    @Override
    public boolean accept(final File pathname) {
        final String name = pathname.getName();
        return !name.startsWith(".") && (name.endsWith(".java") || pathname.isDirectory());
    }
}
