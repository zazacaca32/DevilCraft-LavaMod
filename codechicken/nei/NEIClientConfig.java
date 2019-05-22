package codechicken.nei;

import codechicken.core.ClassDiscoverer;
import codechicken.core.ClientUtils;
import codechicken.core.CommonUtils;
import codechicken.core.IStringMatcher;
import codechicken.core.config.ConfigFile;
import codechicken.core.config.ConfigTag;
import codechicken.core.inventory.ItemKey;
import codechicken.nei.AllowedPropertyMap;
import codechicken.nei.ClientHandler;
import codechicken.nei.GuiNEIBlockIDs;
import codechicken.nei.GuiNEISettings;
import codechicken.nei.InterActionMap;
import codechicken.nei.ItemVisibilityHash;
import codechicken.nei.LayoutManager;
import codechicken.nei.NEICPH;
import codechicken.nei.NEIClientUtils;
import codechicken.nei.NEICompatibility;
import codechicken.nei.NEIController;
import codechicken.nei.api.API;
import codechicken.nei.api.GuiInfo;
import codechicken.nei.api.IConfigureNEI;
import codechicken.nei.api.INEIGuiHandler;
import codechicken.nei.api.ItemInfo;
import codechicken.nei.api.TaggedInventoryArea;
import codechicken.nei.asm.NEIModContainer;
import codechicken.nei.recipe.RecipeInfo;
import codechicken.nei.recipe.weakDependancy_Forge;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.EnumGameType;
import net.minecraft.world.World;

public class NEIClientConfig {

   public static ItemVisibilityHash vishash;
   private static boolean[] statesSaved = new boolean[7];
   private static boolean configLoaded;
   private static boolean internalEnabled = false;
   private static boolean SMPMagnetMode;
   private static boolean SMPCreativeInv;
   private static boolean hasSMPCounterpart;
   private static HashSet permissableActions = new HashSet();
   private static HashSet bannedBlocks = new HashSet();
   private static HashSet disabledProperties = new HashSet();
   public static NBTTagCompound saveCompound = new NBTTagCompound();
   public static File saveFile = new File(Minecraft.getMinecraftDir(), "saves/NEI.dat");
   public static ConfigFile globalConfig = new ConfigFile(new File(Minecraft.getMinecraftDir(), "config/NEI.cfg"));
   public static NBTTagCompound localCompound = new NBTTagCompound();
   public static File localSave;
   public static ConfigFile worldConfig;
   public static ItemStack[] creativeInv;
   public static boolean global = false;


