package com.saucedemo.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.saucedemo.base.BaseClass;

public class ScreenShot extends BaseClass {

	public String screenShot(WebDriver driver, String filename) {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "\\ScreenShots\\" + filename + "_" + dateName + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(source, destination);
		} catch (Exception e) {
			e.getMessage();
		}
		
		return path;
	}
}