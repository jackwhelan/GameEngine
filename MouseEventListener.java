package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseEventListener implements MouseListener, MouseMotionListener
{
	private Control instance;

	public MouseEventListener(Control instance)
	{
		this.instance = instance;
	}
	
	public void mouseDragged(MouseEvent e)
	{
		
	}

	public void mouseMoved(MouseEvent e)
	{
		
	}

	public void mouseClicked(MouseEvent e)
	{
		
	}

	public void mouseEntered(MouseEvent e)
	{
		
	}

	public void mouseExited(MouseEvent e)
	{
		
	}

	public void mousePressed(MouseEvent e)
	{
		if(e.getButton() == MouseEvent.BUTTON1)
		{
			instance.mouseDown(e.getX(), e.getY());
		}
	}

	public void mouseReleased(MouseEvent e)
	{
		
	}
}
