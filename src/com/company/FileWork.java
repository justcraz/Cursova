package com.company;/*   
@USER: GR
@DATE: 25.05.2022

*/

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileWork {

        private FileWork() {
            throw new IllegalStateException("Це допоміжний класс");
        }

        public static String[] readFromFile(String path) {
            try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
                String str;
                List<String> array = new ArrayList<>();
                while ((str = reader.readLine()) != null) {
                    array.add(str);
                }
                return array.toArray(new String[0]);
            } catch (FileNotFoundException e) {
                System.out.println("Файлу не існує, файл створено!");
                try (FileWriter writer = new FileWriter(path)) {
                    writer.write("");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NullPointerException exn) {
                System.out.println("Файл пустий!");
            }
            return new String[0];
        }

        public static void appendToFile(String path, String text) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
                writer.write(text);
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
