
package com.airhacks.echo.boundary;

import javax.inject.Inject;
import javax.json.JsonObject;
import sl.ee.Boundary;

/**
 *
 * @author airhacks.com
 */
public class EchoFunction implements Boundary {

    @Inject
    EchoProvider provider;


    @Override
    public JsonObject handleRequest(JsonObject input) {
        return this.provider.handleRequest(input);
    }

}
