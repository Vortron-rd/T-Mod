package net.vortron.tmod.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.vortron.tmod.CreativeTab;
import net.vortron.tmod.util.ItemUtil;


import static net.vortron.tmod.util.OreUtil.doesBlockMatchOre;

@Mod.EventBusSubscriber
public class ItemSuperLeaf extends Item {
    public ItemSuperLeaf() {
        this.setMaxStackSize(64);
        this.setMaxDamage(0);
        this.setCreativeTab(CreativeTab.TMod);
        ItemUtil.nameItemId(this, "super_leaf");
    }
    public static final Item item = new ItemSuperLeaf();


    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(ItemSuperLeaf.item);
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void registerModel(ModelRegistryEvent event) {
        ItemUtil.setModel(item, 0);
    }

    @SubscribeEvent
    public static void onBlockHarvested(BlockEvent.HarvestDropsEvent event) {
        if (doesBlockMatchOre(event.getState().getBlock(), "treeLeaves") && itemRand.nextInt(101)+1 <= 2) {
            event.getDrops().add(new ItemStack(item, 1));
        }
    }
}