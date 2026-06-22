package net.vortron.tmod.item;

import com.tmtravlr.potioncore.PotionCoreEffects;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.vortron.tmod.CreativeTab;
import net.vortron.tmod.util.ItemUtil;

import java.util.List;

@Mod.EventBusSubscriber
public class ItemWaterEmerald extends ItemFood {
    public ItemWaterEmerald() {
        super(0,0F,false);
        this.setCreativeTab(CreativeTab.TMod);
        ItemUtil.nameItemId(this, "water_emerald");
    }
    public static final Item item = new ItemWaterEmerald().setPotionEffect(new PotionEffect(PotionCoreEffects.POTIONS.get("drown"),3000),1F);

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(ItemWaterEmerald.item);
        OreDictionary.registerOre("gemWaterEmerald",item);
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
        tooltip.add("Ingestion not recommended.");
    }
}