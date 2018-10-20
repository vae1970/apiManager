package com.vae1970.api;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.vae1970.api.util.FileUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.util.concurrent.*;

@RunWith(JUnit4.class)
public class FileUtilTests {

    @Test
    public void copyAndGet() {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("file-thread-pool-%d").build();
        ExecutorService singleThreadPool = new ThreadPoolExecutor(100, 200,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        String filePath = "/dev/shm/666";
        int threadNumber = 1000;
        File sourceFile = new File("/dev/shm/customize_profile_20180809095027");
        for (int i = 0; i < threadNumber; i++) {
            singleThreadPool.execute(() -> {
                FileUtil.copyFile(sourceFile, filePath);
                FileUtil.getFile(filePath);
            });
        }
        singleThreadPool.shutdown();
    }

    @Test
    public void fileComplete () {
        FileUtil.fileComplete(new File("/dev/shm/customize_profile_20180809095027"),null);
    }

}
