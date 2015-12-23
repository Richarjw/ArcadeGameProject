import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;

public class Gold extends GameObject implements Drawable, Temporal {
	private int counter;
	final static int max_Counter = 50;
	private boolean getPicked;
	private boolean canPush;

	public Gold(int x, int y, DigGame game) {
		this.x = x;
		this.y = y;
		this.color = Color.yellow;
		this.game = game;
		this.counter = 0;
		this.getPicked = false;
		this.icon = new ImageIcon(".//Graphics//gold.png");
		this.canPush = true;
	}

	@Override
	public void update() {
		if (!this.isDead) {
			this.move();
			this.kills();
			this.drop();
		}

	}

	@Override
	public void draw(Graphics2D g) {
		if (!this.isDead) {
			g.drawImage(this.icon.getImage(), this.x, this.y, this.height,
					this.width, null);
		}
	}

	public void move() {
		if (this.canPush == true) {
			int xDifference = this.x - this.game.hero.x;
			if (xDifference > 0 && Math.abs(xDifference) < this.width
					&& this.y == this.game.hero.y) {
				this.x += Math.abs(this.game.hero.xSpeed);
				if (this.x >= this.width * (this.game.row - 1)) {
					this.x = (this.game.row - 1) * this.width;
					this.game.hero.x = (this.game.row - 2) * this.width;
				}
			} else if (xDifference < 0 && Math.abs(xDifference) < this.width
					&& this.y == this.game.hero.y) {
				this.x -= Math.abs(this.game.hero.xSpeed);
				if (this.x <= 0) {
					this.x = 0;
					this.game.hero.x = this.width;
				}
			}
		}
	}

	public void kills() {

		if (Math.abs(this.x - this.game.hero.x)<this.width
				&& (this.game.hero.y - this.y) < this.width
				&& (this.game.hero.y - this.y) > 0) {
			this.y = this.y + this.height;
			this.game.status.lossLife();
			this.game.hero.reset();
			this.game.hero.life = this.game.hero.life - 1;
			if (this.y == (this.game.column - 1) * this.height) {
				this.y = (this.game.column - 1) * this.height;
			}

		}

	}

	public void drop() {
		if (this.game.map[this.y / this.height + 1][this.x / this.width] == 0
				&& this.x % this.width == 0) {
			this.canPush = false;
			if (this.counter > 20) {
				this.y = this.y + 5;
			}
			this.counter++;
		} else {
			if (this.counter >= this.max_Counter) {
				this.color = Color.pink;
				Emerald a = new Emerald(this.x / this.width * this.width,
						this.y / this.width * this.width, this.game);
				a.icon = new ImageIcon(".//Graphics//coins.png");
				this.game.emeralds.add(a);
				this.isDead = true;
			} else {
				this.canPush = true;
				this.counter = 0;
			}
		}
	}
}