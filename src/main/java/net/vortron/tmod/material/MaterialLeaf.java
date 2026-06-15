package net.vortron.tmod.material;

import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;
import net.vortron.tmod.item.ItemSuperLeaf;

public class MaterialLeaf {
    public static Item.ToolMaterial leafToolMaterial = EnumHelper.addToolMaterial("Leaf", 4, 3500, 10, 9, 16).setRepairItem(ItemSuperLeaf.item.getDefaultInstance());
}
