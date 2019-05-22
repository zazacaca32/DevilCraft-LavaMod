package net.minecraft.client.addon.lavamobs.block;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.StepSound;
import net.minecraft.block.material.Material;
import net.minecraft.client.addon.lavamobs.tile.TileLavaPortal;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class BlockLavaPortal
  extends BlockContainer
{
  @SideOnly(Side.CLIENT)
  private Icon icon;
  long pause;
  
  public BlockLavaPortal(int par1)
  {
    super(par1, Material.rock);
    setResistance(42.0F);
    setHardness(1.25F);
    setStepSound(new StepSound("stone", 1.0F, 1.0F));
    setCreativeTab(CreativeTabs.tabBlock);
    setUnlocalizedName("LavaPortal");
    setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
  }
  
  @SideOnly(Side.CLIENT)
  public void registerIcons(IconRegister par1IconRegister)
  {
    this.blockIcon = par1IconRegister.registerIcon("anvil_base");
  }
  
  @SideOnly(Side.CLIENT)
  public int getRenderType()
  {
    return -1;
  }
  
  @SideOnly(Side.CLIENT)
  public int getRenderBlockPass()
  {
    return 1;
  }
  
  public boolean isOpaqueCube()
  {
    return false;
  }
  
  public boolean renderAsNormalBlock()
  {
    return false;
  }
  
  public void addCollisionBoxesToList(World par1World, int par2, int par3, int par4, AxisAlignedBB par5AxisAlignedBB, List par6List, Entity par7Entity)
  {
	  setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
    super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
    setBlockBoundsBasedOnState(par1World, par2, par3, par4);
  }
  
  public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
  {
    int metadata = world.getBlockMetadata(x, y, z);
    if ((metadata == 3) || (metadata == 1)) {
    	setBlockBounds(-1.4F, 0.0F, 0.0F, 2.4F, 4.6F, 1.0F);
    } else if ((metadata == 2) || (metadata == 0)) {
    	setBlockBounds(0.0F, 0.0F, -1.4F, 1.0F, 4.6F, 2.4F);
    }
  }
  
  public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
  {
    if (!par1World.isRemote)
    {
      if (this.pause < 40L)
      {
        this.pause += 1L;
        return;
      }
      this.pause = 0L;
      if (!(par5Entity instanceof EntityPlayer)) {
        return;
      }
      TileEntity LavaPortal = par1World.getBlockTileEntity(par2, par3, par4);
      if (!(LavaPortal instanceof TileLavaPortal)) {
        return;
      }
      if (!((TileLavaPortal)LavaPortal).flag) {
        return;
      }
      double x = ((TileLavaPortal)LavaPortal).location.x;
      double y = ((TileLavaPortal)LavaPortal).location.y;
      double z = ((TileLavaPortal)LavaPortal).location.z;
      float yaw = ((TileLavaPortal)LavaPortal).location.yaw;
      
      boolean prevWorldChunkLoadOverride = par1World.findingSpawnPoint;
      par1World.findingSpawnPoint = true;
      Chunk chunk = par1World.getChunkProvider().provideChunk((int)x >> 4, (int)z >> 4);
      par1World.findingSpawnPoint = prevWorldChunkLoadOverride;
      if (chunk == null) {
        return;
      }
      TileEntity ToLavaPortal = chunk.getChunkBlockTileEntity((int)x & 0xF, (int)y, (int)z & 0xF);
      if (!(ToLavaPortal instanceof TileLavaPortal)) {
        return;
      }
      ((EntityPlayerMP)par5Entity).rotationPitch = 0.0F;
      ((EntityPlayerMP)par5Entity).rotationYaw = (yaw - 90.0F);
      ((EntityPlayerMP)par5Entity).setPositionAndUpdate(x + 0.5D, y + 1.0D, z + 0.5D);
    }
  }
  
  public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving, ItemStack par6ItemStack)
  {
    int l = MathHelper.floor_double(par5EntityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 0x3;
    int i1 = par1World.getBlockMetadata(par2, par3, par4);
    if (l == 0) {
      par1World.setBlockMetadataWithNotify(par2, par3, par4, 3, 2);
    }
    if (l == 1) {
      par1World.setBlockMetadataWithNotify(par2, par3, par4, 0, 2);
    }
    if (l == 2) {
      par1World.setBlockMetadataWithNotify(par2, par3, par4, 1, 2);
    }
    if (l == 3) {
      par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 2);
    }
  }
  
  public TileEntity createNewTileEntity(World world)
  {
    return new TileLavaPortal();
  }
}
