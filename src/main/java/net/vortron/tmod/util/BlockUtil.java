package net.vortron.tmod.util;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.vortron.tmod.TMod;

public class BlockUtil {
    public static void setModel(net.minecraft.block.Block block, int meta) {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta,
                new ModelResourceLocation(block.getRegistryName(), "inventory"));
    }
    public static void nameBlockId(Block block, String name) {
        block.setRegistryName(TMod.MOD_ID, name).setTranslationKey(TMod.MOD_ID + "." + name);
    }


}

