package com.concrete.model.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class DateUtil {

	public static final String DD_MM_YYYY = "dd/MM/yyyy";
	public static final int MINUTES_30 = 30;

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
	
	/**
	 * Adiciona minutos na data passada como parametro
	 * 
	 * @param date
	 * @param minute
	 * 
	 * @return A data acrescida dos minutos passado como parametro.
	 */
	public static Date addMinute(final Date date, int minute) {
		
		if (date == null) {
			return null;
		}
		
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);		
		calendar.add(Calendar.MINUTE, minute);
		
		return calendar.getTime();			
	}
}
