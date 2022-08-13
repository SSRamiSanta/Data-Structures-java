/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cromatico;

public class Vertices {
	private int llave;
	private int posX;
	private int posY;

	public Vertices(int llave, int x, int y) {
		this.llave = llave;
		posX = x;
		posY = y;
	}

	public int getLlave() {
		return llave;
	}

	public void setLlave(int llave) {
		this.llave = llave;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

}