   private static void setDefaults() {
      globalConfig.setComment("Main configuration of NEI.\nMost of these options can be changed ingame.\nDeleting any element will restore it to it\'s default value");
      globalConfig.getTag("KeyBinding").useBraces();
      globalConfig.getTag("options").useBraces();
      globalConfig.getTag("command").useBraces().setComment("Change these options if you have a different mod installed on the server that handles the commands differently, Eg. Bukkit Essentials");
      globalConfig.setNewLineMode(1);
      globalConfig.getTag("options.enable").setPosition(0).getBooleanValue(true);
      API.addSetting(new GuiNEISettings.NEIOption("options.enable") {
         public String updateText() {
            return "NEI " + (this.enabled()?"Enabled":"Disabled");
         }
      });
      globalConfig.getTag("options.hidden").setPosition(2).getBooleanValue(false);
      globalConfig.getTag("options.cheatmode").setPosition(3).setNewLine(true).getIntValue(2);
      API.addSetting(new GuiNEISettings.NEIOption("options.cheatmode") {
         public String updateText() {
            return (new String[]{"Recipe", "Utility", "Cheat"})[this.intValue()] + " Mode";
         }
         public void onClick() {
            NEIClientConfig.cycleCheatMode();
         }
      });
      globalConfig.getTag("options.lockmode").setPosition(4).setComment("For those who can\'t help themselves:Set this to a mode and you will be unable to change it ingame").getIntValue(-1);
      globalConfig.getTag("options.utility actions").setPosition(5).setDefaultValue("delete, magnet");
      StringBuilder actionlist = new StringBuilder();
      InterActionMap[] actions = InterActionMap.values();

      for(int i = 0; i < actions.length; ++i) {
         if(i != 0) {
            actionlist.append(", ");
         }

         actionlist.append(actions[i].getName());
      }

      globalConfig.getTag("options.utility actions").setComment("list the actions that are considered \'utilities\' rather than cheats:Choose from " + actionlist.toString());
      globalConfig.getTag("options.layout style").setPosition(6).setNewLine(true).getIntValue(0);
      API.addSetting(new GuiNEISettings.NEIOption("options.layout style") {
         public String updateText() {
            return LayoutManager.getLayoutStyle().getName();
         }
         public void onClick() {
            NEIClientConfig.cycleLayoutStyle();
         }
      });
      globalConfig.getTag("options.edge-align buttons").setPosition(7).getBooleanValue(false);
      globalConfig.getTag("options.show ids").setPosition(8).setNewLine(true).getIntValue(1);
      API.addSetting(new GuiNEISettings.NEIOption("options.show ids") {
         public String updateText() {
            return "ItemID\'s: " + (new String[]{"Hidden", "Auto", "Shown"})[this.intValue()];
         }
         public void onClick() {
            NEIClientConfig.cycleIDVisibility();
         }
      });
      globalConfig.getTag("options.searchinventories").setPosition(10).getBooleanValue(false);
      globalConfig.getTag("options.inworld tooltips").setPosition(11).getBooleanValue(false);
      API.addSetting(new GuiNEISettings.NEIOption("options.inworld tooltips") {
         public String updateText() {
            return "Highlight tips " + (this.enabled()?"shown":"hidden");
         }
      });
      globalConfig.getTag("command.creative").setComment("{0} = 1 for creative, 0 for survival, {1} = player").setDefaultValue("/gamemode {0} {1}");
      globalConfig.getTag("command.give").setNewLine(true).setComment("{0} = player, {1} = itemID, {2} = quantity, {3} = itemDamage").setDefaultValue("/give {0} {1} {2} {3}");
      globalConfig.getTag("command.time").setNewLine(true).setComment("{0} = worldtime").setDefaultValue("/time set {0}");
      globalConfig.getTag("command.rain").setNewLine(true).setComment("{0} = 1 for on, 0 for off").setDefaultValue("/toggledownfall");
      globalConfig.getTag("command.heal").setNewLine(true).setComment("The vanilla server doesn\'t have a heal command, however others may:{0} = player").setDefaultValue("null");
      checkCheatMode();
      addBlockIDSettings();
      setDefaultKeyBindings();
      globalConfig.saveConfig();
   }

