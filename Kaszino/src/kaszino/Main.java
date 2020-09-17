package kaszino;

public class Main {
	static public void main(String[] args) {
		Asztal asztal = new Asztal();
		Jatekos[] jatekosok = new Jatekos[3];
		jatekosok[0] = new Kezdo("Egyik");
		jatekosok[1] = new Kezdo("Masik");
		jatekosok[2] = new Robot();
		
		for (Jatekos j : jatekosok) 
		{
			asztal.addJatekos(j);
		}
		
		for (int i = 0; i < 3; i++) 
		{
			try 
			{
				asztal.kor();
			}
			catch (NincsJatekos nj) {}
		}

		System.out.println();
		System.out.println("------------------");
		System.out.println();
		
		
		Asztal uresAsztal = new Asztal();
		try 
		{
			uresAsztal.kor();
		}
		catch (NincsJatekos nj) {}

		System.out.println();
		System.out.println("------------------");
		System.out.println();
		
		
		Asztal mesternyuszi = new Asztal();
		mesternyuszi.addJatekos(new Mester(4));
		mesternyuszi.addJatekos(new Nyuszi("Kék"));
		for (int i = 0; i < 10; i++)
		{
			try 
			{
				mesternyuszi.kor();
			}
			catch (NincsJatekos nj) {}
		}

		System.out.println();
		System.out.println("------------------");
		System.out.println();
		
		
		Asztal embernyuszi = new Asztal();
		embernyuszi.addJatekos(new Ember());
		embernyuszi.addJatekos(new Nyuszi("Kék"));
		for (int i = 0; i < 10; i++)
		{
			try 
			{
				embernyuszi.kor();
			}
			catch (NincsJatekos nj) {}
		}
	}
}
