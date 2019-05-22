package net.minecraft.client.addon.lavamobs;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.addon.lavamobs.tile.TileLavaPortal;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ItemPortalConnect
  extends Item
{
  public ItemPortalConnect(int par1)
  {
    super(par1);
    setCreativeTab(CreativeTabs.tabMaterials);
    setUnlocalizedName("PortalConnect");
    this.maxStackSize = 1;
  }
  
  @SideOnly(Side.CLIENT)
  public void registerIcons(IconRegister par1IconRegister)
  {
    this.itemIcon = par1IconRegister.registerIcon("lavamobs:" + getUnlocalizedName().substring(5));
  }
  
  public boolean onItemUse(ItemStack ItemStack, EntityPlayer EntityPlayer, World World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
  {
    if (World.isRemote) {
      return false;
    }
    TileEntity LavaPortal = World.getBlockTileEntity(par4, par5, par6);
    if (!(LavaPortal instanceof TileLavaPortal)) {
      return false;
    }
    if (((TileLavaPortal)LavaPortal).flag) {
      return false;
    }
    if (ItemStack.stackTagCompound == null) {
      ItemStack.stackTagCompound = new NBTTagCompound();
    }
    if (ItemStack.stackTagCompound.hasKey("location"))
    {
      NBTTagCompound location = ItemStack.stackTagCompound.getCompoundTag("location");
      if (((TileLavaPortal)LavaPortal).addLocation(location.getDouble("x"), location.getDouble("y"), location.getDouble("z"), location.getFloat("yaw"))) {
        EntityPlayer.sendChatToPlayer("§aТелепорт активирован.");
      } else {
        EntityPlayer.sendChatToPlayer("§aОшибка записи телепорта!");
      }
    }
    else
    {
      NBTTagCompound location = new NBTTagCompound();
      location.setDouble("x", par4);
      location.setDouble("y", par5);
      location.setDouble("z", par6);
      location.setFloat("yaw", LavaPortal.getBlockMetadata() * 90);
      ItemStack.stackTagCompound.setCompoundTag("location", location);
      EntityPlayer.sendChatToPlayer("§aТочка телепорта записана. x=" + par4 + " y=" + par5 + " z=" + par6);
    }
    return true;
  }
}
