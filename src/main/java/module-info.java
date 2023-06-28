module com.tweetap {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires org.jetbrains.annotations;
    requires java.desktop;
    requires java.naming;
    requires de.jensd.fx.glyphs.commons;
    requires de.jensd.fx.glyphs.fontawesome;


    opens com.tweetap to javafx.fxml;
    exports com.tweetap;
    opens com.tweetap.entities.user;
    opens com.tweetap.entities.user.follow;
    opens com.tweetap.entities.tweet;
    opens com.tweetap.entities.tweet.content;
    opens com.tweetap.entities.tweet.content.hashtag;
    opens com.tweetap.entities.exception.io;
    opens com.tweetap.entities.exception.io.server;
    opens com.tweetap.entities.exception.user;
    opens com.tweetap.entities.exception.user.password;
    opens com.tweetap.entities.exception.user.email;
    opens com.tweetap.entities.exception.text;
    opens com.tweetap.entities.exception.hashtag;
    opens com.tweetap.entities.image;
    opens com.tweetap.entities.server;
    opens com.tweetap.server;
    opens com.tweetap.server.model;
    opens com.tweetap.client.model;
    opens com.tweetap.client.controller;
    opens com.tweetap.client.view;

}