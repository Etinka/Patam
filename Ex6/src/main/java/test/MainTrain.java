package test;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.HashSet;

public class MainTrain {


    static String res = "";


    public static void q1() {

        testSerializable();
    }


    private static void testSerializable() {
        if (Level.class.getInterfaces().length == 0 || Level.class.getInterfaces().length > 1 || !Level.class.getInterfaces()[0].equals(Serializable.class)) {
            System.out.println("Level is not Serializable (-20)");
            return;
        }
        Method[] methods = Level.class.getMethods();
        HashSet<String> required = new HashSet<String>();
        required.add("getData");
        required.add("getCx");
        required.add("getCy");
        required.add("setCx");
        required.add("setCy");
        for (Method m : methods)
            required.remove(m.getName());
        if (!required.isEmpty())
            System.out.println("Level is not properly Serializable (-20)");
    }


    public static void q2() throws Exception {
        Level l = new Level();
        l.setCx(15);
        l.setCy(23);
        char[][] data = {{1, 1, 1}, {0, 0, 0}, {1, 0, 1}};
        l.setData(data);
        MyFiler.writeLevel("l1.level", l);
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("l1.level"));
        Level l2 = (Level) in.readObject();
        in.close();
        if (l2 == null || l2.getData() == null || l.getCx() != l2.getCx() || l.getCy() != l2.getCy() || l.getData()[2][0] != l2.getData()[2][0])
            System.out.println("Level is not saved to file correctly (-20)");

        MyClient.sendLevel("127.0.0.1", 444, l);

    }


    public static void main(String[] args) throws Exception {
        q1();
        q2();
        System.out.println("done");
    }

}
