package system;

public class FpsDelayer{
    private int TARGET_FPS; // 목표 FPS
    private int MSPF;       // Milli Second Per Frame
    private int delay;
    
    public FpsDelayer(int targetFps){
        TARGET_FPS = targetFps;
        MSPF = (int)Math.round((1000.0/TARGET_FPS));
  		// 목표한 FPS를 맞추기 위해 한 프레임에 몇 밀리초가 필요한지 계산한다.
		// 예를들어 30 FPS를 원한다면 1000ms / 30Frame = 33 ms/frame 가 된다.
    }
    
    public void delay(int operatingTime){
    // 이번 루프의 연산시간을 받으면 목표 FPS값에 맞추어 프레임을 지연시킨다.
        if(operatingTime > MSPF){
		// 단, 연산시간이 너무 길었다면 지체없이 끝낸다.
            return;
        }else{
            try{
            	delay = (int)(MSPF - operatingTime);
                Thread.sleep( delay );
            }catch(Exception e){}
        }    
    }    // end of delay
    
	public void delay(long operatingTime){
		delay((int)operatingTime);
	}
	
	public int getDelay(){
		return delay;
	}
}    // end of class