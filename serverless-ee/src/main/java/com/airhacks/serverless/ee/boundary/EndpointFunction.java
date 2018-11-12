package com.airhacks.serverless.ee.boundary;

import static com.airhacks.serverless.ee.boundary.Logger.log;
import com.fnproject.fn.api.InputEvent;
import com.fnproject.fn.api.OutputEvent;
import java.io.InputStream;
import java.io.StringWriter;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonWriter;
import sl.ee.Boundary;

public class EndpointFunction {

    private final SeContainer cdiRuntime;

    public EndpointFunction() {
        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        this.cdiRuntime = initializer.initialize();

    }

    public OutputEvent handleRequest(InputEvent input) {
        log(this, "receiving input: " + input);
        JsonObject inputAsJson = input.consumeBody(this::convert);
        log(this, "input converted: " + inputAsJson);
        JsonObject output = this.handleConvertedRequest(inputAsJson);
        log(this, "sending back output: " + output);

        StringWriter writer = new StringWriter();
        JsonWriter jsonWriter = Json.createWriter(writer);
        jsonWriter.writeObject(output);

        return OutputEvent.fromBytes(writer.getBuffer().toString().getBytes(), 200, "application/json");
    }

    public JsonObject handleConvertedRequest(JsonObject input) {
        Instance<Boundary> boundaryInstance = this.cdiRuntime.select(Boundary.class);
        Boundary boundary = boundaryInstance.get();
        log(this, "boundary implementation found: " + boundary.getClass().getName());
        return boundary.handleRequest(input);
    }

    JsonObject convert(InputStream stream) {
        try (JsonReader reader = Json.createReader(stream)) {
            return reader.readObject();
        }
    }

}