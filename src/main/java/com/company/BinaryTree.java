package com.company;


//Binary Search tree
class BinaryTree<T extends Comparable<T>> implements Tree<T> {
    private static final String BARE_PREFIX = "       ";
    private static final String NODE_PREFIX = "-------";

    class Node {               //class which encapsulates data of current node
        private T key;
        private Node left;
        private Node right;

        private Node(T key) {  //constructor of node
            this.key = key;
        }

        @Override
        public String toString() {
            return key.toString();
        }
    }

    private Node root;
    private int size;

    @Override
    public int size() {
        return size;
    }

    private Node add(T key, Node currentRoot) {  //suplemented recursive addittion
        if (currentRoot == null) {
            size++;
            return new Node(key);
        } else if (key.compareTo(currentRoot.key) < 0) {
            currentRoot.left = add(key, currentRoot.left);
        } else if (key.compareTo(currentRoot.key) > 0) {
            currentRoot.right = add(key, currentRoot.right);
        }

        return currentRoot;
    }

    @Deprecated
    private boolean oldAdd(T key, Node currentRoot) {  //suplemented recursive addition
        if (currentRoot == null) {
            currentRoot = new Node(key);
            size++;
            return true;
        } else if (key.compareTo(currentRoot.key) < 0) {
            return oldAdd(key, currentRoot.left);
        } else if (key.compareTo(currentRoot.key) > 0) {
            return oldAdd(key, currentRoot.right);
        } else {        //if (key == currentRoot.key)
            return false;
        }
    }

    @Override
    public boolean add(T key) {      // user addition
        int prevSize = size;
        root = add(key, root);
        return size == prevSize + 1;
    }

    private void print(Node currentRoot, String prefix) {    //suplemented recursive print
        if (currentRoot == null) {
            return;
        }
        String nextPrefix = prefix + BARE_PREFIX;
        print(currentRoot.right, nextPrefix);
        System.out.println(prefix + NODE_PREFIX + currentRoot.key);
        print(currentRoot.left, nextPrefix);
    }

    @Override
    public void print() {
        print(root, "");
    }

    private Node contains(T key, Node currentRoot) {   //suplemented recursive contain
        if (currentRoot == null) {
            return null;
        } else if (currentRoot.key.compareTo(key) == 0) {
            return currentRoot;
        } else if (currentRoot.key.compareTo(key) > 0) {
            return contains(key, currentRoot.left);
        } else {
            return contains(key, currentRoot.right);
        }
    }

    @Override
    public boolean contains(T key) {
        return contains(key, root) != null;
    }

    private int height(Node currentRoot) {    //suplemented recursive height
        if (currentRoot == null) {
            return 0;
        }
        return Math.max(height(currentRoot.left), height(currentRoot.right)) + 1;
    }

    //h(root) = Max(h(root.left), h(root.right)) + 1
    @Override
    public int height() {
        return height(root);
    }

    @Override
    public boolean delete(T key) {  //user deletion
        if (size == 0) {
            return false;
        }

        Node parent = null;
        Node z = root;
        while (z != null && !z.key.equals(key)) {
            if (z.key.compareTo(key) < 0) {
                parent = z;
                z = z.right;
            } else {
                parent = z;
                z = z.left;
            }
        }
        if (z == null) {
            return false;
        }
        if (size == 1) {
            root = null;
            size--;
            return true;
        }
        if (z.left == null && z.right == null) {
            if (parent.left == z) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        } else if (z.left == null) {
            if (parent == null) {
                root = z.right;
            } else if (parent.left == z) {
                parent.left = z.right;
            } else {
                parent.right = z.right;
            }
        } else if (z.right == null) {
            if (parent == null) {
                root = z.left;
            } else if (parent.left == z) {
                parent.left = z.left;
            } else {
                parent.right = z.left;
            }
        } else {
            Node temp = z.right;                                //right subtree root node

            if (temp.left == null) {            //if right subtree does not have left subree
                temp.left = z.left;             //then we need to link it to the left subtree of deleted node
                if (z == root) {
                    root = temp;
                } else {
                    if (parent.right == z) {
                        parent.right = temp;
                    } else {
                        parent.left = temp;
                    }
                }

            } else {
                while (temp.left.left != null) {         //find the leftmost node in right subtree
                    temp = temp.left;
                }
                Node D = temp.left;                      //we found the leftmost node in right subtree
                temp.left = temp.left.right;

                z.key = D.key;      //rewrite data in the deleted node
            }
        }
        size--;         //decrease the number of elements
        return true;
    }

    @Override
    public void clear() { //deletion of all elements
        root = null;
        size = 0;
    }
}


