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

            String answer = this.input.ask("Введите пункт меню : ");
            if (answer.chars().allMatch(Character::isDigit)) {
                switch (Integer.parseInt(answer)) {

                    case ADD:
                        this.createItem();
                        break;
                    case SHOW:
                        this.showAlltems();
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
                        System.out.println("Указанный пункт отсутствует в меню! Пожалуйста выберите существующий вариант!" + "\n");
                        break;
                }
            } else {
                System.out.println("Можно ввести пункт меню только в числовом формате!" + "\n");
            }
        }
    }

    /**
     * Метод, который добавляет заявку в список.
     */
    private void createItem() {
        System.out.println("------------ Добавление новой заявки --------------" + "\n");
        String name = this.input.ask("Введите имя заявки :");
        String desc = this.input.ask("Введите описание заявки :");
        Item item = new Item(name, desc);
        this.tracker.add(item);
        System.out.println("\n" + "------------ Новая заявка с getId : " + item.getId() + " -----------");
    }

    /**
     * Метод, который отображает весь список заявок.
     */
    private void showAlltems() {
        Item[] items = this.tracker.getAll();
        System.out.println("------------ Отображение списка заявок --------------" + "\n");

        for (Item item : items) {
            if (item != null) {
                System.out.println("------------ Заявка с getId : " + item.getId() + " -----------");
                System.out.println("------------ Заявка с getName : " + item.getName() + " -----------");
                System.out.println("------------ Заявка с getDescription : " + item.getDescription() + " -----------" + "\n");
            }
        }
    }

    /**
     * Метод, который редактирует заявку.
     */
    private void editItem() {
        System.out.println("------------ Редактирование указанной заявки --------------" + "\n");
        String id = this.input.ask("Введите Id для поиска заявки из списка :");
        Item itemForEdit = this.tracker.findById(id);

        String name = this.input.ask("Введите имя заявки, которое хотите ей присвоить :");
        String desc = this.input.ask("Введите описание заявки, которое хотите ей присвоить :");

        itemForEdit.setName(name);
        itemForEdit.setDescription(desc);

        this.tracker.replace(id, itemForEdit);
        System.out.println("\n" + "------------ Редактирование указанной заявки прошло успешно --------------");
    }

    /**
     * Метод, который удаляет заявку.
     */
    private void deleteItem() {
        System.out.println("------------ Удаление заявки --------------" + "\n");
        String id = this.input.ask("Введите Id для удаления заявки из списка :");
        this.tracker.delete(id);

        System.out.println("\n" + "------------ Удаление указанной заявки прошло успешно --------------");
    }

    /**
     * Метод, который находит заявку по Id.
     */
    private void findItemById() {
        System.out.println("------------ Поиск заявки по Id --------------" + "\n");
        String id = this.input.ask("Введите Id заявки для поиска её из списка : ");
        Item item = this.tracker.findById(id);

        System.out.println("------------ Найденная заявка --------------" + "\n");
        System.out.println("------------ Заявка с getName : " + item.getName() + " -----------");
        System.out.println("------------ Заявка с getDescription : " + item.getDescription() + " -----------");
    }

    /**
     * Метод, который находит заявку по Имени.
     */
    private void findItemByName() {
        System.out.println("------------ Поиск заявки по Name --------------" + "\n");
        String name = this.input.ask("Введите Name заявки для поиска её из списка : ");
        Item item = this.tracker.findByName(name);

        System.out.println("------------ Найденная заявка --------------" + "\n");
        System.out.println("------------ Заявка с getId : " + item.getId() + " -----------");
        System.out.println("------------ Заявка с getDescription : " + item.getDescription() + " -----------");
    }

    /**
     * Метод, который отображает меню.
     */
    private void showMenu() {
        System.out.println("Меню.");
        System.out.println("0. Add new Item\n"
                + "1. Show all items\n"
                + "2. Edit item\n"
                + "3. Delete item\n"
                + "4. Find item by Id\n"
                + "5. Find items by name\n"
                + "6. Exit Program");
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