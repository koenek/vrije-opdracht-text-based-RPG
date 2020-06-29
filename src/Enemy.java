public class Enemy extends BaseCharacter implements Fighter {
    private int expToGive;
    private String ascii;

    public Enemy(String name, int lv, int maxHp, int att, int def, int spd, int exp) {

        this.name = name;
        this.level = lv;
        this.maxHP = maxHp;
        this.currentHP = maxHp;
        this.attack = att;
        this.defense = def;
        this.speed = spd;
        this.isDead = false;
        this.expToGive = exp;

    }

    public Enemy(String name, int lv, int maxHp, int att, int def, int spd, int exp, String ascii) {
        this(name, lv,maxHp, att, def, spd, exp);
        this.ascii = ascii;
    }

    public int getExpToGive() {
        return expToGive;
    }

    public String getArt() {
        return ascii;
    }

    @Override
    public void attack(PlayerCharacter pc, Enemy e) {
        try
        {
            Thread.sleep(500);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
        int damage = damageFormula(e, pc);
        System.out.println("\n************************\n" + e.getName() + " attacks!\nDeals " + damage + " damage to " + pc.getName() + "\n************************\n");
        pc.takeDamage(damage);
    }

    @Override
    public void takeDamage(int dmg) {
        if (!isDead) {
            if (this.currentHP - dmg <= 0) {
                this.currentHP = 0;
                isDead = true;
            } else {
                this.currentHP -= dmg;
            }
        }
    }



}
