package com.tweetap.client.model;

import com.tweetap.entities.exception.io.server.ServerConnectionFailedException;
import com.tweetap.entities.exception.io.server.ServerInvalidObjectException;
import com.tweetap.entities.exception.io.server.ServerRespondFailedException;
import com.tweetap.entities.server.Respond;

import java.io.*;
import java.net.Socket;

public class ServerConnectionHandler implements Closeable
{
    public static final String IP = "localhost";
    public static final int PORT = 5678;
    private final Socket socket;
    private final InputStream inputStream;
    private final OutputStream outputStream;
    private final ObjectInputStream objectInputStream;
    private final ObjectOutputStream objectOutputStream;

    public ServerConnectionHandler() throws ServerConnectionFailedException
    {
        try
        {
            socket = new Socket(IP, PORT);

            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();

            objectOutputStream = new ObjectOutputStream(outputStream);
            objectInputStream = new ObjectInputStream(inputStream);
        }
        catch (IOException e)
        {
            throw new ServerConnectionFailedException(e.getMessage());
        }
    }

    public void sendCommend(String command) throws ServerInvalidObjectException
    {
        try
        {
            objectOutputStream.writeObject(command);
        } catch (IOException e)
        {
            throw new ServerInvalidObjectException(e.getMessage());
        }
    }

    public void sendObject(Object object) throws ServerInvalidObjectException
    {
        try
        {
            objectOutputStream.writeObject(object);
        } catch (IOException e)
        {
            throw new ServerInvalidObjectException(e.getMessage());
        }
    }

    public void sendLargeObject(Object object)
    {
        // sends objects like ImageBuilder via outputStream
//        object.geyBytes();
//        try
//        {
//            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(outputStream);
//            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
//            objectOutputStream.writeObject(object);
//            objectOutputStream.flush();
//            objectOutputStream.close();
//
//            byte[] bytes = byteArrayOutputStream.toByteArray();
//            objectOutputStream.writeInt(bytes.length);
//            objectOutputStream.write(bytes);
//            objectOutputStream.flush();
//        } catch (IOException e)
//        {
//            e.printStackTrace();
//        }
    }

    public void sendFile(File file)
    {
        // TODO: convert the file to byte -> convert the bytes to an encoded string
        // byte[] fileContent = FileUtils
    }

    public Respond getRespond() throws ServerRespondFailedException
    {
        try
        {
            return (Respond) objectInputStream.readObject();
        }
        catch (IOException | ClassNotFoundException e)
        {
            throw new ServerRespondFailedException(e.getMessage());
        }
    }

    @Override
    public void close()
    {
        try
        {
            socket.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
