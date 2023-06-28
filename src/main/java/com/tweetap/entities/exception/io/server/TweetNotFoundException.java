package com.tweetap.entities.exception.io.server;

public class TweetNotFoundException extends DataNotFoundException
{
    public TweetNotFoundException(){}
    public TweetNotFoundException(String message){super(message);}
}
