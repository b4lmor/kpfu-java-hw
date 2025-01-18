package ru.kpfu.khismatova.lab8;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public record ImageState(
        int power,

        int maxIter,

        float brightness,

        int offsetX,

        int offsetY,

        int width,

        int height

) implements Serializable {

    public static void save(String path, ImageState record) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(record);
            System.out.println("Объект сохранен в файл: " + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ImageState load(String path) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            ImageState record = (ImageState) ois.readObject();
            System.out.println("Объект загружен из файла: " + path);
            return record;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
