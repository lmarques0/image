package io.spring.image.demo.application;

import io.spring.image.demo.domain.entity.Image;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/upload")
@Slf4j
@RequiredArgsConstructor
public class ImagesController {

    //*
    // {"name": "", "size":100} //application/json
    //*

    // mult-part/formdata
    //*

    @PostMapping
    public ResponseEntity save(@RequestParam("file")  MultipartFile file,
                                      @RequestParam("name")String name,
                                      @RequestParam("tags") List<String> tags
    ) {
        log.info("Recebendo tentativa de upload do arquivo: {}", file.getOriginalFilename());
        log.info("Content Type:{} ", file.getContentType());
        log.info("Media Type:{} ", MediaType.valueOf(file.getContentType()));

        Image image = Image.builder()
                .name(name)
                .tags(String.join(",", tags))
                .size(file.getSize())
//                    .extension(file.getContentType())
                .build();

        return ResponseEntity.ok().build();
    }
}