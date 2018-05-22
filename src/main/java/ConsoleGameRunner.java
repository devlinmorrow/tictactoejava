import java.util.ArrayList;
import java.util.Random;

class ConsoleGameRunner {

    private ConsoleDisplay consoleDisplay;
    private PlayerHuman playerHumanOne = new PlayerHuman("Player One", Mark.playerOneMarkedSquare);
    private PlayerHuman playerHumanTwo = new PlayerHuman("Player Two", Mark.playerTwoMarkedSquare);
    private PlayerHuman activePlayer = playerHumanOne;
    private Grid grid = new Grid();
    private Validator validator = new Validator();


    ConsoleGameRunner(ConsoleDisplay consoleDisplay) {
        this.consoleDisplay = consoleDisplay;
    }

    public void run() {
        askGameMode();
        String gameChoice = consoleDisplay.takeInput();
        if (gameChoice.equals("1")) {
            runComputerGame();
        } else {
            runHumanGame();
        }
    }

    public void runComputerGame() {
        displayGrid();
        while (gameOngoing()) {
            if (itIsHumanTurn()) {
                String input = generateComputerInput();
                makeMove(input);
                announceHumanSquareChoice();
            } else {
                announceComputerTurn();
            }
            displayGrid();
            announceIfGameOver();
        }
    }

    public void runHumanGame() {
        displayGrid();
        while (gameOngoing()) {
            String input = takeHumanInput();
            makeMove(input);
            announceHumanSquareChoice();
            displayGrid();
            announceIfGameOver();
            alternatePlayer();
        }
    }

    public String takeHumanInput() {
        String input = activePlayer.askForMove(consoleDisplay);
        boolean inputInvalid = inputNotLegal(input);
        while (inputInvalid) {
            announceInputInvalid();
            askForSquareChoice();
            input = consoleDisplay.takeInput();
            inputInvalid = inputNotLegal(input);
        }
        return input;
    }

    public void makeMove(String input) {
        int inputConverted = validator.convertInputStrtoInt(input);
        inputConverted--;
        grid.markSquare(inputConverted, activePlayer.getMark());
    }

    public void announceIfGameOver() {
        if (gameIsWon()) announceWinner();
        else if (gameTied()) announceGameTied();
    }

    private boolean inputNotLegal(String input) {
        int inputConverted = validator.convertInputStrtoInt(input);
        inputConverted--;
        return grid.moveNotLegal(inputConverted);
    }

    private void announceHumanSquareChoice() {
        consoleDisplay.announceHumanSquareChoice();
    }

    private void displayGrid() {
        consoleDisplay.displayGrid(getGridSquares());
    }

    private boolean itIsHumanTurn() {
        return activePlayer == playerHumanOne;
    }

    private void announceInputInvalid() {
        consoleDisplay.announceInputInvalid();
    }

    private void askForSquareChoice() {
        consoleDisplay.askForSquareChoice(activePlayer.getName());
    }

    private void announceWinner() {
        consoleDisplay.announceWinner(activePlayer.getName());
    }

    private void announceGameTied() {
        consoleDisplay.announceGameTied();
    }

    private void askGameMode() {
        consoleDisplay.askGameMode();
    }

    private void announceComputerTurn() {
        consoleDisplay.announceComputerTurn();
    }

    public boolean gameIsWon() {
        return grid.winningLineExistsInGrid();
    }

    public boolean moveNotLegal(int input) {
        return grid.moveNotLegal(input);
    }

    public boolean gameOngoing() {
        return !gameTied() && !gameIsWon();
    }

    public boolean gameTied() {
        return grid.isFull();
    }

    public void alternatePlayer() {
        if (activePlayer == playerHumanOne) activePlayer = playerHumanTwo;
        else activePlayer = playerHumanOne;
    }

    public ArrayList<Mark> getGridSquares() {
        return grid.getSquares();
    }

    public String generateComputerInput() {
        int potentialInput = 0;
        boolean illegalMove = true;
        while (illegalMove) {
            potentialInput = generateRandomComputerNumber();
            illegalMove = moveNotLegal(potentialInput);
        }
        return String.valueOf(potentialInput + 1);
    }

    private int generateRandomComputerNumber() {
        Random rand = new Random();
        return rand.nextInt(8);
    }
}


