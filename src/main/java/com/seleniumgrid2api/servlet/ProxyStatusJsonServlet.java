package com.seleniumgrid2api.servlet;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.openqa.grid.common.exception.GridException;
import org.openqa.grid.internal.ProxySet;
import org.openqa.grid.internal.Registry;
import org.openqa.grid.internal.RemoteProxy;
import org.openqa.grid.web.servlet.RegistryBasedServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;

public class ProxyStatusJsonServlet extends RegistryBasedServlet {

  private static final long serialVersionUID = -1975392591408983229L;

  public ProxyStatusJsonServlet() {
    this(null);
  }

  public ProxyStatusJsonServlet(Registry registry) {
    super(registry);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    doPost(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    process(req, resp);

  }

  protected void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("text/json");
    response.setCharacterEncoding("UTF-8");
    response.setStatus(200);
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    response.getWriter().print(gson.toJson(getResponse()));
    response.getWriter().close();
  }

  private JsonObject getResponse() throws IOException {
    JsonObject requestJSON = new JsonObject();
    ProxySet proxies = this.getRegistry().getAllProxies();
    Iterator<RemoteProxy> iterator = proxies.iterator();
    JsonArray busyProxies = new JsonArray();
    JsonArray freeProxies = new JsonArray();
    while (iterator.hasNext()) {
      RemoteProxy eachProxy = iterator.next();
      JsonObject proxyInfo = eachProxy.getOriginalRegistrationRequest().getAssociatedJSON();
      if (eachProxy.isBusy()) {
        busyProxies.add(proxyInfo);
      } else {
        freeProxies.add(proxyInfo);
      }
    }
    requestJSON.add("BusyProxies", busyProxies);
    requestJSON.add("FreeProxies", freeProxies);

    return requestJSON;
  }
}
