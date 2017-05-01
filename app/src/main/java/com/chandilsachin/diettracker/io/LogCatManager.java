package com.chandilsachin.diettracker.io;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * <h1>BBI Docs</h1> <h1>public class LogCatManager</h1>
 * <p>
 * Manages LOgcat operations.
 * </p>
 */
public class LogCatManager
{

    public static final String TAG_WARN_JSON_ERROR = "reporting.error.json";

    private static Context context;

    private Process process;

    private InputStream in;

    private OutputStream out;

    /**
     * <h1>@BBI Docs</h1> <h1>public LogCatManager(Context context)</h1>
     * <p>
     * Constructor
     * </p>
     *
     * @param context - context of current activity or application.
     */
    public LogCatManager(Context context)
    {

        this.context = context;
    }

    public static void printLog(Exception stackTrace)
    {
        // if(Constants.TEST_MODE)
        stackTrace.printStackTrace();
        // for(StackTraceElement e:stackTrace)
        // Log.e("bbi.log.reporting", stackTrace);
    }

    public static void printLog(String str)
    {
        // if(Constants.TEST_MODE)
        {
            if (str != null)
                Log.e("bbi.log.reporting", str);
        }
    }

    /**
     * <h1>@BBI Docs</h1> <h1>public String exportCompleteLog(String
     * pathToExport)</h1>
     * <p>
     * Exports complete logcat.
     * </p>
     *
     * @param pathToExport - file path onto logcat to be exported.
     * @return path of exported file.
     * @throws IOException
     */
    public String exportCompleteLog(String pathToExport) throws IOException
    {
        byte[] buffer = new byte[1024];

        // -- Executing command for getting logcat stream.
        Process pro = Runtime.getRuntime().exec("logcat -d -v time ");
        in = pro.getInputStream();

        // -- Preparing for output file.
        File file = new File(context.getExternalFilesDir(null) + "/"
                + pathToExport);
        out = new FileOutputStream(file);

        // -- Create directory if not exists.
        if (!file.exists())
            file.mkdirs();

        // -- coping data in output stream.
        while ((in.read(buffer) != -1))
        {
            out.write(buffer);
        }
        out.close();

        return context.getExternalFilesDir(null) + "/" + pathToExport;
    }

    /**
     * <h1>@BBI Docs</h1> <h1>public String exportLog(String pathToExport)</h1>
     * <p>
     * Exports logcat of current application.
     * </p>
     *
     * @param pathToExport - file path onto logcat to be exported.
     * @return path of exported file.
     * @throws IOException
     */
    public String exportLog(String pathToExport) throws IOException
    {
        byte[] buffer = new byte[1024];

        // -- Executing command for getting logcat stream.
        Process pro = Runtime.getRuntime().exec(
                "logcat -d -v time " + context.getPackageName());
        in = pro.getInputStream();

        // -- Preparing for output file.
        File file = new File(context.getExternalFilesDir(null) + "/"
                + pathToExport);
        out = new FileOutputStream(file);

        // -- Create directory if not exists.
        if (!file.exists())
            file.mkdirs();

        // -- coping data in output stream.
        while ((in.read(buffer) != -1))
        {
            out.write(buffer);
        }
        out.close();

        return context.getExternalFilesDir(null) + "/" + pathToExport;
    }

    /**
     * <h1>@BBI Docs</h1> <h1>public String exportLog(String pathToExport,String
     * []tagName)</h1>
     * <p>
     * Exports logcat of current application filtering with given tagNames.
     * </p>
     *
     * @param pathToExport - file path onto logcat to be exported.
     * @param tagName      - tags to filter logcat.
     * @return path of exported file.
     * @throws IOException
     */
    public String exportLog(String pathToExport, String[] tagName)
            throws IOException
    {
        byte[] buffer = new byte[1024];

        // -- Preparing tags.
        String tags = "";
        for (String s : tagName)
        {
            tags += s + ":V ";
        }

        // -- Executing command for getting logcat stream.
        Process pro = Runtime.getRuntime()
                .exec("logcat -d -v time " + tags + " *:S"
                        + context.getPackageName());
        in = pro.getInputStream();

        // -- Preparing for output file.
        File file = new File(context.getExternalFilesDir(null) + "/"
                + pathToExport);
        out = new FileOutputStream(file);

        // -- Create directory if not exists.
        if (!file.exists())
            file.mkdirs();

        // -- coping data in output stream.
        while ((in.read(buffer) != -1))
        {
            out.write(buffer);
        }
        out.close();

        return context.getExternalFilesDir(null) + "/" + pathToExport;
    }

