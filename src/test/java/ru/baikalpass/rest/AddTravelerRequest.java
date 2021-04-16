package ru.baikalpass.rest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.text.ParseException;


public class AddTravelerRequest {
    String sessionID;

    @Test
    public void create() throws ParseException {
        RestAssured.baseURI = "https://baikalpass.ru/ausweiss/rest/api";
        String beginDate = DateGenerate.genDateMillisecond("2");
        String endDate = DateGenerate.genDateMillisecond("6");
        String beginDateString = DateGenerate.genDate("2");
        String endDateString = DateGenerate.genDate("6");

        JSONObject requestParams1 = new JSONObject();
            requestParams1.put("objectType", "TravelerRequest");
            requestParams1.put("protectedTerritoryUUID", "e5498be5-b1ce-c1a9-03be-fe01cfa2c0c1");
            requestParams1.put("beginDate", beginDate);
            requestParams1.put("endDate", endDate);
            requestParams1.put("beginDateString", beginDateString);
            requestParams1.put("endDateString", endDateString);
            requestParams1.put("comment", "");
        JSONObject requestParams2 = new JSONObject();
            requestParams2.put("objectType", "PlaceOfVisit");
            requestParams2.put("territoryName", "Б. Голоустное-Бугульдейка");
            requestParams2.put("routeUUID", "83818039-899e-dd7e-b1fe-e16f2743524f");
            requestParams2.put("beginDate", beginDate);
            requestParams2.put("endDate", endDate);
            requestParams2.put("beginDateString", beginDateString);
            requestParams2.put("endDateString", endDateString);
            requestParams2.put("WaysOfLivingUUID", "06a1043e-1a3d-928c-154c-0948024f0ee6");
            requestParams2.put("WayToTravelUUID", "0ad5653a-57df-8cd2-e4bf-865038651e30");
        JSONObject requestParams3 = new JSONObject();
            requestParams3.put("objectType", "Traveler");
            requestParams3.put("isMainTraveler", true);
            requestParams3.put("lastName", "test");
            requestParams3.put("firstName", "test");
            requestParams3.put("midName", "testtest");
            requestParams3.put("phone", "71212212212");
            requestParams3.put("email", "xxx@xxx.com");
            requestParams3.put("documentUUID", "");
            requestParams3.put("docParam1", "");
            requestParams3.put("docParam2", "");
            requestParams3.put("docParam3", "_");
            requestParams3.put("privilegeCategoryUUID", "053a8b7c-7035-0617-6f74-1635ef3ba35b");
            requestParams3.put("countryUUID", "b4f120dc-5808-c938-8463-8061e63205c1");
            requestParams3.put("city","");
            requestParams3.put("cityUUID","");


        JSONArray arrData = new JSONArray();
            arrData.add(requestParams1);
            arrData.add(requestParams2);
            arrData.add(requestParams3);

        JSONObject requestParams4 = new JSONObject();
        requestParams4.put("lang","ru");
        requestParams4.put("jsonFullData",arrData.toJSONString());

        RequestSpecification request = RestAssured.given();
            request.header("Content-Type", "application/json");
            request.body(requestParams4.toJSONString());

        Response response = request.post("/addTravelerRequest2");

        System.out.println();
        System.out.println("-------------------------------");
        System.out.println(requestParams4.toJSONString());
        System.out.println("-------------------------------");
        System.out.println("Response code ==> " + response.getStatusCode());
        System.out.println(response.getBody().asString());

        this.sessionID = response.jsonPath().getString("sessionId");
        //System.out.println(sessionID);

        String jsonString = response.asString();
        Assert.assertEquals(jsonString.contains("\"success\":true"), true);
        //return response;
    }

    @Test
    public void requestConfirmCode() throws ParseException {
        RestAssured.baseURI = "https://baikalpass.ru/ausweiss/rest/api";
        RequestSpecification request = RestAssured.given();
        request.formParam("lang", "ru");
        request.formParam("email", "xxx@xxx.com");
        request.formParam("sessionId", this.sessionID);
        Response response = request.post("/requestConfirmCode");
        System.out.println(response.getBody().asString());
        //return response;
    }
}
