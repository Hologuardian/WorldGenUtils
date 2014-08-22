package holo.utils.world.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

public abstract class BaseMapGen
{
    /** The number of Chunks to gen-check in any given direction. */
    protected int range = 8;

    /** The RNG used by the MapGen classes. */
    protected Random rand = new Random();

    /** This world object. */
    protected World worldObj;

    public void generate(IChunkProvider chunkProvider, World world, int centerX, int centerZ, Block[] shortArray)
    {
        int k = this.range;
        this.worldObj = world;
        this.rand.setSeed(world.getSeed());
        long l = this.rand.nextLong();
        long i1 = this.rand.nextLong();

        for (int chunkX = centerX - k; chunkX <= centerX + k; ++chunkX)
        {
            for (int chunkZ = centerZ - k; chunkZ <= centerZ + k; ++chunkZ)
            {
                long l1 = (long)chunkX * l;
                long i2 = (long)chunkZ * i1;
                this.rand.setSeed(l1 ^ i2 ^ world.getSeed());
                this.recursiveGenerate(world, chunkX, chunkZ, centerX, centerZ, shortArray);
            }
        }
    }

    public abstract boolean shouldGenerate(int centerX, int centerZ);

	/**
     * Recursively called by generate() (generate) and optionally by itself.
     */
    protected abstract void recursiveGenerate(World world, int chunkX, int chunkZ, int centerX, int centerZ, Block[] shortArray);
    
    public int hashCoord(int x, int y, int z)
    {
    	return y << 8 | z << 4 | x;
    }
    
    public int getTopSolidOrLiquidBlock(int x, int z, short[] shortArray)
    {
    	int y = 0;
    	for(int i = 0; i < 256; ++i)
    	{
    		int h = hashCoord(x, y, z);
    		if (h < 0)
    			System.out.println(x + " " + y + " " + z);
    		if (shortArray[h] != 0)
    			y = i;
    	}
    	return y;
    }
}
