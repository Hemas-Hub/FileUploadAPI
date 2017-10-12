package my.springboot.app.controller;

import java.io.File;
import java.util.List;

import my.springboot.app.model.MyFile;
import my.springboot.app.service.FileUploadService;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class FileUploadController {
	
	@Autowired
	FileUploadService service;
	
	@RequestMapping(value="/welcome", method= RequestMethod.GET)
	public String welcome(){
		return "uploadForm";
	}
	
	@RequestMapping(value="/upload", method= RequestMethod.POST)
	public String uploadFile(@RequestParam("userFile") MultipartFile userFile, Model model){
		try{
			String message = service.uploadFile(userFile);
			model.addAttribute("message", message);
			return "uploadForm";
		}
		catch(Exception e){
			System.out.println(e);
			model.addAttribute("message", "Error Occured while uploading");
			return "uploadForm";
		}
		
	}
	
	@RequestMapping(value="/lookupFiles", method= RequestMethod.GET)
	public String getFileDetails(Model model){
		List<MyFile> files = service.readDir();
		model.addAttribute("fileDetails", files);
		return "details";
	}

}
