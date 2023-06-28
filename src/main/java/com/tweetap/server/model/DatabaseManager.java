package com.tweetap.server.model;

import com.tweetap.entities.exception.io.server.*;
import com.tweetap.entities.tweet.LikeRelation;
import com.tweetap.entities.user.BlockRelation;
import com.tweetap.entities.user.User;
import com.tweetap.entities.user.follow.FollowRelation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

public class DatabaseManager
{
    public SessionFactory sessionFactory;

    public DatabaseManager()
    {
        StandardServiceRegistry serviceRegistry;
        Metadata metadata;

        serviceRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();

        sessionFactory = metadata.getSessionFactoryBuilder().build();
    }

    public User findUser(String userName , Session session) throws UserNotFoundException
    {
        Query<User> userQuery = session.createQuery("select u from User u where u.userName = :userName", User.class);
        userQuery.setParameter("userName", userName);
//        User user = userQuery.list().get(0);
//        if(user == null)
//        {
//            throw new DataNotFoundException();
//        }
//        else
//        {
//            return user;
//        }
        try
        {
            return userQuery.list().get(0);
        }
        catch (IndexOutOfBoundsException e)
        {
            throw new UserNotFoundException();
        }
    }
    public FollowRelation isFollowRelationExist(FollowRelation followRelation, Session session) throws FollowRelationNotFoundException
    {
        Query<FollowRelation> followRelationQuery = session.createQuery("select f from FollowRelation f where f.user.userName = :user and f.followedUser.userName = :fuser", FollowRelation.class);
        followRelationQuery.setParameter("user", followRelation.getUser().getUserName());
        followRelationQuery.setParameter("fuser", followRelation.getFollowedUser().getUserName());
        try
        {
            return followRelationQuery.list().get(0);
        }
        catch (IndexOutOfBoundsException e)
        {
            throw new FollowRelationNotFoundException();
        }
    }

    public LikeRelation isLikeRelationExist(LikeRelation likeRelation, Session session) throws LikeRelationNotFoundException
    {
        Query<LikeRelation> likeRelationQuery = session.createQuery("select l from LikeRelation l where l.user.userName = :user and l.tweet.id = :tweet", LikeRelation.class);
        likeRelationQuery.setParameter("user", likeRelation.getUser().getUserName());
        likeRelationQuery.setParameter("tweet", likeRelation.getTweet().getId());
        try
        {
            return likeRelationQuery.list().get(0);
        }
        catch (IndexOutOfBoundsException e)
        {
            throw new LikeRelationNotFoundException();
        }
    }

    public BlockRelation isBlockRelationExist(BlockRelation blockRelation, Session session) throws BlockRelationNotFoundException
    {
        Query<BlockRelation> blockRelationQuery = session.createQuery("select b from BlockRelation b where b.blocker.userName = :user and b.blocked.userName = :buser", BlockRelation.class);
        blockRelationQuery.setParameter("user", blockRelation.getBlocker().getUserName());
        blockRelationQuery.setParameter("buser", blockRelation.getBlocked().getUserName());
        try
        {
            return blockRelationQuery.list().get(0);
        }
        catch (IndexOutOfBoundsException e)
        {
            throw new BlockRelationNotFoundException();
        }
    }
}
