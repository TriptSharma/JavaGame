import java.util.*;
import java.io.*;

// Multithreaded Driver 
// Threads are an advanced concept.
// You are not expected to understand or use this file for CSE1OOF.
// It is provied to you so you can make your own games outside of CSE1OOF.
// When we mark the assignment we will be using GameDriver.java, not this file.
public class GameThreaded extends Thread 
{
	GameState game = new GameState();
	
	// This overrides the run() method in the Thread class
	// when we call this.start() the run() method will be executed on its own thread.
	// This means that the code in here is running AT THE SAME TIME as the rest of the app.
	public void run()
	{
		try 
		{
			while(true)
			{
				// Only one thread can be inside a synchronized(game) block at a time.
				// If this thread is inside synchronized(game),
				// then the other thread will block and wait utill we are finished.
				// This is done so that we dont update the game while we are trying to print it.
				synchronized(game)
				{
					game.tick();
				}
				
				// Block for 0.5 seconds.
				Thread.sleep(500);
			}
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	
	private void startGame() throws Exception
	{
		// Spawn a new thread.
		// start is defined in the Thread class, it will call run() on the new thread.
		// this will NOT block, this method will keep executing independatly from what run() is doing.
		this.start();
		
		// We need to handle user input here.
		// keep asking for input forever.
		while(true)
		{
			// read() is a blocking method.
			// It will halt execution untill it recieves input from the user.
			int userInput = System.in.read();
			
			synchronized(game)
			{
				// notify the game.
				boolean continueGame = game.onUserInput(userInput);
				if(continueGame == false)
				{
					// we call exit because we need to terminate both threads.
					System.out.println("Thanks for playing");
					System.exit(0);
				}
				if(!game.isGameFinished())
					game.renderFrame();
				else 
					game.showMenu();
			}
		}
	}
	public static void main(String[] args) throws Exception
	{
		String[] cmd = {"/bin/sh", "-c", "stty --file=/dev/tty -icanon -echo"};
		Runtime.getRuntime().exec(cmd).waitFor();
		GameThreaded game = new GameThreaded();
		game.startGame();
		
	}
}
