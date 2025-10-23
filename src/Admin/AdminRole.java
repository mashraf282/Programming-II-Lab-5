package Admin;

import System.StudentDatabase;
import System.StudentRecord;

public class AdminRole {

    private StudentDatabase database;

    public AdminRole(StudentDatabase database) {
        this.database = database;
        this.database.readFromFile();
    }

    public StudentDatabase getDatabase() {
        return database;
    }

    public void setDatabase(StudentDatabase database) {
        this.database = database;
    }



    public boolean addStudentRecord(StudentRecord record) {
        // return false if not successful
        return false;
    }

    public String[][] returnAllStudentsID() {
        // sort by id
        // ID,Name,Age,Gender,Department,GPA,Grade
        // convert StudentRecord list to String[][]
        return null;
    }

    public String[][] returnAllStudentsByName() {
        // sort by name
        // ID,Name,Age,Gender,Department,GPA,Grade
        return null;
    }

    public void updateStudentRecord(StudentRecord record) {
        // overload depending on which fields to update
    }

    public boolean deleteStudentRecord(StudentRecord record) {
        // return true if successful
        return false;

    }

    public String[] searchStudentRecord(int studentId) {
        // convert StudentRecord to String[]
        return null;
    }

    public String[][] searchStudentRecord(String name) {
        // convert StudentRecord list to String[][]
        return null;
    }

    public void logout() {
        // perform logout operations
    }

}
