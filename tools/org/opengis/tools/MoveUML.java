/*
 * Helper tools for OpenGIS interface.
 */
package org.opengis.tools;

// J2SE dependencies
import java.io.*;


/**
 * Moves @UML tags out of javadoc comments. Put them as (commented-out)
 * annotations instead. The file or directory to process must be specified
 * as an argument on the command line. All ".java" file in the specified
 * directory and all sub-directories will be processed recursively.
 *
 * @author Martin Desruisseaux
 */
public class MoveUML {
    /**
     * The UML tag to search for in javadoc.
     */
    private static final String UML_TAG = "* @UML ";
    
    /**
     * Read all lines from the specified stream, and reformat them as a String.
     * Unusual @UML tags will be left unchanged as javadoc tag.
     *
     * @param  in The stream to read from. This stream will not be closed.
     * @return The reformatted file content as a string, or <code>null</code>
     *         if nothing was changed.
     * @throws IOException if a read operation failed.
     */
    private static String process(final BufferedReader in) throws IOException {
        boolean modified = false;
        final StringBuffer buffer = new StringBuffer();
        /*
         * Skip the copyright statement.
         */
        String line; while ((line=in.readLine()) != null) {
            buffer.append(line);
            buffer.append('\n');
            if (line.trim().startsWith("package ")) {
                break;
            }
        }
        /*
         * Add the 'import' statements.
         */
        while ((line=in.readLine()) != null) {
            if (line.indexOf("/**") < 0) {
                buffer.append(line);
                buffer.append('\n');
                continue;
            }
            int i = buffer.length();
            while (i>0 && Character.isWhitespace(buffer.charAt(--i)));
            buffer.setLength(i+1);
            buffer.append("\n\n" +
                          "// Annotations\n" +
                          "///import org.opengis.annotation.UML;\n" +
                          "///import static org.opengis.annotation.Obligation.*;\n" +
                          "\n\n");
            buffer.append(line);
            buffer.append('\n');
            break;
        }
        /*
         * Process the @UML tags.
         */
        while ((line=in.readLine()) != null) {
            final int start = line.indexOf(UML_TAG);
            if (start < 0) {
                buffer.append(line);
                buffer.append('\n');
                continue;
            }
            final String[] words = line.substring(start + UML_TAG.length()).split("\\s+");
            if (words.length != 2) {
                buffer.append(line);
                buffer.append('\n');
                continue;
            }
            String next; while ((next=in.readLine()) != null) {
                buffer.append(next);
                buffer.append('\n');
                if (next.indexOf("*/") >= 0) {
                    break;
                }
            }
            final boolean classUML = (start < 4);
            buffer.append("///");
            if (!classUML) {
                buffer.append(' ');
            }
            buffer.append("@UML (identifier=\"");
            buffer.append(words[1]);
            buffer.append('"');
            if (!classUML) {
                buffer.append(", obligation=");
                if (words[0].equalsIgnoreCase("optional") ||
                    words[0].equalsIgnoreCase("conditional")) {
                    words[0] = words[0].toUpperCase();
                } else {
                    words[0] = "MANDATORY";
                }
                buffer.append(words[0]);
            }
            buffer.append(")\n");
            modified = true;
        }
        return modified ? buffer.toString() : null;
    }

    /**
     * Read all lines from the specified file or directory. The specified file will be
     * overwritten by the reformated code. If the specified file is a directory, then
     * all file contained and all sub-directories will be processed recursively.
     *
     * @param  file The file or directory to process.
     * @throws IOException if a read or write operation failed.
     */
    private static void process(final File file) throws IOException {
        if (file.isDirectory()) {
            final File[] files = file.listFiles();
            for (int i=0; i<files.length; i++) {
                process(files[i]);
            }
            return;
        }
        if (!file.getName().endsWith(".java")) {
            return;
        }
        final BufferedReader in = new BufferedReader(new FileReader(file));
        final String buffer = process(in);
        in.close();
        if (buffer != null) {
            System.out.println(file);
            final FileWriter out = new FileWriter(file);
            out.write(buffer);
            out.close();
        }
    }

    /**
     * Run the program from the command line.
     *
     * @param args the command line arguments;
     * @throws IOException if a read or write operation failed.
     */
    public static void main(final String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Expected argument: directory to process.");
            return;
        }
        process(new File(args[0]));
    }
}
