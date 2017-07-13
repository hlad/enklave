package com.enklave.game.Chat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.enklave.game.Config;

import org.json.JSONObject;

public class SendLocation {
    private String url = Config.API_URL;
    private String join = "/message/location/send/";
    public SendLocation() {
    }
    public void sendMessage(String message){
        final Preferences pref = Gdx.app.getPreferences("informationLog");
        String acces = pref.getString("accesstoken");
        JSONObject json = new JSONObject();
        json.accumulate("txt",message);
        HttpRequestBuilder builder = new HttpRequestBuilder();
        Net.HttpRequest request = builder.newRequest().method(Net.HttpMethods.POST).url(url + join).build();
        request.setHeader("Authorization", "Bearer " + acces);
        request.setHeader("Content-type", "application/json");
        request.setContent(json.toString());
        Gdx.net.sendHttpRequest(request, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
//                Gdx.app.log("response :"+httpResponse.getStatus().getStatusCode(),"message "+httpResponse.getResultAsString());
            }

            @Override
            public void failed(Throwable t) {
                //Gdx.app.log("response :","failed "+t);
            }

            @Override
            public void cancelled() {
                //Gdx.app.log("response :","cancel ");
            }
        });
    }
}
