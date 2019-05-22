package codechicken.core.render;

import codechicken.core.render.SpriteSheetManager;
import codechicken.core.render.TextureFX;
import codechicken.core.render.TextureUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.client.renderer.texture.Texture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.texture.TextureStitched;
import net.minecraft.client.texturepacks.ITexturePack;

@SideOnly(Side.CLIENT)
public class TextureSpecial extends TextureStitched implements TextureUtils.IIconRegister {

   private int spriteIndex;
   private SpriteSheetManager.SpriteSheet spriteSheet;
   private TextureFX textureFX;
   private int blankSize = -1;
   private String textureFile;
   private ArrayList baseTextures;
   private boolean vanillaAnimate;
   private boolean selfRegister;
   public int atlasIndex;


   protected TextureSpecial(String par1) {
      super(par1);
   }

   public TextureSpecial addTexture(Texture t) {
      if(this.baseTextures == null) {
         this.baseTextures = new ArrayList();
      }

      this.baseTextures.add(t);
      return this;
   }

   public TextureSpecial baseFromSheet(SpriteSheetManager.SpriteSheet spriteSheet, int spriteIndex) {
      this.spriteSheet = spriteSheet;
      this.spriteIndex = spriteIndex;
      return this;
   }

   public TextureSpecial addTextureFX(TextureFX fx) {
      this.textureFX = fx;
      return this;
   }

   public void init(Texture par1Texture, List par2List, int originX, int originY, int width, int height, boolean par7) {
      super.init(par1Texture, par2List, originX, originY, width, height, par7);
      if(this.textureFX != null) {
         this.textureFX.onTextureDimensionsUpdate(width, height);
      } else if(this.textureFile != null && this.vanillaAnimate && super.textureList.size() > 0) {
         try {
            InputStream in = TextureUtils.getTextureResource(this.textureFile.replace(".png", ".txt"));
            this.readAnimationInfo(new BufferedReader(new InputStreamReader(in)));
         } catch (Exception var9) {
            ;
         }
      }

   }

   public void updateAnimation() {
      if(this.textureFX != null) {
         this.textureFX.update();
         if(this.textureFX.changed()) {
            TextureUtils.write(this.textureFX.imageData, this.textureFX.tileSizeBase, this.textureFX.tileSizeBase, super.textureSheet, super.originX, super.originY);
         }
      } else if(this.vanillaAnimate) {
         super.updateAnimation();
      }

   }

   public boolean loadTexture(TextureManager manager, ITexturePack texturepack, String name, String fileName, BufferedImage image, ArrayList textures) {
      if(this.baseTextures != null) {
         textures.addAll(this.baseTextures);
      }

      Texture base;
      if(this.spriteSheet != null) {
         textures.add(this.spriteSheet.createSprite(this.spriteIndex));
      } else if(this.blankSize > 0) {
         textures.add(TextureUtils.createTextureObject(name, this.blankSize, this.blankSize));
      } else if(this.textureFile != null) {
         base = TextureUtils.createTextureObject(this.textureFile);
         int width = base.getWidth();
         int height = base.getHeight();
         if(this.vanillaAnimate && height > width) {
            int frames = height / width;

            for(int frame = 0; frame < frames; ++frame) {
               Texture frameTex = TextureUtils.createTextureObject(name, width, width);
               TextureUtils.copySubImg(base, 0, width * frame, width, width, frameTex, 0, 0);
               textures.add(frameTex);
            }
         } else {
            textures.add(base);
         }
      }

      if(this.textureFX != null) {
         if(textures.isEmpty()) {
            throw new RuntimeException("TextureFX with no base sprite: " + name);
         }

         base = (Texture)textures.get(0);
         textures.add(TextureUtils.createTextureObject(name + "$2", base.getWidth(), base.getHeight()));
      }

      return !textures.isEmpty()?true:super.loadTexture(manager, texturepack, name, fileName, image, textures);
   }

   public TextureSpecial blank(int size) {
      this.blankSize = size;
      return this;
   }

   public TextureSpecial setTextureFile(String fileName) {
      this.textureFile = fileName;
      return this;
   }

   public TextureSpecial selfRegister() {
      this.selfRegister = true;
      TextureUtils.addIconRegistrar(this);
      return this;
   }

   public void registerIcons(IconRegister register) {
      if(this.selfRegister) {
         ((TextureMap)register).setTextureEntry(this.getIconName(), this);
      }

   }

   public int atlasIndex() {
      return this.atlasIndex;
   }

   public TextureSpecial useVanillaAnimation(boolean b) {
      this.vanillaAnimate = b;
      return this;
   }
}
