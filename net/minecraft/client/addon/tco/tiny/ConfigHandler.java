package net.minecraft.client.addon.tco.tiny;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.client.addon.tco.tiny.entity.ConfigMan;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

public class ConfigHandler {

   private static Configuration config;
   public static float configMatterCost = 1.7E7F;
   public static boolean configMatterUtil = true;


   public static void init(File configFile) {
      config = new Configuration(configFile);
   }

   public static void loadEntityMan() {
      try {
         config.load();
         Iterator var9 = config.getCategoryNames().iterator();

         while(var9.hasNext()) {
            String s = (String)var9.next();
            Iterator var10;
            Property var112;
            if(s.equalsIgnoreCase("BlockLava")) {
               var10 = config.getCategory(s).values().iterator();

               while(var10.hasNext()) {
                  var112 = (Property)var10.next();
                  if("MatterCcost".equals(var112.getName())) {
                     configMatterCost = (float)var112.getInt(17000000);
                  }

                  if("isMatterUtil".equals(var112.getName())) {
                     configMatterUtil = var112.getBoolean(true);
                  }
               }
            }

            int var6;
            int var7;
            if(s.equalsIgnoreCase("Gui9priz")) {
               var10 = config.getCategory(s).values().iterator();

               while(var10.hasNext()) {
                  var112 = (Property)var10.next();
                  if("items".equals(var112.getName())) {
                     String[] var121 = var112.getStringList();
                     var7 = var121.length;

                     for(var6 = 0; var6 < var7; ++var6) {
                        String var10000 = var121[var6];
                     }
                  }
               }
            }

            if(s.indexOf("-") == -1) {
               ConfigMan var101 = new ConfigMan();
               Iterator var11 = config.getCategory(s).values().iterator();

               while(var11.hasNext()) {
                  Property var12 = (Property)var11.next();
                  if(!"name".equals(var12.getName())) {
                     if(!"texture".equals(var12.getName())) {
                        if("x".equals(var12.getName())) {
                           var101.x = var12.getInt();
                        } else if("y".equals(var12.getName())) {
                           var101.y = var12.getInt();
                        } else if("z".equals(var12.getName())) {
                           var101.z = var12.getInt();
                        } else {
                           String j;
                           String[] var13;
                           if("info".equals(var12.getName())) {
                              var13 = var12.getStringList();
                              var6 = var13.length;

                              for(var7 = 0; var7 < var6; ++var7) {
                                 j = var13[var7];
                                 var101.add(j.replaceAll("\"0\"", "\n").replaceAll("\"1\"", "/").replaceAll("\"2\"", "?").replaceAll("\"3\"", "=").replaceAll("\"4\"", ","));
                              }
                           } else if("infk".equals(var12.getName())) {
                              var13 = var12.getStringList();
                              var6 = var13.length;

                              for(var7 = 0; var7 < var6; ++var7) {
                                 j = var13[var7];
                                 var101.addInfk(j);
                              }
                           }
                        }
                     } else {
                        var101.setTexture(var12.getString());
                     }
                  } else {
                     var101.setName(var12.getString());
                  }
               }

               ConfigMan.map.add(var101);
            }
         }

      } catch (Exception var91) {
         MinecraftServer.getServer().logInfo("Error Load Config devPlay.");
      }
   }

   public static ItemStack[] getItems() {
      Object stacks = null;
      ArrayList Stack = new ArrayList();

      try {
         config.load();
         Iterator var101 = config.getCategoryNames().iterator();

         while(var101.hasNext()) {
            String s = (String)var101.next();
            if(s.indexOf("-") != -1) {
               String[] g = s.split("-");
               ItemStack d = new ItemStack(Integer.valueOf(g[0]).intValue(), 1, Integer.valueOf(g[1]).intValue());
               NBTTagCompound tag = d.stackTagCompound;
               if(tag == null) {
                  tag = new NBTTagCompound();
                  tag.setCompoundTag("display", new NBTTagCompound());
                  d.stackTagCompound = tag;
               }

               tag = d.stackTagCompound.getCompoundTag("display");
               NBTTagList lore = new NBTTagList();
               Iterator var8 = config.getCategory(s).values().iterator();

               while(var8.hasNext()) {
                  Property f = (Property)var8.next();
                  if(f.getName().equals("count")) {
                     d.stackTagCompound.setInteger("shopcount", Integer.valueOf(f.getString()).intValue());
                  } else if(f.getName().equals("price")) {
                     d.stackTagCompound.setInteger("price", Integer.valueOf(f.getString()).intValue());
                  } else if(f.getName().equals("name")) {
                     if(!f.getString().isEmpty()) {
                        tag.setString("Name", f.getString());
                     }
                  } else {
                     lore.appendTag(new NBTTagString("", f.getString()));
                  }
               }

               tag.removeTag("Lore");
               tag.setTag("Lore", lore);
               d.stackTagCompound.setCompoundTag("display", tag);
               Stack.add(d);
            }
         }

         return (ItemStack[])((ItemStack[])((ItemStack[])((ItemStack[])((ItemStack[])((ItemStack[])((ItemStack[])((ItemStack[])((ItemStack[])((ItemStack[])Stack.toArray((ItemStack[])(new ItemStack[0])))))))))));
      } catch (Exception var10) {
         MinecraftServer.getServer().logInfo("Error Load Config DevPlay!..");
         return (ItemStack[])((ItemStack[])((ItemStack[])((ItemStack[])((ItemStack[])((ItemStack[])((ItemStack[])((ItemStack[])((ItemStack[])((ItemStack[])Stack.toArray((ItemStack[])(new ItemStack[0])))))))))));
      }
   }

}
