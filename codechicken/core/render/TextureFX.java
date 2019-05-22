package codechicken.core.render;

import codechicken.core.render.SpriteSheetManager;
import codechicken.core.render.TextureSpecial;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;

@SideOnly(Side.CLIENT)
public class TextureFX {

   public byte[] imageData;
   public int tileSizeBase = 16;
   public int tileSizeSquare = 256;
   public int tileSizeMask = 15;
   public int tileSizeSquareMask = 255;
   public boolean anaglyphEnabled;
   public TextureSpecial texture;


   public TextureFX(int spriteIndex, SpriteSheetManager.SpriteSheet sheet) {
      this.texture = sheet.bindTextureFX(spriteIndex, this);
   }

   public TextureFX(int size, String name) {
      this.texture = (new TextureSpecial(name)).blank(size).selfRegister().addTextureFX(this);
   }

   public TextureFX setAtlas(int index) {
      this.texture.atlasIndex = index;
      return this;
   }

   public void setup() {
      this.imageData = new byte[this.tileSizeSquare << 2];
   }

   public void onTextureDimensionsUpdate(int width, int height) {
      if(width != height) {
         throw new IllegalArgumentException("Non-Square textureFX not supported (" + width + ":" + height + ")");
      } else {
         this.tileSizeBase = width;
         this.tileSizeSquare = this.tileSizeBase * this.tileSizeBase;
         this.tileSizeMask = this.tileSizeBase - 1;
         this.tileSizeSquareMask = this.tileSizeSquare - 1;
         this.setup();
      }
   }

   public void update() {
      this.anaglyphEnabled = Minecraft.getMinecraft().gameSettings.anaglyph;
      this.onTick();
   }

   public void onTick() {}

   public boolean changed() {
      return true;
   }
}
