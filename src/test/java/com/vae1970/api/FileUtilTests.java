package com.vae1970.api;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.vae1970.api.util.FileUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.util.concurrent.*;

@RunWith(JUnit4.class)
public class FileUtilTests {

    private static ExecutorService fileThreadPool;

    static {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("file-thread-pool-%d").build();
        fileThreadPool = new ThreadPoolExecutor(1000, 2000,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
    }

    @Before
    public void before() {
        FileUtil.copyFile(new File("/home/vae/Pictures/customize_profile_20180809095027")
                , "/dev/shm/customize_profile_20180809095027");
        for (int i = 0; i < 1000; i++) {
            fileThreadPool.execute(() -> System.out.print(""));
        }
    }

    @Test
    public void copyAndGet() {
        String filePath = "/dev/shm/666";
        int threadNumber = 1000;
        File sourceFile = new File("/dev/shm/customize_profile_20180809095027");
        for (int i = 0; i < threadNumber; i++) {
            fileThreadPool.execute(() -> {
                FileUtil.copyFile(sourceFile, filePath);
                FileUtil.getFile(filePath);
            });
        }
        fileThreadPool.shutdown();
    }

    @Test
    public void fileComplete() {
        FileUtil.fileComplete(new File("/dev/shm/customize_profile_20180809095027"), new File("/dev/shm/customize_profile_20180809095027"));
    }

    @Test
    public void getFile() {
        File file1 = FileUtil.getFile("/dev/shm/customize_profile_20180809095027");
        System.out.println("file1: " + (file1 == null ? "null" : file1.length()));
        File file2 = FileUtil.getFile("/dev/shm/customize_profile_20180809095027", 10, 10L);
        System.out.println("file2: " + (file2 == null ? "null" : file2.length()));
    }

}
