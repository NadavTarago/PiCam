package com.example.restservice;

import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class PiController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }



    @GetMapping(
            value = "/getImage",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    // Get image (assuming image is under "resources" folder
    public @ResponseBody byte[] getImageWithMediaType() throws IOException {


        Command takePic = new Command();
        if (takePic.run()) {
            System.out.println("picutre hs been taken");
        }
	InputStream in = new FileInputStream("/home/pi/Pictures/new_image.jpg");
       // InputStream in =getClass().getResourceAsStream("/image.jpg");
        return IOUtils.toByteArray(in);
    }


}
