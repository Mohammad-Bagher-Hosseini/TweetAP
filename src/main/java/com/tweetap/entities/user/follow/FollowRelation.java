package com.tweetap.entities.user.follow;

import com.tweetap.entities.user.User;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "follows")
public class FollowRelation implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToOne
    private final User user;
    @OneToOne
    private final User followedUser;


    public FollowRelation(User user, User followedUser)
    {
        this.user = user;
        this.followedUser = followedUser;
    }

    public FollowRelation()
    {
        this.user = null;
        this.followedUser = null;
    }

    public User getUser()
    {
        return user;
    }

    public User getFollowedUser()
    {
        return followedUser;
    }

//    public boolean equals(FollowRelation followRelation)
//    {
//        if(this.user.equals(followRelation.getUsername()))
//        {
//            return this.followedUsername.equals(followRelation.getFollowedUsername());
//        }
//        return false;
//    }
}
