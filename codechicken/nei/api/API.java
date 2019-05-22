package codechicken.nei.api;

import codechicken.core.inventory.ItemKey;
import codechicken.nei.DropDownFile;
import codechicken.nei.GuiNEIControls;
import codechicken.nei.GuiNEISettings;
import codechicken.nei.LayoutManager;
import codechicken.nei.MultiItemRange;
import codechicken.nei.NEIClientConfig;
import codechicken.nei.NEIClientUtils;
import codechicken.nei.OffsetPositioner;
import codechicken.nei.SubSetRangeTag;
import codechicken.nei.api.GuiInfo;
import codechicken.nei.api.IHighlightHandler;
import codechicken.nei.api.IInfiniteItemHandler;
import codechicken.nei.api.INEIGuiHandler;
import codechicken.nei.api.IOverlayHandler;
import codechicken.nei.api.IStackPositioner;
import codechicken.nei.api.ItemInfo;
import codechicken.nei.api.LayoutStyle;
import codechicken.nei.recipe.GuiCraftingRecipe;
import codechicken.nei.recipe.GuiUsageRecipe;
import codechicken.nei.recipe.ICraftingHandler;
import codechicken.nei.recipe.IUsageHandler;
import codechicken.nei.recipe.RecipeInfo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import net.minecraft.item.ItemStack;

public class API {

   public static void registerRecipeHandler(ICraftingHandler handler) {
      GuiCraftingRecipe.registerRecipeHandler(handler);
   }

   public static void registerUsageHandler(IUsageHandler handler) {
      GuiUsageRecipe.registerUsageHandler(handler);
   }

   public static void registerGuiOverlay(Class class1, String string) {
      registerGuiOverlay(class1, string, 5, 11);
   }

   public static void registerGuiOverlay(Class class1, String string, int x, int y) {
      registerGuiOverlay(class1, string, new OffsetPositioner(x, y));
   }

   public static void registerGuiOverlay(Class classz, String ident, IStackPositioner positioner) {
      RecipeInfo.registerGuiOverlay(classz, ident, positioner);
   }

   public static void registerGuiOverlayHandler(Class classz, IOverlayHandler handler, String ident) {
      RecipeInfo.registerOverlayHandler(classz, handler, ident);
   }

   public static void setGuiOffset(Class classz, int x, int y) {
      RecipeInfo.setGuiOffset(classz, x, y);
   }

   public static void registerNEIGuiHandler(INEIGuiHandler handler) {
      GuiInfo.guiHandlers.add(handler);
   }

   public static void hideItem(int itemID) {
      ItemInfo.excludeIds.add(Integer.valueOf(itemID));
   }

   public static void hideItems(Collection items) {
      ItemInfo.excludeIds.addAll(items);
   }

   public static void setOverrideName(int itemID, int itemDamage, String name) {
      ItemInfo.fallbackNames.put(new ItemKey(itemID, itemDamage), name);
   }

   public static void setItemDamageVariants(int itemID, ArrayList ranges) {
      ItemInfo.damageVariants.put(Integer.valueOf(itemID), ranges);
   }

   public static void setItemDamageVariants(int itemID, Collection damages) {
      setItemDamageVariants(itemID, NEIClientUtils.concatIntegersToRanges(new ArrayList(damages)));
   }

   public static void setMaxDamageException(int itemID, int maxDamage) {
      ArrayList damageset = new ArrayList();
      damageset.add(new int[]{0, maxDamage});
      setItemDamageVariants(itemID, damageset);
   }

   public static void addNBTItem(ItemStack item) {
      ArrayList datalist = (ArrayList)ItemInfo.itemcompounds.get(Integer.valueOf(item.itemID));
      if(datalist == null) {
         datalist = new ArrayList();
         ItemInfo.itemcompounds.put(Integer.valueOf(item.itemID), datalist);
      }

      datalist.add(item);
   }

   public static void addSetRange(String setname, MultiItemRange range) {
      SubSetRangeTag tag = DropDownFile.dropDownInstance.getTag(setname);
      tag.saveTag = false;
      tag.setRange(range);
      DropDownFile.dropDownInstance.updateState();
   }

   public static SubSetRangeTag getRangeTag(String tagname) {
      return DropDownFile.dropDownInstance.getTag(tagname);
   }

   public static void addToRange(String setname, MultiItemRange range) {
      SubSetRangeTag tag = DropDownFile.dropDownInstance.getTag(setname);
      if(tag.validranges == null) {
         tag.setRange(range);
      } else {
         tag.validranges.add(range);
      }

   }

   public static void addSetting(GuiNEISettings.NEIOption o) {
      addSetting(GuiNEISettings.class, o);
   }

   public static void addSetting(Class guiclass, GuiNEISettings.NEIOption o) {
      LinkedList list = (LinkedList)GuiNEISettings.optionMap.get(guiclass);
      if(list == null) {
         list = new LinkedList();
         GuiNEISettings.optionMap.put(guiclass, list);
      }

      list.add(o);
   }

   public static void addKeyBind(String ident, String desc, int defaultKey) {
      NEIClientConfig.setDefaultKeyBinding(ident, defaultKey);
      addKeyBind(GuiNEIControls.class, new GuiNEIControls.NEIKeyBind(ident, desc));
   }

   public static void addKeyBind(Class guiclass, GuiNEIControls.NEIKeyBind key) {
      LinkedList list = (LinkedList)GuiNEIControls.keyBindMap.get(guiclass);
      if(list == null) {
         list = new LinkedList();
         GuiNEIControls.keyBindMap.put(guiclass, list);
      }

      list.add(key);
   }

   public static void addLayoutStyle(int styleID, LayoutStyle style) {
      LayoutManager.layoutStyles.put(Integer.valueOf(styleID), style);
   }

   public static void addInfiniteItemHandler(IInfiniteItemHandler handler) {
      ItemInfo.infiniteHandlers.addFirst(handler);
   }

   public static void registerHighlightIdentifier(int blockID, IHighlightHandler handler) {
      ArrayList handlers = (ArrayList)ItemInfo.highlightIdentifiers.get(Integer.valueOf(blockID));
      if(handlers == null) {
         handlers = new ArrayList();
         ItemInfo.highlightIdentifiers.put(Integer.valueOf(blockID), handlers);
      }

      handlers.add(handler);
   }

   public static void addFastTransferExemptSlot(Class slotClass) {
      ItemInfo.fastTransferExemptions.add(slotClass);
   }

   public static void registerHighlightHandler(IHighlightHandler handler, ItemInfo.Layout ... layout) {
      ItemInfo.registerHighlightHandler(handler, layout);
   }
}