   private static void addBlockIDSettings() {
      API.addSetting((new GuiNEISettings.NEIOption("") {
         public String updateText() {
            return "Block/Item ID Settings";
         }
         public void onClick() {
            NEIClientUtils.mc().displayGuiScreen(new GuiNEIBlockIDs(((GuiNEISettings)NEIClientUtils.mc().currentScreen).parentScreen));
         }
      }).setGlobalOnly());
      globalConfig.getTag("ID dump").useBraces().setComment("Block/Item ID settings, configured via the options menu ingame.");
      globalConfig.getTag("ID dump.show empty blockIDs").getBooleanValue(false);
      API.addSetting(GuiNEIBlockIDs.class, new GuiNEISettings.NEIOption("ID dump.show empty blockIDs") {
         public String updateText() {
            return (this.enabled()?"Show":"Hide") + " Unused BlockIDs";
         }
      });
      globalConfig.getTag("ID dump.dump on load").getBooleanValue(false);
      API.addSetting(GuiNEIBlockIDs.class, (new GuiNEISettings.NEIOption("ID dump.dump on load") {
         public String updateText() {
            return (this.enabled()?"Dump ID Map":"Do Nothing") + " on Load";
         }
      }).setGlobalOnly());
      globalConfig.getTag("ID dump.blockIDs").getBooleanValue(true);
      API.addSetting(GuiNEIBlockIDs.class, (new GuiNEISettings.NEIOption("ID dump.blockIDs") {
         public String updateText() {
            return (this.enabled()?"Dump":"Ignore") + " BlockIDs";
         }
      }).setGlobalOnly());
      globalConfig.getTag("ID dump.itemIDs").getBooleanValue(false);
      API.addSetting(GuiNEIBlockIDs.class, (new GuiNEISettings.NEIOption("ID dump.itemIDs") {
         public String updateText() {
            return (this.enabled()?"Dump":"Ignore") + " ItemIDs";
         }
      }).setGlobalOnly());
      globalConfig.getTag("ID dump.unused blockIDs").getBooleanValue(true);
      API.addSetting(GuiNEIBlockIDs.class, (new GuiNEISettings.NEIOption("ID dump.unused blockIDs") {
         public String updateText() {
            return (this.enabled()?"Dump":"Ignore") + " Unused BlockIDs";
         }
      }).setGlobalOnly());
      globalConfig.getTag("ID dump.unused itemIDs").getBooleanValue(false);
      API.addSetting(GuiNEIBlockIDs.class, (new GuiNEISettings.NEIOption("ID dump.unused itemIDs") {
         public String updateText() {
            return (this.enabled()?"Dump":"Ignore") + " Unused ItemIDs";
         }
      }).setGlobalOnly());
      API.addSetting(GuiNEIBlockIDs.class, (new GuiNEISettings.NEIOption("") {
         public String updateText() {
            return NEIClientConfig.canDump()?"Dump ID Map Now":"Nothing To Dump";
         }
         public void onClick() {
            if(NEIClientConfig.canDump()) {
               NEIClientUtils.dumpIDs();
            }

         }
      }).setGlobalOnly());
      if(!canDump()) {
         globalConfig.getTag("ID dump.dump on load").setBooleanValue(false);
      }

   }

   public static boolean canDump() {
      return getBooleanSetting("ID dump.itemIDs") || getBooleanSetting("ID dump.blockIDs") || getBooleanSetting("ID dump.unused itemIDs") || getBooleanSetting("ID dump.unused blockIDs");
   }

   private static void setDefaultKeyBindings() {
      API.addKeyBind("recipe", "Recipe", 19);
      API.addKeyBind("usage", "Usage", 22);
      API.addKeyBind("back", "Previous Recipe", 14);
      API.addKeyBind("enchant", "Enchantment", 45);
      API.addKeyBind("potion", "Potion", 25);
      API.addKeyBind("prev", "Prev Page", 201);
      API.addKeyBind("next", "Next Page", 209);
      API.addKeyBind("hide", "Hide\\Show", 24);
      API.addKeyBind("chunkoverlay", "Chunk Overlay", 67);
      API.addKeyBind("moboverlay", "Mob Spawn Overlay", 65);
   }

