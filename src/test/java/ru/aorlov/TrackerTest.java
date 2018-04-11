package ru.aorlov;

import org.junit.Assert;
import org.junit.Test;
import ru.aorlov.models.Item;
import ru.aorlov.start.Tracker;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Test.
 *
 * @author AndrewOrlov (stagereagle6@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class TrackerTest {
    /**
     * Test add.
     */
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription");
        tracker.add(item);

        assertThat(tracker.getAll()[0], is(item));
    }

    /**
     * Test replace.
     */
    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription");
        // Добавляем заявку в трекер. Теперь в объект проинициализирован id.
        tracker.add(previous);
        // Создаем новую заявку.
        Item next = new Item("test2", "testDescription2");
        // Проставляем старый id из previous, который был сгенерирован выше.
        next.setId(previous.getId());
        // Обновляем заявку в трекере.
        tracker.replace(previous.getId(), next);
        // Проверяем, что заявка с таким id имеет новые имя test2.
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }

    /**
     * Test delete.
     */
    @Test
    public void whenDeleteOfIdThenArrayNotContainsThisItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription");
        tracker.add(item);
        tracker.delete(item.getId());

        Assert.assertEquals(tracker.getAll()[0], null);
    }

    /**
     * Test findById.
     */
    @Test
    public void whenFindByIdThenReturnItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription");
        tracker.add(item);

        assertThat(tracker.findById(item.getId()).getName(), is("test1"));
    }

    /**
     * Test findByName.
     */
    @Test
    public void whenFindByNameThenReturnItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription");
        tracker.add(item);

        assertThat(tracker.findByName(item.getName()).getName(), is("test1"));
    }

    /**
     * Test getAll.
     */
    @Test
    public void whenWeAddItemsToArrayAndGetAllWeHaveSameArrays() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription");
        Item nextItem = new Item("test2", "testDescription2");
        //добавляем заявки в наш массив, проверку которого производим.
        tracker.add(item);
        tracker.add(nextItem);

        //создём копию массива с такими же заявками.
        Item[] items = new Item[2];
        items[0] = item;
        items[1] = nextItem;

        Assert.assertArrayEquals(items, tracker.getAll());
    }
}