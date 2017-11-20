
/**
 * Objekte dieser Klasse repraesentieren einen Aufzug.
 *
 * @author Angelika Hez 381847, Karine Louise 377854
 */
public class Aufzug {
	/**
	 * Nummer, des aktuellen Stockwerks
	 */
	private int aktuellesStockwerk;
	/**
	 * Anzahl der Personen, die sich aktuell im Aufzug befinden
	 */
	private int anzahlPersonen;
	/**
	 * gibt an, ob die Tuer offen (true) oder geschlossen (false) ist
	 */
	private boolean tuer;

	/**
	 * Nummer, des am hoechsten anfahrbaren Stockwerks
	 */
	private final int maxStockwerk;
	/**
	 * Nummer, des am niedrigsten anfahrbaren Stockwerks
	 */
	private final int minStockwerk;
	/**
	 * Anzahl an Personen, die sich maximal im Aufzug befinden können
	 */
	private final int maxPersonen;

	/**
	 * Array, der die Anzahl an Stockwerken repraesentiert
	 */
	int[] stockwerke;
	/**
	 * Identifikationsnummer eines Aufzugs
	 */
	private final int ID;
	/**
	 * wird bei jedem Aufruf des Konstruktors hochaddiert, um die einzelnen Aufzug-Objekte durchzunummerieren
	 */
	private static int nextID = 0;

	/**
	 * Erstelle einen neuen Aufzug mit einem vorgegebenen hoechsten und niedrigsten Stockwerk und einer maximal Anzahl an Personen im Aufzug
	 *
	 * @param maxStockwerk das hoechste Stockwerk, welches befahren werden kann
	 * @param minStockwerk das niedrigste Stockwerk, welches befahren werden kann
	 * @param maxPersonen  die maximale Anzahl an Personen, die mitfahren kann
	 */
	public Aufzug(int maxStockwerk, int minStockwerk, int maxPersonen) {
		this.maxStockwerk = maxStockwerk;
		this.maxPersonen = maxPersonen;
		this.minStockwerk = minStockwerk;
		tuer = false;
		anzahlPersonen = 0;
		aktuellesStockwerk = minStockwerk;
		stockwerke = new int[Math.abs(maxStockwerk - minStockwerk) + 1];
		ID = nextID;
		nextID++;
	}

	/**
	 * Erstelle einen neuen Aufzug mit einem vorgegebenen hoechsten befahrbaren Stockwerk und einer maximalen Anzahl an Personen
	 *
	 * @param maxStockwerk das hoechste Stockwerk, welches befahren werden kann
	 * @param maxPersonen  die maximale Anzahl an Personen, die mitfahren kann
	 */
	public Aufzug(int maxStockwerk, int maxPersonen) {
		this.maxStockwerk = maxStockwerk;
		this.maxPersonen = maxPersonen;
		minStockwerk = 0;
		tuer = false;
		anzahlPersonen = 0;
		aktuellesStockwerk = minStockwerk;
		stockwerke = new int[Math.abs(maxStockwerk - minStockwerk) + 1];
		ID = nextID;
		nextID++;
	}

	/**
	 * @return die Nummer des aktuellen Stockwerks wirds zurueckgegeben
	 */
	public int getAktuellesStockwerk() {
		return aktuellesStockwerk;
	}

	/**
	 * @return die Anahl der Personen, die sich aktuell im Aufzug befinden, wird zurueckgegeben
	 */
	public int getAnzahlPersonen() {
		return anzahlPersonen;
	}

	/**
	 * @return es wird zurückgegeben, ob die Tuer offen (true) oder zu (false) ist
	 */
	public boolean isTuerAuf() {
		return tuer;
	}

	/**
	 * @return das hoechste Stockwerk, welches angefahren werden kann, wird zurueckgegeben
	 */
	public int getMaxStockwerk() {
		return maxStockwerk;
	}

	/**
	 * @return das niedrigste Stockwerk, welches angefahren werden kann, wird zurueckgegeben
	 */
	public int getMinStockwerk() {
		return minStockwerk;
	}

	/**
	 * @return die Anzahl an Personen, die maximal in einen Aufzug rein koennen, wird zurueckgegeben
	 */
	public int getMaxPersonen() {
		return maxPersonen;
	}

	/**
	 * die Tuer wird auf offen (true) gestellt
	 */
	public void tuerOeffnen() {
		tuer = true;
	}

	/**
	 * die Tuer wird auf zu (false) gestellt
	 */
	public void tuerSchliessen() {
		tuer = false;
	}

	/**
	 * die Methode berechnet, ob die einsteigenden Personen in den Aufzug passen und wie viele ggf. noch warten muessen
	 *
	 * @param x Es wird eine Zahl übergeben, die die Anzahl der Personen, die einsteigen wollen, repraesentiert
	 * @return es wird zurueckgegeben, wie viele Leute nicht einsteigen konnten
	 */
	public int einsteigen(int x) {
		if (x < 0) {
			return x;
		}
		if (isTuerAuf()) {
			int frei = maxPersonen - anzahlPersonen;
			if (x <= frei && x >= 0) {
				anzahlPersonen += x;
				return 0;
			} else {
				anzahlPersonen = maxPersonen;
				return x - frei;
			}
		}
		return x;
	}

	/**
	 * die Methode berechnet, wie viele Personen nach dem Aussteigen, noch im Aufzug sind
	 *
	 * @param x Es wird eine Zahl uebergeben, die die Anzahl der Personen, die aussteigen wollen, repraesentieren
	 */
	public void aussteigen(int x) {
		if (isTuerAuf()) {
			if (x < 0) {
				return;
			}
			anzahlPersonen -= x;
			if (anzahlPersonen < 0) {
				anzahlPersonen = 0;
			}
		}
	}

	/**
	 * die Methode aendert das aktuelle Stockwerk auf das übergebene
	 *
	 * @param x Es wird eine Zahl uebergeben, die fuer das Stockwerk steht, in das der Aufzug fahren soll
	 * @return gibt zurueck, ob der Aufzug gefahren (true) ist oder nicht (false)
	 */
	public boolean fahren(int x) {
		if (isTuerAuf())
			return false;
		if (x > maxStockwerk) {
			aktuellesStockwerk = maxStockwerk;
		} else {
			if (x < minStockwerk) {
				aktuellesStockwerk = minStockwerk;
			} else {
				aktuellesStockwerk = x;
			}
		}
		return true;
	}

	/**
	 * @return gibt die ID des Aufzugs zurueck
	 */
	public int getID() {
		return ID;
	}
}