   public static void loadWorld(String saveName) {
      ClientHandler.instance().setWorld(ClientUtils.getWorld());
      setInternalEnabled(true);
      System.out.println("Loading World: " + saveName);
      loadConfig(ClientUtils.getWorld());
      File saveDir = new File(CommonUtils.getMinecraftDir(), "saves/NEI/" + saveName);
      if(!saveDir.exists()) {
         saveDir.mkdirs();
      }

      worldConfig = new ConfigFile(new File(saveDir, "NEI.cfg"));
      localSave = new File(saveDir, "NEI.dat");

      try {
         if(!localSave.exists()) {
            localSave.createNewFile();
         }

         if(localSave.length() == 0L) {
            localCompound = new NBTTagCompound();
         } else {
            DataInputStream itemList = new DataInputStream(new FileInputStream(localSave));
            localCompound = (NBTTagCompound)NBTBase.readNamedTag(itemList);
            itemList.close();
         }
      } catch (Exception var5) {
         NEIClientUtils.reportException(var5);
      }

      worldConfig.setComment("World based configuration of NEI.\nMost of these options can be changed ingame.\nDeleting any element will restore it to it\'s default value");
      worldConfig.getTag("options").useBraces();
      worldConfig.getTag("options.searchinventories").getBooleanValue(false);
      worldConfig.removeTag("saved");
      setDefaultBoolean("magnetmode", false);
      setDefaultBoolean("creativeinv", false);
      Iterator var6 = AllowedPropertyMap.nameSet.iterator();

      while(var6.hasNext()) {
         String tagPos = (String)var6.next();
         setDefaultBoolean("disabled-" + tagPos, false);
      }

      if(!localCompound.hasKey("search")) {
         localCompound.setString("search", "");
      }

      if(!localCompound.hasKey("quantity")) {
         localCompound.setInteger("quantity", 0);
      }

      creativeInv = new ItemStack[54];
      NBTTagList var7 = localCompound.getTagList("creativeitems");
      if(var7 != null) {
         for(int var8 = 0; var8 < var7.tagCount(); ++var8) {
            NBTTagCompound stacksave = (NBTTagCompound)var7.tagAt(var8);
            creativeInv[stacksave.getByte("Slot") & 255] = ItemStack.loadItemStackFromNBT(stacksave);
         }
      }

      saveLocalConfig();
      LayoutManager.searchField.setText(getSearchExpression());
      LayoutManager.quantity.setText(Integer.toString(getItemQuantity()));
   }

   public static int getItemQuantity() {
      return localCompound.getInteger("quantity");
   }

   private static void setDefaultBoolean(String setting, boolean value) {
      if(!localCompound.hasKey(setting)) {
         localCompound.setBoolean(setting, value);
      }

   }

   public static boolean isWorldSpecific(String setting) {
      return worldConfig != null && worldConfig.containsTag(setting);
   }

   public static void copyWorldSetting(String setting) {
      if(worldConfig != null) {
         worldConfig.getTag(setting).setValue(globalConfig.getTag(setting).getValue());
      }

   }

   public static void removeWorldSetting(String setting) {
      if(worldConfig != null) {
         worldConfig.removeTag(setting);
      }

   }

   public static boolean isStateSaved(int i) {
      return statesSaved[i];
   }

   public static ConfigTag getSetting(String s) {
      return worldConfig != null && !global && worldConfig.containsTag(s)?worldConfig.getTag(s):globalConfig.getTag(s);
   }

   public static boolean getBooleanSetting(String s) {
      return getSetting(s).getBooleanValue();
   }

   public static boolean isEnabled() {
      return internalEnabled && getBooleanSetting("options.enable");
   }

   public static void setEnabled(boolean flag) {
      getSetting("options.enable").setBooleanValue(flag);
   }

   public static int getKeyBinding(String string) {
      return getSetting("KeyBinding." + string).getIntValue();
   }

   public static void setKeyBinding(String string, int key) {
      getSetting("KeyBinding." + string).setIntValue(key);
   }

   public static void setDefaultKeyBinding(String string, int key) {
      globalConfig.getTag("KeyBinding." + string).getIntValue(key);
   }

   public static int getCheatMode() {
      return getIntSetting("options.cheatmode");
   }

   public static void cycleCheatMode() {
      cycleSetting("options.cheatmode", 3);
      checkCheatMode();
   }

   private static void checkCheatMode() {
      if(getLockedMode() != -1) {
         setIntSetting("options.cheatmode", getLockedMode());
      }

   }

   public static int getLockedMode() {
      return getIntSetting("options.lockmode");
   }

   public static int getLayoutStyle() {
      return getIntSetting("options.layout style");
   }

   protected static void cycleLayoutStyle() {
      LinkedList list = new LinkedList();
      Iterator currentLayout = LayoutManager.layoutStyles.entrySet().iterator();

      while(currentLayout.hasNext()) {
         Entry nextLayout = (Entry)currentLayout.next();
         list.add(nextLayout.getKey());
      }

      Collections.sort(list);
      int currentLayout1 = getLayoutStyle();
      int nextLayout1 = currentLayout1;
      if(currentLayout1 == ((Integer)list.getLast()).intValue()) {
         nextLayout1 = -1;
      }

      Iterator i$ = list.iterator();

      while(i$.hasNext()) {
         Integer i = (Integer)i$.next();
         if(i.intValue() > nextLayout1) {
            nextLayout1 = i.intValue();
            break;
         }
      }

      setIntSetting("options.layout style", nextLayout1);
   }

