package test;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MyClientTest {
    @Test
    public void testSimplePayload() throws IOException {
        byte[] emptyPayload = new byte[1001];

        // Using Mockito
        final Socket socket = mock(Socket.class);
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        when(socket.getOutputStream()).thenReturn(byteArrayOutputStream);
        String testString = "testing payload";
        Message text = new Message(testString) {
            @Override
            protected Socket createSocket() {
                return socket;
            }
        };

        Assert.assertTrue("Message sent successfully", text.sendTo("localhost", 1234));
        assertEquals(testString, byteArrayOutputStream.toString());
    }

    @Test
    public void testXmlConvert() {
        Level l = new Level();
        l.setCx(15);
        l.setCy(23);
        char[][] data = {{1, 1, 1}, {2, 2, 2}, {3, 3, 3}};
        l.setData(data);

        String xml = MyClient.jaxbObjectToXML(l);

        Level l2 = MyClient.convertStringToLevel(xml);
        assertEquals(15, l2.getCx());
        assertEquals(23, l2.getCy());
        assertEquals(data, l2.getData());

    }

}