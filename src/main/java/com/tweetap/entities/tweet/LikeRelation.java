package com.tweetap.entities.tweet;

import com.tweetap.entities.user.User;
import jakarta.persistence.*;

@Entity
@Table(name = "like_relations")
public class LikeRelation
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private final User user;
    @OneToOne
    private final Tweet tweet;

    public Long getId()
    {
        return id;
    }

    public LikeRelation(User user, Tweet tweet)
    {
        this.user = user;
        this.tweet = tweet;
    }

    public LikeRelation()
    {
        user = null;
        tweet = null;
    }

    public User getUser()
    {
        return user;
    }

    public Tweet getTweet()
    {
        return tweet;
    }
}
