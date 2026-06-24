package net.vortron.tmod.mob;

import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Biomes;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.vortron.tmod.TMod;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class EntityCheesusCrust extends EntityMob {


    public static final ResourceLocation LOOT = new ResourceLocation(TMod.MOD_ID,"cheesus_crust");

    public EntityCheesusCrust(World worldIn) {
        super(worldIn);
        setSize(0.6F, 1.95F);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.33000000417232513D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(7.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(5.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100D);
        this.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE,Integer.MAX_VALUE,1,false,false));
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAICheesusCrustAttack(this, 1.0D, false));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.applyEntityAI();
    }

    private void applyEntityAI() {
        this.tasks.addTask(6, new EntityAIMoveThroughVillage(this, 1.0D, false));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[]{EntityIronGolem.class}));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityCreeper.class, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntitySkeleton.class, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityZombie.class, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityWitch.class, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntitySpider.class, true));
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        if (super.attackEntityAsMob(entityIn)) {
            if (entityIn instanceof EntityLivingBase) {
                //This won't behave like a regular bolt because it doesn't stay a WeatherEffect, so we add in the effects. Damage comes from cheesus itself.
                Entity lightning = new EntityLightningBolt(entityIn.world,entityIn.posX,entityIn.posY,entityIn.posZ,false);
                if(entityIn.world.addWeatherEffect(lightning)) entityIn.world.weatherEffects.remove(lightning);
                lightning.playSound(SoundEvents.ENTITY_LIGHTNING_IMPACT,1F,1F);
                lightning.playSound(SoundEvents.ENTITY_LIGHTNING_THUNDER,1F,1F);

            }
            return true;
        } else {
            return false;
        }
    }
    @Override
    @Nullable
    protected ResourceLocation getLootTable() {
        return LOOT;
    }
    @Override
    protected boolean isValidLightLevel() {
        return true;
    }
    @Override
    public int getMaxSpawnedInChunk() {
        return 5;
    }
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void initModels(ModelRegistryEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(EntityCheesusCrust.class, RenderCheesusCrust.FACTORY);
    }

    @SubscribeEvent
    public static void init(RegistryEvent.Register <EntityEntry> event) {
        int id = 1;
        EntityRegistry.registerModEntity(new ResourceLocation(TMod.MOD_ID+":cheesus_crust"),EntityCheesusCrust.class,TMod.MOD_ID+".cheesus_crust",id++, TMod.instance, 64, 3, true, 0xfefce7, 0xfcf15a);
        EntityRegistry.addSpawn(EntityCheesusCrust.class, 1, 1, 1, EnumCreatureType.CREATURE, Biomes.DESERT,Biomes.DESERT_HILLS,Biomes.MUTATED_DESERT,Biomes.MESA);
        LootTableList.register(EntityCheesusCrust.LOOT);
    }
    @SubscribeEvent
    public static void cancelLightning(EntityStruckByLightningEvent event) {
        if (event.getEntity() instanceof EntityCheesusCrust) event.setCanceled(true);
    }



    public static class EntityAICheesusCrustAttack extends EntityAIAttackMelee {
        private EntityCheesusCrust cheesuscrust;

        public EntityAICheesusCrustAttack(EntityCheesusCrust entityIn, double speedIn, boolean longMemoryIn) {
            super(entityIn, speedIn, longMemoryIn);
            this.cheesuscrust = entityIn;
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        @Override
        public void startExecuting() {
            super.startExecuting();
        }

        /**
         * Resets the task
         */
        @Override
        public void resetTask() {
            super.resetTask();
        }

        /**
         * Updates the task
         */
        @Override
        public void updateTask() {
            super.updateTask();
        }
    }
    public static class RenderCheesusCrust extends RenderLiving<EntityCheesusCrust> {

        private ResourceLocation mobTexture = new ResourceLocation("tmod:textures/entity/cheesuscrust.png");

        public static final Factory FACTORY = new Factory();

        public RenderCheesusCrust(RenderManager rendermanagerIn) {
            super(rendermanagerIn, new ModelPlayer(0.0F, false),0.5F);
        }

        @Override
        @Nonnull
        protected ResourceLocation getEntityTexture(@Nonnull EntityCheesusCrust entity) {
            return mobTexture;
        }

        public static class Factory implements IRenderFactory<EntityCheesusCrust> {

            @Override
            public Render<? super EntityCheesusCrust> createRenderFor(RenderManager manager) {
                return new RenderCheesusCrust(manager);
            }
        }
    }
}