package my.springboot.app.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import my.springboot.app.model.MyFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadService {
	
	private static final String FILE_SYS_DIR = "//Users//...//TargetFolder";
	private final String SUCCS_MSG = "File upload successful !";
	private final String ERROR_MSG = "Error Occured !";
	
	
	public String uploadFile(MultipartFile mfile){
		try {	
			
			Path path = Paths.get(FILE_SYS_DIR+"//"+mfile.getOriginalFilename());
			Files.write(path, mfile.getBytes());
			return SUCCS_MSG;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR_MSG;
		}
		
		
	}
	
	/*
	 * List all files in a directory with attributes
	 */
	
	public List<MyFile> readDir(){
		File folder = new File(FILE_SYS_DIR);
		File[] listOfFiles = folder.listFiles();
		List<MyFile> filesList = new ArrayList<MyFile>();
		try {
			for(File file: listOfFiles){
				MyFile fd = new MyFile();
				
				BasicFileAttributes attr = Files.readAttributes(Paths.get(FILE_SYS_DIR+"//"+file.getName()), BasicFileAttributes.class);
				fd.setFileName(file.getName());
				fd.setFileSize(attr.size());
				fd.setCreationDate(attr.creationTime().toString());
				fd.setModifiedDate(attr.lastModifiedTime().toString());
				filesList.add(fd);
			}
			return filesList;
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return filesList;
		}
	}

}
