package System;

public class StudentRecord {
    private int studentId, age, gpa;
    private String name, gender, department, grade;

    public StudentRecord(int studentId, String name, int age, String gender, String department, int gpa) {
        this.studentId = studentId;
        this.age = age;
        this.gpa = gpa;
        this.name = name;
        this.gender = gender;
        this.department = department;
        this.grade = toGrade();
    }

    public StudentRecord(int studentId, String name, int age, String gender, String department, String grade) {
        this.studentId = studentId;
        this.age = age;
        this.gpa = toGPA();
        this.name = name;
        this.gender = gender;
        this.department = department;
        this.grade = toGrade();
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGPA() {
        return gpa;
    }

    public void setGPA(int gpa) {
        this.gpa = gpa;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String toGrade(){
        // use this.gpa
        return "";
    }

    public int toGPA(){
        // use this.grade
        return 0;
    }

    public int generateStudentId(){
        return 0;
    }

    public String lineRepresentation(){
        // ID,Name,Age,Gender,Department,GPA,Grade
        // comma separated
        return "";
    }




}
