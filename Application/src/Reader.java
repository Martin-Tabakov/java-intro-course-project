import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader {
    /**
     * Reads data from Data.txt file, containing all the information about the tile effects and messages.
     * @param tileType The type of tile that needs its data extracted
     * @param id The ID of which Data chunk to read
     * @param quantity The number of lines to read
     * @return The lines red from the file
     */
    public static ArrayList<String> read(String tileType, int id, int quantity) {
        ArrayList<String> lines = new ArrayList<>();
        try {
            File file = new File("Data.txt");
            FileInputStream fis = new FileInputStream(file);
            Scanner sc = new Scanner(fis);
            lines = readData(sc, tileType, id, quantity);
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    /**
     * Finds the data, specified by the parameters and reads it from the file
     * @param sc Scanner reading the data from the file
     * @param tileType The type of tile that needs its data extracted
     * @param id The ID of which Data chunk to read
     * @param quantity The number of lines to read
     * @return The lines red from the file
     */
    private static ArrayList<String> readData(Scanner sc, String tileType, int id, int quantity) {
        boolean isDataLocated;
        boolean isIDLocated;
        boolean isCorrectDataFound;
        ArrayList<String> lines = new ArrayList<>();
        while (sc.hasNextLine()) {
            isDataLocated = findBeginning(sc, tileType);
            isIDLocated = findLineID(sc, id);
            isCorrectDataFound = (isDataLocated && isIDLocated);
            if (isCorrectDataFound) {
                readCorrectData(quantity, lines, sc);
                break;
            }
        }
        return lines;
    }

    /**
     * Reads the individual lines of data
     * @param quantity The number of lines to read
     * @param lines Container where the lines red from the file are stored
     * @param sc Scanner reading data from the file
     */
    private static void readCorrectData(int quantity, ArrayList<String> lines, Scanner sc) {
        for (int i = 0; i < quantity; i++) {
            lines.add(sc.nextLine());
        }
    }

    /**
     * Searches the data chunk for the correct identifier
     * @param sc Scanner reading data from the file
     * @param ID Identifier of the retrieved data
     * @return {@code true} if the ID has been found, otherwise {@code false}
     */
    private static boolean findLineID(Scanner sc, int ID) {
        String id = "ID" +ID;
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.equals(id)) return true;
        }
        return false;
    }

    /**
     * Searches the file for the beginning of the data chunk containing the corresponding tile data
     * @param sc Scanner reading data from the file
     * @param tileType The tile`s type
     * @return {@code true} if the beginning has been found, otherwise {@code false}
     */
    private static boolean findBeginning(Scanner sc, String tileType) {
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.equals(tileType)) return true;
        }
        return false;
    }
}
