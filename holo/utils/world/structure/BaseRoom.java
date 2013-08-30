package holo.utils.world.structure;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.StructureBoundingBox;

public abstract class BaseRoom implements IStructureRoom
{
	StructureBoundingBox structureBB;
	
	public BaseRoom(int x, int y, int z)
	{
		structureBB = getDefaultBB();
		structureBB = moveBB(x, y, z);
	}
	
	@Override
	public StructureBoundingBox getBB() {
		return structureBB;
	}

	@Override
	public StructureBoundingBox moveBB(int x, int y, int z) 
	{
		StructureBoundingBox bb = getBB();
		
		bb.minX += x;
		bb.maxX += x;
		bb.minY += y;
		bb.maxY += y;
		bb.minZ += z;
		bb.maxZ += z;
		
		return bb;
	}

	@Override
	public void generate(World world) 
	{
		int[] blocks = getBlocks();
		
		StructureBoundingBox bb = getBB();
		
		int x = bb.minX;
		int y = bb.minY;
		int z = bb.minZ;
		
		for(int i = x ; i < x + bb.getXSize(); ++i)
		{
			for(int j = y ; j < y + bb.getYSize(); ++j)
			{
				for(int k = z ; k < z + bb.getZSize(); ++k)
				{
					world.setBlock(i, j, k, blocks[hashXYZ(i, j, k)]);
				}
			}
		}
	}
	
	int hashXYZ(int x, int y, int z)
	{
		StructureBoundingBox bb = getBB();
		int ySize = bb.getYSize();
		int zSize = bb.getZSize();
		
		return x * ySize * zSize + y * zSize + z;
	}
}
