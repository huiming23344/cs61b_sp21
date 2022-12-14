package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import java.util.Deque;
import java.util.Iterator;

import static org.junit.Assert.*;


/** Performs some basic linked list tests. */
public class LinkedListDequeTest {

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {

        LinkedListDeque<String> lld1 = new LinkedListDeque<String>();

		assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
		lld1.addFirst("front");

		// The && operator is the same as "and" in Python.
		// It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());

		lld1.addLast("middle");
		assertEquals(2, lld1.size());

		lld1.addLast("back");
		assertEquals(3, lld1.size());

        lld1.addFirst("youCannotPrint");

		System.out.println("Printing out deque: ");
		lld1.printDeque();
    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
		// should be empty
		assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());

		lld1.addFirst(10);
		// should not be empty
		assertFalse("lld1 should contain 1 item", lld1.isEmpty());

		lld1.removeFirst();
		// should be empty
		assertTrue("lld1 should be empty after removal", lld1.isEmpty());
    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        lld1.addFirst(3);

        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();

        int size = lld1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
    }

    @Test
    /* Check if you can create LinkedListDeques with different parameterized types*/
    public void multipleParamTest() {

        LinkedListDeque<String>  lld1 = new LinkedListDeque<String>();
        LinkedListDeque<Double>  lld2 = new LinkedListDeque<Double>();
        LinkedListDeque<Boolean> lld3 = new LinkedListDeque<Boolean>();

        lld1.addFirst("string");
        lld2.addFirst(3.14159);
        lld3.addFirst(true);

        String s = (String) lld1.removeFirst();
        double d = (double) lld2.removeFirst();
        boolean b = (boolean) lld3.removeFirst();
    }

    @Test
    /* check if null is return when removing from an empty LinkedListDeque. */
    public void emptyNullReturnTest() {

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, lld1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, lld1.removeLast());

    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }

        for (int i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, lld1.removeFirst());
        }

        for (int  i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, lld1.removeLast());
        }

    }

    @Test
    /**
     *  checkout whether iterator worked well
     */
    public void iteratorTest() {
        // Test by print out
        LinkedListDeque<Integer> test = new LinkedListDeque<>();
        for (int i = 0; i < 8; i++) {
            test.addFirst(i);
        }
        for (int j = 0; j > -8; j--) {
            test.addLast(j);
        }
        Iterator<Integer> test_iter = test.Iterator();
        while (test_iter.hasNext()) {
            System.out.println("" + test_iter.next());
        }
    }

    @Test
    /**
     * add some items and tset .get
     */
    public void getTest() {
        LinkedListDeque<Integer> test = new LinkedListDeque<>();
        for (int i = 0; i < 5; i++) {
            test.addFirst(i);
        }
        int toBeTest = test.get(0);
        assertEquals(4, toBeTest);
        toBeTest = test.get(1);
        assertEquals(3, toBeTest);
        toBeTest = test.get(4);
        assertEquals(0, toBeTest);

        // test the func .getRecursive()
        toBeTest = test.getRecursive(0);
        assertEquals(4, toBeTest);
        toBeTest = test.getRecursive(1);
        assertEquals(3, toBeTest);
        toBeTest = test.getRecursive(4);
        assertEquals(0, toBeTest);
    }

    @Test
    /**
     * test .equals()
     */
    public void equalsTest() {
        LinkedListDeque<Integer> test1 = new LinkedListDeque<>();
        LinkedListDeque<Integer> test2 = new LinkedListDeque<>();
        for (int i = 0; i < 4; i++) {
            test1.addFirst(i);
            test2.addFirst(i);
        }
        assertTrue(test1.equals(test1));
        assertTrue(test1.equals(test2));
        assertTrue(test2.equals(test1));

        // add another int to test2 and should not be equal again
        test2.addFirst(3);
        assertFalse(test1.equals(test2));

        // add a different int to test1 and should not be equal
        test1.addFirst(1);
        assertFalse(test1.equals(test2));
    }

    @Test
    public void randomTest() {
        LinkedListDeque<Integer> test = new LinkedListDeque<>();
        Deque<Integer> solution = new java.util.ArrayDeque<>();
        assertTrue(test.isEmpty());
        assertTrue(solution.isEmpty());
        int times = 100000;
        for (int i = 0; i < times; i++) {
            System.out.println("test time:" + i);
            int operationCode = StdRandom.uniform(0, 5);
            System.out.println("opcode: " + operationCode);
            if (operationCode == 0 || test.size() == 0) {
                // test the add func
                int toBeAdd = StdRandom.uniform(0, 100);
                test.addFirst(toBeAdd);
                solution.addFirst(toBeAdd);
                assertEquals(test.size(), solution.size());
            }
            if (operationCode == 1) {
                int toBeAdd = StdRandom.uniform(0, 100);
                System.out.println("test.size: " + test.size());
                test.addLast(1);
                solution.addLast(1);
                assertEquals(test.size(), solution.size());
            }
            if (operationCode == 2) {
                // test the remove func
                int num_stu = (int) test.removeFirst();
                int num_sol = solution.removeFirst();
                assertEquals(num_sol, num_stu);
                assertEquals(test.size(), solution.size());
            }
            if (operationCode == 3) {
                assertEquals(test.size(), solution.size());
                int size = solution.size();
                int sol = solution.removeLast();
                int sul = (int) test.removeLast();

                assertEquals(test.size(), solution.size());
            }
            if (operationCode == 5) {
                // test get func
                int Index = StdRandom.uniform(0, test.size());
                int stu = test.get(Index);
                //int sol = solution.get(Index);
                //assertEquals(stu, sol);
                //assertEquals(test.size(), solution.size());
            }
        }
    }
}
