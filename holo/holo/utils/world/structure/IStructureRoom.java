package holo.utils.world.structure;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;

public interface IStructureRoom 
{
	/**
	 * @return The structure bounding box for the room.
	 */
	public abstract StructureBoundingBox getBB();
	
	/**
	 * @return The structure bounding box for the room with no coordinates only the sizes.
	 */
	public abstract StructureBoundingBox getDefaultBB();
	
	/**
	 * Shifts the bounding box to the x, y, z coordinates provided.
	 * @param x
	 * @param y
	 * @param z
	 * @return Shifted bounding box.
	 */
	public abstract StructureBoundingBox moveBB(int x, int y, int z);
	
	/**
	 * Checks to see if a door can be created at the specified coordinates used to align rooms.
	 * @param side 0 positive Y, 1 negative Y, 2 positive X, 3 positive Z, 4 negative X, 5 negative Z
	 * @return An int array of the coordinates to create the door on;
	 */
	public abstract int[] getDoorLocationForSide(int side);
	
	/**
	 * @return Location of the door that is created on the specified side. Side ide 0 positive Y, 1 negative Y, 2 positive X, 3 positive Z, 4 negative X, 5 negative Z.
	 */
	public abstract int[] getDoorLocations();
	
	/**
	 * @param blockX
	 * @param blockY
	 * @param blockZ
	 * @return If a decoration feature can be placed here.
	 */
	public abstract boolean canDecorate(int blockX, int blockY, int blockZ);
	
	/**
	 * @return A List of valid decorations for the room.
	 */
	public abstract IStructureDecoration getRandomDecoration(int[] coords);
	
	/**
	 * @return An int array of blockIDs of the room hashed X then Y then Z.
	 */
	public abstract Block[] getBlocks();
	
	/**
	 * Generates the room within the structureBounding box.
	 */
	public abstract void generate(World world);
}
