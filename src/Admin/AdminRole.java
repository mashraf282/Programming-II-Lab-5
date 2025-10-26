package Admin;

import System.StudentDatabase;
import System.StudentRecord;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
        if (database.getStudentRecords().contains(record.getStudentId()))
            return false;
        else {
            database.addRecord(record);
            return true;
        }
    }

    public String[][] returnAllStudentsID() {
        database.getStudentRecords().sort(Comparator.comparingInt(StudentRecord::getStudentId));

        return database.toStringArray(database.getStudentRecords());
    }

    public String[][] returnAllStudentsByName() {
        Collections.sort(database.getStudentRecords(), Comparator.comparing(StudentRecord::getName));

        return database.toStringArray(database.getStudentRecords());
    }

    public void updateStudentRecord(StudentRecord record) {
        for (StudentRecord student : database.getStudentRecords()) {
            if (student.getStudentId() == record.getStudentId()) {
                student.setName(record.getName());
                student.setAge(record.getAge());
                student.setGender(record.getGender());
                student.setDepartment(record.getDepartment());
                student.setGPA(record.getGPA());
            }
        }
    }

    public boolean deleteStudentRecord(StudentRecord record) {
        if (database.contains(record.getStudentId())) {
            database.deleteRecord(record);
            return true;
        }
        return false;
    }

    public String[][] searchStudentRecord(int studentId) {
        for (StudentRecord record : database.getStudentRecords()) {
            if (record.getStudentId() == studentId) {
                String[] student = new String[6];
                student[0] = Integer.toString(record.getStudentId());
                student[1] = record.getName();
                student[2] = Integer.toString(record.getAge());
                student[3] = record.getGender();
                student[4] = record.getDepartment();
                student[5] = Double.toString(record.getGPA());
                return new String[][]{student};
            }
        }
        return null;
    }

    public StudentRecord getStudentByID(int studentId) {
        for (StudentRecord record : database.getStudentRecords())
            if (record.getStudentId() == studentId)
                return record;
        return null;
    }

    public String[][] searchStudentRecord(String name) {
        ArrayList<StudentRecord> students = new ArrayList<>();
        for (StudentRecord record : database.getStudentRecords()) {
            if (record.getName().toLowerCase().contains(name.toLowerCase())) {
                students.add(record);
            }
        }
        return database.toStringArray(students);
    }

    public void logout() {
        database.writeToFile();
    }

    public static boolean login(String username, String password) {
        // hardcoded for simplicity
        return username.equals("admin") && password.equals("admin123");
    }

}
