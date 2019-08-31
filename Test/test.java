class a{
    protected int a1;
    a(){
        a1=6;
    }
    public int ret(){
        return a1;
    }
}
class b extends a{
    protected int b1;
    b(){
        b=a+4;
    }
    @Override
    public int ret() {
        return b;
    }
}
class test{
    public static void main(String[] args) {
        a c = new b();
        System.out.print(c.ret());
    }
}