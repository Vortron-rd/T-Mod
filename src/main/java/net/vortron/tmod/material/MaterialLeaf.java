package net.vortron.tmod.material;

import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;
import net.vortron.tmod.item.ItemSuperLeaf;

public class MaterialLeaf {
    public static Item.ToolMaterial leafToolMaterial = EnumHelper.addToolMaterial("LEAF", 4, 3500, 10, 9, 16).setRepairItem(ItemSuperLeaf.item.getDefaultInstance());
    public static final ItemArmor.ArmorMaterial leafArmorMaterial = EnumHelper.addArmorMaterial("LEAF", net.vortron.tmod.Reference.MOD_ID + ":leaf", 20, new int[]{4, 7, 8, 3}, 16, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.5F).setRepairItem(ItemSuperLeaf.item.getDefaultInstance());
}
