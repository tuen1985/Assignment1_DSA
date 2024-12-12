public class StudentStack {
    private Student[] stack;
    private int top;

    public StudentStack(int capacity) {
        this.stack = new Student[capacity];
        this.top = -1;
    }

    public void push(Student student) {
        if (this.top == this.stack.length - 1) {
//            System.out.println("Stack is full. Cannot push.");
        } else {
            this.stack[++this.top] = student;
        }

    }

    public Student peek() {
        if (this.isEmpty()) {
            System.out.println("Stack is empty. Cannot peek.");
            return null;
        } else {
            return this.stack[this.top];
        }
    }

    public Student pop() {
        if (this.isEmpty()) {
            System.out.println("Stack is empty. Cannot pop.");
            return null;
        } else {
            return this.stack[this.top--];
        }
    }

    public boolean isEmpty() {
        return this.top == -1;
    }

    public int size() {
        return this.top + 1;
    }

    public int getCapacity() {
        return this.stack.length;
    }
}


