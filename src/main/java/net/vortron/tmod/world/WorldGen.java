package net.vortron.tmod.world;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeOcean;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.vortron.tmod.TMod;
import net.vortron.tmod.block.BlockWaterEmeraldOre;

import java.util.Objects;
import java.util.Random;

public class WorldGen implements IWorldGenerator {
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator iChunkGenerator, IChunkProvider iChunkProvider) {
        if(world.provider.getDimension() == 0) {
            generateOre(BlockWaterEmeraldOre.block.getDefaultState(),world, random, chunkX * 16, chunkZ * 16, 35, 45, 4 + random.nextInt(4), 6);
        }

    }
    private void generateOre(IBlockState ore, World world, Random random, int x, int z, int minY, int maxY, int size, int chances) {
        int deltaY = maxY - minY;
        for (int i = 0; i < chances; i++) {
            BlockPos pos = new BlockPos(x + random.nextInt(16), minY + random.nextInt(deltaY), z + random.nextInt(16));
            Biome biome;
            if((biome = world.getBiome(pos)) instanceof BiomeOcean && Objects.equals(biome.getBiomeName(), "Deep Ocean")) {
                WorldGenMinable generator = new WorldGenMinable(ore, size);
                generator.generate(world, random, pos);
                if(world.getBlockState(pos).getBlock() == BlockWaterEmeraldOre.block)
                    TMod.LOGGER.info("Spawned Ore at : {}", pos.toString());
            }
        }
    }
}
