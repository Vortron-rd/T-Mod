package net.vortron.tmod.item.tool;

import com.tmtravlr.potioncore.PotionCoreEffects;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.vortron.tmod.CreativeTab;
import net.vortron.tmod.util.ItemUtil;

import static net.vortron.tmod.material.Materials.waterEmeraldToolMaterial;

@Mod.EventBusSubscriber
public class ItemWaterEmeraldSword extends ItemSword {
    public ItemWaterEmeraldSword() {
        super(waterEmeraldToolMaterial);
        this.setCreativeTab(CreativeTab.TMod);
        ItemUtil.nameItemId(this, "water_emerald_sword");
    }
    public static final Item item = new ItemWaterEmeraldSword().setMaxDamage(250);

    @Override
    public boolean hitEntity(ItemStack itemStack, EntityLivingBase target, EntityLivingBase attacker) {
        if(target.isPotionActive(PotionCoreEffects.POTIONS.get("drown")))
            target.addPotionEffect(new PotionEffect(PotionCoreEffects.POTIONS.get("drown"),50+target.getActivePotionEffect(PotionCoreEffects.POTIONS.get("drown")).getDuration()) );
        else
            target.addPotionEffect(new PotionEffect(PotionCoreEffects.POTIONS.get("drown"),300));
        return super.hitEntity(itemStack, target, attacker);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(ItemWaterEmeraldSword.item);
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void registerModel(ModelRegistryEvent event) {
        ItemUtil.setModel(item, 0);
    }


}