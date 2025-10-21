package Admin;

import Student.StudentDatabase;
import Student.StudentRecord;

import java.util.ArrayList;

public class AdminRole {

    private StudentDatabase database;

    public AdminRole(StudentDatabase database) {
        this.database = database;
        this.database.readFromFile();
    }

    public boolean addStudentRecord(StudentRecord record) {
        // return false if not successful
        return false;
    }

    public ArrayList<StudentRecord> returnAllStudents() {
        // sort by name/id
        return null;
    }

    public void updateStudentRecord(StudentRecord record) {
        // overload depending on which fields to update
    }

    public boolean deleteStudentRecord(StudentRecord record) {
        // return true if successful
        return false;

    }

    public StudentRecord searchStudentRecord(int studentId) {
        return null;
    }

    public ArrayList<StudentRecord> searchStudentRecord(String name) {
        return null;
    }

    public void logout() {
        // perform logout operations
    }

}
