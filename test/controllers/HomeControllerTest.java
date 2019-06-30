package controllers;

import io.netty.handler.codec.http.HttpUtil;
import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Http;
import play.mvc.Result;
import play.test.WithApplication;

import static org.junit.Assert.*;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.*;

public class HomeControllerTest extends WithApplication {

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }

    @Test
    public void testIndex() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/");

        Result result = route(app, request);
        assertEquals(OK, result.status());
    }

    @Test
    public void testJson() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/users");

        Result result = route(app, request);
        assertEquals("application/json", result.body().contentType().get());
    }

    @Test
    public void testUserInsert() {
        WidgetForm.User userToAdd = new WidgetForm.User("aFirstname", "aName");

        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(POST)
                .uri("/users")
                .bodyJson(JsonConverter.toJsonOne(userToAdd));

        Result result = route(app, request);

        request = new Http.RequestBuilder()
                .method(GET)
                .uri("/users");

        result = route(app, request);
        System.out.println(contentAsString(result));
        assertEquals("[{\"firstname\":\"aFirstname\",\"lastname\":\"aName\"}]", contentAsString(result));
    }

}
