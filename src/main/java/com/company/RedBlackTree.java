package com.company;

public abstract class RedBlackTree<T extends Comparable<T>> implements Tree<T> {
    private static final String BARE_PIX = "       ";
    private static final String NODE_PIX = "-------";

    enum Color {     //color of node
        RED, BLACK
    }

    class Node {
        private T key;
        private Node left;
        private Node right;
        private Node parent;
        private Color color = Color.RED;

        private Node(T key, Node parent) {
            this.key = key;
            this.parent = parent;
        }

        public String toString() {

            return key.toString();
        }
    }

    private int size;
    private Node root;

    private Node contains(T key, Node currentRoot) {
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

    public boolean contains(T key) {
        return contains(key, root) != null;
    }

    private int height(Node currentRoot) {
        if (currentRoot == null) {
            return 0;
        }
        return Math.max(height(currentRoot.left), height(currentRoot.right)) + 1;
    }

    //h(root) = Max(h(root.left), h(root.right)) + 1
    public int height() {
        return height(root);
    }


    private int BlackHeight(Node currentRoot) {
        if (currentRoot == null) {
            return 0;
        }
        return Math.max(BlackHeight(currentRoot.left), BlackHeight(currentRoot.right)) +
                (currentRoot.color == Color.BLACK ? 1 : 0);
    }

    public int BlackHeight() {
        if (root == null) {
            return 0;
        }
        return BlackHeight(root) - 1;
    }

    public int size() {
        return size;
    }

    private void print(Node currentRoot, String pix) {
        if (currentRoot == null) {
            return;
        }
        String nextPix = pix + BARE_PIX;
        print(currentRoot.right, nextPix);
        System.out.print(pix + NODE_PIX);
        if (currentRoot.color == Color.RED) {
            System.out.print("R:");
        } else {
            System.out.print("B:");
        }
        System.out.println(currentRoot);
        print(currentRoot.left, nextPix);
    }

    public void print() {
        print(root, "");
    }


    private void LeftRotate(Node X) {
        if (X.right == null) {
            return;
        }
        Node Y = X.right;
        X.right = Y.left;
        if (Y.left != null) {
            Y.left.parent = X;
        }
        Y.parent = X.parent;
        if (X.parent == null) {
            root = Y;
        } else if (X == X.parent.left) {
            X.parent.left = Y;
        } else {
            X.parent.right = Y;
        }
        Y.left = X;
        X.parent = Y;
    }

    private Node RightRotate(Node X) {
        if (X.left == null) {
            return X;
        }
        Node Y = X.left;
        X.left = Y.right;
        if (Y.right != null) {
            Y.right.parent = X;
        }
        Y.parent = X.parent;
        if (X.parent == null) {
            root = Y;
        } else if (X == X.parent.right) {
            X.parent.right = Y;
        } else {
            X.parent.left = Y;
        }
        Y.right = X;
        X.parent = Y;

        return X;
    }

    public boolean add(T key) {
        Node z = new Node(key, null);
        Node y = null;
        Node x = root;
        while (x != null) {
            y = x;

            if (z.key.equals(x.key)) {
                return false;
            } else if (z.key.compareTo(x.key) < 0) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        z.parent = y;
        if (y == null) {
            root = z;
        } else if (z.key.compareTo(y.key) < 0) {
            y.left = z;
        } else {
            y.right = z;
        }

        RBAddFixup(z);

        size++;
        return true;
    }


    private void RBAddFixup(Node z) {
        Node y;
        while (z != null &&
                z.parent != null &&
                z.parent.color == Color.RED) {

            if (z.parent.parent != null &&
                    z.parent == z.parent.parent.left) {
                y = z.parent.parent.right;

                if (y != null && y.color == Color.RED) {
                    z.parent.color = Color.BLACK;
                    y.color = Color.BLACK;
                    z.parent.parent.color = Color.RED;
                    z = z.parent.parent;
                } else {
                    if (z == z.parent.right) {
                        z = z.parent;
                        z = LeftRotate(z);
                    }
                    z.parent.color = Color.BLACK;
                    z.parent.parent.color = Color.RED;
                    RightRotate(z.parent.parent);
                }
            } else if (z.parent.parent != null &&
                    z.parent == z.parent.parent.right) {
                y = z.parent.parent.left;

                if (y != null && y.color == Color.RED) {
                    z.parent.color = Color.BLACK;
                    y.color = Color.BLACK;
                    z.parent.parent.color = Color.RED;
                    z = z.parent.parent;
                } else {
                    if (z == z.parent.left) {
                        z = z.parent;
                        RightRotate(z);
                    }
                    z.parent.color = Color.BLACK;
                    z.parent.parent.color = Color.RED;
                    LeftRotate(z.parent.parent);
                }

            }

        }
        root.color = Color.BLACK;

    }

    private Node TreeMinimum(Node x) {
        while (x.left != null) {
            x = x.left;
        }
        return x;
    }

    private void RBTransplant(Node u, Node v) {
        if (u.parent == null) {
            root = v;
        } else if (u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        if (v != null) {
            v.parent = u.parent;
        }
    }

    private void RBDelete( Node z) {
        Node y;
        Node x;
        y = z;
        Color y_original_color = y.color;
        if (z.left == null) {
            x = z.right;
            RBTransplant(z, z.right);
        } else if (z.right == null) {
            x = z.left;
            RBTransplant(z, z.left);
        } else {
            y = TreeMinimum(z.right);
            y_original_color = y.color;
            x = y.right;
            if (y.parent == z) {
                if (x != null) {
                    x.parent = y;
                }
            } else {
                RBTransplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            RBTransplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }
        if (y_original_color == Color.BLACK) {
            RBDeleteFixup( x);
        }
    }

    private void RBDeleteFixup( Node x) {
        Node w;
        while (x != null && x != root && x.color == Color.BLACK) {
            if (x == x.parent.right) {
                w = x.parent.right;
                if (w.color == Color.RED) {
                    w.color = Color.BLACK;
                    x.parent.color = Color.RED;
                    LeftRotate(x.parent);
                    w = x.parent.right;
                }
               if (w != null && ( w.left != null && w.left.color == Color.BLACK) &&
                       (w.right != null && w.right.color == Color.BLACK)){
                       w.color = Color.RED;
                       x = x.parent;
                }
                /*if (w?.left?.color == Color.BLACK &&
                        w?.right?.color == Color.BLACK) {
                    w.color = Color.RED;
                    x = x.parent;*/
                } else if (w != null && (w.right != null && w.right.color == Color.BLACK)) {
                      {
                        if (w.left != null) {
                            w.left.color = Color.BLACK;
                        }
                        w.color = Color.RED;
                        RightRotate(w);
                        w = x.parent.right;
                    }
                    w.color = x.parent.color;
                    x.parent.color = Color.BLACK;
                    if (w.right != null) {
                        w.right.color = Color.BLACK;
                    }
                    LeftRotate(x.parent);
                    x = root;
                }

            } else if (x != null && (x == x.parent.left)) {
                w = x.parent.left;
                if (w.color == Color.RED) {
                    w.color = Color.BLACK;
                    x.parent.color = Color.RED;
                    RightRotate(x.parent);
                    w = x.parent.left;
                }
            if (w != null && (w.right != null && w.right.color == Color.BLACK) &&
                    ( w.left != null && w.left.color == Color.BLACK) ) {
                w.color = Color.RED;
                x = x.parent;
                /*if (w?.right?.color == Color.BLACK &&
                        w?.left?.color == Color.BLACK) {
                    w.color = Color.RED;
                    x = x.parent;*/
                } else {
                    if (w != null && (w.left != null && w.left.color == Color.BLACK)) {
                        if (w.right != null) {
                            w.right.color = Color.BLACK;
                        }
                        w.color = Color.RED;
                        LeftRotate(w);
                        w = x.parent.left;
                    }
                    w.color = x.parent.color;
                    x.parent.color = Color.BLACK;
                    if (w.left != null) {
                        w.left.color = Color.BLACK;
                    }
                    RightRotate(x.parent);
                    x = root;
                }
            }
        }
        if (x != null) {
            x.color = Color.BLACK;
        }
    }

    public boolean delete(T key) {
        Node node = root;
        while (node != null && !node.key.equals(key)) {  //try to found node with key which we want to delete
            if (node.key.compareTo(key) > 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        if (node != null) {    //if not null the we found node with key which we want to delete
            RBDelete( node);
            size--;
            return true;
        }
        return false;
    }

    public void clear() {
        root = null;
        size = 0;
    }
}
