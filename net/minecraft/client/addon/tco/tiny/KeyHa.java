package net.minecraft.client.addon.tco.tiny;

import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.TickType;
import java.util.EnumSet;
import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tco.tiny.Tiny;
import net.minecraft.client.settings.KeyBinding;

public class KeyHa extends KeyHandler {

   public KeyHa(KeyBinding[] keyBindings, boolean[] repeatings) {
      super(keyBindings, repeatings);
   }

   public String getLabel() {
      return this.getClass().getSimpleName();
   }

   public void keyDown(EnumSet types, KeyBinding kb, boolean tickEnd, boolean isRepeat) {
      if(tickEnd && kb.keyCode == 38 && kb.isPressed()) {
         int x = (int)Minecraft.getMinecraft().thePlayer.posX;
         int y = (int)Minecraft.getMinecraft().thePlayer.posY;
         int z = (int)Minecraft.getMinecraft().thePlayer.posZ;
         Minecraft.getMinecraft().thePlayer.openGui(Tiny.instance, 10000, Minecraft.getMinecraft().theWorld, 0, 0, 0);
         FMLCommonHandler.instance().getFMLLogger().severe("55555555555");
      }

   }

   public void keyUp(EnumSet types, KeyBinding kb, boolean tickEnd) {}

   public EnumSet ticks() {
      return EnumSet.of(TickType.CLIENT);
   }
}
