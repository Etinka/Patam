package test;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

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
        Assert.assertEquals(testString, byteArrayOutputStream.toString());
    }

}