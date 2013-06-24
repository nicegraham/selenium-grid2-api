package com.seleniumgrid2api.api;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;


public class GridInfo {

  private URL proxyId;
  private String host;
  private int port;
  private String internalKey;
  private String session;
  private String inactivityTime;
  private String msg;
  private String success;

  public URL getProxyId() {
    return proxyId;
  }

  public String getHost() {
    return host;
  }

  public int getPort() {
    return port;
  }

  public String getInternalKey() {
    return internalKey;
  }

  public String getSession() {
    return session;
  }

  public String getInactivityTime() {
    return inactivityTime;
  }

  public String getMsg() {
    return msg;
  }

  public String getSuccess() {
    return success;
  }

  public GridInfo(JSONObject object) {
    try {
      proxyId = new URL(object.getString("proxyId"));
      if ((proxyId.getHost() != null) && (proxyId.getPort() != -1)) {
        host = proxyId.getHost();
        port = proxyId.getPort();
      }
      internalKey = object.getString("internalKey");
      session = object.getString("session");
      inactivityTime = object.getString("inactivityTime");
      msg = object.getString("msg");
      success = object.getString("success");

    } catch (MalformedURLException | JSONException e) {
      System.out.println("Error Parsing Grid Info.");
    }

  }
}
