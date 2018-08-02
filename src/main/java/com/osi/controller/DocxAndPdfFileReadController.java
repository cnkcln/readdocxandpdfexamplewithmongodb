package com.osi.controller;

import com.osi.service.DocxAndPdfFileReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/uploadfile")
public class DocxAndPdfFileReadController {

  @Autowired DocxAndPdfFileReadService docxFileReadService;

  @PostMapping
  public ResponseEntity<String> uploadFile(@RequestParam(name = "file") MultipartFile multipartFile)
      throws Exception {
    docxFileReadService.uploadFile(multipartFile);

    return new ResponseEntity<>("uploaded successfully", HttpStatus.OK);
  }
}
