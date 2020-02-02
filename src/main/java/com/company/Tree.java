package com.company;

public interface Tree<T extends Comparable<T>> { // general interface for working with tree,type T must implement IComparable
    boolean add(T key);        // add node by key

    boolean contains(T key);   //contain node by key

    int height();           //height of tree

    int size();             //size of tree

    void print();           //print tree

    boolean delete(T key);     // delete node by key

    void clear();           // delete all nodes
}
