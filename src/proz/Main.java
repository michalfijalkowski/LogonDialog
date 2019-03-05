package proz;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Pair;

/**************************
 * Klasa Main, PROZ
 * 
 * @author Michał Fijałkowski
 * @version 1.2
 **************************/
public class Main extends Application {

	/**
	 * Metoda uruchamiająca okienko
	 * 
	 * @param args
	 *            parametr przekazywany do
	 *            javafx.application.Application.launch(String[] args)
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Metoda wyświetlająca okno logowania i wypisująca rezultat logowania
	 * 
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {

		Pair<String, String> result = (new LogonDialog()).showAndWait();

		if (result == null)
			System.out.println("Niepoprawne dane");
		else
			System.out.println("Witaj " + result.getValue() + " w środowisku " + result.getKey());
	}

}