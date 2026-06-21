package net.vortron.tmod.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.vortron.tmod.CreativeTab;
import net.vortron.tmod.util.BlockUtil;
import net.vortron.tmod.util.ItemUtil;

@Mod.EventBusSubscriber
public class BlockCompressedIron extends Block {
    public BlockCompressedIron() {
        super(Material.ROCK);
        this.setCreativeTab(CreativeTab.TMod);
        this.setResistance(6F);
        this.setHardness(5F);
        BlockUtil.nameBlockId(this, "compressed_iron_block");
    }
    public static final Block block = new BlockCompressedIron();
    public static final ItemBlock itemBlock = new ItemBlock(block);

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(block);
    }
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        itemBlock.setRegistryName(block.getRegistryName());
        event.getRegistry().register(itemBlock);
        OreDictionary.registerOre("blockCompressedIron",itemBlock);
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void registerModel(ModelRegistryEvent event) {
        BlockUtil.setModel(block,0);
        ItemUtil.setModel(itemBlock,0);
    }


}