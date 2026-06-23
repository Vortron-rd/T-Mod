package net.vortron.tmod.item;

import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.IBauble;
import baubles.api.cap.IBaublesItemHandler;
import com.tmtravlr.potioncore.PotionCoreEffects;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.vortron.tmod.CreativeTab;
import net.vortron.tmod.util.ItemUtil;

import java.util.List;

@Mod.EventBusSubscriber
public class ItemCompressedIronCharm extends Item implements IBauble {
    private ItemCompressedIronCharm()
    {
        super();
        this.setMaxStackSize(1);
        this.setCreativeTab(CreativeTab.TMod);

        ItemUtil.nameItemId(this, "compressed_iron_charm");

    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add("Gives Ironskin.");
    }
    public static final Item item = new ItemCompressedIronCharm();

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(ItemCompressedIronCharm.item);
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemstack) {
        return BaubleType.CHARM;
    }

    @SuppressWarnings("deprecation")
    @Override
    public EnumRarity getRarity(ItemStack par1ItemStack) {
        return EnumRarity.UNCOMMON;
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
        player.playSound(SoundEvents.ITEM_ARMOR_EQUIP_IRON, .75F, 1.9f);
        updatePotionStatus(player, getPotion());

    }
    public static Potion getPotion() {
        return PotionCoreEffects.POTIONS.get("iron_skin");
    }

    @Override
    public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {
        player.playSound(SoundEvents.ITEM_ARMOR_EQUIP_IRON, .75F, 2f);
        updatePotionStatus(player, getPotion());

    }
    public boolean playerIsWearingBauble(IInventory inv) {
        for (int i : BaubleType.CHARM.getValidSlots()) {
            if (inv.getStackInSlot(i).isItemEqualIgnoreDurability(item.getDefaultInstance()))
                return true;
        }
        return false;
    }
    public void updatePotionStatus(EntityLivingBase player, Potion potion) {
        if(potion == null || !(player instanceof EntityPlayer))
            return;
        if(playerIsWearingBauble(BaublesApi.getBaubles((EntityPlayer) player)))
            player.addPotionEffect(new PotionEffect(potion, Integer.MAX_VALUE, 0, true, false));
        else
            player.removeActivePotionEffect(potion);
    }



    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void registerModel(ModelRegistryEvent event) {
        ItemUtil.setModel(item, 0);
    }



}