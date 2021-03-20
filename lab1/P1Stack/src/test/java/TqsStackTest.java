import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;
import java.util.Random;

class TqsStackTest {

    private TqsStack<Integer> emptyStack;
    private TqsStack<Integer> stack;
    private int last_elem;
    private int size;
    private Random rand = new Random();

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        System.out.println("@BeforeAll executed");
        emptyStack = new TqsStack<>();
        stack = new TqsStack<>();

        int rand_int = rand.nextInt(1000) + 1;

        int i;
        for(i = 0; i <= rand_int; ++i){
            stack.push(i);
        }

        last_elem = i-1;

        size = i;

    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        System.out.println("@BeforeEach executed");
    }

    @org.junit.jupiter.api.Test
    void isEmpty() {
        assertTrue(emptyStack.isEmpty(), "Stack is not empty on construction");
    }

    @org.junit.jupiter.api.Test
    void size() {
        assertEquals(0, emptyStack.size(), "Stack hasn't got size 0 on construction");
    }

    @org.junit.jupiter.api.Test
    void push() {
        assertFalse(stack.isEmpty(), "Stack is not empty after n pushes, being n>0");

        assertEquals(size, stack.size(), "Stack size is not the same as the number of pushed elements");

    }

    @org.junit.jupiter.api.Test
    void pop() {
        assertEquals(last_elem, stack.pop(), "The value popped is not the one pushed");
    }

    @org.junit.jupiter.api.Test
    void popEmptyStack() {
        Exception exception = assertThrows(NoSuchElementException.class, () -> emptyStack.pop());
    }

    @org.junit.jupiter.api.Test
    void peek() {
        assertEquals(last_elem, stack.peek(), "The value peeked is not the one pushed");

        assertEquals(size, stack.size(), "Size is not the same after peek");

    }

    @org.junit.jupiter.api.Test
    void peekEmptyStack() {
        Exception exception = assertThrows(NoSuchElementException.class, () -> emptyStack.peek());
    }

    @org.junit.jupiter.api.Test
    void pushBoundedStack() {
        TqsStack<Integer> boundedStack = new TqsStack(1);
        boundedStack.push(rand.nextInt(1000));

        Exception exception = assertThrows(IllegalStateException.class, () -> boundedStack.push(rand.nextInt(1000)));
    }
}