package ostatnieStarcie;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;

public class Kwadrat {
	private int x;
	private int y;
	private int szer;
	private int wys;
	private Color kolor;
	private Random rnd;
	private int limit;
	private int df = 1;
	private int wyspocz;
	
	public Kwadrat(Dimension d){
		rnd = new Random();
		x = rnd.nextInt(d.width - 100);
		y = rnd.nextInt(d.height - 100);
		szer = rnd.nextInt(70);
		wys = szer;
		limit = 2*wys;
		kolor = Color.white;
		wyspocz = wys;
	}
	
	public void rysuj(Graphics g){
		g.setColor(kolor);
		g.fillRect(x, y, szer, wys);

	}

	public void ruch() {
		wys = wys + 2*df;
		
		y = y - df;
		
		if (wys < wyspocz) df *= -1;
		if (wys >= limit) df *= -1;
	}

	public boolean zawiera(int x2, int y2) {
		int dist = (x2-x)*(x2-x) - (y2-y)*(y2-y);
		return dist < szer*szer;
	}
	
	public int odleglosc(int x2, int y2) {
		int dist = (x2-x)*(x2-x) - (y2-y)*(y2-y);
		return dist;
	}

	public void rosnie() {
		wys *= 1.5;
		szer *= 1.5;
	}
	
	public void maleje() {
		wys *= 0.5;
		szer *= 0.5;
	}

	public void gora() {
		y = y - 1;		
	}
	
	public void dol() {
		y = y + 1;		
	}

	public void czarny(Graphics graphics) {
		kolor = Color.black;		
	}
	
	public void bialy() {
		kolor = Color.white;		
	}
}
