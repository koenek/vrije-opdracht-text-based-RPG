import java.util.ArrayList;
import java.util.HashMap;

public class PlayerCharacter extends BaseCharacter implements Fighter {
    private AbilityController abilityController = new AbilityController();
    private ItemController itemCtrl = new ItemController();

    // Hashmap: Key is the item, value is the amount of the item in inventory
    private HashMap<Usable, Integer> inventory;

    private int exp;
    private String playerClass;

    public PlayerCharacter(String name, String classChoice) {
        // set class, stats, skills/spells and equipment for each class
        if (classChoice.equals("W")) {
            // Stats
            this.name = name;
            this.playerClass = "Warrior";
            this.level = 1;
            this.maxHP = 300;
            this.maxMP = 10;
            this.currentHP = 300;
            this.currentMP = 10;
            this.attack = 40;
            this.magic = 5;
            this.defense = 15;
            this.magicDefense = 5;
            this.speed = 10;
            this.isDead = false;
            this.exp = 0;

            // Skills
            this.skills = new ArrayList<>();
            this.skills.add(abilityController.getStoneMallet(this));
            this.skills.add(abilityController.getMeditate(this));
            this.skills.add(abilityController.getBearsBane(this));
        }
        if (classChoice.equals("R")) {
            this.name = name;
            this.playerClass = "Ranger";
            this.level = 1;
            this.maxHP = 250;
            this.maxMP = 25;
            this.currentHP = 250;
            this.currentMP = 25;
            this.attack = 35;
            this.magic = 16;
            this.defense = 12;
            this.magicDefense = 12;
            this.speed = 25;
            this.isDead = false;
            this.exp = 0;
            this.inventory = new HashMap<>();
            this.skills = new ArrayList<>();
            this.skills.add(abilityController.getPowerShot(this));
            this.skills.add(abilityController.getMeditate(this));
            this.skills.add(abilityController.getArrowFlurry(this));
        }
        if (classChoice.equals("M")) {
            this.name = name;
            this.playerClass = "Mage";
            this.level = 1;
            this.maxHP = 210;
            this.maxMP = 80;
            this.currentHP = 210;
            this.currentMP = 80;
            this.attack = 5;
            this.magic = 44;
            this.defense = 9;
            this.magicDefense = 18;
            this.speed = 9;
            this.isDead = false;
            this.exp = 0;
            this.inventory = new HashMap<>();
            this.spells = new ArrayList<>();
            this.spells.add(abilityController.getCure(this));
            this.spells.add(abilityController.getTornado(this));
            this.spells.add(abilityController.getLightningBolt(this));
            this.spells.add(abilityController.getTsunami(this));
        }
        // Items
        this.inventory = new HashMap<>();
        try {
            // HP Items
            addToInventory(itemCtrl.returnObj("Healing Herb"), 5);
            addToInventory(itemCtrl.returnObj("Healing Potion"), 3);

            // MP Items
            addToInventory(itemCtrl.returnObj("Mana Herb"), 3);
            addToInventory(itemCtrl.returnObj("Mana Potion"), 1);

            // AttackItems
            addToInventory(itemCtrl.returnObj("Dynamite"), 3);
            addToInventory(itemCtrl.returnObj("Brick"), 10);
        } catch (IllegalArgumentException ie) {
            System.out.println("Something went wrong while creating items, continuing with default inventory.");
            addToInventory(itemCtrl.returnObj("Default Herb"), 10);
            addToInventory(itemCtrl.returnObj("Default Mana"), 10);
            addToInventory(itemCtrl.returnObj("Default Attack Item"), 10);
        }
    }

    public void useAbility(Ability a, PlayerCharacter pc, Enemy e) {
            if (a.type.equals("Physical Attack")) {
                useMP(a.getMpCost());
                int damage = a.getPotency();
                System.out.println("\n************************\n" +
                        pc.getName() + " uses " + a.title + "!\nDeals " +
                        damage + " damage to " + e.getName() +
                        "\n************************\n");
                e.takeDamage(damage);
            } else if (a.type.equals("Healing")) {
                useMP(a.getMpCost());
                int hpToHeal = a.getPotency();
                System.out.println("\n************************\n" +
                        pc.getName() + " uses " + a.title + "!\nRestores " +
                        hpToHeal + " HP to " + pc.getName() +
                        "\n************************\n");
                pc.restoreHP(hpToHeal);
            } else if (a.type.equals("Magical Attack")) {
                useMP(a.getMpCost());
                int damage = a.getPotency();
                System.out.println("\n************************\n" +
                        pc.getName() + " calls forth " + a.title + "!\nDeals " +
                        damage + " damage to " + e.getName() +
                        "\n************************\n");
                e.takeDamage(damage);
            }
    }

    public void restoreHP(int hpToRestore) {
        if (currentHP + hpToRestore <= maxHP) {
            currentHP += hpToRestore;
        } else {
            currentHP = maxHP;
        }
    }

    public void useMP(int mpToUse) {
        currentMP -= mpToUse;
    }

    public Ability getAbility(int num) {
        if (playerClass.equals("Warrior") || playerClass.equals("Ranger")) {
            return this.skills.get(num - 1);
        } else {
            return this.spells.get(num - 1);
        }
    }

    public String getPlayerClass() {
        return playerClass;
    }

