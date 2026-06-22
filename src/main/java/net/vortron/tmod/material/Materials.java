package net.vortron.tmod.material;

import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;
import net.vortron.tmod.TMod;
import net.vortron.tmod.item.ItemSuperLeaf;
import net.vortron.tmod.item.ItemWaterEmerald;

public class Materials {
    public static Item.ToolMaterial leafToolMaterial = EnumHelper.addToolMaterial("LEAF", 4, 3500, 10, 9, 16).setRepairItem(ItemSuperLeaf.item.getDefaultInstance());
    public static ItemArmor.ArmorMaterial leafArmorMaterial = EnumHelper.addArmorMaterial("LEAF", TMod.MOD_ID + ":leaf", 20, new int[]{4, 7, 8, 3}, 16, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.5F).setRepairItem(ItemSuperLeaf.item.getDefaultInstance());
    public static Item.ToolMaterial waterEmeraldToolMaterial = EnumHelper.addToolMaterial("WATER_EMERALD", 3, 2000, 3F, 0.5F, 10).setRepairItem(ItemWaterEmerald.item.getDefaultInstance());
    public static Item.ToolMaterial cactusToolMaterial = EnumHelper.addToolMaterial("CACTUS", 1, 200, 3F, 4F, 7).setRepairItem(Item.getItemFromBlock(Blocks.CACTUS).getDefaultInstance());
    public static ItemArmor.ArmorMaterial cactusArmorMaterial = EnumHelper.addArmorMaterial("CACTUS", TMod.MOD_ID + ":cactus", 6, new int[]{2, 3, 3, 1}, 8, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0F).setRepairItem(Item.getItemFromBlock(Blocks.CACTUS).getDefaultInstance());


}