    /**
     * <h1>@BBI Docs</h1> <h1>public String exportLog(String pathToExport,String
     * tagName)</h1>
     * <p>
     * Exports logcat of current application filtering with given tagName.
     * </p>
     *
     * @param pathToExport - file path onto logcat to be exported.
     * @param tagName      - tag to filter logcat.
     * @return path of exported file.
     * @throws IOException
     */
    public String exportLog(String pathToExport, String tagName)
            throws IOException
    {
        byte[] buffer = new byte[1024];

        // -- Executing command for getting logcat stream.
        Process pro = Runtime.getRuntime().exec(
                "logcat -d -v time " + tagName + ":V *:S"
                        + context.getPackageName());
        in = pro.getInputStream();

        // -- Preparing for output file.
        File file = new File(context.getExternalFilesDir(null) + "/"
                + pathToExport);
        out = new FileOutputStream(file);

        // -- Create directory if not exists.
        if (!file.exists())
            file.mkdirs();

        // -- coping data in output stream.
        while ((in.read(buffer) != -1))
        {
            out.write(buffer);
        }
        out.close();

        return context.getExternalFilesDir(null) + "/" + pathToExport;
    }

    /**
     * <h1>@BBI Docs</h1> <h1>public String exportLog(String pathToExport,String
     * []tagName,String []priority)</h1>
     * <p>
     * Exports logcat of current application filtering with given tagNames and
     * priorities.
     * </p>
     *
     * @param pathToExport - file path onto logcat to be exported.
     * @param tagName      - tags to filter logcat.
     * @param priority     - priority corresponding to tagNames. <b>Priorities :
     *                     verbose(V),debug(D),info(I),warn(W),error(E),and
     *                     assert(A).</b>
     * @return path of exported file.
     * @throws IOException
     */
    public String exportLog(String pathToExport, String[] tagName,
                            String[] priority) throws IOException
    {
        byte[] buffer = new byte[1024];

        // -- Preparing tags.
        StringBuffer tags = new StringBuffer();

        // -- Insuring that no of both tagNames and their priorities are same.
        if (!(tagName.length == priority.length))
            throw new NullPointerException(
                    "Values of tagNames and priority do not match.");

        for (int i = 0; i < tagName.length; i++)
        {
            tags.append(tagName[i] + ":" + priority[i] + " ");
        }

        // -- Executing command for getting logcat stream.
        Process pro = Runtime.getRuntime()
                .exec("logcat -d -v time " + tags + " *:S"
                        + context.getPackageName());
        in = pro.getInputStream();

        // -- Preparing for output file.
        File file = new File(context.getExternalFilesDir(null) + "/"
                + pathToExport);
        out = new FileOutputStream(file);

        // -- Create directory if not exists.
        if (!file.exists())
            file.mkdirs();

        // -- coping data in output stream.
        while ((in.read(buffer) != -1))
        {
            out.write(buffer);
        }
        out.close();

        return context.getExternalFilesDir(null) + "/" + pathToExport;
    }

    public String getLog(String[] tagName, String[] priority)
            throws IOException
    {
        byte[] buffer = new byte[1024];

        // -- Preparing tags.
        StringBuffer tags = new StringBuffer();

        // -- Insuring that no of both tagNames and their priorities are same.
        if (!(tagName.length == priority.length))
            throw new NullPointerException(
                    "Values of tagNames and priority do not match.");

        for (int i = 0; i < tagName.length; i++)
        {
            tags.append(tagName[i] + ":" + priority[i] + " ");
        }

        // -- Executing command for getting logcat stream.
        Process pro = Runtime.getRuntime()
                .exec("logcat -d -v time " + tags + " *:S"
                        + context.getPackageName());
        in = pro.getInputStream();
        StringBuffer logs = new StringBuffer();

        while ((in.read(buffer) != -1))
        {
            logs.append(new String(buffer));
        }
        return logs.toString();
    }

}
