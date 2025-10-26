package System;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Arrays;

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
        try{
            File file = new File(this.filePath);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while((line =br.readLine()) != null)
            {
                StudentRecord record = createRecord(line);
                if(record != null)
                    studentRecords.add(record);
            }
            br.close();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeToFile() {
        try{
            File file = new File(filePath);
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for(StudentRecord record : studentRecords)
            {
                bw.write(record.lineRepresentation());
                bw.newLine();
            }
            bw.close();
        }catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public StudentRecord createRecord(String line){
        String[] parts = line.split(",");
        if (parts.length != 6)
            return null;

        int studentID = Integer.parseInt(parts[0]);
        String studentName = parts[1];
        int age = Integer.parseInt(parts[2]);
        String gender = parts[3];
        String department = parts[4];
        Double gpa = Double.parseDouble(parts[5]);

        StudentRecord sr = new StudentRecord(studentID, studentName, age, gender, department, gpa);
        return sr;
    }

    public boolean contains(int studentId){
        for(StudentRecord record : studentRecords) {
            if (record.getStudentId() == studentId)
                return true;
        }
        return false;
    }

    public void deleteRecord(StudentRecord record){
        for(StudentRecord sr : studentRecords){
            if(record.getStudentId() == sr.getStudentId())
                studentRecords.remove(record);
        }
    }

    public void addRecord(StudentRecord record){
        studentRecords.add(record);
    }

    public String[][] sortByGPA(){
        studentRecords.sort(Comparator.comparingDouble(StudentRecord::getGPA));
        return toStringArray(studentRecords);
    }

    public String[][] filterByGPA(Double minGPA, Double maxGPA){
        ArrayList<StudentRecord> students  = new ArrayList<>();
        for(StudentRecord record : studentRecords){
            if(record.getGPA() > minGPA && record.getGPA() < maxGPA)
                students.add(record);
        }
        return toStringArray(students);
    }

    public String[][] filterByGPA(Double gpa){
        ArrayList<StudentRecord> students  = new ArrayList<>();
        for(StudentRecord record : studentRecords){
            if(record.getGPA() == gpa)
                students.add(record);
        }
        return toStringArray(students);
    }

    public String[][] toStringArray(ArrayList<StudentRecord> students) {
        String[][] records = new String[students.size()][6];
        for (int i = 0; i < students.size(); i++) {
            StudentRecord record = students.get(i);
            records[i][0] = Integer.toString(record.getStudentId());
            records[i][1] = record.getName();
            records[i][2] = Integer.toString(record.getAge());
            records[i][3] = record.getGender();
            records[i][4] = record.getDepartment();
            records[i][5] = Double.toString(record.getGPA());
        }
        return records;
    }

    public void filterByGrade(String grade){
        // implement filtering by grade
    }

}
