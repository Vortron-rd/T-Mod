package net.vortron.tmod.item.tool;

import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.vortron.tmod.CreativeTab;
import net.vortron.tmod.util.ItemUtil;

import static net.vortron.tmod.material.Materials.leafToolMaterial;

@Mod.EventBusSubscriber
public class ItemLeafAxe extends ItemAxe {
    public ItemLeafAxe() {
        super(leafToolMaterial, leafToolMaterial.getAttackDamage()+2,-2.9F);
        this.setCreativeTab(CreativeTab.TMod);
        ItemUtil.nameItemId(this, "leaf_axe");
    }
    public static final Item item = new ItemLeafAxe();


    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(ItemLeafAxe.item);
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void registerModel(ModelRegistryEvent event) {
        ItemUtil.setModel(item, 0);
    }


}