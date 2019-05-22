package codechicken.core.render;

import codechicken.core.colour.Colour;
import codechicken.core.colour.ColourARGB;
import codechicken.core.render.PlaceholderTexture;
import codechicken.core.render.TextureSpecial;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.client.renderer.texture.Texture;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.texture.TextureStitched;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.client.event.TextureStitchEvent.Pre;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Dimension;

public class TextureUtils {

   private static HashMap textureDimensions;
   private static ArrayList iconRegistrars;
   private static Icon missingno;


   static {
      MinecraftForge.EVENT_BUS.register(new TextureUtils());
      textureDimensions = new HashMap();
      iconRegistrars = new ArrayList();
   }

   public static void addIconRegistrar(TextureUtils.IIconRegister registrar) {
      iconRegistrars.add(registrar);
   }

   @ForgeSubscribe
   public void textureLoad(Pre event) {
      missingno = event.map.registerIcon("missingno");
      Iterator var3 = iconRegistrars.iterator();

      while(var3.hasNext()) {
         TextureUtils.IIconRegister reg = (TextureUtils.IIconRegister)var3.next();
         if(reg.atlasIndex() == event.map.textureType) {
            reg.registerIcons(event.map);
         }
      }

   }

   public static Icon getMissingno() {
      return missingno;
   }

   public static Texture createTextureObject(String textureFile) {
      BufferedImage img = loadBufferedImage(textureFile);
      return new Texture(textureFile, 2, img.getWidth(), img.getHeight(), 10496, 6408, 9728, 9728, img);
   }

   public static int[] loadTextureData(String textureFile) {
      BufferedImage img = loadBufferedImage(textureFile);
      int[] data = new int[img.getWidth() * img.getHeight()];
      img.getRGB(0, 0, img.getWidth(), img.getHeight(), data, 0, img.getWidth());
      return data;
   }

   public static Colour[] loadTextureColours(String textureFile) {
      int[] idata = loadTextureData(textureFile);
      Colour[] data = new Colour[idata.length];

      for(int i = 0; i < data.length; ++i) {
         data[i] = new ColourARGB(idata[i]);
      }

      return data;
   }

   public static InputStream getTextureResource(String textureFile) throws IOException {
      return engine().texturePack.getSelectedTexturePack().getResourceAsStream(textureFile);
   }

   public static BufferedImage loadBufferedImage(String textureFile) {
      try {
         InputStream e = getTextureResource(textureFile);
         if(e != null) {
            BufferedImage img = loadBufferedImage(e);
            if(img != null) {
               textureDimensions.put(textureFile, new Dimension(img.getWidth(), img.getHeight()));
            }

            return img;
         }
      } catch (Exception var3) {
         System.err.println("Failed to load texture file: " + textureFile);
         var3.printStackTrace();
      }

      textureDimensions.put(textureFile, new Dimension(0, 0));
      return null;
   }

   public static Dimension getTextureDimension(String textureFile) {
      Dimension dim = (Dimension)textureDimensions.get(textureFile);
      if(dim == null) {
         loadBufferedImage(textureFile);
         dim = (Dimension)textureDimensions.get(textureFile);
      }

      return dim;
   }

   public static BufferedImage loadBufferedImage(InputStream in) throws IOException {
      BufferedImage img = ImageIO.read(in);
      in.close();
      return img;
   }

   public static RenderEngine engine() {
      return Minecraft.getMinecraft().renderEngine;
   }

   public static Texture createTextureObject(String name, int w, int h) {
      return new Texture(name, 2, w, h, 10496, 6408, 9728, 9728, (BufferedImage)null);
   }

