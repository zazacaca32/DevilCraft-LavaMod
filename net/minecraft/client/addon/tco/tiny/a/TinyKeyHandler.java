package net.minecraft.client.addon.tco.tiny.a;

import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;
import java.util.EnumSet;
import net.minecraft.client.settings.KeyBinding;

public class TinyKeyHandler extends KeyHandler {

   private static KeyBinding[] keyBindings = new KeyBinding[]{new KeyBinding("Open Tiny", 35)};
   private static boolean[] repeatings = new boolean[]{false};
   private EnumSet tickTypes;


   public TinyKeyHandler() {
      super(keyBindings, repeatings);
      this.tickTypes = EnumSet.of(TickType.CLIENT);
   }

   public String getLabel() {
      return null;
   }

   public void keyUp(EnumSet types, KeyBinding kb, boolean tickEnd) {}

   public EnumSet ticks() {
      return this.tickTypes;
   }

   public void keyDown(EnumSet types, KeyBinding kb, boolean tickEnd, boolean isRepeat) {}

}
