package com.codeallday.arrays.strings;


import java.util.ArrayList;
import java.util.Iterator;

/**
 * Is Unique: Implement algorithm to determine if a string has all unique characters.
 * What if you cannot use additional structures?
 */
public class CheckPermutation {

    public static void printMe() {
        System.out.println("hello");
    }

    public static void testIterator() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        arrayList.add("4");
        arrayList.add("5");
        Iterator iterator = arrayList.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            System.out.println("here");
            count++;
        }

        System.out.println("Number of Elements in the list " + count);
    }

}
