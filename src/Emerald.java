import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;

public class Emerald extends GameObject implements Drawable, Temporal {
	public boolean picked = false;

	public Emerald(int x, int y, DigGame game) {
		this.x = x;
		this.y = y;
		this.color = Color.blue;
		this.game = game;
		this.icon = new ImageIcon(".//Graphics//diamond.png");

	}

	@Override
	public void update() {
		this.getPicked();
	}

	@Override
	public void draw(Graphics2D g) {
		if (!this.picked) {
			g.drawImage(this.icon.getImage(), this.x, this.y, this.height,
					this.width, null);
		}
	}

	public void getPicked() {
		if (!this.picked) {
			if ((this.x == this.game.hero.x && Math.abs(this.y
					- this.game.hero.y) < this.height / 2)
					|| (this.y == this.game.hero.y && Math.abs(this.x
							- this.game.hero.x) < this.width / 2)) {	//if touch
				this.picked = true;
				this.game.status.changePoint(100);
				this.game.orgEmerald--;
				
				}
			}
		}

}