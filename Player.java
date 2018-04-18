package main;

public class Player implements GameObject
{
	Rectangle playTangle;
	int speed = 10;
	
	public Player()
	{
		playTangle = new Rectangle(32, 16, 16, 32);
		playTangle.generateGraphics(2, 0xFF00FF90);
	}
	
	// Call whenever possible
	public void render(RenderHandler r, int xZoom, int yZoom)
	{
		r.renderRectangle(playTangle, xZoom, yZoom);
	}
	
	// Call at 60 fps
	public void update(Control game)
	{
		KeyBoardListener keyListener = game.getKeyListener();
		
		if(keyListener.up())
			playTangle.y = playTangle.y - speed;
		if(keyListener.down())
			playTangle.y = playTangle.y + speed;
		if(keyListener.left())
			playTangle.x = playTangle.x - speed;
		if(keyListener.right())
			playTangle.x = playTangle.x + speed;
		
		focusPlayer(game.getRendHand().getCamPos());
	}
	
	// Updates the camera Rectangle to focus on the player.
	public void focusPlayer(Rectangle camera)
	{
		camera.x = playTangle.x - (camera.w / 2);
		camera.y = playTangle.y - (camera.h / 2);
	}
}
