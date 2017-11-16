import java.lang.reflect.*;

public class AufzugSteuerung_Test {
  public static void main(String[] args) {
    Aufzug_Test t = new Aufzug_Test();
    try {
      System.out.println("Konstruktor-Test:");
      Aufzug a = new Aufzug(10, -5, 3);
      AufzugSteuerung s = new AufzugSteuerung(a);
      Field f = s.getClass().getDeclaredField("stockwerkAngefragt");
      f.setAccessible(true);
      t.assertEqual(((boolean[]) f.get(s)).length, 16,
          "stockwerkAngefragt initialisierung");
      t.assertTrue(a.isTuerAuf(), "Tuer auf");
      Field f2 = s.getClass().getDeclaredField("aufzug");
      f2.setAccessible(true);
      t.assertTrue(a == (Aufzug)f2.get(s), "Aufzug setzen");

      System.out.println("-- Fertig --\n");
      System.out.println("Fahren:");
      
      s.rufen(5);
      s.aufzugStarten();
      t.assertTrue(a.isTuerAuf(), "Tuer auf");
      t.assertEqual(a.getAktuellesStockwerk(), 5, "Fahren");
      s.aufzugStarten();
      t.assertEqual(a.getAktuellesStockwerk(), 5, "Warten");
      s.rufen(7);
      s.rufen(3);
      s.rufen(-3);
      s.aufzugStarten();
      t.assertEqual(a.getAktuellesStockwerk(), 7, "Fahren aus Warten");
      s.aufzugStarten();
      t.assertEqual(a.getAktuellesStockwerk(), 3, "Runter fahren");
      s.rufen(9);
      s.rufen(10);
      s.aufzugStarten();
      t.assertEqual(a.getAktuellesStockwerk(), -3, "Weiter runter fahren");
      s.aufzugStarten();
      t.assertEqual(a.getAktuellesStockwerk(), 9, "Wechsel nach hoch fahren");
      s.aufzugStarten();
      t.assertEqual(a.getAktuellesStockwerk(), 10, "Weiter hoch fahren");
      s.aufzugStarten();
      t.assertEqual(a.getAktuellesStockwerk(), 10, "Warten");
      s.rufen(4);
      s.aufzugStarten();
      t.assertEqual(a.getAktuellesStockwerk(), 4, "Fahren aus Warten");
      s.rufen(0);
      s.rufen(10);
      s.aufzugStarten();
      t.assertEqual(a.getAktuellesStockwerk(), 0, "Weiterfahren");
      ((boolean[]) f.get(s))[15] = false;
      s.aufzugStarten();
      t.assertEqual(a.getAktuellesStockwerk(), 0, "Warten");
      s.rufen(-2);
      s.rufen(5);
      s.aufzugStarten();
      t.assertEqual(a.getAktuellesStockwerk(), -2, "Fahren aus warten");

      System.out.println("-- Fertig --\n");
      
    } catch (Exception e) {
      System.out.println("Es ist ein Fehler aufgetreten!");
      e.printStackTrace();
      System.out.println("Tests wurden abgebrochen, weil ein Fehler "
         + "aufgetreten ist!");
    }
    System.out.println(t);
  }
}
