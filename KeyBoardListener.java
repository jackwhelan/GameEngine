package main;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;

public class KeyBoardListener implements KeyListener, FocusListener
{
	public boolean[] valKeys = new boolean[150];
	
	// Need to add these even if unused.
	public void keyTyped(KeyEvent e){}
	public void focusGained(FocusEvent e){}

	// When focus is lost, set all keys to unpressed.
	public void focusLost(FocusEvent e)
	{
		for(int i = 0; i < valKeys.length; i++)
		{
			valKeys[i] = false;
		}
	}

	// When a key is pressed set the (keyCode) index of (valKeys) to true
	public void keyPressed(KeyEvent e)
	{
		int keyCode = e.getKeyCode();
		if(keyCode < valKeys.length)
		{
			valKeys[keyCode] = true;
		}
	}

	// When a key is released set the (keyCode) index of (valKeys) to false
	public void keyReleased(KeyEvent e)
	{
		int keyCode = e.getKeyCode();
		if(keyCode < valKeys.length)
		{
			valKeys[keyCode] = false;
		}
	}
	
	public boolean up()
	{
		return valKeys[KeyEvent.VK_W] || valKeys[KeyEvent.VK_UP];
	}
	
	public boolean down()
	{
		return valKeys[KeyEvent.VK_S] || valKeys[KeyEvent.VK_DOWN];
	}
	
	public boolean left()
	{
		return valKeys[KeyEvent.VK_A] || valKeys[KeyEvent.VK_LEFT];
	}
	
	public boolean right()
	{
		return valKeys[KeyEvent.VK_D] || valKeys[KeyEvent.VK_RIGHT];
	}
	
}
