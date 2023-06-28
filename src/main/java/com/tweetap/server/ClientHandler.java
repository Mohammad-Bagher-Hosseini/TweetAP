package com.tweetap.server;

import com.tweetap.entities.exception.io.server.ServerInvalidObjectException;
import com.tweetap.entities.server.Respond;
import com.tweetap.server.model.ModelCommandHandler;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable
{
    private final Socket connectionSocket;
    private final ModelCommandHandler modelCommandHandler;

    public ClientHandler(Socket connectionSocket)
    {
        this.connectionSocket = connectionSocket;
        modelCommandHandler = new ModelCommandHandler();
    }

    @Override
    public void run()
    {
        try
        {
            handleClient();
        } catch (IOException e)
        {
            System.out.println("Error in handling client!");
        } catch (ClassNotFoundException e)
        {
            System.out.println("Command not found!");
        } catch (ServerInvalidObjectException e)
        {
            System.out.println("Invalid object!");
        } catch(Exception e)
        {
            e.printStackTrace();
        }

        try
        {
            connectionSocket.close();
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void handleClient() throws IOException, ClassNotFoundException, ServerInvalidObjectException
    {
        InputStream inputStream = connectionSocket.getInputStream();
        OutputStream outputStream = connectionSocket.getOutputStream();

        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

        String command = (String) objectInputStream.readObject();
        modelCommandHandler.setCommand(command);
        Respond respond = modelCommandHandler.runCommand(objectInputStream);


        respond.send(objectOutputStream);
    }
}
