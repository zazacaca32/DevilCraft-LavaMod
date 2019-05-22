package net.minecraft.client.addon.tco.tiny.blocks.models.RenderItem;

import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;

class ItemBlockRender$1 {

   static final int[] $SwitchMap$net$minecraftforge$client$IItemRenderer$ItemRenderType;
   static final int[] $SwitchMap$net$minecraftforge$client$IItemRenderer$ItemRendererHelper = new int[ItemRendererHelper.values().length];


   static {
      try {
         $SwitchMap$net$minecraftforge$client$IItemRenderer$ItemRendererHelper[ItemRendererHelper.ENTITY_ROTATION.ordinal()] = 1;
      } catch (NoSuchFieldError var8) {
         ;
      }

      try {
         $SwitchMap$net$minecraftforge$client$IItemRenderer$ItemRendererHelper[ItemRendererHelper.ENTITY_BOBBING.ordinal()] = 2;
      } catch (NoSuchFieldError var7) {
         ;
      }

      try {
         $SwitchMap$net$minecraftforge$client$IItemRenderer$ItemRendererHelper[ItemRendererHelper.EQUIPPED_BLOCK.ordinal()] = 3;
      } catch (NoSuchFieldError var6) {
         ;
      }

      try {
         $SwitchMap$net$minecraftforge$client$IItemRenderer$ItemRendererHelper[ItemRendererHelper.BLOCK_3D.ordinal()] = 4;
      } catch (NoSuchFieldError var5) {
         ;
      }

      try {
         $SwitchMap$net$minecraftforge$client$IItemRenderer$ItemRendererHelper[ItemRendererHelper.INVENTORY_BLOCK.ordinal()] = 5;
      } catch (NoSuchFieldError var4) {
         ;
      }

      $SwitchMap$net$minecraftforge$client$IItemRenderer$ItemRenderType = new int[ItemRenderType.values().length];

      try {
         $SwitchMap$net$minecraftforge$client$IItemRenderer$ItemRenderType[ItemRenderType.EQUIPPED.ordinal()] = 1;
      } catch (NoSuchFieldError var3) {
         ;
      }

      try {
         $SwitchMap$net$minecraftforge$client$IItemRenderer$ItemRenderType[ItemRenderType.INVENTORY.ordinal()] = 2;
      } catch (NoSuchFieldError var2) {
         ;
      }

      try {
         $SwitchMap$net$minecraftforge$client$IItemRenderer$ItemRenderType[ItemRenderType.ENTITY.ordinal()] = 3;
      } catch (NoSuchFieldError var1) {
         ;
      }

   }
}
