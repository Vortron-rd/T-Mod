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
public class ItemDivineCheese extends ItemFood {
    public ItemDivineCheese() {
        super(15,15F,false);
        this.setCreativeTab(CreativeTab.TMod);
        ItemUtil.nameItemId(this, "divine_cheese");
    }
    public static final Item item = new ItemDivineCheese().setPotionEffect(new PotionEffect(PotionCoreEffects.POTIONS.get("drown"),3000),1F);

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(ItemDivineCheese.item);
        OreDictionary.registerOre("foodDivineCheese",item);
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
        tooltip.add("§a§oEven in the wake of his parting, he bestows this gift unto you.");
    }
}