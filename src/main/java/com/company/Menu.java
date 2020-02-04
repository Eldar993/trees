package com.company;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Menu {
    public static class MenuItem  {

        private String title;
        private String key;
        public MenuItem(String title, String key) {
            this.title = title;
            this.key = key;
        }

    }

    private Scanner scanner = new Scanner(System.in);

    private static final int MENU_PREFIX_LENGTH = 5;



    List<MenuItem> items = new ArrayList<>();
    //position
    int left;
    int top;

    int activeIndex;
    String title;

    public Menu() {
        initializeItems();
        activeIndex = 0;
    }

    private void initializeItems() {
        items.add(new MenuItem("Insert node          ", "1"));
        items.add(new MenuItem("Insert random node   ", "2"));
        items.add(new MenuItem("Delete node          ", "3"));
        items.add(new MenuItem("Clear tree           ", "4"));
        items.add(new MenuItem("Contain node         ", "5"));
        items.add(new MenuItem("Tree height          ", "6"));
        items.add(new MenuItem("Tree size            ", "7"));
        items.add(new MenuItem("Print rb-tree        ", "8"));
        items.add(new MenuItem("Print tree           ", "9"));
        items.add(new MenuItem("Demo case#1          ", "f"));
        items.add(new MenuItem("Demo case#2          ", "s"));
        items.add(new MenuItem("Exit                 ", "e"));
    }
    
    public void setItems(List<MenuItem> items) {
        this.items = items;
    }

    //return max length of menu items plus prefix
    private int maxMenuItemLength(List<MenuItem> list) {
        int res = -1;
        for (int i = 0; i < list.size(); i++) {
            res = Math.max(res, list.get(i).title.length());
        }
        return res + MENU_PREFIX_LENGTH;
    }

    public void render() {
        for (MenuItem item : items) {
            System.out.println(item.key + " - " + item.title);
        }
        System.out.println(">>");
    }

    public int choose() {

        int i = 0;
        activeIndex = 0;
        do {
            render();
            String key = scanner.nextLine();

            for (i = 0; i < items.size(); i++) {
                if (items.get(i).key.equals(key)) {
                    return i;
                }
            }
        } while (true);                //while user choosed the menu item or escaped
    }

    public boolean isLastItem(int index) {
        return index == items.size() - 1;
    }
}

