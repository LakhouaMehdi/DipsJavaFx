// Lakhoua Mehdi <Lakhoua_Mehdi@yahoo.com> 
// 12/18/2017   

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class TopMenu extends MenuBar {

	//in file menu
	MenuItem loadMenuItem ;
    MenuItem saveMenuItem ;
    
    MenuItem undoAllActions;
    
	public TopMenu () {
		 super();
		 //File Menu ---
	     Menu fileMenu = new Menu("File");
	       
	     //File Menu Items
	     loadMenuItem = new MenuItem("Load ...");
	     saveMenuItem = new MenuItem("Save ...");
	          
	     //Add menu items to the menu        
	     fileMenu.getItems().add(loadMenuItem);
	     fileMenu.getItems().add(saveMenuItem);
	        
	     //add menu to the menu bar
	     this.getMenus().add(fileMenu);
	     
	     //Edit Menu ---
	     Menu editMenu = new Menu("Edit");
	   
	     //Edit Menu Items
	     undoAllActions = new MenuItem("Undo All");
	     
	     //Add menu items to edit menu
	     editMenu.getItems().add(undoAllActions);
	     
	     //Edit Menu Items
	     this.getMenus().add(editMenu);
	     
	}
	
	public void disableMenus()
	{
		undoAllActions.setDisable(true);
		
	}
	
	public void enableMenus() 
	{
		undoAllActions.setDisable(false);
		
	}
	
	
}

