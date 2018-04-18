package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.Runnable;
import java.lang.Thread;
import javax.swing.JFrame;
import javax.imageio.ImageIO;

@SuppressWarnings({ "serial", "unused" })
public class Control extends JFrame implements Runnable
{
	public static int alpha = 0xFFFF00DC;
	private Canvas canvas = new Canvas();
	private RenderHandler renderHandler;
	private BufferedImage testImage;
	private Sprite testSprite;
	private SpriteSheet sheet;
	private Tiles tiles;
	private Map map;
	private GameObject[] para;
	private KeyBoardListener KeyListener = new KeyBoardListener(this);
	private MouseEventListener mouseListener = new MouseEventListener(this);
	private Player player;
	private int xZoom = 3;
	private int yZoom = 3;
	
	public Control()
	{
		// Ensures program terminates when exited.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Sets X, Y position and W, H size of frame.
		setBounds(0,0,1000,800);
		
		// Places frame at the center of the screen.
		setLocationRelativeTo(null);
		
		add(canvas);
		
		// Makes frame visible.
		setVisible(true);
		
		// Don't allow resizing
		setResizable(false);
		
		canvas.createBufferStrategy(3);
		
		renderHandler = new RenderHandler(getWidth(), getHeight());
		
		// Load assets
		BufferedImage sheetImage = loadImage("Tiles1.png");
		sheet = new SpriteSheet(sheetImage);
		sheet.loadSprites(16, 16);
		
		// Load tiles
		tiles = new Tiles(new File("src/Tiles.txt"), sheet);
		
		// Load map
		map = new Map(new File("src/Map.txt"), tiles);
		
		// Load objects
		para = new GameObject[1];
		player = new Player();
		para[0] = player;
		
		// Add Listeners
		canvas.addKeyListener(KeyListener);
		canvas.addFocusListener(KeyListener);
		canvas.addMouseListener(mouseListener);
		canvas.addMouseMotionListener(mouseListener);
	}
	
	public void update()
	{
		for(int i = 0; i < para.length; i++)
		{
			para[i].update(this);
		}
	}
	
	private BufferedImage loadImage(String path)
	{
		try
		{
			BufferedImage loadedImage = ImageIO.read(Control.class.getResource(path));
			BufferedImage formattedImage = new BufferedImage(loadedImage.getWidth(), loadedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
			formattedImage.getGraphics().drawImage(loadedImage, 0, 0, null);
			return(formattedImage);
		}
		catch(IOException exc)
		{
			exc.printStackTrace();
			return(null); 
		}
	}
	
	public void mouseDown(int x, int y)
	{
		x = (int) Math.floor((x + renderHandler.getCamPos().x)/(16.0 * xZoom));
		y = (int) Math.floor((y + renderHandler.getCamPos().y)/(16.0 * yZoom));
		map.setTile(x, y, 2);
	}
	
	public void render() 
	{
			BufferStrategy bufferStrategy = canvas.getBufferStrategy();
			Graphics graphics = bufferStrategy.getDrawGraphics();
			super.paint(graphics);

			map.render(renderHandler, xZoom, yZoom);

			for(int i = 0; i < para.length; i++) 
				para[i].render(renderHandler, xZoom, yZoom);

			renderHandler.render(graphics);

			graphics.dispose();
			bufferStrategy.show();
			renderHandler.clrscr();
	}

	public void run() 
	{
		BufferStrategy bufferStrategy = canvas.getBufferStrategy();
		int i = 0;
		int x = 0;

		long lastTime = System.nanoTime(); //long 2^63
		double nanoSecondConversion = 1000000000.0 / 60; //60 frames per second
		double changeInSeconds = 0;

		while(true) 
		{
			long now = System.nanoTime();

			changeInSeconds += (now - lastTime) / nanoSecondConversion;
			while(changeInSeconds >= 1) {
				update();
				changeInSeconds--;
			}

			render();
			lastTime = now;
		}

	}
	
	public void handleCTRL(boolean[] keys) 
	{
		System.out.println("test");
		if(keys[KeyEvent.VK_S])
			map.saveMap();
	}
	
	public KeyBoardListener getKL()
	{
		return KeyListener;
	}
	
	public RenderHandler getRendHand()
	{
		return renderHandler;
	}
	
	public MouseEventListener getMEL()
	{
		return mouseListener;
	}
	
	public static void main(String[] args)
	{
		Control game = new Control();
		Thread gameThread = new Thread(game);
		gameThread.start();
	}
	
}
