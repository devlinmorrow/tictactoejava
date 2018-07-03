package Core;

import Core.Games.Game;
import Core.Games.GameFactory;
import Core.Games.GameRunner;
import Core.UserInterfaces.Communicator;

public class TicTacToe {

    private Communicator communicator;
    private GameRunner gameRunner;
    private GameFactory gameFactory;
    private GameModeSelector gameModeSelector;
    private boolean anotherGame;
    private boolean isRecording;

    public TicTacToe(Communicator communicator) {
        this.communicator = communicator;
        gameRunner = new GameRunner();
        gameFactory = new GameFactory(communicator);
        gameModeSelector = new GameModeSelector(communicator);
        anotherGame = true;
        isRecording = true;
    }

    public void run() {
        while (anotherGame) {
            GameMode gameMode = getMode();
            runGame(gameMode);
            findIfPlayAnotherGame();
        }
        communicator.announceProgramOver();
    }

    private void findIfPlayAnotherGame() {
        communicator.askIfPlayAgain();
        anotherGame = communicator.returnTrueIfYes();
    }

    private GameMode getMode() {
        return gameModeSelector.getMode();
    }

    private void runGame(GameMode gameMode) {
        Game game = gameFactory.buildGame(gameMode, communicator, isRecording);
        gameRunner.runGame(game);
        while (rewatch()) {
            Game rewatchGame = gameFactory.buildGame(GameMode.SIMULATEDPLAY, communicator, isRecording);
            gameRunner.runGame(rewatchGame);
        }
    }

    private boolean rewatch() {
        communicator.askRewatch();
        return communicator.returnTrueIfYes();
    }
}
