public class Aufzug_Test {
  private int successes;
  private int failures;
  private int run;
  public static void main(String[] args) {
    Aufzug_Test t = new Aufzug_Test();
    try {
      System.out.println("Konstruktor und Getter-Test:");
      Aufzug a1 = new Aufzug(10, 3, 5);
      Aufzug a2 = new Aufzug(10, -5, 3);
      Aufzug a3 = new Aufzug(5, 4);

      t.assertEqual(a1.getMinStockwerk(), 3, "a1.getMinStockwerk");
      t.assertEqual(a1.getMaxStockwerk(), 10, "a1.getMaxStockwerk");
      t.assertEqual(a1.getMaxPersonen(), 5, "a1.getMaxPersonen");
      t.assertEqual(a1.getAktuellesStockwerk(), 3, "a1.getAktuellesStockwerk");
      t.assertEqual(a1.getAnzahlPersonen(), 0, "a1.getAnzahlPersonen");
      t.assertTrue(!a1.isTuerAuf(), "a1.getTuerAuf ist true");
      t.assertEqual(a1.getID(), 0, "a1.getID");
      
      t.assertEqual(a2.getMinStockwerk(), -5, "a2.getMinStockwerk");
      t.assertEqual(a2.getMaxStockwerk(), 10, "a2.getMaxStockwerk");
      t.assertEqual(a2.getMaxPersonen(), 3, "a2.getMaxPersonen");
      t.assertEqual(a2.getAktuellesStockwerk(), -5, "a2.getAktuellesStockwerk");
      t.assertEqual(a2.getAnzahlPersonen(), 0, "a2.getAnzahlPersonen");
      t.assertTrue(!a2.isTuerAuf(), "a2.getTuerAuf ist true");
      t.assertEqual(a2.getID(), 1, "a2.getID");

      t.assertEqual(a3.getMinStockwerk(), 0, "a3.getMinStockwerk");
      t.assertEqual(a3.getMaxStockwerk(), 5, "a3.getMaxStockwerk");
      t.assertEqual(a3.getMaxPersonen(), 4, "a3.getMaxPersonen");
      t.assertEqual(a3.getAktuellesStockwerk(), 0, "a3.getAktuellesStockwerk");
      t.assertEqual(a3.getAnzahlPersonen(), 0, "a3.getAnzahlPersonen");
      t.assertTrue(!a3.isTuerAuf(), "a3.getTuerAuf ist true");
      t.assertEqual(a3.getID(), 2, "a3.getID");

      System.out.println("-- Fertig --\n");
      System.out.println("Ein/Aussteigen:");

      a1.tuerOeffnen();
      t.assertTrue(a1.isTuerAuf(), "a1.tuerOeffnen");
      a1.tuerSchliessen();
      t.assertTrue(!a1.isTuerAuf(), "a1.tuerSchliessen");
      t.assertEqual(a1.einsteigen(3), 3, "Einsteigen mit Tuer zu");
      t.assertEqual(a1.getAnzahlPersonen(), 0, "Einsteigen mit Tuer zu");
      a1.tuerOeffnen();
      t.assertEqual(a1.einsteigen(3), 0, "Einsteigen");
      t.assertEqual(a1.getAnzahlPersonen(), 3, "Einsteigen");
      t.assertEqual(a1.einsteigen(3), 1, "Ueberfuellung");
      t.assertEqual(a1.getAnzahlPersonen(), 5, "Ueberfuellung");
      a1.tuerSchliessen();
      a1.aussteigen(3);
      t.assertEqual(a1.getAnzahlPersonen(), 5, "Aussteigen mit Tuer zu");
      a1.tuerOeffnen();
      a1.aussteigen(3);
      t.assertEqual(a1.getAnzahlPersonen(), 2, "Aussteigen");
      a1.aussteigen(3);
      t.assertEqual(a1.getAnzahlPersonen(), 0, "Negative Personen");

      System.out.println("-- Fertig --\n");
      System.out.println("Fahren:");
      
      t.assertTrue(a2.fahren(4), "Nicht gefahren");
      t.assertEqual(a2.getAktuellesStockwerk(), 4, "Fahren");
      t.assertTrue(a2.fahren(-3), "Nicht gefahren");
      t.assertEqual(a2.getAktuellesStockwerk(), -3, "Fahren");
      t.assertTrue(a2.fahren(-7), "Nicht gefahren");
      t.assertEqual(a2.getAktuellesStockwerk(), -5, "Fahren");
      t.assertTrue(a2.fahren(20), "Nicht gefahren");
      t.assertEqual(a2.getAktuellesStockwerk(), 10, "Fahren");
      t.assertTrue(!a1.fahren(9), "Fahren mit Tuer auf");
      t.assertEqual(a1.getAktuellesStockwerk(), 3, "Fahren mit Tuer auf");

      System.out.println("-- Fertig --\n");
      
    } catch (Exception e) {
      System.out.println("Es ist ein Fehler aufgetreten!");
      e.printStackTrace();
      System.out.println("Tests wurden abgebrochen, weil ein Fehler "
         + "aufgetreten ist!");
    }
    System.out.println(t);
  }

  public String toString() {
    return run + " Tests abgeschlossen, davon " + successes + " OK und "
      + failures + " Fehler.";
  }

  public void assertTrue(boolean val, String msg) {
    if(!val) {
      System.out.println("Fehler: " + msg);
      this.failures += 1;
    } else {
      System.out.println("OK");
      this.successes += 1;
    }
    this.run += 1;
  }

  public void assertEqual(int v1, int v2, String msg) {
    assertTrue(v1 == v2, v1 + " != " + v2 + " (" + msg + ")");
  }
}
