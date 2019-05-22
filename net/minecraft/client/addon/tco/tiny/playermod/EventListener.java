package net.minecraft.client.addon.tco.tiny.playermod;

import cpw.mods.fml.common.FMLLog;
import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tco.tiny.playermod.Flag;
import net.minecraft.client.addon.tco.tiny.playermod.PlayerLogic;
import net.minecraft.client.addon.tco.tiny.playermod.PlayerMap;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.ClientNickNameRenderEvent;
import net.minecraftforge.event.ForgeSubscribe;
import org.lwjgl.opengl.GL11;

public class EventListener {

   private boolean translCheck = true;
   static long lBegin;


   public EventListener() {
      new PlayerLogic();
   }

   public static void st() {
      lBegin = System.nanoTime();
   }

   public static void en() {
      long lEnd = System.nanoTime();
      long lDelta = lEnd - lBegin;
      FMLLog.getLogger().info("time " + lDelta);
   }

   @ForgeSubscribe
   public void onRenderPlayerName(ClientNickNameRenderEvent event) {
      if(event.par1EntityLiving instanceof EntityPlayer && event.par1EntityLiving != event.renderManager.livingPlayer) {
         EntityPlayer p1 = (EntityPlayer)event.par1EntityLiving;
         PlayerMap pm = PlayerLogic.isMap(p1.getEntityName());
         if(pm == null) {
            return;
         }

         if(pm.getDisplayCount() == 0) {
            return;
         }

         int brightness = 15728880;
         boolean brightMod = true;
         boolean brightDiv = true;
         OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
         float f = 1.6F;
         float f2 = 0.026666671F;
         float rest = 16.0F;
         float healthHeight = 16.0F;
         GL11.glDisable(2896);
         GL11.glPushMatrix();
         GL11.glTranslatef((float)event.par3 + 0.0F, (float)event.par5 + event.par1EntityLiving.height + 0.5F, (float)event.par7);
         GL11.glRotatef(-event.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(event.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
         GL11.glScalef(-f2, f2, f2 /= 2.0F);
         Tessellator t = Tessellator.instance;
         this.translCheck = true;
         Flag[] arr$ = pm.get();
         int len$ = arr$.length;

         for(int i$ = 0; i$ < len$; ++i$) {
            Flag flag = arr$[i$];
            if(flag.ordinal() > 0) {
               Minecraft.getMinecraft().renderEngine.bindTexture("/mods/" + flag.ordinal() + ".png");
               if(this.translCheck) {
                  this.translCheck = false;
                  if(pm.getDisplayCount() == 1) {
                     GL11.glTranslatef(-8.0F, 0.0F, 0.0F);
                  } else {
                     GL11.glTranslatef((8.0F - (float)(pm.getDisplayCount() * 16)) / 2.0F, 0.0F, 0.0F);
                  }
               } else {
                  GL11.glTranslatef(16.0F, 0.0F, 0.0F);
               }

               t.startDrawingQuads();
               t.addVertexWithUV(0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
               t.addVertexWithUV(16.0D, 0.0D, 0.0D, 1.0D, 0.0D);
               t.addVertexWithUV(16.0D, 16.0D, 0.0D, 1.0D, 1.0D);
               t.addVertexWithUV(0.0D, 16.0D, 0.0D, 0.0D, 1.0D);
               t.draw();
            }
         }

         GL11.glPopMatrix();
         GL11.glEnable(2896);
      }

   }
}
