package com.concrete.model.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.lang.StringUtils;

public class DateUtil {

	private static final String DD_MM_YYYY = "dd/MM/yyyy";

	/**
	 * Construtor
	 */
	private DateUtil() {
		super();
	}

	/**
	 * Converte uma data em String no formato dd/MM/yyyy
	 * 
	 * @param date
	 *            date
	 * 
	 * @return Uma data formatada
	 */
	public static String toStr(final Calendar date) {

		String dateStr = StringUtils.EMPTY;
		if (date != null) {
			final SimpleDateFormat format = new SimpleDateFormat(DD_MM_YYYY);
			dateStr = format.format(date.getTime());
		}
		return dateStr;
	}
}
