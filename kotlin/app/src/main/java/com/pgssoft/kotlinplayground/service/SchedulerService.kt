package com.pgssoft.kotlinplayground.service

import android.os.Handler
import java.util.*

/**
 * Created by bstokrocki on 19.02.2017.
 */

class SchedulerService {
    private val handler = Handler()
    private val tasks = HashMap<() -> Unit, Runnable>()

    fun scheduleRepeatingTask(task: () -> Unit, interval: Long) {
        val internalTask = object : Runnable {
            override fun run() {
                task.invoke()
                runRepeating(this, interval)
            }
        }

        tasks.put(task, internalTask) ?: runRepeating(internalTask, interval)
    }

    fun cancelTask(task: () -> Unit) {
        val toCancel = tasks[task]

        if (toCancel != null) {
            handler.removeCallbacks(toCancel)
        }

        tasks.remove(task)
    }

    private fun runRepeating(task: Runnable, interval: Long) {
        handler.postDelayed(task, interval)
    }
}
