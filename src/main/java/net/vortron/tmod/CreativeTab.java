package net.vortron.tmod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.vortron.tmod.item.ItemPizzaPendant;

public class CreativeTab extends CreativeTabs {

    public CreativeTab() {
        super(Reference.MOD_ID);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ItemStack createIcon() {
        return new ItemStack(ItemPizzaPendant.item);
    }
    public static final CreativeTabs TMod = new CreativeTab();
}
