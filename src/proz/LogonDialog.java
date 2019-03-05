package proz;

import java.util.Optional;
import javafx.geometry.Insets;
import javafx.scene.control.Dialog;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.File;

/*****************************
 * Klasa okna logowania
 * 
 * @author Michał Fijałkowski
 * @version 1.2
 *****************************/
public class LogonDialog {

	// tworzenie dialogu, przycisków, grida, passwordfielda, choiceboxa i comboboxa
	private Dialog<ButtonType> dialog = new Dialog<>();
	private ButtonType logonButton = new ButtonType("Logon");
	private ButtonType cancleButton = new ButtonType("Anuluj");
	private GridPane grid = new GridPane();
	private PasswordField password = new PasswordField();
	private ChoiceBox<String> choiceEnvironment = new ChoiceBox<>();
	private ComboBox<String> comboUser = new ComboBox<>();

	private Data data = new Data();

	/**
	 * Metoda odpowiedzialna za wygląd grida	 
	 */
	private void gridLook() {

		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 30, 10, 20));
		grid.add(new Label("Środowisko:"), 0, 0);
		grid.add(choiceEnvironment, 1, 0);
		grid.add(new Label("Użytkownik:"), 0, 1);
		grid.add(comboUser, 1, 1);
		grid.add(new Label("Hasło:"), 0, 2);
		grid.add(password, 1, 2);
	}

	/**
	 * Metoda aktywująca lub blokująca przycisk Logon
	 */
	private void lockButton() {
		if (choiceEnvironment.getValue() == null || comboUser.getEditor().getText().trim().isEmpty()
				|| password.getText().isEmpty())
			dialog.getDialogPane().lookupButton(logonButton).setDisable(true);
		else
			dialog.getDialogPane().lookupButton(logonButton).setDisable(false);
	}


	/**
	 * Metoda przyporzadkowująca listą użytkowników na podstawie wybranego środowiska
	 */

	public void makeCombo() {

		for (int i = 0; i < data.nameProd.length; ++i) {
			comboUser.getItems().addAll(data.nameProd[i]);
		}

		choiceEnvironment.getSelectionModel().selectedItemProperty().addListener((observable, oldVal, newVal) -> {
			if (choiceEnvironment.getValue() == "Produkcyjne") {
				comboUser.getItems().clear();
				for (int i = 0; i < data.nameProd.length; ++i) {
					comboUser.getItems().addAll(data.nameProd[i]);
				}
			} else if (choiceEnvironment.getValue() == "Testowe") {
				comboUser.getItems().clear();
				for (int i = 0; i < data.nameTest.length; ++i) {
					comboUser.getItems().addAll(data.nameTest[i]);
				}
			} else {
				comboUser.getItems().clear();
				for (int i = 0; i < data.nameDev.length; ++i) {
					comboUser.getItems().addAll(data.nameDev[i]);// comboUser.setItems(nameProd);
				}
			}
		});

	}

	/**
	 * Metoda
	 * 
	 * @return przekonwertowana wartość
	 */
	public Pair<String, String> showAndWait() {
		return makeResult(dialog.showAndWait(), logonButton);
	}

	/**
	 * Metoda zamieniająca buttonType na Pair
	 * 
	 * @param buttonType
	 *            zwracany przez okno po zakończeniu pracy programu
	 * @param logonButton
	 *            weryfikuje akcje wykonania logowania
	 * @return Pair<String,String> zwracana jest para <użytkownik, środowisko> przy
	 *         poprawnym logowaniu, w przeciwnym wypadku zwracany jest null
	 */
	private Pair<String, String> makeResult(Optional<ButtonType> buttonType, ButtonType logonButton) {
		if (buttonType.get() == logonButton) {
			if (data.passwordVerfication(choiceEnvironment.getValue(), comboUser.getEditor().getText(),
					password.getText()))
				return new Pair<>(choiceEnvironment.getValue(), comboUser.getEditor().getText());
		}
		return null;
	}

	/**
	 * Konstruktor organizujący wygląd i ustawienie komponentów okna logowania
	 */
	public LogonDialog() {

		// ustawianie elementow dialog
		dialog.setTitle("Logowanie");
		dialog.setHeaderText("Logowanie do systemu STYLEman");
		dialog.getDialogPane().getButtonTypes().addAll(logonButton, cancleButton);
		dialog.getDialogPane().setContent(grid);
		Node loginButton = dialog.getDialogPane().lookupButton(logonButton);
		loginButton.setDisable(true);

		// ustawienie obrazka w oknie logowania

		File file = new File("login.png");
		Image image = new Image(file.toURI().toString());
		ImageView iv = new ImageView();
		iv.setImage(image);
		dialog.setGraphic(iv);

		// ustawianie ukladu grida
		gridLook();
		choiceEnvironment.setPrefSize(105, 30);
		comboUser.setPrefSize(150, 30);
		password.setPrefSize(150, 30);

		// ustawienie wyboru srodowiska
		choiceEnvironment.getItems().addAll("Produkcyjne", "Testowe", "Deweloperskie");
		choiceEnvironment.setValue("Produkcyjne");

		// sprawdzenie mozliwosci odblokowania przycisku logowania
		choiceEnvironment.valueProperty().addListener((observable, oldVal, newVal) -> lockButton());
		comboUser.setEditable(true);
		comboUser.getEditor().textProperty().addListener((observable, oldVal, newVal) -> lockButton());
		password.textProperty().addListener((observable, oldVal, newVal) -> lockButton());

		// przypisanie listy uzytkownikow
		makeCombo();

		// wyswietlenie rezultatu
	
	}

}