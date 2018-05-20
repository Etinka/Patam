package test;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class Level implements Serializable {

    private char[][] data;
    private int cx, cy;

    public char[][] getData() {
        return data;
    }

    public void setData(char[][] data) {
        this.data = data;
    }

    public int getCx() {
        return cx;
    }

    public void setCx(int cx) {
        this.cx = cx;
    }

    public int getCy() {
        return cy;
    }

    public void setCy(int cy) {
        this.cy = cy;
    }
}
