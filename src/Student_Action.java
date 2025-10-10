package management_System;
import java.util.Scanner;
import java.util.ArrayList;

public interface Student_Action {
	/* interface that provides the method stubs for all operations that the Student class implements */
	abstract void view_not_full(ArrayList<Course>courses);
	abstract void register1(ArrayList<Course>courses, Scanner scanner);
	abstract void withdraw(ArrayList<Course>courses, Scanner scanner);
	abstract void view_all_course(ArrayList<Course>courses, Student b);
}
