package com.tweetap.entities.exception.io.server;

public class ServerInvalidCommandException extends ServerException
{
public ServerInvalidCommandException()
    {
        super();
    }

    public ServerInvalidCommandException(String message)
    {
        super(message);
    }
}
