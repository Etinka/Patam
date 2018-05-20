package test;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Message {
    String payload;

    public Message(String payload) {
        this.payload = payload;
    }

    protected Socket createSocket() {
        return new Socket();
    }

    public boolean sendTo(String hostname, int port) throws IOException {
        boolean sent = false;

        Socket socket = createSocket();

//            OutputStream out = socket.getOutputStream();
//
//            out.write(payload);
//        OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
//        osw.write(payload, 0, payload.length());
//        osw.flush();


        PrintWriter out = new PrintWriter(socket.getOutputStream());
        out.print(payload);
        out.flush();
        socket.close();

        sent = true;

        return sent;
    }

    private void readInputAndSend(BufferedReader in, PrintWriter out, String exitStr) throws IOException {
        String line;
        while (!(line = in.readLine()).equals(exitStr)) {
            out.print(line);
            out.flush();
        }
    }
}
