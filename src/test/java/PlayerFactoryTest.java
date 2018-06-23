import Console.ConsoleUI;
import Core.GameMode;
import Core.GameRunner;
import Core.Players.Player;
import Core.Players.PlayerComputer;
import Core.Players.PlayerFactory;
import Core.Players.PlayerHuman;
import Core.UI;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertTrue;

public class PlayerFactoryTest {

    @Test
    public void gameMode_humanvscomp() {
        UI ui = new ConsoleUI(System.in, System.out, 1);
        PlayerFactory playerFactory = new PlayerFactory(ui);

        ArrayList<Player> players = playerFactory.producePlayers(GameMode.HUMANVSCOMP);

        assertTrue(players.get(0) instanceof PlayerHuman);
        assertTrue(players.get(1) instanceof PlayerComputer);
    }

    @Test
    public void gameMode_compvscomp() {
        UI ui = new ConsoleUI(System.in, System.out, 1);
        PlayerFactory playerFactory = new PlayerFactory(ui);

        ArrayList<Player> players = playerFactory.producePlayers(GameMode.COMPVSCOMP);

        assertTrue(players.get(0) instanceof PlayerComputer);
        assertTrue(players.get(1) instanceof PlayerComputer);
    }

    @Test
    public void gameMode_humanvshuman() {
        UI ui = new ConsoleUI(System.in, System.out, 1);
        PlayerFactory playerFactory = new PlayerFactory(ui);

        ArrayList<Player> players = playerFactory.producePlayers(GameMode.COMPVSCOMP);

        assertTrue(players.get(0) instanceof PlayerHuman);
        assertTrue(players.get(1) instanceof PlayerHuman);
    }
}
