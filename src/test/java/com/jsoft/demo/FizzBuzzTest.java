/*
 * JUnit 5, Maven 4, Maven Repo
 */
package com.jsoft.demo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FizzBuzzTest {

	public FizzBuzz fB;

	@BeforeEach
	public void setUp() {
		fB = new FizzBuzz();
	}

	@DisplayName("Play FizzBuzz with number = 1")
	@Test
	public void testNumber() {
		String fizzBuzz = fB.play(1);
		Assertions.assertEquals("1", fizzBuzz);
	}

	@DisplayName("Play FizzBuzz with number = 3")
	@Test
	public void testFizz() {
		String fizzBuzz = fB.play(3);
		Assertions.assertEquals("Fizz", fizzBuzz);
	}

	@DisplayName("Play FizzBuzz with number = 5")
	@Test
	public void testBuzz() {
		String fizzBuzz = fB.play(5);
		Assertions.assertEquals("Buzz", fizzBuzz);
	}

	@DisplayName("Don't Play FizzBuzz with number = 0")
	@Test
	public void testZero() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> fB.play(0));
	}

	@AfterEach
	public void tearDown() {
		fB = null;
	}

}