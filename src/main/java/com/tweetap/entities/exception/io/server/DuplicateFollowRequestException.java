package com.tweetap.entities.exception.io.server;

public class DuplicateFollowRequestException extends DatabaseException
{
    public DuplicateFollowRequestException (){}
    public DuplicateFollowRequestException (String message){super(message);}
}
