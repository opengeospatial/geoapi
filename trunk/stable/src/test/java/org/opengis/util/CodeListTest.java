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
package org.opengis.util;

// J2SE dependencies
import java.util.*;
import java.lang.reflect.*;

// OpenGIS dependencies
import org.opengis.util.CodeList;
import org.opengis.referencing.cs.AxisDirection;

// JUnit dependencies
import junit.framework.*;


/**
 * Tests all code list in all packages found.
 *
 * @version $Id$
 * @author Martin Desruisseaux
 */
public class CodeListTest extends TestCase {
    /**
     * List of enums to ignore during the test operation.
     * Must be static because JUnit seems to recreate this object
     * for each test.
     */
    private static final Set<CodeList> toIgnore = new HashSet<CodeList>();
    
    /**
     * Run the suite from the command line.
     */
    public static void main(String[] args) {
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
        final Set<Class> classes = ClassFinder.getClasses(CodeList.class);
        for (final Class classe : classes) {
            if (!CodeList.class.isAssignableFrom(classe)) {
                continue;
            }
            if (CodeList.class.equals(classe)) {
                continue;
            }
            final String name = classe.getName();
            final CodeList[] codes;
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
}
