package snake;

/**
 * @name SnakeGame
 * @author Hasan Yosin
 * @version 1.0
 * @date 24.11.2014
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class SnakeGame extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	public static final int scale = 25;
	public static final int width = 40;
	public static final int height = 20;
	public static final int speed = 7;
	private static int cond = 0;
	public static  int wid = 0;
	public static  int hei = 0;
	
	Snake s = new Snake(10,10,9,10);
	Timer t = new Timer(1000/speed,this);
	public SnakeGame (){
		t.start();
		addKeyListener(new Keyboard());
		setFocusable(true);
	}
	public void paint(Graphics g){
		g.setColor(color(0,0,0));
		g.fillRect(0, 0, width*scale, height*scale);
		g.setColor(color(20,20,20));
		for(int x=0;x < width*scale;x+=scale){
			g.drawLine(x, 0, x, height*scale);
		}
		for(int y=0; y < height*scale;y+=scale){
			g.drawLine(0, y, width*scale, y);
		}
		
		for(int d = 0; d < s.length;d++){
			g.setColor(color(255,200,0));
			g.fillRect(s.snakeX[d]*scale+1, s.snakeY[d]*scale+1, scale-1,scale-1);
		}
		
		if(cond==0){
			wid =(s.length*75)% 40;
			hei = wid % height;
			g.setColor(color(0,100,0));
			g.fillRect(wid*scale,hei*scale, scale,scale);
		}
		if((s.snakeX[0]==wid) & (s.snakeY[0]==hei)) {
			s.length+=1;
			g.setColor(color(0,0,0));
			g.fillRect(wid*scale,hei*scale, scale,scale);
			cond = 0;
		} 
		if(s.snakeX[0]==width){
			s.snakeX[0]=0;
		}
		if(s.snakeY[0]==height){
			s.snakeY[0]=0;
		}
		if(s.snakeX[0]<0){
			s.snakeX[0]=width-1;
		}
		if(s.snakeY[0]<0){
			s.snakeY[0]=height-1;
		}
		
	}
	public Color color(int red,int green,int blue){
		return new Color(red,green,blue);
		
	}
	
	public static void main(String[] args){
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		f.setSize(width*scale+7,height*scale+27);
		f.setLocationRelativeTo(null);
		f.add(new SnakeGame());
		f.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		s.move();
		repaint();
	}
	private class Keyboard extends KeyAdapter {
		
		public void keyPressed(KeyEvent kEvt){
			if((kEvt.getKeyCode() == KeyEvent.VK_RIGHT) & s.direction !=2) s.direction = 0;
			if((kEvt.getKeyCode() == KeyEvent.VK_DOWN) & s.direction !=3)  s.direction = 1;
			if((kEvt.getKeyCode() == KeyEvent.VK_LEFT) & s.direction !=0)  s.direction = 2;
			if((kEvt.getKeyCode() == KeyEvent.VK_UP)  & s.direction !=3)   s.direction = 3;
			repaint();
		}
	}
}
