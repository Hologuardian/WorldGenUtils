package holo.utils.world.structure;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.StructureBoundingBox;

public abstract class WorldGenStructure extends WorldGenerator
{
	public StructureBoundingBox structureBounds;
	public Table<IStructureRoom, Integer, IStructureRoom> roomConnections = HashBasedTable.create();
	public List<IStructureRoom> rooms = new ArrayList<IStructureRoom>();
	public final int maxRooms;
	
	public WorldGenStructure(int rooms)
    {
        super();
        maxRooms = rooms;
    }

    public WorldGenStructure(int rooms, boolean doBlockNotify)
    {
    	super(doBlockNotify);
        maxRooms = rooms;
    }
	
	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) 
	{
		structureBounds = new StructureBoundingBox(x, y, z, x, y, z);
		
		IStructureRoom startingRoom = getRandomRoom(new int[]{x, y, z}, -1);
		structureBounds.expandTo(startingRoom.getBB());
		rooms.add(startingRoom);
		
		for (int i = 1; i < maxRooms; ++i)
		{
			IStructureRoom room = rooms.get(rand.nextInt(rooms.size()));
			while(room.getDoorLocations().length == 0)
			{
				room = rooms.get(rand.nextInt(rooms.size()));
			}
			
			int doorSide = room.getDoorLocations()[rand.nextInt(room.getDoorLocations().length)];
			int[] roomLocation = room.getDoorLocationForSide(doorSide);
			IStructureRoom newRoom = getRandomRoom(roomLocation, doorSide);
			
			this.rooms.add(newRoom);
			this.roomConnections.put(room, doorSide, newRoom);
			structureBounds.expandTo(newRoom.getBB());
		}
		
		for(IStructureRoom room : rooms)
		{
			room.generate(world);
		}
		
		return true;
	}
	
	/**
	 * The side provided is the side of the connecting room, if it's -1 the room should be fully centered on the coordinates.
	 * Returns a random room for the structure to generate.
	 * @param doorCoord An int array that specifies the coordinates the door of the new room will be on.
	 * @param side 0 positive Y, 1 negative Y, 2 positive X, 3 positive Z, 4 negative X, 5 negative Z
	 * @return An IStructureRoom that has a side opposite to the one provided centered on the coordinates provided.
	 */
	public abstract IStructureRoom getRandomRoom(int[] doorCoord, int side);
	
	/**
	 * @param room The room that the side check is on.
	 * @param side 0 positive Y, 1 negative Y, 2 positive X, 3 positive Z, 4 negative X, 5 negative Z
	 * @return If the side of the room passed has not been connected.
	 */
	public boolean isRoomSideClear(IStructureRoom room, int side)
	{
		return roomConnections.contains(room, side) ? false : true;
	}
}