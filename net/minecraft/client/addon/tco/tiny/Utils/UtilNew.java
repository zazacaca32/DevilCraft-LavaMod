package net.minecraft.client.addon.tco.tiny.Utils;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tco.tiny.Utils.LAItemStack;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagLong;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.world.World;

public final class UtilNew {

   public static int[] DarckNodesID;
   public static int[] BitNodesID;
   private static Random rdnSrc = new Random();


   public static NBTTagCompound openNbtData(ItemStack i) {
      NBTTagCompound compound = i.getTagCompound();
      if(compound == null) {
         i.setTagCompound(compound = new NBTTagCompound());
      }

      return compound;
   }

   public static ItemStack addLoretoItemStack(ItemStack stack, List list) {
      NBTTagCompound tag = stack.stackTagCompound;
      if(tag == null) {
         tag = new NBTTagCompound();
         tag.setCompoundTag("display", new NBTTagCompound());
         stack.stackTagCompound = tag;
      }

      tag = stack.stackTagCompound.getCompoundTag("display");
      NBTTagList lore = new NBTTagList();
      Iterator i$ = list.iterator();

      while(i$.hasNext()) {
         String text = (String)i$.next();
         lore.appendTag(new NBTTagString("", text));
      }

      tag.setTag("Lore", lore);
      stack.stackTagCompound.setCompoundTag("display", tag);
      return stack;
   }

   public static boolean isStackEqual(ItemStack stack1, ItemStack stack2) {
      return stack1 != null && stack2 != null && stack1.itemID == stack2.itemID && (!stack1.getHasSubtypes() && !stack1.isItemStackDamageable() || stack1.getItemDamage() == stack2.getItemDamage() && ItemStack.areItemStackTagsEqual(stack1, stack2));
   }

   public static Field getPrivateField(Class fromClass, String ... names) throws NoSuchFieldException {
      Field field = null;
      String[] nameStr = names;
      int i = names.length;
      int i$ = 0;

      String var9;
      while(i$ < i) {
         var9 = nameStr[i$];

         try {
            field = fromClass.getDeclaredField(var9);
            field.setAccessible(true);
            return field;
         } catch (NoSuchFieldException var8) {
            ++i$;
         }
      }

      if(names.length == 1) {
         throw new NoSuchFieldException("Could not find a field named " + names[0]);
      } else {
         var9 = "[" + names[0];

         for(i = 1; i < names.length; ++i) {
            var9 = var9 + ", " + names[i];
         }

         throw new NoSuchFieldException("Could not find a field with any of the following names: " + var9 + "]");
      }
   }

   public static Object getPrivateValue(Class objectClass, Object object, String ... names) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException, SecurityException {
      Field field = getPrivateField(objectClass, names);
      return field.get(object);
   }

   public static NBTTagCompound getOrCreateNbtData(ItemStack itemStack) {
      NBTTagCompound ret = itemStack.getTagCompound();
      if(ret == null) {
         ret = new NBTTagCompound("tag");
         itemStack.setTagCompound(ret);
      }

      return ret;
   }

   public static void addDarkEnergyNode(int ... id) {
      DarckNodesID = id;
   }

   public static void addBitNode(int ... id) {
      BitNodesID = id;
   }

   public static boolean findDarkEnergyNode(World world, int x, int y, int z) {
      int id = world.getBlockId(x, y, z);
      int[] arr$ = DarckNodesID;
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         int id_node = arr$[i$];
         if(id == id_node) {
            return true;
         }
      }

