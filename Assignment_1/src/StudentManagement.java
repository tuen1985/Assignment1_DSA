import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class StudentManagement {
    private LinkedList<Student> students = new LinkedList<>();  // Using LinkedList for easy deletion and addition

    // Add a new student
    public void addStudent(String id, String name, double score) {
        if (id == null || name == null || id.isEmpty() || name.isEmpty()) {
            System.out.println("Invalid input!");
            return;
        }
        students.add(new Student(id, name, score));
    }

    // Edit an existing student
    public void editStudent(String id, String newName, double newScore) {
        for (Student student : students) {
            if (student.id.equals(id)) {
                student.name = newName;
                student.score = newScore;
                student.ranking = student.calculateRanking(newScore);
                return;
            }
        }
        System.out.println("Student not found!");
    }

    // Delete a student by ID
    public void deleteStudent(String id) {
        if (students.removeIf(student -> student.id.equals(id))) {
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Student not found!");
        }
    }

    // Sort students by score using bubble sort
    public void sortByScoreBubbleSort() {
        for (int i = 0; i < students.size() - 1; i++) {
            for (int j = 0; j < students.size() - 1 - i; j++) {
                if (students.get(j).score > students.get(j + 1).score) {
                    Student temp = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, temp);
                }
            }
        }
    }

    // Sort students by name using selection sort
    public void sortByNameSelectionSort() {
        for (int i = 0; i < students.size() - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < students.size(); j++) {
                if (students.get(j).name.compareTo(students.get(minIdx).name) < 0) {
                    minIdx = j;
                }
            }
            Student temp = students.get(i);
            students.set(i, students.get(minIdx));
            students.set(minIdx, temp);
        }
    }

    // Stack + Queue algorithm: Print students using both stack and queue
    public void printUsingStackQueue() {
        StudentStack stack = new StudentStack();  // Using custom StudentStack
        Queue<Student> queue = new LinkedList<>();

        // Push all students to stack and queue
        for (Student student : students) {
            stack.push(student);
            queue.offer(student);
        }

        System.out.println("Stack (LIFO):");
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }

        System.out.println("\nQueue (FIFO):");
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }

    // Search student by ID using linear search
    public Student searchByID(String id) {
        for (Student student : students) {
            if (student.id.equals(id)) {
                return student;
            }
        }
        return null;
    }

    public void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
        } else {
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    public static void main(String[] args) {
        StudentManagement management = new StudentManagement();
        Scanner scanner = new Scanner(System.in);

        // Example of a menu-driven approach
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Display Students");
            System.out.println("5. Sort by Score");
            System.out.println("6. Sort by Name");
            System.out.println("7. Print Using Stack & Queue");
            System.out.println("8. Search by ID");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Score: ");
                    double score = scanner.nextDouble();
                    management.addStudent(id, name, score);
                    break;
                case 2:
                    System.out.print("Enter ID to edit: ");
                    id = scanner.nextLine();
                    System.out.print("Enter new Name: ");
                    name = scanner.nextLine();
                    System.out.print("Enter new Score: ");
                    score = scanner.nextDouble();
                    management.editStudent(id, name, score);
                    break;
                case 3:
                    System.out.print("Enter ID to delete: ");
                    id = scanner.nextLine();
                    management.deleteStudent(id);
                    break;
                case 4:
                    management.displayStudents();
                    break;
                case 5:
                    management.sortByScoreBubbleSort();
                    management.displayStudents();
                    break;
                case 6:
                    management.sortByNameSelectionSort();
                    management.displayStudents();
                    break;
                case 7:
                    management.printUsingStackQueue();
                    break;
                case 8:
                    System.out.print("Enter ID to search: ");
                    id = scanner.nextLine();
                    Student found = management.searchByID(id);
                    if (found != null) {
                        System.out.println("Found: " + found);
                    } else {
                        System.out.println("Student not found!");
                    }
                    break;
                case 9:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}