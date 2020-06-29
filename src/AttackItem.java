public class AttackItem extends Item implements Usable {

    public AttackItem(String name, int potency) {
        this.name = name;
        this.potency = potency;
        if (this.name == null || this.potency == 0) {
            throw new NullPointerException("name can not be null and/or potency can not be 0");
        }
    }

    // Create default item in case something goes wrong
    public AttackItem() {
        this.name = "Default Attack Item";
        this.potency = 250;
    }

    @Override
    public void use(PlayerCharacter f, int selectedNum, Enemy e) {
        // get item from inventory
        Usable itemToUse = f.getFromInventory(selectedNum);
        // deplete enemy HP
        e.takeDamage(itemToUse.getPotency());
        // restoreMP(this.potency, f);
        // delete item from inventory
        f.removeItem(itemToUse);
        // Communicate to player
        System.out.println("\n************************\n" + f.getName() + " threw a " + itemToUse.getName() +
                "!");
        System.out.println("The " + itemToUse.getName() + " does " + itemToUse.getPotency() + " HP damage to " + e.getName() + "\n************************\n");
    }
}
