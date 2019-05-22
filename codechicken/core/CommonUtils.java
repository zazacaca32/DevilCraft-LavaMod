package codechicken.core;

import codechicken.core.ReflectionManager;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import java.io.File;
import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.storage.AnvilChunkLoader;
import net.minecraft.world.chunk.storage.IChunkLoader;
import net.minecraft.world.storage.ISaveHandler;

public class CommonUtils {

   private static File minecraftDir;
   private static byte[] charWidth = new byte[]{(byte)4, (byte)2, (byte)5, (byte)6, (byte)6, (byte)6, (byte)6, (byte)3, (byte)5, (byte)5, (byte)5, (byte)6, (byte)2, (byte)6, (byte)2, (byte)6, (byte)6, (byte)6, (byte)6, (byte)6, (byte)6, (byte)6, (byte)6, (byte)6, (byte)6, (byte)6, (byte)2, (byte)2, (byte)5, (byte)6, (byte)5, (byte)6, (byte)7, (byte)6, (byte)6, (byte)6, (byte)6, (byte)6, (byte)6, (byte)6, (byte)6, (byte)4, (byte)6, (byte)6, (byte)6, (byte)6, (byte)6, (byte)6, (byte)6, (byte)6, (byte)6, (byte)6, (byte)6, (byte)6, (byte)6, (byte)6, (byte)6, (byte)6, (byte)6, (byte)4, (byte)6, (byte)4, (byte)6, (byte)6, (byte)3, (byte)6, (byte)6, (byte)6, (byte)6, (byte)6, (byte)5, (byte)6, (byte)6, (byte)2, (byte)6, (byte)5, (byte)3, (byte)6, (byte)6, (byte)6, (byte)6, (byte)6, (byte)6, (byte)6, (byte)4, (byte)6, (byte)6, (byte)6, (byte)6, (byte)6, (byte)6, (byte)5, (byte)2, (byte)5, (byte)7, (byte)6, (byte)6, (byte)6, (byte)6, (byte)6, (byte)6, (byte)6, (byte)6, (byte)6, (byte)6, (byte)6, (byte)6, (byte)4, (byte)6, (byte)3, (byte)6, (byte)6, (byte)6, (byte)6, (byte)6, (byte)6, (byte)6, (byte)6, (byte)6, (byte)6, (byte)6, (byte)6, (byte)6, (byte)6, (byte)6, (byte)6, (byte)4, (byte)6, (byte)6, (byte)3, (byte)6, (byte)6, (byte)6, (byte)6, (byte)6, (byte)6, (byte)6, (byte)7, (byte)6, (byte)6, (byte)6, (byte)2, (byte)6, (byte)6};


   public static boolean isClient() {
      return FMLCommonHandler.instance().getSide().isClient();
   }

   public static File getWorldBaseSaveLocation(World world) {
      File savedir = getWorldSaveLocation(world);
      return savedir == null?null:(savedir.getName().contains("DIM")?savedir.getParentFile():savedir);
   }

   public static File getWorldSaveLocation(World world, int dimension) {
      File basesave = getWorldBaseSaveLocation(world);
      return dimension != 0?new File(basesave, world.provider.getSaveFolder()):basesave;
   }

   private static File getWorldSaveLocation(World world) {
      try {
         ISaveHandler e = world.getSaveHandler();
         IChunkLoader loader = e.getChunkLoader(world.provider);
         return loader instanceof AnvilChunkLoader?((AnvilChunkLoader)loader).chunkSaveLocation:null;
      } catch (IllegalAccessError var3) {
         return ((WorldServer)world).getChunkSaveLocation();
      } catch (Exception var4) {
         throw new RuntimeException(var4);
      }
   }

   public static String getWorldName(World world) {
      return world.getWorldInfo().getWorldName();
   }

   public static int getDimension(World world) {
      return world.provider.dimensionId;
   }

   public static File getModsFolder() {
      return new File(getMinecraftDir(), "mods");
   }

   public static File getMinecraftDir() {
      if(minecraftDir == null) {
         minecraftDir = (File)ReflectionManager.getField(Loader.class, File.class, Loader.instance(), "minecraftDir");
      }

      return minecraftDir;
   }

