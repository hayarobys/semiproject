package object;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import db.CreditDTO;
import system.GameObject;

public class CreditsDataObj extends GameObject {
	private CreditDTO dto;
	private double fontDescriptionX, fontDescriptionY;
	private int fontWidth, fontHeight, fontAscent;
	public static final Font font = new Font("DIALOG_INPUT", Font.BOLD, 19);
	
	public int getFontAscent() {
		return fontAscent;
	}

	public void setFontAscent(int fontAscent) {
		this.fontAscent = fontAscent;
	}

	public CreditsDataObj(){
	}
	
	public Font getFont() {
		return font;
	}

	public int getFontWidth() {
		return fontWidth;
	}

	public void setFontWidth(int fontWidth) {
		this.fontWidth = fontWidth;
	}

	public int getFontHeight() {
		return fontHeight;
	}

	public void setFontHeight(int fontHeight) {
		this.fontHeight = fontHeight;
	}

	@Override
	public int getHeight(){
		return dto.getImg().getHeight(null) + fontHeight;
	}
	
	public double getFontDescriptionX() {
		return fontDescriptionX;
	}


	public void setFontDescriptionX(double fontDescriptionX) {
		this.fontDescriptionX = fontDescriptionX;
	}


	public double getFontDescriptionY() {
		return fontDescriptionY;
	}


	public void setFontDescriptionY(double fontDescriptionY) {
		this.fontDescriptionY = fontDescriptionY;
	}


	public CreditsDataObj(CreditDTO dto){
		this.dto = dto;
		setRenderingPriority(20);
	}
	
	
	@Override
	public void update() {
		setY(getY()-0.6);
		fontDescriptionY -= 0.6;
		
	}

	@Override
	public void draw(Graphics g) {
		Font originalFont = g.getFont();
		Color originalColor = g.getColor();
		
		g.setFont(font);
		g.setColor(Color.white);
		
		g.drawImage(dto.getImg(), getRoundX(), getRoundY(), null);
		g.drawString(dto.getDescription(), (int)fontDescriptionX, (int)fontDescriptionY);
		
		
		g.setFont(originalFont);
		g.setColor(originalColor);
		
		
	}

}	// class CreditsDataObj{}

