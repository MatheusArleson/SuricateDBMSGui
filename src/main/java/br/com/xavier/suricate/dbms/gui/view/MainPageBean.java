package br.com.xavier.suricate.dbms.gui.view;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.xavier.suricate.dbms.gui.service.MainPageService;

@Scope("view")
@Controller
public class MainPageBean implements Serializable {

	private static final long serialVersionUID = -803115359056989054L;
	
	//XXX DEPENDENCIES
	@Autowired
	private MainPageService service;
	
	//XXX PROPERTIES
	private UploadedFile uploadedFile;
	
	//XXX CONSTRUCTOR
	public MainPageBean() { }
	
	@PostConstruct
	private void initialize(){
		
	}
	
	//XXX FILE UPLOAD METHODS
	public void graphFileUploadListener(FileUploadEvent event){
		this.uploadedFile = event.getFile();
	}
	
	//XXX GETTERS/SETTERS
	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}
	
	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

}
