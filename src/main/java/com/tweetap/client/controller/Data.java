package com.tweetap.client.controller;

import com.tweetap.client.controller.SettingIO;
import com.tweetap.client.model.ModelCommands;
import com.tweetap.client.view.ProgramState;
import com.tweetap.entities.exception.TwitException;
import com.tweetap.entities.exception.UnknownException;
import com.tweetap.entities.exception.io.server.*;
import com.tweetap.entities.exception.text.TextTooLongException;
import com.tweetap.entities.exception.user.password.InvalidPasswordException;
import com.tweetap.entities.tweet.TimeLine;
import com.tweetap.entities.user.User;

import java.io.Serializable;

public class Data implements Serializable
{
    private static Data data;

    private ProgramState programState;

    private User user;
    private TimeLine timeLine;
    private transient final SettingIO settingIO;

    private Data()
    {
        programState = ProgramState.LOGGED_OUT;
        user = null;
        timeLine = new TimeLine();
        settingIO = new SettingIO(this);
        Data loadedData = settingIO.load();
        if(loadedData != null)
        {
            programState = loadedData.getProgramState();
            user = loadedData.getUser();
            if(programState == ProgramState.MAIN_MENU)
            {
                verifyUser();
            }
            timeLine = loadedData.getTimeLine();
        }
        settingIO.startAutoSave();
    }

    private void verifyUser()
    {
        ModelCommands modelCommands = new ModelCommands();
        try
        {
            user = modelCommands.signIn(user.getUserName(), user.getPassHash());
        } catch (TwitException e)
        {
            programState = ProgramState.LOGGED_OUT;
            user = null;
        }
        save();
    }

    public static Data getInstance()
    {
        if(data == null)
            data = new Data();
        return data;
    }

    public ProgramState getProgramState()
    {
        return programState;
    }

    public void setProgramState(ProgramState programState)
    {
        this.programState = programState;
        save();
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
        save();
    }

    public TimeLine getTimeLine()
    {
        return timeLine;
    }

    public void setTimeLine(TimeLine timeLine)
    {
        this.timeLine = timeLine;
        save();
    }

    private boolean save()
    {
        return settingIO.save();
    }
}
