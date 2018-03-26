package ru.aorlov.start;

import ru.aorlov.models.*;

import java.util.Random;

/**
 * @version $Id$
 * @since 0.1
 */
public class Tracker {
    /**
     * Массив для хранения заявок.
     */
    private Item[] items = new Item[100];

    /**
     * Указатель ячейки для новой заявки.
     */
    private int position = 0;
    private static final Random RN = new Random();

    /**
     * Метод реализаущий добавление заявки в хранилище
     *
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(this.generetedId());
        this.items[position++] = item;

        return item;
    }

    /**
     * Метод, реализующий удаление заявки по указанному id.
     *
     * @param id - id заявки, которую будут удалять.
     */
    public void delete(String id) {
        int index;
        for (index = 0; index < items.length; index++) {
            if (items[index].getId().equals(id)) {
                break;
            }
        }
        Item[] copy = new Item[items.length - 1];
        System.arraycopy(items, 0, copy, 0, index);
        System.arraycopy(items, index + 1, copy, index, items.length - index - 1);

        items = copy;
    }

    /**
     * Метод, реализующий замену заявки по id другой указанной заявкой.
     *
     * @param id - id заявки, которую будут заменять.
     * @param item - заявка, которой будет заменена предыдущая.
     */
    public void replace(String id, Item item) {
        for (int index = 0; index != this.position; index++) {
            if (this.items[index].getId().equals(id) && this.items[index].getId() != null) {
                this.items[index] = item;
            }
        }
    }

    /**
     * Метод, реализующий поиск заявки по id.
     *
     * @param id
     * @return заявка.
     */
    public Item findById(String id) {
        Item result = null;
        for (Item item : items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }

    /**
     * Метод, реализующий поиск заявки по имени.
     *
     * @param key имя.
     * @return заявка.
     */
    public Item findByName(String key) {
        Item result = null;
        for (Item item : items) {
            if (item != null && item.getName().equals(key)) {
                result = item;
                break;
            }
        }
        return result;
    }

    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     *
     * @return Уникальный ключ.
     */
    private String generetedId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }

    /**
     * Метод, реализующий получение списка всех существующих заявок.
     *
     * @return список заявок.
     */
    public Item[] getAll() {
        Item[] result = new Item[position];
        for (int index = 0; index != this.position; index++) {
            result[index] = this.items[index];
        }
        return result;
    }
}