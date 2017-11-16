
/**
 * Beschreiben Sie hier die Klasse Aufzug.
 * 
 * @author Angelika Hez 8..., Karine Louise ...
 * @version 
 */
public class Aufzug
{
    private int aktuellesStockwerk;
    private int anzahlPersonen;
    private boolean tuer;

    private static final int maxStockwerk;
    private static final int minStockwerk;
    private static final int maxPersonen;

    private int[] stockwerke; 

    public Aufzug()
    {

    }

    public int getAktuellesStockwerk()
    {
        return aktuellesStockwerk;
    }

    public int getAnzahlPersonen()
    {
        return anzahlPersonen; 
    }

    public boolean isTuerAuf()
    {
        if(tuer==true)
        {
            return true; 
        }
        else 
        {
            return false;
        }
    }

    public int getMaxStockwerk()
    {
        return maxStockwerk;
    }

    public int getMinStockwerk()
    {
        return minStockwerk;
    }

    public int getMaxPersonen()
    {
        return maxPersonen;
    }

    public void tuerOeffnen()
    {
        tuer=true; 
    }

    public void tuerSchliessen()
    {
        tuer=false;
    }

    public int einsteigen(int x)
    {
        int uebrig=0; 
        if(isTuerAuf()==true)
        {
            int frei=maxPersonen-anzahlPersonen;
            if(x<=frei && x>=0)
            {
                anzahlPersonen+=x;
            }
            else
            {
                anzahlPersonen=maxPersonen;
                uebrig=x-frei; 
            }
        }
        return uebrig;
    }

    public void aussteigen(int x)
    {
        if(isTuerAuf()==true)
        {
            if(x>=0 && x<=anzahlPersonen)
            {
                anzahlPersonen-=x;
            }
        }
    }

    public boolean fahren (int x)
    {
        tuerSchliessen();
        if(x>stockwerke.length)
        {
            aktuellesStockwerk=stockwerke.length;
        }
        if(x<0)
        {
            aktuellesStockwerk=0;
        }
        for(int i=0; i<stockwerke.length; i++)
        {
            if(x==i)
            {
                aktuellesStockwerk=x;
            }

        }
    }
}
    
