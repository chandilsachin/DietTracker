package com.chandilsachin.diettracker.io;

import android.content.Context;
import android.os.Environment;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

/**
 * <h1>@BBI Docs</h1>
 *
 * @author Kapil Lokhande
 *         <h1>public class SdCardManager</h1>
 *         <p>Controls operations related to SD Card.</p>
 */
public class SdCardManager
{
    File root;
    private Context context;

    public SdCardManager(Context context)
    {

        this.context = context;
        root = Environment.getExternalStorageDirectory();
    }

    /**
     * <h1>@BBI Docs</h1>
     * <h1>public boolean ishavingSdCard()</h1>
     * <p>checks whether device has a SD card inserted.</p>
     *
     * @return a boolean
     */
    public boolean ishavingSdCard()
    {
        String state = Environment.getExternalStorageState();
        boolean mExternalStorageWriteable;
        if (Environment.MEDIA_MOUNTED.equals(state))
        {
            // We can read and write the media
            mExternalStorageWriteable = true;
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state))
        {
            mExternalStorageWriteable = false;
        } else
        {
            mExternalStorageWriteable = false;
        }
        return mExternalStorageWriteable;
    }

    /**
     * <h1>@BBI Docs</h1>
     * <h1>public String createFolderOnSdCard(String folderName)</h1>
     * <p>creates a folder on SD card.<p/>
     *
     * @param folderName - a String path with folder name.
     * @return a String containing path of created folder.
     */
    public String createFolderOnSdCard(String folderName)
    {
        File newFolder = new File(folderName);
        if (!newFolder.exists())
            newFolder.mkdir();
        return newFolder.getPath();
    }

    /**
     * <h1>@BBI Docs</h1>
     * <h1>public boolean isFileExist(String filePathOnSdCard)</h1>
     * <p>Reports whether file is exist on SD card.</p>
     *
     * @param filePathOnSdCard - path of file on sd card.
     * @return a boolean.
     */
    public boolean isFileExist(String filePathOnSdCard)
    {
        File file = new File(root.getAbsolutePath() + root.separatorChar + filePathOnSdCard);
        return file.exists();
    }

    /**
     * <h1>@BBI Docs</h1>
     * <h1>public File[] getFilesAtLocation(String location)</h1>
     * <p>Returns all files at given location.</p>
     *
     * @param location - location of directory
     * @return Array of File Class.
     */
    public File[] getFilesAtLocation(String location)
    {
        File res[] = null;
        File f = new File(location);
        if (f.exists() && f.isDirectory())
        {
            res = f.listFiles();
        }
        return res;
    }

    /**
     * <h1>@BBI Docs</h1>
     * <h1>public File[] getFilesAtLocation(String location, FileFilter filter)</h1>
     * <p>Returns all files at given location using given filter.</p>
     *
     * @param location - location of directory
     * @param filter   - FileFilter to filter files to return
     * @return Array of File Class.
     */
    public File[] getFilesAtLocation(String location, FileFilter filter)
    {
        File res[] = null;
        File f = new File(location);
        if (f.exists() && f.isDirectory())
        {
            res = f.listFiles(filter);
        }
        return res;
    }

    /**
     * <h1>@BBI Docs</h1>
     * <h1>public String[] getFileNamesAtLocation(String location)</h1>
     * <p>Returns all files at given location.</p>
     *
     * @param location - location of directory
     * @return Array containing file names.
     */
    public String[] getFileNamesAtLocation(String location)
    {
        String names[] = null;
        File f = new File(location);
        if (f.exists() && f.isDirectory())
        {
            File fs[] = f.listFiles();
            names = new String[fs.length];
            for (int i = 0; i < fs.length; i++)
            {
                names[i] = fs[i].getName();
            }
        }
        return names;
    }

    /**
     * <h1>@BBI Docs</h1>
     * <h1>public String[] getFileNamesAtLocation(String location)</h1>
     * <p>Returns all files at given location using given filter.</p>
     *
     * @param location - location of directory
     * @param filter   - FileFilter to filter files to return
     * @return Array containing file names.
     */
    public String[] getFileNamesAtLocation(String location, FileFilter filter)
    {
        String names[] = null;
        File f = new File(location);
        if (f.exists() && f.isDirectory())
        {
            File fs[] = f.listFiles(filter);
            names = new String[fs.length];
            for (int i = 0; i < fs.length; i++)
            {
                names[i] = fs[i].getName();
            }
        }
        return names;
    }

    /**
     * <h1>@BBI Docs</h1>
     * <h1>public void writeTofile(String textToWrite,String filePath)</h1>
     * <p>Writes text into file.</p>
     *
     * @param textToWrite - text to be written into file.
     * @param filePath    - target file path.
     * @throws IOException
     */
    public void writeToFile(String textToWrite, String filePath) throws IOException
    {
        new File(filePath).getParentFile().mkdirs();
        FileWriter out = new FileWriter(filePath);
        out.write(textToWrite);
        out.close();
    }

    /**
     * <h1>@BBI Docs</h1>
     * public void writeTofile(String textToWrite,String filePath,boolean append)
     * <p>Writes text into file.</p>
     *
     * @param textToWrite - text to be written into file.
     * @param filePath    - target file path.
     * @param append      - indicates whether or not to append to an existing file.
     * @throws IOException
     */
    public void writeToFile(String textToWrite, String filePath, boolean append) throws IOException
    {
        new File(filePath).getParentFile().mkdirs();
        FileWriter out = new FileWriter(filePath, append);
        out.append(textToWrite);
        out.close();
    }

    /**
     * <h1>private String convertStreamToString(InputStream is)</h1>
     * <p>Converts input inputStream to String.</p>
     *
     * @param is - InputStream containing data.
     * @return String containing converted text.
     * @throws IOException
     */
    private String convertStreamToString(InputStream is) throws IOException
    {
        if (is != null)
        {
            Writer writer = new StringWriter();

            char[] buffer = new char[1024];
            try
            {
                Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                int n;
                while ((n = reader.read(buffer)) != -1)
                {
                    writer.write(buffer, 0, n);
                }
            } finally
            {
                is.close();
            }
            return writer.toString();
        } else
        {
            return "";
        }
    }
    //*Zip file extraction code.*/
}
