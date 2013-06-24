package com.seleniumgrid2api.api;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.remote.SessionId;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public final class GridInfoExtractor {

  private GridInfoExtractor() {
  }

  public static GridInfo getHostNameAndPort(String hubHost, int hubPort, SessionId session) {

    GridInfo retVal = null;

    try {
      HttpHost host = new HttpHost(hubHost, hubPort);
      DefaultHttpClient client = new DefaultHttpClient();
      URL sessionURL = new URL("http://" + hubHost + ":" + hubPort + "/grid/api/testsession?session=" + session);
      BasicHttpEntityEnclosingRequest basicHttpEntityEnclosingRequest = new BasicHttpEntityEnclosingRequest("POST", sessionURL.toExternalForm());
      HttpResponse response = client.execute(host, basicHttpEntityEnclosingRequest);
      if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
        JSONObject object = extractObject(response);
        retVal = new GridInfo(object);
      } else {
        System.out.println("Problem connecting to Grid Server");
      }
    } catch (JSONException | IOException  e) {
      throw new RuntimeException("Failed to acquire remote webdriver node and port info", e);
    }
    return retVal;
  }

  private static JSONObject extractObject(HttpResponse resp) throws IOException, JSONException {
    try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resp.getEntity().getContent()))) {
      StringBuilder stringBuilder = new StringBuilder();
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        stringBuilder.append(line);
      }
      bufferedReader.close();
      return new JSONObject(stringBuilder.toString());
    } catch (Exception e) {
      System.out.println("error" + e.toString());
    }
    return null;
  }
}
