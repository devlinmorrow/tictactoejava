public class Game {

    private String[] squares = new String[]{" ", " ", " ", " ", " ", " ", " ", " ", " "};

    public String[] getSquares() {
        return squares;
    }

    public int convertInputToSquareNumber(String input) {
       return Integer.parseInt(input) - 1;
    }

    public void setSquareToX(int input) {
        squares[input] = "X";
    }

//    public boolean isBoardFull() {
//        return false;
//    }
}
