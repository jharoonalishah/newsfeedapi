package com.codetask.alfutaim.dailynewsfeed.app.controller;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.codetask.alfutaim.dailynewsfeed.ApplicationContext;
import com.codetask.alfutaim.dailynewsfeed.app.constant.AppNewsApiCredentials;
import com.codetask.alfutaim.dailynewsfeed.app.interfacer.Callable;
import com.codetask.alfutaim.dailynewsfeed.core.controller.BaseController;
import com.codetask.alfutaim.dailynewsfeed.core.service.VollySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class AppNewsApiController extends BaseController {

    public List<Map<String, String>> newsApiFeedCall(String url, final Callable serverCall) {

        final List<Map<String, String>> tmp = new ArrayList<>();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, AppNewsApiCredentials.NEWS_API_URL, null, new Response.Listener<JSONObject>() {


                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            final JSONArray articles = response.getJSONArray("articles");
                            serverCall.onCompletion(articles);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // todo handle error
                    }
                });

        VollySingleton.getInstance(ApplicationContext.getContext()).addToRequestQueue(jsonObjectRequest);

        return tmp;
    }
}
