package main;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Tiles
{
	private SpriteSheet spriteSheet;
	private ArrayList<Tile> tilesList = new ArrayList<Tile>();
	
	// This will only work if the sprites in the spritesheet have been loaded.
	public Tiles(File tilesFile, SpriteSheet spriteSheet)
	{
		this.spriteSheet = spriteSheet;
		try
		{
			Scanner scanner = new Scanner(tilesFile);
			while(scanner.hasNextLine())
			{
				//Read each line and create a tile object.
				String line = scanner.nextLine();
				if(!line.startsWith("//"))
				{
					String[] splitString = line.split("-");
					String tileName = splitString[0];
					int spriteX = Integer.parseInt(splitString[1]);
					int spriteY = Integer.parseInt(splitString[2]);
					Tile tile = new Tile(tileName, spriteSheet.getSprite(spriteX, spriteY));
				}
			}
			scanner.close();
		} 
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	public void renderTile()
	{
		
	}
	
	class Tile
	{
		public String tileName;
		public Sprite sprite;
		
		public Tile(String tileName, Sprite sprite)
		{
			this.tileName = tileName;
			this.sprite = sprite;
		}
	}
}