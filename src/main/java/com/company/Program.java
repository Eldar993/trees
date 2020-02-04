package com.company;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class Program {
    //    static ITree<Integer> rbtree = new RedBlackTree<>();
    static Tree<Integer> tree = new BinaryTree<>();
    private static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        Menu menu = new Menu();

        int index = 0;
        do {
            index = menu.choose();
            if (index == 0) {
                insert();
            } else if (index == 1) {
                insertRandom();
            } else if (index == 2) {
                delete();
            } else if (index == 3) {
                clear();
            } else if (index == 4) {
                contain();
            } else if (index == 5) {
                height();
            } else if (index == 6) {
                size();
            } else if (index == 7) {
//                rbtree.Print();
                scanner.nextLine();
            } else if (index == 8) {
                System.out.println("Binary tree:");
                tree.print();
                System.out.println("Press ENTER to continue...");
                scanner.nextLine();
            } else if (index == 9) {
                DemoFirst();
                scanner.nextLine();
            } else if (index == 10) {
                DemoSecond();
                scanner.nextLine();
            } else if (menu.isLastItem(index)) {
                return;
            }
        } while (index != -1);

    }

    private static void insert() {

        System.out.print("key = ");
        int i = scanner.nextInt();
        scanner.nextLine();

        if (tree.add(i)) {
            System.out.println("Key " + i + " was added in tree");
        } else {
            System.out.println("Key " + i + " was NOT added in tree");
        }
//        if (rbtree.add(i)) {
//            System.out.println("Key " + i + " was added in rb-tree");
//        } else {
//            System.out.println("Key " + i + " was NOT added in rb-tree");
//        }
    }

    private static void insertRandom() {
        int count;
        do {
            System.out.println("Count = ");
            count = scanner.nextInt();
        } while (count <= 0);


        List<Menu.MenuItem> items = new ArrayList<>();
        items.add(new Menu.MenuItem("random    ", "1"));
        items.add(new Menu.MenuItem("ascending ", "2"));
        items.add(new Menu.MenuItem("descending", "3"));

        Menu submenu = new Menu();
        submenu.setItems(items);
        int index;
        List<Integer> list = generateList(count, 100);
        do {
            index = submenu.choose();
            if (index == 0) {
                break;
            } else if (index == 1) {
                Collections.sort(list);
                break;
            } else if (index == 2) {
                list.sort(Comparator.reverseOrder());
                break;
            }
        } while (true);

        int addedInTree = fill(list, tree);
        //int addedInRBTree = Fill(list, rbtree);
        System.out.println("Try to add " + list.size() + " element(s): ");
        System.out.println(list);
        System.out.println(addedInTree + " node(s) was added in binary search tree");
        //System.out.println(addedInRBTree + " node(s) was added  in red black tree");
    }

    private static void delete() {
        System.out.println("Deleted node = ");
        int i = scanner.nextInt();
        if (tree.delete(i)) {
            System.out.println("Key " + i + " was deleted in tree");
        } else {
            System.out.println("Key " + i + " was NOT deleted in tree");
        }
       /* if (rbtree.Delete(i)) {
            System.out.println("Key " + i + " was deleted in rb-tree");
        } else {
            System.out.println("Key " + i + " was NOT deleted in rb-tree");
        }*/
    }

    private static void clear() {
        tree.clear();
        //rbtree.clear();
        System.out.println("Binary search and red black trees were cleared");
    }

    private static void contain() {
        System.out.println("Contains node = ");
        int i = scanner.nextInt();
        if (tree.contains(i)) {
            System.out.println("Key " + i + " was found in tree");
        } else {
            System.out.println("Key " + i + " was NOT found in tree");
        }
       /* if (rbtree.contains(i)) {
            System.out.println("Key " + i + " was found in rb-tree");
        } else {
            System.out.println("Key " + i + " was NOT found in rb-tree");
        }*/
    }

    private static void size() {
        System.out.println("Tree size:  " + tree.size());
        //System.out.println("RB-Tree size:  " + rbtree.Size());
    }

    private static void height() {
        System.out.println("Tree height:  " + tree.height());
        //System.out.println("RB-Tree height:  " + rbtree.height());
        //System.out.println("RB-Tree black height:  " + ((RedBlackTree<Integer>)rbtree).BlackHeight());
    }

    static void Demo(List<Integer> list, boolean visibleElements) {
        System.out.println("Clear trees...");
        tree.clear();
        //rbtree.clear();

        System.out.println("Try to add " + list.size() + " element(s): ");
        if(visibleElements) {
            System.out.println(list.stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(", ")));
                    
        }

        int addedInTree = fill(list, tree);
        //int addedInRBTree = Fill(list, rbtree);
        System.out.println(addedInTree + " node(s) was added in binary search tree");
        //System.out.println(addedInRBTree + " node(s) was added  in red black tree");
        System.out.println("Deleting the same " + list.size() + " eletemt(-s)...");
        int noDelete = 0;
        int noDeleteRB = 0;

        for (Integer i : list) {
            if (!tree.delete(i)) {
                ++noDelete;
            }

            //if (!rbtree.Delete(i)) {
                //++noDeleteRB;
            //}
        }

        System.out.println(noDelete + " value(-s) could not be deleted in tree");
        //System.out.println(noDeleteRB + " value(-s) could not be deleted in rb-tree");

        System.out.println("Explanation: list.size() == addedInTree + noDelete, because list can contain duplicates");
        System.out.println("tree:    " + list.size() + " == " + addedInTree + " + " + noDelete + "   is "
                + (list.size() == addedInTree + noDelete));
       // System.out.println("rb-tree: " + list.size() + " == " + addedInRBTree + " + " + noDeleteRB + "   is "
                //+ (list.size() == addedInRBTree + noDeleteRB));

        System.out.println("\nPrint tree:");
        tree.print();
       // System.out.println("\nPrint rb-tree:");
      //  rbtree.Print();

        System.out.println("\ntree size    = {tree.Size()}");
       // System.out.println($"rb-tree size = {rbtree.Size()}");
        System.out.println("\n\nPress any key to continue...");
    }

    private static void DemoFirst() {
        System.out.println("           -= DEMO case#1 =-");
        List<Integer> list = new ArrayList<>(Arrays.asList(
                206043, 423728, 390348, 175224, 588825, 477871, 567496, 905545, 262916, 247149));
        Demo(list, true);
    }

    private static void DemoSecond() {
        System.out.println("           -= DEMO case#2 =-");
        List<Integer> list = generateList(1000000, 1000000);

        Demo(list, false);
    }

    private static List<Integer> generateList(int size, int maxValue) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        List<Integer> list = new ArrayList<>();
        System.out.printf("Generate %d element(-s) in range=[1,%d]...", size, maxValue);

        for (int i = 1; i <= size; i++) {
            list.add(random.nextInt(1, maxValue));
        }
        return list;
    }

    private static <T extends Comparable<T>> int fill(List<T> list, Tree<T> tree) {
        int count = 0;
        for (T element : list) {
            if (tree.add(element)) {
                count++;
            }
        }
        return count;
    }
}