   public static String getStringSetting(String s) {
      return getSetting(s).getValue();
   }

   public static boolean isHidden() {
      return !internalEnabled || getBooleanSetting("options.hidden");
   }

   public static boolean showIDs() {
      int i = getIntSetting("options.show ids");
      return i == 2 || i == 1 && isEnabled() && !isHidden();
   }

   public static int getIDVisibility() {
      return getIntSetting("options.show ids");
   }

   public static void cycleIDVisibility() {
      cycleSetting("options.show ids", 3);
   }

   public static void toggleBooleanSetting(String setting) {
      ConfigTag tag = getSetting(setting);
      tag.setBooleanValue(!tag.getBooleanValue());
   }

   public static void cycleSetting(String setting, int max) {
      ConfigTag tag = getSetting(setting);
      tag.setIntValue((tag.getIntValue() + 1) % max);
   }

   public static int getIntSetting(String setting) {
      return getSetting(setting).getIntValue();
   }

   public static void setIntSetting(String setting, int val) {
      getSetting(setting).setIntValue(val);
   }

   public static String getSearchExpression() {
      return localCompound.getString("search");
   }

   public static void setSearchExpression(String expression) {
      localCompound.setString("search", expression);
      saveLocalConfig();
   }

   public static boolean getMagnetMode() {
      return SMPMagnetMode;
   }

   public static boolean invCreativeMode() {
      if(SMPCreativeInv && !isActionPermissable(InterActionMap.CREATIVE)) {
         SMPCreativeInv = false;
      }

      return SMPCreativeInv;
   }

   public static void setInvCreative(boolean b) {
      SMPCreativeInv = b;
   }

   public static void setMagnetMode(boolean b) {
      SMPMagnetMode = b;
   }

   public static boolean areDamageVariantsShown() {
      return hasSMPCounterPart() || getSetting("command.give").getValue().contains("{3}");
   }

   public static void clearState(int state) {
      statesSaved[state] = false;
      saveCompound.setTag("save" + state, new NBTTagCompound());
      saveConfig();
   }

   public static void loadState(int state) {
      if(statesSaved[state]) {
         NBTTagCompound statesave = saveCompound.getCompoundTag("save" + state);
         GuiContainer currentContainer = NEIClientUtils.getGuiContainer();
         LinkedList saveAreas = new LinkedList();
         saveAreas.add(new TaggedInventoryArea(NEIClientUtils.mc().thePlayer.inventory));
         Iterator i$ = GuiInfo.guiHandlers.iterator();

         while(i$.hasNext()) {
            INEIGuiHandler area = (INEIGuiHandler)i$.next();
            List areaTag = area.getInventoryAreas(currentContainer);
            if(areaTag != null) {
               saveAreas.addAll(areaTag);
            }
         }

         i$ = saveAreas.iterator();

         while(i$.hasNext()) {
            TaggedInventoryArea var10 = (TaggedInventoryArea)i$.next();
            if(statesave.hasKey(var10.tag)) {
               Iterator var11 = var10.slots.iterator();

               int i;
               while(var11.hasNext()) {
                  i = ((Integer)var11.next()).intValue();
                  NEIClientUtils.setSlotContents(i, (ItemStack)null, var10.isContainer());
               }

               NBTTagList var12 = statesave.getTagList(var10.tag);

               for(i = 0; i < var12.tagCount(); ++i) {
                  NBTTagCompound stacksave = (NBTTagCompound)var12.tagAt(i);
                  int slot = stacksave.getByte("Slot") & 255;
                  if(var10.slots.contains(Integer.valueOf(slot))) {
                     NEIClientUtils.setSlotContents(slot, ItemStack.loadItemStackFromNBT(stacksave), var10.isContainer());
                  }
               }
            }
         }

      }
   }

