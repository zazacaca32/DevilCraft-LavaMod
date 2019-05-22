package codechicken.nei.api;

import codechicken.core.CommonUtils;
import codechicken.core.featurehack.GameDataManipulator;
import codechicken.core.inventory.ItemKey;
import codechicken.nei.InfiniteStackSizeHandler;
import codechicken.nei.InfiniteToolHandler;
import codechicken.nei.ItemMobSpawner;
import codechicken.nei.MultiItemRange;
import codechicken.nei.NEICompatibility;
import codechicken.nei.PopupInputHandler;
import codechicken.nei.api.API;
import codechicken.nei.api.IHighlightHandler;
import codechicken.nei.forge.GuiContainerManager;
import codechicken.nei.recipe.BrewingRecipeHandler;
import codechicken.nei.recipe.RecipeItemInputHandler;
import codechicken.nei.recipe.weakDependancy_Forge;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.common.registry.ItemData;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

public class ItemInfo {

   public static final HashMap highlightHandlers = new HashMap();
   public static final HashMap fallbackNames = new HashMap();
   public static final HashSet excludeIds = new HashSet();
   public static final HashSet nonUnlimitedIds = new HashSet();
   public static final HashMap damageVariants = new HashMap();
   public static final ArrayList defaultDamageRange = new ArrayList();
   public static final HashMap itemcompounds = new HashMap();
   public static final LinkedList infiniteHandlers = new LinkedList();
   public static final HashMap highlightIdentifiers = new HashMap();
   public static final HashSet fastTransferExemptions = new HashSet();


   static {
      ItemInfo.Layout[] var3;
      int var2 = (var3 = ItemInfo.Layout.values()).length;

      for(int var1 = 0; var1 < var2; ++var1) {
         ItemInfo.Layout layout = var3[var1];
         highlightHandlers.put(layout, new ArrayList());
      }

   }

   public static boolean isHidden(int itemID) {
      return excludeIds.contains(Integer.valueOf(itemID));
   }

   public static ArrayList getItemDamageVariants(int itemID) {
      ArrayList damages = (ArrayList)damageVariants.get(Integer.valueOf(itemID));
      return damages == null?defaultDamageRange:damages;
   }

   public static String getOverrideName(int itemID, int itemDamage) {
      ItemKey itemhash = new ItemKey(itemID, itemDamage);
      return (String)fallbackNames.get(itemhash);
   }

   public static boolean canItemBeUnlimited(int itemID) {
      return !nonUnlimitedIds.contains(Integer.valueOf(itemID));
   }

   public static ArrayList getItemCompounds(int itemID) {
      return (ArrayList)itemcompounds.get(Integer.valueOf(itemID));
   }

   public static void load(World world) {
      defaultDamageRange.add(new int[]{0, 15});
      addVanillaBlockProperties();
      addDefaultDropDowns();
      searchItems();
      addModItemDropDowns();
      addMobSpawnerItem(world);
      addSpawnEggs();
      (new BrewingRecipeHandler()).searchPotions();
      addInfiniteHandlers();
      addInputHandlers();
   }

   private static void addModItemDropDowns() {
      NBTTagList itemDataList = new NBTTagList();
      GameData.writeItemData(itemDataList);
      HashMap modRangeMap = new HashMap();

      for(int entry = 0; entry < itemDataList.tagCount(); ++entry) {
         ItemData itemData = new ItemData((NBTTagCompound)itemDataList.tagAt(entry));
         MultiItemRange modID = (MultiItemRange)modRangeMap.get(itemData.getModId());
         if(modID == null) {
            modRangeMap.put(itemData.getModId(), modID = new MultiItemRange());
         }

         modID.add(itemData.getItemId());
      }

      Iterator var8 = modRangeMap.entrySet().iterator();

      while(var8.hasNext()) {
         Entry var7 = (Entry)var8.next();
         String var9 = (String)var7.getKey();
         ModContainer mod = CommonUtils.findModContainer(var9);
         String modname = mod == null?var9:mod.getName();
         API.addSetRange("Мод." + modname, (MultiItemRange)var7.getValue());
      }

   }

   private static void addInputHandlers() {
      GuiContainerManager.addInputHandler(new RecipeItemInputHandler());
      GuiContainerManager.addInputHandler(new PopupInputHandler());
   }

   private static void addMobSpawnerItem(final World world) {
      Item.itemsList[Block.mobSpawner.blockID] = null;
      GameDataManipulator.createHiddenItem(new Runnable() {
         public void run() {
            new ItemMobSpawner(world);
         }
      });
      if(NEICompatibility.hasForge) {
         weakDependancy_Forge.addMobSpawnerRenderer();
      }

   }

