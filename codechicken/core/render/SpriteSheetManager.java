package codechicken.core.render;

import codechicken.core.render.TextureFX;
import codechicken.core.render.TextureSpecial;
import codechicken.core.render.TextureUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.client.renderer.texture.Texture;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.Icon;
import org.lwjgl.util.Dimension;

public class SpriteSheetManager {

   private static HashMap spriteSheets = new HashMap();


   public static SpriteSheetManager.SpriteSheet getSheet(String textureFile) {
      return getSheet(16, 16, textureFile);
   }

   public static SpriteSheetManager.SpriteSheet getSheet(int tilesX, int tilesY, String textureFile) {
      SpriteSheetManager.SpriteSheet sheet = (SpriteSheetManager.SpriteSheet)spriteSheets.get(textureFile);
      if(sheet == null) {
         spriteSheets.put(textureFile, sheet = new SpriteSheetManager.SpriteSheet(tilesX, tilesY, textureFile, (SpriteSheetManager.SpriteSheet)null));
      }

      return sheet;
   }

   @SideOnly(Side.CLIENT)
   public static class SpriteSheet implements TextureUtils.IIconRegister {

      private int tilesX;
      private int tilesY;
      private ArrayList newSprites;
      private TextureSpecial[] sprites;
      private String textureFile;
      private Texture texture;
      private int spriteWidth;
      private int spriteHeight;
      public int atlasIndex;


      private SpriteSheet(int tilesX, int tilesY, String textureFile) {
         this.newSprites = new ArrayList();
         this.tilesX = tilesX;
         this.tilesY = tilesY;
         this.textureFile = textureFile;
         this.sprites = new TextureSpecial[tilesX * tilesY];
      }

      public void requestIndicies(int ... indicies) {
         int[] var5 = indicies;
         int var4 = indicies.length;

         for(int var3 = 0; var3 < var4; ++var3) {
            int i = var5[var3];
            this.setupSprite(i);
         }

      }

      public void registerIcons(IconRegister register) {
         TextureMap textureMap = (TextureMap)register;
         int i;
         if(TextureUtils.refreshTexture(textureMap, this.textureFile)) {
            this.reloadTexture();

            for(i = 0; i < this.sprites.length; ++i) {
               if(this.sprites[i] != null) {
                  textureMap.setTextureEntry(this.sprites[i].getIconName(), this.sprites[i]);
               }
            }
         } else {
            Iterator var4 = this.newSprites.iterator();

            while(var4.hasNext()) {
               i = ((Integer)var4.next()).intValue();
               textureMap.setTextureEntry(this.sprites[i].getIconName(), this.sprites[i]);
            }
         }

         this.newSprites.clear();
      }

      public TextureSpecial setupSprite(int i) {
         if(this.sprites[i] == null) {
            String name = this.textureFile + "_" + i;
            this.sprites[i] = (new TextureSpecial(name)).baseFromSheet(this, i);
            this.newSprites.add(Integer.valueOf(i));
         }

         return this.sprites[i];
      }

      private void reloadTexture() {
         this.texture = TextureUtils.createTextureObject(this.textureFile);
         Dimension dim = TextureUtils.getTextureDimension(this.textureFile);
         this.spriteWidth = dim.getWidth() / this.tilesX;
         this.spriteHeight = dim.getHeight() / this.tilesY;
      }

      public Icon getSprite(int index) {
         TextureSpecial i = this.sprites[index];
         if(i == null) {
            throw new IllegalArgumentException("Sprite at index: " + index + " from texture file " + this.textureFile + " was not preloaded.");
         } else {
            return i;
         }
      }

      public Texture createSprite(int spriteIndex) {
         int sx = spriteIndex % this.tilesX;
         int sy = spriteIndex / this.tilesX;
         Texture sprite = TextureUtils.createTextureObject(this.textureFile + "_" + spriteIndex, this.spriteWidth, this.spriteHeight);
         TextureUtils.copySubImg(this.texture, sx * this.spriteWidth, sy * this.spriteHeight, this.spriteWidth, this.spriteHeight, sprite, 0, 0);
         return sprite;
      }

      public int spriteWidth() {
         return this.spriteWidth;
      }

      public int spriteHeight() {
         return this.spriteHeight;
      }

      public TextureSpecial bindTextureFX(int i, TextureFX textureFX) {
         return this.setupSprite(i).addTextureFX(textureFX);
      }

      public SpriteSheetManager.SpriteSheet selfRegister(int atlas) {
         TextureUtils.addIconRegistrar(this);
         return this;
      }

      public int atlasIndex() {
         return this.atlasIndex;
      }

      // $FF: synthetic method
      SpriteSheet(int var1, int var2, String var3, SpriteSheetManager.SpriteSheet var4) {
         this(var1, var2, var3);
      }
   }
}
