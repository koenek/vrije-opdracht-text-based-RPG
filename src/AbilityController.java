
public class AbilityController {

        // Skills
        // potency is initially set to 0 because there is no access to the skill user's stats at this point
        Skill stoneMallet = new Skill("Physical Attack", "Stone Mallet", 0, 3, "Rams into the enemy and sends them flying");
        Skill meditate = new Skill("Healing", "Meditate", 0, 5, "Heals a small amount of HP");
        Skill bearsBane = new Skill("Physical Attack", "Bear's Bane", 0, 6, "Slams the axe deep into the ground before pulling it out and creating a shockwave");
        Skill powerShot = new Skill("Physical Attack", "Power Shot", 0, 3, "A charged shot that does slightly more damage than a normal attack");
        Skill arrowFlurry = new Skill("Physical Attack", "Arrow Flurry", 0, 10, "Hits the enemy with a flurry of arrows");


        // Spells
        // potency is initially set to 0 because there is no access to the magic user's stats at this point
        Spell cure = new Spell("Healing", "Cure", 0, 8, "Heals a moderate amount of HP");
        Spell tornado = new Spell("Magical Attack", "Tornado", 0, 14, "Exposes the enemy to the eye of the storm!");
        Spell lightningBolt = new Spell("Magical Attack", "Lightning Bolt", 0, 18, "Strikes enemy with a bolt of lightning!");
        Spell tsunami = new Spell("Magical Attack", "Tsunami", 0, 25, "Calls forth a tsunami!");


    // Skill getters. Potency is set here
    public Skill getStoneMallet(BaseCharacter c) {
        stoneMallet.setPotency((int) (c.getAtt() * 1.65));
        return stoneMallet;
    }

    public Skill getMeditate(BaseCharacter c) {
        meditate.setPotency((int) (c.getMaxHP() * 0.2));
        return meditate;
    }

    public Skill getBearsBane(BaseCharacter c) {
        bearsBane.setPotency((int) (c.getAtt() * 1.95));
        return bearsBane;
    }

    public Skill getPowerShot(BaseCharacter c) {
        powerShot.setPotency((int) (c.getAtt() * 1.45));
        return powerShot;
    }

    public Skill getArrowFlurry(BaseCharacter c) {
        arrowFlurry.setPotency((int) (c.getAtt() * 1.95));
        return arrowFlurry;
    }

    // Spell getters. Potency is set here
    public Spell getCure(BaseCharacter c) {
        cure.setPotency((int) (c.getMaxHP() * 0.35));
        return cure;
    }

    public Spell getTornado(BaseCharacter c) {
        tornado.setPotency((int) (c.getMagicAttack() * 1.55));
        return tornado;
    }

    public Spell getLightningBolt(BaseCharacter c) {
        lightningBolt.setPotency((int) (c.getMagicAttack() * 1.85));
        return lightningBolt;
    }

    public Spell getTsunami(BaseCharacter c) {
        tsunami.setPotency((int) (c.getMagicAttack() * 2.35));
        return tsunami;
    }

}
