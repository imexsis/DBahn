package de.dbahn.step_definitions;

import de.dbahn.pages.SearchReiseauskunftPage;
import de.dbahn.utilities.BrowserUtils;
import de.dbahn.utilities.ConfigurationReader;
import de.dbahn.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class SearchReiseauskunft {

    List<String> depTimesUI, arrTimesUI, depTrainCodesUI, arrTrainCodesUI, durationUI;

    @Given("the user is on the homepage")
    public void the_user_is_on_the_homepage() {
        String url = ConfigurationReader.get("url");
        Driver.get().get(url);
    }

    @When("the user searchs from {string} to {string} with default inputs")
    public void the_user_searchs_from_to_with_default_inputs(String cityStart, String cityDest) {

        SearchReiseauskunftPage srp = new SearchReiseauskunftPage();

        srp.cityStart.sendKeys(cityStart);
        srp.cityDest.sendKeys(cityDest);
        srp.submitSearchRA.click();
    }

    @Then("the search result topic should show the cities names {string} {string}")
    public void theSearchResultTopicShouldShowTheCitiesNames(String cityStart, String cityDest){
        SearchReiseauskunftPage srp = new SearchReiseauskunftPage();

        String[] summaryTextWords = srp.citySummaryResult.getText().toLowerCase().split(" ");

        String actualCityStart = summaryTextWords[0]+" "+summaryTextWords[1];
        String actualCityDest = summaryTextWords[2]+" "+summaryTextWords[3];


        //codes for only DEMO start
        System.out.println("actualCityStart = " + actualCityStart);
        System.out.println("actualCityDest = " + actualCityDest);
        BrowserUtils.waitFor(2);
        //codes for only DEMO end

        Assert.assertEquals("Verify Summary Start City",cityStart.toLowerCase(),actualCityStart);
        Assert.assertEquals("Verify Summary Start City",cityStart.toLowerCase(),actualCityStart);
    }

    @Then("the page title should be {string}")
    public void thePageTitleShouldBe(String expectedTitle) {

        String actualTitel = Driver.get().getTitle();

        Assert.assertEquals("Verify Page Titel", expectedTitle, actualTitel);

        //codes for only DEMO start
        System.out.println("actualTitel = " + actualTitel);
        //codes for only DEMO end

    }

    @And("get the journey datas from UI")
    public void getTheJourneyDatasFromUI() {
        BrowserUtils.waitForPageToLoad(10);
        SearchReiseauskunftPage srp = new SearchReiseauskunftPage();

        depTimesUI = srp.getDepatureTimes();
        arrTimesUI = srp.getArrivalTimes();
        depTrainCodesUI = srp.getDepTrainCodes();
        arrTrainCodesUI = srp.getArrTrainCodes();
        durationUI = srp.getDuration();

    }

    @Then("verify UI start and arrive hours with API {string} {string} hours data")
    public void verifyUIStartAndArriveHoursWithAPIHoursData(String cityStart, String cityDest) {
        String timeNow = String.valueOf(LocalDateTime.now()).substring(0, 16);
        String bearerToken = ConfigurationReader.get("bearerToken");
        // Get City IDs from API
        String urlCity = ConfigurationReader.get("api.uri") + "/location/{id}";
        // Get CityStart ID from API
        Response resCityStart = given().header("Authorization", bearerToken)
                .pathParam("id", cityStart)
                .when().get(urlCity);

        List<Map<String, Object>> mappedCityStart = resCityStart.body().as(List.class);

        double idStart = (double) mappedCityStart.get(0).get("id");
        int cityStartID = (int) idStart;
        System.out.println(cityStart + " cityStartID = " + cityStartID);

        // Get CityDest ID from API
        Response resCityDest = given().header("Authorization", bearerToken)
                .pathParam("id", cityDest)
                .when().get(urlCity);

        List<Map<String, Object>> mappedCityDest = resCityDest.body().as(List.class);

        double idDest = (double) mappedCityDest.get(0).get("id");
        int cityDestID = (int) idDest;
        System.out.println(cityDest + " cityDestID = " + cityDestID);

        // Get CityStart API time data

        String urlCityStartData = ConfigurationReader.get("api.uri") + "/departureBoard/{id}";
        Response resCityStartDatas = given().header("Authorization", bearerToken)
                .queryParam("date", timeNow)
                .pathParam("id", cityStartID)
                .when().get(urlCityStartData);

        System.out.println(resCityStartDatas.statusCode());

        List<Map<String, Object>> mappedCityStartDatas = resCityStartDatas.body().as(List.class);

        // Verify UI-API CityStart Time
        for (int i = 0; i < depTrainCodesUI.size(); i++) {
            for (Map<String, Object> map : mappedCityStartDatas) {
                if (map.containsValue(depTrainCodesUI.get(i))) {

                    String expectedTimeAPI = String.valueOf(map.get("dateTime")).substring(11);
                    System.out.println(i + "-expectedDepTimeAPI = " + expectedTimeAPI);
                    String actualTimeUI = depTimesUI.get(i);
                    System.out.println(i + "-actualDepTimeUI = " + actualTimeUI);
                    Assert.assertEquals("Verify start hour UI-API ", expectedTimeAPI, actualTimeUI);
                    break;
                }
            }
        }

        // Get CityDest API time data

        int firstDepTimeH = Integer.parseInt(depTimesUI.get(0).split(":")[0]);
        int firstDepTimeMin = Integer.parseInt(depTimesUI.get(0).split(":")[1]);
        int firstDepTotalMin = firstDepTimeH*60+firstDepTimeMin;

        int firstArrTimeH = Integer.parseInt(arrTimesUI.get(0).split(":")[0]);
        int firstArrTimeMin = Integer.parseInt(arrTimesUI.get(0).split(":")[1]);
        int firstArrTotalMin = firstArrTimeH*60+firstArrTimeMin;

        LocalDate dateDest = LocalDate.now();

        if(firstArrTotalMin<firstDepTotalMin){
            dateDest = dateDest.plusDays(1);
        }

        String timeFirstArrival = String.valueOf(dateDest)+"T"+arrTimesUI.get(0);

        String urlCityDestData = ConfigurationReader.get("api.uri") + "/arrivalBoard/{id}";
        Response resCityDestDatas = given().header("Authorization", bearerToken)
                .queryParam("date", timeFirstArrival)
                .pathParam("id", cityDestID)
                .when().get(urlCityDestData);

        System.out.println(resCityDestDatas.statusCode());

        List<Map<String, Object>> mappedCityDestDatas = resCityDestDatas.body().as(List.class);

        // Verify UI-API CityDest Time
        for (int j = 0; j < arrTrainCodesUI.size(); j++) {
            for (Map<String, Object> map : mappedCityDestDatas) {
                if (map.containsValue(arrTrainCodesUI.get(j))) {
                    String expectedTimeAPI = String.valueOf(map.get("dateTime")).substring(11);
                    System.out.println(j+"-expectedArrTimeAPI = " + expectedTimeAPI);
                    String actualTimeUI = arrTimesUI.get(j);
                    System.out.println(j+"-actualArrTimeUI = " + actualTimeUI);
                    Assert.assertEquals("Verify start hour UI-API ", expectedTimeAPI, actualTimeUI);
                    break;
                }
            }
        }

    }

}
