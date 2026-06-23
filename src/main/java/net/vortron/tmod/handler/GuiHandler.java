package net.vortron.tmod.handler;

import net.minecraft.block.Block;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.vortron.tmod.block.BlockCacticrafter;

public class GuiHandler implements IGuiHandler {
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        Block block = world.getBlockState(pos).getBlock();
        if (Block.isEqualTo(BlockCacticrafter.block, block)) {
            return new ContainerWorkbench(player.inventory,world,pos) {
                @Override
                public boolean canInteractWith(EntityPlayer playerIn) {
                    return true;
                }
            };

        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        Block block = world.getBlockState(pos).getBlock();
        if (Block.isEqualTo(BlockCacticrafter.block, block)) {
            return new GuiCrafting(player.inventory,world);
        }
        return null;
    }

}
