package com.pgssoft.mvvm.services.interfaces;

import com.pgssoft.mvvm.model.api.ApiRate;
import com.pgssoft.mvvm.model.api.ApiTable;
import com.pgssoft.mvvm.model.database.Rate;
import com.pgssoft.mvvm.model.database.Table;

/**
 * Created by bartooo on 31.01.2017.
 */
public interface MapperService {
    Rate map(ApiRate apiRate);

    Table map(ApiTable apiTable);
}
