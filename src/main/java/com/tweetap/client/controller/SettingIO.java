package com.tweetap.client.controller;

import java.io.*;

public class SettingIO
{
    private final Data data;
    private static final String FILE_NAME = "settings.bin";
    private static final String FOLDER_PATH = "config";
    private static final String FILE_PATH = FOLDER_PATH + "/" + FILE_NAME;
    private static final int AUTO_SAVE_INTERVAL = 1000 * 60 * 2;

    public SettingIO(Data data)
    {
        this.data = data;
    }

    public Data load()
    {
        try(FileInputStream fileInputStream = new FileInputStream(FILE_PATH);
            ObjectInputStream in = new ObjectInputStream(fileInputStream))
        {
            return (Data) in.readObject();
        }
        catch (IOException | ClassNotFoundException e)
        {
            return null;
        }
    }

    public void startAutoSave()
    {
        new Thread(() ->
        {
            while(true)
            {
                try
                {
                    Thread.sleep(AUTO_SAVE_INTERVAL);
                    save();
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * save the data in the FILE_PATH file
     * @return true if the save was successful, false otherwise
     */
    public boolean save()
    {
        // delete the old file

        File file = new File(FILE_PATH);
        if(file.exists())
            if(!file.delete())
                return false;

        // create a new file
        try
        {
            file = new File(FOLDER_PATH);
            file.mkdirs();
            file = new File(FILE_PATH);
            if(!file.createNewFile())
                return false;
        } catch (IOException e)
        {
            return false;
        }

        try(FileOutputStream fileOutputStream = new FileOutputStream(FILE_PATH);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream))
        {
            objectOutputStream.writeObject(data);
        }
        catch (IOException e)
        {
            return false;
        }

        return true;
    }
}
