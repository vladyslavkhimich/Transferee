package com.example.transferee.helpers;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class FileHelper {
    public static String playerIdFile = "playerids";
    public static String playerIdFileTemp = "playeridstemp";
    public static File directory;
    public static boolean addPlayerIdToStorage(Context context, Integer id) {
        try {
            FileOutputStream fileOutputStream = context.openFileOutput(playerIdFile, Context.MODE_APPEND);
            fileOutputStream.write((id.toString() + "\n").getBytes());
            fileOutputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean removePlayerIdFromStorage(Context context, Integer id) {
        try {
            FileInputStream fileInputStream = context.openFileInput(playerIdFile);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            File tempFile = new File(directory, playerIdFileTemp);
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tempFile));
            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                String trimmedLine = currentLine.trim();
                if (trimmedLine.equals(id.toString()))
                    continue;
                bufferedWriter.write(currentLine + "\n");
            }
            bufferedWriter.close();
            bufferedReader.close();
            boolean isDeleteSuccessful = new File(directory, playerIdFile).delete();
            if (isDeleteSuccessful)
            {
                boolean isRenamed = tempFile.renameTo(new File(directory, playerIdFile));
                return isRenamed;
            }
            return false;
        }
        catch (IOException ioException) {
            ioException.printStackTrace();
            return false;
        }

    }

    public static ArrayList<Integer> returnStoredPlayersIds(Context context) {
        try {
            FileInputStream fileInputStream = context.openFileInput(playerIdFile);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String currentLine;
            ArrayList<Integer> playerIdsArrayList = new ArrayList<>();
            while ((currentLine = bufferedReader.readLine()) != null) {
                String trimmedLine = currentLine.trim();
                playerIdsArrayList.add(Integer.parseInt(trimmedLine));
            }
            return playerIdsArrayList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<Integer>();
    }

    public static void createPlayerIdsFileIfNotExist(Context context) throws IOException {
        directory = context.getFilesDir();
        File playerIdsFile = new File(directory, playerIdFile);
        if (!playerIdsFile.exists()) {
            playerIdsFile.createNewFile();
        }
    }
}
