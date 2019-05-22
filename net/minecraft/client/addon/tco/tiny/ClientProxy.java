package net.minecraft.client.addon.tco.tiny;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.lavablock.Gui.GuiArmorStand;
import net.minecraft.client.addon.lavablock.Tile.ArmorStandTile;
import net.minecraft.client.addon.tchestplate.donate.api.Manager;
import net.minecraft.client.addon.tco.tiny.CommonProxy;
import net.minecraft.client.addon.tco.tiny.GuiTiny;
import net.minecraft.client.addon.tco.tiny.IronChestRenderHelper;
import net.minecraft.client.addon.tco.tiny.TileEntityTiny;
import net.minecraft.client.addon.tco.tiny.TileRender;
import net.minecraft.client.addon.tco.tiny.Tiny;
import net.minecraft.client.addon.tco.tiny.a.Gui10Lollipop;
import net.minecraft.client.addon.tco.tiny.a.TinyKeyHandler;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockClearItem;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockColorer;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockCompressionMatter;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockDarkEnergyControler;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockDarkEnergyHarvest;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockDarkMatterFabricator;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockDeadPlayer;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockDecor;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockGeneratorLava;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockInfo;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockLavaAnvil;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockMatterFabricator;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockPatternModule;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockPetDesk;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockPresentNewYear;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockPresentPirat;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockPressBow;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockPressHammer;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockPressOther;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockRepeaterArmor;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockSmurfChest;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockTrade;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockTrader;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockTraderCoin;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockTraderController;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockTraderControllerMonitor;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockTurning;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockUpgradeArmor;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockUpgradeUltima;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityBlockWeaponColorer;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityH1LVL;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileEntityPressSpear;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileGetExPult;
import net.minecraft.client.addon.tco.tiny.blocks.TileEntity.TileGetExperience;
import net.minecraft.client.addon.tco.tiny.blocks.gui.GuiClearItem;
import net.minecraft.client.addon.tco.tiny.blocks.gui.GuiColorer;
import net.minecraft.client.addon.tco.tiny.blocks.gui.GuiCompressionMatter;
import net.minecraft.client.addon.tco.tiny.blocks.gui.GuiDarkController;
import net.minecraft.client.addon.tco.tiny.blocks.gui.GuiDarkHarvester;
import net.minecraft.client.addon.tco.tiny.blocks.gui.GuiDarkMatterFabricator;
import net.minecraft.client.addon.tco.tiny.blocks.gui.GuiGeneratorLava;
import net.minecraft.client.addon.tco.tiny.blocks.gui.GuiGetExPult;
import net.minecraft.client.addon.tco.tiny.blocks.gui.GuiGetExperience;
import net.minecraft.client.addon.tco.tiny.blocks.gui.GuiH1LVL;
import net.minecraft.client.addon.tco.tiny.blocks.gui.GuiInfo;
import net.minecraft.client.addon.tco.tiny.blocks.gui.GuiInputFurnace;
import net.minecraft.client.addon.tco.tiny.blocks.gui.GuiLavaAnvil;
import net.minecraft.client.addon.tco.tiny.blocks.gui.GuiMatterFabricator;
import net.minecraft.client.addon.tco.tiny.blocks.gui.GuiPatternModule;
import net.minecraft.client.addon.tco.tiny.blocks.gui.GuiPetDesk;
import net.minecraft.client.addon.tco.tiny.blocks.gui.GuiPressBow;
import net.minecraft.client.addon.tco.tiny.blocks.gui.GuiPressHammer;
import net.minecraft.client.addon.tco.tiny.blocks.gui.GuiPressOther;
import net.minecraft.client.addon.tco.tiny.blocks.gui.GuiRepeaterArmor;
import net.minecraft.client.addon.tco.tiny.blocks.gui.GuiSmurfChest;
import net.minecraft.client.addon.tco.tiny.blocks.gui.GuiSpear;
import net.minecraft.client.addon.tco.tiny.blocks.gui.GuiTrade;
import net.minecraft.client.addon.tco.tiny.blocks.gui.GuiTraderClose;
import net.minecraft.client.addon.tco.tiny.blocks.gui.GuiTraderCloseCoin;
import net.minecraft.client.addon.tco.tiny.blocks.gui.GuiTraderCloseInfo;
import net.minecraft.client.addon.tco.tiny.blocks.gui.GuiTraderController;
import net.minecraft.client.addon.tco.tiny.blocks.gui.GuiTraderControllerMonitor;
import net.minecraft.client.addon.tco.tiny.blocks.gui.GuiTraderOpen;
import net.minecraft.client.addon.tco.tiny.blocks.gui.GuiTraderOpenCoin;
import net.minecraft.client.addon.tco.tiny.blocks.gui.GuiUpgradeArmor;
import net.minecraft.client.addon.tco.tiny.blocks.gui.GuiUpgradeUltima;
import net.minecraft.client.addon.tco.tiny.blocks.gui.GuiWeaponColorer;
import net.minecraft.client.addon.tco.tiny.blocks.models.ModelBlockClearItem;
import net.minecraft.client.addon.tco.tiny.blocks.models.ModelBlockColorer;
import net.minecraft.client.addon.tco.tiny.blocks.models.ModelBlockCompressionMatter;
import net.minecraft.client.addon.tco.tiny.blocks.models.ModelBlockDarkEnergyControler;
import net.minecraft.client.addon.tco.tiny.blocks.models.ModelBlockDarkMatterFabricator;
import net.minecraft.client.addon.tco.tiny.blocks.models.ModelBlockDeadPlayer;
import net.minecraft.client.addon.tco.tiny.blocks.models.ModelBlockDecorContainer;
import net.minecraft.client.addon.tco.tiny.blocks.models.ModelBlockLavaAnvil;
import net.minecraft.client.addon.tco.tiny.blocks.models.ModelBlockMatterFabricator;
import net.minecraft.client.addon.tco.tiny.blocks.models.ModelBlockPatternModule;
import net.minecraft.client.addon.tco.tiny.blocks.models.ModelBlockPetDesk;
import net.minecraft.client.addon.tco.tiny.blocks.models.ModelBlockPresent;
import net.minecraft.client.addon.tco.tiny.blocks.models.ModelBlockPresentPirat;
import net.minecraft.client.addon.tco.tiny.blocks.models.ModelBlockPressHammer;
import net.minecraft.client.addon.tco.tiny.blocks.models.ModelBlockRepeaterArmor;
import net.minecraft.client.addon.tco.tiny.blocks.models.ModelBlockSmurfChest;
import net.minecraft.client.addon.tco.tiny.blocks.models.ModelBlockTrade;
import net.minecraft.client.addon.tco.tiny.blocks.models.ModelBlockTurning;
import net.minecraft.client.addon.tco.tiny.blocks.models.ModelBlockUpgradeArmor;
import net.minecraft.client.addon.tco.tiny.blocks.models.ModelBlockUpgradeUltima;
import net.minecraft.client.addon.tco.tiny.blocks.models.ModelPressSpear;
import net.minecraft.client.addon.tco.tiny.blocks.models.Render.RenderBlockCable;
import net.minecraft.client.addon.tco.tiny.blocks.models.Render.RenderBlockSide;
import net.minecraft.client.addon.tco.tiny.blocks.models.Render.RenderBlockSideController;
import net.minecraft.client.addon.tco.tiny.blocks.models.Render.RenderBlockSideControllerMonitor;
import net.minecraft.client.addon.tco.tiny.blocks.models.Render.RenderBlockStaticBase;
import net.minecraft.client.addon.tco.tiny.blocks.models.Render.RenderTrader;
import net.minecraft.client.addon.tco.tiny.blocks.models.RenderItem.ItemBlockRender;
import net.minecraft.client.addon.tco.tiny.entity.EntityMan;
import net.minecraft.client.addon.tco.tiny.entity.EntityManTrader;
import net.minecraft.client.addon.tco.tiny.entity.EntityManTraderDedMoroz;
import net.minecraft.client.addon.tco.tiny.entity.NpcMerchant;
import net.minecraft.client.addon.tco.tiny.entity.RenderMan;
import net.minecraft.client.addon.tco.tiny.entity.gui.GuiMerchant;
import net.minecraft.client.addon.tco.tiny.entity.gui.GuiMerchantAdm;
import net.minecraft.client.addon.tco.tiny.fx.FXWisp;
import net.minecraft.client.addon.tco.tiny.gui.GuiMob;
import net.minecraft.client.addon.tco.tiny.items.models.ModelBagPirat;
import net.minecraft.client.addon.tco.tiny.items.models.ModelBagSanta;
import net.minecraft.client.addon.tco.tiny.items.models.ModelBukvarik;
import net.minecraft.client.addon.tco.tiny.items.models.ModelLavapad;
import net.minecraft.client.addon.tco.tiny.items.models.ModelPetDesk;
import net.minecraft.client.addon.tco.tiny.items.renders.ItemRenderLavaPad;
import net.minecraft.client.addon.tco.tiny.items.renders.ItemRenderPriz;
import net.minecraft.client.addon.tco.tiny.playermod.EventListener;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.ChestItemRenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.texturepacks.ITexturePack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {

   private Map renders = new HashMap();
   public static final RenderItem itemRenderer = new RenderItem();


   public void preinit(FMLPreInitializationEvent event) {}

   public void init(FMLInitializationEvent event) {}

   public void postinit(FMLPostInitializationEvent event) {}

   public void registerTileEntitySpecialRenderer() {
      ArrayList listModel = new ArrayList();
      String pathrender = "/mods/renderblock/";
      ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTiny.class, new TileRender());
      RenderingRegistry.registerEntityRenderingHandler(EntityMan.class, new RenderMan(new ModelBiped(), 0.3F));
      RenderingRegistry.registerEntityRenderingHandler(EntityManTrader.class, new RenderMan(new ModelBiped(), 0.3F));
      RenderingRegistry.registerEntityRenderingHandler(EntityManTraderDedMoroz.class, new RenderMan(new ModelBiped(), 0.3F));
      listModel.add(new ModelBlockPatternModule());
      listModel.trimToSize();
      ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBlockPatternModule.class, new RenderBlockStaticBase(new ArrayList(listModel), 0, new String[]{"/mods/provider/ual/patterner.png"}));
      MinecraftForgeClient.registerItemRenderer(Tiny.car.blockID, new ItemBlockRender(new ArrayList(listModel), new String[]{"/mods/provider/ual/patterner.png"}));
      listModel.clear();
      listModel.add(new ModelBlockSmurfChest());
      listModel.trimToSize();
      ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBlockSmurfChest.class, new RenderBlockStaticBase(new ArrayList(listModel), 1, new String[]{pathrender + "blocksmurf.png"}));
      MinecraftForgeClient.registerItemRenderer(Tiny.blockSmurfChest.blockID, new ItemBlockRender(new ArrayList(listModel), new String[]{pathrender + "blocksmurf.png"}));
      listModel.clear();
      listModel.add(new ModelBlockPresent());
      listModel.trimToSize();
      ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBlockPresentNewYear.class, new RenderBlockStaticBase(new ArrayList(listModel), 0, new String[]{"/mods/renderblock/present.png"}));
      MinecraftForgeClient.registerItemRenderer(Tiny.blockPresent.blockID, new ItemBlockRender(new ArrayList(listModel), new String[]{"/mods/renderblock/present.png"}));
      listModel.clear();
      listModel.add(new ModelBlockPresentPirat());
      listModel.trimToSize();
      ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBlockPresentPirat.class, new RenderBlockStaticBase(new ArrayList(listModel), 0, new String[]{"/mods/renderblock/presentpirat.png"}));
      MinecraftForgeClient.registerItemRenderer(Tiny.blockPresentPirat.blockID, new ItemBlockRender(new ArrayList(listModel), new String[]{"/mods/renderblock/presentpirat.png"}));
      listModel.clear();
      listModel.add(new ModelBlockDeadPlayer());
      listModel.trimToSize();
      ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBlockDeadPlayer.class, new RenderBlockStaticBase(new ArrayList(listModel), 0, new String[]{"/mods/renderblock/blockdeadplayer.png"}));
      MinecraftForgeClient.registerItemRenderer(Tiny.blockDeadPlayer.blockID, new ItemBlockRender(new ArrayList(listModel), new String[]{"/mods/renderblock/blockdeadplayer.png"}));
      listModel.clear();
      listModel.add(new ModelBlockTurning());
      listModel.trimToSize();
      ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBlockTurning.class, new RenderBlockStaticBase(new ArrayList(listModel), 0, new String[]{"/mods/renderblock/blockturning.png"}));
      MinecraftForgeClient.registerItemRenderer(Tiny.blockTurning.blockID, new ItemBlockRender(new ArrayList(listModel), new String[]{"/mods/renderblock/blockturning.png"}));
      listModel.clear();
      listModel.add(new ModelBlockRepeaterArmor());
      listModel.trimToSize();
      ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBlockRepeaterArmor.class, new RenderBlockStaticBase(new ArrayList(listModel), 0, new String[]{"/mods/renderblock/blockreparmor.png"}));
      MinecraftForgeClient.registerItemRenderer(Tiny.blockRepeaterArmor.blockID, new ItemBlockRender(new ArrayList(listModel), new String[]{"/mods/renderblock/blockreparmor.png"}));
      listModel.clear();
      listModel.add(new ModelBlockMatterFabricator());
      listModel.trimToSize();
      ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBlockMatterFabricator.class, new RenderBlockStaticBase(new ArrayList(listModel), 0, new String[]{"/mods/renderblock/blockmatter.png"}));
      MinecraftForgeClient.registerItemRenderer(Tiny.blockMatterFabricator.blockID, new ItemBlockRender(new ArrayList(listModel), new String[]{"/mods/renderblock/blockmatter.png"}));
      listModel.clear();
      listModel.add(new ModelPressSpear());
      listModel.trimToSize();
      ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPressSpear.class, new RenderBlockStaticBase(new ArrayList(listModel), 0, new String[]{"/mods/lavamobs/textures/blocks/PressBow.png"}));
      MinecraftForgeClient.registerItemRenderer(Tiny.PressSpear.blockID, new ItemBlockRender(new ArrayList(listModel), new String[]{"/mods/lavamobs/textures/blocks/PressBow.png"}));
      listModel.clear();
      listModel.add(new ModelBlockCompressionMatter());
      listModel.trimToSize();
      ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBlockCompressionMatter.class, new RenderBlockStaticBase(new ArrayList(listModel), 0, new String[]{"/mods/renderblock/blockcompressmatter.png"}));
      MinecraftForgeClient.registerItemRenderer(Tiny.blockCompressionMatter.blockID, new ItemBlockRender(new ArrayList(listModel), new String[]{"/mods/renderblock/blockcompressmatter.png"}));
      listModel.clear();
      listModel.add(new ModelBlockTrade());
      listModel.trimToSize();
      ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBlockTrade.class, new RenderBlockStaticBase(new ArrayList(listModel), 0, new String[]{"/mods/renderblock/blocktrade.png"}));
      MinecraftForgeClient.registerItemRenderer(Tiny.blockTrade.blockID, new ItemBlockRender(new ArrayList(listModel), new String[]{"/mods/renderblock/blocktrade.png"}));
      listModel.clear();
      listModel.add(new ModelBlockDecorContainer(0));
      listModel.add(new ModelBlockDecorContainer(1));
      listModel.add(new ModelBlockDecorContainer(2));
      listModel.trimToSize();
      String s = "/mods/tco/textures/items/models/";
      ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBlockDecor.class, new RenderBlockStaticBase(new ArrayList(listModel), 0, new String[]{"/mods/tco/textures/items/models/kn.png", "/mods/tco/textures/items/models/ks.png", "/mods/tco/textures/items/models/kv.png"}));
      MinecraftForgeClient.registerItemRenderer(Tiny.blockMultiDecor.blockID, new ItemBlockRender(new ArrayList(listModel), new String[]{"/mods/tco/textures/items/models/kn.png", "/mods/tco/textures/items/models/ks.png", "/mods/tco/textures/items/models/kv.png"}));
      listModel.clear();
      listModel.add(new ModelBlockUpgradeArmor());
      listModel.trimToSize();
      ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBlockUpgradeArmor.class, new RenderBlockStaticBase(new ArrayList(listModel), 0, new String[]{"/mods/renderblock/blockupgradearm.png"}));
      MinecraftForgeClient.registerItemRenderer(Tiny.blockUpgradeArmor.blockID, new ItemBlockRender(new ArrayList(listModel), new String[]{"/mods/renderblock/blockupgradearm.png"}));
      listModel.clear();
      listModel.add(new ModelBlockDarkEnergyControler());
      listModel.trimToSize();
      ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBlockDarkEnergyControler.class, new RenderBlockStaticBase(new ArrayList(listModel), 0, new String[]{"/mods/tco/textures/blocks/models/darkcontrol.png"}));
      MinecraftForgeClient.registerItemRenderer(Tiny.blockDarkEnergyControler.blockID, new ItemBlockRender(new ArrayList(listModel), new String[]{"/mods/tco/textures/blocks/models/darkcontrol.png"}));
      listModel.clear();
      listModel.add(new ModelBlockDarkMatterFabricator());
      listModel.trimToSize();
      ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBlockDarkMatterFabricator.class, new RenderBlockStaticBase(new ArrayList(listModel), 0, new String[]{"/mods/tco/textures/blocks/models/darkmatter.png"}));
      MinecraftForgeClient.registerItemRenderer(Tiny.blockDarkMatterFabricator.blockID, new ItemBlockRender(new ArrayList(listModel), new String[]{"/mods/tco/textures/blocks/models/darkmatter.png"}));
      listModel.clear();
      listModel.add(new ModelBlockClearItem());
      listModel.trimToSize();
      ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBlockClearItem.class, new RenderBlockStaticBase(new ArrayList(listModel), 0, new String[]{"/mods/provider/ual/cib.png"}));
      MinecraftForgeClient.registerItemRenderer(Tiny.ClearItemBlock.blockID, new ItemBlockRender(new ArrayList(listModel), new String[]{"/mods/provider/ual/cib.png"}));
      listModel.clear();
      listModel.add(new ModelBlockPetDesk());
      listModel.trimToSize();
      ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBlockPetDesk.class, new RenderBlockStaticBase(new ArrayList(listModel), 0, new String[]{"/mods/tco/textures/blocks/models/petdesk.png"}));
      MinecraftForgeClient.registerItemRenderer(Tiny.blockPetDesk.blockID, new ItemBlockRender(new ArrayList(listModel), new String[]{"/mods/tco/textures/blocks/models/petdesk.png"}));
      listModel.clear();
      listModel.add(new ModelBlockLavaAnvil());
      listModel.trimToSize();
      ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBlockLavaAnvil.class, new RenderBlockStaticBase(new ArrayList(listModel), 0, new String[]{"/mods/tco/textures/blocks/models/lavaanvil.png"}));
      MinecraftForgeClient.registerItemRenderer(Tiny.blockLavaAnvil.blockID, new ItemBlockRender(new ArrayList(listModel), new String[]{"/mods/tco/textures/blocks/models/lavaanvil.png"}));
      listModel.clear();
      listModel.add(new ModelBlockPressHammer());
      listModel.trimToSize();
      ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBlockPressHammer.class, new RenderBlockStaticBase(new ArrayList(listModel), 0, new String[]{"/mods/renderblock/blockpresshammer.png"}));
      MinecraftForgeClient.registerItemRenderer(Tiny.blockPressHammer.blockID, new ItemBlockRender(new ArrayList(listModel), new String[]{"/mods/renderblock/blockpresshammer.png"}));
      listModel.clear();
      ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBlockTrader.class, new RenderTrader());
      ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBlockTraderCoin.class, new RenderTrader());
      MinecraftForgeClient.registerItemRenderer(Tiny.BagPirat.itemID, new ItemRenderPriz(new ModelBase[]{new ModelBagPirat(), new ModelBukvarik(), new ModelBagSanta(), new ModelPetDesk()}, new String[]{"/mods/tco/textures/items/models/bagpirat.png", "/mods/tco/textures/items/models/bukvarik.png", "/mods/tco/textures/items/models/bagsanta.png", "/mods/tco/textures/items/models/petdesk.png"}));
      MinecraftForgeClient.registerItemRenderer(Tiny.lavapad.itemID, new ItemRenderLavaPad(new ModelBase[]{new ModelLavapad()}, new String[]{"/mods/tco/textures/items/models/lavapad.png"}));
      RenderingRegistry.registerBlockHandler(new RenderBlockCable());
      RenderingRegistry.registerBlockHandler(new RenderBlockSide());
      RenderingRegistry.registerBlockHandler(new RenderBlockSideController());
      RenderingRegistry.registerBlockHandler(new RenderBlockSideControllerMonitor());
      this.renders.put("cable", Integer.valueOf(RenderBlockCable.renderId));
      this.renders.put("blockside", Integer.valueOf(RenderBlockSide.renderId));
      this.renders.put("blocksidecontroller", Integer.valueOf(RenderBlockSideController.renderId));
      this.renders.put("blocksidecontrollermonitor", Integer.valueOf(RenderBlockSideControllerMonitor.renderId));
      MinecraftForge.EVENT_BUS.register(new EventListener());
      listModel.clear();
      listModel.add(new ModelBlockUpgradeUltima());
      listModel.trimToSize();
      ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBlockUpgradeUltima.class, new RenderBlockStaticBase(new ArrayList(listModel), 1, new String[]{"/mods/provider/ual/ultima.png"}));
      MinecraftForgeClient.registerItemRenderer(Tiny.blockUpgradeUltima.blockID, new ItemBlockRender(new ArrayList(listModel), new String[]{"/mods/provider/ual/ultima.png"}));
   }

   public int getRenderId(String name) {
      return ((Integer)this.renders.get(name)).intValue();
   }

   public void wispFX(World worldObj, double posX, double posY, double posZ, float f, float g, float h, float i) {
      FXWisp ef = new FXWisp(worldObj, posX, posY, posZ, f, g, h, i);
      ef.setGravity(0.02F);
      ModLoader.getMinecraftInstance().effectRenderer.addEffect(ef);
   }

   public void wispFX2(World worldObj, double posX, double posY, double posZ, float size, int type, boolean shrink, float gravity) {
      FXWisp ef = new FXWisp(worldObj, posX, posY, posZ, size, type);
      ef.setGravity(gravity);
      ef.shrink = shrink;
      ModLoader.getMinecraftInstance().effectRenderer.addEffect(ef);
   }

   public void wispFX3(World worldObj, double posX, double posY, double posZ, double posX2, double posY2, double posZ2, float size, int type, boolean shrink, float gravity) {
      FXWisp ef = new FXWisp(worldObj, posX, posY, posZ, posX2, posY2, posZ2, size, type);
      ef.setGravity(gravity);
      ef.shrink = shrink;
      ModLoader.getMinecraftInstance().effectRenderer.addEffect(ef);
   }

   public World getClientWorld() {
      return FMLClientHandler.instance().getClient().theWorld;
   }

   public void registerRenderInformation() {
      ChestItemRenderHelper.instance = new IronChestRenderHelper();
   }

   @SideOnly(Side.CLIENT)
   public static void renderItemStack(FontRenderer fr, ItemStack it, int x, int y) {
      FontRenderer font = null;
      if(it != null) {
         font = it.getItem().getFontRenderer(it);
      }

      if(font == null) {
         font = fr;
      }

      itemRenderer.renderItemAndEffectIntoGUI(font, Minecraft.getMinecraft().renderEngine, it, x, y);
   }

   public void initKeybind() {
      KeyBindingRegistry.registerKeyBinding(new TinyKeyHandler());
   }

   public Minecraft getMinecraftInstance() {
      return FMLClientHandler.instance().getClient();
   }

   public void openGUI(EntityPlayer player, ItemStack stack) {
      FMLClientHandler.instance().displayGuiScreen(player, new Gui10Lollipop(stack));
   }

   public ITexturePack getSelectedTexturePack() {
      return this.getMinecraftInstance().renderEngine.texturePack.getSelectedTexturePack();
   }

   public void stopTessellating() {
      if(Tessellator.instance.isDrawing) {
         Tessellator.instance.draw();
      }

   }

   public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
      TileEntity tileEntity;
      switch(ID) {
      case 502:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity != null && tileEntity instanceof ArmorStandTile) {
            return new GuiArmorStand((ArmorStandTile)tileEntity, player);
         }
         break;
      case 601:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileEntityPressSpear) {
            return new GuiSpear(player.inventory, (TileEntityPressSpear)tileEntity);
         }
         break;
      case 901:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileEntityBlockClearItem) {
            return new GuiClearItem(player.inventory, (TileEntityBlockClearItem)tileEntity);
         }
         break;
      case 8001:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileEntityBlockWeaponColorer) {
            return new GuiWeaponColorer(player.inventory, (TileEntityBlockWeaponColorer)tileEntity);
         }
         break;
      case 10000:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity != null && tileEntity instanceof TileEntityTiny) {
            return new GuiTiny((TileEntityTiny)tileEntity);
         }
         break;
      case 10001:
         return new GuiMob(player, x, y);
      case 10002:
      case 10003:
      case 10004:
      case 10005:
      case 10006:
      case 10007:
      case 10008:
      case 10009:
      default:
         break;
      case 10010:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileEntityBlockTurning) {
            return new GuiInputFurnace(player.inventory, (TileEntityBlockTurning)tileEntity);
         }
         break;
      case 10011:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileEntityBlockRepeaterArmor) {
            return new GuiRepeaterArmor(player.inventory, (TileEntityBlockRepeaterArmor)tileEntity);
         }
         break;
      case 10012:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileEntityBlockMatterFabricator) {
            return new GuiMatterFabricator(player.inventory, (TileEntityBlockMatterFabricator)tileEntity);
         }
         break;
      case 10013:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileEntityBlockCompressionMatter) {
            return new GuiCompressionMatter(player.inventory, (TileEntityBlockCompressionMatter)tileEntity);
         }
         break;
      case 10014:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileEntityBlockTrade) {
            return new GuiTrade(player.inventory, (TileEntityBlockTrade)tileEntity);
         }
         break;
      case 10015:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileEntityBlockUpgradeArmor) {
            return new GuiUpgradeArmor(player.inventory, (TileEntityBlockUpgradeArmor)tileEntity);
         }
         break;
      case 10016:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileEntityBlockDarkEnergyControler) {
            return new GuiDarkController(player.inventory, (TileEntityBlockDarkEnergyControler)tileEntity);
         }
         break;
      case 10017:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileEntityBlockDarkEnergyHarvest) {
            return new GuiDarkHarvester(player.inventory, (TileEntityBlockDarkEnergyHarvest)tileEntity);
         }
         break;
      case 10018:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileEntityBlockDarkMatterFabricator) {
            return new GuiDarkMatterFabricator(player.inventory, (TileEntityBlockDarkMatterFabricator)tileEntity);
         }
         break;
      case 10019:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileEntityBlockPetDesk) {
            return new GuiPetDesk(player.inventory, (TileEntityBlockPetDesk)tileEntity);
         }
         break;
      case 10020:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileEntityBlockTrader) {
            return new GuiTraderOpen(player.inventory, (TileEntityBlockTrader)tileEntity);
         }
         break;
      case 10021:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileEntityBlockTrader) {
            return new GuiTraderClose(player.inventory, (TileEntityBlockTrader)tileEntity);
         }
         break;
      case 10022:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileEntityBlockTraderController) {
            return new GuiTraderController(player.inventory, (TileEntityBlockTraderController)tileEntity);
         }
         break;
      case 10023:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileEntityBlockLavaAnvil) {
            return new GuiLavaAnvil(player.inventory, (TileEntityBlockLavaAnvil)tileEntity, Manager.getDonateUser(player));
         }
         break;
      case 10024:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileEntityBlockTraderControllerMonitor) {
            return new GuiTraderControllerMonitor(player.inventory, (TileEntityBlockTraderControllerMonitor)tileEntity);
         }
         break;
      case 10025:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileEntityBlockTraderCoin) {
            return new GuiTraderOpenCoin(player.inventory, (TileEntityBlockTraderCoin)tileEntity);
         }
         break;
      case 10026:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileEntityBlockTraderCoin) {
            return new GuiTraderCloseCoin(player.inventory, (TileEntityBlockTraderCoin)tileEntity);
         }
         break;
      case 10027:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileEntityBlockPressHammer) {
            return new GuiPressHammer(player.inventory, (TileEntityBlockPressHammer)tileEntity);
         }
         break;
      case 10028:
         return new GuiMerchant(player.inventory, new NpcMerchant(player), world, "Бородач");
      case 10029:
         return new GuiMerchantAdm(player.inventory, new NpcMerchant(player), world, "Бородач");
      case 10030:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileEntityBlockTrader) {
            return new GuiTraderCloseInfo(player.inventory, (TileEntityBlockTrader)tileEntity);
         }
         break;
      case 10031:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileEntityBlockSmurfChest) {
            return new GuiSmurfChest(player.inventory, (TileEntityBlockSmurfChest)tileEntity);
         }
         break;
      case 10032:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileEntityBlockUpgradeUltima) {
            return new GuiUpgradeUltima(player.inventory, (TileEntityBlockUpgradeUltima)tileEntity);
         }
         break;
      case 10050:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileEntityBlockInfo) {
            return new GuiInfo(player.inventory, (TileEntityBlockInfo)tileEntity);
         }
         break;
      case 10075:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileEntityBlockColorer) {
            return new GuiColorer(player.inventory, (TileEntityBlockColorer)tileEntity);
         }
         break;
      case 10099:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity != null && tileEntity instanceof TileGetExperience) {
            return new GuiGetExperience((TileGetExperience)tileEntity, player);
         }
         break;
      case 10100:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity != null && tileEntity instanceof TileGetExPult) {
            return new GuiGetExPult((TileGetExPult)tileEntity, player);
         }
         break;
      case 10101:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileEntityBlockPressOther) {
            return new GuiPressOther(player.inventory, (TileEntityBlockPressOther)tileEntity);
         }
         break;
      case 10102:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileEntityBlockPressBow) {
            return new GuiPressBow(player.inventory, (TileEntityBlockPressBow)tileEntity, player);
         }
         break;
      case 10996:
         return new GuiMerchant(player.inventory, new NpcMerchant(player), world, "Дед Мороз");
      case 11001:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileEntityH1LVL) {
            return new GuiH1LVL(player.inventory, (TileEntityH1LVL)tileEntity);
         }
         break;
      case 11002:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileEntityBlockGeneratorLava) {
            return new GuiGeneratorLava(player.inventory, (TileEntityBlockGeneratorLava)tileEntity);
         }
         break;
      case 228228:
         tileEntity = world.getBlockTileEntity(x, y, z);
         if(tileEntity instanceof TileEntityBlockPatternModule) {
            return new GuiPatternModule(player.inventory, (TileEntityBlockPatternModule)tileEntity);
         }
      }

      return null;
   }

}
