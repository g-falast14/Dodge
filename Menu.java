package com.tutorial.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.tutorial.main.Game.STATE;

public class Menu extends MouseAdapter{
	
	private Game game;
	private Handler handler;
	private Random r = new Random();
	private HUD hud;
	
	public Menu(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
	}
	
	public void render(Graphics g){
		if (game.gameState == STATE.Menu) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);

			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Dodge", 240, 70);
			
			g.setFont(fnt2);
			g.drawString("Play", 270, 190);
			
			g.setFont(fnt2);
			g.drawString("Help", 270, 290);

			g.setFont(fnt2);
			g.drawString("Quit", 270, 390);
			
			g.setColor(Color.white);
			g.drawRect(210, 150, 200, 64);
			
			g.setColor(Color.white);
			g.drawRect(210, 250, 200, 64);
			
			g.setColor(Color.white);
			g.drawRect(210, 350, 200, 64);
		}else if (game.gameState == STATE.Help) {
			Font fnt = new Font("arial", 1, 50);			
			Font fnt3 = new Font("arial", 1, 20);

			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Help", 240, 70);
			
			g.setFont(fnt3);
			g.drawString("Use WASD keys to move player and dodge enemies", 50, 200);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Back", 270, 390);
		}else if (game.gameState == STATE.End) {
			Font fnt = new Font("arial", 1, 50);			
			Font fnt3 = new Font("arial", 1, 20);

			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Game Over", 180, 70);
			
			g.setFont(fnt3);
			g.drawString("You lost with a score of " + hud.getScore(), 175, 200);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Try Again", 245, 390);
		}else if (game.gameState == STATE.Select) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);

			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Select Difficulty", 240, 70);
			
			g.setFont(fnt2);
			g.drawString("Normal", 270, 190);
			
			g.setFont(fnt2);
			g.drawString("Hard", 270, 290);

			g.setFont(fnt2);
			g.drawString("Back", 270, 390);
			
			g.setColor(Color.white);
			g.drawRect(210, 150, 200, 64);
			
			g.setColor(Color.white);
			g.drawRect(210, 250, 200, 64);
			
			g.setColor(Color.white);
			g.drawRect(210, 350, 200, 64);
		}
		
	}
	
	public void tick() {
		
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		AudioPlayer.getSound("menu_sound").play();
		if (game.gameState == STATE.Menu) {
			// play button
			if (mouseOver(mx, my, 210, 150, 200, 64)) {
				//game.gameState = STATE.Game;
				//handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				//handler.clearEnemies();
				//handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
				game.gameState = STATE.Select;
				return;
			}
			// help button
			if (mouseOver(mx, my, 210, 250, 200, 64)) {
				game.gameState = STATE.Help;
			}
			// quit button
			if (mouseOver(mx, my, 210, 350, 200, 64)) {
				System.exit(0);
			}
		}
		
		if (game.gameState == STATE.Select) {
			// normal button
			if (mouseOver(mx, my, 210, 150, 200, 64)) {
				game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				handler.clearEnemies();
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
				
				game.diff = 0;
			}
			// hard button
			if (mouseOver(mx, my, 210, 250, 200, 64)) {
				game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				handler.clearEnemies();
				handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
				
				game.diff = 1;
			}
			// back button
			if (mouseOver(mx, my, 210, 350, 200, 64)) {
				game.gameState = STATE.Menu;			}
		}
		
		// back button for help
		if (game.gameState == STATE.Help) {
			if (mouseOver(mx, my, 210, 350, 200, 64)) {
				game.gameState = STATE.Menu;
				return;
			}
		}
		// restart button after death
		if (game.gameState == STATE.End) {
			if (mouseOver(mx, my, 210, 350, 200, 64)) {
				game.gameState = STATE.Menu;
				hud.setLevel(1);
				hud.setScore(0);
				return;
			}
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if (mx > x && mx < x + width) {
			if (my > y && my < y + height) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
}
