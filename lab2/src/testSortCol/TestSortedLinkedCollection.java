package testSortCol;

import datastructures.SortedLinkedCollection;

import java.util.Iterator;

/**
 * Created by: Tim Kerschbaumer
 * Project: lab2
 * Date: 15-02-10
 * Time: 18:13
 */
public class TestSortedLinkedCollection {
	static SortedLinkedCollection<Integer> slc = new SortedLinkedCollection<Integer>();

	public static void main(String[] args) {
		slc.add(10);
		slc.add(-5);
		slc.add(4);
		slc.add(120);
		slc.add(30);

		Iterator<Integer> it = slc.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}

		System.out.println(slc.get(130) + "");

	}
}
