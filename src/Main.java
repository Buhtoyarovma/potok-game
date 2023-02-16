import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    static String newFolder(String pathFolder) {
        File dir = new File(pathFolder);
        if (dir.mkdir()) {
            return LocalDateTime.now() + " Папка " + dir.getName() + " создана" + "\n";
        } else
            return LocalDateTime.now() + " Папка " + dir.getName() + " не создана" + "\n";
    }

    static String newFile(String pathFile, String nameFile) {
        File file = new File(pathFile + nameFile);
        try {
            if (file.createNewFile()) {
                return LocalDateTime.now() + " Файл " + file.getName() + " создан" + "\n";
            } else
                return LocalDateTime.now() + " Файл " + file.getName() + " не создан" + "\n";

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void zipFiles(String pathZipFile, List<String> list) {

        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(pathZipFile))) {

            for (String file : list) {
                FileInputStream fis = new FileInputStream(file);
                File saveFile = new File(file);
                ZipEntry entry = new ZipEntry(saveFile.getName());
                zout.putNextEntry(entry);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zout.write(buffer);
                fis.close();
                zout.closeEntry();
                saveFile.delete();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static void main(String[] args) {

        List<String> directories = new ArrayList<>();
        directories.addAll(Arrays.asList("D://Games/res", "D://Games/src", "D://Games/savegames", "D://Games/temp",
                "D://Games/src/main", "D://Games/src/test", "D://Games/src/main/", "D://Games/res/drawables",
                "D://Games/res/vectors", "D://Games/res/icons"));

        StringBuilder sb = new StringBuilder();

        for (String i : directories) {
            sb.append(newFolder(i));
        }

        sb.append(newFile("D://Games/temp/", "temp.txt"));
        sb.append(newFile("D://Games/src/main/", "Main.java"));
        sb.append(newFile("D://Games/src/main/", "Utils.java"));

        try (FileWriter writer = new FileWriter("D://Games/temp/temp.txt")) {
            writer.write(String.valueOf(sb));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        GameProgress gameProgress1 = new GameProgress(66, 55, 2, 2.2);
        GameProgress gameProgress2 = new GameProgress(99, 99, 100, 152.5);
        GameProgress gameProgress3 = new GameProgress(21, 2, 1, 0.1);

        List<String> list = Arrays.asList("D://Games/savegames/save.dat", "D://Games/savegames/save1.dat", "D://Games/savegames/save2.dat");

        saveGame(list.get(0), gameProgress1);
        saveGame(list.get(1), gameProgress2);
        saveGame(list.get(2), gameProgress3);

        zipFiles("D://Games/savegames/zip.zip", list);
    }
}





