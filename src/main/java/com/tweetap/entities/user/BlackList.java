package com.tweetap.entities.user;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class BlackList implements Serializable, Iterable<MiniUser>
{
    private final ArrayList<MiniUser> blockedUsers;

    public BlackList()
    {
        blockedUsers = new ArrayList<>();
    }

    public BlackList(ArrayList<MiniUser> blockedUsers)
    {
        this.blockedUsers = blockedUsers;
    }

    public void add(MiniUser blockedUser)
    {
        blockedUsers.add(blockedUser);
    }

    public void remove(MiniUser miniUser)
    {
        blockedUsers.remove(miniUser);
    }

    public boolean contains(String username)
    {
        for(MiniUser miniUser : blockedUsers)
            if(miniUser.getUserName().equals(username))
                return true;

        return false;
    }

    @NotNull
    @Override
    public Iterator<MiniUser> iterator()
    {
        return blockedUsers.iterator();
    }

    public int size()
    {
        return blockedUsers.size();
    }
}