    public void showStatus() {
        System.out.println("==========" +
                "\n=NAME:          " + this.name +
                "\n=LEVEL:         " + this.level +
                "\n=HP:            " + this.maxHP +
                "\n=MP:            " + this.maxMP +
                "\n=ATTACK:        " + this.attack +
                "\n=MAGIC:         " + this.magic +
                "\n=DEFENSE:       " + this.defense +
                "\n=MAGIC DEFENSE: " + this.magicDefense +
                "\n=SPEED:         " + this.speed);
    }

    public void showAbilityMenu() {
        int i = 1;
        if (this.playerClass.equals("Warrior") ||this.playerClass.equals("Ranger")) {
            System.out.println("\n==============SKILLS================");
            for (Skill skill : skills) {
                System.out.println("| " + i + ": " + skill + " - " + skill.getMpCost() + " MP");
                i++;
            }
        } else {
            System.out.println("\n==============SPELLS================");
            for (Spell spell : spells) {
                System.out.println("| " + i + ": " + spell + " - " + spell.getMpCost() + " MP");
                i++;
            }
        }
        System.out.println("====================================\n");
    }

    public void showInventory() {
        int i = 1;
        System.out.println("\n==============ITEMS================");
        for (Usable item : inventory.keySet()) {
            System.out.println("| " + i + ": " + item + " - " + inventory.get(item) + " |");
            i++;
        }
        System.out.println("| Enter 'q' to cancel using an item");
        System.out.println("===================================\n");
    }

    public HashMap<Usable, Integer> getInventory() {
        return inventory;
    }

    public void removeItem(Usable usedItem) {
        inventory.replace(usedItem, inventory.get(usedItem), inventory.get(usedItem) - 1);
    }

    public int getInventorySize() {
        return inventory.size();
    }

    public void addToInventory(Usable item, int amount) {
        this.inventory.put(item, amount);
    }

    public Usable getFromInventory(int num) {
        int i = 1;
        for (Usable item : inventory.keySet()) {
            if (i == num) {
                return item;
            }
            i++;
        }
        return null;
    }

    public boolean selectedItemEmpty(Usable itemToUse) {
        if (inventory.get(itemToUse) == 0) {
            return true;
        }
        return false;
    }

    public void defendBuff() {
        this.defense *= 2;
        System.out.println("\n************************\n" +
                getName() + " is defending!\nNext physical attack damage will be halved" +
                "\n" +
                "************************\n");
    }

    public void defendDeBuff() {
        this.defense /= 2;
    }

    // Levelling etc.
    public int getExp() {
        return exp;
    }

    public void addExp(int experiencePts) {
        this.exp += experiencePts;
    }

    public void levelUp(int expAfterBattle) {
        int levelBefore = getLevel();
        int levelAfter = levelBefore;

        if (exp >= 8 && exp < 14) {
            // Level 2
            levelAfter = 2;
        } else if (exp >= 14 && exp < 23) {
            // Level 3
            levelAfter = 3;
        } else if (exp >= 23 && exp < 35) {
            // Level 4
            levelAfter = 4;
        } else if (exp >= 35 && exp < 50) {
            // Level 5
            levelAfter = 5;
        } else if (exp >= 50 && exp < 68) {
            // Level 6
            levelAfter = 6;
        } else if (exp >= 68 && exp < 89) {
            levelAfter = 7;
        } else if (exp >= 89 && exp < 113) {
            levelAfter = 8;
        } else if (exp >= 113 && exp < 140) {
            levelAfter = 9;
        } else if (exp >= 150) {
            levelAfter = 10;
        }

        level = levelAfter;

        if (!(levelBefore == levelAfter)) {
            if (playerClass.equals("Warrior")) {
                for (int i = levelBefore; i < levelAfter; i++) {
                    maxHP = (int) Math.ceil(getMaxHP() * 1.12);
                    maxMP = (int) Math.ceil(getMaxMP() * 1.1);
                    attack = (int) Math.ceil(getAtt() * 1.18);
                    defense = (int) Math.ceil(getDef() * 1.125);
                    magicDefense = (int) Math.ceil(getMagDef() * 1.125);
                    speed = (int) Math.ceil(getSpeed() * 1.1);
                }
            } else if (playerClass.equals("Ranger")) {
                for (int i = levelBefore; i < levelAfter; i++) {
                    maxHP = (int) Math.ceil(getMaxHP() * 1.09);
                    maxMP = (int) Math.ceil(getMaxMP() * 1.1);
                    attack = (int) Math.ceil(getAtt() * 1.15);
                    defense = (int) Math.ceil(getDef() * 1.125);
                    magicDefense = (int) Math.ceil(getMagDef() * 1.15);
                    speed = (int) Math.ceil(getSpeed() * 1.18);
                }
            } else if (playerClass.equals("Mage")) {
                for (int i = levelBefore; i < levelAfter; i++) {
                    maxHP = (int) Math.ceil(getMaxHP() * 1.08);
                    maxMP = (int) Math.ceil(getMaxMP() * 1.8);
                    magic = (int) Math.ceil(getMagicAttack() * 1.18);
                    defense = (int) Math.ceil(getDef() * 1.125);
                    magicDefense = (int) Math.ceil(getMagDef() * 1.18);
                    speed = (int) Math.ceil(getSpeed() * 1.1);
                }
            }

            System.out.println(getName() + " LEVEL UP!\n" + getName() + "'s stats have increased:");
            showStatus();
        }
    }

    public void attack(PlayerCharacter pc, Enemy e) {
        int damage = damageFormula(pc, e);

        System.out.println("\n************************\n" +
                pc.getName() + " attacks!\nDeals " +
                damage + " damage to " + e.getName() +
                "\n************************\n");

        e.takeDamage(damage);
    }

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
