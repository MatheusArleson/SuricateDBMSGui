package br.com.xavier.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.Days;

public final class DateUtils {

	private static final String DEFAULT_DATE_PATTERN = "dd/MM/yyyy";
	private static final String DEFAULT_DATETIME_PATTERN = "dd/MM/yyyy HH:mm:ss";
	
	private DateUtils() {}
	
	public static Integer  getAnoCorrente(){
		GregorianCalendar DataAtual = new GregorianCalendar();
		return DataAtual.get(GregorianCalendar.YEAR);
	}
	
	public static Date dataAtual() {
		return new Date();
	}
	
	public static Date addDaysToCurrent(int quantidadeDias){
		GregorianCalendar dataAtual = new GregorianCalendar();
		dataAtual.add( GregorianCalendar.DATE , quantidadeDias);		
		return dataAtual.getTime();
	}
	
	public static boolean after(Date d1, Date d2, String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			d1 = sdf.parse(sdf.format(d1));
			d2 = sdf.parse(sdf.format(d2));
			return d1.compareTo(d2) > 0;
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean after(Date d1, Date d2) {
		return after(d1, d2, "dd/MM/yyyy");
	}
	
	public static boolean afterNow(Date d1){
		return after(d1, DateUtils.dataAtual());
	}
	
	public static boolean before(Date d1, Date d2, String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			d1 = sdf.parse(sdf.format(d1));
			d2 = sdf.parse(sdf.format(d2));
			return d1.compareTo(d2) < 0;
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean before(Date d1, Date d2) {
		return before(d1, d2, "dd/MM/yyyy");
	}
	
	public static boolean beforeNow(Date d1){
		return before(d1, DateUtils.dataAtual());
	}
	
	public static boolean beforeMinimumDate(Date d1){
		Calendar calendarInstance = Calendar.getInstance();
		calendarInstance.set(1970, Calendar.JANUARY, 1);
		Date minimumDate = calendarInstance.getTime();
		return before(d1, minimumDate);
	}
	
	public static boolean beforeOrEquals(Date d1, Date d2) {
		return before(d1, d2) || equals(d1, d2);
	}
	
	public static boolean afterOrEquals(Date d1, Date d2) {
		return after(d1, d2) || equals(d1, d2);
	}
	
	public static boolean equals(Date d1, Date d2, String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			d1 = sdf.parse(sdf.format(d1));
			d2 = sdf.parse(sdf.format(d2));
			return d1.compareTo(d2) == 0;
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean equals(Date d1, Date d2) {
		return equals(d1, d2, "dd/MM/yyyy");
	}
	
	public static boolean between(Date data, Date dataInicio, Date dataFim){
		if(!before(data, dataInicio) && !after(data, dataFim)){
			return true;
		}
		return false;
	}
	
	public static String format(Date date) {
		return format(date, DEFAULT_DATE_PATTERN);
	}
	
	public static String formatDateTime(Date date) {
		return format(date, DEFAULT_DATETIME_PATTERN);
	}
	
	public static String format(Date date, String pattern){
		return new SimpleDateFormat(pattern).format(date);
	}
	
	public static String formatDataAtual(String pattern){
		return new SimpleDateFormat(pattern).format(dataAtual());
	}
	
	public static Calendar parseDateToCalendar(Date date){
		if(date==null)
			return  null;
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}
	
	public static Date parse(String date, String pattern){
		try {
			return new SimpleDateFormat(pattern).parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Date parse(String date) {
		return parse(date, DEFAULT_DATE_PATTERN);
	}
	
	public static Date addDays(Date date, int days){
		if(date == null){
			return null;
		}
		
		DateTime dt = new DateTime(date);
		dt = dt.plusDays(days);
		return dt.toDate();
	}

	//XXX GET DATE FIELDS METHODS
	public static Integer getYear(Date date){
		return getDateField(date, Calendar.YEAR);
	}
	
	public static Integer getMonth(Date date){
		return getDateField(date, Calendar.MONTH);
	}
	
	public static Integer getDayOfMonth(Date date){
		return getDateField(date, Calendar.DAY_OF_MONTH);
	}
	
	private static Integer getDateField(Date date, Integer dateField){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(dateField);
	}
	
	public static Integer daysBetween(Date dataInicio, Date dataFim) {
		if(dataInicio == null || dataFim == null){
			return null;
		}
		
		DateTime dtInicio = new DateTime(dataInicio);
		DateTime dtFim = new DateTime(dataFim);
		return Days.daysBetween(dtInicio, dtFim).getDays();
	}
	
	public static Integer daysIn(Date dataInicio, Date dataFim) {
		if(dataInicio == null || dataFim == null){
			return null;
		}
		
		DateTime dtInicio = new DateTime(dataInicio);
		DateTime dtFim = new DateTime(dataFim);
		return Days.daysBetween(dtInicio, dtFim).getDays() + 1;
	}
	
	public static Date byNumberOfRunnedDaysInclusive(Date dataInicio, Integer days){
		return new DateTime(dataInicio).plusDays(days  - 1).toDate();
	}

	public static  List<Integer> anosEntre(Integer anoDeInicio, Integer anoDeFim){
		if(anoDeInicio == null || anoDeFim == null){
			return null;
		}
		
		List<Integer> anosList = new ArrayList<Integer>();
		
		if(anoDeInicio.equals(anoDeFim)){
			anosList.add(anoDeInicio);
			return anosList;
		}
		
		Set<Integer> anosSet = new LinkedHashSet<Integer>();
		anosSet.add(anoDeInicio);
		anosSet.add(anoDeFim);
		
		anosList = new ArrayList<Integer>(anosSet);
		Collections.sort(anosList);
		
		anoDeInicio = anosList.get(0);
		anoDeFim = anosList.get(1);
		
		anosList.clear();
		for (int i = anoDeInicio; i <= anoDeFim; i++) {
			anosList.add(i);
		}
		
		return anosList;
	}
}