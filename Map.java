package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Map
{
	private Tiles allTiles;
	private int defaultTile = -1;
	private ArrayList<MappedTile> mappedTiles = new ArrayList<MappedTile>();
	
	public Map(File map, Tiles allTiles)
	{
		this.allTiles = allTiles;
		
		try
		{
			Scanner sc = new Scanner(map);
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
					
					String[] splitStr = line.split("-");
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
		int xInc = 16 * xZoom;
		int yInc = 16 * yZoom;
		
		if(defaultTile >= 0)
		{
			Rectangle camera = r.getCamera();
			for (int y = 0; y < camera.w; y++)
			{
				for (int x = 0; x < camera.h; x++)
				{
					
				}
			}
		}
		
		for(int i = 0; i < mappedTiles.size(); i++)
		{
			MappedTile mTile = mappedTiles.get(i);
			allTiles.renderTile(mTile.id, r, mTile.x * xInc, mTile.y * yInc, xZoom, yZoom);
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
