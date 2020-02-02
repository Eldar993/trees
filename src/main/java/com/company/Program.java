package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

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
 //               insertRandom();
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
//                DemoFirst();
                scanner.nextLine();
            } else if (index == 10) {
//                DemoSecond();
                scanner.nextLine();
            } else if (menu.isLastItem(index)) {
                return;
            }
        } while (index != -1);

    }

    static void insert() {

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

    /*static void insertRandom() {
        int count;
        do {
            System.out.println("Count = ");
            count = scanner.nextInt();
        } while (count <= 0);


        List<MenuItem> items = new ArrayList<>();
        items.add(new MenuItem("random    ", "1"));
        items.add(new MenuItem("ascending ", '2'));
        items.add(new MenuItem("descending", '3'));

        Menu submenu = new Menu();
        submenu.setItems(items);
        int index;
        List<Integer> list = generateList(count, 100);
        do {
            index = submenu.choose();
            if (index == 0) {
                break;
            } else if (index == 1) {
                list.sort();
                break;
            } else if (index == 2) {
                list.sort((a, b) => -1 * a.CompareTo(b));
                break;
            }
        } while (true);

        int addedInTree = fill(list, tree);
        //int addedInRBTree = Fill(list, rbtree);
        System.out.println("Try to add " + list.size() + " element(s): ");
        System.out.println(String.join(", ", list));
        System.out.println(addedInTree + " node(s) was added in binary search tree");
        //System.out.println(addedInRBTree + " node(s) was added  in red black tree");
    }*/

    static void delete() {
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

    static void clear() {
        tree.clear();
        //rbtree.clear();
        System.out.println("Binary search and red black trees were cleared");
    }

    static void contain() {
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

    static void size() {
        System.out.println("Tree size:  " + tree.size());
        //System.out.println("RB-Tree size:  " + rbtree.Size());
    }

    static void height() {
        System.out.println("Tree height:  " + tree.height());
        //System.out.println("RB-Tree height:  " + rbtree.height());
        //System.out.println("RB-Tree black height:  " + ((RedBlackTree<Integer>)rbtree).BlackHeight());
    }

    /*static void Demo(List<Integer> list, boolean visibleElements) {
        System.out.println("Clear trees...");
        tree.clear();
        //rbtree.clear();

        System.out.println("Try to add " + list.size() + " element(s): ");
        if(visibleElements) {
            System.out.println(list.stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(", ")));
                    
        }

        int addedInTree = Fill(list, tree);
        //int addedInRBTree = Fill(list, rbtree);
        System.out.println(addedInTree + " node(s) was added in binary search tree");
        System.out.println(addedInRBTree + " node(s) was added  in red black tree");
        System.out.println("Deleting the same " + list.size() + " eletemt(-s)...");
        int noDelete = 0;
        int noDeleteRB = 0;

        for (Integer i : list) {
            if (!tree.delete(i)) {
                ++noDelete;
            }

            if (!rbtree.Delete(i)) {
                //++noDeleteRB;
            }
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

    static void DemoFirst() {
        System.out.println("           -= DEMO case#1 =-");
        List<Integer> list = new ArrayList<Integer>(
                new int[] { 206043, 423728, 390348, 175224, 588825, 477871, 567496, 905545, 262916, 247149 });
        Demo(list, true);
    }

    static void DemoSecond() {
        System.out.println("           -= DEMO case#2 =-");
        List<Integer> list = generateList(1000000, 1000000);

        Demo(list, false);
    }

    static List<Integer> generateList(int size, int maxValue) {
        Random random = new Random();
        List<Integer> list = new ArrayList<Integer>();
        System.out.println($"Generate {size} element(-s) in range=[1,{maxValue}]...");

        for (int i = 1; i <= size; i++) {
            list.add(random.next(1, maxValue));
        }
        return list;
    }

    static int fill<T>(List<T> list, Tree<T> tree) where T : Comparable<T> {
        int count = 0;
        for(T element : list) {
            if (tree.add(element)) {
                count++;
            }
        }
        return count;
    }*/
}
