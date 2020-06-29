public class MPItem extends Item implements Usable {

    public MPItem(String name, int potency) throws InvalidInputException {
        this.name = name;
        this.potency = potency;
        if (this.name == null || this.potency == 0) {
            throw new InvalidInputException("name can not be null and/or potency can not be 0");
        }
    }

    // Create default item in case something goes wrong
    public MPItem() {
        this.name = "Default Mana";
        this.potency = 150;
    }

    public void restoreMP(int pot, BaseCharacter f) {
        if (f.getCurrentMP() + pot <= f.getMaxMP()) {
            f.setMP(f.getCurrentMP() + pot);
        } else {
            f.setMP(f.getMaxMP());
        }
    }

    @Override
    public void use(PlayerCharacter f, int selectedNum, Enemy e) {
        // get item from inventory
        Usable itemToUse = f.getFromInventory(selectedNum);
        // restore MP with item
        restoreMP(this.potency, f);
        // delete item from inventory
        f.removeItem(itemToUse);
        // Communicate to player
        System.out.println("\n************************\n" + f.getName() + " used a " + itemToUse.getName() +
                " restoring " + itemToUse.getPotency() + " MP");
        System.out.println(f.getName() + " " +
                f.getCurrentMP() + "/" + f.getMaxMP() + " MP\n************************\n");
    }
}
