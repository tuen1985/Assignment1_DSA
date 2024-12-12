import java.util.Random;
import java.util.Scanner;

public class TestAlgorithm {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of students: ");
        int n = scanner.nextInt();

        StudentStack studentStack = generateRandomStudents(n);

        // Clone the stack to ensure both algorithms work on identical data
        StudentStack stackForBubbleSort = cloneStack(studentStack);
        StudentStack stackForMergeSort = cloneStack(studentStack);
        StudentStack stackForQuickSort = cloneStack(studentStack);

        // Measure Bubble Sort time
        long startTimeBubble = System.nanoTime();
        bubbleSort(stackForBubbleSort);
        long endTimeBubble = System.nanoTime();
        System.out.println("Bubble Sort Time: " + (endTimeBubble - startTimeBubble) + " ns");

        // Measure Merge Sort time
        long startTimeMerge = System.nanoTime();
        mergeSort(stackForMergeSort);
        long endTimeMerge = System.nanoTime();
        System.out.println("Merge Sort Time: " + (endTimeMerge - startTimeMerge) + " ns");

        long startTimeQuick = System.nanoTime();
        quickSort(stackForQuickSort); // Clone đã được tạo
        long endTimeQuick = System.nanoTime();
        System.out.println("Quick Sort Time: " + (endTimeQuick - startTimeQuick) + " ns");
    }

    private static StudentStack generateRandomStudents(int n) {
        StudentStack stack = new StudentStack(n);
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            String id = "S" + (i + 1);
            String name = "Student" + (i + 1);
            double marks = 1 + random.nextDouble() * 9; // Marks in range [1.0, 10.0]
            stack.push(new Student(id, name, marks));
        }
        return stack;
    }

    private static void displayStack(StudentStack stack) {
        StudentStack tempStack = cloneStack(stack);
        while (!tempStack.isEmpty()) {
            System.out.println(tempStack.pop());
        }
    }

    private static StudentStack cloneStack(StudentStack original) {
        StudentStack temp = new StudentStack(original.getCapacity());
        StudentStack clone = new StudentStack(original.getCapacity());

        // Pop tất cả phần tử từ stack gốc vào stack tạm
        while (!original.isEmpty()) {
            temp.push(original.pop());
        }

        // Đẩy lại phần tử vào stack gốc và stack clone
        while (!temp.isEmpty()) {
            Student student = temp.pop();
            original.push(student);
            clone.push(student);
        }

        return clone;
    }

    private static void bubbleSort(StudentStack stack) {
        int n = stack.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Student s1 = stack.pop();
                Student s2 = stack.pop();
                if (s1.getMarks() > s2.getMarks()) {
                    stack.push(s1);
                    stack.push(s2);
                } else {
                    stack.push(s2);
                    stack.push(s1);
                }
            }
        }
    }

    private static StudentStack mergeSort(StudentStack stack) {
        if (stack.size() <= 1) {
            return stack;
        }

        int mid = stack.size() / 2;
        StudentStack left = new StudentStack(mid);
        StudentStack right = new StudentStack(stack.size() - mid);

        while (!stack.isEmpty()) {
            if (left.size() < mid) {
                left.push(stack.pop());
            } else {
                right.push(stack.pop());
            }
        }

        left = mergeSort(left);
        right = mergeSort(right);

        return merge(left, right);
    }

    private static StudentStack merge(StudentStack left, StudentStack right) {
        StudentStack merged = new StudentStack(left.size() + right.size());

        while (!left.isEmpty() && !right.isEmpty()) {
            if (left.peek().getMarks() <= right.peek().getMarks()) {
                merged.push(left.pop());
            } else {
                merged.push(right.pop());
            }
        }

        while (!left.isEmpty()) {
            merged.push(left.pop());
        }

        while (!right.isEmpty()) {
            merged.push(right.pop());
        }

        return merged;
    }
    // QuickSort Implementation
    private static StudentStack quickSort(StudentStack stack) {
        if (stack.size() <= 1) {
            return stack;
        }

        // Random Pivot
        Random random = new Random();
        int pivotIndex = random.nextInt(stack.size());
        Student pivot = null;

        // Di chuyển pivot về đỉnh stack
        for (int i = 0; i <= pivotIndex; i++) {
            pivot = stack.pop();
        }

        StudentStack lower = new StudentStack(stack.getCapacity());
        StudentStack higher = new StudentStack(stack.getCapacity());

        // Phân chia stack thành lower và higher
        while (!stack.isEmpty()) {
            Student current = stack.pop();
            if (current.getMarks() <= pivot.getMarks()) {
                lower.push(current);
            } else {
                higher.push(current);
            }
        }

        // Đệ quy sắp xếp lower và higher
        lower = quickSort(lower);
        higher = quickSort(higher);

        // Hợp nhất: lower + pivot + higher
        StudentStack sortedStack = new StudentStack(lower.size() + higher.size() + 1);

        // Push từ lower trước
        while (!lower.isEmpty()) {
            sortedStack.push(lower.pop());
        }

        // Push pivot
        sortedStack.push(pivot);

        // Push từ higher sau
        while (!higher.isEmpty()) {
            sortedStack.push(higher.pop());
        }

        return sortedStack;
    }
}