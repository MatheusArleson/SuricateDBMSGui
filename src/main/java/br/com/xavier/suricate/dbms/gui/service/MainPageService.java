package br.com.xavier.suricate.dbms.gui.service;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import org.springframework.stereotype.Service;

@Service
public class MainPageService {

	public File getWorkspaceFolder() {
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
			//silently ignore exception
		}
		
		JFrame frame = new JFrame();
		frame.setVisible(false);

		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("user.home"));
		chooser.setDialogTitle("Select Workspace folder...");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		int result = chooser.showOpenDialog(frame);
		
		if (result == JFileChooser.APPROVE_OPTION) {
			File workspaceFolder = chooser.getSelectedFile();
			System.out.println("Selected file: " + workspaceFolder.getAbsolutePath());
			return workspaceFolder;
		} else {
			return null;
		}
	}

}
