package com.cj.shared.util.installer.version;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

import static com.cj.shared.util.installer.LogUtil.log;
import static com.cj.shared.util.installer.minecraft.MinecraftFiles.getVersions;

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
                    if (!file.getName().contains(".json") || !file.getName().contains("1.12.2") || !file.getName().contains("forge")) continue;

                    String path = String.valueOf(Paths.get(file.getAbsolutePath()));

                    editVersionArgs(path);
                    editVersionLibrarys(path);
                }
            }
        }
    }

    public static void editVersionArgs(String path) throws IOException {

        String FILE = path;
        JSONObject obj = parseJSONFile(FILE);

        String args = (String) obj.get("minecraftArguments");
        if (!args.contains("--tweakClass com.cj.bootstrap.Injector")){
            obj.remove("minecraftArguments");
            obj.put("minecraftArguments", args + " --tweakClass com.cj.bootstrap.Injector");

            String newJsonStr = obj.toString();

            Files.write(Paths.get(FILE), new JSONObject(newJsonStr).toString(2).getBytes());
        }
    }

    public static void editVersionLibrarys(String path) throws IOException {
        String FILE = path;
        JSONObject obj = parseJSONFile(FILE);
        JSONArray arr = obj.getJSONArray("libraries");
        String args = String.valueOf(obj);

        if (!args.contains("com.oneratus:injector:1.1.2")){
            JSONObject injectorJson = new JSONObject()
                    .put("name", "com.oneratus:injector:1.1.2");

            JSONArray ModifiedArray = arr.put(injectorJson);
            JSONObject newJson = obj.put("libraries", ModifiedArray);

            String newJsonStr = newJson.toString();

            Files.write(Paths.get(FILE), new JSONObject(newJsonStr).toString(4).getBytes());
            log("Successfully Added Args To The Forge JSON File");
        }else{
            log("The Version Json Has Already Been Injected, Skipping");
        }
    }
}
