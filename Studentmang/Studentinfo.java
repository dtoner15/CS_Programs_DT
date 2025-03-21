package Studentmang;

public class Studentinfo {
    
    private int id;
    private String name;
    private char letterGrade;

    public void setid(int id){
        this.id =id;
    }

    public int getid(){
        return id;
    }

    public void setname(String name){
        this.name = name;
    }

    public String getname(){
        return name;
    }

    public void setletterGrade(int numericGrade){
        if(numericGrade >= 90){
             this.letterGrade = 'A';
        }else if(numericGrade >= 80){
            this.letterGrade = 'B';
        }else if(numericGrade >= 70){
            this.letterGrade = 'C';
        }else if(numericGrade >=60){
             this.letterGrade = 'D';
        }else if(numericGrade <= 59){
            this.letterGrade = 'F';
        }   
}

    public char getletterGrade(){
        return letterGrade;
    }


}
