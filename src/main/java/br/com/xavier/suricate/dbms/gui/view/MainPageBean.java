package br.com.xavier.suricate.dbms.gui.view;

import java.io.File;
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
	private File workspaceFolder;
	
	//XXX CONSTRUCTOR
	public MainPageBean() { }
	
	@PostConstruct
	private void initialize(){
	}
	
	//XXX VIEW METHODS
	public boolean renderBottomGroup(){
		if(workspaceFolder == null){
			return false;
		}
		
		return true;
	}
	
	//XXX FILE UPLOAD METHODS
	public boolean renderBtnUploadFile(){
		if(workspaceFolder == null){
			return false;
		}
		
		return true;
	}
	
	public void clearUploadedFile(){
		this.uploadedFile = null;
	}
	
	public void graphFileUploadListener(FileUploadEvent event){
		this.uploadedFile = event.getFile();
	}
	
	//XXX CHOOSE WORKSPACE METHODS
	public String getWorkspaceFolderAbsolutePath(){
		if(workspaceFolder != null){
			return workspaceFolder.getAbsolutePath();
		} else {
			return "No workspace loaded...";
		}
	}
	
	public void defineWorkspace(){
		workspaceFolder = service.getWorkspaceFolder();
	}
	
	//XXX SAVE WORKSPACE METHODS
	public boolean renderBtnSaveWorkspace(){
		if(workspaceFolder == null){
			return false;
		}
		
		return true;
	}
	
	public void saveWorkspace(){
		System.out.println("#> SAVE WORKSPACE METHOD CALLED");
	}
	
	//XXX GETTERS/SETTERS
	public File getWorkspaceFolder() {
		return workspaceFolder;
	}
	
	public void setWorkspaceFolder(File workspaceFolder) {
		this.workspaceFolder = workspaceFolder;
	}
	
	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}
	
	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

}
