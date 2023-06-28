package com.tweetap.entities.user;

import com.tweetap.entities.image.Avatar;
import com.tweetap.entities.image.Header;
import jakarta.persistence.*;

import java.io.Serializable;


public class MiniUser implements Serializable
{
    protected String userName;

    protected String name;

    protected String family;

    protected Bio bio;
    @OneToOne
    protected Avatar avatar;
    @OneToOne
    protected Header header;


    public MiniUser()
    {
        userName = null;
        name = null;
        family = null;
        bio = null;
        avatar = null;
        header = null;
    }

    public MiniUser(User user)
    {
        userName = user.getUserName();
        name = user.getName();
        family = user.getFamily();
        bio = user.getBio();
        avatar = user.getAvatar();
        header = user.getHeader();
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getFamily()
    {
        return family;
    }

    public void setFamily(String family)
    {
        this.family = family;
    }

    public Bio getBio()
    {
        return bio;
    }

    public void setBio(Bio bio)
    {
        this.bio = bio;
    }

    public Avatar getAvatar()
    {
        return avatar;
    }

    public void setAvatar(Avatar avatar)
    {
        this.avatar = avatar;
    }

    public Header getHeader()
    {
        return header;
    }

    public void setHeader(Header header)
    {
        this.header = header;
    }
}
