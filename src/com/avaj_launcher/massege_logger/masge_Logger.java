package com.avaj_launcher.massege_logger;
import com.avaj_launcher.massege_logger.masge_Logger;
import java.io.*;

public class masge_Logger {


    private static Writer writer = null;

    public static void setOutputFile(String filename) throws IOException
    {
        if (masge_Logger.writer != null)
            masge_Logger.writer.close();
        masge_Logger.writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename)));
    }

    public static void log(String message)
    {
        try
        {
            masge_Logger.writer.write(message+ '\n');
            writer.flush();
        }
        catch (IOException e)
        {
            System.out.println("Error while trying to write to the file");
            System.exit(1);
        }
    }
}
