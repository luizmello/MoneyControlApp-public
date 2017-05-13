package com.myniotech.moneycontrol.converter;


import com.raizlabs.android.dbflow.converter.TypeConverter;

import org.joda.time.LocalDateTime;

import java.util.Date;

public class LocalDateTimeConverter extends TypeConverter<Long,LocalDateTime> {

	@Override
	public Long getDBValue(LocalDateTime model) {
		if (model != null) {
			return model.toDate().getTime();
		}

		return null;
	}

	@Override
	public LocalDateTime getModelValue(Long data) {
		if (data != null) {
			return LocalDateTime.fromDateFields(new Date(data));
		}

		return null;
	}
}