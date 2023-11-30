package com.example.firebasestorage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping
    public ResponseEntity<String> create(@RequestParam("file") MultipartFile[] files) {

        String res = "";
        for (MultipartFile file : files) {
            try {
                String fileName = imageService.save(file);
                String imageUrl = imageService.getImageUrl(fileName);
                res = imageUrl;
            } catch (Exception e) {
                res = e.getMessage();
                System.out.println(e.getMessage());
            }
        }

        return ResponseEntity.ok(res);
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@RequestBody String fileName) {
        String res = "";
        try {
            imageService.delete(fileName);
            res = "Success";
        } catch (Exception e) {
            res = e.getMessage();
        }

        return ResponseEntity.ok(res);
    }
}
