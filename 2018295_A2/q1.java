class Student{
    private String name;
    private int age;
    private long rollno;
    private String branch;
    static long latestRno = 0;
    Student(){
        Student.latestRno+=1;
        this.rollno=Student.latestRno;
    }
    Student(String name, int age, String branch){
        Student.latestRno+=1;
        this.rollno=Student.latestRno;
        this.name = name;
        this.age=age;
        this.branch=branch;
    }

    public void printData(){
        System.out.println("name: "+name);
        System.out.println("age: "+age);
        System.out.println("branch: "+branch);
        System.out.println("roll no: "+rollno);
    }
    static void display(Student[] lStudents, int len){
        for(int i=0; i< len; ++i){
            lStudents[i].printData();
        }
    }

}
class q1{
    public static void main(String[] args) {
        Student[] ls = new Student[10];
        for(int i =0; i<5;++i){
            ls[i] = new Student();
        }
        ls[5]=new Student("stud5",20,"CSE");
        ls[6]=new Student("stud6",20,"CSD");
        ls[7]=new Student("stud7",20,"CSB");
        ls[8]=new Student("stud8",20,"CSSS");
        ls[9]=new Student("stud5",20,"ECE");
    
        Student.display(ls, 10);
    
        System.out.println("Latest roll no: "+Student.latestRno);
    }

}