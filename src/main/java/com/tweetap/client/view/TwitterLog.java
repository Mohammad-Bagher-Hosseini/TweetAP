package com.tweetap.client.view;

import java.util.Scanner;

public class TwitterLog
{
    public static boolean showLog = true;
    public static Scanner scanner;
    private static int nestedCount = 0;

    private TwitterLog()
    {
    }

    private static void scannerInit()
    {
        if(scanner == null)
            scanner = new Scanner(System.in);
    }

    public static void println(String str)
    {
        if(showLog)
            System.out.println(nest(str));
    }

    public static void print(String str)
    {
        if(showLog)
            System.out.print(nest(str));
    }

    public static void printlnError(String str)
    {
        if(showLog)
        {
            System.err.println(nest(str));
            try
            {
                Thread.sleep(100);
            } catch (InterruptedException ignored){}
        }
    }

    public static void printError(String str)
    {
        if(showLog)
        {
            System.err.print(nest(str));
            try
            {
                Thread.sleep(100);
            } catch (InterruptedException ignored){}
        }
    }

    public static String nextLine()
    {
        scannerInit();
        return scanner.nextLine();
    }

    public static String nextLine(String str)
    {
        print(str);
        return nextLine();
    }

    /**
     * Nest the incoming strings
     */
    public static void startNest()
    {
        nestedCount++;
    }

    /**
     * Un-nest the incoming strings
     */
    public static void endNest()
    {
        nestedCount--;
    }

    /**
     * Nest the string with tabs
     * @param str : The string we want to nest
     * @return : The nested string
     */
    private static String nest(String str)
    {
        char[] chars = str.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < chars.length; i++)
        {
            char c = chars[i];

            if(i == chars.length - 1)
            {
                stringBuilder.append(c);
                break;
            }
            if(i == 0)
                stringBuilder.append("\t".repeat(Math.max(0, nestedCount)));

            if(c == '\n')
                stringBuilder.append(c).append("\t".repeat(Math.max(0, nestedCount)));
            else
                stringBuilder.append(c);
        }

        return stringBuilder.toString();
    }

    public static void printlnNested(String str)
    {
        startNest();
        println(str);
        endNest();
    }
}
