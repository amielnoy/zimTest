package pageObjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.initializePageObjects.PageFactoryInitializer;
import utils.ExplicitWaiting;
import io.qameta.allure.Allure;

import org.apache.log4j.Logger;
import utils.seleniumActions;

import java.util.List;

public class mainPage extends PageFactoryInitializer {
    final static Logger logger = Logger.getLogger(mainPage.class);

    protected WebElement languageSelector =getWebDriver().findElement(By.id("ChooseLanguageDlgOpener"));
    protected WebElement floatingMenu =getWebDriver().findElement(By.id("Col1"));
    protected List<WebElement> webElementsLangugesList=getWebDriver().findElements(By.xpath("//div[@id='Col1']/ul/li"));
    String lastLanguageMenuItemText;
    String allLanguages="";
    String allLanguagesSecondWay="";


    public String getLangListFromDom()
    {
       return allLanguagesSecondWay;
    }

    public List<WebElement> getLangList()
    {
        return webElementsLangugesList;
    }

    public String getLangChoosenFromMenuItem()
    {
        return lastLanguageMenuItemText;
    }

    public String mouseHover()
    {
        try {
            System.out.println("HOVERING LANG SELECTION BUTTON:");
            ExplicitWaiting.getWebDriver();
            ExplicitWaiting.explicitWaitVisibilityOfElement(languageSelector,10);
            mousehover(languageSelector);

            //get all the values of the floating menue
            for(WebElement listOfLangsElement:webElementsLangugesList)
                allLanguagesSecondWay+=listOfLangsElement.getText()+"\n";
            allLanguages=floatingMenu.getText();
            logger.debug("floating menu elements text:"+allLanguages);
            Allure.label("reporting floating elements after hovering: ",allLanguages);
            return allLanguagesSecondWay;
        } catch (Exception e) {
            Allure.label("reporting floating elements after hovering: AFTER EXCEPTION",allLanguages);
            e.printStackTrace();
        }
        return "ERROR_IN_ALL_RETURNED_LANGUAGES";
    }


    public String chooseLanguage(String languageIndex)
    {
        String langSelected="ERROR";
        String [] tmpArr=null;
        try {
            mouseHover();
            WebElement menuItem=getWebDriver().findElement(By.xpath("//*[@id='Col1']/ul/li["+languageIndex+"]/a"));
            lastLanguageMenuItemText=menuItem.getText();

            logger.debug("floating menu element= "+lastLanguageMenuItemText);
            Allure.label("floating menu element= ",menuItem.getText());

            ExplicitWaiting.driver =getWebDriver();
            ExplicitWaiting.explicitWaitVisibilityOfElement(menuItem,10);
            menuItem.click();
            languageSelector =getWebDriver().findElement(By.id("ChooseLanguageDlgOpener"));
            ExplicitWaiting.explicitWaitElementToBeClickable(languageSelector,10);
            langSelected=languageSelector.getText();

            logger.debug("language selected= "+langSelected);
            Allure.label("language selected=",langSelected);

            String currUrlAfterLangSelection=getWebDriver().getCurrentUrl();
            String [] currUrlAfterLangSelectionArr=currUrlAfterLangSelection.split("//");
            tmpArr=currUrlAfterLangSelectionArr[1].split("\\.");
            if(tmpArr[0].equals("www"))
                return "en";
            return tmpArr[0];
        } catch (Exception e) {
            Allure.label("reporting floating elements after hovering: AFTER EXCEPTION",tmpArr[0]);
            e.printStackTrace();
        }
        return "ERROR_IN_RETURNED_LANGUAGE";
    }
}
