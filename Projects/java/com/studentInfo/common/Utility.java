/**
 * @ (#) Utility.java
 * Project     : SIMS
 * File        : Utility.java
 * Author      : Ninganna.c
 * Company     : EVRY
 * Date Created: 15/Mar/2017
 *
 * ========================================================================================================================
 *  No | Modified date |      Modified by     |  Reason
 * ========================================================================================================================
 *  1.   
 * ========================================================================================================================
 */
package com.studentInfo.common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

/**
 * utility class for project common tasks 
 * @author Ninganna.c
 *
 */
public class Utility {
	
	private static final Logger LOGGER = Logger.getLogger(Utility.class);
	// ***************************************
	    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	// *****************************************

		private Utility() {
			/* private constructor */
		}
		
		/**
		 * generet randomAlphaNumeric
		 * @param count
		 * @return
		 */
		public static String randomAlphaNumeric(int count) {
			StringBuilder builder = new StringBuilder();
			while (count-- != 0) {
				int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
				builder.append(ALPHA_NUMERIC_STRING.charAt(character));
			}
			return builder.toString();
		}
		
		/**
		 * Privileges are added to arrayList from comma separated string
		 * 
		 * @param assigned
		 *            String
		 * @return arList Returns the List
		 */
		public static List addToArrayList(String assigned) {
			List arList = new ArrayList();
			if(assigned != null){
				StringTokenizer st = new StringTokenizer(assigned, ",");
				while (st.hasMoreTokens()) {
					arList.add(st.nextToken());
				}
			}
			return arList;
		}

		/**
		 * Check if the passed in string is null, if true return an empty string.
		 * 
		 * @param originalString
		 *            String
		 * @return String
		 */
		public static String checkNull(final Object originalString) {
			String retStr = null;
			if (originalString == null || "null".equals(originalString)) {
				retStr = "";
			} else if ("undefined".equals(originalString)) {
				retStr = "";
			} else {
				retStr = originalString.toString().trim();
			}
			return retStr;
		}

		/**
		 * Replace a Given String
		 * 
		 * @param target
		 * @param from
		 * @param to
		 * @return
		 */
		public static String replaceString(String target, String from, String to) {
			int start = target.indexOf(from);
			if (start == -1) {
				return target;
			}
			int lf = from.length();
			char[] targetChars = target.toCharArray();
			StringBuilder buffer = new StringBuilder();
			int copyFrom = 0;
			while (start != -1) {
				buffer.append(targetChars, copyFrom, start - copyFrom);
				buffer.append(to);
				copyFrom = start + lf;
				start = target.indexOf(from, copyFrom);
			}
			buffer.append(targetChars, copyFrom, targetChars.length - copyFrom);
			return buffer.toString();
		}

		/**
		 * 
		 * 
		 * @param strQuery
		 * @return
		 */
		public static String hqlToken(String strQuery) {
			String strReturn = "";
			int iVal = 1;
			if (strQuery.indexOf('?') > 0) {
				while (true) {
					if (strQuery.indexOf('?') > 0) {
						strQuery = strQuery.replaceFirst("\\?", "{" + (iVal++) + "}");
					} else {
						break;
					}
				}
				for (int i = 1; i < iVal; i++) {
					strQuery = strQuery.replace("{" + i + "}", "?" + i + "");
				}
				strReturn = strQuery;
			} else {
				strReturn = strQuery;
			}
			return strReturn;
		}

		/**
		 * @param originalString
		 * @return
		 */
		public static Object checkNullWithPercentage(Object originalString) {
			String retStr = null;
			if (originalString == null || "null".equals(originalString) || "".equals(replaceString(replaceString((originalString.toString()).trim(), "%", ""), "||", ""))) {
				retStr = "";
			} else if ("undefined".equals(originalString)) {
				retStr = "";
			} else {
				retStr = originalString.toString().trim();
			}
			return retStr;
		}