   private static void addInfiniteHandlers() {
      API.addInfiniteItemHandler(new InfiniteStackSizeHandler());
      API.addInfiniteItemHandler(new InfiniteToolHandler());
   }

   private static void addVanillaBlockProperties() {
      API.setOverrideName(Block.waterMoving.blockID, 0, "Water Source");
      API.setMaxDamageException(Block.waterMoving.blockID, 0);
      API.setOverrideName(Block.waterStill.blockID, 0, "Water Still");
      API.setMaxDamageException(Block.waterStill.blockID, 0);
      API.setOverrideName(Block.lavaMoving.blockID, 0, "Lava Source");
      API.setMaxDamageException(Block.lavaMoving.blockID, 0);
      API.setOverrideName(Block.lavaStill.blockID, 0, "Lava Still");
      API.setMaxDamageException(Block.lavaStill.blockID, 0);
      API.setOverrideName(Block.silverfish.blockID, 0, "Silverfish Stone");
      API.setOverrideName(Block.endPortal.blockID, 0, "End Portal");
      API.setOverrideName(Block.endPortalFrame.blockID, 0, "End Portal Frame");
      API.hideItem(Block.pistonExtension.blockID);
      API.hideItem(Block.pistonMoving.blockID);
      API.hideItem(Block.melonStem.blockID);
      API.hideItem(Block.pumpkinStem.blockID);
      API.hideItem(Block.bed.blockID);
      API.hideItem(Block.redstoneWire.blockID);
      API.hideItem(Block.crops.blockID);
      API.hideItem(Block.signPost.blockID);
      API.hideItem(Block.doorWood.blockID);
      API.hideItem(Block.signWall.blockID);
      API.hideItem(Block.doorIron.blockID);
      API.hideItem(Block.oreRedstoneGlowing.blockID);
      API.hideItem(Block.torchRedstoneIdle.blockID);
      API.hideItem(Block.reed.blockID);
      API.hideItem(Block.redstoneRepeaterIdle.blockID);
      API.hideItem(Block.redstoneRepeaterActive.blockID);
      API.hideItem(Block.cauldron.blockID);
      API.hideItem(Block.netherStalk.blockID);
      API.hideItem(Block.brewingStand.blockID);
      API.hideItem(Block.furnaceBurning.blockID);
      API.hideItem(Block.redstoneLampActive.blockID);
      API.hideItem(Block.flowerPot.blockID);
      API.hideItem(Block.carrot.blockID);
      API.hideItem(Block.potato.blockID);
      API.hideItem(Block.skull.blockID);
      API.hideItem(Block.tripWire.blockID);
   }

   private static void addDefaultDropDowns() {
      API.addSetRange("Блоки", new MultiItemRange("[0-32000]") {
         public void addItemIfInRange(int item, int damage, NBTTagCompound compound) {
            if(item < Block.blocksList.length && Block.blocksList[item] != null && Block.blocksList[item].blockMaterial != Material.air) {
               super.addItemIfInRange(item, damage, compound);
            }

         }
      });
      API.addSetRange("Предметы", new MultiItemRange("[0-32000]") {
         public void addItemIfInRange(int item, int damage, NBTTagCompound compound) {
            if(item >= Block.blocksList.length || Block.blocksList[item] == null || Block.blocksList[item].blockMaterial == Material.air) {
               super.addItemIfInRange(item, damage, compound);
            }

         }
      });
      API.addSetRange("Блоки.Спавнеры", new MultiItemRange("[52]"));
   }

