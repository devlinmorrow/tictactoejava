package Console;

import java.io.*;
import java.util.ArrayList;

public class GameMovesReader {

    private static File movesList;

    public ArrayList<String> runRead(File movesList) throws IOException {
        ArrayList<String> gameValues = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(movesList)));

        String line;
        line = reader.readLine();
        while (line != null) {
            gameValues.add(line);
            line = reader.readLine();
        }

        return gameValues;
    }
}
