
import java.util.Scanner;

public class BattleController {
    Scanner scanner = new Scanner(System.in);
    EnemyController enemyController = new EnemyController();

    // battleController
    public void doBattle(PlayerCharacter pc, Enemy e) {
        System.out.println("\n=====Get ready for battle=====\n");

        scanner.nextLine();

        System.out.println(e.getArt());

        System.out.println("\n" + e.getName() + " appears before you!\nWhat will you do?");

        while (true) {
            // If neither player or enemy is dead:
            if ((!pc.isDead) && (!e.isDead)) {
                // Show menu and ask player for input
                if (pc.getPlayerClass().equals("Warrior") || pc.getPlayerClass().equals("Ranger")) {
                    System.out.println("\n=========================\n" +
                            "|=Attack=[A]=|=" + parsePlayerName(pc.getName()) + "=|" + "\n" +
                            "|=Skills=[S]=|=" + pc.getCurrentHP() + "/" + pc.getMaxHP() + "==|" + "\n" +
                            "|=Defend=[D]=|===" + pc.getCurrentMP() + "/" + pc.getMaxMP() + "==|" + "\n" +
                            "|=Item===[I]=" + "|==========|\n" +
                            "=========================\n");
                } else {
                    System.out.println("\n=========================\n" +
                            "|=Attack=[A]=|=" + parsePlayerName(pc.getName()) + "=|" + "\n" +
                            "|=Spells=[S]=|=" + pc.getCurrentHP() + "/" + pc.getMaxHP() + "==|" + "\n" +
                            "|=Defend=[D]=|===" + pc.getCurrentMP() + "/" + pc.getMaxMP() + "==|" + "\n" +
                            "|=Item===[I]=" + "|==========|\n" +
                            "=========================\n");
                }

                String input = scanner.nextLine().toLowerCase();

                switch (input) {
                    case "a":
                        attackInputTurn(pc, e);
                        break;
                    case "s":
                        abilityInputTurn(pc, e);
                        break;
                    case "d":
                        defendInputTurn(pc, e);
                        break;
                    case "i":
                        itemInputTurn(pc, e);
                        break;
                }
            } else if (pc.isDead) {
                // call gameOver method
                System.out.println("GAME OVER");
                System.exit(0);
            } else if (e.isDead) {
                int expBeforeAdding = pc.getExp();
                System.out.println("\n************************\n");
                System.out.println(e.getName() + " defeated! Received " + e.getExpToGive() + " experience points.");
                pc.addExp(e.getExpToGive());
                // check if current total exp of player is enough to level up
                pc.levelUp(e.getExpToGive());
                System.out.println("\n************************\n");
                // exit battle if enemy is defeated
                return;
            }
        }
    }

    public void attackInputTurn(PlayerCharacter pc, Enemy e) {

        // perform speedcheck. Character with higher speed goes first
        if (pc.getSpeed() > e.getSpeed()) {
            pc.attack(pc, e);
            // Check if player or enemy is dead
            if (e.isDead) {
                // if player defeats enemy, player gains experience
                // Placeholder for now:
                return;
            }
            e.attack(pc, e);
        } else {
            e.attack(pc, e);
            if (pc.isDead) {
                // if player is dead. Show game over screen and end game.
                return;
            }
            pc.attack(pc, e);
        }
    }

    public void abilityInputTurn(PlayerCharacter pc, Enemy e) {
        boolean confirmed;
        pc.showAbilityMenu();
        do {
            String input = scanner.nextLine();

            // input "q" cancels ability selection
            if (input.equals("q")) {
                System.out.println("cancelled using an ability");
                return;
            }

            confirmed = false;

            // select skill to use
            int abilityNum = Integer.valueOf(input);

            // get skill from skill arraylist
            Ability abilityToUse = pc.getAbility(abilityNum);

            // Check if player has enough mp for selected ability
            if (!(pc.getCurrentMP() < abilityToUse.getMpCost())) {
                // perform speedcheck
                if (pc.getSpeed() > e.getSpeed()) {
                    pc.useAbility(abilityToUse, pc, e);
                    e.attack(pc, e);
                    confirmed = true;
                } else {
                    e.attack(pc, e);
                    pc.useAbility(abilityToUse, pc, e);
                    confirmed = true;
                }
            } else {
                System.out.println("Not enough MP!");
                return;
            }
        } while (!confirmed);
    }

    public void defendInputTurn(PlayerCharacter pc, Enemy e) {
        pc.defendBuff();
        if (e.isDead) {
            return;
        }
        e.attack(pc, e);
        pc.defendDeBuff();
        if (pc.isDead) {
            // if player is dead. Show game over screen and end game.
            return;
        }
        return;
    }

    public void itemInputTurn(PlayerCharacter pc, Enemy e) {
        boolean confirmed;
        pc.showInventory();
        do {
            String input = scanner.nextLine();

            // input "q" cancels item usage
            if (input.equals("q")) {
                System.out.println("cancelled using an item");
                return;
            }

            confirmed = false;

            // select Item to use
            int itemNum = Integer.valueOf(input);
            Usable itemToUse = pc.getFromInventory(itemNum);

            if (!(itemNum > pc.getInventorySize()) && !(pc.selectedItemEmpty(itemToUse))) {
                // if not, perform speedcheck and then use item
                if (pc.getSpeed() > e.getSpeed()) {
                    pc.getFromInventory(itemNum).use(pc, itemNum, e);
                    e.attack(pc, e);
                    confirmed = true;
                } else {
                    e.attack(pc, e);
                    pc.getFromInventory(itemNum).use(pc, itemNum, e);
                    confirmed = true;
                }
            } else {
                // if we reach this part of the code, the selected item was not in inventory
                System.out.println("\nThe selected item is not in your inventory. Please select another item or cancel by typing 'q'\n");
                return;
            }

        } while (!confirmed);
    }

    public String parsePlayerName(String playerName) {
        if (playerName.length() < 8) {
            for (int i = playerName.length(); i < 8; i++) {
                playerName = playerName.concat("=");
            }
        }
        return playerName;
    }

}
