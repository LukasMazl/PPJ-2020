package cz.mazl.tul.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class FileController {

    @RequestMapping(path = "/download/{country}/{city}", method = RequestMethod.GET)
    public ResponseEntity<Resource> download(@PathVariable String country, @PathVariable String city) {

        String content = "testString";

        String filename = city + "-" + country + ".txt";

        HttpHeaders header = new HttpHeaders();
        prepareHeader(header, filename);

        ByteArrayResource resource = new ByteArrayResource(content.getBytes());

        return ResponseEntity.ok()
                .headers(header)
                .contentLength(content.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }

    private void prepareHeader(HttpHeaders httpHeaders, String filename) {
        httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);
        httpHeaders.add("Cache-Control", "no-cache, no-store, must-revalidate");
        httpHeaders.add("Pragma", "no-cache");
        httpHeaders.add("Expires", "0");
    }

    @RequestMapping(path = "/uploadFile/{country}/{city}", method = RequestMethod.POST)
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file, @PathVariable String country, @PathVariable String city) {
        try {
            String content = new String(file.getBytes());
            System.out.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(null);
    }
}
