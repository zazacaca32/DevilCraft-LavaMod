package net.minecraft.client.addon.tco.tiny.a;

import java.util.Iterator;
import net.minecraft.client.addon.tco.tiny.a.BoxModel;
import net.minecraft.client.addon.tco.tiny.a.BoxModelCactusBase;
import net.minecraft.client.addon.tco.tiny.a.Gui10Lollipop;
import org.lwjgl.opengl.GL11;

public class BoxModelPikachu extends BoxModel {

   float rotate = -20.0F;
   float particletick = 0.0F;
   float my = 20.0F;
   int lvl = 0;
   int toplvl = 0;
   int mxx = 0;
   int myy = 0;


   public BoxModelPikachu(Gui10Lollipop base, float x, float y, float width, float height) {
      super(base, x, y, width, height);
   }

   public void mouseClicked(int par1, int par2, int par3) {
      int var10000 = super.base.xSize - par1;
      var10000 = super.base.ySize - par2;
      if(!super.isDead) {
         this.rotate = -20.0F;
         this.my -= 40.0F;
         if(this.my < 20.0F) {
            this.my = 20.0F;
         }

         this.particletick = (float)super.base.ySize - this.my;
         this.mxx = par1;
         this.myy = par2;
      } else if(super.bounds.contains(par1, par2)) {
         super.isDead = false;
         this.rotate = -20.0F;
         this.my = 20.0F;
         this.particletick = 0.0F;
         super.base.addCactus();
      }

   }

   public void isBoundDamage(BoxModel e) {
      if(!super.isDead) {
         super.isDead = super.bounds.intersects(e.bounds);
         if(super.isDead) {
            if(this.lvl > this.toplvl) {
               this.toplvl = this.lvl;
            }

            this.lvl = 0;
         }
      }

   }

   public void tick() {
      if(super.isDead) {
         Iterator var1 = super.base.list.iterator();

         while(var1.hasNext()) {
            BoxModel e = (BoxModel)var1.next();
            if(e instanceof BoxModelCactusBase) {
               e.isDead = true;
            }
         }
      } else {
         this.rotate += 90.0F / this.particletick * 3.5F;
         if(this.rotate >= 60.0F) {
            this.rotate = 60.0F;
         }

         this.my += 3.5F;
         if(this.my > (float)(super.base.ySize - 10)) {
            this.my = (float)(super.base.ySize - 10);
         }
      }

   }

   public void render() {
      float x;
      float scale;
      if(super.isDead) {
         x = (float)(super.base.xSize / 2);
         scale = (float)(super.base.ySize / 2 + 10);
         super.bounds.setBounds((int)x - 30, (int)scale - 15, 60, 40);
         super.render();
         GL11.glTranslatef(x - 5.0F, scale, 0.0F);
         GL11.glRotatef(17.0F, 0.0F, 0.0F, 1.0F);
         float scale1 = 1.5F;
         GL11.glScalef(scale1, scale1 * 0.8F, scale1);
         this.drawTexturedModalRect(0, 0, 0, 0, 44, 44);
      } else {
         x = (float)(super.base.xSize / 2);
         super.bounds.setBounds((int)(x - 10.0F), (int)this.my - 10, 20, 20);
         super.render();
         GL11.glTranslatef(x, this.my, 0.0F);
         GL11.glRotatef(this.rotate, 0.0F, 0.0F, 1.0F);
         scale = 0.5F;
         GL11.glScalef(scale, scale, scale);
         this.drawTexturedModalRect(0, 0, 0, 0, 44, 44);
      }

   }

   public int Inclvl() {
      return ++this.lvl;
   }
}
