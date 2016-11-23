package com.jiayou.fyg.myapplication.com.jiayou.fyg.myapplication.mlink;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class FileUtils {
    private static final int MAX_PHOTO_HEIGHT = 1080;
    private static final int MAX_PHOTO_WIDTH = 1080;

    private static final String COMMENT_PHOTO_THUMBNAIL_PART = "jiuxian_comment_";
    private static final String COMMENT_PHOTO_THUMBNAIL_EXTENSION = ".jpg";


    // 用于格式化日期,作为日志文件名的一部分
    private static final String FORMATTER = "yyyy-MM-dd-kk-mm-ss";

    /**
     * 将输入流读取为字符串
     *
     * @param in
     * @return
     */
    public static String readInputStream(InputStream in) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            sb = null;
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
                sb = null;
            }
        }
        return sb == null ? null : sb.toString();
    }

    /**
     * 转换文件大小
     *
     * @param fileS
     * @return B/KB/MB/GB
     */
    public static String formatFileSize(long fileS) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("0.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }


    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }


    /**
     * 删除文件
     *
     * @param fileName
     * @return
     */
    public static boolean deleteFile(String fileName) {
        boolean status;
        SecurityManager checker = new SecurityManager();
        if (!fileName.equals("")) {
            File path = Environment.getExternalStorageDirectory();
            File newPath = new File(path.toString() + fileName);
            checker.checkDelete(newPath.toString());
            if (newPath.isFile()) {
                try {
                    newPath.delete();
                    status = true;
                } catch (SecurityException se) {
                    status = false;
                }
            } else
                status = false;
        } else
            status = false;
        return status;
    }


    public static long getFilesSize(List<String> paths) {
        long size = 0;
        if (paths != null) {
            for (String path : paths) {
                size += getFilesSize(path);
            }
        }
        return size;
    }

    public static long getFilesSize(String path) {
        if (TextUtils.isEmpty(path)) {
            return 0;
        }
        return getFilesSize(new File(path));
    }

    public static long getFilesSize(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] children = file.listFiles();
                long size = 0;
                for (File f : children)
                    size += getFilesSize(f);
                return size;
            } else {
                long size = file.length();
                return size;
            }
        } else {
            return 0;
        }
    }


    private static Bitmap getThumbnail(String paths, int width, int height) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(paths, options);
            int scale = 1;
            int destWidth = options.outWidth;
            int destHeight = options.outHeight;
            if (destWidth > destHeight) {
                int tmp = destWidth;
                destWidth = destHeight;
                destHeight = tmp;
            }
            while (destWidth / scale > width) {
                scale++;
            }
            while (destHeight / scale > height) {
                scale++;
            }
            options.inSampleSize = scale;
            options.inJustDecodeBounds = false;
            Bitmap bitmap = BitmapFactory.decodeFile(paths, options);
            return bitmap;
        } catch (Throwable e) {
            System.gc();
        }
        return null;
    }

    public static String handlePhoto(String path) {
        return handlePhoto(path, 80);
    }

    public static String handlePhoto(String path, int quality) {
        Bitmap bitmap = getThumbnail(path, MAX_PHOTO_WIDTH, MAX_PHOTO_HEIGHT);
        if (bitmap == null) {
            return path;
        }
        BufferedOutputStream bos = null;
        try {
            File file = File.createTempFile(COMMENT_PHOTO_THUMBNAIL_PART + System.currentTimeMillis(),
                    COMMENT_PHOTO_THUMBNAIL_EXTENSION);
            bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, bos);
            bos.flush();
            return file.getAbsolutePath();
        } catch (IOException e) {
            return path;
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                }
            }
            if (bitmap != null) {
                bitmap.recycle();
                System.gc();
            }
        }

    }


    public static boolean isExist(File file){

        if (file != null){
            return file.exists();
        }
        return false;
    }
}