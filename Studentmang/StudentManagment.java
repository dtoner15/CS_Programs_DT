package Studentmang;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentManagment {

  //Creates a variable from an arraylist that can store an unlimited amount of names with no limit.
  //Is declared outside of class to be available to entire class
  //Is static so that methods can use it. If it was declared static in main method it wouldn't work
  //because 
  static ArrayList<Studentinfo> students = new ArrayList<>();
    public static void main(String[] args) {
       
        
        Scanner sc = new Scanner(System.in);
        int choice;

        loadStudentFiles();
        do{
            menu();
            choice = sc.nextInt();
            sc.nextLine();

            switch(choice){
            case 1:
                addStudent(sc);
               
               break;
            case 2:
                displayStudent();
               
               break;
            case 3:
               System.out.println("Exiting... ");
               break;
               default:
               System.out.println("Invalid choice, try again");
            }
        }while(choice != 3);
        sc.close();
    
    }

    public static void menu(){
        System.out.printf("----Student Mangment System----%n");
        System.out.printf("1. Add new student%n");
        System.out.printf("2. Display all students and information%n");
        System.out.printf("3. Exit%n");
        System.out.printf("Enter your choice%n");
    }

    public static void addStudent(Scanner sc){

    
        Studentinfo student = new Studentinfo();

        System.out.printf("Enter students name:%n");
        String names = sc.nextLine();
        student.setname(names);

        System.out.printf("Enter numeric grade:%n");
        int grade = sc.nextInt();
        student.setletterGrade(grade);

        students.add(student);
        System.out.println("Student added succesfully");
        saveStudentFiles();

    }

    public static void displayStudent(){
        for(int i = 0; i < students.size(); i++){
            Studentinfo s = students.get(i);
            System.out.println("Name: " + s.getname() + ", Grade: " + s.getletterGrade());

    }
 }

 public static void saveStudentFiles(){
    File file = new File("C:/Users/derri/OneDrive/Documents/Java Practice/Studentmang/Students.txt");

   try(PrintWriter writer = new PrintWriter(new FileWriter(file))){
           
           for(Studentinfo sr : students){
            writer.printf("%s,%c%n", sr.getname(), sr.getletterGrade());
            System.out.println("Student files succesfully saved.");
           }
       }catch(IOException e){
              System.out.printf("Error while catching the file, please try again.", e.getMessage());
       }
    }
    
    public static void loadStudentFiles(){
      File file = new File("C:/Users/derri/OneDrive/Documents/Java Practice/Studentmang/Students.txt");

      try(BufferedReader reader = new BufferedReader(new FileReader(file))){
        String line;
        while((line = reader.readLine()) != null){

            //splits each line with a comma "," then returns an array where the first 
            //part is the name and the second part is the letter grade.
            String[] data  = line.split(",");

            String name = data[0];
            char grade = data[1].charAt(0);// converts string to char

            //create the studentinfo objects to intialize into array.
            Studentinfo student = new Studentinfo();
            student.setname(name);
            student.setletterGrade(grade);

            //adds student into list
            students.add(student);

        }
      }catch(IOException e){
        System.out.printf("Error while loading file. %s%n", e.getMessage());
      }
    }

 
}

