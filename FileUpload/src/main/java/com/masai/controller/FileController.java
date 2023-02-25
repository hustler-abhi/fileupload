package com.masai.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.masai.service.FileDataService;

@RestController
@RequestMapping("/image")
public class FileController {
	
	@Autowired
	FileDataService service;
	

	@PostMapping("/fileSystem")
	public ResponseEntity<String> uploadImageToFIleSystem(@RequestParam("image")
			MultipartFile file) throws IOException {
		System.out.println("in controller"+file);
		
		
		String uploadImage = service.uploadImageToFileSystem(file);
		 
		
		return new ResponseEntity<String>("uplod succesfully", HttpStatus.ACCEPTED);
	}

	@GetMapping("/fileSystem/{fileName}")
	public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable String fileName) throws IOException {
		byte[] imageData=service.downloadImageFromFileSystem(fileName);
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.valueOf("image/png"))
				.body(imageData);

	}

}
