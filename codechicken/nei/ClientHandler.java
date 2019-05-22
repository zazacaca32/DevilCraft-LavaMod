package codechicken.nei;

import codechicken.core.ClientUtils;
import codechicken.core.packet.PacketCustom;
import codechicken.nei.HUDAugmenter;
import codechicken.nei.HUDAugmenterDefault;
import codechicken.nei.NEICPH;
import codechicken.nei.NEIClientConfig;
import codechicken.nei.NEIClientUtils;
import codechicken.nei.NEIController;
import codechicken.nei.TMIUninstaller;
import codechicken.nei.api.API;
import codechicken.nei.api.ItemInfo;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

public class ClientHandler implements ITickHandler {

   private static ClientHandler instance;
   private ArrayList SMPmagneticItems = new ArrayList();
   private World lastworld;
   private boolean firstTick = true;
   private boolean mobOverlayHeld = false;
   public int mobSpawnOverlay = 0;
   private boolean overlayKeyHeld = false;
   public int chunkOverlay = 0;


   public void addSMPMagneticItem(int i, World world) {
      WorldClient cworld = (WorldClient)world;
      Entity e = cworld.getEntityByID(i);
      if(e != null && e instanceof EntityItem) {
         this.SMPmagneticItems.add((EntityItem)e);
      }
   }

   private void updateMagnetMode(World world, EntityPlayerSP player) {
      if(NEIClientConfig.getMagnetMode()) {
         float distancexz = 16.0F;
         float distancey = 8.0F;
         double maxspeedxz = 0.5D;
         double maxspeedy = 0.5D;
         double speedxz = 0.05D;
         double speedy = 0.07D;
         Object items;
         if(world.isRemote) {
            items = this.SMPmagneticItems;
         } else {
            items = world.getEntitiesWithinAABB(EntityItem.class, player.boundingBox.expand((double)distancexz, (double)distancey, (double)distancexz));
         }

         Iterator iterator = ((List)items).iterator();

         while(iterator.hasNext()) {
            EntityItem item = (EntityItem)iterator.next();
            if(item.delayBeforeCanPickup <= 0) {
               if(item.isDead && world.isRemote) {
                  iterator.remove();
               }

               if(NEIClientUtils.canItemFitInInventory(player, item.getEntityItem())) {
                  double dx = player.posX - item.posX;
                  double dy = player.posY + (double)player.getEyeHeight() - item.posY;
                  double dz = player.posZ - item.posZ;
                  double absxz = Math.sqrt(dx * dx + dz * dz);
                  double absy = Math.abs(dy);
                  if(absxz <= (double)distancexz) {
                     if(absxz > 1.0D) {
                        dx /= absxz;
                        dz /= absxz;
                     }

                     if(absy > 1.0D) {
                        dy /= absy;
                     }

                     double vx = item.motionX + speedxz * dx;
                     double vy = item.motionY + speedy * dy;
                     double vz = item.motionZ + speedxz * dz;
                     double absvxz = Math.sqrt(vx * vx + vz * vz);
                     double absvy = Math.abs(vy);
                     double rationspeedxz = absvxz / maxspeedxz;
                     if(rationspeedxz > 1.0D) {
                        vx /= rationspeedxz;
                        vz /= rationspeedxz;
                     }

                     double rationspeedy = absvy / maxspeedy;
                     if(rationspeedy > 1.0D) {
                        vy /= rationspeedy;
                     }

                     if(absvxz < 0.2D && absxz < 0.2D && world.isRemote) {
                        item.setDead();
                     }

                     item.setVelocity(vx, vy, vz);
                  }
               }
            }
         }

      }
   }

   public static void load() {
      try {
         TMIUninstaller.deleteTMIUninstaller();
         if(TMIUninstaller.TMIInstalled()) {
            TMIUninstaller.runTMIUninstaller();
            NEIClientUtils.mc().shutdownMinecraftApplet();
         }
      } catch (Exception var1) {
         System.err.println("Error with TMI Uninstaller");
         var1.printStackTrace();
      }

      instance = new ClientHandler();
      PacketCustom.assignHandler("NEI", 0, 255, new NEICPH());
      TickRegistry.registerTickHandler(instance, Side.CLIENT);
      LanguageRegistry.instance().addStringLocalization("entity.SnowMan.name", "Snow Golem");
      API.registerHighlightHandler(new HUDAugmenterDefault(), new ItemInfo.Layout[]{ItemInfo.Layout.HEADER});
   }

   public void tickStart(EnumSet type, Object ... tickData) {
      Minecraft mc = Minecraft.getMinecraft();
      if(type.contains(TickType.CLIENT) && mc.theWorld != null) {
         if(mc.theWorld != this.lastworld) {
            this.onWorldChange(mc.theWorld);
         }

         NEIController.updateUnlimitedItems(mc.thePlayer.inventory);
         NEIController.processCreativeCycling(mc.thePlayer.inventory);
         this.toggleChunkOverlay();
         this.toggleMobOverlay();
         this.updateMagnetMode(mc.theWorld, mc.thePlayer);
      }

      if(type.contains(TickType.CLIENT)) {
         GuiScreen gui = mc.currentScreen;
         if(gui instanceof GuiMainMenu) {
            if(this.firstTick) {
               this.firstTick = false;
               this.onMainMenuInit();
            }

            if(this.lastworld != null) {
               this.lastworld = null;
            }
         }
      }

   }

   private void toggleMobOverlay() {
      if(Keyboard.isKeyDown(NEIClientConfig.getKeyBinding("moboverlay"))) {
         if(!this.mobOverlayHeld) {
            this.mobSpawnOverlay = (this.mobSpawnOverlay + 1) % 2;
         }

         this.mobOverlayHeld = true;
      } else {
         this.mobOverlayHeld = false;
      }

   }

   private void toggleChunkOverlay() {
      if(Keyboard.isKeyDown(NEIClientConfig.getKeyBinding("chunkoverlay"))) {
         if(!this.overlayKeyHeld) {
            this.chunkOverlay = (this.chunkOverlay + 1) % 3;
         }

         this.overlayKeyHeld = true;
      } else {
         this.overlayKeyHeld = false;
      }

   }

   private void onWorldChange(World world) {
      NEIClientConfig.setHasSMPCounterPart(false);
      this.SMPmagneticItems.clear();
      this.chunkOverlay = 0;
      NEIClientConfig.setInternalEnabled(false);
      if(!ClientUtils.isLocal()) {
         NEIClientConfig.loadWorld("remote/" + ClientUtils.getServerIP().replace(':', '~'));
      }
   }

   private void onMainMenuInit() {
      if(NEIClientConfig.getBooleanSetting("ID dump.dump on load")) {
         NEIClientUtils.dumpIDs();
      }

   }

   public void tickEnd(EnumSet type, Object ... tickData) {
      if(type.contains(TickType.RENDER)) {
         HUDAugmenter.renderOverlay();
      }

   }

   public EnumSet ticks() {
      return EnumSet.of(TickType.CLIENT, TickType.CLIENT, TickType.RENDER);
   }

   public String getLabel() {
      return "NEI Client";
   }

   public static ClientHandler instance() {
      return instance;
   }

   public void setWorld(World world) {
      this.lastworld = world;
   }
}
