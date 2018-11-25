package controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.MemoryManager;
import view.MemoryManagerPane;

/**
 * @author
 *
 */
public class ViewController extends Application {
	MemoryManagerPane mmp;
	MemoryManager mm;

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader f = new FXMLLoader(getClass().getResource("/Memory Manager/src/view/Manager.fxml"));
		MemoryManagerPane mmp = f.getController();
		mmp.setOnActions(new HandleRun(), new HandleCompact(), new HandleRemove());
		Scene scene = new Scene(mmp);
		primaryStage.setScene(scene);
		primaryStage.show();
		mm = new MemoryManager(2000000);
		mmp.refreshMemoryDisplay(mm.exportData());
	}

	/**
	 * @author
	 *
	 */
	public class HandleRun implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			// TODO Update view and memory
		}

	}

	/**
	 * @author
	 *
	 */
	public class HandleCompact implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			// TODO Call compaction and update view

		}

	}

	/**
	 * @author
	 *
	 */
	public class HandleRemove implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			// TODO Call removal from memory and update view

		}

	}

}