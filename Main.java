import java.util.Iterator;

public class Main {


    public static void main(String[] args){
        MyALDAList<String> list = new MyALDAList<>();


        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");
        list.add("F");

        Iterator<String> iter = list.iterator();
        System.out.println(list + "2");
    }
}
