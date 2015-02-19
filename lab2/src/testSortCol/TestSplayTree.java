package testSortCol;

import datastructures.SplayTree;

/**
 * Authors Joakim Berntsson & Tim Kerschbaumer
 * Date: 2015-02-19.
 * Group: 6
 * Project: lab 2.2
 * Purpose: Testing the capabilities of SplayTree
 */
public class TestSplayTree {

    public static void main(String[] args){
        SplayTree<Integer> tree = new SplayTree<>();
        tree.add(100);
        tree.add(50);
        tree.add(1000);
        tree.add(25);
        tree.add(75);
        tree.add(500);
        tree.add(10000);
        tree.add(0);
        tree.add(40);
        tree.add(60);
        tree.add(90);
        tree.add(250);
        tree.add(750);
        tree.add(5000);
        tree.add(250);
        /*SplayTree<Integer> tree = new SplayTree<>();
        tree.add(10);
        tree.add(100);
        tree.add(1000);
        tree.add(9);
        tree.add(99);
        tree.add(999);
        tree.add(1001);
        System.out.println("Before Splaying root\n" + tree.toString());
        /*tree.testZigZig();
        System.out.println("After Splaying root\n" + tree.toString());
	    System.out.println("");
	    System.out.println("Before Splaying root\n" + tree.toString());
	    tree.testZagZag();
	    System.out.println("After Splaying root\n" + tree.toString());
        tree.testZag();
        System.out.println("After Zagging root\n" + tree.toString());
        System.out.println("Get : " + tree.get(100));
        System.out.println("After Get root\n" + tree.toString());
        System.out.println("Get : " + tree.get(9));
        System.out.println("After Get root\n" + tree.toString());
        System.out.println("Get : " + tree.get(999));
        System.out.println("After Get root\n" + tree.toString());
        System.out.println();*/
    }

}
