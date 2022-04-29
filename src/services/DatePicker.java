/**
 * This is the utility class implementation "DatePicker" which deals with the
 * date related operations when handling date time related data.
 * 
 * @author IT19075754 Jayasinghe D.T.
 */

package services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DatePicker {

	public DatePicker() {

	}

	//returns the current date formatted using DateTimeFormatter
	public String getCurrentDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		return dtf.format(now);
	}
}
