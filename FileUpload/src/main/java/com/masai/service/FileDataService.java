package com.masai.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.masai.entity.FileData;
import com.masai.repositry.FileDataDao;

@Service
public class FileDataService {
	

	@Autowired
	private FileDataDao fileDataRepository;
	 
	private final String FOLDER_PATH="C:\\Users\\Abhishekkumar\\Desktop\\Images";
	
	 public String uploadImageToFileSystem( MultipartFile file) throws IOException {
	        String filePath=FOLDER_PATH;

	        
	        FileData f=new FileData();
	        f.setFilePath(filePath);
	        f.setName(file.getOriginalFilename());
	        f.setType(file.getContentType());
	        FileData fileData1= fileDataRepository.save(f);

	        file.transferTo(new File(filePath));

	        if (fileData1 != null) {
	            return "file uploaded successfully : " + filePath;
	        }
	        return "file not uploaded";
	    }

	    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
	        FileData fileData = fileDataRepository.findByName(fileName);
	        String filePath=fileData.getFilePath();
	        byte[] images = Files.readAllBytes(new File(filePath).toPath());
	        return images;
	    }
}
