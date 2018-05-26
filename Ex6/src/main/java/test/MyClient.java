package test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
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

    public static String jaxbObjectToXML(Level level) {
        String xmlString = "";
        try {
            JAXBContext context = JAXBContext.newInstance(Level.class);
            Marshaller m = context.createMarshaller();

            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // To format XML
            // m.marshal(level, System.out);

            StringWriter sw = new StringWriter();
            m.marshal(level, sw);
            xmlString = sw.toString();

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return xmlString;
    }

    public static Level convertStringToLevel(String xml) {
        Level level = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Level.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(xml);
            level = (Level) unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return level;
    }

    private void readInputAndSend(BufferedReader in, PrintWriter out, String exitStr) throws IOException {
        String line;
        while (!(line = in.readLine()).equals(exitStr)) {
            out.println(line);
            out.flush();
        }
    }
}
