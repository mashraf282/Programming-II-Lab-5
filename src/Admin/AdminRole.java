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
        ArrayList<StudentRecord> records = database.getStudentRecords();
        if(records.contains(record.getStudentId()))
            return false;
        else
            return true;
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
        for(StudentRecord student : database.getStudentRecords()){
            if(student.getStudentId() == record.getStudentId()){
                student.setName(record.getName());
                student.setAge(record.getAge());
                student.setGender(record.getGender());
                student.setDepartment(record.getDepartment());
                student.setGPA(record.getGPA());
            }
        }
    }

    public boolean deleteStudentRecord(StudentRecord record) {
        if(database.contains(record.getStudentId())) {
            database.deleteRecord(record);
            return true;
        }
        return false;
    }

    public String[] searchStudentRecord(int studentId) {
        for(StudentRecord record : database.getStudentRecords()){
            if(record.getStudentId() == studentId) {
                String[] student = new String[6];
                student[0] = Integer.toString(record.getStudentId());
                student[1] = record.getName();
                student[2] = Integer.toString(record.getAge());
                student[3] = record.getGender();
                student[4] = record.getDepartment();
                student[5] = Double.toString(record.getGPA());
                return student;
            }
        }
        return null;
    }
//did u mean String[] instead of String[][]
    public String[] searchStudentRecord(String name) {
        for(StudentRecord record : database.getStudentRecords()){
            if(record.getName() == name) {
                String[] student = new String[6];
                student[0] = Integer.toString(record.getStudentId());
                student[1] = record.getName();
                student[2] = Integer.toString(record.getAge());
                student[3] = record.getGender();
                student[4] = record.getDepartment();
                student[5] = Double.toString(record.getGPA());
                return student;
            }
        }
        return null;
    }

    public void logout() {
        database.writeToFile();
    }

}
