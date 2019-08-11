import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Hashtable;
import java.util.Set;
import java.util.Comparator;
class Student{

    private static int count=0;
    private static int placedStudents=0;
    private static int unplacedStudents=0;
    private float cgpa;
    private String branch;
    private int rollno;
    private Hashtable<String,Float> technicalMarks = new Hashtable<String,Float>(); 
    private boolean placed;
    private String companyEnrolled;

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
            stud.set(stud.indexOf(temp), null);
            --Student.count;
            --Student.placedStudents;
            
        } 
    }
    //-----public functions------
    public boolean isPlaced(){
        return this.placed;
    }
    public void placeMe(String company){
        this.placed=true;
        this.companyEnrolled = company;
        Student.placedStudents+=1;
        Student.unplacedStudents-=1;
    }
    public int getRollno(){
        return this.rollno;
    }
    public String getBranch(){
        return this.branch;
    }
    public float getCgpa(){
        return this.cgpa;
    }
    public Hashtable<String,Float> getTechnicalMarks(){
        return this.technicalMarks;
    }
    public void companyAndMarks(){
        if(!this.technicalMarks.isEmpty()){
            System.out.println("Technical round marks: ");
            Set<String> keys = technicalMarks.keySet();
            Iterator<String> itr= keys.iterator();
            while (itr.hasNext()) {
                String k = itr.next();
                System.out.println("Company: " + k + "  Score: " + technicalMarks.get(k));
            } 
        }
    }
    public void printDetails(){
        System.out.println("Roll no: " + this.rollno);
        System.out.println("CGPA: " + this.cgpa);
        System.out.println("Course: "+ this.branch);
        System.out.println("Is placed? : "+ this.placed);
        this.companyAndMarks();
        if(this.placed){
            System.out.println("Company placed in: "+ this.companyEnrolled);
        }
        
    }
   public void addMarks(String compName, float marks){
        this.technicalMarks.put(compName,marks);
   }

}
class TechnicalMarks{
    private int rollNo;
    private float marks;
    private float cgpa;

    TechnicalMarks(int rno, float marks, float cgpa){
        this.rollNo = rno;
        this.marks = marks;
        this.cgpa = cgpa;
    }
    public float getMarks(){
        return this.marks;
    }
    public float getCgpa(){
        return this.cgpa;
    }
    public int getRollno(){
        return rollNo;
    }

}
class SortbyMarks implements Comparator<TechnicalMarks> 
{ 
  
    public int compare(TechnicalMarks a, TechnicalMarks b) 
    { 
        if(a.getMarks()>b.getMarks()){
            return -1;
        } 
        else if(a.getMarks()<b.getMarks()){
            return 1;
        }
        else{
            if(a.getCgpa()>b.getCgpa()){
                return -1;
            } 
            else if(a.getCgpa()<b.getCgpa()){
                return 1;
            }
        }
        return -1;
    } 
} 
class Company{
    private static int count =0;

    private String name;
    private String[] courseCriterion;
    private int numStudents;
    private boolean applicationOpen;

    Company(String name,String[] courseCriterion, int nOfStudents){
        this.name = name;
        this.courseCriterion = courseCriterion;
        this.numStudents = nOfStudents;
        applicationOpen=true;
        count+=1;
    }
    //----------Static functions---------
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
        System.out.println("Company name: " + this.name);
        System.out.println("Courses: ");
        for(int i=0; i<this.courseCriterion.length; ++i){
            System.out.println("\t"+courseCriterion[i]);
        }

