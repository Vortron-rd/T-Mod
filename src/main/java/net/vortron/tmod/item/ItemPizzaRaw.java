package net.vortron.tmod.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.vortron.tmod.CreativeTab;
import net.vortron.tmod.util.ItemUtil;

@Mod.EventBusSubscriber
public class ItemPizzaRaw extends ItemFood {
    public ItemPizzaRaw(int i, float f, boolean bl) {
        super(i, f, bl);
        this.setMaxStackSize(16);
        this.setMaxDamage(0);
        this.setCreativeTab(CreativeTab.TMod);
        ItemUtil.nameItemId(this, "raw_pizza");
    }
    public static final Item item = new ItemPizzaRaw(2, 3, false);


    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(ItemPizzaRaw.item);
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void registerModel(ModelRegistryEvent event) {
        ItemUtil.setModel(item, 0);
    }


}