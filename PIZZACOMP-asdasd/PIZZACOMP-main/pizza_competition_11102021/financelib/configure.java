package financelib;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class configure {

    public static void append(String input, String file) {
        FileWriter fw = null;
        BufferedWriter bw = null;
        PrintWriter pw = null;
        try {
            fw = new FileWriter(file, true);
            bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);
            pw.println(input);
            pw.flush();

        } catch (IOException e) {
            System.out.println("Method append failed");
            e.printStackTrace();
        } finally {
            try {
                pw.close();
                bw.close();
                fw.close();
            } catch (IOException io) {
            }

        }
    }

    public static void inject(String input, int pos, String file) {
        Path path = Paths.get(file);
        List<String> lines;
        try {
            lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            lines.set(pos - 1, input);
            Files.write(path, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Method inject failed");
            e.printStackTrace();
        }

    }

    public static String read(int pos, String file) {
        String line = "";
        try {
            line = Files.readAllLines(Paths.get(file)).get(pos);
        } catch (IOException e) {
            System.out.println("Method read failed");
            e.printStackTrace();
        }
        return line;
    }
}
