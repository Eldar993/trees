package com.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

class BinaryTreeTest {
    BinaryTree<String> tree;

    @BeforeEach
    void setUp() {
        tree = new BinaryTree<>();
    }

    @Test
    void emptyTree() {
        Assertions.assertEquals(0, tree.size());
    }

    @Test
    void notEmpty() {
        tree.add("first");
        tree.add("second ");
        tree.add("third");

        Assertions.assertEquals(3, tree.size());
    }

    @Test
    void unique() {
        //given
        Set<String> values = new HashSet<>();
        values.add("5");
        values.add("7");
        values.add("3");

        for (String value : values) {
            //when
            boolean result = tree.add(value);

            //then
            Assertions.assertSame(true, result);
            Assertions.assertTrue( result);
        }
    }
    @Test
    void duplicate() {
        //given
        Set<String> values = new HashSet<>();
        values.add("5");
        values.add("7");
        values.add("3");
        for (String value : values) {
            tree.add(value);
        }


        for (String value : values) {
            //when
            boolean result = tree.add(value);
            int size = tree.size();

            //then
            Assertions.assertSame(false, result);
            Assertions.assertFalse( result);
            Assertions.assertSame(3, size);
        }
    }

    @Test
    void SizeAfterDelete() {
        //given
        Set<String> values = new HashSet<>();
        values.add("5");
        values.add("7");
        values.add("3");


            //when
            tree.delete("5");

            //then
            Assertions.assertEquals(2, values.size());
    }


}