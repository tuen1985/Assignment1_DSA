class StudentStack {
    private static final int MAX_SIZE = 100;
    private Student[] stack;
    private int top;

    public StudentStack() {
        stack = new Student[MAX_SIZE];
        top = -1;
    }

    // Push a student onto the stack
    public void push(Student student) {
        if (top == MAX_SIZE - 1) {
            System.out.println("Stack overflow!");
            return;
        }
        stack[++top] = student;
    }

    // Pop a student off the stack
    public Student pop() {
        if (isEmpty()) {
            System.out.println("Stack underflow!");
            return null;
        }
        return stack[top--];
    }

    // Peek at the top student without removing it
    public Student peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return null;
        }
        return stack[top];
    }

    // Get the top student (alias for peek)
    public Student top() {
        return peek();
    }

    // Check if the stack is empty
    public boolean isEmpty() {
        return top == -1;
    }
}