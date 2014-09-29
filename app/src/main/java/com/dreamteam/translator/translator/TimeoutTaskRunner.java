package com.dreamteam.translator.translator;

import android.content.Intent;
import android.os.AsyncTask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by flyingleafe on 29.09.14.
 */
public class TimeoutTaskRunner implements Runnable {
    AsyncTask task;
    long timeout;

    public static void runTask(AsyncTask task, long timeout) {
        new Thread(new TimeoutTaskRunner(task, timeout)).start();
    }

    private TimeoutTaskRunner(AsyncTask task, long timeout) {
        this.task = task;
        this.timeout = timeout;
    }

    @Override
    public void run() {
        try {
            task.get(timeout, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            task.cancel(true);
            e.printStackTrace();
        }
    }
}