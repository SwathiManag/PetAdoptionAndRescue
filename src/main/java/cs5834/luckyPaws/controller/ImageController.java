package cs5834.luckyPaws.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cs5834.luckyPaws.entity.Image;
import cs5834.luckyPaws.respository.ImageRepository;
import cs5834.luckyPaws.util.ImageUtil;

@RestController
@CrossOrigin(origins = { "https://luckypaws.discovery.cs.vt.edu", "http://localhost:4000", "http://localhost:8081" })
//@CrossOrigin() // open for all ports
@RequestMapping("/api")
public class ImageController {

  @Autowired
  ImageRepository imageRepository;

  @PostMapping("/image")
  public ResponseEntity<ImageUploadResponse> uplaodImage(@RequestParam("image") MultipartFile file)
          throws IOException {

      imageRepository.save(Image.builder()
              .name(file.getOriginalFilename())
              .type(file.getContentType())
              .image(ImageUtil.compressImage(file.getBytes())).build());
      return ResponseEntity.status(HttpStatus.OK)
              .body(new ImageUploadResponse("Image uploaded successfully: " +
                      file.getOriginalFilename()));
  }

  @GetMapping(path = {"/image/info/{name}"})
  public Image getImageDetails(@PathVariable("name") String name) throws IOException {

      final Optional<Image> dbImage = imageRepository.findByName(name);

      return Image.builder()
              .name(dbImage.get().getName())
              .type(dbImage.get().getType())
              .image(ImageUtil.decompressImage(dbImage.get().getImage())).build();
  }
  
  @GetMapping(path = {"/image/ID/{id}"})
  public ResponseEntity<byte[]> getImageByID(@PathVariable("id") Long ID) throws IOException {

      final Optional<Image> dbImage = imageRepository.findById(ID);

      return ResponseEntity
              .ok()
              .contentType(MediaType.valueOf(dbImage.get().getType()))
              .body(ImageUtil.decompressImage(dbImage.get().getImage()));
  }

  @GetMapping(path = {"/image/name/{name}"})
  public ResponseEntity<byte[]> getImage(@PathVariable("name") String name) throws IOException {

      final Optional<Image> dbImage = imageRepository.findByName(name);

      return ResponseEntity
              .ok()
              .contentType(MediaType.valueOf(dbImage.get().getType()))
              .body(ImageUtil.decompressImage(dbImage.get().getImage()));
  }
}
