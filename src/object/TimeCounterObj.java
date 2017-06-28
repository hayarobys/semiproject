package object;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Calendar;

import system.GameObject;
import system.MainFrame;

public class TimeCounterObj extends GameObject {
	private static long currentTime;
	private static long timeRecord;
	private static long startTime;
	private static long stopTime;
	
	Color color = new Color(255, 255, 255);
	StringBuilder sb;
	
	public TimeCounterObj(){
		sb = new StringBuilder().append("시간: ");
		
		setLocation(MainFrame.FRAME_WIDTH/2 - 20, 40);
		startTime = 0;
		stopTime = 0;
	}
	
	@Override
	public void update() {
		currentTime = Calendar.getInstance().getTimeInMillis();
		timeRecord = (currentTime - startTime) / 1000;
	}

	@Override
	public void draw(Graphics g) {
		Color originalColor = g.getColor();
		
		
		g.setColor(color);
		
		sb.replace(4, sb.length(), Long.toString(timeRecord)).append(" s");
		g.drawString(sb.toString(), (int)getX(), (int)getY());
		
		
		g.setColor(originalColor);

	}

	public static void startTimer(){
		startTime = Calendar.getInstance().getTimeInMillis();
	}
	
	public static void stopTimer(){
		stopTime = Calendar.getInstance().getTimeInMillis();
	}
	
	public static long getTimeRecord(){
		return timeRecord;
	}
}
