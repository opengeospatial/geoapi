/*
 * Geotools 2 - OpenSource mapping toolkit
 * (C) 2004, Geotools Project Managment Committee (PMC)
 * (C) 2004, Institut de Recherche pour le Développement
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation; either
 *    version 2.1 of the License, or (at your option) any later version.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 *
 *    You should have received a copy of the GNU Lesser General Public
 *    License along with this library; if not, write to the Free Software
 *    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package org.opengis.tools;

// J2SE dependencies
import java.io.*;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.lang.reflect.*;
import java.net.URLDecoder;
import java.util.logging.Logger;

// OpenGIS dependencies
import org.opengis.util.CodeList;
import org.opengis.referencing.cs.AxisDirection;

// JUnit dependencies
import junit.framework.*;


/**
 * Test all code list in all packages found.
 *
 * @version $Id$
 * @author Martin Desruisseaux
 */
public class CodeListTest extends TestCase implements FileFilter {
    /**
     * Set to <code>true</code> for verbose mode (for debugging only).
     */
    private static final boolean VERBOSE = false;
    
    /**
     * List of enums to ignore in the {@link #process} operation.
     * Must be static because JUnit seems to recreate this object
     * for each test.
     */
    private static final Set<CodeList> toIgnore = new HashSet<CodeList>();
    
    /**
     * Run the suite from the command line.
     */
    public static void main(String[] args) {
        org.geotools.util.MonolineFormatter.initGeotools();
        junit.textui.TestRunner.run(suite());
    }
    
    /**
     * Returns the test suite.
     */
    public static Test suite() {
        return new TestSuite(CodeListTest.class);
    }

    /**
     * Construct a test case.
     */
    public CodeListTest(String testName) {
        super(testName);
    }
    
    /**
     * Test {@link AxisDirection} operations.
     */
    public void testAxisDirection() {
        assertSame("SOUTH",         AxisDirection.NORTH,         AxisDirection.SOUTH        .opposite());
        assertSame("NORTH",         AxisDirection.SOUTH,         AxisDirection.NORTH        .opposite());
        assertSame("WEST",          AxisDirection.EAST,          AxisDirection.WEST         .opposite());
        assertSame("EAST",          AxisDirection.WEST,          AxisDirection.EAST         .opposite());
        assertSame("DOWN",          AxisDirection.UP,            AxisDirection.DOWN         .opposite());
        assertSame("UP",            AxisDirection.DOWN,          AxisDirection.UP           .opposite());
        assertSame("DISPLAY_LEFT",  AxisDirection.DISPLAY_RIGHT, AxisDirection.DISPLAY_LEFT .opposite());
        assertSame("DISPLAY_RIGHT", AxisDirection.DISPLAY_LEFT,  AxisDirection.DISPLAY_RIGHT.opposite());
        assertSame("DISPLAY_DOWN",  AxisDirection.DISPLAY_UP,    AxisDirection.DISPLAY_DOWN .opposite());
        assertSame("DISPLAY_UP",    AxisDirection.DISPLAY_DOWN,  AxisDirection.DISPLAY_UP   .opposite());
        assertSame("PAST",          AxisDirection.FUTURE,        AxisDirection.PAST         .opposite());
        assertSame("FUTURE",        AxisDirection.PAST,          AxisDirection.FUTURE       .opposite());
        assertSame("OTHER",         AxisDirection.OTHER,         AxisDirection.OTHER        .opposite());
        assertSame("SOUTH",         AxisDirection.NORTH,         AxisDirection.SOUTH        .absolute());
        assertSame("NORTH",         AxisDirection.NORTH,         AxisDirection.NORTH        .absolute());
        assertSame("WEST",          AxisDirection.EAST,          AxisDirection.WEST         .absolute());
        assertSame("EAST",          AxisDirection.EAST,          AxisDirection.EAST         .absolute());
        assertSame("DOWN",          AxisDirection.UP,            AxisDirection.DOWN         .absolute());
        assertSame("UP",            AxisDirection.UP,            AxisDirection.UP           .absolute());
        assertSame("DISPLAY_LEFT",  AxisDirection.DISPLAY_RIGHT, AxisDirection.DISPLAY_LEFT .absolute());
        assertSame("DISPLAY_RIGHT", AxisDirection.DISPLAY_RIGHT, AxisDirection.DISPLAY_RIGHT.absolute());
        assertSame("DISPLAY_DOWN",  AxisDirection.DISPLAY_UP,    AxisDirection.DISPLAY_DOWN .absolute());
        assertSame("DISPLAY_UP",    AxisDirection.DISPLAY_UP,    AxisDirection.DISPLAY_UP   .absolute());
        assertSame("PAST",          AxisDirection.FUTURE,        AxisDirection.PAST         .absolute());
        assertSame("FUTURE",        AxisDirection.FUTURE,        AxisDirection.FUTURE       .absolute());
        assertSame("OTHER",         AxisDirection.OTHER,         AxisDirection.OTHER        .absolute());
    }
    
