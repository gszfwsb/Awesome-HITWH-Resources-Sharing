public class Test{
    public static void method(A a){
        a.print();
    }
    public static void main(String[] args){
        method(new B());
    }
}

class A{
    int data;
    public A(){
        System.out.println(data);
        add(2);
    }
    public void add(int i){
        data+=i;
    }
    public void print(){
        System.out.println("data"+data);
    }
}

class B extends A{
    public B(){
        System.out.println(data);
        add(1);
    }
    public void add(int i){
        data+=i*3;
    }
}