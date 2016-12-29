package ostatnieStarcie;

import java.applet.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TimerTask.*;


public class Aplecik extends Applet{
	private ArrayList<Kwadrat> square = new ArrayList();
   	Iterator<Kwadrat> it = square.iterator();
//	private int N;
	Random rnd = new Random();
	
	public void szukaj (MouseEvent e){
		int wlasciwy =  square.get(0).odleglosc(e.getX(), e.getY());
		
		for (int i=0; i<square.size(); i++)
			if (square.get(i).odleglosc(e.getX(), e.getY()) < wlasciwy)
				wlasciwy = square.get(i).odleglosc(e.getX(), e.getY());
		for (int i=0; i<square.size(); i++)
			if (wlasciwy == square.get(i).odleglosc(e.getX(), e.getY()))
				square.get(i).czarny(getGraphics());
	}
	
	@Override
	public void init() {
		System.out.println("start");
		String liczba = getParameter("ILE_KWADRATOW");
		int N = 1 + rnd.nextInt(6);
		if(liczba != null)
			try{
				N = Integer.parseInt(liczba);
			}catch(NumberFormatException e){
				System.err.println("Zla liczba:" + liczba);
			}
		setSize(500,500);
		setBackground(Color.blue);
		Dimension d = getSize();
		for (int i=0; i<N; i++)
			square.add(new Kwadrat(d));
		
		addMouseListener(new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent e) {
//				if(e.getButton() == MouseEvent.BUTTON1){
//					for (Kwadrat kwadrat : square){
//						if(kwadrat.zawiera(e.getX(), e.getY()))
//							kwadrat.rosnie();
//					repaint();	
//					}
//				}
				if(e.getButton() == MouseEvent.BUTTON1){
					szukaj(e);
					repaint();
					}
				if(e.getButton() == MouseEvent.BUTTON2){
					for (Kwadrat kwadrat : square)
						kwadrat.bialy();
					repaint();
					}
				if(e.getButton() == MouseEvent.BUTTON3){
					for (Kwadrat kwadrat : square){
						if(kwadrat.zawiera(e.getX(), e.getY()))
							kwadrat.maleje();
					repaint();	
					}	
				}
			}
		});
		
		addKeyListener(new KeyAdapter(){

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_UP){
					for(Kwadrat kwadrat : square)
						kwadrat.gora();
				if(e.getKeyCode() == KeyEvent.VK_SPACE){
					for(Kwadrat kwadrat : square)
						kwadrat.dol();
					repaint();
				}
				}
			}
			
		});

		
		class Animation extends TimerTask{
			@Override
			public void run() {
				for (Kwadrat kwadrat : square)
					kwadrat.ruch();
				repaint();
			}
		}
		

		Timer go = new Timer();
		go.schedule(new Animation(), 2000, 100);
	}
	
	


	@Override
	public void paint(Graphics g) {
		for (Kwadrat kwadrat : square)
			kwadrat.rysuj(g);
	}
	

}
