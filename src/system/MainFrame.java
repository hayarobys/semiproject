package system;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class MainFrame extends JFrame{
	private BufferStrategy bs;
	public static final int FRAME_WIDTH = 952;
	public static final int FRAME_HEIGHT = 576;
	Container ct;
	
	
	MainFrame(String title){
		super(title);
		
		ct = getContentPane();
		
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		
		Toolkit tk =Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		setLocation(
				(screenSize.width - FRAME_WIDTH)/2 + 320,
				(screenSize.height - FRAME_HEIGHT)/2 + 30
		);
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setVisible(true);
		
		createBufferStrategy(2);
		bs = getBufferStrategy();	
	}
	
	public BufferStrategy getMainBufferStrategy(){
		return bs;
	}
	
	public int getScreenWidth(){
		return FRAME_WIDTH;
	}
	
	public int getScreenHeight(){
		return FRAME_HEIGHT;
	}
}	// class MainFrame{}
