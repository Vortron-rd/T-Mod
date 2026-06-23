package net.vortron.tmod.item.tool;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.vortron.tmod.CreativeTab;
import net.vortron.tmod.util.ItemUtil;

import java.util.*;

import static net.vortron.tmod.material.Materials.cactusToolMaterial;

@Mod.EventBusSubscriber
public class ItemCactusClub extends ItemTool {
    public ItemCactusClub() {
        super(cactusToolMaterial, Collections.emptySet());
        this.setHarvestLevel("pickaxe", cactusToolMaterial.getHarvestLevel());
        this.setHarvestLevel("axe", cactusToolMaterial.getHarvestLevel());
        this.attackDamage = 4F;
        this.efficiency = 2F;
        this.attackSpeed = -2.5F;
        this.setCreativeTab(CreativeTab.TMod);
        ItemUtil.nameItemId(this, "cactus_club");
    }
    public static final Item item = new ItemCactusClub();


    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(ItemCactusClub.item);
    }

    @Override
    public Set<String> getToolClasses(ItemStack stack) {
        return new HashSet<>(Arrays.asList("axe", "pickaxe"));
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void registerModel(ModelRegistryEvent event) {
        ItemUtil.setModel(item, 0);
    }
    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add("Works as a crude axe and pickaxe.");
    }


}