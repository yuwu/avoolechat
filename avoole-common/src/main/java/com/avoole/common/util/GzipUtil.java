package com.avoole.common.util;
import com.avoole.common.Applog;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

public class GzipUtil
{
    private static final String TAG = "GzipUtil";
    private static final int BUFFER = 8192;

    public static void compress(File fromfile, File tozip)
    {
        if (!fromfile.exists()) {
            return;
        }

        GZIPOutputStream out = null;
        BufferedInputStream bis = null;
        try {
            out = new GZIPOutputStream(new FileOutputStream(tozip));
            bis = new BufferedInputStream(new FileInputStream(fromfile));

            byte[] data = new byte[8192];
            int count;
            while ((count = bis.read(data, 0, 8192)) != -1)
                out.write(data, 0, count);
        }
        catch (Exception e) {
            Applog.e("GzipUtil", e);
        } finally {
            if (bis != null)
                try {
                    bis.close();
                }
                catch (IOException e)
                {
                }
            if (out != null)
                try {
                    out.close();
                }
                catch (IOException e)
                {
                }
        }
    }
}