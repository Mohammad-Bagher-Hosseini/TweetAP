package com.tweetap.server.model;

import com.tweetap.entities.exception.io.server.ServerInvalidObjectException;

import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;

public class ObjectGetter
{
    public static <T> T getObject(ObjectInputStream objectInputStream, Class<T> clazz) throws ServerInvalidObjectException
    {
        T t;
        try
        {
            t = (T) objectInputStream.readObject();
        } catch (IOException e)
        {
            throw new ServerInvalidObjectException("Invalid object received from client!");
        } catch (ClassNotFoundException e)
        {
            throw new ServerInvalidObjectException("The object received is not user!");
        }

        return t;
    }
}
