package Chain_of_responsability;

import java.util.ArrayList;
import java.util.List;

abstract class Creatures
{
    protected Games game;
    protected int baseAttack, baseDefense;

    public Creatures(Games game, int baseAttack, int baseDefense)
    {
        this.game = game;
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
    }

    public abstract int getAttack();
    public abstract int getDefense();
    public abstract void query(Object source, StatQuery sq);
}

class Goblin extends Creatures
{
    protected Goblin(Games game, int baseAttack, int baseDefense)
    {
        super(game, baseAttack, baseDefense);
    }

    public Goblin(Games game)
    {
        super(game, 1, 1);
    }

    @Override
    public int getAttack()
    {
        StatQuery q = new StatQuery(Statistic.ATTACK);
        for (Creature c : game.creatures)
            c.query(this, q);
        return q.result;
    }

    @Override
    public int getDefense()
    {
        StatQuery q = new StatQuery(Statistic.DEFENSE);
        for (Creature c : game.creatures)
            c.query(this, q);
        return q.result;
    }

    @Override
    public void query(Object source, StatQuery sq)
    {
        if (source == this)
        {
            switch (sq.statistic)
            {
                case ATTACK:
                    sq.result += baseAttack;
                    break;
                case DEFENSE:
                    sq.result += baseDefense;
                    break;
            }
        }
        else if (sq.statistic == Statistic.DEFENSE)
        {
            sq.result++;
        }
    }
}

class GoblinKing extends Goblin
{
    public GoblinKing(Games game)
    {
        super(game, 3, 3);
    }

    @Override
    public void query(Object source, StatQuery sq)
    {
        if (source != this && sq.statistic == Statistic.ATTACK)
        {
            sq.result++; // every goblin gets +1 attack
        }
        else super.query(source, sq);
    }
}

enum Statistic
{
    ATTACK, DEFENSE
}

class StatQuery
{
    public Statistic statistic;
    public int result;

    public StatQuery(Statistic statistic)
    {
        this.statistic = statistic;
    }
}

class Games
{
    public List<Creature> creatures = new ArrayList<>();
}
