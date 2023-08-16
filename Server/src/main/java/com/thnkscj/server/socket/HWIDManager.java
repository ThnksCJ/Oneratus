package com.thnkscj.server.socket;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class HWIDManager {
    static ArrayList<String> hwidList = new ArrayList<>();

    public static void flush() {
        try {
            File file = new File("hwid.txt");

            if (!file.exists()) {
                file.createNewFile();
            }

            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                hwidList.add(fileReader.nextLine().trim());
            }
            fileReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Loaded " + hwidList.size() + " HWIDs");
    }

    public static boolean isHWIDValid(String hwid) {
        return hwidList.contains(hwid);
    }
}
