package System;

public class StudentRecord {
    private int studentId, age;
    private Double gpa;
    private String name, gender, department, grade;

    public StudentRecord(int studentId, String name, int age, String gender, String department, Double gpa) {
        this.studentId = studentId;
        this.age = age;
        this.gpa = gpa;
        this.name = name;
        this.gender = gender;
        this.department = department;
    }
/*
    public StudentRecord(int studentId, String name, int age, String gender, String department, String grade) {
        this.setStudentId(studentId);
        this.setAge(age);
        this.setName(name);
        this.setGender(gender);
        this.setDepartment(department);
        this.setGrade(grade);
    }
*/
    public int getStudentId() { return studentId; }

    public boolean setStudentId(int studentId) {
        StudentDatabase records = new StudentDatabase("jadh");
        if(records.contains(studentId))
            return false;
        else {
            this.studentId = studentId;
            return true;
        }
    }

    public int getAge() { return age;}

    public boolean setAge(int age) {
        if(age >= 18 && age <= 4)
            return false;
        this.age = age;
        return true;
    }

    public Double getGPA() { return gpa; }

    public boolean setGPA(Double gpa) {
        if(gpa > 4.0 && gpa < 0)
            return false;
        this.gpa = gpa;
        return true;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getGender() { return gender; }

    public void setGender(String gender) { this.gender = gender; }

    public String getDepartment() { return department; }

    public void setDepartment(String department) { this.department = department; }
/*
    public boolean setGrade(String grade){ this.grade = grade; }

    public String getGrade(){ return this.grade; }
*/
    public int generateStudentId(){
        StudentDatabase records = new StudentDatabase("filePath");
        int id = 0;
        for(StudentRecord record : records.getStudentRecords()){
            if(record.getStudentId() > id)
                id = record.getStudentId();
        }
        return id + 1;
    }

    public String lineRepresentation(){
        // ID,Name,Age,Gender,Department,GPA,Grade
        // comma separated
        return this.studentId+","+this.name+","+this.gender+","+this.department+","+this.grade;
    }




}
