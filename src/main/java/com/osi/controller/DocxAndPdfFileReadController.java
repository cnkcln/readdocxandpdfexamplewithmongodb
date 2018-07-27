package com.osi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.osi.service.DocxAndPdfFileReadService;

@RestController
@RequestMapping("/uploadfile")
public class DocxAndPdfFileReadController {

  @Autowired
  DocxAndPdfFileReadService docxFileReadServiceImpl;

  @PostMapping
  public ResponseEntity<String> uploadFile(
      @RequestParam(name = "file") MultipartFile multipartFile) throws Exception {
    docxFileReadServiceImpl.uploadFile(multipartFile);

    return new ResponseEntity<>("uploaded successfully", HttpStatus.OK);
  }
}