   private static void searchItems() {
      MultiItemRange tools = new MultiItemRange();
      MultiItemRange picks = new MultiItemRange();
      MultiItemRange shovels = new MultiItemRange();
      MultiItemRange axes = new MultiItemRange();
      MultiItemRange hoes = new MultiItemRange();
      MultiItemRange swords = new MultiItemRange();
      MultiItemRange chest = new MultiItemRange();
      MultiItemRange helmets = new MultiItemRange();
      MultiItemRange legs = new MultiItemRange();
      MultiItemRange boots = new MultiItemRange();
      MultiItemRange other = new MultiItemRange();
      MultiItemRange ranged = new MultiItemRange();
      MultiItemRange food = new MultiItemRange();
      MultiItemRange potioningredients = new MultiItemRange();
      MultiItemRange[] creativeTabRanges = new MultiItemRange[CreativeTabs.creativeTabArray.length];
      CreativeTabs[] var18 = CreativeTabs.creativeTabArray;
      int var17 = CreativeTabs.creativeTabArray.length;

      CreativeTabs tab;
      int var16;
      for(var16 = 0; var16 < var17; ++var16) {
         tab = var18[var16];
         creativeTabRanges[tab.getTabIndex()] = new MultiItemRange();
      }

      Item[] var21 = Item.itemsList;
      var17 = Item.itemsList.length;

      for(var16 = 0; var16 < var17; ++var16) {
         Item var20 = var21[var16];
         if(var20 != null) {
            CreativeTabs itemTab = var20.getCreativeTab();
            if(itemTab != null) {
               creativeTabRanges[itemTab.getTabIndex()].add(var20);
            }

            if(var20.isDamageable()) {
               tools.add(var20);
               if(var20 instanceof ItemPickaxe) {
                  picks.add(var20);
               } else if(var20 instanceof ItemSpade) {
                  shovels.add(var20);
               } else if(var20 instanceof ItemAxe) {
                  axes.add(var20);
               } else if(var20 instanceof ItemHoe) {
                  hoes.add(var20);
               } else if(var20 instanceof ItemSword) {
                  swords.add(var20);
               } else if(var20 instanceof ItemArmor) {
                  switch(((ItemArmor)var20).armorType) {
                  case 0:
                     helmets.add(var20);
                     break;
                  case 1:
                     chest.add(var20);
                     break;
                  case 2:
                     legs.add(var20);
                     break;
                  case 3:
                     boots.add(var20);
                  }
               } else if(var20 != Item.arrow && var20 != Item.bow) {
                  if(var20 == Item.fishingRod || var20 == Item.flintAndSteel || var20 == Item.shears) {
                     other.add(var20);
                  }
               } else {
                  ranged.add(var20);
               }
            }

            if(var20 instanceof ItemFood) {
               food.add(var20);
            }

            if(var20.isPotionIngredient()) {
               BrewingRecipeHandler.ingredientIDs.add(Integer.valueOf(var20.itemID));
               potioningredients.add(var20);
            }
         }
      }

      API.addSetRange("Предметы.Инструмент.Кирки", picks);
      API.addSetRange("Предметы.Инструмент.Лопаты", shovels);
      API.addSetRange("Предметы.Инструмент.Топоры", axes);
      API.addSetRange("Предметы.Инструмент.Мотыги", hoes);
      API.addSetRange("Предметы.Инструмент.Разное", other);
      API.addSetRange("Предметы.Оружие.Мечи", swords);
      API.addSetRange("Предметы.Оружие.Луки", ranged);
      API.addSetRange("Предметы.Броня.Нагрудники", chest);
      API.addSetRange("Предметы.Броня.Штаны", legs);
      API.addSetRange("Предметы.Броня.Шлемы", helmets);
      API.addSetRange("Предметы.Броня.Ботинки", boots);
      API.addSetRange("Предметы.Еда", food);
      API.addSetRange("Предметы.Зелья.Ингредиенты", potioningredients);
      var18 = CreativeTabs.creativeTabArray;
      var17 = CreativeTabs.creativeTabArray.length;

      for(var16 = 0; var16 < var17; ++var16) {
         tab = var18[var16];
         if(creativeTabRanges[tab.getTabIndex()].ranges.size() > 0) {
            API.addSetRange("Творчество." + tab.getTranslatedTabLabel(), creativeTabRanges[tab.getTabIndex()]);
         }
      }

   }

   private static void addSpawnEggs() {
      ArrayList damages = new ArrayList();

      try {
         HashMap e = (HashMap)EntityList.classToIDMapping;
         damages.add((Integer)e.get(EntityMooshroom.class));
         damages.add((Integer)e.get(EntitySkeleton.class));
         damages.add((Integer)e.get(EntityCreeper.class));
         damages.add((Integer)e.get(EntitySlime.class));
         damages.add((Integer)e.get(EntityZombie.class));
         damages.add((Integer)e.get(EntitySpider.class));
         damages.add((Integer)e.get(EntityChicken.class));
         damages.add((Integer)e.get(EntityCaveSpider.class));
         damages.add((Integer)e.get(EntityCow.class));
         damages.add((Integer)e.get(EntityEnderman.class));
         damages.add((Integer)e.get(EntityWolf.class));
         damages.add((Integer)e.get(EntityPigZombie.class));
         damages.add((Integer)e.get(EntitySquid.class));
         damages.add((Integer)e.get(EntityGhast.class));
         damages.add((Integer)e.get(EntityMagmaCube.class));
         damages.add((Integer)e.get(EntitySheep.class));
         damages.add((Integer)e.get(EntityBlaze.class));
         damages.add((Integer)e.get(EntityVillager.class));
         damages.add((Integer)e.get(EntitySilverfish.class));
         damages.add((Integer)e.get(EntityPig.class));
         damages.add((Integer)e.get(EntityOcelot.class));
         addEntityEgg(damages, (Integer)e.get(EntitySnowman.class), 15663103, 16753185);
         addEntityEgg(damages, (Integer)e.get(EntityIronGolem.class), 12960449, 16769484);
      } catch (Exception var2) {
         var2.printStackTrace();
      }

      API.setItemDamageVariants(Item.monsterPlacer.itemID, (Collection)damages);
   }

