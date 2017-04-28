package com.pgssoft.mvvm.viewmodels;

import android.databinding.Observable;
import android.databinding.ObservableField;

import com.pgssoft.mvvm.constant.TimeIntervals;
import com.pgssoft.mvvm.model.database.Rate;
import com.pgssoft.mvvm.models.dto.RateDto;
import com.pgssoft.mvvm.services.SchedulerService;
import com.pgssoft.mvvm.services.interfaces.Repository;

import java.util.Random;

/**
 * Created by bstokrocki on 31.01.2017.
 */
public class RateActivityViewModel extends BaseActivityViewModel {
    private final SchedulerService schedulerService;
    private final RateDto rate;

    private final double originalRateValue;
    private final int maxPrecisionLevel;
    private boolean isLiveRateEnabled;

    private final ObservableField<Integer> precisionLevel;

    private final Runnable liveRateTask;
    private final Random rateRandomizer;

    public RateActivityViewModel(Repository repository, SchedulerService schedulerService, String rateId) {
        this.schedulerService = schedulerService;
        this.rate = repository.findRateById(rateId);

        this.originalRateValue = rate.getRate();
        this.maxPrecisionLevel = 5;

        precisionLevel = new ObservableField<>(2);
        rateRandomizer = new Random();

        liveRateTask = new Runnable() {
            @Override
            public void run() {
                rate.setRate(rateRandomizer.nextDouble() * originalRateValue);
            }
        };
    }

    public RateDto getRate() {
        return rate;
    }

    public ObservableField<Integer> getPrecisionLevel() {
        return precisionLevel;
    }

    public int getMaxPrecisionLevel() {
        return maxPrecisionLevel;
    }

    public void liveRateCheckChanged(boolean checked) {
        scheduleLiveRateTask(checked);
        this.isLiveRateEnabled = checked;
    }

    private void scheduleLiveRateTask(boolean isLiveRateEnabled) {
        if (isLiveRateEnabled) {
            schedulerService.scheduleRepeatingTask(liveRateTask,
                    TimeIntervals.LIVE_RATE_CHECK_INTERVAL_MILLIS);
        } else {
            schedulerService.cancelTask(liveRateTask);
        }
    }

    @Override
    public void onStart() {
        if (isLiveRateEnabled) {
            scheduleLiveRateTask(true);
        }
    }

    @Override
    public void onStop() {
        if (isLiveRateEnabled) {
            scheduleLiveRateTask(false);
        }
    }
}