        System.out.println("Number of students req: " + this.numStudents);
        if(this.applicationOpen){
            System.out.println("The applications are OPEN");
        }
        else{
            System.out.println("The applications are CLOSED");
        }

    }
    public static int totalCompanies(){
        return count;
    }
    public static void openCompanies(ArrayList<Company> comp){
        Iterator<Company> itr = comp.iterator();
        while(itr.hasNext()){  
            Company temp = itr.next();
            if(temp.isApplicationOpen()){
                System.out.println(temp.getName());
            }
        } 
    }

    //---------non-static functions-------
    public void giveMarks(ArrayList<Student> studs) throws IOException{
        Iterator<Student> itr = studs.iterator();
        while(itr.hasNext()){
            Student temp = itr.next();
            boolean eligible = false;
            for(int i=0;i<this.courseCriterion.length;++i){
                if(this.courseCriterion[i].equals(temp.getBranch())){
                    eligible=true;
                    break;           
                }
            }
            if(eligible){
                System.out.print("Enter the score of technical round of roll no. " + temp.getRollno() + " for company "+ this.name+": ");
                String[] s = program.br.readLine().trim().split("\\s+");
                float marks = Float.parseFloat(s[0]);
                temp.addMarks(this.getName(), marks);
            }
            
        }
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
    public void placeStudents(ArrayList<Student> studs){
        
        ArrayList<TechnicalMarks> toSelect = new ArrayList<TechnicalMarks>();
        Iterator<Student> itr = studs.iterator();
        
        while (itr.hasNext()) {
            Student temp = itr.next();
            if(temp.getTechnicalMarks().containsKey(this.name)){
                int rno = temp.getRollno();
                float marks = temp.getTechnicalMarks().get(this.name);
                float cgpa = temp.getCgpa();
                TechnicalMarks eligible = new TechnicalMarks(rno, marks, cgpa);
                toSelect.add(eligible);

            }
        }
        toSelect.sort(new SortbyMarks());
        for(int i=0; i<this.numStudents && i<toSelect.size();++i){

            TechnicalMarks s = toSelect.get(i);
            studs.get(s.getRollno()).placeMe(this.name);
            System.out.println("Roll no: "+s.getRollno()+" is placed in "+this.name);
            

        }
    this.applicationOpen=false;
        
        
    }
}

class program{
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
    public static void main(String[] args) throws IOException{
            System.out.println("[Roll numbers start from 0]");        
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
            // System.out.println(Student.totalSudents());


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
                        Ctemp.printDetails();
                        Ctemp.giveMarks(allStudents);
                        companies.add(Ctemp);
                        
                        break;
                    case 2:
                        System.out.println("Deleting placed students: ");
                        Student.delPlacedStudents(allStudents);
                        break;
                    case 3:
                        System.out.println("Deleting closed companies: ");
                        Company.delFullCompanies(companies);
                        break;
                    case 4:
                        System.out.println("Number of unplaced students: " + Student.getUnplacedStudents());
                        break;
                    case 5:
                        System.out.println("Open companies: ");
                        Company.openCompanies(companies);
                        break;
                    case 6:
                        String compName = s[1];
                        Iterator<Company> itrC = companies.iterator();
                        while(itrC.hasNext()){
                            Company tmp= itrC.next();
                            if(tmp.getName().equals(compName)){
                                if(tmp.isApplicationOpen())
                                    tmp.placeStudents(allStudents);
                                else
                                    System.out.println("Company not open");
                                break;
                            }
                        }
                        break;
                    case 7:
                        String compDetName = s[1];
                        
                        try {
                            Iterator<Company> itrCN = companies.iterator();
                            while(itrCN.hasNext()){
                                Company temp = itrCN.next();
                                if(temp.getName().equals(compDetName)){
                                    temp.printDetails();
                                    break;
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid input");
                        }
                        break;
                    case 8:
                        int rNoDet = Integer.parseInt(s[1]);
                        try {
                            Student toPrint = allStudents.get(rNoDet);
                            toPrint.printDetails();
                        } catch (Exception e) {
                            System.out.println("Invalid input");                            
                        }
                        break;
                    case 9:
                        int rnoCom = Integer.parseInt(s[1]);
                        try{
                            Student tempS = allStudents.get(rnoCom);
                            if(tempS==null){
                                System.out.println("Roll no not found");
                            }
                            else{
                                tempS.companyAndMarks();
                            }
                        }
                        catch(Exception e){
                            System.out.println("Roll no not found");
                        }
                        break;
                   default:
                        System.out.println("Wrong input");
                }
            }
      
    }
    
}