   public static void copySubImg(Texture fromTex, int fromX, int fromY, int width, int height, Texture toTex, int toX, int toY) {
      int fromWidth = fromTex.getWidth();
      ByteBuffer from = fromTex.getTextureData();
      Texture tmp = createTextureObject("tmp", width, height);
      ByteBuffer to = tmp.getTextureData();
      from.position(0);
      to.position(0);

      for(int y = 0; y < height; ++y) {
         for(int x = 0; x < width; ++x) {
            int fp = ((y + fromY) * fromWidth + x + fromX) * 4;
            int tp = (y * width + x) * 4;
            to.put(tp, from.get(fp));
            to.put(tp + 1, from.get(fp + 1));
            to.put(tp + 2, from.get(fp + 2));
            to.put(tp + 3, from.get(fp + 3));
         }
      }

      toTex.copyFrom(toX, toY, tmp, false);
   }

   public static void write(byte[] data, int width, int height, Texture toTex, int toX, int toY) {
      Texture tmp = createTextureObject("tmp", width, height);
      ByteBuffer to = tmp.getTextureData();
      to.position(0);

      for(int y = 0; y < height; ++y) {
         for(int x = 0; x < width; ++x) {
            int p = (y * width + x) * 4;
            to.put(p, data[p]);
            to.put(p + 1, data[p + 1]);
            to.put(p + 2, data[p + 2]);
            to.put(p + 3, data[p + 3]);
         }
      }

      toTex.copyFrom(toX, toY, tmp, false);
   }

   public static boolean refreshTexture(TextureMap map, String name) {
      if(map.getTextureExtry(name) == null) {
         map.setTextureEntry(name, new PlaceholderTexture(name));
         return true;
      } else {
         return false;
      }
   }

   public static void bindItemTexture(ItemStack stack) {
      engine().bindTexture(stack.getItemSpriteNumber() == 0?"/terrain.png":"/gui/items.png");
   }

   public static void bindTexture(String string) {
      engine().bindTexture(string);
   }

   public static Icon getIconFromTexture(String name, IconRegister iconRegister, boolean animated) {
      TextureMap textureMap = (TextureMap)iconRegister;
      TextureStitched entry = textureMap.getTextureExtry(name);
      if(entry != null) {
         return entry;
      } else {
         TextureSpecial icon = (new TextureSpecial(name)).setTextureFile(name).useVanillaAnimation(animated);
         textureMap.setTextureEntry(name, icon);
         return icon;
      }
   }

   public static Icon getIconFromTexture(String name, IconRegister iconRegister) {
      return getIconFromTexture(name, iconRegister, true);
   }

   public static Icon getBlankIcon(int size, IconRegister iconRegister) {
      TextureMap textureMap = (TextureMap)iconRegister;
      String s = "blank_" + size;
      if(textureMap.getTextureExtry(s) == null) {
         TextureSpecial icon = (new TextureSpecial(s)).blank(size);
         textureMap.setTextureEntry(s, icon);
      }

      return iconRegister.registerIcon(s);
   }

   public static TextureSpecial getTextureSpecial(IconRegister iconRegister, String name) {
      TextureMap textureMap = (TextureMap)iconRegister;
      TextureStitched entry = textureMap.getTextureExtry(name);
      if(entry != null) {
         throw new IllegalStateException("Texture: " + name + " is already registered");
      } else {
         TextureSpecial icon = new TextureSpecial(name);
         textureMap.setTextureEntry(name, icon);
         return icon;
      }
   }

   public static void prepareTexture(int target, int texture, int min_mag_filter, int wrap) {
      GL11.glBindTexture(target, texture);
      engine().resetBoundTexture();
      GL11.glTexParameteri(target, 10241, min_mag_filter);
      GL11.glTexParameteri(target, 10240, min_mag_filter);
      switch(target) {
      case '\u806f':
         GL11.glTexParameteri(target, '\u8072', wrap);
      case 3553:
         GL11.glTexParameteri(target, 10243, wrap);
      case 3552:
         GL11.glTexParameteri(target, 10242, wrap);
      default:
      }
   }

   public interface IIconRegister {

      void registerIcons(IconRegister var1);

      int atlasIndex();
   }
}
