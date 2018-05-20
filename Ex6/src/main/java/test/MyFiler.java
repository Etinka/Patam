package test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class MyFiler {
    public static void writeLevel(String fileName, Level level) throws IOException {
        // write your answer here
        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        // write object to file
        oos.writeObject(level);
        // closing resources
        oos.close();
        fos.close();
    }
}
