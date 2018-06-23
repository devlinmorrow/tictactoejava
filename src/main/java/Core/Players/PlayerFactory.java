package Core.Players;

import Core.GameMode;
import Core.Mark;
import Core.UI;

import java.util.ArrayList;

public class PlayerFactory {

    private UI ui;

    private final String playerOneName = "Player One";
    private final String playerTwoName = "Player Two";
    private final String computerName = "Computer";
    private final String computerOneName = "Computer One";
    private final String computerTwoName = "Computer Two";

    public PlayerFactory(UI ui) {
        this.ui = ui;
    }

    public ArrayList<Player> producePlayers(GameMode gameMode) {
        ArrayList<Player> players = new ArrayList<>();
        Player playerOne;
        Player playerTwo;

        switch (gameMode) {
            case HUMANVSCOMP:
                playerOne = new PlayerHuman(playerOneName, Mark.PLAYERONEMARK, ui);
                playerTwo = new PlayerComputer(computerName, Mark.PLAYERTWOMARK, Mark.PLAYERONEMARK);
                break;
            case COMPVSCOMP:
                playerOne = new PlayerComputer(computerOneName, Mark.PLAYERONEMARK, Mark.PLAYERTWOMARK);
                playerTwo = new PlayerComputer(computerTwoName, Mark.PLAYERTWOMARK, Mark.PLAYERONEMARK);
                break;
            case HUMANVSHUMAN:
                playerOne = new PlayerHuman(playerOneName, Mark.PLAYERONEMARK, ui);
                playerTwo = new PlayerHuman(playerTwoName, Mark.PLAYERTWOMARK, ui);
                break;
            default:
                throw new RuntimeException("Something wrong with creating the players");
        }

        players.add(playerOne);
        players.add(playerTwo);
        return players;
    }

}