   public static String getRelativePath(File parent, File child) {
      return !parent.isFile() && child.getPath().startsWith(parent.getPath())?child.getPath().substring(parent.getPath().length() + 1):null;
   }

   public static int getFreeBlockID(int preferred) {
      int i;
      for(i = preferred; i < 255; ++i) {
         if(Block.blocksList[i] == null) {
            return i;
         }
      }

      for(i = preferred - 1; i > 0; --i) {
         if(Block.blocksList[i] == null) {
            return i;
         }
      }

      return -1;
   }

   public static Object[] subArray(Object[] args, int i) {
      if(i > args.length) {
         return (Object[])Array.newInstance(args.getClass().getComponentType(), 0);
      } else {
         Object[] narray = (Object[])Array.newInstance(args.getClass().getComponentType(), args.length - i);
         System.arraycopy(args, i, narray, 0, narray.length);
         return narray;
      }
   }

   public static int getCharWidth(char c) {
      if(c == 167) {
         return -1;
      } else {
         int charIndex = ChatAllowedCharacters.allowedCharacters.indexOf(c);
         return charIndex + 32 <= charWidth.length && charIndex >= 0?charWidth[charIndex + 32]:0;
      }
   }

   public static int getStringWidth(String s) {
      if(s == null) {
         return 0;
      } else {
         int width = 0;
         boolean var3 = false;

         for(int charIndex = 0; charIndex < s.length(); ++charIndex) {
            char c = s.charAt(charIndex);
            int charWidth = getCharWidth(c);
            if(charWidth < 0 && charIndex < s.length() - 1) {
               ++charIndex;
               c = s.charAt(charIndex);
               if(c != 108 && c != 76) {
                  if(c == 114 || c == 82) {
                     var3 = false;
                  }
               } else {
                  var3 = true;
               }

               charWidth = getCharWidth(c);
            }

            width += charWidth;
            if(var3) {
               ++width;
            }
         }

         return width;
      }
   }

   public static List formatMessage(String message) {
      LinkedList splitNotice = new LinkedList();
      String[] splits = message.split(" ");
      String partial = "";
      int colour = 7;

      for(int i = 0; i < splits.length; ++i) {
         String next = partial.length() == 0?splits[i]:partial + " " + splits[i];
         if(getStringWidth(next) > 377) {
            splitNotice.add(colourPrefix(colour) + partial);

            for(int charPos = 0; charPos < partial.length(); ++charPos) {
               for(; partial.length() > charPos + 1 && partial.charAt(charPos) == 167; ++charPos) {
                  char c = partial.toLowerCase().charAt(charPos + 1);
                  if(c != 107) {
                     colour = "0123456789abcdef".indexOf(c);
                     if(colour < 0 || colour > 15) {
                        colour = 15;
                     }
                  }
               }
            }

            partial = splits[i];
         } else {
            partial = next;
         }
      }

      splitNotice.add(colourPrefix(colour) + partial);
      return splitNotice;
   }

   public static String colourPrefix(int colour) {
      return colour == -1?"":"§" + "0123456789abcdef".charAt(colour);
   }

   public static boolean isBlock(int ID) {
      return ID < Block.blocksList.length && Block.blocksList[ID] != null && Block.blocksList[ID].blockID != 0;
   }

   public static ModContainer findModContainer(String modID) {
      Iterator var2 = Loader.instance().getModList().iterator();

      while(var2.hasNext()) {
         ModContainer mc = (ModContainer)var2.next();
         if(modID.equals(mc.getModId())) {
            return mc;
         }
      }

      return null;
   }

   public static ItemStack consumeItem(ItemStack stack) {
      if(stack.getItem().hasContainerItem()) {
         return stack.getItem().getContainerItemStack(stack);
      } else if(stack.stackSize == 1) {
         return null;
      } else {
         --stack.stackSize;
         return stack;
      }
   }

   public static String filterText(String s) {
      return ChatAllowedCharacters.filerAllowedCharacters(s.replaceAll("§.", ""));
   }
}
