package com.chandilsachin.diettracker.io;

import android.content.res.AssetManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

/**
 * <h1>@BBI Docs</h1>
 * <h1>public class JSONReader</h1>
 * <p>Represents JSON file operation.</p>
 */
public class JSONReader
{


    /**
     * <h1>public JSONObject readExternalJsonFileToObject(String filePath)</h1>
     * <p>Returns JSON object reading file at a given path.</p>
     *
     * @param filePath - path of JSON file on SD card.
     * @throws IOException
     * @throws JSONException
     */
    public static JSONObject readExternalJsonFileToObject(String filePath) throws IOException, JSONException
    {
        String jsonStr = convertStreamToString(new FileInputStream(filePath));
        JSONObject json = new JSONObject(jsonStr);
        return json;
    }

    /**
     * <h1>public JSONArray readExternalJsonFileToArray(String filePath)</h1>
     * <p>Returns JSON object reading file at a given path.</p>
     *
     * @param filePath - path of JSON file on SD card.
     * @throws IOException
     * @throws JSONException
     */
    public static JSONArray readExternalJsonFileToArray(String filePath) throws IOException, JSONException
    {
        String jsonStr = convertStreamToString(new FileInputStream(filePath));
        JSONArray json = new JSONArray(jsonStr);
        return json;
    }

    /**
     * <h1>public JSONObject readInternalJsonFileToObject(String filePath)</h1>
     * <p>Returns JSON object reading file at a given path.</p>
     *
     * @param filePath - path of JSON file on Internal Storage.
     * @throws IOException
     * @throws JSONException
     */
    public static JSONObject readInternalJsonFileToObject(String filePath) throws IOException, JSONException
    {
        String jsonStr = convertStreamToString(new FileInputStream(filePath));
        JSONObject json = new JSONObject(jsonStr);
        return json;
    }

    /**
     * <h1>public JSONArray readInternalJsonFileToArray(String filePath)</h1>
     * <p>Returns JSON object reading file at a given path.</p>
     *
     * @param filePath - path of JSON file on Internal Storage.
     * @throws IOException
     * @throws JSONException
     */
    public static JSONArray readInternalJsonFileToArray(String filePath) throws IOException, JSONException
    {
        String jsonStr = convertStreamToString(new FileInputStream(filePath));
        JSONArray json = new JSONArray(jsonStr);
        return json;
    }

    /**
     * <h1>public JSONObject readJsonFileToObject(String filePath)</h1>
     * <p>Returns JSON object reading file at a given path.</p>
     *
     * @param filePath - path of JSON file.
     * @throws IOException
     * @throws JSONException
     */
    public static JSONObject readJsonFileToObject(String filePath) throws IOException, JSONException
    {
        JSONObject json = null;
        try
        {
            String jsonStr = convertStreamToString(new FileInputStream(filePath));
            json = new JSONObject(jsonStr);
        }catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return json;
    }

    /**
     * <h1>public JSONObject readJsonFileToObject(String filePath)</h1>
     * <p>Returns JSON object reading file at a given path.</p>
     *
     * @param filePath - path of JSON file.
     * @throws IOException
     * @throws JSONException
     */
    public static JSONObject readJsonFileToObjectFromAssets(AssetManager assetsManager, String filePath) throws IOException, JSONException
    {
        JSONObject json = null;
        try
        {
            String jsonStr = convertStreamToString(assetsManager.open(filePath));
            json = new JSONObject(jsonStr);
        }catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return json;
    }

    public static JSONArray readJsonFileToArrayFromAssets(AssetManager assetsManager, String filePath) throws IOException, JSONException
    {
        JSONArray json = null;
        try
        {
            String jsonStr = convertStreamToString(assetsManager.open(filePath));
            json = new JSONArray(jsonStr);
        }catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return json;
    }



    /**
     * <h1>public JSONObject readJsonFileToObject(File file)</h1>
     * <p>Returns JSON object reading file at a given path.</p>
     *
     * @param file - path of JSON file.
     * @throws IOException
     * @throws JSONException
     */
    public static JSONObject readJsonFileToObject(File file) throws IOException, JSONException
    {
        String jsonStr = convertStreamToString(new FileInputStream(file));
        JSONObject json = new JSONObject(jsonStr);
        return json;
    }

    /**
     * <h1>public JSONObject readJsonStreamToObject(InputStream stream)</h1>
     * <p>Returns JSON Object from given stream.</p>
     *
     * @param stream
     * @throws IOException
     * @throws JSONException
     */
    public static JSONObject readJsonStreamToObject(InputStream stream) throws IOException, JSONException
    {
        String jsonStr = convertStreamToString(stream);
        JSONObject json = new JSONObject(jsonStr);
        return json;
    }

    /**
     * <h1>public JSONArray readJsonStreamToArray(InputStream stream)</h1>
     * <p>Returns JSON array from given stream.</p>
     *
     * @param stream
     * @throws IOException
     * @throws JSONException
     */
    public static JSONArray readJsonStreamToArray(InputStream stream) throws IOException, JSONException
    {
        String jsonStr = convertStreamToString(stream);
        JSONArray json = new JSONArray(jsonStr);
        return json;
    }

    /**
     * <h1>public JSONArray readJsonFileToArray(String filePath)</h1>
     * <p>Returns JSON object reading file at a given path.</p>
     *
     * @param filePath - path of JSON file.
     * @throws IOException
     * @throws JSONException
     */
    public static JSONArray readJsonFileToArray(String filePath) throws IOException, JSONException
    {
        if (new File(filePath).exists())
        {
            String jsonStr = convertStreamToString(new FileInputStream(filePath));
            JSONArray json = new JSONArray(jsonStr);
            return json;
        }
        return null;
    }

    /**
     * <h1>public JSONArray readJsonFileToArray(File file)</h1>
     * <p>Returns JSON object reading file at a given path.</p>
     *
     * @param file - path of JSON file.
     * @throws IOException
     * @throws JSONException
     */
    public static JSONArray readJsonFileToArray(File file) throws IOException, JSONException
    {
        String jsonStr = convertStreamToString(new FileInputStream(file));
        JSONArray json = new JSONArray(jsonStr);
        return json;
    }

    /**
     * <h1>private String convertStreamToString(InputStream is)</h1>
     * <p>Converts input inputStream to String.</p>
     *
     * @param is - InputStream containing data.
     * @return String containing converted text.
     * @throws IOException
     */
    public static String convertStreamToString(InputStream is) throws IOException
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

    /**
     * path to the json file on SDCard
     *
     * @return jsonString.
     */
    public String getJsonString(File file)
    {
        String jsonString2 = null;
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(file), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null)
            {
                sb.append(line + "\n");
            }
            jsonString2 = sb.toString();

            reader.close();
        } catch (Exception e)
        {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }
        return jsonString2;
    }
}
