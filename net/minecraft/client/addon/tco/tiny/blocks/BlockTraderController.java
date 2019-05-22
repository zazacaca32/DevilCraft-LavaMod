package net.minecraft.client.addon.tco.tiny.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.addon.tco.tiny.Tiny;
import net.minecraft.client.addon.tco.tiny.Utils.LAItemStack;
import net.minecraft.client.addon.tco.tiny.Utils.Utils;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.BaseTileEntityBlock;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockTraderController;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockTraderController extends BlockContainer {

   public Icon[] icon = new Icon[3];


   public BlockTraderController(int id) {
      super(id, Material.ground);
   }

   public void onBlockPlacedBy(World world, int i, int j, int k, EntityLiving entityliving, ItemStack itemStack) {
      int facing = MathHelper.floor_double((double)(entityliving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
      TileEntity te = world.getBlockTileEntity(i, j, k);
      if(te != null && te instanceof BaseTileEntityBlock) {
         ((BaseTileEntityBlock)te).placedBy(entityliving);
         world.markBlockForUpdate(i, j, k);
      }

   }

   public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
      if(par1World.isRemote) {
         return true;
      } else if(!par5EntityPlayer.isSneaking()) {
         TileEntityBlockTraderController var10 = (TileEntityBlockTraderController)par1World.getBlockTileEntity(par2, par3, par4);
         if(var10 != null) {
            par5EntityPlayer.openGui(Tiny.instance, 10022, par1World, par2, par3, par4);
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
      return new TileEntityBlockTraderController();
   }

   @SideOnly(Side.CLIENT)
   public int getRenderType() {
      return Tiny.proxy.getRenderId("blocksidecontroller");
   }

   public void registerIcons(IconRegister par1IconRegister) {
      super.blockIcon = par1IconRegister.registerIcon("Tile:blockTraderController");
      this.icon[0] = par1IconRegister.registerIcon("Tile:blockTraderController0");
      this.icon[1] = par1IconRegister.registerIcon("Tile:blockTraderController1");
      this.icon[2] = par1IconRegister.registerIcon("Tile:blockTraderController2");
   }

   public Icon getBlockTexture(IBlockAccess ba, int x, int y, int z, int side) {
      return side > 1?this.icon[2]:this.icon[side];
   }

   public boolean removeBlockByPlayer(World world, EntityPlayer player, int x, int y, int z) {
      if(!Utils.isClient()) {
         TileEntity tile = world.getBlockTileEntity(x, y, z);
         if(tile instanceof TileEntityBlockTraderController) {
            LAItemStack[] t = ((TileEntityBlockTraderController)tile).cellinv.copyAvaleableLA();
            if(t != null) {
               boolean flag = true;
               LAItemStack[] arr$ = t;
               int len$ = t.length;

               for(int i$ = 0; i$ < len$; ++i$) {
                  LAItemStack it = arr$[i$];
                  if(it != null) {
                     flag = false;
                  }
               }

               if(!flag) {
                  player.sendChatToPlayer("[Controller] Заберите все итемы чтобы разрушить");
                  return false;
               }
            }
         }
      }

      return super.removeBlockByPlayer(world, player, x, y, z);
   }

   public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6) {
      super.breakBlock(par1World, par2, par3, par4, par5, par6);
   }
}
