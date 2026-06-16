package net.vortron.tmod.item.armor;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.vortron.tmod.CreativeTab;
import net.vortron.tmod.material.MaterialLeaf;
import net.vortron.tmod.util.ItemUtil;

@Mod.EventBusSubscriber
public class ItemLeafChestplate extends ItemArmor {
    public ItemLeafChestplate() {
        super(MaterialLeaf.leafArmorMaterial,0, EntityEquipmentSlot.CHEST);
        this.setCreativeTab(CreativeTab.TMod);
        ItemUtil.nameItemId(this, "leaf_chestplate");
    }
    public static final Item item = new ItemLeafChestplate();


    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(ItemLeafChestplate.item);
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void registerModel(ModelRegistryEvent event) {
        ItemUtil.setModel(item, 0);
    }


}