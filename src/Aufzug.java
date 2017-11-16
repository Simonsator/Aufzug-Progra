
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

    private final int maxStockwerk;
    private final int minStockwerk;
    private final int maxPersonen;

    private int[] stockwerke;

    private final int ID;
    private static int nextID=0;

    public Aufzug(int maxStockwerk, int minStockwerk, int maxPersonen)
    {
        this.maxStockwerk=maxStockwerk;
        this.maxPersonen=maxPersonen;
        this.minStockwerk=minStockwerk;
        tuer=false;
        anzahlPersonen=0;
        aktuellesStockwerk=minStockwerk;
        stockwerke=new int[Math.abs(maxStockwerk-minStockwerk)+1];
        ID=nextID;
        nextID++;
    }

    public Aufzug(int maxStockwerk,int maxPersonen)
    {
        this.maxStockwerk=maxStockwerk;
        this.maxPersonen=maxPersonen;
        minStockwerk=0;
        tuer=false;
        anzahlPersonen=0;
        aktuellesStockwerk=minStockwerk;
        stockwerke=new int[Math.abs(maxStockwerk-minStockwerk)+1];
        ID=nextID;
        nextID++;
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
        tuerOeffnen();
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
        tuerOeffnen();
        if(x>=0 && x<=anzahlPersonen)
        {
            anzahlPersonen-=x;
        }
    }

    public boolean fahren (int x)
    {
        tuerSchliessen();
        if(x>stockwerke.length)
        {
            aktuellesStockwerk=stockwerke.length;
        }
        else 
        {
            if(x<0)
            {
                aktuellesStockwerk=0;
            }
            else 
            {
                for(int i=0; i<stockwerke.length; i++)
                {
                    if(x==i)
                    {
                        aktuellesStockwerk=x;
                    }

                }
            }
        }
        return true;
    } 

    public int getID()
    {
        return ID;
    }
}

