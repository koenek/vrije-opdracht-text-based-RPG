import java.util.Scanner;

public class PlayerController {
    Scanner scanner = new Scanner(System.in);
    private AbilityController abilityController = new AbilityController();
    private Validator validator = new Validator();
    PlayerCharacter player;

    // playerController
    public PlayerCharacter createCharacter() {
        // boolean confirmed = false;
        boolean confirmed = true;
        String playerName = "";
        String playerClassSelection = "";
        String YesOrNoInput = "";

        do {

            while (true) {
                boolean nameValid = false;
                System.out.println("Please choose your character name: ");
                playerName = scanner.nextLine();
                try {
                    validator.validate(playerName, 8);
                    nameValid = true;
                } catch (InvalidInputException ie) {
                    System.out.println(ie.getMessage() + "A character name can contain 8 characters or less and it can not be empty.");
                }
                if (nameValid) break;
            }

            while (true) {
                boolean classInputValid = false;
                System.out.println("Please choose your character class:\nEnter [W] for Warrior, [R] for Ranger and [M] for Mage");
                playerClassSelection = scanner.nextLine().toUpperCase();
                try {
                    validator.validate(playerClassSelection, "W", "R", "M");
                    classInputValid = true;
                } catch (InvalidInputException ie) {
                    System.out.println(ie.getMessage() + "Please input [W] for Warrior, [R] for Ranger or [M] for Mage to select a class.");
                }
                if (classInputValid) break;
            }

            // create the character
            player = new PlayerCharacter(playerName, playerClassSelection);

            // Show created character status menu
            System.out.println("This is your character:");
            player.showStatus();
            player.showAbilityMenu();

            // Ask player to confirm
            while (true) {
                boolean yesOrNoEntered = false;
                System.out.println("Do you wish to continue with this character? [Y/N]");
                YesOrNoInput = scanner.nextLine().toUpperCase();
                try {
                    validator.validate(YesOrNoInput, "Y", "N");
                    yesOrNoEntered = true;
                } catch (InvalidInputException ie) {
                    System.out.println(ie.getMessage() + "Please confirm with [Y] or reset character creation with [N].");
                }
                if (yesOrNoEntered) break;
            }

            if (YesOrNoInput.equals("Y")) {
                confirmed = true;
            } else {
                confirmed = false;
            }
        } while (confirmed == false);
        return player;
    }

}
