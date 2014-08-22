package holo.utils.world.structure;

import java.util.ArrayList;

import net.minecraft.world.gen.structure.StructureBoundingBox;

public interface IStructureDecoration 
{
	public ArrayList<IStructureRoom> validRooms = new ArrayList<IStructureRoom>();

	/**
	 * @return The structure bounding box for the feature.
	 */
	public abstract StructureBoundingBox getBB();
	
	/**
	 * @return An int array of blockIDs of the feature hashed X then Y then Z.
	 */
	public abstract int[] getBlocks();
	
	/**
	 * @return If the feature can generate at the location specified by its structure bounding box.
	 */
	public abstract boolean canGenerate();
}
