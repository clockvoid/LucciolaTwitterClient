package Gui;

import java.io.IOException;

import Event.LucciolaEvent;
import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.layout.*;

public class GuiCore extends Application {
	private static LucciolaEvent lucciolaEvent;
	
	public void setLucciolaEvent(LucciolaEvent arg0) {
		GuiCore.lucciolaEvent = arg0;
	}
	
	@Override
	public void start(Stage arg0) {
		// TODO Auto-generated method stub
		try {
			arg0.setTitle("Lucciola -Twitter Client Made by Java-");
			FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemResource("Gui/MainScene.fxml"));
			fxmlLoader.load();
			BorderPane root = fxmlLoader.getRoot();
			((MainSceneController) fxmlLoader.getController()).setLucciolaEvent(lucciolaEvent);
			Scene mainScene = new Scene(root, 200, 100);
			arg0.setScene(mainScene);
			arg0.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("catch");
			e.printStackTrace();
		}
	}

}
