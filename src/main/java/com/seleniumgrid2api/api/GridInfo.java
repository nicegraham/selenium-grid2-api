package com.seleniumgrid2api.api;

import com.google.gson.JsonObject;

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

  public GridInfo(JsonObject object) {
    try {
      proxyId = new URL(object.get("proxyId").getAsString());
      if ((proxyId.getHost() != null) && (proxyId.getPort() != -1)) {
        host = proxyId.getHost();
        port = proxyId.getPort();
      }
      internalKey = object.get("internalKey").getAsString();
      session = object.get("session").getAsString();
      inactivityTime = object.get("inactivityTime").getAsString();
      msg = object.get("msg").getAsString();
      success = object.get("success").getAsString();

    } catch (MalformedURLException e) {
      System.out.println("Error Parsing Grid Info.");
    }

  }
}
