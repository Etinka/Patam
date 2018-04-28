package test;

public class LevelMainTrain {
    public static void main(String[] args) {
        char[][] data=new char[10][10];
        Level l=new Level(data);
        l.onChange=()->System.out.println("a change!");
        l.changeData(0, 0, 'a'); // output = "a change!"
    }
}
