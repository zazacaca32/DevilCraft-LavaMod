package net.minecraft.client.addon.tco.tiny.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.addon.tco.tiny.Tiny;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileGetExperience;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockGetExperience extends BlockContainer {

   @SideOnly(Side.CLIENT)
   private Icon icon;


   public BlockGetExperience(int id) {
      super(id, Material.iron);
      this.setCreativeTab(CreativeTabs.tabBlock);
      this.setUnlocalizedName("GetExperienceBlock");
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.2F, 1.0F);
      this.setHardness(2.5F);
   }

   public int getRenderType() {
      return -1;
   }

   public boolean isOpaqueCube() {
      return false;
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   @SideOnly(Side.CLIENT)
   public void registerIcons(IconRegister par1IconRegister) {
      super.blockIcon = Block.blockIron.getIcon(1, 0);
   }

   public boolean removeBlockByPlayer(World world, EntityPlayer player, int x, int y, int z) {
      if(!world.isRemote) {
         TileEntity tile = world.getBlockTileEntity(x, y, z);
         if(tile instanceof TileGetExperience) {
            boolean flag = true;
            if(((TileGetExperience)tile).inventoryContents != null) {
               flag = false;
            }

            if(!flag) {
               player.sendChatToPlayer("Заберите все итемы чтобы разрушить");
               return false;
            }
         }
      }

      return super.removeBlockByPlayer(world, player, x, y, z);
   }

   public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving player, ItemStack stack) {
      TileGetExperience tile = (TileGetExperience)world.getBlockTileEntity(x, y, z);
      if(tile != null) {
         tile.Owner = player.getEntityName();
      }

   }

   public TileEntity createNewTileEntity(World world) {
      return new TileGetExperience();
   }

   public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity) {
      if(!par1World.isRemote) {
         TileGetExperience tile = (TileGetExperience)par1World.getBlockTileEntity(par2, par3, par4);
         if(tile != null && !tile.powerEx && par5Entity instanceof EntityPlayer && tile.player == null && par5Entity.getEntityName().equals(tile.Owner)) {
            tile.player = (EntityPlayer)par5Entity;
            tile.powerEx = true;
            tile.triger = true;
         }
      }

   }

   public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
      if(world.isRemote) {
         return true;
      } else if(!player.isSneaking()) {
         TileGetExperience tile = (TileGetExperience)world.getBlockTileEntity(x, y, z);
         if(tile != null && player.getEntityName().equals(tile.Owner)) {
            player.openGui(Tiny.instance, 10099, world, x, y, z);
         }

         return true;
      } else {
         return false;
      }
   }
}
