package com.soap.mtom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;

@Service
public class StuffService {

    private static final Logger LOG = LoggerFactory.getLogger(StuffService.class);

    public void byteToZip(String path, String name, byte[] data) throws IOException {

        byte[] content = Base64.getDecoder().decode(data);

        try (FileOutputStream fileOutputStream = new FileOutputStream(path+"/"+name)) {
            fileOutputStream.write(content);
        }

    }
}
