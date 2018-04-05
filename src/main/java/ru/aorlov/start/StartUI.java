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
     * ���������� ���������������� ����.
     *
     * @param input   ���� ������.
     * @param tracker ��������� ������.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * �����, ������� �������������� ��������� ����� ����.
     */
    public void init() {
        boolean exit = false;
        while (!exit) {
            this.showMenu();

            String answer = this.input.ask("������� ����� ���� : ");
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
                        System.out.println("��������� ����� ����������� � ����! ���������� �������� ������������ �������!" + "\n");
                        break;
                }
            } else {
                System.out.println("����� ������ ����� ���� ������ � �������� �������!" + "\n");
            }
        }
    }

    /**
     * �����, ������� ��������� ������ � ������.
     */
    private void createItem() {
        System.out.println("------------ ���������� ����� ������ --------------" + "\n");
        String name = this.input.ask("������� ��� ������ :");
        String desc = this.input.ask("������� �������� ������ :");
        Item item = new Item(name, desc);
        this.tracker.add(item);
        System.out.println("\n" + "------------ ����� ������ � getId : " + item.getId() + " -----------");
    }

    /**
     * �����, ������� ���������� ���� ������ ������.
     */
    private void showAlltems() {
        Item[] items = this.tracker.getAll();
        System.out.println("------------ ����������� ������ ������ --------------" + "\n");

        for (Item item : items) {
            if (item != null) {
                System.out.println("------------ ������ � getId : " + item.getId() + " -----------");
                System.out.println("------------ ������ � getName : " + item.getName() + " -----------");
                System.out.println("------------ ������ � getDescription : " + item.getDescription() + " -----------" + "\n");
            }
        }
    }

    /**
     * �����, ������� ����������� ������.
     */
    private void editItem() {
        System.out.println("------------ �������������� ��������� ������ --------------" + "\n");
        String id = this.input.ask("������� Id ��� ������ ������ �� ������ :");
        Item itemForEdit = this.tracker.findById(id);

        String name = this.input.ask("������� ��� ������, ������� ������ �� ��������� :");
        String desc = this.input.ask("������� �������� ������, ������� ������ �� ��������� :");

        itemForEdit.setName(name);
        itemForEdit.setDescription(desc);

        this.tracker.replace(id, itemForEdit);
        System.out.println("\n" + "------------ �������������� ��������� ������ ������ ������� --------------");
    }

    /**
     * �����, ������� ������� ������.
     */
    private void deleteItem() {
        System.out.println("------------ �������� ������ --------------" + "\n");
        String id = this.input.ask("������� Id ��� �������� ������ �� ������ :");
        this.tracker.delete(id);

        System.out.println("\n" + "------------ �������� ��������� ������ ������ ������� --------------");
    }

    /**
     * �����, ������� ������� ������ �� Id.
     */
    private void findItemById() {
        System.out.println("------------ ����� ������ �� Id --------------" + "\n");
        String id = this.input.ask("������� Id ������ ��� ������ � �� ������ : ");
        Item item = this.tracker.findById(id);

        System.out.println("------------ ��������� ������ --------------" + "\n");
        System.out.println("------------ ������ � getName : " + item.getName() + " -----------");
        System.out.println("------------ ������ � getDescription : " + item.getDescription() + " -----------");
    }

    /**
     * �����, ������� ������� ������ �� �����.
     */
    private void findItemByName() {
        System.out.println("------------ ����� ������ �� Name --------------" + "\n");
        String name = this.input.ask("������� Name ������ ��� ������ � �� ������ : ");
        Item item = this.tracker.findByName(name);

        System.out.println("------------ ��������� ������ --------------" + "\n");
        System.out.println("------------ ������ � getId : " + item.getId() + " -----------");
        System.out.println("------------ ������ � getDescription : " + item.getDescription() + " -----------");
    }

    /**
     * �����, ������� ���������� ����.
     */
    private void showMenu() {
        System.out.println("����.");
        System.out.println("0. Add new Item\n"
               + "1. Show all items\n"
               + "2. Edit item\n"
               + "3. Delete item\n"
               + "4. Find item by Id\n"
               + "5. Find items by name\n"
               + "6. Exit Program");
    }

    /**
     * ������ ���������.
     *
     * @param args
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}