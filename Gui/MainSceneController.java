package Gui;

import Event.LucciolaEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MainSceneController {
	@FXML TextField tweetBox;
	LucciolaEvent lucciolaEvent;
	
	@FXML
	protected void tweet(ActionEvent ev) {
		lucciolaEvent.onEvent("tweet:" + tweetBox.getText());
		tweetBox.setText("");
	}
	
	public void setLucciolaEvent(LucciolaEvent arg0) {
		this.lucciolaEvent = arg0;
	}
}
