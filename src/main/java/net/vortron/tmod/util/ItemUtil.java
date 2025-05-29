package net.vortron.tmod.util;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.vortron.tmod.Reference;

public class ItemUtil {
    public static void setModel(Item item, int meta) {
        ModelLoader.setCustomModelResourceLocation(item, meta,
                new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
    public static void nameItemId(Item item, String name) {
        item.setRegistryName(Reference.MOD_ID, name).setTranslationKey(Reference.MOD_ID + "." + name);
    }


}

