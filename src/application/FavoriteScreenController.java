package application;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import daisty.Recipe;
import daisty.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class FavoriteScreenController {
    User user = new User();

    @FXML
    private Button getRecipesBtn;

    @FXML
    private Button favoritesBtn;

    @FXML
    private Button settingsBtn;

    @FXML
    private Button logOutBtn;

    @FXML
    private GridPane favGrid;
    
    @FXML
    private Button tester;

    private ArrayList<String> favList;
    @FXML
    void initialize(User user)
    {
    	this.user = user;
        favList = user.getFavNames();
    	fillFavGrid();
    }
    
    @FXML
    private void fillFavGrid()
    {
    	System.out.println("running test..");
    	System.out.println(user.getFirstName());
    	System.out.println("favList size is " + favList.size());
    	int imageCol = 1;
    	int imageRow = 1;
    	for (int i = 0; i < favList.size(); i++)
    	{
    		System.out.println("inside loop interation: " + i);
    		File file = new File("src/recipeInfo/recipeImage/" + favList.get(i) + ".jpeg");	// retrieve image source
    		Image favImage = new Image(file.toURI().toString());							// create javafx Image
    		System.out.println(favList.get(i));
    		// place image into ImageView
    		ImageView pic = new ImageView();
    		pic.setFitHeight(160);
    		pic.setFitWidth(160);
    		pic.setImage(favImage);
    		
    		favGrid.setPadding(new Insets(20));
    		favGrid.setHgap(25);
    		favGrid.setVgap(25);
    		
    		favGrid.add(pic, imageCol, imageRow);	// add image to grid
    		imageCol++;								// move one over in row
    		
    		// makes sure only 3 images per row
    		if (imageCol > 3)
    		{
    			imageCol = 1;
    			imageRow++;
    		}
    	}
    	
//    	for (int i = 0; i < 8; i++)
//    	{
//    		System.out.println("column: " + imageCol + " row: " + imageRow);
//    		File file = new File("src/recipeInfo/recipeImage/TFTdumpling.jpg");	// retrieve image source
//    		Image favImage = new Image(file.toURI().toString());				// create javafx Image
//    		// place image into ImageView
//    		ImageView pic = new ImageView();
//    		pic.setFitHeight(150);
//    		pic.setFitWidth(150);
//    		pic.setImage(favImage);
//    		
//    		favGrid.setPadding(new Insets(20));
//    		favGrid.setHgap(20);
//    		favGrid.setVgap(20);
//    		
//    		favGrid.add(pic, imageCol, imageRow);	// add image to grid
//    		imageCol++;								// move one over in row
//    		
//    		// makes sure only 3 images per row
//    		if (imageCol > 3)
//    		{
//    			imageCol = 1;
//    			imageRow++;
//    		}
//    	}
    	
    	System.out.println("test done.");
    }
    
	@FXML
    void toFavorites(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("FavoriteScreen.fxml"));
    	Parent mainSceneParent = loader.load();
		Scene mainScene = new Scene(mainSceneParent);
		Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		user = (User) appStage.getUserData();
		if (user.getFavorites() != null)
		{
			for (int i = 0; i < user.getFavorites().size(); i++)
			{
				System.out.println(user.getFavorites().get(i).getName());
			}
		}
		FavoriteScreenController controller = loader.getController(); 
		controller.initialize(user);
		appStage.setUserData(user);
		appStage.setScene(mainScene);
		appStage.show();
    }

    @FXML
    void toRecipes(ActionEvent event) throws IOException {
        Parent mainSceneParent = FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
        Scene mainScene = new Scene(mainSceneParent);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        user = (User) appStage.getUserData();
        appStage.setUserData(user);
        appStage.setScene(mainScene);
        appStage.show();
    }

    @FXML
    void toSettings(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SettingsScreen.fxml"));
        Parent mainSceneParent = loader.load();
        Scene mainScene = new Scene(mainSceneParent);

        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        user = (User) appStage.getUserData();
        SettingsScreenController controller = loader.getController();
        controller.initialize(user);
        //appStage.setUserData(user);
        appStage.setScene(mainScene);
        appStage.show();
    }


    @FXML
    void logOut(ActionEvent event) throws IOException {
        Parent mainSceneParent = FXMLLoader.load(getClass().getResource("SignInScreen.fxml"));
        Scene mainScene = new Scene(mainSceneParent);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(mainScene);
        appStage.show();
    }

}
