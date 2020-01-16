package controllers;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import javax.imageio.ImageIO;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;


public class WebDriverFactory extends BrowserFactory {
	public static ThreadLocal<WebDriver> wd = new ThreadLocal<WebDriver>();
	public static String browser;
	public static String url;

	@BeforeClass
	public static void setupClass() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeMethod
	public void beforeMethod(Method method) throws Exception
	{
		String testName;
		System.out.println("Browser: " + Browser);
		System.out.println("WebsiteURL: " + WebsiteURL);
		new WebDriverFactory();
		testName = method.getName();
		if(testName.equals("testTableElementsText"))
			WebsiteURL="http://www.w3schools.com/html/html_tables.asp";
		WebDriver driver = WebDriverFactory.createDriver();
		driver.manage().window().maximize();
		setWebDriver(driver);
	}

	public void setWebDriver(WebDriver driver) {
		wd.set(driver);
	}

	public static WebDriver getWebDriver() {
		return wd.get();
	}

	public static void saveFullPageScreenshot(String name) throws IOException {
		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
				.takeScreenshot(getWebDriver());
		ImageIO.write(screenshot.getImage(), "PNG", new File(name));
	}

	@AfterMethod(alwaysRun = true, enabled = true)
	public void afterMethod(ITestResult result) throws Exception {
		Thread.sleep(2000);
		if (result.getStatus() == ITestResult.FAILURE) {
			saveFullPageScreenshot("./src/test/resources/Reports/Images/" + result.getTestClass().getName() + "."
					+ result.getMethod().getMethodName() + ".png");
		}
		getWebDriver().quit();
	}
}