      return false;
   }

   public static boolean findBitNode(World world, int x, int y, int z) {
      int id = world.getBlockId(x, y, z);
      int[] arr$ = BitNodesID;
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         int id_node = arr$[i$];
         if(id == id_node) {
            return true;
         }
      }

      return false;
   }

   @SideOnly(Side.CLIENT)
   public static List getTooltip(Object o) {
      if(o == null) {
         return new ArrayList();
      } else {
         ItemStack itemStack = null;
         if(o instanceof LAItemStack) {
            LAItemStack var31 = (LAItemStack)o;
            return var31.getToolTip();
         } else if(o instanceof ItemStack) {
            itemStack = (ItemStack)o;

            try {
               return itemStack.getTooltip(Minecraft.getMinecraft().thePlayer, false);
            } catch (Exception var3) {
               return new ArrayList();
            }
         } else {
            return new ArrayList();
         }
      }
   }

   public static String getItemDisplayName(Object o) {
      if(o == null) {
         return "** Null";
      } else {
         ItemStack itemStack = null;
         String name;
         if(o instanceof LAItemStack) {
            name = ((LAItemStack)o).getDisplayName();
            return name == null?"** Null":name;
         } else if(o instanceof ItemStack) {
            itemStack = (ItemStack)o;

            try {
               name = itemStack.getDisplayName();
               if(name == null || name.equals("")) {
                  name = itemStack.getItem().getUnlocalizedName(itemStack);
               }

               return name == null?"** Null":name;
            } catch (Exception var6) {
               try {
                  String var51 = itemStack.getItemName();
                  return var51 == null?"** Null":var51;
               } catch (Exception var5) {
                  return "** Exception";
               }
            }
         } else {
            return "**Invalid Object";
         }
      }
   }

   public static boolean NBTEqualityTest(NBTBase A, NBTBase B) {
      byte id = A.getId();
      if(id != B.getId()) {
         return false;
      } else {
         switch(id) {
         case 1:
            return ((NBTTagByte)A).data == ((NBTTagByte)B).data;
         case 2:
         case 7:
         default:
            return A.equals(B);
         case 3:
            return ((NBTTagInt)A).data == ((NBTTagInt)B).data;
         case 4:
            return ((NBTTagLong)A).data == ((NBTTagLong)B).data;
         case 5:
            return ((NBTTagFloat)A).data == ((NBTTagFloat)B).data;
         case 6:
            return ((NBTTagDouble)A).data == ((NBTTagDouble)B).data;
         case 8:
            return ((NBTTagString)A).data == ((NBTTagString)B).data || ((NBTTagString)A).data.equals(((NBTTagString)B).data);
         case 9:
            NBTTagList lA = (NBTTagList)A;
            NBTTagList lB = (NBTTagList)B;
            if(lA.tagCount() != lB.tagCount()) {
               return false;
            } else {
               for(int var12 = 0; var12 < lA.tagCount(); ++var12) {
                  NBTBase var13 = lA.tagAt(var12);
                  NBTBase var14 = lB.tagAt(var12);
                  if(var14 == null) {
                     return false;
                  }

                  if(!NBTEqualityTest(var13, var14)) {
                     return false;
                  }
               }

               return true;
            }
         case 10:
            NBTTagCompound ctA = (NBTTagCompound)A;
            NBTTagCompound ctB = (NBTTagCompound)B;
            Collection cA = ctA.getTags();
            Collection cB = ctB.getTags();
            if(cA.size() != cB.size()) {
               return false;
            } else {
               Iterator i = cA.iterator();

               while(i.hasNext()) {
                  NBTBase tag = (NBTBase)i.next();
                  NBTBase aTag = ctB.getTag(tag.getName());
                  if(aTag == null) {
                     return false;
                  }

                  if(!NBTEqualityTest(tag, aTag)) {
                     return false;
                  }
               }

               return true;
            }
         }
      }
   }

   public static boolean[] findDarkEnergyNodeDirection(World world, int x, int y, int z) {
      boolean[] result = new boolean[]{false, false, false, false, false, false};
      int i = 0;

      for(int x_ = x - 1; x_ < x + 1; ++x_) {
         for(int y_ = y - 1; y_ < y + 1; ++y_) {
            for(int z_ = z - 1; z_ < z + 1; ++z_) {
               int id = world.getBlockId(x, y, z);
               int[] arr$ = DarckNodesID;
               int len$ = arr$.length;

               for(int i$ = 0; i$ < len$; ++i$) {
                  int id_node = arr$[i$];
                  if(id == id_node) {
                     result[i++] = true;
                  }
               }
            }
         }
      }

      return result;
   }

   public static boolean findDarkEnergyNodeFromID(int blockID) {
      int[] arr$ = DarckNodesID;
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         int id_node = arr$[i$];
         if(blockID == id_node) {
            return true;
         }
      }

      return false;
   }

   public static boolean[] findBitNodeDirection(World world, int x, int y, int z) {
      boolean[] result = new boolean[]{false, false, false, false, false, false};
      int i = 0;

      for(int x_ = x - 1; x_ < x + 1; ++x_) {
         for(int y_ = y - 1; y_ < y + 1; ++y_) {
            for(int z_ = z - 1; z_ < z + 1; ++z_) {
               int id = world.getBlockId(x, y, z);
               int[] arr$ = BitNodesID;
               int len$ = arr$.length;

               for(int i$ = 0; i$ < len$; ++i$) {
                  int id_node = arr$[i$];
                  if(id == id_node) {
                     result[i++] = true;
                  }
               }
            }
         }
      }

      return result;
   }

   public static boolean findBitNodeFromID(int blockID) {
      int[] arr$ = BitNodesID;
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         int id_node = arr$[i$];
         if(blockID == id_node) {
            return true;
         }
      }

      return false;
   }

   public static boolean isClient() {
      return FMLCommonHandler.instance().getEffectiveSide().isClient();
   }

   public static boolean isServer() {
      return !isClient();
   }

   public static boolean isSameItem(ItemStack a, ItemStack b) {
      return a == null && b == null?true:(a == null?false:(b == null?false:a.isItemEqual(b) && sameStackStags(a, b)));
   }

   public static boolean sameStackStags(ItemStack a, ItemStack b) {
      if(a == null && b == null) {
         return true;
      } else if(a != null && b != null) {
         if(a == b) {
            return true;
         } else {
            NBTTagCompound ta = a.getTagCompound();
            NBTTagCompound tb = b.getTagCompound();
            return ta == tb?true:((ta != null || tb != null) && (ta == null || !ta.hasNoTags() || tb != null) && (tb == null || !tb.hasNoTags() || ta != null) && (ta == null || !ta.hasNoTags() || tb == null || !tb.hasNoTags())?((ta != null || tb == null) && (ta == null || tb != null)?NBTEqualityTest(ta, tb):false):true);
         }
      } else {
         return false;
      }
   }

   public static int getRandomInt() {
      return Math.abs(rdnSrc.nextInt());
   }

   public static void spawnDrops(World w, int x, int y, int z, List drops) {
      if(isServer()) {
         Iterator i$ = drops.iterator();

         while(i$.hasNext()) {
            ItemStack i = (ItemStack)i$.next();
            if(i != null && i.stackSize > 0) {
               double offset_x = (double)((getRandomInt() % 32 - 16) / 82);
               double offset_y = (double)((getRandomInt() % 32 - 16) / 82);
               double offset_z = (double)((getRandomInt() % 32 - 16) / 82);
               EntityItem ei = new EntityItem(w, 0.5D + offset_x + (double)x, 0.5D + offset_y + (double)y, 0.2D + offset_z + (double)z, i.copy());
               w.spawnEntityInWorld(ei);
            }
         }
      }

   }

   public static ItemStack getSharedItemStack(LAItemStack filter) {
      return filter == null?null:filter.getSharedItemStack();
   }

   public static ItemStack getItemStackLowIdDamage(LAItemStack filter) {
      return filter == null?null:filter.getItemStackLowIdDamage();
   }

   public static ItemStack cloneItemStack(ItemStack sub) {
      if(sub.hasTagCompound()) {
         NBTTagCompound tmp = sub.getTagCompound();
         sub.setTagCompound((NBTTagCompound)null);
         ItemStack copy = sub.copy();
         sub.setTagCompound(tmp);
         copy.setTagCompound(tmp);
         return copy;
      } else {
         return sub.copy();
      }
   }

   public static void sumItemToList(List out, LAItemStack sub) {
      if(sub != null) {
         Iterator i$ = out.iterator();

         while(i$.hasNext()) {
            LAItemStack g = (LAItemStack)i$.next();
            if(g.equals(sub)) {
               long newSize = g.getStackSize() + sub.getStackSize();
               if(newSize < 2147483647L) {
                  g.setStackSize(newSize);
                  return;
               }

               g.setStackSize(2147483647L);
            }
         }

         out.add(sub.copy());
      }

   }

}