   private static void addEntityEgg(ArrayList damages, Integer ID, int i, int j) {
      damages.add(ID);
      EntityList.entityEggs.put(ID, new EntityEggInfo(ID.intValue(), i, j));
   }

   public static ArrayList getIdentifierItems(World world, EntityPlayer player, MovingObjectPosition hit) {
      int x = hit.blockX;
      int y = hit.blockY;
      int z = hit.blockZ;
      Block mouseoverBlock = Block.blocksList[world.getBlockId(x, y, z)];
      ArrayList items = new ArrayList();
      ArrayList handlers = new ArrayList();
      if(highlightIdentifiers.get(Integer.valueOf(0)) != null) {
         handlers.addAll((Collection)highlightIdentifiers.get(Integer.valueOf(0)));
      }

      if(highlightIdentifiers.get(Integer.valueOf(mouseoverBlock.blockID)) != null) {
         handlers.addAll((Collection)highlightIdentifiers.get(Integer.valueOf(mouseoverBlock.blockID)));
      }

      Iterator shearable = handlers.iterator();

      while(shearable.hasNext()) {
         IHighlightHandler pick = (IHighlightHandler)shearable.next();
         ItemStack item = pick.identifyHighlight(world, player, hit);
         if(item != null) {
            items.add(item);
         }
      }

      if(items.size() > 0) {
         return items;
      } else {
         ItemStack pick1 = mouseoverBlock.getPickBlock(hit, world, x, y, z);
         if(pick1 != null) {
            items.add(pick1);
         }

         try {
            items.addAll(mouseoverBlock.getBlockDropped(world, x, y, z, world.getBlockMetadata(x, y, z), 0));
         } catch (Exception var12) {
            ;
         }

         if(mouseoverBlock instanceof IShearable) {
            IShearable shearable1 = (IShearable)mouseoverBlock;
            if(shearable1.isShearable(new ItemStack(Item.shears), world, x, y, z)) {
               items.addAll(shearable1.onSheared(new ItemStack(Item.shears), world, x, y, z, 0));
            }
         }

         if(items.size() == 0) {
            items.add(0, new ItemStack(mouseoverBlock, 1, world.getBlockMetadata(x, y, z)));
         }

         return items;
      }
   }

   public static void registerHighlightHandler(IHighlightHandler handler, ItemInfo.Layout ... layouts) {
      ItemInfo.Layout[] var5 = layouts;
      int var4 = layouts.length;

      for(int var3 = 0; var3 < var4; ++var3) {
         ItemInfo.Layout layout = var5[var3];
         ((ArrayList)highlightHandlers.get(layout)).add(handler);
      }

   }

   public static List getText(ItemStack itemStack, World world, EntityPlayer player, MovingObjectPosition mop) {
      Object retString = new ArrayList();
      ItemInfo.Layout[] var8;
      int var7 = (var8 = ItemInfo.Layout.values()).length;

      for(int var6 = 0; var6 < var7; ++var6) {
         ItemInfo.Layout layout = var8[var6];

         IHighlightHandler handler;
         for(Iterator var10 = ((ArrayList)highlightHandlers.get(layout)).iterator(); var10.hasNext(); retString = handler.handleTextData(itemStack, world, player, mop, (List)retString, layout)) {
            handler = (IHighlightHandler)var10.next();
         }
      }

      return (List)retString;
   }

   public static enum Layout {

      HEADER("HEADER", 0),
      BODY("BODY", 1),
      FOOTER("FOOTER", 2);
      // $FF: synthetic field
      private static final ItemInfo.Layout[] ENUM$VALUES = new ItemInfo.Layout[]{HEADER, BODY, FOOTER};


      private Layout(String var1, int var2) {}
   }
}
