import java.util.Stack;
// you can replace min max with any associative fxn
class MaxMinStack {
    Stack<Long> items;
    Stack<Long> maxStack;
    Stack<Long> minStack;

    public MaxMinStack() {
        items = new Stack<>();
        maxStack = new Stack<>();
        minStack = new Stack<>();
        maxStack.push(Long.MIN_VALUE); // Similar to 1e18 in C++
        minStack.push(Long.MAX_VALUE); // Similar to 0 in C++
    }

    public void push(long x) {
        items.push(x);
        maxStack.push(Math.max(maxStack.peek(), x));
        minStack.push(Math.min(minStack.peek(), x));
    }

    public void pop() {
        if (!items.isEmpty()) {
            items.pop();
            maxStack.pop();
            minStack.pop();
        }
    }

    public long top() {
        return items.isEmpty() ? -1 : items.peek();
    }

    public long getMax() {
        return maxStack.peek();
    }

    public long getMin() {
        return minStack.peek();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}

class MaxMinQueue {
    MaxMinStack s1;
    MaxMinStack s2;

    public MaxMinQueue() {
        s1 = new MaxMinStack();
        s2 = new MaxMinStack();
    }

    public void push(long x) {
        s1.push(x);
    }

    public void pour() {
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.top());
                s1.pop();
            }
        }
    }

    public long front() {
        if (s2.isEmpty()) {
            pour();
        }
        return s2.top();
    }

    public void pop() {
        if (s2.isEmpty()) {
            pour();
        }
        if (!s2.isEmpty()) {
            s2.pop();
        }
    }

    public long getMax() {
        return Math.max(s1.getMax(), s2.getMax());
    }

    public long getMin() {
        return Math.min(s1.getMin(), s2.getMin());
    }
}

public class Main {
    public static void main(String[] args) {
        MaxMinQueue q = new MaxMinQueue();
        q.push(100);
        System.out.println(q.getMax() + " " + q.getMin());
        q.push(50);
        System.out.println(q.getMax() + " " + q.getMin());
        q.push(150);
        System.out.println(q.getMax() + " " + q.getMin());
        q.pop();
        System.out.println(q.getMax() + " " + q.getMin());
        q.pop();
        System.out.println(q.getMax() + " " + q.getMin());
        q.pop();
        System.out.println(q.getMax() + " " + q.getMin());
    }
}