package com.dong.starsmind.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池
 * Created by zengwendong on 16/10/30.
 */
public class ThreadPool {

    private static ExecutorService pool = Executors.newFixedThreadPool(3);

    public static void execute(Runnable runnable){
        pool.execute(runnable);
    }

}
