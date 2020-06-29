import java.util.ArrayList;

public abstract class BaseCharacter {
    protected String name;
    protected int level;
    protected int maxHP;
    protected int currentHP;
    protected int maxMP;
    protected int currentMP;
    protected int attack;
    protected int magic;
    protected int defense;
    protected int magicDefense;
    protected int speed;
    protected boolean isDead;

    protected ArrayList<Skill> skills;
    protected ArrayList<Spell> spells;

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public void setHP(int hpToSet) {
        currentHP = hpToSet;
    }

    public int getMaxMP() {
        return maxMP;
    }

    public int getCurrentMP() {
        return currentMP;
    }

    public void setMP(int mpToSet) {
        currentMP = mpToSet;
    }

    public int getAtt() {
        return attack;
    }

    public int getMagicAttack() {
        return magic;
    }

    public int getDef() {
        return defense;
    }

    public int getMagDef() {
        return magicDefense;
    }

    public int getSpeed() {
        return speed;
    }

    static int damageFormula(BaseCharacter attacker, BaseCharacter defender) {
        // get damage into a dbl (needs to be a dbl to multiply with randomPercentage
        double dmg = (attacker.getAtt() * 12) / defender.getDef();
        // Implement a simple randomizer so that damage isn't always the same
        // get random percentage between 0% and 13%
        double randomElement = dmg * randomPercentage();
        // Get a randomInt
        int randomInt = (int)Math.ceil(randomElement);
        // randomly select if randomInt should be used to increase or decrease damage output. Then return actual damage
        int decreaseOrIncrease = (int)Math.round(Math.random());
        if (decreaseOrIncrease == 0) {
            return (int)dmg - randomInt;
        } else {
            return (int)dmg + randomInt;
        }
    }

    static double randomPercentage() {
        return Math.floor(Math.random() * 9) / 100;
    }
}
