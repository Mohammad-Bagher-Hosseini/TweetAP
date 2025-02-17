package com.tweetap.entities.tweet;

import com.tweetap.entities.exception.UnknownException;
import com.tweetap.entities.user.MiniUser;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "base_tweets")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public abstract class BaseTweet implements Serializable
{
    // this is automatically generated by the database
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private final String userName;
    private final LocalDateTime date;
    @Transient
    private MiniUser owner;

    public BaseTweet(LocalDateTime date, MiniUser owner)
    {
        this.date = date;
        this.owner = owner;
        this.userName = owner.getUserName();
    }

    public BaseTweet(MiniUser owner)
    {
        this(LocalDateTime.now(), owner);
    }

    public BaseTweet()
    {
        this.date = null;
        this.owner = null;
        this.userName = null;
    }

    public long getId()
    {
        return id;
    }

    // this is the date the tweet was created
    public LocalDateTime getDate()
    {
        return date;
    }

    public Tweet toTweet() throws UnknownException
    {
        if(this instanceof Tweet)
            return (Tweet) this;
        else if(this instanceof Retweet)
            return ((Retweet) this).getTweet();
        else
            throw new UnknownException("Unknown tweet type");
    }

    public MiniUser getOwner()
    {
        return owner;
    }

    public void setOwner(MiniUser owner)
    {
        this.owner = owner;
    }

    public String getUserName()
    {
        return userName;
    }
}
