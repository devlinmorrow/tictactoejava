package Core.Games;

import Console.ConsoleUI;
import Core.Grid;
import Core.Mark;
import Core.Players.Player;
import Core.Players.PlayerComputer;
import Core.UserInterfaces.Communicator;
import Core.UserInterfaces.UI;
import org.junit.Test;

import java.util.ArrayList;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class PrimaryGameTest {

    @Test
    public void runGame_oneMove() {
        UI ui = new ConsoleUI(System.in, System.out, 1);
        Communicator communicator = new Communicator(ui);
        Grid grid = new Grid();
        Player playerOne = new PlayerComputer("Player One", Mark.PLAYERONEMARK, Mark.PLAYERTWOMARK);
        Player playerTwo = new PlayerComputer("Player Two", Mark.PLAYERTWOMARK, Mark.PLAYERONEMARK);
        PrimaryGame primaryGame = new PrimaryGame(grid, playerOne, playerTwo, communicator);
        ArrayList<Mark> squaresResult = new ArrayList<>(
                asList(Mark.PLAYERONEMARK, Mark.PLAYERONEMARK, Mark.PLAYERONEMARK,
                        Mark.UNMARKEDSQUARE, Mark.UNMARKEDSQUARE, Mark.UNMARKEDSQUARE,
                        Mark.UNMARKEDSQUARE, Mark.UNMARKEDSQUARE, Mark.UNMARKEDSQUARE));

        grid.markSquare(0, Mark.PLAYERONEMARK);
        grid.markSquare(1, Mark.PLAYERONEMARK);
        primaryGame.runGame();

        assertEquals(squaresResult, grid.getSquares());
        assertEquals(playerOne, primaryGame.getActivePlayer());
    }
}