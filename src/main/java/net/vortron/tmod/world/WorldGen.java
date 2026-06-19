package net.vortron.tmod.world;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeOcean;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.vortron.tmod.TMod;
import net.vortron.tmod.block.BlockWaterEmeraldOre;

import java.util.Objects;
import java.util.Random;

public class WorldGen implements IWorldGenerator {
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator iChunkGenerator, IChunkProvider iChunkProvider) {
        if(world.provider.getDimension() == 0) {
            generateDeepSeaOre(BlockWaterEmeraldOre.block.getDefaultState(),world, random, chunkX * 16, chunkZ * 16, 1);
        }

    }
    public int getHighestBlockOfType(World world, int x, int z, Block targetBlock) {
        for (int y = world.getSeaLevel(); y >= 0; y--) {
            BlockPos pos = new BlockPos(x, y, z);
            Block block = world.getBlockState(pos).getBlock();
            if (block == targetBlock) {
                return y;
            }
        }
        return -1;
    }
    private void generateDeepSeaOre(IBlockState ore, World world, Random random, int x, int z, int chances) {
        int tryX;
        int tryY;
        int tryZ;
        for (int i = 0; i < chances; i++) {
            tryX = x + random.nextInt(16);
            tryZ = z + random.nextInt(16);
            if((tryY = getHighestBlockOfType(world,tryX,tryZ,Blocks.GRAVEL)) > 0) {
                BlockPos pos = new BlockPos(tryX,tryY,tryZ);
                Biome biome;
                if((biome = world.getBiome(pos)) instanceof BiomeOcean && Objects.equals(biome.getBiomeName(), "Deep Ocean") && random.nextFloat() > 0.8F) {
                    if(world.setBlockState(pos,ore))
                        TMod.LOGGER.info("Spawned Ore at : {}", pos.toString());
                    }
            }
        }
    }
}
