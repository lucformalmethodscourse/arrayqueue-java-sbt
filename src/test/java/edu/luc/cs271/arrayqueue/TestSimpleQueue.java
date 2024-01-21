package edu.luc.cs271.arrayqueue;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class TestSimpleQueue {

  private SimpleQueue<String> fixture;

  @BeforeEach
  public void setUp() {
    fixture = new FixedArrayQueue<>(2);
  }

  @AfterEach
  public void tearDown() {
    fixture = null;
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeCapacity() {
    new FixedArrayQueue<>(-12);
  }
  
  @Test
  public void testInitial() {
    assertTrue(fixture.isEmpty());
    assertEquals(0, fixture.size());
    assertNull(fixture.peek());
    assertNull(fixture.poll());
  }

  @Test
  public void testAfterOffer() {
    final var value = "hello";
    assertTrue(fixture.offer(value));
    assertFalse(fixture.isEmpty());
    assertEquals(1, fixture.size());
    assertEquals(value, fixture.peek());
  }

  @Test
  public void testOfferThenPoll() {
    final var value = "hello";
    assertTrue(fixture.offer(value));
    assertEquals(value, fixture.poll());
    assertTrue(fixture.isEmpty());
  }

  @Test
  public void testOffer2ThenPoll2() {
    final var value1 = "hello";
    final var value2 = "world";
    assertTrue(fixture.offer(value1));
    assertTrue(fixture.offer(value2));
    assertEquals(value1, fixture.poll());
    assertEquals(value2, fixture.poll());
    assertTrue(fixture.isEmpty());
  }

  @Test
  public void testOffer3Poll3() {
    final var value1 = "hello";
    final var value2 = "world";
    final var value3 = "what";
    assertTrue(fixture.offer(value1));
    assertTrue(fixture.offer(value2));
    assertEquals(value1, fixture.poll());
    assertTrue(fixture.offer(value3));
    assertEquals(value2, fixture.poll());
    assertEquals(value3, fixture.poll());
    assertTrue(fixture.isEmpty());
  }

  @Test
  public void testOffer5Poll5() {
    final var value1 = "hello";
    final var value2 = "world";
    final var value3 = "what";
    final var value4 = "up";
    final var value5 = "today";
    assertTrue(fixture.offer(value1));
    assertTrue(fixture.offer(value2));
    assertEquals(value1, fixture.poll());
    assertTrue(fixture.offer(value3));
    assertEquals(value2, fixture.poll());
    assertEquals(value3, fixture.poll());
    assertTrue(fixture.offer(value4));
    assertTrue(fixture.offer(value5));
    assertEquals(value4, fixture.poll());
    assertEquals(value5, fixture.poll());
    assertTrue(fixture.isEmpty());
  }

  @Test
  public void testOffer3() {
    final var value1 = "hello";
    final var value2 = "world";
    final var value3 = "what";
    assertTrue(fixture.offer(value1));
    assertTrue(fixture.offer(value2));
    assertFalse(fixture.offer(value3));
    assertEquals(2, fixture.size());
  }

  @Test
  public void testAsListEmpty() {
    assertEquals(0, fixture.asList().size());
  }

  @Test
  public void testAsListNonempty() {
    final var value1 = "hello";
    final var value2 = "world";
    fixture.offer(value1);
    fixture.offer(value2);
    final var list = fixture.asList();
    assertEquals(2, list.size());
    assertEquals(List.of(value1, value2), list);
  }

  @Test
  public void testAsListNonempty2() {
    final var value1 = "hello";
    final var value2 = "world";
    final var value3 = "what";
    fixture.offer(value1);
    fixture.offer(value2);
    fixture.poll();
    fixture.offer(value3);
    final var list = fixture.asList();
    assertEquals(2, fixture.size());
    assertEquals(2, list.size());
    assertEquals(List.of(value2, value3), list);
  }
}
