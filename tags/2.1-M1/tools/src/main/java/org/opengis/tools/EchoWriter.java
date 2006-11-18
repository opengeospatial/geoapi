/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.tools;

// J2SE dependencies
import java.io.Writer;
import java.io.FilterWriter;
import java.io.IOException;


/**
 * A writer that send every text to two writers.
 *
 * @author Martin Desruisseaux
 */
final class EchoWriter extends FilterWriter {
    /**
     * The writer where to send a copy of all data.
     */
    private final Writer echo;

    /**
     * Creates a new instance of {@code EchoWriter}.
     */
    public EchoWriter(final Writer main, final Writer echo) {
        super(main);
        this.echo = echo;
    }

    /**
     * Write a single character.
     */
    @Override
    public void write(final int c) throws IOException {
        super.write(c);
        echo .write(c);
    }

    /**
     * Write a portion of an array of characters.
     *
     * @param  cbuf   Buffer of characters to be written
     * @param  offset Offset from which to start reading characters
     * @param  length Number of characters to be written
     */
    @Override
    public void write(final char[] cbuf, final int offset, final int length) throws IOException {
        super.write(cbuf, offset, length);
        echo .write(cbuf, offset, length);
    }

    /**
     * Write a portion of a string.
     *
     * @param  str    String to be written
     * @param  offset Offset from which to start reading characters
     * @param  length Number of characters to be written
     */
    @Override
    public void write(final String str, final int offset, final int length) throws IOException {
        super.write(str, offset, length);
        echo .write(str, offset, length);
    }

    /**
     * Flush the stream.
     */
    @Override
    public void flush() throws IOException {
        super.flush();
        echo .flush();
    }

    /**
     * Close the stream.
     */
    @Override
    public void close() throws IOException {
        super.close();
        echo .close();
    }
}
