package lib;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CalendarEvents {
	
	public static String calendarAction(WebDriver driver, String locatorType, String locatorValue,String data)
	{
	try {
	if(locatorType.equalsIgnoreCase("id"))
	{
	WebElement calendar = driver.findElement(By.id(locatorValue));
	List<WebElement> dates = calendar.findElements(By.tagName("td"));
	int totaldates= dates.size();
	for (int i=0; i<totaldates; i++) {
	String date = dates.get(i).getText();
	if (date.equals(data))
	{
	dates.get(i).click();}}}

	else if (locatorType.equalsIgnoreCase("xpath"))
	{
	WebElement calendar = driver.findElement(By.xpath(locatorValue));
	List<WebElement> dates = calendar.findElements(By.tagName("td"));
	int totaldates= dates.size();
	for (int i=0; i<totaldates; i++) {
	String date = dates.get(i).getText();
	if (date.equals(data))
	{
	dates.get(i).click();}}}
	return "pass";
	} catch (Exception e) {
	return e.getMessage();}}}



