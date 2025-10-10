package management_System;
import java.io.Serializable;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Admin extends User implements Admin_Actions, Serializable {
	
	/* constructs an Admin object using the default username and password "Admin" and "Admin001" as well
	 * as the first name and last name the user inputs via the CLI. */
    public Admin(String username, String password, String first_name, String last_name) {
        super("Admin", "Admin001", first_name, last_name); 
    }
    
    /* allows the Admin to view all available courses that exist */
    public void view_courses(ArrayList<Course>courses) {
    	for (Course a:courses) {
    		System.out.println("name:" + a.name + " id:" + a.id + " maximum_students:" + a.max + " current_students:" + a.num);
        	System.out.println(String.join(",", a.students));
        	System.out.println("Course_Instructor:"+ a.instructor + " Course_Section_Number:" + a.sec_num + " Course_Location:" + a.location);
    	}
    }
    
    /* allows the Admin to search for a specific course via its course ID and returns a Course, unless
     * the course cannot be found via the course ID inputted in the CLI. */
    public Course search_course(ArrayList<Course>courses, Scanner scanner) {
    	Course course1 = null;
	    while (course1 == null){
	    	System.out.println("Enter the course ID of the course you want");
			String given_id = scanner.nextLine();
			for (int i=0; i<courses.size();i++) {
				if (courses.get(i).id.equals(given_id)) {
					course1 = courses.get(i);
					break;
				}
			}
			if (course1== null) {
				System.out.println ("Course not found. Please try again.");
			}
	    }

	    return course1;
		
    }
    	
    /* allows the Admin to add a course to the list of courses as long as they input all requisite
     * information in the CLI (course name, course ID, max students, instructor, section number
     * and course location separated by commas. */
    public void add_course(ArrayList<Course>courses, Scanner scanner) {
    	System.out.println("Please give me the course name, course id, maximum number of students, course instructor, course section number and course location, delimited by coma");
    	String answer = scanner.nextLine();
    	String[] a = answer.split(",");
    	int max_num = Integer.parseInt(a[2]);
    	ArrayList<Student>students = new ArrayList<>();
    	ArrayList<String>students1 = new ArrayList<>();
    	Course course = new Course(a[0], a[1], max_num, 0, students,students1,a[3],a[4],a[5]);
    	courses.add(course);

    }
    
    /* allows the Admin to delete a course by removing the course specified from the ArrayList of
     * courses. */
    public void del_course(ArrayList<Course>courses, Scanner scanner) {
    	Course course_to_delete = search_course(courses, scanner);
    	courses.remove(course_to_delete);
    }
    	
    /* allows the Admin to edit the specific properties of a certain course (uses the search method).
     * enters into a submenu of choices A-D for the following properties: max students, instructor,
     * section number, and location. */	
    public void edit(ArrayList<Course>courses, Scanner scanner) {
    	Course course_to_edit = search_course(courses,scanner);
    	
    	Scanner c = new Scanner(System.in);
    	System.out.println("What do you want to edit: A:maximum number of students, B:instructor, C:section number or D:location?");
    	String answer = c.nextLine();
    	switch (answer) {
    	case "A":
    		System.out.println("Enter the new maximum number of students:");
    		int max_given = c.nextInt();
    		course_to_edit.max = max_given;
    		break;
    	case "B":
    		System.out.println("Enter the name of the new instructor:");
    		String new_name = c.nextLine();
    		course_to_edit.instructor = new_name;
    		break;
    	case "C":
    		System.out.println("Enter the new section number:");
    		String section = c.nextLine();
    		course_to_edit.sec_num = section;
    		break;
    	case "D":
    		System.out.println("Enter the new location:");
    		String loc = c.nextLine();
    		course_to_edit.location = loc;
    		break;

    	}
    }
    
    /* allows the Admin to view the info for a specific course that the user searches for. */
    public void display_info(ArrayList<Course>courses, Scanner scanner) {
    	Course course2 = search_course(courses,scanner);
    	System.out.println("name:" + course2.name + " id:" + course2.id + " maximum_students:" + course2.max + " current_students:" + course2.num);
    	System.out.println(String.join(",", course2.students));
    	System.out.println("Course_Instructor:"+ course2.instructor + " Course_Section_Number:" + course2.sec_num + " Course_Location:" + course2.location);
    }
    
    /* allows the Admin to register a specific student for a specific course as long as the course
     * isn't full, the student exists, and the student isn't already registered for the course. */
    public void register(ArrayList<Course>courses, Scanner scanner) {
    	Course course_to_register = search_course(courses,scanner);
    	Scanner c = new Scanner(System.in);
    	System.out.println("Enter the name of the student you want to register for a course:");
    	String r_name = c.nextLine();
    	if (course_to_register.students.contains(r_name)) {
    		System.out.println("Student is already registered.");
    	}
    	else {
    		if (course_to_register.num >= course_to_register.max) {
    			System.out.println("Course is already full.");
    		}
    		else{
    			for (int i=0; i< User_Manager.all_students.size();i++) {
    				Student temp = User_Manager.all_students.get(i);
    				String fullName = temp.first_name + " " + temp.last_name;
    				if (fullName.equals(r_name)) {
    					course_to_register.students_objects.add(temp);
    				}
    			}
    			course_to_register.students.add(r_name);
    			course_to_register.num++;
    		}
    	}
    }
    
    /* overloaded method for register, this one allows the Admin to create a whole new Student
     * by inputting all necessary information via the CLI. it then adds the Student to the ArrayList
     * of all Students.*/
    public void register(Scanner scanner) {
    	Scanner c = new Scanner(System.in);
    	System.out.println("Enter the student's username:");
		String username1 = c.nextLine();
		System.out.println("Enter the student's password:");
		String password1 = c.nextLine();
		System.out.println("Enter the student's first name:");
		String first_name1 = c.nextLine();
		System.out.println("Enter the student's last name:");
		String last_name2 = c.nextLine();
		System.out.println("Would you like to enter a birthday for the student (Y/N)?");
		String birthdayChoice = c.nextLine();
		
		if(birthdayChoice.equals("Y")) {
			System.out.println("Enter the student's birthday in the format MM-DD-YYYY:");
			String birthday = c.nextLine();
			Student student = new Student(username1, password1, first_name1, last_name2, birthday);
			User_Manager.all_students.add(student);
		}
		
		else {
			Student student = new Student(username1, password1, first_name1, last_name2);
			User_Manager.all_students.add(student);
		}
    }
    
    /* allows Admin to view all courses that are at max capacity of students registered. */
    public void view_full(ArrayList<Course>courses) {
    	for (int i=0; i<courses.size();i++) {
    		if (courses.get(i).num == courses.get(i).max) {
    			System.out.println(courses.get(i).name+"," + courses.get(i).id);
    		}
    	}
    }
    
    /* allows Admin to write all the courses to a CSV file that gets saved in the directory of the program */
    public void write_file(ArrayList<Course>courses) {
    	try (FileWriter writer = new FileWriter ("fullcourse.csv");) {
			writer.append("Course_Id,Course_Name,Maximum_Students,Current_Students,List_of_Names,Course_Instructor,Course_Section_Number,Course_Location\n");
			for (Course c:courses) {
				if (c.num == c.max) {
					writer.append(c.id).append(",")
						  .append(c.name).append(",")
						  .append(String.valueOf(c.max)).append(",")
						  .append(String.valueOf(c.num)).append(",")
						  .append(String.join(",",c.students)).append(",")
						  .append(c.instructor).append(",")
						  .append(c.sec_num).append(",")
						  .append(c.location).append("\n");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    
    }
    
    /* allows the Admin to view all students that are registered for a specific course. */
	public void roster(ArrayList<Course>courses, Scanner scanner) {
    	Course course_roster = search_course(courses,scanner);
    	System.out.println(String.join(",", course_roster.students));
    	
    }
	
	/* allows the Admin to view all the courses a specific student is registered for as long as
	 * the student exists. */
	@Override
	public void view_student(ArrayList<Course> courses,Scanner scanner) {
    	boolean found = true;
    	while (found){
	    	System.out.println("Which student do you want to search:");
	    	String student_name = scanner.nextLine();
	    	
	    	for (Student temp: User_Manager.all_students) {
				String fullName = temp.first_name + " " + temp.last_name;
				if (fullName.equals(student_name)) {
					found = false;
					for (Course a:courses) {
						if (a.students.contains(fullName)) {
							System.out.println(a.name);
						}
					}
				}
	    	}
	    	if (!found) {
	    		System.out.println("no record of the student");
	    	}
    		System.out.println("Do you want to continue, answer yes or no");
    		String answer = scanner.nextLine();
    		switch(answer) {
    			case "yes": break;
    			case "no": found=false; break;
    			default: System.out.println("Invalid Choice, Enter name again"); break;
	    	}
	    }
    }

	public void sort_course(ArrayList<Course>courses) {
    	courses.sort(Comparator.comparingInt(c -> c.num));
    }	
	/* provides the menu for the Admin to perform all actions described above through the CLI.
	 * the commands are split into two menus selected by either 1 or 2: 1 for course management
	 * and 2 for reports. in course management, there are options 1-6 that do the following:
	 * add a course, delete a course, edit a course's attributes, display the info for a course,
	 * register a certain student for a specific course OR register a new student, and exit the menu.
	 * in reports, there are options 1-7 that do the following: view all courses, view all courses that
	 * are full, write the list of courses that are full to a file, view the names of the students
	 * that are registered in a specific course, view the list of courses a specific student is 
	 * registered for, sort courses based on the current number of student registers, and exit the menu. */
	@Override
	public void menu(Scanner scanner) {
		Boolean running = true;
		while (running) {
			System.out.println("1.Course Management\n2.Reports");
			String d = scanner.nextLine();
			switch(d) {
			case "1":
				System.out.println("1.Create a Course\n2.Delete a Course\n3.Edit a Course\n4.Display Information for a Given Course\n5.Register a Student\n6.Exit");
				String e = scanner.nextLine();
				switch(e) {
					case "1": this.add_course(ReadCSV.courses,scanner); break;
					case "2": this.del_course(ReadCSV.courses,scanner); break;
					case "3": this.edit(ReadCSV.courses,scanner); break;
					case "4": this.display_info(ReadCSV.courses,scanner); break;
					case "5": 
						System.out.println("Would you like to register 1) a new student or 2) an existing student for a specific course?");
						String registrationType = scanner.nextLine();
						switch(registrationType) {
							case "1": this.register(scanner); break;
							case "2": this.register(ReadCSV.courses,scanner); break;
						}
						break;
					case "6": System.out.println("Logging out..."); running = false; break;
				}
				break;
			case "2":
				System.out.println("1.View All Courses\n2.View all Courses that are Full\n3.a file of all full courses\n4.view names of a students registered in a course\n5.view a student's list of courses");
				String f = scanner.nextLine();
				switch(f) {
					case "1": this.view_courses(ReadCSV.courses); break;
					case "2": this.view_full(ReadCSV.courses); break;
					case "3": this.write_file(ReadCSV.courses); break;
					case "4": this.roster(ReadCSV.courses,scanner); break;
					case "5": this.view_student(ReadCSV.courses,scanner); break;
					case "6": this.sort_course(ReadCSV.course); break;
					case "7": System.out.println("Logging out..."); running = false; break;
				}
				break;
			default:
				System.out.println("Invalid Choice.");
			}	
		}	
	}
}	