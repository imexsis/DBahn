package de.dbahn.pages;

import de.dbahn.utilities.BrowserUtils;
import de.dbahn.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class SearchReiseauskunftPage extends BasePage {

    @FindBy(id="js-auskunft-autocomplete-from")
    public WebElement cityStart;

    @FindBy(id="js-auskunft-autocomplete-to")
    public WebElement cityDest;

    @FindBy(className = "js-submit-btn")
    public WebElement submitSearchRA;

    @FindBy(xpath = "//*[@id='querySummaryTextual']/div[1]/div/div[1]")
    public WebElement citySummaryResult;

    @FindBy(id = "tp_overview_headline_date")
    public WebElement resultStartDate;

    @FindBy(css = "[id^='overview_updateC']")
    public List<WebElement> journeyResults;

    public List<String> getDepatureTimes(){
        List<String> depTimes = new ArrayList<>();
        for(int i=0; i< journeyResults.size();i++){
            String row = "#overview_updateC0-"+i+"> tr.firstrow > td.time";
            depTimes.add(Driver.get().findElement(By.cssSelector(row)).getText().split(" ")[0]);
        }
        return depTimes;
    }

    public List<String> getArrivalTimes(){
        List<String> arrTimes = new ArrayList<>();
        for(int i=0; i< journeyResults.size();i++){
            String row = "#overview_updateC0-"+i+"> tr.last > td.time";
            arrTimes.add(Driver.get().findElement(By.cssSelector(row)).getText().split(" ")[0]);
        }
        return arrTimes;
    }

    public List<String> getDuration(){
        List<String> duration = new ArrayList<>();
        for(int i=0; i< journeyResults.size();i++){
            String row = "#overview_updateC0-"+i+"> tr.firstrow > td.duration.lastrow";
            duration.add(Driver.get().findElement(By.cssSelector(row)).getText().split(" ")[0]);
        }
        return duration;
    }

    public List<String> getDepTrainCodes(){
        List<String> depTrainCodes = new ArrayList<>();

        for(int i=0; i< journeyResults.size();i++) {
            String details = "//a[@id='linkDtlC0-"+i+"']";
            WebElement detailsEinAus= Driver.get().findElement(By.xpath(details));
            detailsEinAus.click();
            BrowserUtils.waitFor(1);

            String depLocation = "#updateC0-" + i + "> table > tbody > tr.first.\\30 > td.nowrap.lastrow.products > span > a";
            depTrainCodes.add(Driver.get().findElement(By.cssSelector(depLocation)).getText());
            detailsEinAus.click();
            BrowserUtils.waitFor(1);
        }
        return depTrainCodes;
    }

    public List<String> getArrTrainCodes(){
        List<String> arrTrainCodes = new ArrayList<>();

        for(int i=0; i< journeyResults.size();i++){
            String details = "//a[@id='linkDtlC0-"+i+"']";
            WebElement detailsEinAus= Driver.get().findElement(By.xpath(details));
            detailsEinAus.click();
            BrowserUtils.waitFor(1);

            String umstLocation = "#overview_updateC0-"+i+"> tr.firstrow > td.changes.lastrow";
            int umst = Integer.parseInt(Driver.get().findElement(By.cssSelector(umstLocation)).getText());

            String arrLocation = "#updateC0-"+i+"> table > tbody > tr.first.\\"+(30+umst)+" > td.nowrap.lastrow.products > span > a";

            arrTrainCodes.add(Driver.get().findElement(By.cssSelector(arrLocation)).getText());
            detailsEinAus.click();
            BrowserUtils.waitFor(1);
        }
        return arrTrainCodes;
    }


}
