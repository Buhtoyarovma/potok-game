
import java.io.*;
import java.time.LocalDateTime;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    static void saveGame(String pathSaveFile, GameProgress gameProgress) {

        try (FileOutputStream fos = new FileOutputStream(pathSaveFile);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }


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

    static void zipFiles(String pathZipFile, String filesToZip) {

        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(pathZipFile + "/zip.zip"));

             FileInputStream fis = new FileInputStream("D:/Games/savegames/save.dat")) {

            ZipEntry entry = new ZipEntry("save.dat");
            zout.putNextEntry(entry);

            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);

            zout.write(buffer);

            zout.closeEntry();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }


    }


    public static void main(String[] args) {

        GameProgress gameProgress1 = new GameProgress(66, 55, 2, 2.2);
        GameProgress gameProgress2 = new GameProgress(99, 99, 100, 152.5);
        GameProgress gameProgress3 = new GameProgress(21, 2, 1, 0.1);


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

        try (FileWriter writer = new FileWriter("D:/Games/temp/temp.txt")) {
            writer.write(text);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        saveGame("D:/Games/savegames/save.dat", gameProgress1);
        saveGame("D:/Games/savegames/save1.dat", gameProgress2);
        saveGame("D:/Games/savegames/save2.dat", gameProgress3);

        File pathZipFile = new File("D:/Games/savegames/");
        StringBuilder sb2 = new StringBuilder();

        if (pathZipFile.isDirectory()) {
            for (File item : pathZipFile.listFiles()) {
                if (item.isFile()) {
                    sb2.append(pathZipFile + "/" + item.getName()).append("\n");
                }
            }
        }
        String filesToZip = sb2.toString();

        zipFiles(String.valueOf(pathZipFile), filesToZip);
    }
}





