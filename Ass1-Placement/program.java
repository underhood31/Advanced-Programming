import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

class TechnicalMarks{
    String company;
    float marks;
}
class Student{
    private static int count=0;
    private static int placedStudents=0;
    private static int unplacedStudents=0;
    private float cgpa;
    private String branch;
    private int rollno;
    private TechnicalMarks[] technicalMarks = new TechnicalMarks[1000];
    private boolean placed;
    Student(float cgpa,String branch){
        this.rollno = count++;
        unplacedStudents=unplacedStudents+1;
        this.cgpa = cgpa;
        this.branch = branch;
        this.placed = false;
    } 
    //------Static functions
    public static int totalSudents(){
        return count;
    }
    public static int getPlacedStudents(){
        return placedStudents;
    }
    public static int getUnplacedStudents(){
        return unplacedStudents;
    }
   
    public static void delPlacedStudents(ArrayList<Student> stud){
   
        ArrayList<Student> toDel= new ArrayList<Student>();

        Iterator<Student> itr=stud.iterator();  
        
        while(itr.hasNext()){  
            Student temp = itr.next();
            if(temp.isPlaced()){
                toDel.add(temp);
            }
        }  
        Iterator<Student> itr2 = toDel.iterator();
        while(itr2.hasNext()){  
            Student temp = itr2.next();
            
            System.out.println(temp.getRollno());
            stud.remove(stud.indexOf(temp));
            --Student.count;
            --Student.placedStudents;
            
        } 
    }
    //-----public functions------
    public boolean isPlaced(){
        return this.placed;
    }
    public void placeMe(){
        this.placed=true;
        Student.placedStudents+=1;
        Student.unplacedStudents-=1;
    }
    public int getRollno(){
        return this.rollno;
    }
   

}

class Company{
    private static int count =0;

    String name;
    String[] courseCriterion;
    int numStudents;
    boolean applicationOpen;
    Student[] selected = new Student[1000];

    Company(String name,String[] courseCriterion, int nOfStudents){
        this.name = name;
        this.courseCriterion = courseCriterion;
        this.numStudents = nOfStudents;
        applicationOpen=true;
        count+=1;
    }
    //----------Static functions
    public static void delFullCompanies(ArrayList<Company> compn){
   
        ArrayList<Company> toDel= new ArrayList<Company>();

        Iterator<Company> itr=compn.iterator();  
        while(itr.hasNext()){  
            Company temp = itr.next();
            if(!temp.isApplicationOpen()){
                toDel.add(temp);
            }
        }  
        Iterator<Company> itr2 = toDel.iterator();
        while(itr2.hasNext()){
            Company temp=itr2.next();
            String name = temp.getName();
            System.out.println(name);
            compn.remove(compn.indexOf(temp));
            --Company.count;
        }
    }

    public void printDetails(){
        
    }
    public static int totalCompanies(){
        return count;
    }
    private void selectStudents(){

    }
    public boolean isApplicationOpen() {
        return this.applicationOpen;
    }
    public String getName(){
        return this.name;
    }
    public void complete(){
        this.applicationOpen=false;
    }
}

class program{
    public static void main(String[] args) throws IOException{
        // try {
        
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String s[] = br.readLine().trim().split("\\s+") ;
            int nOfStudents = Integer.parseInt(s[0]);
            
            // Student allStudents[] = new Student[100];
            // Company companies[] = new Company[100];

            ArrayList<Student> allStudents = new ArrayList<Student>();
            ArrayList<Company> companies = new ArrayList<Company>();

            for(int i = 0; i<nOfStudents; ++i){
                s = br.readLine().trim().split("\\s+");
                float cgpa = Float.parseFloat(s[0]);
                String branch = s[1];
                allStudents.add(new Student(cgpa,branch));
            }
            System.out.println(Student.totalSudents());


            while(Student.getUnplacedStudents()>0){
                s = br.readLine().trim().split("\\s+");
                int command = Integer.parseInt(s[0]);
                switch(command){
                    case 1:
                        s = br.readLine().trim().split("\\s+");
                        String Cname = s[0];
                        System.out.print("Enter number of eligible courses: ");
                        s = br.readLine().trim().split("\\s+");
                        int numOfCourses = Integer.parseInt(s[0]);
                        String[] courses = new String[numOfCourses];
                        for(int co=0; co<numOfCourses; ++co){
                            s = br.readLine().trim().split("\\s+");
                            courses[co] = s[0];                            
                        }
                        System.out.print("Students required: ");
                        s = br.readLine().trim().split("\\s+");
                        int CnumOfstudents = Integer.parseInt(s[0]);
                        Company Ctemp = new Company(Cname, courses, CnumOfstudents);
                        companies.add(Ctemp);
                        Ctemp.printDetails();
                        break;
                    case 2:
                        Student Stemp = allStudents.get(0);
                        Stemp.placeMe();
                        allStudents.set(0,Stemp);
                        Student.delPlacedStudents(allStudents);
                        break;
                    case 3:
                        Company Cotemp = companies.get(0);
                        Cotemp.complete();
                        companies.set(0, Cotemp);
                        Company.delFullCompanies(companies);
                        break;
                    case 4:
                        //code
                        break;
                    case 5:
                        //code
                        break;
                    case 6:
                        //code
                        break;
                    case 7:
                        //code
                        break;
                    case 8:
                        //code
                        break;
                    case 9:
                        //code
                        break;
                   default:
                        System.out.println("Wrong input");
                }
            }
        // }    
        // catch (Exception e) {
            // System.out.println("Error:" +e.toString());    
        // }
    }
    
}
