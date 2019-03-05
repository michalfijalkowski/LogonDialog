package proz;

/*****************************
 * Klasa bazy danych użytkowników i haseł
 * 
 * @author Michał Fijałkowski
 * @version 1.2
 *****************************/
public class Data {

	// baza uzytkownikow i ich hasel
	String[] nameProd = { "Adam Małysz", "Kamil Stoch", "Robert Mateja", "Stefan Hula", "Wojciech Fortuna" };
	String[] passwordProd = { "adamm", "kamils", "robertm", "stefanh", "wojciechf" };
	String[] nameTest = { "Iwona Nowak", "Katarzyna Kowal", "Jan Marzec", "Andrzej Kapciak", "Jakub Gród" };
	String[] passwordTest = { "abc123", "bca123", "qwerty", "qwert123", "345" };
	String[] nameDev = { "Łukasz Głaz", "Karina Krawiec", "Robert Gumny", "Adrian Grudzieñ", "Kacper Malik" };
	String[] passwordDev = { "qwe123", "karkra", "rob123", "grudzien12", "malik123" };
	
	
	/**
	 * Metoda sprawdzająca poprawność wpisanego lub wybranego loginu w połączeniu z
	 * hasłem
	 * 
	 * @param srodowisko
	 *            wartośc pola Środowisko wybierana spośrod 3 dostępnych
	 *            {Produkcyjne, Testowe, Deweloperskie}
	 * @param uzytkownik
	 *            wartość pola Użytkownik
	 * @param haslo
	 *            wartość pola Hasło
	 * @return true/false dla poprawnych danych zwróci true, dla błednych false
	 */
	public boolean passwordVerfication(String srodowisko, String uzytkownik, String haslo) {

		if (srodowisko == "Produkcyjne") {

			for (int i = 0; i < nameProd.length; ++i) {
				if (nameProd[i].equals(uzytkownik) && passwordProd[i].equals(haslo))
					return true;
			}
		} else if (srodowisko == "Testowe") {

			for (int i = 0; i < nameTest.length; ++i) {
				if (nameTest[i].equals(uzytkownik) && passwordTest[i].equals(haslo)) {
					return true;
				}
			}
		} else if (srodowisko == "Deweloperskie") {

			for (int i = 0; i < nameDev.length; ++i) {
				if (nameDev[i].equals(uzytkownik) && passwordDev[i].equals(haslo))
					return true;
			}
		}
		return false;
	}

}
