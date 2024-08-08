import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static List<Student> studentList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n;
        System.out.print("Enter the number of students: ");
        n = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (int i = 1; i <= n; i++) {
            input();
        }
        boolean running = true;
        while (running) {
            System.out.println("1. Print student list");
            System.out.println("2. Delete student by ID");
            System.out.println("3. Sort students by descending scores");
            System.out.println("4. Search for students by ID or name");
            System.out.println("5. Find students with scores >= x");
            System.out.println("6. Exit");
            System.out.print("Select a function: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    output();
                    break;
                case 2:
                    System.out.print("Enter the student ID to delete:");
                    String code = scanner.nextLine();
                    removeByCode(code);
                    break;
                case 3:
                    sortByGradeDesc();
                    break;
                case 4:
                    System.out.print("Enter student ID or name: ");
                    String keyword = scanner.nextLine();
                    Student student = findByCodeOrName(keyword);
                    if (student != null) {
                        System.out.println(student);
                    } else {
                        System.out.println("No students found!");
                    }
                    break;
                case 5:
                    System.out.print("Enter minimum score: ");
                    float grade = scanner.nextFloat();
                    scanner.nextLine(); // Consume newline
                    List<Student> filteredStudents = filterByGrade(grade);
                    if (filteredStudents.isEmpty()) {
                        System.out.println("No students were found with a score >= " + grade);
                    } else {
                        for (Student s : filteredStudents) {
                            System.out.println(s);
                        }
                    }
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid selection. Please select again!");
                    break;
            }
        }
        scanner.close();
    }

    // Nhập thông tin sinh viên
    public static void input() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your student code: ");
        String code = scanner.nextLine();
        System.out.print("Enter student name:");
        String name = scanner.nextLine();
        System.out.print("Enter Age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter Gender (0: Male, 1: Female): ");
        int gender = scanner.nextInt();
        System.out.print("Enter Grade: ");
        float grade = scanner.nextFloat();
        scanner.nextLine(); // Consume newline

        Student student = new Student(name, age, phone, code, gender, grade);
        studentList.add(student);
    }

    // In danh sách sinh viên
    public static void output() {
        for (Student student : studentList) {
            System.out.println(student);
        }
    }

    // Xóa sinh viên theo mã
    public static void removeByCode(String code) {
        boolean removed = studentList.removeIf(student -> student.getCode().equals(code));
        if (removed) {
            System.out.println("Students have encryption " + code + " has been deleted!");
        } else {
            System.out.println("No student with code found " + code);
        }
    }

    // Sắp xếp sinh viên theo điểm giảm dần
    public static void sortByGradeDesc() {
        Collections.sort(studentList, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return Float.compare(s2.getGrade(), s1.getGrade());
            }
        });
        System.out.println("The student list has been sorted by descending score!");
    }

    // Tìm kiếm sinh viên theo mã hoặc tên
    public static Student findByCodeOrName(String keyword) {
        for (Student student : studentList) {
            if (student.getCode().equalsIgnoreCase(keyword) || student.getName().equalsIgnoreCase(keyword)) {
                return student;
            }
        }
        return null;
    }

    // Tìm sinh viên có điểm >= x
    public static List<Student> filterByGrade(float x) {
        List<Student> result = new ArrayList<>();
        for (Student student : studentList) {
            if (student.getGrade() >= x) {
                result.add(student);
            }
        }
        return result;
    }
}

class Student {
    private String name;
    private int age;
    private String phone;
    private String code;
    private int gender; // 0: Nam, 1: Nữ
    private float grade;

    public Student(String name, int age, String phone, String code, int gender, float grade) {
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.code = code;
        this.gender = gender;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }

    public String getCode() {
        return code;
    }

    public int getGender() {
        return gender;
    }

    public float getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", code='" + code + '\'' +
                ", gender=" + (gender == 0 ? "Male" : "Female") +
                ", grade=" + grade +
                '}';
    }
}