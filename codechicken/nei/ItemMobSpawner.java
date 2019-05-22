package codechicken.nei;

import codechicken.nei.NEICPH;
import codechicken.nei.NEIClientUtils;
import codechicken.nei.api.API;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class ItemMobSpawner extends ItemBlock {

   private static Map entityHashMap;
   private static Map IDtoNameMap;
   public static int idPig;
   private static boolean loaded;
   public static int placedX;
   public static int placedY;
   public static int placedZ;


   public ItemMobSpawner(World world) {
      super(Block.mobSpawner.blockID - 256);
      Item.itemsList[super.itemID] = this;
      super.hasSubtypes = true;
      entityHashMap = new HashMap();
      IDtoNameMap = new HashMap();
      loadSpawners(world);
   }

   public Icon getIconFromDamage(int par1) {
      return Block.mobSpawner.getBlockTextureFromSide(0);
   }

   public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {
      if(super.onItemUse(itemstack, entityplayer, world, x, y, z, par7, par8, par9, par10) && world.isRemote) {
         TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner)world.getBlockTileEntity(placedX, placedY, placedZ);
         if(tileentitymobspawner != null) {
            this.setDefaultTag(itemstack);
            String mobtype = (String)IDtoNameMap.get(Integer.valueOf(itemstack.getItemDamage()));
            if(mobtype != null) {
               NEICPH.sendMobSpawnerID(placedX, placedY, placedZ, mobtype);
               tileentitymobspawner.func_98049_a().setMobID(mobtype);
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public void addInformation(ItemStack itemstack, EntityPlayer par2EntityPlayer, List list, boolean par4) {
      this.setDefaultTag(itemstack);
      int meta = itemstack.getItemDamage();
      if(meta == 0) {
         meta = idPig;
      }

      EntityLiving e = getEntity(meta);
      if(e != null) {
         list.add("§" + (e instanceof IMob?"4":"3") + (String)IDtoNameMap.get(Integer.valueOf(meta)));
      }
   }

   public static EntityLiving getEntity(int ID) {
      EntityLiving entityliving = (EntityLiving)entityHashMap.get(Integer.valueOf(ID));
      if(entityliving == null) {
         WorldClient world = NEIClientUtils.mc().theWorld;
         loadSpawners(world);

         try {
            Class t = (Class)EntityList.IDtoClassMapping.get(Integer.valueOf(ID));
            if(t != null && EntityLiving.class.isAssignableFrom(t)) {
               entityliving = (EntityLiving)t.getConstructor(new Class[]{World.class}).newInstance(new Object[]{world});
            }
         } catch (Throwable var4) {
            var4.printStackTrace();
         }

         entityHashMap.put(Integer.valueOf(ID), entityliving);
      }

      return entityliving;
   }

   private void setDefaultTag(ItemStack itemstack) {
      if(!IDtoNameMap.containsKey(Integer.valueOf(itemstack.getItemDamage()))) {
         itemstack.setItemDamage(idPig);
      }

   }

   public static void loadSpawners(World world) {
      if(!loaded) {
         loaded = true;
         ArrayList eIDs = new ArrayList();

         try {
            HashMap t = (HashMap)EntityList.classToStringMapping;
            HashMap classToIDMapping = (HashMap)EntityList.classToIDMapping;
            Iterator var5 = t.keySet().iterator();

            while(var5.hasNext()) {
               Class eclass = (Class)var5.next();
               if(EntityLiving.class.isAssignableFrom(eclass)) {
                  try {
                     EntityLiving entityliving = (EntityLiving)eclass.getConstructor(new Class[]{World.class}).newInstance(new Object[]{world});
                     entityliving.isChild();
                     int id = ((Integer)classToIDMapping.get(eclass)).intValue();
                     String name = (String)t.get(eclass);
                     if(!name.equals("EnderDragon")) {
                        IDtoNameMap.put(Integer.valueOf(id), name);
                        eIDs.add(Integer.valueOf(id));
                        if(name.equals("Pig")) {
                           idPig = id;
                        }
                     }
                  } catch (Throwable var9) {
                     ;
                  }
               }
            }
         } catch (Throwable var10) {
            var10.printStackTrace();
         }

         API.setItemDamageVariants(Block.mobSpawner.blockID, (Collection)eIDs);
      }
   }
}
