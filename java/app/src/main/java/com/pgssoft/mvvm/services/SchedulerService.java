package com.pgssoft.mvvm.services;

import android.os.Handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by bstokrocki on 19.02.2017.
 */

public class SchedulerService {
    private final Handler handler;
    private final HashMap<Runnable, Runnable> tasks;

    public SchedulerService() {
        handler = new Handler();
        tasks = new HashMap<>();
    }

    public void scheduleRepeatingTask(final Runnable task, final long interval) {
        Runnable internalTask = new Runnable() {
            @Override
            public void run() {
                task.run();
                runRepeating(this, interval);
            }
        };

        if (tasks.put(task, internalTask) == null) {
            runRepeating(internalTask, interval);
        }
    }

    public void cancelTask(Runnable task) {
        Runnable toCancel = tasks.get(task);

        if (toCancel != null) {
            handler.removeCallbacks(toCancel);
        }

        tasks.remove(task);
    }

    private void runRepeating(final Runnable task, long interval) {
        handler.postDelayed(task, interval);
    }
}
