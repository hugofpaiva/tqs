import java.util.LinkedList;

public class TqsStack<T> {

    private LinkedList<T> stack = new LinkedList<>();

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public int size() {
        return stack.size();
    }

    public void push(T element){
        stack.addFirst(element);
    }

    public T pop(){
        return stack.removeFirst();
    }

    public T peek(){
        return stack.getFirst();
    }

}
