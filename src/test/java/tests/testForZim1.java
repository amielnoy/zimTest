package tests;

import controllers.BaseMethod;
import controllers.DataProviders.textFileProvider;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.pages.mainPage;

@Test
public class testForZim1 extends BaseMethod {
    private mainPage currMainPage;

    @Test
    @Description("Hover above lang selector and extract all language options")
    public void TestHoverAndGetAllLanuageOptions() throws Exception {
        String actualLangugesOption,expectedLanguages;
        expectedLanguages="English\n" +
                "Deutsch\n" +
                "Suomi\n";
        currMainPage = new mainPage();
        actualLangugesOption=currMainPage.mouseHover();
        Allure.step("Actual languages="+actualLangugesOption);
        Allure.step("Expected languages="+expectedLanguages);

        Assert.assertEquals(actualLangugesOption,expectedLanguages,"not all the 3 lang's apear");
    }
    /////////////////////////////////////////////////////////
    //Compare languge selection extraction from two sources
    //1.after real selection 2.the url after selection
    /////////////////////////////////////////////////////////
    @Test(dataProvider = "textFileNameAsMethodName",dataProviderClass= textFileProvider.class)
    @Description("test that languages are chosen validly(english,German....")
    public void TestLanguagesAreChoosedValidly(String expectedLanguage) throws Exception {
        String actualLanguage="ERROR";
        String currLangPrefix="";
        String specialCaseFi="fi";

        currMainPage = new mainPage();
        actualLanguage=currMainPage.chooseLanguage(expectedLanguage);
        currLangPrefix=currMainPage.getLangChoosenFromMenuItem().substring(0,2).toLowerCase();



        if(true==currLangPrefix.equals("su"))
            currLangPrefix=specialCaseFi;
        Allure.step("Actual language="+actualLanguage);
        Allure.step("Expected language="+currLangPrefix);
        Assert.assertEquals(actualLanguage,
                currLangPrefix, "The expected Language & the actual are diffrent!!!");
    }

}
