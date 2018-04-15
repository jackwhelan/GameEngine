package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class RenderHandler
{
	private BufferedImage view;
	private Rectangle camera;
	private int[] pixels;
	
	public RenderHandler(int width, int height)
	{
		// Creating a buffered image that will represent our view
		view = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		camera = new Rectangle(0, 0, width, height);
		
		// Create an array for the pixels
		pixels = ((DataBufferInt) view.getRaster().getDataBuffer()).getData();
	}
	
	// Renders pixel array to screen
	public void render(Graphics g)
	{
		g.drawImage(view, 0, 0, view.getWidth(), view.getHeight(), null);
	}
	
	// Render image to array of pixels
	public void renderImage(BufferedImage image, int xPos, int yPos, int xZoom, int yZoom)
	{
		int[] imagePixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
		renderArray(imagePixels, image.getWidth(), image.getHeight(), xPos, yPos, xZoom, yZoom);
	}
	
	public void renderRectangle(Rectangle rectangle, int xZoom, int yZoom)
	{
		int[] rectanglePixels = rectangle.getPixels();
		if (rectanglePixels != null)
		{
			renderArray(rectanglePixels, rectangle.w, rectangle.h, rectangle.x, rectangle.y, xZoom, yZoom);
		}
	}
	
	public void renderArray(int[] renderPixels, int renderWidth, int renderHeight, int xPos, int yPos, int xZoom, int yZoom)
	{
		for (int y = 0; y < renderHeight; y++)
			for(int x = 0; x < renderWidth; x++)
				for(int yZoomPos = 0; yZoomPos < yZoom; yZoomPos++)
					for(int xZoomPos = 0; xZoomPos < xZoom; xZoomPos++)
						setPixel(renderPixels[x + y * renderWidth], ((x * xZoom) + xPos + xZoomPos), ((y * yZoom) + yPos + yZoomPos));
	}
	
	public void renderSprite(Sprite sprite, int xPos, int yPos, int xZoom, int yZoom)
	{
		renderArray(sprite.getPixels(), sprite.getWidth(), sprite.getHeight(), xPos, yPos, xZoom, yZoom);
	}
	
	private void setPixel(int pixel, int x, int y)
	{
		if(x >= camera.x && y >= camera.y && x <= camera.x + camera.w && y <= camera.y + camera.h)
		{
			int pixelIndex = (x - camera.x) + (y - camera.y) * view.getWidth();
			if(pixels.length >= pixelIndex && pixel != Control.alpha)
			{
				pixels[pixelIndex] = pixel;
			}
		}
	}
	
	public Rectangle getCamera()
	{
		return camera;
	}
}