		/**
		 * Method to create a date from Calendar
		 * 
		 * @param Year
		 * @param month
		 * @param day
		 * @return
		 */
		public static Date getUtilDate(int year, int month, int day) {
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, year);
			cal.set(Calendar.MONTH, month);
			cal.set(Calendar.DAY_OF_MONTH, day);
			return cal.getTime();
		}

		/**
		 * converts the given string into date object
		 * 
		 * @param date
		 * @return
		 * @throws ParseException
		 */
		public static Date checkDate(String date) throws SimsException {
			Date convertedDate;
			if (checkNull(date).isEmpty()) {
				return null;
			} else {
				DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
				try {
					convertedDate = format.parse(date);
				} catch (ParseException e) {
					throw new SimsException(e);
				}

				return convertedDate;
			}
		}

		/**
		 * @param date
		 * @return
		 * @throws OnlineTestException
		 */
		public static Date checkDateForTransaction(String date) throws SimsException {
			Date convertedDate = null;
			if (checkNull(date).isEmpty()) {
				return null;
			} else {
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				try {
					convertedDate = format.parse(date);
				} catch (ParseException e) {
					throw new SimsException(e);
				}
				return convertedDate;
			}

		}

		/**
		 * @param date
		 * @return
		 */
		public static String dateToString(Date date) {
			if (checkNull(date).isEmpty()) {
				return null;
			} else {
				SimpleDateFormat dateformatyyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
				String date_to_string = dateformatyyyyMMdd.format(date);
				return date_to_string;
			}
		}

		/**
		 * converts the lonf to String
		 * 
		 * @param strValue
		 * @return
		 */
		public static Long checkLong(Long lngValue) {
			if (lngValue == 0) {
				return null;
			} else {
				return lngValue;
			}
		}

		/**
		 * converts the string to Bigdecimal
		 * 
		 * @param strValue
		 * @return
		 */
		public static BigDecimal checkBigDecimal(String strValue) {
			if (checkNull(strValue).isEmpty()) {
				return null;
			} else {
				return new BigDecimal(strValue);
			}
		}

		/**
		 * converts the string to Bigdecimal
		 * 
		 * @param strValue
		 * @return
		 */
		public static Integer checkInteger(String strValue) {
			if (checkNull(strValue).isEmpty()) {
				return null;
			} else {
				return Integer.parseInt(strValue);
			}
		}

		/**
		 * converts the String to character
		 * 
		 * @param strValue
		 * @return
		 */
		public static Character checkCharacter(String strValue) {
			if (checkNull(strValue).isEmpty()) {
				return null;
			} else {
				return strValue.charAt(0);
			}
		}

		/**
		 * @param forMonth
		 * @return
		 */
		/*public static int returnMonthAsNumber(String forMonth) {
			String[] splitforMonth = forMonth.split("-");
			int month = 0;
			switch (splitforMonth[0]) {
			case "January":
				month = 1;
				break;
			case "February":
				month = 2;
				break;
			case "March":
				month = 3;
				break;
			case "April":
				month = 4;
				break;
			case "May":
				month = 5;
				break;
			case "June":
				month = 6;
				break;
			case "July":
				month = 7;
				break;
			case "August":
				month = 8;
				break;
			case "September":
				month = 9;
				break;
			case "October":
				month = 10;
				break;
			case "November":
				month = 11;
				break;
			case "December":
				month = 12;
				break;
			default:
				break;
			}
			return month;

		}*/

		/**
		 * @param highest
		 * @return
		 */
		public static String getPriorDate(Date highest) {
			String fmtDt = new SimpleDateFormat("dd-MM-yyyy").format(highest);
			String date = null, month = null;
			String[] dtSlpit = fmtDt.split("-");
			if (dtSlpit[0].length() == 1) {
				date = "0" + dtSlpit[0];
			} else {
				date = dtSlpit[0];
			}
			if (dtSlpit[1].length() == 1) {
				int mon = Integer.parseInt(dtSlpit[1]);
				if (mon == 2) {
					month = "12";
				} else if (mon == 1) {
					month = "11";
				} else {
					month = "0" + (Integer.parseInt(dtSlpit[1]) - 2);
				}
			} else {
				String val = String.valueOf(Integer.parseInt(dtSlpit[1]) - 2);
				if (val.length() == 1) {
					month = "0" + val;
				} else {
					month = val;
				}
			}

			if (("04".equals(month) || "06".equals(month) || "09".equals(month) || "11".equals(month)) && ("31".equals(date))) {
				date = "30";
			}
			if ("02".equals(month) && ("31".equals(date) || "30".equals(date) || "29".equals(date))) {
				date = "28";
			}
			String newDt = date + "-" + month + "-" + dtSlpit[2];
			return newDt;
		}

		public static Integer getMonthNumber(String monthName) throws SimsException {
			Integer monthNumber = 0;
			try {
				Date date = new SimpleDateFormat("MMM").parse(monthName);
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				monthNumber = cal.get(Calendar.MONTH);
			} catch (Exception e) {
				throw new SimsException(e);
			}
			return monthNumber + 1;
		}
		
		public static Date getOneMonthPriorDate(){
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, -1);
			Date result = cal.getTime();
			return result;
		}
		public static Integer calculateDifference(Calendar currentDate, Calendar expiryDate) {
			Integer diffYear = expiryDate.get(Calendar.YEAR) - currentDate.get(Calendar.YEAR);
			Integer diffMonth = diffYear * 12 + expiryDate.get(Calendar.MONTH) - currentDate.get(Calendar.MONTH);
			return diffMonth;
		}

		/**
		 * Gets the Value from the object for the method name @param obj Object @param
		 * methodName String @return Object
		 * 
		 * @param obj
		 * @param methodName
		 * @return
		 */
		/*public static Object getMethodValue(Object obj, String methodName) {
			Class c = obj.getClass();
			Object ret = new Object();
			try {
				Method method = c.getMethod(methodName, new Class[0]);
				ret = method.invoke(obj, new Object[0]);
			} catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
				LOGGER.error("Error in getMethodValue call getMethod", e);
			}
			c = null;
			return ret;
		}*/
		
		public String specialCharReplace(String question) {
			for (int i = 0; i < question.length();) {
				switch (question.charAt(i)) {
				case ' ':
					question = question.substring(0, i) + "&#32" + question.substring(i + 1);
					i = i + 4;
					break;
				case '!':
					question = question.substring(0, i) + "&#33" + question.substring(i + 1);
					i = i + 4;
					break;
				case '"':
					question = question.substring(0, i) + "&#34" + question.substring(i + 1);
					i = i + 4;
					break;
				case '#':
					question = question.substring(0, i) + "&#35" + question.substring(i + 1);
					i = i + 4;
					break;
				case '$':
					question = question.substring(0, i) + "&#36" + question.substring(i + 1);
					i = i + 4;
					break;
				case '%':
					question = question.substring(0, i) + "&#37" + question.substring(i + 1);
					i = i + 4;
					break;
				case '&':
					question = question.substring(0, i) + "&#38" + question.substring(i + 1);
					i = i + 4;
					break;
				case '\'':
					question = question.substring(0, i) + "&#39" + question.substring(i + 1);
					i = i + 4;
					break;
				case '(':
					question = question.substring(0, i) + "&#40" + question.substring(i + 1);
					i = i + 4;
					break;
				case ')':
					question = question.substring(0, i) + "&#41" + question.substring(i + 1);
					i = i + 4;
					break;
				case '*':
					question = question.substring(0, i) + "&#42" + question.substring(i + 1);
					i = i + 4;
					break;
				case '+':
					question = question.substring(0, i) + "&#43" + question.substring(i + 1);
					i = i + 4;
					break;
				case ',':
					question = question.substring(0, i) + "&#44" + question.substring(i + 1);
					i = i + 4;
					break;
				case '-':
					question = question.substring(0, i) + "&#45" + question.substring(i + 1);
					i = i + 4;
					break;
				case '.':
					question = question.substring(0, i) + "&#46" + question.substring(i + 1);
					i = i + 4;
					break;
				case '/':
					question = question.substring(0, i) + "&#47" + question.substring(i + 1);
					i = i + 4;
					break;
				case ':':
					question = question.substring(0, i) + "&#58" + question.substring(i + 1);
					i = i + 4;
					break;
				case ';':
					question = question.substring(0, i) + "&#59" + question.substring(i + 1);
					i = i + 4;
					break;
				case '<':
					question = question.substring(0, i) + "&#60" + question.substring(i + 1);
					i = i + 4;
					break;
				case '=':
					question = question.substring(0, i) + "&#61" + question.substring(i + 1);
					i = i + 4;
					break;
				case '>':
					question = question.substring(0, i) + "&#62" + question.substring(i + 1);
					i = i + 4;
					break;
				case '?':
					question = question.substring(0, i) + "&#63" + question.substring(i + 1);
					i = i + 4;
					break;
				case '@':
					question = question.substring(0, i) + "&#64" + question.substring(i + 1);
					i = i + 4;
					break;
				case '[':
					question = question.substring(0, i) + "&#91" + question.substring(i + 1);
					i = i + 4;
					break;
				case '\\':
					question = question.substring(0, i) + "&#92" + question.substring(i + 1);
					i = i + 4;
					break;
				case ']':
					question = question.substring(0, i) + "&#93" + question.substring(i + 1);
					i = i + 4;
					break;
				case '^':
					question = question.substring(0, i) + "&#94" + question.substring(i + 1);
					i = i + 4;
					break;
				case '_':
					question = question.substring(0, i) + "&#95" + question.substring(i + 1);
					i = i + 4;
					break;
				case '`':
					question = question.substring(0, i) + "&#96" + question.substring(i + 1);
					i = i + 4;
					break;
				case '{':
					question = question.substring(0, i) + "&#123" + question.substring(i + 1);
					i = i + 5;
					break;
				case '|':
					question = question.substring(0, i) + "&#124" + question.substring(i + 1);
					i = i + 5;
					break;
				case '}':
					question = question.substring(0, i) + "&#125" + question.substring(i + 1);
					i = i + 5;
					break;
				case '~':
					question = question.substring(0, i) + "&#126" + question.substring(i + 1);
					i = i + 5;
					break;
/*				case '¡':
					question = question.substring(0, i) + "&#160" + question.substring(i + 1);
					i = i + 5;
					break;*/
				/*case '¢':
					question = question.substring(0, i) + "&#161" + question.substring(i + 1);
					i = i + 5;
					break;
				case '£':
					question = question.substring(0, i) + "&#162" + question.substring(i + 1);
					i = i + 5;
					break;
				case '¤':
					question = question.substring(0, i) + "&#163" + question.substring(i + 1);
					i = i + 5;
					break;
				case '¥':
					question = question.substring(0, i) + "&#164" + question.substring(i + 1);
					i = i + 5;
					break;
				case '¦':
					question = question.substring(0, i) + "&#165" + question.substring(i + 1);
					i = i + 5;
					break;
				case '§':
					question = question.substring(0, i) + "&#166" + question.substring(i + 1);
					i = i + 5;
					break;
				case '¨':
					question = question.substring(0, i) + "&#167" + question.substring(i + 1);
					i = i + 5;
					break;
				case '©':
					question = question.substring(0, i) + "&#168" + question.substring(i + 1);
					i = i + 5;
					break;
				case 'ª':
					question = question.substring(0, i) + "&#169" + question.substring(i + 1);
					i = i + 5;
					break;
				case '«':
					question = question.substring(0, i) + "&#170" + question.substring(i + 1);
					i = i + 5;
					break;
				case '¬':
					question = question.substring(0, i) + "&#171" + question.substring(i + 1);
					i = i + 5;
					break;
				case '­':
					question = question.substring(0, i) + "&#172" + question.substring(i + 1);
					i = i + 5;
					break;
				case '®':
					question = question.substring(0, i) + "&#173" + question.substring(i + 1);
					i = i + 5;
					break;
				case '¯':
					question = question.substring(0, i) + "&#174" + question.substring(i + 1);
					i = i + 5;
					break;
				case '°':
					question = question.substring(0, i) + "&#176" + question.substring(i + 1);
					i = i + 5;
					break;
				case '±':
					question = question.substring(0, i) + "&#177" + question.substring(i + 1);
					i = i + 5;
					break;
				case '²':
					question = question.substring(0, i) + "&#178" + question.substring(i + 1);
					i = i + 5;
					break;
				case '³':
					question = question.substring(0, i) + "&#179" + question.substring(i + 1);
					i = i + 5;
					break;
				case '´':
					question = question.substring(0, i) + "&#180" + question.substring(i + 1);
					i = i + 5;
					break;
				case 'µ':
					question = question.substring(0, i) + "&#181" + question.substring(i + 1);
					i = i + 5;
					break;
				case '¶':
					question = question.substring(0, i) + "&#182" + question.substring(i + 1);
					i = i + 5;
					break;
				case '·':
					question = question.substring(0, i) + "&#183" + question.substring(i + 1);
					i = i + 5;
					break;
				case '¸':
					question = question.substring(0, i) + "&#184" + question.substring(i + 1);
					i = i + 5;
					break;
				case '¹':
					question = question.substring(0, i) + "&#185" + question.substring(i + 1);
					i = i + 5;
					break;
				case 'º':
					question = question.substring(0, i) + "&#186" + question.substring(i + 1);
					i = i + 5;
					break;
				case '»':
					question = question.substring(0, i) + "&#187" + question.substring(i + 1);
					i = i + 5;
					break;
				case '¼':
					question = question.substring(0, i) + "&#188" + question.substring(i + 1);
					i = i + 5;
					break;
				case '½':
					question = question.substring(0, i) + "&#189" + question.substring(i + 1);
					i = i + 5;
					break;
				case '¾':
					question = question.substring(0, i) + "&#190" + question.substring(i + 1);
					i = i + 5;
					break;
				case '¿':
					question = question.substring(0, i) + "&#191" + question.substring(i + 1);
					i = i + 5;
					break;
				case 'À':
					question = question.substring(0, i) + "&#192" + question.substring(i + 1);
					i = i + 5;
					break;
				case '�?':
					question = question.substring(0, i) + "&#193" + question.substring(i + 1);
					i = i + 5;
					break;
				case 'Â':
					question = question.substring(0, i) + "&#194" + question.substring(i + 1);
					i = i + 5;
					break;
				case 'Ã':
					question = question.substring(0, i) + "&#195" + question.substring(i + 1);
					i = i + 5;
					break;
				case 'Ä':
					question = question.substring(0, i) + "&#196" + question.substring(i + 1);
					i = i + 5;
					break;
				case 'Å':
					question = question.substring(0, i) + "&#197" + question.substring(i + 1);
					i = i + 5;
					break;
				case 'Æ':
					question = question.substring(0, i) + "&#198" + question.substring(i + 1);
					i = i + 5;
					break;
				case 'Ç':
					question = question.substring(0, i) + "&#199" + question.substring(i + 1);
					i = i + 5;
					break;
				case 'È':
					question = question.substring(0, i) + "&#200" + question.substring(i + 1);
					i = i + 5;
					break;
				case 'É':
					question = question.substring(0, i) + "&#201" + question.substring(i + 1);
					i = i + 5;
					break;
				case 'Ê':
					question = question.substring(0, i) + "&#202" + question.substring(i + 1);
					i = i + 5;
					break;
				case 'Ë':
					question = question.substring(0, i) + "&#203" + question.substring(i + 1);
					i = i + 5;
					break;
				case 'Ì':
					question = question.substring(0, i) + "&#204" + question.substring(i + 1);
					i = i + 5;
					break;
				case '�?':
					question = question.substring(0, i) + "&#205" + question.substring(i + 1);
					i = i + 5;
					break;
				case 'Î':
					question = question.substring(0, i) + "&#206" + question.substring(i + 1);
					i = i + 5;
					break;
				case '�?':
					question = question.substring(0, i) + "&#207" + question.substring(i + 1);
					i = i + 5;
					break;
				case '�?':
					question = question.substring(0, i) + "&#208" + question.substring(i + 1);
					i = i + 5;
					break;
				case 'Ñ':
					question = question.substring(0, i) + "&#209" + question.substring(i + 1);
					i = i + 5;
					break;
				case 'Ò':
					question = question.substring(0, i) + "&#210" + question.substring(i + 1);
					i = i + 5;
					break;
				case 'Ó':
					question = question.substring(0, i) + "&#211" + question.substring(i + 1);
					i = i + 5;
					break;
				case 'Ô':
					question = question.substring(0, i) + "&#212" + question.substring(i + 1);
					i = i + 5;
					break;
				case 'Õ':
					question = question.substring(0, i) + "&#213" + question.substring(i + 1);
					i = i + 5;
					break;
				case 'Ö':
					question = question.substring(0, i) + "&#214" + question.substring(i + 1);
					i = i + 5;
					break;
				case '×':
					question = question.substring(0, i) + "&#215" + question.substring(i + 1);
					i = i + 5;
					break;
				case 'Ø':
					question = question.substring(0, i) + "&#216" + question.substring(i + 1);
					i = i + 5;
					break;
				case 'Ù':
					question = question.substring(0, i) + "&#217" + question.substring(i + 1);
					i = i + 5;
					break;
				case 'Ú':
					question = question.substring(0, i) + "&#218" + question.substring(i + 1);
					i = i + 5;
					break;
				case 'Û':
					question = question.substring(0, i) + "&#219" + question.substring(i + 1);
					i = i + 5;
					break;
				case 'Ü':
					question = question.substring(0, i) + "&#220" + question.substring(i + 1);
					i = i + 5;
					break;
				case '�?':
					question = question.substring(0, i) + "&#221" + question.substring(i + 1);
					i = i + 5;
					break;
				case 'Þ':
					question = question.substring(0, i) + "&#222" + question.substring(i + 1);
					i = i + 5;
					break;
				case 'ß':
					question = question.substring(0, i) + "&#223" + question.substring(i + 1);
					i = i + 5;
					break;
				case 'à':
					question = question.substring(0, i) + "&#224" + question.substring(i + 1);
					i = i + 5;
					break;
				case 'á':
					question = question.substring(0, i) + "&#225" + question.substring(i + 1);
					i = i + 5;
					break;
				case 'â':
					question = question.substring(0, i) + "&#226" + question.substring(i + 1);
					i = i + 5;
					break;
				case 'ã':
					question = question.substring(0, i) + "&#227" + question.substring(i + 1);
					i = i + 5;
					break;
				case 'ä':
					question = question.substring(0, i) + "&#228" + question.substring(i + 1);
					i = i + 5;
					break;
				case 'å':
					question = question.substring(0, i) + "&#229" + question.substring(i + 1);
					i = i + 5;
					break;
				case 'æ':
					question = question.substring(0, i) + "&#230" + question.substring(i + 1);
					i = i + 5;
					break;
				case 'ç':
					question = question.substring(0, i) + "&#231" + question.substring(i + 1);
					i = i + 5;
					break;
				case 'è':
					question = question.substring(0, i) + "&#232" + question.substring(i + 1);
					i = i + 5;
					break;
				case 'é':
					question = question.substring(0, i) + "&#233" + question.substring(i + 1);
					i = i + 5;
					break;
				case 'ê':
					question = question.substring(0, i) + "&#234" + question.substring(i + 1);
					i = i + 5;
					break;
				case 'ë':
					question = question.substring(0, i) + "&#235" + question.substring(i + 1);
					i = i + 5;
					break;
				case 'ì':
					question = question.substring(0, i) + "&#236" + question.substring(i + 1);
					i = i + 5;
					break;
				case 'í':
					question = question.substring(0, i) + "&#237" + question.substring(i + 1);
					i = i + 5;
					break;
				case 'î':
					question = question.substring(0, i) + "&#238" + question.substring(i + 1);
					i = i + 5;
					break;
				case 'ï':
					question = question.substring(0, i) + "&#239" + question.substring(i + 1);
					i = i + 5;
					break;
				case 'ð':
					question = question.substring(0, i) + "&#240" + question.substring(i + 1);
					i = i + 5;
					break;
				case 'ñ':
					question = question.substring(0, i) + "&#241" + question.substring(i + 1);
					i = i + 5;
					break;
				case 'ò':
					question = question.substring(0, i) + "&#242" + question.substring(i + 1);
					i = i + 5;
					break;
				case 'ó':
					question = question.substring(0, i) + "&#243" + question.substring(i + 1);
					i = i + 5;
					break;
				case 'ô':
					question = question.substring(0, i) + "&#244" + question.substring(i + 1);
					i = i + 5;
					break;
				case 'õ':
					question = question.substring(0, i) + "&#245" + question.substring(i + 1);
					i = i + 5;
					break;
				case 'ö':
					question = question.substring(0, i) + "&#246" + question.substring(i + 1);
					i = i + 5;
					break;
				case '÷':
					question = question.substring(0, i) + "&#247" + question.substring(i + 1);
					i = i + 5;
					break;
				case 'ø':
					question = question.substring(0, i) + "&#248" + question.substring(i + 1);
					i = i + 5;
					break;
				case 'ù':
					question = question.substring(0, i) + "&#249" + question.substring(i + 1);
					i = i + 5;
					break;
				case 'ú':
					question = question.substring(0, i) + "&#250" + question.substring(i + 1);
					i = i + 5;
					break;
				case 'û':
					question = question.substring(0, i) + "&#251" + question.substring(i + 1);
					i = i + 5;
					break;
				case 'ü':
					question = question.substring(0, i) + "&#252" + question.substring(i + 1);
					i = i + 5;
					break;
				case 'ý':
					question = question.substring(0, i) + "&#253" + question.substring(i + 1);
					i = i + 5;
					break;
				case 'þ':
					question = question.substring(0, i) + "&#254" + question.substring(i + 1);
					i = i + 5;
					break;
				case 'ÿ':
					question = question.substring(0, i) + "&#255" + question.substring(i + 1);
					i = i + 5;
					break;
				case '–':
					question = question.substring(0, i) + "&#8211" + question.substring(i + 1);
					i = i + 6;
					break;
				case '—':
					question = question.substring(0, i) + "&#8212" + question.substring(i + 1);
					i = i + 6;
					break;
				case '‘':
					question = question.substring(0, i) + "&#8216" + question.substring(i + 1);
					i = i + 6;
					break;
				case '’':
					question = question.substring(0, i) + "&#8217" + question.substring(i + 1);
					i = i + 6;
					break;
				case '‚':
					question = question.substring(0, i) + "&#8218" + question.substring(i + 1);
					i = i + 6;
					break;
				case '“':
					question = question.substring(0, i) + "&#8220" + question.substring(i + 1);
					i = i + 6;
					break;
				case '�?':
					question = question.substring(0, i) + "&#8221" + question.substring(i + 1);
					i = i + 6;
					break;
				case '„':
					question = question.substring(0, i) + "&#8222" + question.substring(i + 1);
					i = i + 6;
					break;
				case '†':
					question = question.substring(0, i) + "&#8224" + question.substring(i + 1);
					i = i + 6;
					break;
				case '‡':
					question = question.substring(0, i) + "&#8225" + question.substring(i + 1);
					i = i + 6;
					break;
				case '•':
					question = question.substring(0, i) + "&#8226" + question.substring(i + 1);
					i = i + 6;
					break;
				case '…':
					question = question.substring(0, i) + "&#8230" + question.substring(i + 1);
					i = i + 6;
					break;
				case '‰':
					question = question.substring(0, i) + "&#8240" + question.substring(i + 1);
					i = i + 6;
					break;
				case '€':
					question = question.substring(0, i) + "&#8264" + question.substring(i + 1);
					i = i + 6;
					break;
				case '™':
					question = question.substring(0, i) + "&#8282" + question.substring(i + 1);
					i = i + 6;
					break;*/

				default:
					i = i + 1;
				}
				
			}
			return question;
		}
}