   public static void saveState(int state) {
      NBTTagCompound statesave = saveCompound.getCompoundTag("save" + state);
      GuiContainer currentContainer = NEIClientUtils.getGuiContainer();
      LinkedList saveAreas = new LinkedList();
      saveAreas.add(new TaggedInventoryArea(NEIClientUtils.mc().thePlayer.inventory));
      Iterator i$ = GuiInfo.guiHandlers.iterator();

      while(i$.hasNext()) {
         INEIGuiHandler area = (INEIGuiHandler)i$.next();
         List areaTag = area.getInventoryAreas(currentContainer);
         if(areaTag != null) {
            saveAreas.addAll(areaTag);
         }
      }

      i$ = saveAreas.iterator();

      while(i$.hasNext()) {
         TaggedInventoryArea area1 = (TaggedInventoryArea)i$.next();
         NBTTagList areaTag1 = new NBTTagList(area1.tag);
         Iterator i$1 = area1.slots.iterator();

         while(i$1.hasNext()) {
            int i = ((Integer)i$1.next()).intValue();
            ItemStack stack = area1.getStackInSlot(i);
            if(stack != null) {
               NBTTagCompound stacksave = new NBTTagCompound();
               stacksave.setByte("Slot", (byte)i);
               stack.writeToNBT(stacksave);
               areaTag1.appendTag(stacksave);
            }
         }

         statesave.setTag(area1.tag, areaTag1);
      }

      saveCompound.setTag("save" + state, statesave);
      statesSaved[state] = true;
      saveConfig();
   }

   public static void loadStates() {
      for(int state = 0; state < 7; ++state) {
         if(saveCompound.hasKey("save" + state) && saveCompound.getTag("save" + state) instanceof NBTTagList) {
            saveCompound.removeTag("save" + state);
         }

         statesSaved[state] = !saveCompound.getCompoundTag("save" + state).hasNoTags();
      }

   }

   public static void loadConfig(World world) {
      if(!configLoaded) {
         loadSavedConfig();
         vishash = new ItemVisibilityHash();
         ItemInfo.load(world);
         GuiInfo.load();
         RecipeInfo.load();
         LayoutManager.load();
         NEIController.load();
         if(NEICompatibility.hasForge) {
            weakDependancy_Forge.load();
         }

         configLoaded = true;
         ClassDiscoverer classDiscoverer = new ClassDiscoverer(new IStringMatcher() {
            public boolean matches(String test) {
               return test.startsWith("NEI") && test.endsWith("Config.class");
            }
         }, new Class[]{IConfigureNEI.class});
         classDiscoverer.findClasses();
         Iterator i$ = classDiscoverer.classes.iterator();

         while(i$.hasNext()) {
            Class class1 = (Class)i$.next();

            try {
               IConfigureNEI e = (IConfigureNEI)class1.newInstance();
               e.loadConfig();
               NEIModContainer.plugins.add(e);
               System.out.println("Loaded " + class1.getName());
            } catch (Exception var5) {
               System.out.println("Failed to Load " + class1.getName());
               var5.printStackTrace();
            }
         }

      }
   }

   public static void saveConfig() {
      try {
         if(!saveFile.exists()) {
            saveFile.createNewFile();
         }

         FileOutputStream e = new FileOutputStream(saveFile);
         DataOutputStream dout = new DataOutputStream(e);
         NBTBase.writeNamedTag(saveCompound, dout);
         dout.close();
         e.close();
      } catch (Exception var2) {
         NEIClientUtils.reportException(var2);
      }

   }

   public static void saveLocalConfig() {
      try {
         DataOutputStream e = new DataOutputStream(new FileOutputStream(localSave));
         NBTBase.writeNamedTag(localCompound, e);
         e.close();
      } catch (Exception var1) {
         NEIClientUtils.reportException(var1);
      }

   }

