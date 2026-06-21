package net.vortron.tmod.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.IRarity;
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
public class BlockTripleCompressedDiamond extends Block {
    public BlockTripleCompressedDiamond() {
        super(Material.ROCK);
        this.setCreativeTab(CreativeTab.TMod);
        this.setResistance(6F);
        this.setHardness(10F);
        BlockUtil.nameBlockId(this, "triple_compressed_diamond_block");
    }
    public static final Block block = new BlockTripleCompressedDiamond();
    private static class item extends ItemBlock {

        public item(Block block) {
            super(block);
        }

        @Override
        public IRarity getForgeRarity(ItemStack stack) {
            return new IRarity() {
                @Override
                public TextFormatting getColor() {
                    return TextFormatting.LIGHT_PURPLE;
                }

                @Override
                public String getName() {
                    return "EPIC";
                }
            };
        }

        @Override
        public EnumRarity getRarity(ItemStack stack) {
            return EnumRarity.EPIC;
        }

    }
    public static final ItemBlock itemBlock = new item(block);

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(block);
    }
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        itemBlock.setRegistryName(block.getRegistryName());
        event.getRegistry().register(itemBlock);
        OreDictionary.registerOre("blockTripleCompressedDiamond",itemBlock);

    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void registerModel(ModelRegistryEvent event) {
        BlockUtil.setModel(block,0);
        ItemUtil.setModel(itemBlock,0);
    }


}