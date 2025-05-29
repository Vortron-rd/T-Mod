package net.vortron.tmod.item;
import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.IBauble;
import baubles.api.cap.IBaublesItemHandler;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.vortron.tmod.CreativeTab;
import net.vortron.tmod.util.ItemUtil;
import net.vortron.tmod.TMod;

import java.util.List;

@Mod.EventBusSubscriber
public class ItemPizzaPendant extends Item implements IBauble {
    private ItemPizzaPendant()
    {
        super();
        this.setMaxStackSize(1);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setCreativeTab(CreativeTab.TMod);

        ItemUtil.nameItemId(this, "pizza_pendant");

    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        super.addInformation(stack, worldIn, tooltip, flagIn);

        tooltip.add("May sacrifice itself to help you when you are in need.");
    }
    public static final Item item = new ItemPizzaPendant();

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(net.vortron.tmod.item.ItemPizzaPendant.item);
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemstack) {
        return BaubleType.AMULET;
    }

    @SuppressWarnings("deprecation")
    @Override
    public EnumRarity getRarity(ItemStack par1ItemStack) {
        return EnumRarity.RARE;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        if(!world.isRemote) {
            IBaublesItemHandler baubles = BaublesApi.getBaublesHandler(player);
            for(int i = 0; i < baubles.getSlots(); i++) {
                baubles.getStackInSlot(i);
                if(baubles.getStackInSlot(i).isEmpty() && baubles.isItemValidForSlot(i, player.getHeldItem(hand), player)) {
                    baubles.setStackInSlot(i, player.getHeldItem(hand).copy());
                    if(!player.capabilities.isCreativeMode){
                        player.inventory.setInventorySlotContents(player.inventory.currentItem, ItemStack.EMPTY);
                    }
                    onEquipped(player.getHeldItem(hand), player);
                    break;
                }
            }
        }
        return new ActionResult<>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
    }

    @Override
    public void onEquipped(ItemStack itemstack, EntityLivingBase player) {
        player.playSound(SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, .75F, 1.9f);
    }


    @Override
    public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {
        player.playSound(SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, .75F, 2f);
    }

@SubscribeEvent
public static void InterceptPlayerDeath(LivingDeathEvent event) {

    if (event.getEntity() instanceof EntityPlayer player) {
        IBaublesItemHandler baubles = BaublesApi.getBaublesHandler(player);
            if(baubles.getStackInSlot(0).getItem() == item)
                if((int )(Math.random() * 100 + 1) <= 20) {
                    event.setCanceled(true);
                    if (event.isCanceled()) {
                        TMod.LOGGER.info("Cancelled Death Event for {}", player.getDisplayNameString());
                        player.setHealth(1F);
                        player.extinguish();
                        player.addPotionEffect(new PotionEffect(MobEffects.GLOWING, 100));
                        player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 300));
                        FoodStats food = player.getFoodStats();
                        food.setFoodLevel(5);
                        food.setFoodSaturationLevel(0);
                        if (!player.world.isRemote) {
                            TextComponentString msg = new TextComponentString("Your pizza pendant disintegrates as it saves your life...");
                            msg.setStyle(msg.getStyle().setColor(TextFormatting.fromColorIndex(7)).setItalic(true));
                            player.sendMessage(msg);
                        }
                        if (!player.capabilities.isCreativeMode) {
                            baubles.setStackInSlot(0, ItemStack.EMPTY);
                        }
                    }
                }

            }
        }


    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void registerModel(ModelRegistryEvent event) {
        ItemUtil.setModel(item, 0);
    }



}