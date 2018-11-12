
package com.airhacks.echo.boundary;

import javax.json.Json;
import javax.json.JsonObject;

/**
 *
 * @author airhacks.com
 */
public class EchoProvider {

    public JsonObject handleRequest(JsonObject input) {
        return Json.createObjectBuilder(input).add("message", "thanks for your message").build();
    }

}
