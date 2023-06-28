package com.tweetap.entities.user;

import jakarta.persistence.*;

@Entity
@Table(name = "block_relations")
public class BlockRelation
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private final User blocker;
    @OneToOne
    private final User blocked;

    public Long getId()
    {
        return id;
    }

    public BlockRelation(User blocker, User blocked)
    {
        this.blocker = blocker;
        this.blocked = blocked;
    }

    public BlockRelation()
    {
        blocker = null;
        blocked = null;
    }

    public User getBlocker()
    {
        return blocker;
    }

    public User getBlocked()
    {
        return blocked;
    }
}
