package holo.utils.world.structure;

import java.util.Random;

import net.minecraft.world.gen.structure.StructureBoundingBox;

public abstract class StructureRoom 
{
	private StructureBoundingBox bounds;
	private Random rand;
	
	public StructureRoom(int[] minCoords, int[] maxCoords, Random random)
	{
		bounds = new StructureBoundingBox(minCoords[0], minCoords[1], minCoords[2], maxCoords[0], maxCoords[1], maxCoords[2]);
		rand = random;
	}
	
	/**
	 * Checks to see if a door can be created at the specified coordinates used to align rooms.
	 * @param blockX
	 * @param blockY
	 * @param blockZ
	 * @return If a door can be created at the specified spot.
	 */
	public abstract boolean isDoor(int blockX, int blockY, int blockZ);
	
	/**
	 * 
	 * @param blockX
	 * @param blockY
	 * @param blockZ
	 * @return If a decoration feature can be placed here.
	 */
	public abstract boolean canDecorate(int blockX, int blockY, int blockZ);
	
	public StructureBoundingBox getBB()
	{
		return this.bounds;
	}
}