   private static void loadSavedConfig() {
      try {
         if(!saveFile.exists()) {
            saveFile.createNewFile();
         }

         if(saveFile.length() == 0L) {
            return;
         }

         FileInputStream e = new FileInputStream(saveFile);
         DataInputStream din = new DataInputStream(e);
         saveCompound = (NBTTagCompound)NBTBase.readNamedTag(din);
         din.close();
         e.close();
         loadStates();
         ItemVisibilityHash.loadStates();
      } catch (Exception var2) {
         NEIClientUtils.reportException(var2);
      }

   }

   public static boolean hasSMPCounterPart() {
      return hasSMPCounterpart;
   }

   public static void setHasSMPCounterPart(boolean flag) {
      hasSMPCounterpart = flag;
      permissableActions.clear();
      bannedBlocks.clear();
      disabledProperties.clear();
   }

   public static void resetPermissableActions() {
      permissableActions.clear();
   }

   public static void addPermissableAction(InterActionMap action) {
      permissableActions.add(action);
   }

   public static boolean isActionPermissable(InterActionMap action) {
      return isActionPermissable(action.getName());
   }

   public static boolean isActionPermissable(String actionname) {
      if(isEnabled() && !isHidden()) {
         if(actionname.equals("nbt")) {
            return hasSMPCounterPart();
         } else {
            InterActionMap action = InterActionMap.getAction(actionname);
            return !isActionPermissableInMode(actionname)?false:(action == InterActionMap.HEAL && !hasSMPCounterpart?!getStringSetting("command.heal").equals("null"):(hasSMPCounterpart?permissableActions.contains(action):!hasSMPCounterpart && !action.requiresSMPCounterpart));
         }
      } else {
         return false;
      }
   }

   private static boolean isActionPermissableInMode(String actionmode) {
      if(getCheatMode() == 0) {
         return false;
      } else if(getCheatMode() == 2) {
         return true;
      } else {
         String[] actions = getUtilityDefinition();
         String[] arr$ = actions;
         int len$ = actions.length;

         for(int i$ = 0; i$ < len$; ++i$) {
            String action = arr$[i$];
            if(action.equalsIgnoreCase(actionmode)) {
               return true;
            }
         }

         return false;
      }
   }

   private static String[] getUtilityDefinition() {
      return getStringSetting("options.utility actions").replace(" ", "").split(",");
   }

   public static void setBannedBlocks(ArrayList ahash) {
      bannedBlocks.clear();
      Iterator i$ = ahash.iterator();

      while(i$.hasNext()) {
         ItemKey hash = (ItemKey)i$.next();
         bannedBlocks.add(hash);
      }

   }

   public static void resetDisabledProperties() {
      disabledProperties.clear();
   }

   public static boolean canGetItem(ItemKey item) {
      return !bannedBlocks.contains(item);
   }

   public static boolean isPropertyDisabled(String name) {
      return disabledProperties.contains(AllowedPropertyMap.nameToIDMap.get(name));
   }

   public static void setPropertyDisabled(int ID) {
      disabledProperties.add(Integer.valueOf(ID));
   }

   public static void setPropertyDisabled(String name, boolean disable) {
      if(hasSMPCounterPart()) {
         NEICPH.sendSetPropertyDisabled(name, disable);
      }

   }

   public static void setItemQuantity(int i) {
      localCompound.setInteger("quantity", i);
      saveLocalConfig();
   }

   public static void setInternalEnabled(boolean b) {
      internalEnabled = b;
   }

   public static void setCreativeMode(int mode) {
      setInvCreative(mode == 2);
      Minecraft.getMinecraft().playerController.setGameType(mode != 0?EnumGameType.CREATIVE:EnumGameType.SURVIVAL);
   }

   public static boolean validateEnchantments() {
      return worldConfig.getTag("saved.validateenchantments").getBooleanValue(true);
   }

   public static void toggleEnchantmentValidation() {
      worldConfig.getTag("saved.validateenchantments").setBooleanValue(!validateEnchantments());
   }

   static {
      setDefaults();
   }
}
