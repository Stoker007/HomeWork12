package ru.rusalitc.java.basic.homework12;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        File root = new File("src");
        File[] files = root.listFiles((dir, name) -> name.endsWith(".txt"));
        System.out.println("В папке находятся следующие текстовые файлы:");
        if (files != null) {
            for (File f : files) {
                System.out.println(f.getName());
            }
        }
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите имя файла для чтения его содержимого (для выхода введите q )");
            String fileName = scanner.next();

            if (fileName.equals("q")) {
                break;
            }

            File file = new File("src/" + fileName);
            if (!file.exists()) {
                System.out.println("Такого файла нет или неккоретктный ввод имени файла");
            } else {
                try (InputStreamReader in = new InputStreamReader(new FileInputStream("src/" + fileName))) {
                    System.out.println("Содержимое файла:");
                    int n = in.read();
                    while (n != -1) {
                        System.out.print((char) n);
                        n = in.read();
                    }
                    System.out.println();

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
            Scanner scanner2 = new Scanner(System.in);
            System.out.println("Введите строку для добавления в этот файл (для выхода введите q )");
            String string = scanner2.nextLine();
            if (string.equals("q")) {
                break;
            }
            try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("src/"+fileName, true))) {
                string = "\n" + string;
                byte[] buffer = string.getBytes(StandardCharsets.UTF_8);
                for (int i = 0; i < buffer.length; i++) {
                    out.write(buffer[i]);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }




        }

    }


}







