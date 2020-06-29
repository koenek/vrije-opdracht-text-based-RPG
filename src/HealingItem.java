public class HealingItem extends Item implements Usable {

    public HealingItem(String name, int potency) throws InvalidInputException {
        this.name = name;
        this.potency = potency;
        if (this.name == null || this.potency == 0) {
            throw new InvalidInputException("name can not be null and/or potency can not be 0");
        }
    }

    // Create default item in case something goes wrong
    public HealingItem() {
        this.name = "Default Herb";
        this.potency = 250;
    }


    public void restoreHP(int pot, BaseCharacter f) {
        if (f.getCurrentHP() + pot <= f.getMaxHP()) {
            f.setHP(f.getCurrentHP() + pot);
        } else {
            f.setHP(f.getMaxHP());
        }
    }

    @Override
    public void use(PlayerCharacter f, int selectedNum, Enemy e) {
        // get item from inventory
        Usable itemToUse = f.getFromInventory(selectedNum);
        // restore HP with item
        restoreHP(this.potency, f);
        // delete item from inventory
        f.removeItem(itemToUse);
        // Communicate to player
        System.out.println("\n************************\n" + f.getName() + " used a " + itemToUse.getName() +
                " restoring " + itemToUse.getPotency() + " HP");
        System.out.println(f.getName() + " " +
                f.getCurrentHP() + "/" + f.getMaxHP() + " HP\n************************\n");
    }
}
