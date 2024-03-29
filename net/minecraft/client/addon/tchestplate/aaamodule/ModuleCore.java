package net.minecraft.client.addon.tchestplate.aaamodule;

import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.client.addon.tchestplate.aaamodule.items.Capsule64;
import net.minecraft.client.addon.tchestplate.aaamodule.items.ExoItem;
import net.minecraft.client.addon.tchestplate.aaamodule.items.HebItem;
import net.minecraft.client.addon.tchestplate.aaamodule.items.ItemColor;
import net.minecraft.client.addon.tchestplate.aaamodule.items.ItemDark;
import net.minecraft.client.addon.tchestplate.aaamodule.items.ItemDelColor;
import net.minecraft.client.addon.tchestplate.aaamodule.items.ItemKDI;
import net.minecraft.client.addon.tchestplate.aaamodule.items.ItemKoin;
import net.minecraft.client.addon.tchestplate.aaamodule.items.ItemKorona;
import net.minecraft.client.addon.tchestplate.aaamodule.items.ItemKrid;
import net.minecraft.client.addon.tchestplate.aaamodule.items.ItemLGen;
import net.minecraft.client.addon.tchestplate.aaamodule.items.ItemLItems;
import net.minecraft.client.addon.tchestplate.aaamodule.items.ItemPartLava0;
import net.minecraft.client.addon.tchestplate.aaamodule.items.ItemPartLava1;
import net.minecraft.client.addon.tchestplate.aaamodule.items.ItemWither;
import net.minecraft.client.addon.tchestplate.aaamodule.items.YellowCapsule;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ModuleCore {

   public static Item[] itemColor = new Item[36];
   public static Item[] itemLGen = new Item[2];
   public static Item[] itemDark = new Item[20];
   public static Item[] exoItem = new Item[2];
   public static Item[] itemDelColor = new Item[2];
   public static Item[] itemHeb = new Item[2];
   public static Item[] itemCap1 = new Item[2];
   public static Item[] itemCap3 = new Item[2];
   public static Item[] item = new Item[256];
   public static Item[] itemKDI = new Item[4];
   public static Item[] itemKrid = new Item[4];
   public static Item[] itemKOIN = new Item[8];
   public static Item[] itemPanther = new Item[2];
   public static Item[] itemKorona = new Item[2];
   public static Item[] itemWither = new Item[2];
   
   public static Item[] itemPartLava0 = new Item[2];
   public static Item[] itemPartLava1 = new Item[2];


   @Init
   public void loadModules(FMLInitializationEvent e) {
      itemColor[0] = new ItemColor(3004, 0, "itemColor0");
      itemColor[1] = new ItemColor(3006, 1, "itemColor1");
      itemColor[2] = new ItemColor(3008, 2, "itemColor2");
      itemColor[3] = new ItemColor(3010, 3, "itemColor3");
      itemColor[4] = new ItemColor(3012, 4, "itemColor4");
      itemColor[5] = new ItemColor(3014, 5, "itemColor5");
      itemColor[6] = new ItemColor(3016, 6, "itemColor6");
      itemColor[7] = new ItemColor(3018, 7, "itemColor7");
      itemColor[8] = new ItemColor(3020, 8, "itemColor8");
      itemColor[9] = new ItemColor(3022, 9, "itemColor9");
      itemColor[10] = new ItemColor(3024, 10, "itemColor10");
      itemColor[11] = new ItemColor(3026, 11, "itemColor11");
      itemColor[12] = new ItemColor(3028, 12, "itemColor12");
      itemColor[13] = new ItemColor(3030, 13, "itemColor13");
      itemColor[14] = new ItemColor(3032, 14, "itemColor14");
      itemColor[15] = new ItemColor(3034, 15, "itemColor15");
      itemColor[16] = new ItemColor(3005, 16, "itemColor0");
      itemColor[17] = new ItemColor(3007, 17, "itemColor1");
      itemColor[18] = new ItemColor(3009, 18, "itemColor2");
      itemColor[19] = new ItemColor(3011, 19, "itemColor3");
      itemColor[20] = new ItemColor(3013, 20, "itemColor4");
      itemColor[21] = new ItemColor(3015, 21, "itemColor5");
      itemColor[22] = new ItemColor(3017, 22, "itemColor6");
      itemColor[23] = new ItemColor(3019, 23, "itemColor7");
      itemColor[24] = new ItemColor(3021, 24, "itemColor8");
      itemColor[25] = new ItemColor(3023, 25, "itemColor9");
      itemColor[26] = new ItemColor(3025, 26, "itemColor10");
      itemColor[27] = new ItemColor(3027, 27, "itemColor11");
      itemColor[28] = new ItemColor(3029, 28, "itemColor12");
      itemColor[29] = new ItemColor(3031, 29, "itemColor13");
      itemColor[30] = new ItemColor(3033, 30, "itemColor14");
      itemColor[31] = new ItemColor(3035, 31, "itemColor15");
      itemLGen[0] = new ItemLGen(3068, 0, "itemArmorLevel");
      itemLGen[1] = new ItemLGen(3069, 1, "itemArmorLevel");
      itemKDI[0] = new ItemKDI(3070, 0, "itemKDI");
      itemKDI[1] = new ItemKDI(3071, 1, "itemKDI");
      itemKrid[0] = new ItemKrid(3072, 0, "itemKrid");
      itemKrid[1] = new ItemKrid(3073, 1, "itemKrid");
      itemKOIN[0] = new ItemKoin(3074, 0, "itemKoin1");
      LanguageRegistry.addName(itemKOIN[0], "Серебрянный старый койн");
      LanguageRegistry.addName(itemKDI[0], "Индикатор КД");
      LanguageRegistry.addName(itemKDI[1], "Индикатор КД");
      LanguageRegistry.addName(itemKrid[0], "Алмазная капсула");
      LanguageRegistry.addName(itemKrid[1], "Алмазная капсула");
      LanguageRegistry.addName(itemLGen[0], "Ускоритель частиц");
      LanguageRegistry.addName(itemLGen[1], "Ускоритель частиц");
      LanguageRegistry.addName(itemColor[0], "Покрашенная пластина");
      LanguageRegistry.addName(itemColor[1], "Покрашенная пластина");
      LanguageRegistry.addName(itemColor[2], "Покрашенная пластина");
      LanguageRegistry.addName(itemColor[3], "Покрашенная пластина");
      LanguageRegistry.addName(itemColor[4], "Покрашенная пластина");
      LanguageRegistry.addName(itemColor[5], "Покрашенная пластина");
      LanguageRegistry.addName(itemColor[6], "Покрашенная пластина");
      LanguageRegistry.addName(itemColor[7], "Покрашенная пластина");
      LanguageRegistry.addName(itemColor[8], "Покрашенная пластина");
      LanguageRegistry.addName(itemColor[9], "Покрашенная пластина");
      LanguageRegistry.addName(itemColor[10], "Покрашенная пластина");
      LanguageRegistry.addName(itemColor[11], "Покрашенная пластина");
      LanguageRegistry.addName(itemColor[12], "Покрашенная пластина");
      LanguageRegistry.addName(itemColor[13], "Покрашенная пластина");
      LanguageRegistry.addName(itemColor[14], "Покрашенная пластина");
      LanguageRegistry.addName(itemColor[15], "Покрашенная пластина");
      LanguageRegistry.addName(itemColor[16], "Покрашенная пластина");
      LanguageRegistry.addName(itemColor[17], "Покрашенная пластина");
      LanguageRegistry.addName(itemColor[18], "Покрашенная пластина");
      LanguageRegistry.addName(itemColor[19], "Покрашенная пластина");
      LanguageRegistry.addName(itemColor[20], "Покрашенная пластина");
      LanguageRegistry.addName(itemColor[21], "Покрашенная пластина");
      LanguageRegistry.addName(itemColor[22], "Покрашенная пластина");
      LanguageRegistry.addName(itemColor[23], "Покрашенная пластина");
      LanguageRegistry.addName(itemColor[24], "Покрашенная пластина");
      LanguageRegistry.addName(itemColor[25], "Покрашенная пластина");
      LanguageRegistry.addName(itemColor[26], "Покрашенная пластина");
      LanguageRegistry.addName(itemColor[27], "Покрашенная пластина");
      LanguageRegistry.addName(itemColor[28], "Покрашенная пластина");
      LanguageRegistry.addName(itemColor[29], "Покрашенная пластина");
      LanguageRegistry.addName(itemColor[30], "Покрашенная пластина");
      LanguageRegistry.addName(itemColor[31], "Покрашенная пластина");
      short i = 256;
      exoItem[0] = new ExoItem(3036, 0, "itemExo");
      exoItem[1] = new ExoItem(3037, 1, "itemExo");
      LanguageRegistry.addName(new ItemStack(3035 + i + 1, 1, 0), "Экзоскелет");
      LanguageRegistry.addName(new ItemStack(3036 + i + 1, 1, 0), "Экзоскелет");
      itemCap1[0] = new Capsule64(3038, 0, "itemCap1");
      itemCap1[1] = new Capsule64(3039, 1, "itemCap1");
      LanguageRegistry.addName(new ItemStack(3037 + i + 1, 1, 0), "Капсула с материей");
      LanguageRegistry.addName(new ItemStack(3038 + i + 1, 1, 0), "Капсула с материей");
      itemCap3[0] = new YellowCapsule(3042, 0, "itemCap3");
      itemCap3[1] = new YellowCapsule(3043, 1, "itemCap3");
      LanguageRegistry.addName(new ItemStack(3041 + i + 1, 1, 0), "Капсула с блесс добавкой");
      LanguageRegistry.addName(new ItemStack(3042 + i + 1, 1, 0), "Капсула с блесс добавкой");
      itemHeb[0] = new HebItem(3044, 0, "itemHeb");
      itemHeb[1] = new HebItem(3045, 1, "itemHeb");
      LanguageRegistry.addName(new ItemStack(3043 + i + 1, 1, 0), "Картридж ада");
      LanguageRegistry.addName(new ItemStack(3044 + i + 1, 1, 0), "Картридж ада");
      itemDelColor[0] = new ItemDelColor(3046, 0, "itemDelColor");
      itemDelColor[1] = new ItemDelColor(3047, 1, "itemDelColor");
      LanguageRegistry.addName(new ItemStack(3045 + i + 1, 1, 0), "Очиститель краски");
      LanguageRegistry.addName(new ItemStack(3046 + i + 1, 1, 0), "Очиститель краски");
      itemDark[0] = new ItemDark(3048, 0, "itemDark1");
      itemDark[1] = new ItemDark(3050, 1, "itemDark2");
      itemDark[2] = new ItemDark(3052, 2, "itemDark3");
      itemDark[3] = new ItemDark(3054, 3, "itemDark4");
      itemDark[4] = new ItemDark(3056, 4, "itemDark5");
      itemDark[5] = new ItemDark(3058, 5, "itemDark6");
      itemDark[6] = new ItemDark(3060, 6, "itemDark7");
      itemDark[7] = new ItemDark(3062, 7, "itemDark8");
      itemDark[8] = new ItemDark(3064, 8, "itemDark9");
      itemDark[9] = new ItemDark(3066, 9, "itemDark10");
      itemDark[10] = new ItemDark(3049, 10, "itemDark1");
      itemDark[11] = new ItemDark(3051, 11, "itemDark2");
      itemDark[12] = new ItemDark(3053, 12, "itemDark3");
      itemDark[13] = new ItemDark(3055, 13, "itemDark4");
      itemDark[14] = new ItemDark(3057, 14, "itemDark5");
      itemDark[15] = new ItemDark(3059, 15, "itemDark6");
      itemDark[16] = new ItemDark(3061, 16, "itemDark7");
      itemDark[17] = new ItemDark(3063, 17, "itemDark8");
      itemDark[18] = new ItemDark(3065, 18, "itemDark9");
      itemDark[19] = new ItemDark(3067, 19, "itemDark10");
      LanguageRegistry.addName(new ItemStack(3047 + i + 1, 1, 0), "Щит тёмной энергии 1ур");
      LanguageRegistry.addName(new ItemStack(3049 + i + 1, 1, 0), "Щит тёмной энергии 2ур");
      LanguageRegistry.addName(new ItemStack(3051 + i + 1, 1, 0), "Щит тёмной энергии 3ур");
      LanguageRegistry.addName(new ItemStack(3053 + i + 1, 1, 0), "Щит тёмной энергии 4ур");
      LanguageRegistry.addName(new ItemStack(3055 + i + 1, 1, 0), "Щит тёмной энергии 5ур");
      LanguageRegistry.addName(new ItemStack(3057 + i + 1, 1, 0), "Щит тёмной энергии 6ур");
      LanguageRegistry.addName(new ItemStack(3059 + i + 1, 1, 0), "Щит тёмной энергии 7ур");
      LanguageRegistry.addName(new ItemStack(3061 + i + 1, 1, 0), "Щит тёмной энергии 8ур");
      LanguageRegistry.addName(new ItemStack(3063 + i + 1, 1, 0), "Щит тёмной энергии 9ур");
      LanguageRegistry.addName(new ItemStack(3065 + i + 1, 1, 0), "Щит тёмной энергии 10ур");
      LanguageRegistry.addName(new ItemStack(3048 + i + 1, 1, 0), "Щит тёмной энергии 1ур");
      LanguageRegistry.addName(new ItemStack(3050 + i + 1, 1, 0), "Щит тёмной энергии 2ур");
      LanguageRegistry.addName(new ItemStack(3052 + i + 1, 1, 0), "Щит тёмной энергии 3ур");
      LanguageRegistry.addName(new ItemStack(3054 + i + 1, 1, 0), "Щит тёмной энергии 4ур");
      LanguageRegistry.addName(new ItemStack(3056 + i + 1, 1, 0), "Щит тёмной энергии 5ур");
      LanguageRegistry.addName(new ItemStack(3058 + i + 1, 1, 0), "Щит тёмной энергии 6ур");
      LanguageRegistry.addName(new ItemStack(3060 + i + 1, 1, 0), "Щит тёмной энергии 7ур");
      LanguageRegistry.addName(new ItemStack(3062 + i + 1, 1, 0), "Щит тёмной энергии 8ур");
      LanguageRegistry.addName(new ItemStack(3064 + i + 1, 1, 0), "Щит тёмной энергии 9ур");
      LanguageRegistry.addName(new ItemStack(3066 + i + 1, 1, 0), "Щит тёмной энергии 10ур");
      itemPartLava1[0] = new ItemPartLava1(8036, 0, "itemPartLava1");
      itemPartLava1[1] = new ItemPartLava1(8037, 1, "itemPartLava1");
      LanguageRegistry.addName(new ItemStack(8035 + i + 1, 1, 0), "Лава пластина покрытая энергией");
      LanguageRegistry.addName(new ItemStack(8036 + i + 1, 1, 0), "Лава пластина покрытая энергией");
      itemPartLava0[0] = new ItemPartLava0(8038, 0, "itemPartLava0");
      itemPartLava0[1] = new ItemPartLava0(8039, 1, "itemPartLava0");
      LanguageRegistry.addName(new ItemStack(8037 + i + 1, 1, 0), "Лава пластина покрытая материей");
      LanguageRegistry.addName(new ItemStack(8038 + i + 1, 1, 0), "Лава пластина покрытая материей");
      item[3] = new ItemLItems(2652, 3, 64, "colorplastine");
      item[4] = new ItemLItems(2653, 4, 64, "module");
      LanguageRegistry.addName(item[3], "Синяя пластина");
      LanguageRegistry.addName(item[4], "Шаблон модуля");
      itemWither[0] = new ItemWither(3759, 0, "itemWither");
      itemWither[1] = new ItemWither(3760, 1, "itemWither");
      LanguageRegistry.addName(new ItemStack(3759 + i + 0, 1, 0), "Рб пластина");
      LanguageRegistry.addName(new ItemStack(3760 + i + 0, 1, 0), "Модуль рб пластины");
      itemKorona[0] = new ItemKorona(3659, 0, "ItemKorona");
      itemKorona[1] = new ItemKorona(3660, 1, "ItemKorona");
      LanguageRegistry.addName(new ItemStack(3659 + i + 0, 1, 0), "Модуль короны");
      LanguageRegistry.addName(new ItemStack(3660 + i + 0, 1, 0), "Модуль короны");
      ItemStack White = new ItemStack(3260, 1, 0);
      ItemStack Orange = new ItemStack(3262, 1, 0);
      ItemStack rozoviy = new ItemStack(3264, 1, 0);
      ItemStack Golyboi = new ItemStack(3266, 1, 0);
      ItemStack Yellow = new ItemStack(3268, 1, 0);
      ItemStack LiteGreen = new ItemStack(3270, 1, 0);
      ItemStack rozoviyrozoviy = new ItemStack(3272, 1, 0);
      ItemStack Seriy = new ItemStack(3274, 1, 0);
      ItemStack SvetloSeriy = new ItemStack(3276, 1, 0);
      ItemStack Biryzoviy = new ItemStack(3278, 1, 0);
      ItemStack Fiolet = new ItemStack(3280, 1, 0);
      ItemStack Blue = new ItemStack(3282, 1, 0);
      ItemStack Kori4 = new ItemStack(3284, 1, 0);
      ItemStack Green = new ItemStack(3286, 1, 0);
      ItemStack Red = new ItemStack(3288, 1, 0);
      ItemStack Black = new ItemStack(3290, 1, 0);
      ItemStack Assembler = new ItemStack(2908, 1, 0);
      ItemStack plast1 = new ItemStack(2906, 1, 0);
      ItemStack plast2 = new ItemStack(2907, 1, 0);
      ItemStack cap1 = new ItemStack(3294, 1, 0);
      ItemStack cap2 = new ItemStack(3322, 1, 0);
      ItemStack plas1 = new ItemStack(2472, 1, 5);
      ItemStack sfera1 = new ItemStack(20977, 1, 2);
      ItemStack plastrb1 = new ItemStack(2468, 1, 0);
      ItemStack pil = new ItemStack(2472, 1, 3);
      ItemStack encoder = new ItemStack(3326, 1, 0);
      ItemStack decoder = new ItemStack(3328, 1, 0);
      ItemStack kovaika = new ItemStack(145, 1, 0);
      ItemStack steklo = new ItemStack(20, 1, 0);
      new ItemStack(2469, 1, 0);
      ItemStack adplast = new ItemStack(2472, 1, 6);
      new ItemStack(2472, 1, 7);
      ItemStack paladiy = new ItemStack(2471, 1, 0);
      ItemStack block1 = new ItemStack(2716, 1, 0);
      ItemStack block2 = new ItemStack(2715, 1, 0);
      ItemStack plas2 = new ItemStack(2472, 1, 12);
      ItemStack plas3 = new ItemStack(2472, 1, 8);
      ItemStack block3 = new ItemStack(2717, 1, 0);
      ItemStack palad = new ItemStack(2471, 1, 1);
      ItemStack modfarmat = new ItemStack(2909, 1, 0);
      ItemStack nonmodule = new ItemStack(itemDelColor[0].itemID, 1, 0);
      ItemStack rbplast = new ItemStack(2468, 1, 0);
      ItemStack Capsula64 = new ItemStack(3294, 1, 0);
      ItemStack w1 = new ItemStack(2472, 1, 14);
      ItemStack w2 = new ItemStack(2472, 1, 15);
      GameRegistry.addRecipe(Capsula64, new Object[]{"###", "#&#", "&&&", Character.valueOf('#'), w1, Character.valueOf('&'), w2});
      GameRegistry.addRecipe(nonmodule, new Object[]{" X ", "XOX", " X ", Character.valueOf('X'), modfarmat, Character.valueOf('O'), rbplast});
      GameRegistry.addRecipe(modfarmat, new Object[]{"XXX", "X X", "XXX", Character.valueOf('X'), palad});
      GameRegistry.addRecipe(block1, new Object[]{"   ", "XXX", "YYY", Character.valueOf('X'), plastrb1, Character.valueOf('Y'), kovaika});
      GameRegistry.addRecipe(block2, new Object[]{"XXX", "ZGZ", "YYY", Character.valueOf('X'), steklo, Character.valueOf('Y'), kovaika, Character.valueOf('Z'), paladiy, Character.valueOf('G'), plas1});
      GameRegistry.addRecipe(block3, new Object[]{"ZXZ", "XCX", "ZXZ", Character.valueOf('Z'), plas2, Character.valueOf('X'), plas3, Character.valueOf('C'), adplast});
      GameRegistry.addRecipe(plast1, new Object[]{"ZYZ", "YXY", "ZYZ", Character.valueOf('X'), cap2, Character.valueOf('Y'), cap1, Character.valueOf('Z'), plas1});
      GameRegistry.addRecipe(plast2, new Object[]{"ZYZ", "YXY", "ZYZ", Character.valueOf('X'), cap2, Character.valueOf('Y'), cap1, Character.valueOf('Z'), sfera1});
      GameRegistry.addRecipe(encoder, new Object[]{"   ", "XYX", "   ", Character.valueOf('X'), plastrb1, Character.valueOf('Y'), sfera1});
      GameRegistry.addRecipe(decoder, new Object[]{"   ", "XYX", "   ", Character.valueOf('X'), plastrb1, Character.valueOf('Y'), pil});
      GameRegistry.addRecipe(White, new Object[]{"SSS", "SXS", "SSS", Character.valueOf('S'), new ItemStack(351, 1, 15), Character.valueOf('X'), Assembler});
      GameRegistry.addRecipe(Orange, new Object[]{"SSS", "SXS", "SSS", Character.valueOf('S'), new ItemStack(351, 1, 14), Character.valueOf('X'), Assembler});
      GameRegistry.addRecipe(rozoviy, new Object[]{"SSS", "SXS", "SSS", Character.valueOf('S'), new ItemStack(351, 1, 13), Character.valueOf('X'), Assembler});
      GameRegistry.addRecipe(Golyboi, new Object[]{"SSS", "SXS", "SSS", Character.valueOf('S'), new ItemStack(351, 1, 12), Character.valueOf('X'), Assembler});
      GameRegistry.addRecipe(Yellow, new Object[]{"SSS", "SXS", "SSS", Character.valueOf('S'), new ItemStack(351, 1, 11), Character.valueOf('X'), Assembler});
      GameRegistry.addRecipe(LiteGreen, new Object[]{"SSS", "SXS", "SSS", Character.valueOf('S'), new ItemStack(351, 1, 10), Character.valueOf('X'), Assembler});
      GameRegistry.addRecipe(rozoviyrozoviy, new Object[]{"SSS", "SXS", "SSS", Character.valueOf('S'), new ItemStack(351, 1, 9), Character.valueOf('X'), Assembler});
      GameRegistry.addRecipe(Seriy, new Object[]{"SSS", "SXS", "SSS", Character.valueOf('S'), new ItemStack(351, 1, 8), Character.valueOf('X'), Assembler});
      GameRegistry.addRecipe(SvetloSeriy, new Object[]{"SSS", "SXS", "SSS", Character.valueOf('S'), new ItemStack(351, 1, 7), Character.valueOf('X'), Assembler});
      GameRegistry.addRecipe(Biryzoviy, new Object[]{"SSS", "SXS", "SSS", Character.valueOf('S'), new ItemStack(351, 1, 6), Character.valueOf('X'), Assembler});
      GameRegistry.addRecipe(Fiolet, new Object[]{"SSS", "SXS", "SSS", Character.valueOf('S'), new ItemStack(351, 1, 5), Character.valueOf('X'), Assembler});
      GameRegistry.addRecipe(Blue, new Object[]{"SSS", "SXS", "SSS", Character.valueOf('S'), new ItemStack(351, 1, 4), Character.valueOf('X'), Assembler});
      GameRegistry.addRecipe(Kori4, new Object[]{"SSS", "SXS", "SSS", Character.valueOf('S'), new ItemStack(351, 1, 3), Character.valueOf('X'), Assembler});
      GameRegistry.addRecipe(Green, new Object[]{"SSS", "SXS", "SSS", Character.valueOf('S'), new ItemStack(351, 1, 2), Character.valueOf('X'), Assembler});
      GameRegistry.addRecipe(Red, new Object[]{"SSS", "SXS", "SSS", Character.valueOf('S'), new ItemStack(351, 1, 1), Character.valueOf('X'), Assembler});
      GameRegistry.addRecipe(Black, new Object[]{"SSS", "SXS", "SSS", Character.valueOf('S'), new ItemStack(351, 1, 0), Character.valueOf('X'), Assembler});
   }

}
