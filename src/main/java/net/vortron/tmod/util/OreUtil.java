package net.vortron.tmod.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;

public class OreUtil {
    public static boolean doesBlockMatchOre(Block block, String ore) {
        NonNullList<ItemStack> ores = OreDictionary.getOres(ore);
        for (ItemStack itemStack : ores) {
            if (itemStack.getItem() == Item.getItemFromBlock(block))
                return true;
        }
        return false;
    }
}
