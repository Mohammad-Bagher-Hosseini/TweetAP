package com.tweetap.client.controller;

import com.tweetap.client.view.MenuOption;

public class Program
{
    private static Program program;

    private boolean isRunning = false;
    private final MenuOption menuOption;

    private Program()
    {
        menuOption = new MenuOption();
    }

    public static void start()
    {
        if(program == null)
            program = new Program();
        program.cycle();
    }

    public static void stop()
    {
        if(program == null)
            throw new NullPointerException("no program to stop!");
        program.stopInstance();
    }

    private void stopInstance()
    {
        isRunning = false;
    }

    private void cycle()
    {
        isRunning = true;

        while (isRunning)
            loop();
    }

    private void loop()
    {
        menuOption.showOptions();
        try
        {
            menuOption.getCommand();
        }
        catch (IllegalArgumentException e)
        {
            return;
        }
        menuOption.doCommand();
    }
}
