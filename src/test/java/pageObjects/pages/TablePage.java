package pageObjects.pages;

import controllers.WebDriverFactory;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import pageObjects.initializePageObjects.PageFactoryInitializer;
import utils.ExplicitWaiting;

import java.util.HashMap;

public class TablePage extends PageFactoryInitializer {
    final static Logger logger = Logger.getLogger(TablePage.class);

    public boolean verifyTableCellText(WebElement table,
                                       int searchColumn,
                                       String searchText,
                                       int returnColumnText,
                                       String expectedText) throws Exception {
        ExplicitWaiting.explicitWaitVisibilityOfElement(table,10);
        String actualCellText=getTableCellText(table,searchColumn,searchText,returnColumnText);

        Allure.step("searched column number="+searchColumn);
        Allure.step("searched text to find correct row="+searchText);
        Allure.step("My Search RESULT="+actualCellText);

        return actualCellText==expectedText;
    }


    protected String getTableCellText(WebElement table,
                                   int searchColumn,
                                    String searchText,
                                    int returnColumnText) throws Exception {

        try {
            String cellText=getTableCellTextByXpath(table,searchColumn, searchText, returnColumnText);
            return cellText;
        } catch (Exception e) {
            logger.debug("getTableCellTextByXpath(table,searchColumn, searchText, returnColumnText) throwed exception!!");
            e.printStackTrace();
        }
        return "ERROR";
    }

    @Step("using value at the row get value searched")
    public String getTableCellTextByXpath(
            WebElement table,
            int searchColumn,
            String searchText,
            int returnColumnText) throws Exception {
        String tableCellText;
        try{
            if ((table == null) || searchColumn <=0 || searchText == "")
                return "ERROR";

            WebElement TableCell = table.findElement(By.xpath("//tbody/tr[contains(.,'" + searchText + "')]/td[" + searchColumn + "]"));
            tableCellText = TableCell.getText();

            if (false == tableCellText.equals("")) {
                return TableCell.getText();
            } else
                return "ERROR";
        }catch(Exception e){
            e.printStackTrace();
        }
        return "ERROR";
    }
}