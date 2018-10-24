package com.vae1970.api.util;

import java.io.*;
import java.time.LocalTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author vae
 */
public class FileUtil {

    private static final Map<String, AtomicReference<Object>> TEMP_FILE_MAP = new ConcurrentHashMap<>();
    private static final Object INITIAL_VALUE = new Object();
    private static final Object UPDATE_VALUE = new Object();

    /**
     * copy file(thread safe)
     *
     * @param sourceFile     sourceFile
     * @param targetFilePath targetFilePath
     */
    public static void copyFile(File sourceFile, String targetFilePath) {
        if (!sourceFile.exists()) {
            return;
        }
        AtomicReference<Object> sign;
        File targetFile = new File(targetFilePath);
        if ((sign = TEMP_FILE_MAP.get(targetFilePath)) == null) {
            synchronized (TEMP_FILE_MAP) {
                if ((sign = TEMP_FILE_MAP.get(targetFilePath)) == null && !fileComplete(sourceFile, targetFile)) {
                    sign = new AtomicReference<>(INITIAL_VALUE);
                    TEMP_FILE_MAP.put(targetFilePath, sign);
                    System.out.println("write");
                }
            }
        }
        if (sign != null && sign.compareAndSet(INITIAL_VALUE, UPDATE_VALUE)) {
            if (!fileComplete(sourceFile, targetFile)) {
                copy(sourceFile, targetFilePath);
            }
            TEMP_FILE_MAP.remove(targetFilePath);
        }
    }

    /**
     * check file exists and completes
     *
     * @param sourceFile source
     * @param targetFile target
     * @return boolean
     */
    public static boolean fileComplete(File sourceFile, File targetFile) {
        return sourceFile.exists() && targetFile.exists() && sourceFile.length() == targetFile.length();
    }

    /**
     * get file(copy file)
     *
     * @param targetFilePath filePath
     * @param timeoutSeconds timeout for waiting get file(seconds)
     * @param sleepMillis    interlude for getting file(millis)
     * @return File
     */
    public static File getFile(String targetFilePath, Integer timeoutSeconds, Long sleepMillis) {
        File file = new File(targetFilePath);
        LocalTime startTime = LocalTime.now();
        if (file.exists()) {
            AtomicReference<Object> sign = TEMP_FILE_MAP.get(targetFilePath);
            if (sign == null) {
                return file;
            } else {
                while (true) {
                    if (LocalTime.now().isAfter(startTime.plusSeconds(timeoutSeconds))) {
                        return null;
                    } else if (TEMP_FILE_MAP.get(targetFilePath) == null) {
                        return new File(targetFilePath);
                    }
                    try {
                        Thread.sleep(sleepMillis);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            return null;
        }
    }

    /**
     * copy file(thread safe)
     *
     * @param filePath filePath
     * @return File
     */
    public static File getFile(String filePath) {
        return getFile(filePath, 10, 10L);
    }

    /**
     * copy file(thread unsafe)
     *
     * @param sourceFile     sourceFile
     * @param targetFilePath targetFilePath
     */
    private static void copy(File sourceFile, String targetFilePath) {
        try (FileInputStream fileInputStream = new FileInputStream(sourceFile);
             BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
             FileOutputStream fileOutputStream = new FileOutputStream(targetFilePath);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream)) {
            byte[] b = new byte[1024];
            int len;
            while ((len = bufferedInputStream.read(b)) != -1) {
                bufferedOutputStream.write(b, 0, len);
            }
            bufferedOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
