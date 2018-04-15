package main;

public interface GameObject
{
	// Call whenever possible
	public void render(RenderHandler r, int xZoom, int yZoom);
	
	// Call at 60 fps
	public void update(Control game);
}
