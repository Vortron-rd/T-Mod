package net.vortron.tmod.item.tool;

import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.vortron.tmod.CreativeTab;
import net.vortron.tmod.util.ItemUtil;

import static net.vortron.tmod.material.MaterialLeaf.leafToolMaterial;

@Mod.EventBusSubscriber
public class ItemLeafHoe extends ItemHoe {
    public ItemLeafHoe() {
        super(leafToolMaterial);
        this.setCreativeTab(CreativeTab.TMod);
        ItemUtil.nameItemId(this, "leaf_hoe");
    }
    public static final Item item = new ItemLeafHoe();


    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(ItemLeafHoe.item);
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void registerModel(ModelRegistryEvent event) {
        ItemUtil.setModel(item, 0);
    }


}