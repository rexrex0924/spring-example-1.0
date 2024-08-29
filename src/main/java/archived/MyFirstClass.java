package archived;

public class MyFirstClass {

    private final String myString;


    public MyFirstClass(String myString) {
        this.myString = myString;
    }

    public String sayHello(){
        return "Hello World from the first class ==> " + myString;
    }
}
