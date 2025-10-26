package System;

public class StudentRecord {
    private int studentId, age;
    private Double gpa;
    private String name, gender, department, grade;

    public StudentRecord(int studentId, String name, int age, String gender, String department, Double gpa) {
        this.setStudentId(studentId);
        this.setAge(age);
        this.setName(name);
        this.setGender(gender);
        this.setDepartment(department);
        this.setGPA(gpa);
    }

    public StudentRecord() { this.setStudentId(generateStudentId()); }

    public int getStudentId() { return studentId; }

    public boolean setStudentId(int studentId) {
        StudentDatabase records = new StudentDatabase("Students.txt");
        if(records.contains(studentId))
            return false;
        else {
            this.studentId = studentId;
            return true;
        }
    }

    public int getAge() { return age;}

    public boolean setAge(int age) {
        if(age < 17)
            return false;
        this.age = age;
        return true;
    }

    public Double getGPA() { return gpa; }

    public boolean setGPA(Double gpa) {
        if(gpa > 4.0 && gpa < 0.0)
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

    public int generateStudentId(){
        StudentDatabase records = new StudentDatabase("Students.txt");
        int id = 0;
        for(StudentRecord record : records.getStudentRecords()){
            if(record.getStudentId() > id)
                id = record.getStudentId();
        }
        return id + 1;
    }

    public String lineRepresentation(){
        // ID,Name,Age,Gender,Department,GPA
        // comma separated
        return this.studentId+","+this.name+","+this.age+","+this.gender+","+this.department+","+this.gpa;
    }




}
