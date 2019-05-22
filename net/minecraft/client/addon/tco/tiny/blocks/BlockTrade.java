package net.minecraft.client.addon.tco.tiny.blocks;

import java.util.HashMap;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.addon.tco.tiny.Tiny;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.BaseTileEntityBlock;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockTrade;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockTrade extends BlockContainer {

   public static HashMap delay = new HashMap();


   public BlockTrade(int id) {
      super(id, Material.ground);
   }

   public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
      if(par1World.isRemote) {
         return true;
      } else {
         if(!par5EntityPlayer.isSneaking()) {
            TileEntityBlockTrade var10 = (TileEntityBlockTrade)par1World.getBlockTileEntity(par2, par3, par4);
            if(var10 != null) {
               long time = System.currentTimeMillis();
               Long untime = (Long)delay.get(Integer.valueOf(par2 + par3 + par4));
               if(untime != null && untime.longValue() > time) {
                  par5EntityPlayer.addChatMessage("[Обменник] §6Подождите секундочку.");
                  return false;
               }

               delay.put(Integer.valueOf(par2 + par3 + par4), Long.valueOf(time + 2000L));
               int u = var10.CheckChest(par5EntityPlayer);
               if(u > 0) {
                  par5EntityPlayer.openGui(Tiny.instance, 10014, par1World, par2, par3, par4);
                  return true;
               }

               par5EntityPlayer.addChatMessage("[Обменник] §6Тут уже проводится обмен");
            }
         }

         return false;
      }
   }

   public void onBlockPlacedBy(World world, int i, int j, int k, EntityLiving entityliving, ItemStack itemStack) {
      int facing = MathHelper.floor_double((double)(entityliving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
      TileEntity te = world.getBlockTileEntity(i, j, k);
      if(te != null && te instanceof BaseTileEntityBlock) {
         ((BaseTileEntityBlock)te).setFacing(facing);
         world.markBlockForUpdate(i, j, k);
      }

   }

   public TileEntity createNewTileEntity(World world) {
      return null;
   }

   public TileEntity createTileEntity(World world, int metadata) {
      return new TileEntityBlockTrade();
   }

   public int getRenderType() {
      return -1;
   }

   public boolean isOpaqueCube() {
      return false;
   }

   public void registerIcons(IconRegister par1IconRegister) {
      super.blockIcon = par1IconRegister.registerIcon("Tile:present_0");
   }

}
