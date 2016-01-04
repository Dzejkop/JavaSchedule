package IO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by Jakobs on 2015-10-27.
 */
public class InputReader {

    private Scanner scanner;
    private BufferedReader reader;

    public InputReader() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.scanner = new Scanner(System.in);
    }

    public String readString() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int readInt() {
        try {
            return Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error encountered! Using 0 as default.");
        }

        return 0;
    }

    public float readFloat() {
        try {
            return Float.parseFloat(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error encountered! Using 0.0 as default.");
        }

        return 0;
    }

}
