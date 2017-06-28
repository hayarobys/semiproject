package scene;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;

import db.DodgeRankDAO;
import db.DodgeRankDTO;
import system.GameObject;
import system.MainFrame;
import system.MyKeyListener;
import system.Scene;
import system.SceneManager;

public class RankScene extends Scene {
	ArrayList<DodgeRankDTO> rankList;
	Graphics g;
	
	
	private MyKeyListener myKeyListener;
	
	int showedRankData = 23	;// 보여질 랭크데이터의 갯수
	
	int rankFontHeight;
	int dRankWidth;
	int dRecordWidth;
	int dNameWidth;
	int dDateWidth;
	int fontAscent;
	
	int topGap = 0;
	int leftGap = 0;
	int gap = 30;
	
	boolean colorState;
	
	public static final Color columnNameFontColor = new Color(18, 144, 195);
	public static final Color columnNameBackColor = new Color(34, 34, 34);
	
	public static final Color group1FontColor = new Color(255, 170, 0);
	public static final Color group1BackColor = new Color(34, 34, 34);
	
	public static final Color group2FontColor = new Color(255, 170, 0);
	public static final Color group2BackColor = new Color(54, 54, 54);
	
	private Color fontColor = group1FontColor;
	private Color rankBackColor = group1BackColor;
	
	private Font columnFont;
	private Font rankFont;
	private FontMetrics fontMetrics;
	
	SceneManager sceneManager;
	
	public RankScene(SceneManager sceneManager, Graphics g){
		super();
		setSceneName("RankScene");
		this.g = g;
		this.sceneManager = sceneManager;
		
		columnFont = new Font("DIALOG_INPUT", Font.BOLD, 18);
		rankFont = new Font("DIALOG_INPUT", Font.PLAIN, 18);
		fontMetrics = g.getFontMetrics(rankFont);
		
		rankFontHeight = fontMetrics.getHeight();
		dRankWidth = fontMetrics.stringWidth("55");
		dRecordWidth = fontMetrics.stringWidth("0000");
		dNameWidth = fontMetrics.stringWidth("하두세네다여일여아열하둘셋");
		dDateWidth = fontMetrics.stringWidth("2017-03-28 16:06:13.0");
		fontAscent = fontMetrics.getAscent();
		leftGap = (MainFrame.FRAME_WIDTH - (dRankWidth + gap + dRecordWidth + gap + dNameWidth + gap + dDateWidth))/2;
		
		
	}
	
	void changeColor(){
		if(colorState){
			rankBackColor = group1BackColor;
			fontColor = group1FontColor;
		}else{
			rankBackColor = group2BackColor;
			fontColor = group2FontColor;
		}
		
		colorState = !colorState;
	}
	
	public void leaveScene(){
		super.leaveScene();
		System.out.println("leaved " + getSceneName());
		
		rankList = null;
		
		
		myKeyListener = null;
		sceneManager.getMainFrame().removeKeyListener(myKeyListener);
	}
	
	public void enterScene(Graphics g){
		super.enterScene(g);
		System.out.println("entered " + getSceneName());
		this.g = g;
		
		rankList = new DodgeRankDAO().select(showedRankData);
		
		myKeyListener = new MyKeyListener();
		sceneManager.getMainFrame().addKeyListener(myKeyListener);
	}
	
	@Override
	public void addGameObject(GameObject o) {
		
		
	}

	@Override
	public void update() {
		if(MyKeyListener.getKeyAllBuff() != 0){
			System.out.println("키입력 감지!");
			SceneManager.sceneChange("TitleScene");
		}
		
	}

	@Override
	public void draw(Graphics g) {
		Color originalColor = g.getColor();
		Font originalFont = g.getFont();
		g.setFont(columnFont);
		
		//changeColor();
		
		g.setColor(Color.darkGray);
		g.fillRect(0, 0, MainFrame.FRAME_WIDTH, MainFrame.FRAME_HEIGHT);
		
		g.setColor(columnNameBackColor);
		g.fillRect(0, topGap, MainFrame.FRAME_WIDTH, rankFontHeight);
		
		g.setColor(columnNameFontColor);
		g.drawString("순위", leftGap - 60, topGap + fontAscent);
		g.drawString("기록", leftGap, topGap + fontAscent);
		g.drawString("이름", leftGap + dRecordWidth + gap, topGap + fontAscent);
		g.drawString("날짜", leftGap + dRecordWidth + gap + dNameWidth + gap, topGap + fontAscent);
		
		g.setFont(rankFont);
		DodgeRankDTO dto = null;
		for(int i = 0; i < rankList.size(); i++){
			dto = rankList.get(i);
			changeColor();
			
			g.setColor(rankBackColor);
			g.fillRect(0, topGap + rankFontHeight * (i+1), MainFrame.FRAME_WIDTH, rankFontHeight);
			
			g.setColor(fontColor);
			g.drawString(Integer.toString(i+1), leftGap - 60, topGap + rankFontHeight * (i+1) + fontAscent);
			g.drawString(Integer.toString(dto.getDrecord()), leftGap, topGap + rankFontHeight * (i+1) + fontAscent);
			g.drawString(dto.getDname(), leftGap + dRecordWidth + gap, topGap + rankFontHeight * (i+1) +fontAscent);
			g.drawString(dto.getDdate(), leftGap + dRecordWidth + gap + dNameWidth + gap, topGap + rankFontHeight * (i+1) + fontAscent);
			
			if(i >= showedRankData)	break;
		}
		
		
		
		g.setFont(originalFont);
		g.setColor(originalColor);
		colorState = false;
		
	}

	
	
	@Override
	public void lineupObjectToRenderingPriority() {
		
		
	}



}
