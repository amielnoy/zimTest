package tests;

import controllers.ApplicationConfig;
import controllers.BaseMethod;
import controllers.DataProviders.textFileProvider;
import io.qameta.allure.Description;
import org.eclipse.core.runtime.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.pages.TablePage;
import utils.ExplicitWaiting;

import java.util.HashMap;

@Test
public class testTableForZim2 extends BaseMethod {
    @BeforeMethod
    public void  setup(){
        //ApplicationConfig.setWebsiteUrl("http://www.w3schools.com/html/html_tables.asp");
        WebsiteURL = appConfig.getWebsiteUrl();
    }


    @Test(dataProvider = "textFileNameAsMethodName",dataProviderClass= textFileProvider.class)
    @Description("test that string values exist in the table")
    public void testTableElementsText(String tableColumnName,String valueToSearchInColumn,String expectedTableCellValue) {
        HashMap searchedColumnNameToIndexMap =new HashMap<String, Integer>();

        searchedColumnNameToIndexMap.put("Company",1);
        searchedColumnNameToIndexMap.put("Contact",2);
        searchedColumnNameToIndexMap.put("Country",3);

        TablePage tablePage=new TablePage();
        WebElement tableWebElement=getWebDriver().findElement(By.id("customers"));


        int intExpectedColumnIndex=(int)searchedColumnNameToIndexMap.get(tableColumnName);
        try {
            boolean testResult=tablePage.verifyTableCellText(tableWebElement,
                    (int)searchedColumnNameToIndexMap.get(tableColumnName),
                    valueToSearchInColumn,
                    intExpectedColumnIndex,
                    expectedTableCellValue);
            Assert.isTrue(testResult);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }


}
