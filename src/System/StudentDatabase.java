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

    //leh da hena.. leh de method ma3ana
    public String recordToString(StudentRecord record){
        return record.lineRepresentation();
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
                studentRecords.remove(record.getStudentId());
        }
    }

    public StudentRecord[] sortByGPA(){
        StudentRecord[] records = studentRecords.toArray(new StudentRecord[0]);
        Arrays.sort(records, Comparator.comparingDouble(StudentRecord::getGPA));

        return records;
    }

    public StudentRecord[] filterByGPA(Double minGPA, Double maxGPA){
        ArrayList<StudentRecord> students  = new ArrayList<>();
        for(StudentRecord record : studentRecords){
            if(record.getGPA() > minGPA && record.getGPA() < maxGPA)
                students.add(record);
        }
        StudentRecord[] records = students.toArray(new StudentRecord[0]);
        return records;
    }

    public StudentRecord[] filterByGPA(int gpa){
        ArrayList<StudentRecord> students  = new ArrayList<>();
        for(StudentRecord record : studentRecords){
            if(record.getGPA() == gpa)
                students.add(record);
        }
        StudentRecord[] records = students.toArray(new StudentRecord[0]);
        return records;
    }

    public void filterByGrade(String grade){
        // implement filtering by grade
    }

}
