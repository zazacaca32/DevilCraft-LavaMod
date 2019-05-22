package net.minecraft.client.addon.tco.tiny.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.imageio.ImageIO;
import net.minecraft.client.renderer.texture.Texture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureStitched;
import net.minecraft.client.texturepacks.ITexturePack;

@SideOnly(Side.CLIENT)
public class BlockTextureStitched extends TextureStitched {

   private BufferedImage comparisonImage;
   private TextureStitched mappedTexture;
   private static Map cachedImages = new HashMap();
   private static Map existingTextures = new HashMap();


   public BlockTextureStitched(String name) {
      super(name);
   }

   public void copyFrom(TextureStitched textureStitched) {
      if(textureStitched.getIconName().equals("missingno") && this.mappedTexture != null) {
         super.copyFrom(this.mappedTexture);
      } else {
         super.copyFrom(textureStitched);
      }

   }

   public boolean loadTexture(TextureManager manager, ITexturePack texturepack, String name, String fileName, BufferedImage image, ArrayList textures) {
      int extStart = fileName.lastIndexOf(46);
      int indexStart = fileName.lastIndexOf(46, extStart - 1);
      String textureFile = "/" + fileName.substring(0, indexStart) + ".png";
      int index = Integer.parseInt(fileName.substring(indexStart + 1, extStart));
      BufferedImage bufferedImage = (BufferedImage)cachedImages.get(textureFile);
      if(bufferedImage == null) {
         try {
            bufferedImage = ImageIO.read(texturepack.getResourceAsStream(textureFile));
            cachedImages.put(textureFile, bufferedImage);
         } catch (IOException var20) {
            return false;
         }
      }

      int size = bufferedImage.getHeight();
      int count = bufferedImage.getWidth() / size;
      if(count != 1 && count != 6 && count != 12) {
         if(count != 2) {
            return false;
         }

         index /= 6;
      } else {
         index %= count;
      }

      this.comparisonImage = bufferedImage.getSubimage(index * size, 0, size, size);
      int[] rgbaData = new int[size * size];
      this.comparisonImage.getRGB(0, 0, size, size, rgbaData, 0, size);
      int hash = Arrays.hashCode(rgbaData);
      ArrayList matchingTextures = (ArrayList)existingTextures.get(Integer.valueOf(hash));
      if(matchingTextures != null) {
         int[] texture111111 = new int[size * size];
         Iterator var18 = matchingTextures.iterator();

         while(var18.hasNext()) {
            BlockTextureStitched matchingTexture = (BlockTextureStitched)var18.next();
            if(matchingTexture.comparisonImage.getWidth() == size) {
               matchingTexture.comparisonImage.getRGB(0, 0, size, size, texture111111, 0, size);
               if(Arrays.equals(rgbaData, texture111111)) {
                  this.mappedTexture = matchingTexture;
                  return true;
               }
            }
         }

         matchingTextures.add(this);
      } else {
         matchingTextures = new ArrayList();
         matchingTextures.add(this);
         existingTextures.put(Integer.valueOf(hash), matchingTextures);
      }

      Texture texture1111111 = new Texture(name, 2, size, size, 10496, 6408, 9728, 9728, this.comparisonImage);
      textures.add(texture1111111);
      return true;
   }

}
