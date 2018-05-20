package test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.net.Socket;

public class MyClient {
    public static void sendLevel(String ip, int port, Level level) throws IOException {
        // write your answer here
        String levelXml = jaxbObjectToXML(level);
        Socket socket = new Socket(ip, port);
        PrintWriter out = new PrintWriter(socket.getOutputStream());
        out.print(levelXml);
        out.flush();
        socket.close();
    }

    private static String jaxbObjectToXML(Level level) {
        String xmlString = "";
        try {
            JAXBContext context = JAXBContext.newInstance(Level.class);
            Marshaller m = context.createMarshaller();

            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // To format XML

            StringWriter sw = new StringWriter();
            m.marshal(level, sw);
            xmlString = sw.toString();

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return xmlString;
    }

    private void readInputAndSend(BufferedReader in, PrintWriter out, String exitStr) throws IOException {
        String line;
        while (!(line = in.readLine()).equals(exitStr)) {
            out.println(line);
            out.flush();
        }
    }
}
