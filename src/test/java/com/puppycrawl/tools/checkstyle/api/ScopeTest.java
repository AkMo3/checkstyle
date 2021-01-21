////////////////////////////////////////////////////////////////////////////////
// checkstyle: Checks Java source code for adherence to a set of rules.
// Copyright (C) 2001-2021 the original author or authors.
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public
// License along with this library; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
////////////////////////////////////////////////////////////////////////////////

package com.puppycrawl.tools.checkstyle.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.DefaultLocale;

/**
 * Test cases for {@link Scope} enumeration.
 */
public class ScopeTest {

    /* Additional test for jacoco, since valueOf()
     * is generated by javac and jacoco reports that
     * valueOf() is uncovered.
     */
    @Test
    public void testScopeValueOf() {
        final Scope scope = Scope.valueOf("PRIVATE");
        assertEquals(Scope.PRIVATE, scope, "Invalid scope");
    }

    @Test
    public void testMisc() {
        final Scope scope = Scope.getInstance("public");
        assertNotNull(scope, "Scope must not be null");
        assertEquals("public", scope.toString(), "Invalid scope toString");
        assertEquals("public", scope.getName(), "Invalid scope name");

        try {
            Scope.getInstance("unknown");
            fail("exception expected");
        }
        catch (IllegalArgumentException ex) {
            assertEquals(
                    "No enum constant com.puppycrawl.tools.checkstyle.api.Scope.UNKNOWN",
                    ex.getMessage(), "Invalid error message");
        }
    }

    @Test
    public void testMixedCaseSpaces() {
        assertEquals(Scope.NOTHING, Scope.getInstance("NothinG "), "Invalid scope");
        assertEquals(Scope.PUBLIC, Scope.getInstance(" PuBlic"), "Invalid scope");
        assertEquals(Scope.PROTECTED, Scope.getInstance(" ProteCted"), "Invalid scope");
        assertEquals(Scope.PACKAGE, Scope.getInstance("    PackAge "), "Invalid scope");
        assertEquals(Scope.PRIVATE, Scope.getInstance("privaTe   "), "Invalid scope");
        assertEquals(Scope.ANONINNER, Scope.getInstance("AnonInner"), "Invalid scope");
    }

    @DefaultLocale(language = "tr", country = "TR")
    @Test
    public void testMixedCaseSpacesWithDifferentLocale() {
        assertEquals(Scope.NOTHING, Scope.getInstance("NothinG "), "Invalid scope");
        assertEquals(Scope.PUBLIC, Scope.getInstance(" PuBlic"), "Invalid scope");
        assertEquals(Scope.PROTECTED, Scope.getInstance(" ProteCted"), "Invalid scope");
        assertEquals(Scope.PACKAGE, Scope.getInstance("    PackAge "), "Invalid scope");
        assertEquals(Scope.PRIVATE, Scope.getInstance("privaTe   "), "Invalid scope");
        assertEquals(Scope.ANONINNER, Scope.getInstance("AnonInner"), "Invalid scope");
    }

    @Test
    public void testIsInAnonInner() {
        assertTrue(Scope.NOTHING.isIn(Scope.ANONINNER), "Invalid subscope");
        assertTrue(Scope.PUBLIC.isIn(Scope.ANONINNER), "Invalid subscope");
        assertTrue(Scope.PROTECTED.isIn(Scope.ANONINNER), "Invalid subscope");
        assertTrue(Scope.PACKAGE.isIn(Scope.ANONINNER), "Invalid subscope");
        assertTrue(Scope.PRIVATE.isIn(Scope.ANONINNER), "Invalid subscope");
        assertTrue(Scope.ANONINNER.isIn(Scope.ANONINNER), "Invalid subscope");
    }

    @Test
    public void testIsInPrivate() {
        assertTrue(Scope.NOTHING.isIn(Scope.PRIVATE), "Invalid subscope");
        assertTrue(Scope.PUBLIC.isIn(Scope.PRIVATE), "Invalid subscope");
        assertTrue(Scope.PROTECTED.isIn(Scope.PRIVATE), "Invalid subscope");
        assertTrue(Scope.PACKAGE.isIn(Scope.PRIVATE), "Invalid subscope");
        assertTrue(Scope.PRIVATE.isIn(Scope.PRIVATE), "Invalid subscope");
        assertFalse(Scope.ANONINNER.isIn(Scope.PRIVATE), "Invalid subscope");
    }

    @Test
    public void testIsInPackage() {
        assertTrue(Scope.NOTHING.isIn(Scope.PACKAGE), "Invalid subscope");
        assertTrue(Scope.PUBLIC.isIn(Scope.PACKAGE), "Invalid subscope");
        assertTrue(Scope.PROTECTED.isIn(Scope.PACKAGE), "Invalid subscope");
        assertTrue(Scope.PACKAGE.isIn(Scope.PACKAGE), "Invalid subscope");
        assertFalse(Scope.PRIVATE.isIn(Scope.PACKAGE), "Invalid subscope");
        assertFalse(Scope.ANONINNER.isIn(Scope.PACKAGE), "Invalid subscope");
    }

    @Test
    public void testIsInProtected() {
        assertTrue(Scope.NOTHING.isIn(Scope.PROTECTED), "Invalid subscope");
        assertTrue(Scope.PUBLIC.isIn(Scope.PROTECTED), "Invalid subscope");
        assertTrue(Scope.PROTECTED.isIn(Scope.PROTECTED), "Invalid subscope");
        assertFalse(Scope.PACKAGE.isIn(Scope.PROTECTED), "Invalid subscope");
        assertFalse(Scope.PRIVATE.isIn(Scope.PROTECTED), "Invalid subscope");
        assertFalse(Scope.ANONINNER.isIn(Scope.PROTECTED), "Invalid subscope");
    }

    @Test
    public void testIsInPublic() {
        assertTrue(Scope.NOTHING.isIn(Scope.PUBLIC), "Invalid subscope");
        assertTrue(Scope.PUBLIC.isIn(Scope.PUBLIC), "Invalid subscope");
        assertFalse(Scope.PROTECTED.isIn(Scope.PUBLIC), "Invalid subscope");
        assertFalse(Scope.PACKAGE.isIn(Scope.PUBLIC), "Invalid subscope");
        assertFalse(Scope.PRIVATE.isIn(Scope.PUBLIC), "Invalid subscope");
        assertFalse(Scope.ANONINNER.isIn(Scope.PUBLIC), "Invalid subscope");
    }

    @Test
    public void testIsInNothing() {
        assertTrue(Scope.NOTHING.isIn(Scope.NOTHING), "Invalid subscope");
        assertFalse(Scope.PUBLIC.isIn(Scope.NOTHING), "Invalid subscope");
        assertFalse(Scope.PROTECTED.isIn(Scope.NOTHING), "Invalid subscope");
        assertFalse(Scope.PACKAGE.isIn(Scope.NOTHING), "Invalid subscope");
        assertFalse(Scope.PRIVATE.isIn(Scope.NOTHING), "Invalid subscope");
        assertFalse(Scope.ANONINNER.isIn(Scope.NOTHING), "Invalid subscope");
    }

}
