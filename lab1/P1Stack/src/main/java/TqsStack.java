import java.util.LinkedList;

/**
 * Implementation of a Stack
 * @param <T>
 */
public class TqsStack<T> {

    private LinkedList<T> stack = new LinkedList<>();

    private int bound = -1;

    public TqsStack(){

    }

    /**
     * For Bounded Stack only
     * @param bound
     */
    public TqsStack(int bound){
        this.bound=bound;
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public int size() {
        return stack.size();
    }

    /**
     * Checks if it is a Bounded Stack and then pushes accordingly
     * @param element
     */
    public void push(T element){
        if (bound > 0){
            if (this.size() < bound){
                stack.addFirst(element);
            } else {
                throw new IllegalStateException();
            }
        } else {
        stack.addFirst(element);
        }
    }

    public T pop(){
        return stack.removeFirst();
    }

    public T peek(){
        return stack.getFirst();
    }

}
