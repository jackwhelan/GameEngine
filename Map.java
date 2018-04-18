package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Map
{
	private Tiles allTiles;
	private int defaultTile = 0;
	private ArrayList<MappedTile> mappedTiles = new ArrayList<MappedTile>();
	private File mapFile;
	
	public Map(File mapFile, Tiles allTiles)
	{
		this.allTiles = allTiles;
		this.mapFile = mapFile;
		
		try
		{
			Scanner sc = new Scanner(mapFile);
			while(sc.hasNextLine()) 
			{
				String line = sc.nextLine();
				if(!line.startsWith("//"))
				{
					if(line.contains(":"))
					{
						String[] splitStr = line.split(":");
						if(splitStr[0].equalsIgnoreCase("fill"))
						{
							defaultTile = Integer.parseInt(splitStr[1]);
							continue;
						}
					}
					
					String[] splitStr = line.split(",");
					if(splitStr.length >= 3)
					{
						MappedTile mTile = new MappedTile(Integer.parseInt(splitStr[0]),
														  Integer.parseInt(splitStr[1]),
														  Integer.parseInt(splitStr[2]));
						mappedTiles.add(mTile);
					}
				}
			}
			sc.close();
		}
		catch(FileNotFoundException FNFE)
		{
			FNFE.printStackTrace(); 
		}
	}
	
	public void render(RenderHandler r, int xZoom, int yZoom)
	{
		int tileWidth = 16 * xZoom;
		int tileHeight = 16 * yZoom;
		
		if(defaultTile >= 0)
		{
			Rectangle camera = r.getCamPos();
			
			for(int y = camera.y - tileHeight - (camera.y % tileHeight); y < camera.y + camera.h; y+= tileHeight)
			{
				for(int x = camera.x - tileWidth - (camera.x % tileWidth); x < camera.x + camera.w; x+= tileWidth)
				{
					allTiles.renderTile(defaultTile, r, x, y, xZoom, yZoom);
				}
			}
		}
		
		for(int i = 0; i < mappedTiles.size(); i++)
		{
			MappedTile mTile = mappedTiles.get(i);
			allTiles.renderTile(mTile.id, r, mTile.x * tileWidth, mTile.y * tileHeight, xZoom, yZoom);
		}
	}
	
	public void setTile(int tileXPos, int tileYPos, int id)
	{
		boolean exists = false;
		
		for (int i = 0; i < mappedTiles.size(); i++)
		{
			MappedTile mTile = mappedTiles.get(i);
			if(mTile.x == tileXPos && mTile.y == tileYPos)
			{
				mTile.id = id;
				exists = true;
				break; // Breaks out of for loop as tile is found.
			}
			
			if(!exists)
			{
				mappedTiles.add(new MappedTile(id, tileXPos, tileYPos));
			}
		}
	}
	
	public void saveMap()
	{
		try
		{
//			if(mapFile.exists())
//			{
//				mapFile.delete();
//			}
			
			File test = new File("src/test.txt");
			test.createNewFile();
			
			PrintWriter printWriter = new PrintWriter(mapFile);
			
			if(defaultTile >= 0)
			{
				printWriter.println("Fill:" + defaultTile);
			}
		}
		catch(java.io.IOException e)
		{
			e.printStackTrace();
		}
	}
	
	class MappedTile
	{
		public int id, x, y;
		
		public MappedTile(int id, int x, int y)
		{
			this.id = id;
			this.x = x;
			this.y = y;
		}
	}
}
