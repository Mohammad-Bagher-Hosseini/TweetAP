package com.tweetap.server.model;

import com.tweetap.entities.exception.UnknownException;
import com.tweetap.entities.exception.io.server.*;
import com.tweetap.entities.exception.text.TextTooLongException;
import com.tweetap.entities.exception.user.CountryException;
import com.tweetap.entities.exception.user.email.EmailFormatException;
import com.tweetap.entities.exception.user.password.InvalidPasswordException;
import com.tweetap.entities.image.Avatar;
import com.tweetap.entities.image.Header;
import com.tweetap.entities.tweet.*;
import com.tweetap.entities.tweet.content.hashtag.Hashtag;
import com.tweetap.entities.user.*;
import com.tweetap.entities.user.follow.FollowRelation;
import com.tweetap.entities.user.follow.Followers;
import com.tweetap.entities.user.follow.Followings;
import jakarta.persistence.PersistenceException;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DatabaseCommands
{
    private final DatabaseManager databaseManager;
    public DatabaseCommands()
    {
        databaseManager = new DatabaseManager();
    }

    public void signUp(User user) throws DuplicateUserNameException
    {
        try(Session session = databaseManager.sessionFactory.openSession())
        {
            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();
        } catch (PersistenceException e){
            throw new DuplicateUserNameException();
        }
    }
    public User signIn (String userName, Password passwordHash) throws InvalidPasswordException, UserNotFoundException
    {
        Session session = databaseManager.sessionFactory.openSession();
        User user = databaseManager.findUser(userName, session);
        if(!user.getPassHash().equals(passwordHash))
        {
            throw new InvalidPasswordException();
        }
        return user;
    }

    public void setAvatar(String userName, Avatar avatar) throws UserNotFoundException
    {
        Session session = databaseManager.sessionFactory.openSession();
        User user = databaseManager.findUser(userName, session);
        user.setAvatar(avatar);
        session.beginTransaction();
        session.persist(avatar);
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }

    public void setHeader(String userName, Header header) throws UserNotFoundException
    {
        Session session = databaseManager.sessionFactory.openSession();
        User user = databaseManager.findUser(userName, session);
        user.setHeader(header);
        session.beginTransaction();
        session.persist(header);
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }

    public void changeUserPassword(String userName, Password newpass) throws UserNotFoundException
    {
        Session session = databaseManager.sessionFactory.openSession();
        User user = databaseManager.findUser(userName, session);
        user.setPassword(newpass);
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }

    public void changeName(String userName, String name) throws UserNotFoundException
    {
        Session session = databaseManager.sessionFactory.openSession();
        User user = databaseManager.findUser(userName, session);
        user.setName(name);
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }
    public void changeFamily(String userName, String family) throws UserNotFoundException
    {
        Session session = databaseManager.sessionFactory.openSession();
        User user = databaseManager.findUser(userName, session);
        user.setFamily(family);
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }
    public void changeEmail(String userName, String email) throws UserNotFoundException, EmailFormatException
    {
        Session session = databaseManager.sessionFactory.openSession();
        User user = databaseManager.findUser(userName, session);
        user.setEmail(email);
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }

    public void changePhone(String userName, String phone) throws UserNotFoundException
    {
        Session session = databaseManager.sessionFactory.openSession();
        User user = databaseManager.findUser(userName, session);
        user.setPhoneNumber(phone);
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }

    public void changeBirthDate(String userName, LocalDate birthDate) throws UserNotFoundException
    {
        Session session = databaseManager.sessionFactory.openSession();
        User user = databaseManager.findUser(userName, session);
        user.setBirthDate(birthDate);
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }

    public void changeCountry (String userName, String country) throws CountryException, UserNotFoundException
    {
        Session session = databaseManager.sessionFactory.openSession();
        User user = databaseManager.findUser(userName, session);
        user.setCountry(country);
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }
    public void changeLocation (String userName, String location) throws UserNotFoundException
    {
        Session session = databaseManager.sessionFactory.openSession();
        User user = databaseManager.findUser(userName, session);
        user.setLocation(location);
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }
    public void changeWebsite (String userName, String website) throws UserNotFoundException
    {
        Session session = databaseManager.sessionFactory.openSession();
        User user = databaseManager.findUser(userName, session);
        user.setWebsite(website);
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }

    public Followers showFollowers(String userName) throws UserNotFoundException
    {
        Session session = databaseManager.sessionFactory.openSession();
        Query<User> userQuery = session.createQuery("select f.user from FollowRelation f where f.followedUser.userName = :user", User.class);
        userQuery.setParameter("user", userName);
        Followers followers = new Followers();
        for (User u : userQuery.list())
        {
            followers.add(u.toMiniUser());
        }
        return followers;
    }

    public Followings showFollowings(String userName) throws UserNotFoundException
    {
        Session session = databaseManager.sessionFactory.openSession();
        Query<User> userQuery = session.createQuery("select f.followedUser from FollowRelation f where f.user.userName = :user", User.class);
        userQuery.setParameter("user", userName);
        Followings followings = new Followings();
        for (User u : userQuery.list())
        {
            followings.add(u.toMiniUser());
        }
        return followings;
    }

    public void changeBio (String userName, Bio bio) throws UserNotFoundException, TextTooLongException
    {
        Session session = databaseManager.sessionFactory.openSession();
        User user = databaseManager.findUser(userName, session);
        user.setBio(bio);
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }

    public void follow (String userName, String followedUserName) throws UserNotFoundException, DuplicateFollowRequestException
    {
        Session session = databaseManager.sessionFactory.openSession();
        FollowRelation followRelation = new FollowRelation(databaseManager.findUser(userName, session), databaseManager.findUser(followedUserName, session));
        BlockRelation blockRelation = new BlockRelation(databaseManager.findUser(userName, session), databaseManager.findUser(followedUserName, session));
        try
        {
            databaseManager.isFollowRelationExist(followRelation, session);
            databaseManager.isBlockRelationExist(blockRelation, session);
            throw new DuplicateFollowRequestException();
        } catch (FollowRelationNotFoundException | BlockRelationNotFoundException e)
        {
            session.beginTransaction();
            session.persist(followRelation);
            session.getTransaction().commit();
        }
        finally
        {
            session.close();
        }
    }
    public void unfollow (String userName, String followedUserName) throws UserNotFoundException, FollowRelationNotFoundException
    {

        Session session = databaseManager.sessionFactory.openSession();
        try (session)
        {
            FollowRelation followRelation = new FollowRelation(databaseManager.findUser(userName, session), databaseManager.findUser(followedUserName, session));
            followRelation = databaseManager.isFollowRelationExist(followRelation, session);
            session.beginTransaction();
            session.delete(followRelation);
            session.getTransaction().commit();
        }
    }
    public MiniUser showUser (String userName) throws UserNotFoundException
    {
        Session session = databaseManager.sessionFactory.openSession();
        User user = databaseManager.findUser(userName,session);
        return user.toMiniUser();
    }

    public void sendTweet(Tweet tweet)
    {
        // temporary send tweet this throw exception
        Session session = databaseManager.sessionFactory.openSession();
        session.beginTransaction();
        for (Hashtag hashtag : tweet.getHashtags())
        {
            session.persist(hashtag);
        }
        session.persist(tweet.getHashtags());
        session.persist(tweet);
        session.getTransaction().commit(); // FIXME: got a {PersistenceException: Converting `org.hibernate.exception.SQLGrammarException` to JPA `PersistenceException` : could not execute statement} exception when tried to send a tweet with a hashtag
        session.close();
    }

    public void sendRetweet(Long id, String userName) throws TweetNotFoundException, UserNotFoundException
    {
        Session session = databaseManager.sessionFactory.openSession();
        Retweet retweet = new Retweet(findTweet(id), databaseManager.findUser(userName, session).toMiniUser());
        updateTweet(retweet.getTweet(), +1, "retweet");
        session.beginTransaction();
        session.persist(retweet);
        session.getTransaction().commit();
        session.close();
    }

    public void sendQuote(Long id, Quote quote) throws TweetNotFoundException
    {
        quote.setTweet(findTweet(id));
        updateTweet(quote.getTweet(), +1, "quote");
        Session session = databaseManager.sessionFactory.openSession();
        session.beginTransaction();
        session.persist(quote);
        session.getTransaction().commit();
        session.close();
    }

    public void sendReply(Long id, Reply reply) throws TweetNotFoundException
    {
        reply.setTweet(findTweet(id));
        Session session = databaseManager.sessionFactory.openSession();
        session.beginTransaction();
        session.persist(reply);
        session.getTransaction().commit();
        session.close();
    }

    public void likeTweet(Tweet tweet, String userName) throws UserNotFoundException, DuplicateLikeRequestException, TweetNotFoundException
    {
        Session session = databaseManager.sessionFactory.openSession();
        User user = databaseManager.findUser(userName, session);
        LikeRelation likeRelation = new LikeRelation(user, tweet);
        try
        {
            databaseManager.isLikeRelationExist(likeRelation, session);
            throw new DuplicateLikeRequestException();
        }
        catch (LikeRelationNotFoundException e)
        {
            session.beginTransaction();
            session.persist(likeRelation);
            session.getTransaction().commit();
            session.close();
            updateTweet(tweet, +1, "like");
        }
        finally
        {
            if(session.isOpen())
                session.close();
        }
    }

    public void dislikeTweet(Tweet tweet, String userName) throws UserNotFoundException, LikeRelationNotFoundException, TweetNotFoundException
    {
        Session session = databaseManager.sessionFactory.openSession();
        try
        {
            User user = databaseManager.findUser(userName, session);
            LikeRelation likeRelation = new LikeRelation(user, tweet);
            likeRelation = databaseManager.isLikeRelationExist(likeRelation, session);
            session.beginTransaction();
            session.delete(likeRelation);
            session.getTransaction().commit();
            session.close();
            updateTweet(tweet, -1, "like");
        }
        finally
        {
            if(session.isOpen())
                session.close();
        }
    }

    public void updateTweet(Tweet tweet, int change, String command) throws TweetNotFoundException
    {
        Session session = databaseManager.sessionFactory.openSession();
        Query<Tweet> tweetQuery = session.createQuery("select t from Tweet t where t.id = :id", Tweet.class);
        tweetQuery.setParameter("id", tweet.getId()); // FIXME: got a {NullPointerException: Cannot invoke "com.tweetap.entities.tweet.Tweet.getId()" because "tweet" is null} here when wanted to send a retweet / reply (by id)
        try
        {
            Tweet temptweet = tweetQuery.list().get(0);
            switch (command)
            {
                case "like" ->
                {
                    temptweet.setLikeCount(temptweet.getLikeCount() + change);
                    session.beginTransaction();
                    session.update(temptweet);
                    session.getTransaction().commit();
                }
                case "retweet" ->
                {
                    temptweet.setRetweetCount(temptweet.getRetweetCount() + change);
                    session.beginTransaction();
                    session.update(temptweet);
                    session.getTransaction().commit();
                }
                case "quote" ->
                {
                    temptweet.setQuoteCount(tweet.getQuoteCount() + change);
                    session.beginTransaction();
                    session.update(temptweet);
                    session.getTransaction().commit();
                }
            }
        }
        catch (IndexOutOfBoundsException e)
        {
            throw new TweetNotFoundException();
        }
        finally
        {
            session.close();
        }
    }

    public TimeLine showTimeLine(String userName) throws UserNotFoundException, UnknownException
    {
        // FIXME: you should show the tweets of a user to himself
        Session session = databaseManager.sessionFactory.openSession();
        Followings followings = showFollowings(userName);
        TimeLine timeLine = new TimeLine();
        // FIXME: add the miniUser to the baseTweet
        for (MiniUser u : followings)
        {
            Query<BaseTweet> baseTweetQuery = session.createQuery("select b from BaseTweet b where b.userName = :user", BaseTweet.class);
            baseTweetQuery.setParameter("user", u.getUserName());
            timeLine.add(baseTweetQuery.list());
        }
        List<BaseTweet> favStars = session.createQuery("select t from Tweet t where t.likeCount >= 10", BaseTweet.class).list();
        List<String> blockedUsers = blockedUsers(userName);
        List<String> blockerUsers = blockerUsers(userName);
        for (BaseTweet t :favStars)
        {
            if(blockerUsers.contains(t.getUserName()))
            {
                favStars.remove(t);
                continue;
            }
            if(blockedUsers.contains(t.getUserName()))
            {
                favStars.remove(t);
                continue;
            }
            if(followings.getUserNames().contains(t.getUserName()))
            {
                favStars.remove(t);
                continue;
            }
            if(t.getUserName().equals(userName))
            {
                favStars.remove(t);
            }
        }
        timeLine.add(favStars);
        Query<BaseTweet> baseTweetQuery = session.createQuery("select b from BaseTweet b where b.userName = :user", BaseTweet.class);
        baseTweetQuery.setParameter("user", userName);
        timeLine.add(baseTweetQuery.list());
        for (BaseTweet b : timeLine)
        {
            b.setOwner(databaseManager.findUser(b.getUserName(), session).toMiniUser());
            if(b instanceof Tweet)
            {
                Query<Reply> replyQuery = session.createQuery("select r from Reply r where r.tweet.id = :id", Reply.class);
                replyQuery.setParameter("id", b.getId());
                ((Tweet) b).setReplies(new ArrayList<>());
                for (Reply r : replyQuery.list())
                {
                    ((Tweet) b).getReplies().add(r);
                }
                if(((Tweet) b).getReplies() != null)
                {
                    for (Reply r : ((Tweet) b).getReplies())
                    {
                        r.setReplier(databaseManager.findUser(r.getUserName(), session).toMiniUser());
                    }
                }
                Hibernate.initialize(((Tweet) b).getHashtags().getHashtags());
                Hibernate.initialize(((Tweet) b).getHashtags());
            }
            if(b instanceof Retweet)
            {
                ((Retweet) b).getTweet().setOwner(databaseManager.findUser(((Retweet) b).getTweet().getUserName(), session).toMiniUser());
                Hibernate.initialize(((Retweet) b).getTweet().getHashtags().getHashtags());
                Hibernate.initialize(((Retweet) b).getTweet().getHashtags());
                ((Retweet) b).getTweet().setReplies(new ArrayList<>());
                for (Reply r : ((Retweet) b).getTweet().getReplies())
                {
                    r.setReplier(databaseManager.findUser(r.getUserName(), session).toMiniUser());
                }
            }
        }
        timeLine.sort();
        return timeLine;
    }

    public void block(String blocker, String blocked) throws UserNotFoundException, DuplicateBlockRequestException
    {
        // TODO : check the followRelations
        Session session = databaseManager.sessionFactory.openSession();
        BlockRelation blockRelation = new BlockRelation(databaseManager.findUser(blocker, session), databaseManager.findUser(blocked, session));
        FollowRelation followerRelation = new FollowRelation(blockRelation.getBlocked(), blockRelation.getBlocker());
        FollowRelation followingRelation = new FollowRelation(blockRelation.getBlocker(), blockRelation.getBlocked());
        try
        {
            followerRelation = databaseManager.isFollowRelationExist(followerRelation, session);
            session.beginTransaction();
            session.delete(followerRelation);
            session.getTransaction().commit();
        } catch (FollowRelationNotFoundException e) {}
        try
        {
            followingRelation = databaseManager.isFollowRelationExist(followingRelation, session);
            session.beginTransaction();
            session.delete(followingRelation);
            session.getTransaction().commit();
        } catch (FollowRelationNotFoundException e) {}
        try
        {
            databaseManager.isBlockRelationExist(blockRelation, session);
            throw new DuplicateBlockRequestException();
        } catch (BlockRelationNotFoundException e)
        {
            session.beginTransaction(); // FIXME: got a {IllegalStateException: Session/EntityManager is closed} error when i wanted to block a user
            session.persist(blockRelation);
            session.getTransaction().commit();
        }
        finally
        {
            session.close();
        }
    }

    public void unblock(String blocker, String blocked) throws UserNotFoundException, BlockRelationNotFoundException
    {
        Session session = databaseManager.sessionFactory.openSession();
        try(session)
        {
            BlockRelation blockRelation = new BlockRelation(databaseManager.findUser(blocker, session), databaseManager.findUser(blocked, session));
            blockRelation = databaseManager.isBlockRelationExist(blockRelation, session);
            session.beginTransaction();
            session.delete(blockRelation);
            session.getTransaction().commit();
        }
    }

    public BlackList showBlackList(String userName)
    {
        Session session = databaseManager.sessionFactory.openSession();
        Query<User> userQuery = session.createQuery("select b.blocked from BlockRelation b where b.blocker.userName = :user", User.class);
        userQuery.setParameter("user", userName);
        BlackList blackList = new BlackList();
        for (User u : userQuery.list())
        {
            blackList.add(u.toMiniUser());
        }
        return blackList;
    }

    public List<String> blockedUsers(String userName)
    {
        Session session = databaseManager.sessionFactory.openSession();
        Query<String> blockedUsers = session.createQuery("select b.blocked.userName from BlockRelation b where b.blocker.userName = :user", String.class);
        blockedUsers.setParameter("user", userName);
        return blockedUsers.list();
    }

    public List<String> blockerUsers(String userName)
    {
        Session session = databaseManager.sessionFactory.openSession();
        Query<String> blockerUsers = session.createQuery("select b.blocker.userName from BlockRelation b where b.blocked.userName = :user", String.class);
        blockerUsers.setParameter("user", userName);
        return blockerUsers.list();
    }

    public Tweet findTweet(Long id) throws TweetNotFoundException
    {
        Session session = databaseManager.sessionFactory.openSession();
        Query<Tweet> tweetQuery = session.createQuery("select t from Tweet t where t.id = :id", Tweet.class);
        tweetQuery.setParameter("id", id);
        try
        {
            Tweet temp = tweetQuery.list().get(0);
            temp.setReplies(new ArrayList<>());
            return temp;
        }
        catch (IndexOutOfBoundsException e)
        {
            throw new TweetNotFoundException();
        }
        finally
        {
            session.close();
        }
    }

    public User findUser(String userName) throws UserNotFoundException
    {
        try(Session session = databaseManager.sessionFactory.openSession())
        {
            return databaseManager.findUser(userName, session);
        }
    }
}
