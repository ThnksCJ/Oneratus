package com.thnkscj.installer.util.version;

import com.thnkscj.installer.Installer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

import static com.thnkscj.installer.Installer.mainClassPath;
import static com.thnkscj.installer.util.minecraft.MinecraftFiles.getVersions;

public class VersionInstaller {

    public static JSONObject parseJSONFile(String filename) throws JSONException, IOException {
        String content = new String(Files.readAllBytes(Paths.get(filename)));
        return new JSONObject(content);
    }

    public static void injectVersionJson() throws IOException {
        File file2 = new File(getVersions());
        if (file2.isDirectory()) {
            for (File file1 : Objects.requireNonNull(file2.listFiles())) {
                if (!file1.isDirectory()) continue;
                for (File file : Objects.requireNonNull(file1.listFiles())) {
                    if (!file.getName().contains(".json") || !file.getName().contains("1.12.2") || !file.getName().contains("forge"))
                        continue;

                    String path = String.valueOf(Paths.get(file.getAbsolutePath()));

                    editVersionArgs(path);
                    editVersionLibrarys(path);
                }
            }
        }
    }

    public static void editVersionArgs(String path) throws IOException {
        JSONObject obj = parseJSONFile(path);

        String args = (String) obj.get("minecraftArguments");
        if (!args.contains("--tweakClass " + mainClassPath)) {
            obj.remove("minecraftArguments");
            obj.put("minecraftArguments", args + " --tweakClass " + mainClassPath);

            Files.write(Paths.get(path), new JSONObject(obj.toString()).toString(2).getBytes());
        }
    }

    public static void editVersionLibrarys(String path) throws IOException {
        JSONObject obj = parseJSONFile(path);
        JSONArray arr = obj.getJSONArray("libraries");
        String args = String.valueOf(obj);

        if (!args.contains(convertPath(Installer.jarPath))) {
            JSONObject injectorJson = new JSONObject();

            injectorJson.put("name", Installer.jarPath);

            JSONArray ModifiedArray = arr.put(injectorJson);
            JSONObject newJson = obj.put("libraries", ModifiedArray);

            Files.write(Paths.get(path), new JSONObject(newJson.toString()).toString(4).getBytes());
            System.out.println("Successfully Added Args To The Forge JSON File");
        } else {
            System.out.println("The Version Json Has Already Been Injected, Skipping");
        }
    }

    public static String convertPath(String path) {
        String[] split = path.split("/");
        return split[0] + "." + split[1] + ":" + split[2] + ":" + split[3];
    }
}
