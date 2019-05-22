package net.minecraft.client.addon.tchestplate.weapon.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.addon.tchestplate.Utils;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class Pickaxe3x3 extends BaseHammerWeapon
{
    private static Material[] materials;

    static
    {
        materials = new Material[] {Material.rock, Material.grass, Material.ground, Material.sand, Material.clay};
    }

    public Pickaxe3x3(int par1, EnumToolMaterial par2EnumToolMaterial)
    {
        super(par1, par2EnumToolMaterial);
        super.maxCharge = Integer.valueOf(5000);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        super.registerIcons(par1IconRegister);
        super.itemIcon = par1IconRegister.registerIcon("LavaChestPlate:LavaPickAxe3x3");
    }

    public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int side, float a, float b, float c)
    {
        return false;
    }

    ItemStack CreateItem(ItemStack itemstack)
    {
        return itemstack;
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
        boolean var10000 = world.isRemote;
        return itemstack;
    }

    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
    {
        boolean var10000 = player.worldObj.isRemote;
        return true;
    }

    public static MovingObjectPosition locateClickSide(World world, Entity player, boolean par3, double range)
    {
        float f = 1.0F;
        float f1 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f;
        float f2 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * f;
        double d0 = player.prevPosX + (player.posX - player.prevPosX) * (double)f;
        double d1 = player.prevPosY + (player.posY - player.prevPosY) * (double)f + 1.62D - (double)player.yOffset;
        double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * (double)f;
        Vec3 vec3 = world.getWorldVec3Pool().getVecFromPool(d0, d1, d2);
        float f3 = MathHelper.cos(-f2 * 0.01745329F - (float)Math.PI);
        float f4 = MathHelper.sin(-f2 * 0.01745329F - (float)Math.PI);
        float f5 = -MathHelper.cos(-f1 * 0.01745329F);
        float f6 = MathHelper.sin(-f1 * 0.01745329F);
        float f7 = f4 * f5;
        float f8 = f3 * f5;
        double d3 = range;

        if (player instanceof EntityPlayerMP)
        {
            d3 = ((EntityPlayerMP)player).theItemInWorldManager.getBlockReachDistance();
        }

        Vec3 vec31 = vec3.addVector((double)f7 * d3, (double)f6 * d3, (double)f8 * d3);
        return world.rayTraceBlocks_do_do(vec3, vec31, par3, !par3);
    }

    public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block)
    {
        return this.getCharge(par1ItemStack).intValue() > 0 ? 20000.0F : 1.0F;
    }

    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack var1)
    {
        return EnumRarity.epic;
    }

    public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World, int blockID, int xPos, int yPos, int zPos, EntityLiving par7EntityLiving)
    {
        MovingObjectPosition clickPos = locateClickSide(par2World, (EntityPlayer)par7EntityLiving, true, 5.0D);

        if (clickPos == null)
        {
            return false;
        }
        else
        {
            byte xRange = 1;
            byte yRange = 1;
            byte zRange = 1;

            switch (clickPos.sideHit)
            {
                case 0:
                case 1:
                    yRange = 0;
                    break;

                case 2:
                case 3:
                    zRange = 0;
                    break;

                case 4:
                case 5:
                    xRange = 0;
            }

            Boolean lowEnergy = Boolean.valueOf(false);
            NBTTagCompound nbtData = Utils.getOrCreateNbtData(par1ItemStack);
            int charge = nbtData.getInteger("charge");

            if (charge - 9 >= 0)
            {
                for (int newXPos = xPos - xRange; newXPos <= xPos + xRange; ++newXPos)
                {
                    for (int newYPos = yPos - yRange; newYPos <= yPos + yRange; ++newYPos)
                    {
                        for (int newZPos = zPos - zRange; newZPos <= zPos + zRange; ++newZPos)
                        {
                            int meta = par2World.getBlockMetadata(newXPos, newYPos, newZPos);
                            int newblockID = par2World.getBlockId(newXPos, newYPos, newZPos);
                            Block block = Block.blocksList[newblockID];

                            if (block != null)
                            {
                                boolean canBreak = false;

                                for (int iter = 0; iter < materials.length; ++iter)
                                {
                                    if (materials[iter] == block.blockMaterial)
                                    {
                                        canBreak = true;
                                        break;
                                    }
                                }

                                if (block == Block.bedrock || block == Block.mobSpawner || !block.canHarvestBlock((EntityPlayer)par7EntityLiving, meta) || block.idDropped(meta, par2World.rand, 1) == 0)
                                {
                                    canBreak = false;
                                }

                                if (!((EntityPlayer)par7EntityLiving).capabilities.isCreativeMode && canBreak)
                                {
                                    this.discharge(par1ItemStack, 1);
                                    block.harvestBlock(par2World, (EntityPlayer)par7EntityLiving, newXPos, newYPos, newZPos, meta);
                                    block.onBlockHarvested(par2World, newXPos, newYPos, newZPos, meta, (EntityPlayer)par7EntityLiving);
                                    par2World.setBlockToAir(newXPos, newYPos, newZPos);
                                }
                            }
                        }
                    }
                }
            }

            return false;
        }
    }

    public int getDamageVsEntity(Entity par1Entity)
    {
        return 1;
    }

    public boolean canHarvestBlock(Block block)
    {
        return block != Block.bedrock;
    }

    public float getStrVsBlock(ItemStack stack, Block block, int meta)
    {
        return this.getStrVsBlock(stack, block);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
    {
        par2List.add("§a3 x 3");
        par2List.add("§a" + this.getCharge(par1ItemStack) + "/" + super.maxCharge + " L");
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack par1ItemStack)
    {
        return false;
    }
}
