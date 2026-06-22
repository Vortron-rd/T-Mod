package net.vortron.tmod.item.armor;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.vortron.tmod.CreativeTab;
import net.vortron.tmod.material.Materials;
import net.vortron.tmod.util.ItemUtil;

import java.util.List;

import static net.minecraft.util.math.MathHelper.ceil;

@Mod.EventBusSubscriber
public class ItemCactusChestplate extends ItemArmor {
    public ItemCactusChestplate() {
        super(Materials.cactusArmorMaterial,0, EntityEquipmentSlot.CHEST);
        this.setCreativeTab(CreativeTab.TMod);
        ItemUtil.nameItemId(this, "cactus_chestplate");
    }
    public static final Item item = new ItemCactusChestplate();


    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(ItemCactusChestplate.item);
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void registerModel(ModelRegistryEvent event) {
        ItemUtil.setModel(item, 0);
    }

    @SubscribeEvent
    public static void prickPlayers(LivingDamageEvent event) {
        if (event.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntity();
            if(player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).isItemEqualIgnoreDurability(item.getDefaultInstance())) {
                if (itemRand.nextInt(101) + 1 <= 5) {
                    event.setAmount((float) (ceil(event.getAmount() * 1.2)));
                    player.sendMessage(new TextComponentString("Prick!"));
                    player.getEntityWorld().playSound(null,player.posX,player.posY,player.posZ, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS,100F,100F);              }
            }
        }
    }
    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add("Has a small chance to prick you.");
    }
}