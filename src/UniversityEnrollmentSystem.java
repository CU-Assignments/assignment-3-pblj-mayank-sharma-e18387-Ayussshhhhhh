import java.util.HashMap;
import java.util.Scanner;

class CourseFullException extends Exception {
    public CourseFullException(String message) {
        super(message);
    }
}

class PrerequisiteNotMetException extends Exception {
    public PrerequisiteNotMetException(String message) {
        super(message);
    }
}


class Course {
    private String name;
    private int maxSeats;
    private int enrolledStudents;
    private String prerequisite;

    public Course(String name, int maxSeats, String prerequisite) {
        this.name = name;
        this.maxSeats = maxSeats;
        this.prerequisite = prerequisite;
        this.enrolledStudents = 0;
    }

    public String getPrerequisite() {
        return prerequisite;
    }

    public void enrollStudent(boolean prerequisiteCompleted) throws CourseFullException, PrerequisiteNotMetException {
        if (!prerequisite.isEmpty() && !prerequisiteCompleted) {
            throw new PrerequisiteNotMetException("Error: Complete " + prerequisite + " before enrolling in " + name + ".");
        }

        if (enrolledStudents >= maxSeats) {
            throw new CourseFullException("Error: " + name + " is full. No more seats available.");
        }

        enrolledStudents++;
        System.out.println("Enrollment successful! " + name + " Seats Left: " + (maxSeats - enrolledStudents));
    }
}

public class UniversityEnrollmentSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        HashMap<String, Course> courses = new HashMap<>();
        courses.put("Core Java", new Course("Core Java", 5, ""));
        courses.put("Advanced Java", new Course("Advanced Java", 3, "Core Java"));
        courses.put("Data Science", new Course("Data Science", 2, "Core Java"));

        try {
            System.out.print("Enter Course Name to Enroll: ");
            String courseName = scanner.nextLine();

            if (!courses.containsKey(courseName)) {
                System.out.println("Error: Course not found.");
                return;
            }

            Course selectedCourse = courses.get(courseName);

            boolean prerequisiteCompleted = true;
            if (!selectedCourse.getPrerequisite().isEmpty()) { // Using getter method here
                System.out.print("Have you completed " + selectedCourse.getPrerequisite() + "? (yes/no): ");
                String response = scanner.nextLine().trim().toLowerCase();
                prerequisiteCompleted = response.equals("yes");
            }

            selectedCourse.enrollStudent(prerequisiteCompleted);

        } catch (PrerequisiteNotMetException | CourseFullException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: Invalid input.");
        } finally {
            scanner.close();
        }
    }
}
