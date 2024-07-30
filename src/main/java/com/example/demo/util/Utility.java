package com.example.demo.util;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Component
public class Utility {

    private final Cloudinary cloud;

    @Autowired
    public Utility(Cloudinary cloud) {
        this.cloud = cloud;
    }

    public String uploadImage(MultipartFile image) {
        try {
            Map uploadResult = cloud.uploader().upload(image.getBytes(), ObjectUtils.asMap("folder", "Project_Management_System"));
            return uploadResult.get("url").toString();
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public  void removeImage(String url) {
        try {
            String[] urlParts = url.split("/");
            String publicIdWithExtension = urlParts[urlParts.length - 1];
            String publicId = publicIdWithExtension.split("\\.")[0];
            System.out.println("Public ID: " + publicId);

            Map result = cloud.uploader().destroy(publicId, ObjectUtils.asMap("folder", "Project_Management_System"));
            System.out.println("Cloudinary Response: " + result);

            if ("ok".equals(result.get("result"))) {
                return;
            } else {
                throw new RuntimeException("Something went wrong in image removal from Cloudinary!");
            }
        } catch (IOException ex) {
            throw new RuntimeException("Image removal failed: " + ex.getMessage());
        }
    }
}
