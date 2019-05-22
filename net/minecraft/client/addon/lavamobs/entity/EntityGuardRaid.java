package net.minecraft.client.addon.lavamobs.entity;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.addon.lavamobs.packets.PacketMAUpdateInt2;
import net.minecraft.client.addon.tchestplate.fx.FXLightningBolt;
import net.minecraft.client.addon.tchestplate.fx.manager.ManagerFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.src.ModLoader;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityGuardRaid extends LavaEntityMob
{
    public int smashCounter = 0;
    private boolean smash = false;
    private boolean flag = true;
    private float jumpValue = 0.25F;
    private int healtick = 100;
    public int effectheal = 0;
    private int ranheal;
    private boolean flagheal;
    private EntityRaidBoss entityRaidBoss;

    public EntityGuardRaid(World par1World)
    {
        super(par1World);
        this.ranheal = super.worldObj.rand.nextInt(200) + 200;
        this.flagheal = true;
        super.texture = "/mods/lavamobs/textures/rbguard.png";
        super.moveSpeed = 0.25F;
        this.setSize(0.9F, 0.9F);
        super.tasks.addTask(0, new EntityAISwimming(this));
        super.tasks.addTask(1, new EntityAIBreakDoor(this));
        super.tasks.addTask(2, new LavaEntityAIAttackOnCollide(this, EntityPlayer.class, super.moveSpeed, false));
        super.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        super.tasks.addTask(4, new EntityAIWander(this, super.moveSpeed));
        super.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        super.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 16.0F, 0, true));
    }

    protected void despawnEntity()
    {
        super.entityAge = 0;
    }

    public int getAttackStrength(Entity par1Entity)
    {
        int r = super.worldObj.rand.nextInt(100);

        if (r > 60 && r < 70)
        {
            ManagerFX.setEffectSlowe(par1Entity, 4000, (byte)0);
        }

        return 200;
    }

    protected boolean isAIEnabled()
    {
        return true;
    }

    public int getMaxHealth()
    {
        return 1000;
    }

    @SideOnly(Side.CLIENT)
    public int getBrightnessForRender(float par1)
    {
        return 15728880;
    }

    protected String getLivingSound()
    {
        return "lavamobs.attackguard";
    }

    public int getranheal()
    {
        return super.worldObj.rand.nextInt(800) + 800;
    }

    public void onLivingUpdate()
    {
        if (!super.worldObj.isRemote)
        {
            if (this.ranheal <= 0)
            {
                --this.healtick;

                if (this.healtick <= 0)
                {
                    this.ranheal = this.getranheal();
                }

                if (this.entityRaidBoss != null && this.entityRaidBoss.deathTicks == 0 && this.entityRaidBoss.getMaxHealth() >= this.entityRaidBoss.getHealth() + 50)
                {
                    int h = 1;

                    if (this.entityRaidBoss.plauercount > 10)
                    {
                        h = (int)((double)this.entityRaidBoss.plauercount * 0.2D);
                    }

                    this.entityRaidBoss.setEntityHealth(this.entityRaidBoss.getHealth() + h);

                    if (this.flagheal)
                    {
                        this.flagheal = false;
                        PacketDispatcher.sendPacketToAllAround(super.posX, super.posY, super.posZ, 20.0D, super.worldObj.provider.dimensionId, (new PacketMAUpdateInt2((byte)1, super.entityId, this.entityRaidBoss.entityId)).makePacket());
                    }
                }
            }
            else
            {
                --this.ranheal;
                this.flagheal = true;
                this.healtick = 100;
            }

            if (this.entityRaidBoss != null && this.entityRaidBoss.isDead)
            {
                this.setDead();
            }

            if (this.entityRaidBoss == null)
            {
                this.setDead();
            }

            if (super.health <= 0 && this.flag)
            {
                this.flag = false;

                if (this.entityRaidBoss != null)
                {
                    this.entityRaidBoss.timesguard.add(Long.valueOf(System.currentTimeMillis() + 120000L));
                }
            }

            if (super.isSwingInProgressq)
            {
                super.moveSpeed = 1.3F;

                if ((-1.0D <= super.motionX && super.motionX <= 1.0D || -1.0D <= super.motionZ && super.motionZ <= 1.0D) && super.onGround && !super.worldObj.isRemote)
                {
                    super.motionY += (double)this.jumpValue;
                    this.jumpValue -= 0.06F;

                    if (this.jumpValue < 0.5F)
                    {
                        this.jumpValue = 0.7F + super.rand.nextFloat() / 3.0F;
                    }
                }
            }
            else
            {
                super.moveSpeed = 0.25F;
            }
        }
        else if (this.effectheal > 0)
        {
            --this.effectheal;

            if (this.entityRaidBoss != null)
            {
                shootLightning(super.worldObj, this, this.entityRaidBoss.posX, this.entityRaidBoss.posY + 1.5D, this.entityRaidBoss.posZ, true);
            }
        }

        super.onLivingUpdate();
    }

    protected void playStepSound(int par1, int par2, int par3, int par4)
    {
        super.worldObj.playSoundAtEntity(this, "mob.zombie.step", 0.15F, 1.0F);
    }

    protected void dropRareDrop(int par1)
    {
        switch (super.rand.nextInt(2))
        {
            case 0:
                this.dropItem(4015, 1);
                break;
        }
    }

    protected int getDropItemId()
    {
        return Item.appleGold.itemID;
    }

    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEAD;
    }

    public void setraidboss(EntityRaidBoss entityRaidBoss)
    {
        this.entityRaidBoss = entityRaidBoss;
    }

    public static void shootLightning(World world, EntityLiving entityplayer, double xx, double yy, double zz, boolean offset)
    {
        double px = entityplayer.posX;
        double py = entityplayer.posY + 0.2D;
        double pz = entityplayer.posZ;

        if (entityplayer.entityId != ModLoader.getMinecraftInstance().thePlayer.entityId)
        {
            py = entityplayer.boundingBox.minY + (double)(entityplayer.height / 2.0F) + 0.25D;
        }

        px -= (double)(MathHelper.cos(entityplayer.rotationYaw / 180.0F * (float)Math.PI) * 0.16F);
        py -= 0.05000000014901161D;
        pz -= (double)(MathHelper.sin(entityplayer.rotationYaw / 180.0F * (float)Math.PI) * 0.16F);
        Vec3 vec3d = entityplayer.getLook(1.0F);
        FXLightningBolt bolt = new FXLightningBolt(world, px + vec3d.xCoord * 0.25D, py + vec3d.yCoord * 0.25D, pz + vec3d.zCoord * 0.25D, xx, yy, zz, world.rand.nextLong(), 6, 0.5F, 5);
        bolt.defaultFractal();
        bolt.setType(4);
        bolt.setWidth(0.125F);
        bolt.finalizeBolt();
    }
}
