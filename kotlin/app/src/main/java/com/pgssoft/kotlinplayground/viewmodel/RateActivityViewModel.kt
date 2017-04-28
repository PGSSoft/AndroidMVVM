package com.pgssoft.kotlinplayground.viewmodel

import android.databinding.ObservableInt
import com.pgssoft.kotlinplayground.constant.TimeIntervals
import com.pgssoft.kotlinplayground.model.RateDto
import com.pgssoft.kotlinplayground.service.SchedulerService
import com.pgssoft.kotlinplayground.service.iface.Repository
import java.util.*

/**
 * Created by bstokrocki on 31.01.2017.
 */
class RateActivityViewModel(
        private val schedulerService: SchedulerService,
        repository: Repository, rateId: String) : BaseActivityViewModel() {

    val rate: RateDto
    val maxPrecisionLevel = 5
    val precisionLevel = ObservableInt(2)

    private val originalRateValue: Double
    private var isLiveRateEnabled = false

    private val liveRateTask: () -> Unit
    private val rateRandomizer = Random()

    init {
        this.rate = repository.findRateById(rateId) as RateDto
        this.originalRateValue = rate.rate

        liveRateTask = { rate.rate = rateRandomizer.nextDouble() * originalRateValue }
    }

    fun liveRateCheckChanged(checked: Boolean) {
        scheduleLiveRateTask(checked)
        this.isLiveRateEnabled = checked
    }

    private fun scheduleLiveRateTask(isLiveRateEnabled: Boolean) {
        if (isLiveRateEnabled) {
            schedulerService.scheduleRepeatingTask(liveRateTask, TimeIntervals.LIVE_RATE_CHECK_INTERVAL_MILLIS)
        } else {
            schedulerService.cancelTask(liveRateTask)
        }
    }

    override fun onStart() {
        if (isLiveRateEnabled) scheduleLiveRateTask(true)
    }

    override fun onStop() {
        if (isLiveRateEnabled) scheduleLiveRateTask(false)
    }
}
