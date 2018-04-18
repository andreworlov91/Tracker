package ru.aorlov.start;

import org.hamcrest.core.IsNull;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.aorlov.models.Item;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringContains.containsString;

/**
 * Test.
 *
 * @author AndrewOrlov (stagereagle6@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class StartUITest {

    private final PrintStream stdout = System.out;

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void loadOutput() {
        System.out.println("execute before method");
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
        System.out.println("execute after method");
    }

    private void start(String[] array, Tracker tracker) {
        Input input = new StubInput(array);
        new StartUI(input, tracker).init();
    }


    /**
     * Test createItem
     */
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();
        start(new String[]{"0", "test name", "desc", "6"}, tracker);

        assertThat(tracker.getAll()[0].getName(), is("test name"));
    }

    /**
     * Test editItem
     */
    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item());

        start(new String[]{"2", item.getId(), "test name", "desc", "6"}, tracker);

        assertThat(tracker.findById(item.getId()).getName(), is("test name"));
    }

    /**
     * Test deleteItem
     */
    @Test
    public void whenDeleteThenTrackerHasNullValue() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item());
        start(new String[]{"3", item.getId(), "6"}, tracker);

        assertThat(tracker.getAll()[0], is((IsNull.nullValue())));
    }

    /**
     * Test showAllItems
     */
    @Test
    public void whenWeShowAllItemsThenTrackerHasIdNameDescriptionEachItem() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("item 1", "description 1"));
        start(new String[]{"1", "6"}, tracker);

        assertThat(
                new String(this.out.toByteArray()),
                containsString(
                        new StringBuilder()
                                .append("------------ Item with getId : " + item.getId() + " -----------" + "\n")
                                .append("------------ Item with getName : " + item.getName() + " -----------" + "\n")
                                .append("------------ Item with getDescription : " + item.getDescription() + " -----------" + "\n")
                                .toString()
                )
        );
    }

    /**
     * Test findItemById
     */
    @Test
    public void whenWeFindItemByIdThenTrackerHasNameDescriptionOfItem() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("item 1", "description 1"));
        start(new String[]{"4", item.getId(),"6"}, tracker);

        assertThat(
                new String(this.out.toByteArray()),
                containsString(
                        new StringBuilder()
                                .append("------------ Item with getName : " + item.getName() + " -----------" + "\n")
                                .append("------------ Item with getDescription : " + item.getDescription() + " -----------" + "\n")
                                .toString()
                )
        );
    }

    /**
     * Test findItemById
     */
    @Test
    public void whenWeFindItemByNameThenTrackerHasIdDescriptionOfItem() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("item 1", "description 1"));
        start(new String[]{"5", item.getName(),"6"}, tracker);

        assertThat(
                new String(this.out.toByteArray()),
                containsString(
                        new StringBuilder()
                                .append("------------ Item with getId : " + item.getId() + " -----------" + "\n")
                                .append("------------ Item with getDescription : " + item.getDescription() + " -----------" + "\n")
                                .toString()
                )
        );
    }
}
