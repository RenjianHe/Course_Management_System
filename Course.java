package management_System;
import java.io.Serializable;
import java.util.ArrayList;

// class for courses that contains all necessary fields for a Course object and has a constructor
// that sets all of the fields to the values passed by the constructor. it is serializable.
public class Course implements Serializable {
	protected String name;
	protected String id;
	protected int max;
	protected int num;
	protected ArrayList<Student>students_objects;
	protected ArrayList<String>students;
	protected String instructor;
	protected String sec_num;
	protected String location;
	
	public Course (String name, String id, int max, int num, ArrayList<Student>students_objects, ArrayList<String>students, String instructor, String sec_num, String location) {
		this.name = name;
		this.id = id;
		this.max = max;
		this.num = num;
		this.students_objects = students_objects;
		this.students = students;
		this.instructor = instructor;
		this.sec_num = sec_num;
		this.location = location;
	}
		
}
