package co.khanal.todos;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhi on 2/10/16.
 */
public class ThingsToDoDataSourceTest extends ApplicationTest {
    ThingsToDoDataSource thingsToDoDataSource;
    List<ThingToDo> thingsToDo;

    public void setUp() throws Exception {
        thingsToDoDataSource = new ThingsToDoDataSource(getContext());
        thingsToDo = new ArrayList<>();

        thingsToDo.add(new ThingToDo("Wake up"));
        thingsToDo.add(new ThingToDo("Use the bathroom"));
        thingsToDo.add(new ThingToDo("Brush the teeth"));
        thingsToDo.add(new ThingToDo("Take a shower"));
        thingsToDo.add(new ThingToDo("Have breakfast"));
        thingsToDo.add(new ThingToDo("Go to work"));
        thingsToDoDataSource.open();
        thingsToDoDataSource.deleteAllTodos();

    }

    public void tearDown() throws Exception {
        thingsToDoDataSource.close();
        thingsToDoDataSource = null;
        thingsToDo = null;
    }

    public void testOpen() throws Exception {
        thingsToDoDataSource.close();
    }

    public void testClose() throws Exception {
        thingsToDoDataSource.close();
    }

    public void testAddThingToDo() throws Exception {
        thingsToDoDataSource.addThingToDo(thingsToDo.get(0));
        assertEquals(1, thingsToDoDataSource.thingsToDoCount());

    }

    public void testDeleteThingToDo() throws Exception {
        thingsToDoDataSource.addThingToDo(thingsToDo.get(0));
        thingsToDoDataSource.deleteAllTodos();
        assertEquals(0, thingsToDoDataSource.thingsToDoCount());

    }

    public void testThingsToDoCount() throws Exception {
        thingsToDoDataSource.addThingToDo(thingsToDo.get(0));
        assertEquals(1, thingsToDoDataSource.thingsToDoCount());

    }

    public void testGetThingToDo() throws Exception {
        thingsToDoDataSource.addThingToDo(thingsToDo.get(0));
        ThingToDo td;
        td = thingsToDoDataSource.getAllThingsToDo().get(0);
        assertEquals(td.toString(), thingsToDoDataSource.getThingToDo(td.getId()).toString());
    }

    public void testGetAllThingsToDo() throws Exception {
        thingsToDoDataSource.addThingToDo(thingsToDo.get(0));
        thingsToDoDataSource.addThingToDo(thingsToDo.get(1));
        List<ThingToDo> lt = thingsToDoDataSource.getAllThingsToDo();
        assertEquals(2, lt.size());

    }

    public void testDeleteAllTodos() throws Exception {
        thingsToDoDataSource.addThingToDo(thingsToDo.get(0));
        thingsToDoDataSource.addThingToDo(thingsToDo.get(1));
        thingsToDoDataSource.deleteAllTodos();
        assertEquals(0, thingsToDoDataSource.thingsToDoCount());
    }

    public void testUpdateThingToDo() throws Exception {
        thingsToDoDataSource.addThingToDo(thingsToDo.get(1));
        ThingToDo td = thingsToDoDataSource.getAllThingsToDo().get(0);
        td.setTitle("Poop");
        thingsToDoDataSource.updateThingToDo(td);
        ThingToDo utd = thingsToDoDataSource.getThingToDo(td.getId());
        assertEquals("Poop", utd.getTitle());

    }
}