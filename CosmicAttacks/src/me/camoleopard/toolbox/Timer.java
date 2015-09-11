package me.camoleopard.toolbox;

public class Timer {
	
	private long lastTime = System.nanoTime();
	private long timeInterval = 0;
	private boolean isPaused = false;
	
	public Timer(long timeInterval)
	{
		this.timeInterval = timeInterval;
	}
	
	public long getInterval(){
		return timeInterval;
	}

	public boolean ifFinishedReset()
	{
		boolean temp = isFinished();
		if(temp)reset();
		return temp;
	}
	
	public void reset()
	{
		lastTime = System.nanoTime();
	}
	
	public double getPercentageCompleted()
	{
		return (double)(System.nanoTime() - lastTime)/(double)timeInterval;
	}
	
	public void play()
	{
		if(isPaused)
		{
			reset();
		}
		isPaused=false;
	}
	
	public void pause()
	{
		isPaused=true;
		reset();
	}
	
	
	public boolean isFinished()
	{
		if(isPaused)
		{
			reset();
			return false;
		}
		
		return System.nanoTime() - lastTime >= timeInterval;
	}
	
	public static long secondsToNano(float seconds)
	{
		return (long)(seconds*1000000000);
	}
	
	public void setInterval(long nanoSeconds)
	{
		timeInterval = nanoSeconds;
	}

}
