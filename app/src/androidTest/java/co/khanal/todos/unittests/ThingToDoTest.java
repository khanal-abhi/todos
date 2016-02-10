package co.khanal.todos.unittests;

import junit.framework.TestCase;

import co.khanal.todos.ThingToDo;

/**
 * Created by abhi on 2/10/16.
 */
public class ThingToDoTest extends TestCase {
    ThingToDo thingToDo = null;

    public void setUp() throws Exception {
        thingToDo = new ThingToDo("Title");

    }

    public void tearDown() throws Exception {
        thingToDo = null;
    }

    public void testHasATitleOnlyConstructor(){
        assertEquals(0, thingToDo.getId());
    }

    public void testCanComplete(){
        assertFalse(thingToDo.isCompleted());
        thingToDo.setCompleted(true);
        assertTrue(thingToDo.isCompleted());
    }

    public void testToStringIsVerbose(){
        String toString = thingToDo.toString();
        assertTrue(toString.contains("completed:"));
        assertTrue(toString.contains("_id:"));
        assertTrue(toString.contains("title:"));
    }
}