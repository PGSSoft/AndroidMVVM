package com.pgssoft.mvvm.services;

import com.pgssoft.mvvm.model.api.ApiRate;
import com.pgssoft.mvvm.model.api.ApiTable;
import com.pgssoft.mvvm.model.database.Rate;
import com.pgssoft.mvvm.model.database.Table;
import com.pgssoft.mvvm.models.dto.RateDto;
import com.pgssoft.mvvm.services.interfaces.MapperService;

/**
 * Created by bstokrocki on 16.02.2017.
 */

public class AutoMapper implements MapperService {
//    ModelMapper modelMapper;

    public AutoMapper() {
//        modelMapper = new ModelMapper();
    }

    @Override
    public Rate map(ApiRate apiRate) {
        return null;
//        return modelMapper.map(apiRate, Rate.class);
    }

    @Override
    public Table map(ApiTable apiTable) {
        return null;
//        return modelMapper.map(apiTable, Table.class);
    }

    @Override
    public RateDto map(Rate rate) {
        return null;
    }
}
