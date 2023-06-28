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
