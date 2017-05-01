package com.chandilsachin.diettracker.io;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class AssetsManager
{

    Context context;

    public AssetsManager(Context context)
    {
        this.context = context;
    }

    public void copyfileFromAssets(String assetFilePath, String outputFilePath) throws IOException
    {

        InputStream reader = null;
        FileOutputStream out = null;

        reader = context.getResources().getAssets().open(assetFilePath);

        File f = new File(outputFilePath);

        f.getParentFile().mkdirs();

        out = new FileOutputStream(outputFilePath);
        byte[] buffer = new byte[1024];
        while (reader.read(buffer) != -1)
        {
            out.write(buffer);
        }
        try
        {
            if (out != null)
                out.close();
            if (out != null)
                reader.close();
        } catch (IOException e)
        {
            LogCatManager.printLog(e);
        }
    }

    public void copyfilesFromAssets(String parent, String assetDirectoryPath, String destinationDirectoryPath, boolean withRoot) throws IOException
    {

        String[] assetsEntries = isDirectory(parent + "/" + assetDirectoryPath);

        if (assetsEntries.length == 0)
            copyfileFromAssets(parent + "/" + assetDirectoryPath, destinationDirectoryPath + "/" + assetDirectoryPath);

        parent += "/" + assetDirectoryPath;

        if (!withRoot)
            destinationDirectoryPath += "/" + assetDirectoryPath;
        else
            destinationDirectoryPath += "/" + parent;
        for (String entry : assetsEntries)
        {
            copyfilesFromAssets(parent, entry, destinationDirectoryPath, withRoot);
        }
    }

    public void copyfilesFromAssets(String parent, String[] assetsEntries, String destinationDirectoryPath, boolean withRoot) throws IOException
    {

        for (String entry : assetsEntries)
        {
            copyfilesFromAssets(parent, entry, destinationDirectoryPath, withRoot);
        }
    }

    public String[] isDirectory(String directoryPath)
    {
        try
        {
            return context.getAssets().list(directoryPath);
        } catch (IOException e)
        {
            return null;
        }
    }

}
