
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Main {


    static String newFolder(String pathFolder, String nameFolder) {
        File dir = new File(pathFolder + nameFolder);
        if (dir.mkdir())
            return LocalDateTime.now() + " Папка " + dir.getName() + " создана";
        else
            return LocalDateTime.now() + " Папка " + dir.getName() + " не создана";
    }

    static String newFile(String pathFile, String nameFile) {
        File file = new File(pathFile + nameFile);
        try {

            if (file.createNewFile())
                return LocalDateTime.now() + " Файл " + file.getName() + " создан";
            else
                return LocalDateTime.now() + " Файл " + file.getName() + " не создан";

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {

        StringBuilder sb = new StringBuilder();


        String text = sb.append(newFolder("D:/Games/", "scr")).append("\n")
                .append(newFolder("D:/Games/", "res")).append("\n")
                .append(newFolder("D:/Games/", "savegames")).append("\n")
                .append(newFolder("D:/Games/", "temp")).append("\n")

                .append(newFolder("D:/Games/scr/", "main")).append("\n")
                .append(newFolder("D:/Games/scr/", "test")).append("\n")


                .append(newFile("D:/Games/scr/main/", "Main.java")).append("\n")
                .append(newFile("D:/Games/scr/main/", "Utils.java")).append("\n")

                .append(newFolder("D:/Games/res/", "drawables")).append("\n")
                .append(newFolder("D:/Games/res/", "vectors")).append("\n")
                .append(newFolder("D:/Games/res/", "icons")).append("\n")

                .append(newFile("D:/Games/temp/", "temp.txt")).toString();

        //String text = sb.toString();
        try (FileWriter writer = new FileWriter("D:/Games/temp/temp.txt")) {
            writer.write(text);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }


    }


}
