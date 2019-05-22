package net.minecraft.client.addon.tchestplate;

import cpw.mods.fml.common.IWorldGenerator;
import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class WorldgeneratorOre implements IWorldGenerator
{
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        switch (world.provider.dimensionId)
        {
            case -1:
                this.generateNether(world, random, chunkX * 16, chunkZ * 16);

            case 0:
                this.generateSurface(world, random, chunkX * 16, chunkZ * 16);

            case 1:
                this.generateEnd(world, random, chunkX * 16, chunkZ * 16);

            default:
        }
    }

    private void generateEnd(World world, Random random, int chunkX, int chunkZ)
    {
    }

    private void generateSurface(World world, Random random, int chunkX, int chunkZ)
    {
        this.genOre(world, random, chunkX, chunkZ, LavaChestPlate.adamantiteOre.blockID, 3, 3, 35, 10);
        this.genOre(world, random, chunkX, chunkZ, LavaChestPlate.palladiumOre.blockID, 3, 20, 50, 15);
        this.genOre(world, random, chunkX, chunkZ, LavaChestPlate.cobaltOre.blockID, 4, 35, 50, 25);
    }

    private void genOre(World world, Random random, int chunkX, int chunkZ, int BlockID, int BlockSum, int Min, int Max, int count)
    {
        int rY = Min + (int)(Math.random() * (double)(Max - Min + 1));

        for (int i = 0; i < random.nextInt(count); ++i)
        {
            int randPosX = chunkX + random.nextInt(16);
            int randPosZ = chunkZ + random.nextInt(16);
            (new WorldGenMinable(BlockID, BlockSum)).generate(world, random, randPosX, rY, randPosZ);
        }
    }

    private void generateNether(World world, Random random, int chunkX, int chunkZ)
    {
    }
}
