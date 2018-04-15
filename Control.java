package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
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
	private Rectangle testRectangle = new Rectangle(30,30,100,100);
	private Tiles tiles;
	private Map map;
	private GameObject[] para;
	private KeyBoardListener KeyListener = new KeyBoardListener();
	private Player player;
	
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
		
		//testImage = loadImage("GrassTile.png");
		//testSprite = sheet.getSprite(4, 1);
		
		testRectangle.generateGraphics(3, 12234);
		
		// Load objects
		para = new GameObject[1];
		player = new Player();
		para[0] = player;
		
		// Add Listeners
		canvas.addKeyListener(KeyListener);
		canvas.addFocusListener(KeyListener);
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
	
	public void render()
	{
		BufferStrategy bufferStrategy = canvas.getBufferStrategy();
		Graphics g = bufferStrategy.getDrawGraphics();
		super.paint(g);
		
		map.render(renderHandler, 3, 3);
		//renderHandler.renderRectangle(testRectangle, 1, 1);
		
		for(int i = 0; i < para.length; i++)
		{
			para[i].render(renderHandler, 3, 3);
		}
		
		renderHandler.render(g);
		
		g.dispose();
		bufferStrategy.show();
	}
	
	
	
	public void run()
	{
		BufferStrategy bufferStrategy = canvas.getBufferStrategy();
		
		int i = 0;
		int x = 0;
		
		long lastTime = System.nanoTime();
		double nanoSecondConversion = 1000000000.0 / 60; // 60 = fps
		double changeInSeconds = 0;
		
		while(true)
		{
			long now = System.nanoTime();
			
			changeInSeconds += (now - lastTime) / nanoSecondConversion;
			
			while(changeInSeconds >= 1)
			{
				update();
				changeInSeconds = 0;
			}
			
			render();
			lastTime = now;
		}
		
	}
	
	public KeyBoardListener getKeyListener()
	{
		return KeyListener;
	}
	
	public static void main(String[] args)
	{
		Control game = new Control();
		Thread gameThread = new Thread(game);
		gameThread.start();
	}
	
}