    /**
     * Test the extensibility of some enums.
     */
    public void testExtensibility() {
        final AxisDirection[] dir = AxisDirection.values();
        final AxisDirection DUMMY = new AxisDirection("DUMMY");
        final AxisDirection[] dim = AxisDirection.values();
        assertEquals("Addition 1", dir.length+1, dim.length);
        assertEquals("Ordinal", dir.length, DUMMY.ordinal());
        assertSame("Last element", DUMMY, dim[dir.length]);
        for (int i=0; i<dir.length; i++) {
            assertSame("Element", dir[i], dim[i]);
        }
        assertTrue(toIgnore.add(DUMMY));
    }
    
    /**
     * Test all code list found in the system.
     */
    public void testCodeList() {
        final File base = defaultRootDirectory();
        scan(base, base);
    }
    
    /**
     * Filter the files to includes on the processing. Only the files with the ".class" extensions
     * will be loaded. Directory must be included too if recursive scanning is wanted.
     */
    public boolean accept(final File pathname) {
        final String name = pathname.getName();
        if (pathname.isDirectory()) {
            if (name.equals("org")) {
                return true;
            }
            if (name.equals("go") || name.equals("tools")) {
                return false;
            }
            return pathname.getPath().indexOf("opengis") >= 0;
        } else {
            if (name.equals("SimpleEnumerationType.class")) {
                return false;
            }
        }
        return name.endsWith(".class");
    }
    
    /**
     * Returns the default root directory, or <code>null</code> if not found.
     * The default root directory is the one where the implementation of this
     * class is found, up to the 'org' package.
     */
    private File defaultRootDirectory() {
        final Class c = getClass();
        final URL url = c.getClassLoader().getResource(c.getName().replace('.','/')+".class");
        if (VERBOSE) {
            System.out.print("Class loader path:   ");
            System.out.println(url);
        }
        if (url==null || !url.getProtocol().trim().equalsIgnoreCase("file")) {
            return null; // bad developer no test not on filesystem
        }
        String path;
        try {
            path = URLDecoder.decode(url.getPath(), "UTF-8");
        } catch (UnsupportedEncodingException exception) {
            return null;
        }
        if (path.length()>=3 && path.charAt(0)=='/' && path.charAt(2)==':' ) {
            // Windows drive letter (e.g. /C:). Trim the leading '/'.
            path = path.substring(1);
        }
        File file = new File(path);
        do {
            path = file.getName();
            file = file.getParentFile();
        } while (file!=null && !path.equals("org"));
        if (VERBOSE) {
            System.out.print("Path in file system: ");
            System.out.println(file);
        }
        return file;
    }
    
    /**
     * Scan the directory and all subdirectory for classes implementing {@link CodeList}.
     */
    private void scan(final File directory, final File base) {
        if (directory == null || !directory.exists() || !directory.isDirectory()) {
            Logger.getLogger("org.geotools").warning("No directory to scan:"+directory);
            return; // Just a warning; do not fails.
        }
        final StringBuffer buffer = new StringBuffer();
        final File[] files = directory.listFiles(this);
        for (int i=0; i<files.length; i++) {
            File file = files[i];
            if (file.isDirectory()) {
                scan(file, base);
                continue;
            }
            buffer.setLength(0);
            String name = file.getName();
            final int ext = name.lastIndexOf('.');
            if (ext >= 0) {
                name = name.substring(0, ext);
            }
            buffer.append(name);
            while ((file=file.getParentFile()) != null) {
                if (file.equals(base)) {
                    break;
                }
                buffer.insert(0, '.');
                buffer.insert(0, file.getName());
            }
            name = buffer.toString();
            try {
                process(Class.forName(name));
            } catch (ClassNotFoundException exception) {
                fail("Class not found: "+name);
            }
        }
    }
    
    /**
     * Process a class. Only code list will be procceded.
     */
    private void process(final Class classe) {
        if (!CodeList.class.isAssignableFrom(classe)) {
            return;
        }
        if (CodeList.class.equals(classe)) {
            return;
        }
        final String name = classe.getName();
        final CodeList[] codes;
        if (VERBOSE) {
            System.out.print("Testing ");
            System.out.println(name);
        }
        try {
            codes = (CodeList[]) classe.getMethod("values", (Class[])null).invoke(null,(Object[])null);
        } catch (NoSuchMethodException exception) {
            fail("No values() method in "+name);
            return;
        } catch (IllegalAccessException exception) {
            fail("values() method is not public in "+name);
            return;
        } catch (InvocationTargetException exception) {
            fail("values() method failed in "+name);
            return;
        } catch (ClassCastException exception) {
            fail("values() method returned wrong type in "+name);
            return;
        }
        assertNotNull("values() returned null", codes);
        for (int i=0; i<codes.length; i++) {
            final CodeList code = codes[i];
            assertNotNull("Null element", code);
            assertEquals("Not equals to itself", code, code);
            assertEquals("Wrong index", i, code.ordinal());
            final CodeList field;
            try {
                field = (CodeList) classe.getField(code.name()).get(null);
            } catch (NoSuchFieldException exception) {
                if (toIgnore.contains(code)) {
                    continue;
                }
                fail("No field "+code+" in "+name);
                return;
            } catch (IllegalAccessException exception) {
                fail("Field "+code+" is not public in "+name);
                return;
            } catch (ClassCastException exception) {
                fail("Field "+code+" has a wrong type in "+name);
                return;
            }
            assertSame("Wrong name", code, field);
            if (toIgnore.contains(code)) {
                fail("Code "+code+" was not expected in a field");
            }
        }
    }
}
