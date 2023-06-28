package com.tweetap.entities.user.follow;

import com.tweetap.entities.user.MiniUser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ConnectedUsers implements Serializable, Iterable<MiniUser>
{
    ArrayList<MiniUser> miniUsers;

    public ConnectedUsers()
    {
        miniUsers = new ArrayList<>();
    }

    public void add(MiniUser miniUser)
    {
        miniUsers.add(miniUser);
    }

    public void remove(MiniUser miniUser)
    {
        miniUsers.remove(miniUser);
    }

    public MiniUser get(int index)
    {
        return miniUsers.get(index);
    }

    public int size()
    {
        return miniUsers.size();
    }

    public List<String> getUserNames ()
    {
        List<String> userNames = new ArrayList<>();
        for (MiniUser m : miniUsers)
        {
            userNames.add(m.getUserName());
        }
        return userNames;
    }

    @Override
    public Iterator<MiniUser> iterator()
    {
        return miniUsers.iterator();
    }
}
