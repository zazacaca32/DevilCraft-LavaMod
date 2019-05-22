package net.minecraft.client.addon.tco.tiny.blocks;

import net.minecraft.client.addon.tco.tiny.Tiny;
import net.minecraft.client.addon.tco.tiny.Utils.Utils;
import net.minecraft.client.addon.tco.tiny.blocks.BlockBaseSide;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockLavaAnvil;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockLavaAnvil extends BlockBaseSide {

   public BlockLavaAnvil(int id) {
      super(id);
   }

   public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
      if(par1World.isRemote) {
         return true;
      } else if(!par5EntityPlayer.isSneaking()) {
         TileEntityBlockLavaAnvil var10 = (TileEntityBlockLavaAnvil)par1World.getBlockTileEntity(par2, par3, par4);
         if(var10 != null) {
            par5EntityPlayer.openGui(Tiny.instance, 10023, par1World, par2, par3, par4);
         }

         return true;
      } else {
         return false;
      }
   }

   public TileEntity createNewTileEntity(World world) {
      return null;
   }

   public TileEntity createTileEntity(World world, int metadata) {
      return new TileEntityBlockLavaAnvil();
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

   public boolean removeBlockByPlayer(World world, EntityPlayer player, int x, int y, int z) {
      TileEntity tile;
      if(!Utils.isClient() && (tile = world.getBlockTileEntity(x, y, z)) instanceof TileEntityBlockLavaAnvil) {
         ItemStack[] t = ((TileEntityBlockLavaAnvil)tile).ItemStacks;
         if(((TileEntityBlockLavaAnvil)tile).ItemStacks != null) {
            boolean flag = true;
            ItemStack[] var9 = t;
            int var10 = t.length;

            for(int var11 = 0; var11 < var10; ++var11) {
               ItemStack it = var9[var11];
               if(it != null) {
                  flag = false;
               }
            }

            if(!flag) {
               player.sendChatToPlayer("[Наковальня] Заберите все итемы чтобы разрушить");
               return false;
            }
         }
      }

      return super.removeBlockByPlayer(world, player, x, y, z);
   }
}
