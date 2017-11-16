public class AufzugSteuerung {
	private final boolean[] stockwerkAngefragt;

	private AufzugZustand zustand;

	private final Aufzug aufzug;

	/**
	 * Erzeugt eine Steuerung zu einem gegebenen Aufzug.
	 *
	 * @param aufzug der zu steuernde Aufzug
	 */
	public AufzugSteuerung(Aufzug aufzug) {
		this.aufzug = aufzug;
		aufzug.tuerOeffnen();
		stockwerkAngefragt = new boolean[Math.abs(aufzug.getMaxStockwerk() - aufzug.getMinStockwerk()) + 1];
		for (int i = 0; i < stockwerkAngefragt.length; i++) {
			stockwerkAngefragt[i] = false;
		}
		zustand = AufzugZustand.Warten;
	}

	/**
	 * Gibt das aktuelle Stockwerk, normalisiert als Array-Index, zurueck.
	 *
	 * @return Index des aktuellen Stockwerks
	 */
	private int aktuellesStockwerk() {
		return aufzug.getAktuellesStockwerk() - aufzug.getMinStockwerk();
	}

	/**
	 * Faehrt den Aufzug zu einem gegebenen Stockwerk.
	 *
	 * @param stockwerk Der Array-Index des Stockwerks
	 */
	private void fahren(int stockwerk) {
		aufzug.tuerSchliessen();
		aufzug.fahren(stockwerk + aufzug.getMinStockwerk());
		aufzug.tuerOeffnen();
	}

	/**
	 * Ruft den Aufzug zu einem gegebenen Stockwerk. Das Stockwerk
	 * wird intern in einen Array-Index umgerechnet. Das aktuelle
	 * Stockwerk kann nicht angefragt werden, der Aufzug ist ja schon da.
	 *
	 * @param stockwerk Die Nummer des Stockwerks
	 */
	public void rufen(int stockwerk) {
		if (aufzug.getAktuellesStockwerk() != stockwerk
				&& stockwerk <= aufzug.getMaxStockwerk()
				&& stockwerk >= aufzug.getMinStockwerk()) {
			this.stockwerkAngefragt[stockwerk - aufzug.getMinStockwerk()] = true;
		}
	}

	/**
	 * Arbeitet ein Stockwerk ab, sofern mindestens ein Stockwerk
	 * angefragt ist.
	 */
	public void aufzugStarten() {
		Integer nextFloor = 0;
		switch (zustand) {
			case Warten:
				nextFloor = nextFloor();
				if (nextFloor == null)
					return;
				if (nextFloor > aktuellesStockwerk()) {
					zustand = AufzugZustand.Hoch;
				} else {
					zustand = AufzugZustand.Runter;
				}
				break;
			case Hoch:
				nextFloor = nextHigherFloor(aktuellesStockwerk());
				if (nextFloor == null) {
					nextFloor = nextLowerFloor(aktuellesStockwerk());
					if (nextFloor == null) {
						zustand = AufzugZustand.Warten;
						return;
					} else {
						zustand = AufzugZustand.Runter;
					}
				} else {
					zustand = AufzugZustand.Hoch;
				}
				break;
			case Runter:
				nextFloor = nextLowerFloor(aktuellesStockwerk());
				if (nextFloor == null) {
					nextFloor = nextHigherFloor(aktuellesStockwerk());
					if (nextFloor == null) {
						zustand = AufzugZustand.Warten;
						return;
					} else {
						zustand = AufzugZustand.Hoch;
					}
				} else {
					zustand = AufzugZustand.Runter;
				}
				break;
		}
		aufzug.fahren(nextFloor);
	}

	private Integer nextLowerFloor(int pMomStockwerk) {
		for (int i = pMomStockwerk - 1; i > stockwerkAngefragt.length; i--)
			if (stockwerkAngefragt[i])
				return i;
		return null;
	}

	private Integer nextFloor() {
		int aktuell = aktuellesStockwerk();
		Integer nextHigherRequested = nextHigherFloor(aktuell);
		Integer nextLowerRequested = nextLowerFloor(aktuell);
		if (nextHigherRequested == null) {
			return nextLowerRequested;
		}
		if (nextLowerRequested == null)
			return nextHigherRequested;
		if (distance(aktuell, nextHigherRequested) > distance(aktuell, nextLowerRequested)) {
			return nextLowerRequested;
		}
		return nextHigherRequested;
	}

	private int distance(int a, int b) {
		return Math.abs(a - b);
	}

	private Integer nextHigherFloor(int pMomStockwerk) {
		for (int i = pMomStockwerk + 1; i < stockwerkAngefragt.length; i++)
			if (stockwerkAngefragt[i])
				return i;
		return null;
	}
}
