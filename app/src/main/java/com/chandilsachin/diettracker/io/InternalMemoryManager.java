package com.chandilsachin.diettracker.io;

import android.content.Context;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class InternalMemoryManager
{

    private Context context;

    public InternalMemoryManager(Context context)
    {
        this.context = context;
    }

    public FileOutputStream writeToFile(InputStream in, String TargetPath) throws IOException
    {
        FileOutputStream out = context.openFileOutput(TargetPath, Context.MODE_PRIVATE);
        BufferedInputStream inStream = new BufferedInputStream(in);
        byte[] buffer = new byte[in.available()];
        inStream.read(buffer);
        while (buffer != null)
        {
            out.write(buffer);
            inStream.read(buffer);
        }
        return out;
    }
}
