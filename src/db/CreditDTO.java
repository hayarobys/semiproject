package db;

import java.awt.Image;

public class CreditDTO {
	private Image img;
	private String description;
	
	public CreditDTO(Image img, String description){
		this.img = img;
		this.description = description;
		System.out.println("CreditDTO가 생성됨 (" + description + ")");
	}
	
	public Image getImg() {
		return img;
	}
	
	public void setImg(Image img) {
		this.img = img;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

}
