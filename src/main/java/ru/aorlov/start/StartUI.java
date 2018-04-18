package ru.aorlov.start;

import ru.aorlov.models.*;

public class StartUI {

    private static final int ADD = 0;
    private static final int SHOW = 1;
    private static final int EDIT = 2;
    private static final int DELETE = 3;
    private static final int FINDBYID = 4;
    private static final int FINDBYNAME = 5;
    private static final int EXIT = 6;
    private final Input input;
    private final Tracker tracker;

    /**
     * Конструтор инициализирующий поля.
     *
     * @param input   ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Метод, который инициализирует выбранный пункт меню.
     */
    public void init() {
        boolean exit = false;
        while (!exit) {
            this.showMenu();

            String answer = this.input.ask("Enter menu item : ");
            if (answer.chars().allMatch(Character::isDigit)) {
                switch (Integer.parseInt(answer)) {

                    case ADD:
                        this.createItem();
                        break;
                    case SHOW:
                        this.showAllItems();
                        break;
                    case EDIT:
                        this.editItem();
                        break;
                    case DELETE:
                        this.deleteItem();
                        break;
                    case FINDBYID:
                        this.findItemById();
                        break;
                    case FINDBYNAME:
                        this.findItemByName();
                        break;
                    case EXIT:
                        exit = true;
                        break;
                    default:
                        System.out.println("This item does not exist in list! Please choose existing value!");
                        break;
                }
            } else {
                System.out.println("Can enter a menu item in numeric format only!" + "\n");
            }
        }
    }

    /**
     * Метод, который добавляет заявку в список.
     */
    private void createItem() {
        System.out.print("------------ Add new item --------------" + "\n");
        String name = this.input.ask("Enter name of item :");
        String desc = this.input.ask("Enter description of item :");
        Item item = new Item(name, desc);
        this.tracker.add(item);
        System.out.print("\n" + "------------ New item with getId : " + item.getId() + " -----------" + "\n");
    }

    /**
     * Метод, который отображает весь список заявок.
     */
    private void showAllItems() {
        Item[] items = this.tracker.getAll();
        System.out.print("------------ Show list of items --------------" + "\n");

        for (Item item : items) {
            if (item != null) {
                System.out.print("------------ Item with getId : " + item.getId() + " -----------" + "\n");
                System.out.print("------------ Item with getName : " + item.getName() + " -----------" + "\n");
                System.out.print("------------ Item with getDescription : " + item.getDescription() + " -----------" + "\n");
            }
        }
    }

    /**
     * Метод, который редактирует заявку.
     */
    private void editItem() {
        System.out.print("------------ Editing of specified item --------------" + "\n");
        String id = this.input.ask("Enter Id for search of item in list :");
        Item itemForEdit = this.tracker.findById(id);

        String name = this.input.ask("Enter name of item :");
        String desc = this.input.ask("Enter description of item :");

        itemForEdit.setName(name);
        itemForEdit.setDescription(desc);

        this.tracker.replace(id, itemForEdit);
        System.out.print("\n" + "------------ Editing was successful --------------");
    }

    /**
     * Метод, который удаляет заявку.
     */
    private void deleteItem() {
        System.out.println("------------ Delete of item --------------" + "\n");
        String id = this.input.ask("Enter Id for delete of item in list :");
        this.tracker.delete(id);

        System.out.println("\n" + "------------ Deleting was successful! --------------");
    }

    /**
     * Метод, который находит заявку по Id.
     */
    private void findItemById() {
        System.out.print("------------ Searching of item by Id --------------" + "\n");
        String id = this.input.ask("Enter Id for search of item in list : ");
        Item item = this.tracker.findById(id);

        System.out.print("------------ Found item --------------" + "\n");
        System.out.print("------------ Item with getName : " + item.getName() + " -----------" + "\n");
        System.out.print("------------ Item with getDescription : " + item.getDescription() + " -----------" + "\n");
    }

    /**
     * Метод, который находит заявку по Имени.
     */
    private void findItemByName() {
        System.out.print("------------ Searching of item by Name --------------" + "\n");
        String name = this.input.ask("Enter Name for search of item in list : ");
        Item item = this.tracker.findByName(name);

        System.out.print("------------ Found item --------------" + "\n");
        System.out.print("------------ Item with getId : " + item.getId() + " -----------" + "\n");
        System.out.print("------------ Item with getDescription : " + item.getDescription() + " -----------" + "\n");
    }

    /**
     * Метод, который отображает меню.
     */
    private void showMenu() {
        System.out.println("Menu.");
        System.out.println("0. Add new Item\n"
                + "1. Show all items\n"
                + "2. Edit item\n"
                + "3. Delete item\n"
                + "4. Find item by Id\n"
                + "5. Find items by name\n"
                + "6. Exit Program\n");
    }

    /**
     * Запуск программы.
     *
     * @param args
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}