package com.twitter.client.view;

import org.junit.jupiter.api.Test;
import static com.tweetap.client.view.TwitterLog.*;

public class TwitterLogTest
{
    @Test
    void startNestTest()
    {
        startNest();
        println("test");
        println("test");

        startNest();
        println("test");
        endNest();

        println("test");

        endNest();
        println("test");
    }

    @Test
    void printlnNestedTest()
    {
        println("test");
        println("---|");
        printlnNested("test");
        println("---|");
        println("test");
    }
}
