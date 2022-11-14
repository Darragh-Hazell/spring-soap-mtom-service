package com.soap.mtom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import mtom_example_stuff.SendStuffRequest;
import mtom_example_stuff.SendStuffResponse;

import java.io.IOException;
import java.util.Arrays;

@Endpoint
public class StuffEndpoint {
    private static final Logger LOG = LoggerFactory.getLogger(StuffEndpoint.class);
    private static final String NAMESPACE_URI = "mtom-example-stuff";

    private final StuffService stuffService;

    public StuffEndpoint(StuffService stuffService) {
        this.stuffService = stuffService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "sendStuffRequest")
    @ResponsePayload
    public SendStuffResponse sendStuff(@RequestPayload SendStuffRequest request) {

        LOG.info(request.toString());
        LOG.info(request.getStuff().getName());
        LOG.info(Arrays.toString(request.getStuff().getSauce()));

        try {
            stuffService.byteToZip(
                    "/Users/darragh/Documents/Spring/mtom/src/main/resources",
                    "lmao.txt",
                    request.getStuff().getSauce()
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        SendStuffResponse response = new SendStuffResponse();
        response.setSuccess(true);
        return response;
    }

}
