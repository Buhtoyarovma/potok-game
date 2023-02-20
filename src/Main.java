import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {

        List<String> directories = new ArrayList<>();
        directories.addAll(Arrays.asList("D://Games/res", "D://Games/src", "D://Games/savegames", "D://Games/temp",
                "D://Games/src/main", "D://Games/src/test", "D://Games/src/main/", "D://Games/res/drawables",
                "D://Games/res/vectors", "D://Games/res/icons"));

        for (String i : directories) {
            newFolder(i);
        }

        newFile("D://Games/temp/", "temp.txt");
        newFile("D://Games/src/main/", "Main.java");
        newFile("D://Games/src/main/", "Utils.java");

        try (FileWriter writer = new FileWriter("D://Games/temp/temp.txt")) {
            writer.write(String.valueOf(sb));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        List<String> list = Arrays.asList("D://Games/savegames/save.dat", "D://Games/savegames/save1.dat", "D://Games/savegames/save2.dat");

        saveGame(list.get(0), new GameProgress(66, 55, 2, 2.2));
        saveGame(list.get(1), new GameProgress(99, 99, 100, 152.5));
        saveGame(list.get(2), new GameProgress(21, 2, 1, 0.1));

        zipFiles("D://Games/savegames/zip.zip", list);
    }

    static void saveGame(String pathSaveFile, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(pathSaveFile);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    static void newFolder(String pathFolder) {
        File dir = new File(pathFolder);
        if (dir.mkdir()) {
//            return LocalDateTime.now() + " Папка " + dir.getName() + " создана" + "\n";
            sb.append(LocalDateTime.now() + " Папка " + pathFolder + " успешно создана \n");
        } else
//            return LocalDateTime.now() + " Папка " + dir.getName() + " не создана" + "\n";
            sb.append(LocalDateTime.now() + " Папка " + pathFolder + " не создана \n");
    }

    static void newFile(String pathFile, String nameFile) {
        File file = new File(pathFile + nameFile);
        try {
            if (file.createNewFile()) {
                sb.append(LocalDateTime.now() + " Файл " + nameFile + " Успешно создан \n");
            } else
                sb.append(LocalDateTime.now() + " Файл " + nameFile + " не создан \n");

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


}





