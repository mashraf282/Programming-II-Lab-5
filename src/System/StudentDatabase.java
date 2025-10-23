package System;

import java.util.ArrayList;

public class StudentDatabase {

    private ArrayList<StudentRecord> studentRecords;
    private final String filePath;

    public StudentDatabase(String filePath) {
        this.filePath = filePath;
        this.studentRecords = new ArrayList<>();
    }

    public ArrayList<StudentRecord> getStudentRecords() {
        return studentRecords;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setStudentRecords(ArrayList<StudentRecord> studentRecords) {
        this.studentRecords = studentRecords;
    }

    public void readFromFile() {
        // implement file reading logic
        // ID,Name,Age,Gender,Department,GPA,Grade
    }

    public void writeToFile() {
        // implement file writing logic
        // ID,Name,Age,Gender,Department,GPA,Grade
    }

    public StudentRecord createRecord(String line){
        // implement record creation from a line of text
        // ID,Name,Age,Gender,Department,GPA,Grade
        return null;
    }

    public String recordToString(StudentRecord record){
        // implement conversion of a record to a string
        // ID,Name,Age,Gender,Department,GPA,Grade
        return "";
    }

    public boolean contains(int studentId){
        // implement check for existence of a student ID
        return false;
    }

    public void deleteRecord(StudentRecord record){
        // implement record deletion
    }

    public void sortByGPA(){
        // implement sorting by GPA
    }

    public void filterByGPA(int minGPA, int maxGPA){
        // implement filtering by GPA
    }

    public void filterByGPA(int gpa){
        // implement filtering by exact GPA
    }

    public void filterByGrade(String grade){
        // implement filtering by grade
    }

}
