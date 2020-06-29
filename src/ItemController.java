import java.util.ArrayList;

public class ItemController implements FindableByName {
    private ArrayList<Usable> items = new ArrayList<>();

    public ItemController() {
        // Create items to be used in game world. After creating them, they will be accessible through the itemController
        try {
            HealingItem healingHerb = new HealingItem("Healing Herb", 150);
            HealingItem healingPotion = new HealingItem("Healing Potion", 300);

            MPItem manaHerb = new MPItem("Mana Herb", 50);
            MPItem manaPotion = new MPItem("Mana Potion", 100);

            AttackItem brick = new AttackItem("Brick", 75);
            AttackItem dynamite = new AttackItem("Dynamite", 250);

            items.add(healingHerb);
            items.add(healingPotion);

            items.add(manaHerb);
            items.add(manaPotion);

            items.add(brick);
            items.add(dynamite);
        } catch (InvalidInputException ie) {
            HealingItem defaultHealingItem = new HealingItem();
            MPItem defaultMPItem = new MPItem();
            AttackItem defaultAttackItem = new AttackItem();
            items.add(defaultHealingItem);
            items.add(defaultMPItem);
            items.add(defaultAttackItem);
        }
    }

    @Override
    public Usable returnObj(String name) throws IllegalArgumentException {
    // public Usable returnObj(String name) {
        for (Usable i : items) {
            if (i.getName().equals(name)) {
                return i;
            }
        }
        throw new IllegalArgumentException("Something went wrong");
    }
}
