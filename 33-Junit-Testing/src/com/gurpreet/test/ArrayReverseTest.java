package com.gurpreet.test;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

import com.gurpreet.model.ArrayReverse;

class ArrayReverseTest {

	ArrayReverse array = new ArrayReverse();

	@Test 
	void shouldHaveNormal() {
		int[] expected = {4, 3, 2, 1};
        int[] input = {1, 2, 3, 4};
		assertArrayEquals(expected , array.reverseArray(input));
	}
	
	@Test 
	void testSingleElementArray() {
        int[] input = {10};
        int[] expected = {10};
        assertArrayEquals(expected, array.reverseArray(input));
    }
	
	@Test
	void testEmptyArray() {
        int[] input = {};
        int[] expected = {};
        assertArrayEquals(expected, array.reverseArray(input));
    }
	
	@Test
	void testNullArray() {
        assertNull(array.reverseArray(null));
    }
}
