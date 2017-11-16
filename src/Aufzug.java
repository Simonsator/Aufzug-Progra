
/**
 * Beschreiben Sie hier die Klasse Aufzug.
 *
 * @author Angelika Hez 8..., Karine Louise ...
 */
public class Aufzug {
	private int aktuellesStockwerk;
	private int anzahlPersonen;
	private boolean tuer;

	private final int maxStockwerk;
	private final int minStockwerk;
	private final int maxPersonen;

	private int[] stockwerke;

	private final int ID;
	private static int nextID = 0;

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

	public int getAktuellesStockwerk() {
		return aktuellesStockwerk;
	}

	public int getAnzahlPersonen() {
		return anzahlPersonen;
	}

	public boolean isTuerAuf() {
		return tuer;
	}

	public int getMaxStockwerk() {
		return maxStockwerk;
	}

	public int getMinStockwerk() {
		return minStockwerk;
	}

	public int getMaxPersonen() {
		return maxPersonen;
	}

	public void tuerOeffnen() {
		tuer = true;
	}

	public void tuerSchliessen() {
		tuer = false;
	}

	public int einsteigen(int x) {
		if (x < 0)
			return x;
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

	public void aussteigen(int x) {
		if (isTuerAuf()) {
			if (x < 0)
				return;
			anzahlPersonen -= x;
			if (anzahlPersonen < 0) {
				anzahlPersonen = 0;
			}
		}
	}

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

	public int getID() {
		return ID;
	}
}

