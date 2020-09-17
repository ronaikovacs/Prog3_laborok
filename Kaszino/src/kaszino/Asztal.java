package kaszino;
import java.util.Random;

public class Asztal 
{
	private Jatekos[] jatekosok;
	private double tet;
	private int kor;
	private double goal;
	
	private int j_db;
	
	public Asztal()
	{
		jatekosok = new Jatekos[10];
		ujJatek();
	}
	
	public void ujJatek() 
	{
		tet = kor = 0;
		Random r = new Random();
		goal = r.nextDouble() * 100.0;
	}
	
	public void addJatekos(Jatekos j) 
	{
		if (j_db >= 10)
			System.out.println("Nem fér ide több játékos");
		else 
		{
			j.setAsztal(this);
			jatekosok[j_db++] = j;
		}
	}
	
	public int getKor() { return kor; }
	
	public void emel(double d) { tet += d; }
	
	public void kor() throws NincsJatekos {
		
		if (j_db == 0) 
			throw new NincsJatekos("Nem ülnek az asztalnál.");
		else
		{
			if (tet > goal) { 
				System.out.println("Vége a játéknak.");
			}
			else 
			{
				kor++;
				for (int i = 0; i < j_db; i++) 
				{
					jatekosok[i].lep();
					if (tet > goal) 
					{
						if (tet < 1.1*goal) 
						{
							System.out.println("A nyertes: " + i);
							break;
						}
					}
				}
				System.out.println("Aktuális tét: " + tet);
			}
		}
		System.out.println();
		System.out.println();
	}
	
	public double getTet() 
	{
		return tet; 
	}
}
