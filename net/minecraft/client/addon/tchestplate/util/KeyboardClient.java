package net.minecraft.client.addon.tchestplate.util;

import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.EnumSet;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

@SideOnly(Side.CLIENT)
public class KeyboardClient extends Keyboard
{
    public static int icBoostKeyID;
    public static int icAltKeyID;
    public static int icModeKeyID;
    public static int GraviFlyKey;
    public static String tcNightKey;
    public static boolean _NightKey;
    public static boolean _upUseKey;
    public static String tcLavaPotionKey;
    public static String tcInvisible;
    public static boolean _LavaPotionKey;
    public static boolean _InvisibleKey;
    public static boolean _LavaPanter;
    private Minecraft mc = Minecraft.getMinecraft();
    private KeyBinding invKey;
    private KeyBinding NightKey;
    private KeyBinding LavaPotionKey;
    public static KeyBinding Invisible;
    public static KeyBinding LavaPanter;

    public KeyboardClient()
    {
        this.invKey = this.mc.gameSettings.keyBindInventory;
        this.NightKey = new KeyBinding("Night Key", 49);
        Invisible = new KeyBinding("Невидимость", 48);
        this.LavaPotionKey = new KeyBinding("Пузырек с лава энергией", 44);
        LavaPanter = new KeyBinding("Рывок пантеры", 41);
    }

    public void load()
    {
        KeyBindingRegistry.registerKeyBinding(new KeyHandler(new KeyBinding[] {this.NightKey, this.LavaPotionKey, Invisible, LavaPanter}, new boolean[] {false, false, false, false})
        {
            public String getLabel()
            {
                return "tchestplateKeyboard";
            }
            public EnumSet ticks()
            {
                return EnumSet.of(TickType.CLIENT);
            }
            public void keyUp(EnumSet types, KeyBinding kb, boolean tickEnd)
            {
                if (tickEnd && KeyboardClient.this.mc.inGameHasFocus)
                {
                    if (kb == KeyboardClient.this.NightKey)
                    {
                        KeyboardClient._NightKey = true;
                    }

                    if (kb == KeyboardClient.this.LavaPotionKey)
                    {
                        KeyboardClient._LavaPotionKey = true;
                    }

                    KeyboardClient var10001 = KeyboardClient.this;

                    if (kb == KeyboardClient.Invisible)
                    {
                        KeyboardClient._InvisibleKey = true;
                    }

                    KeyboardClient var100011 = KeyboardClient.this;

                    if (kb == KeyboardClient.LavaPanter)
                    {
                        KeyboardClient._LavaPanter = true;
                    }

                    if (kb == KeyboardClient.this.mc.gameSettings.keyBindUseItem)
                    {
                        KeyboardClient._upUseKey = true;
                    }
                }
            }
            public void keyDown(EnumSet types, KeyBinding kb, boolean tickEnd, boolean isRepeat)
            {
            }
        });
        tcNightKey = this.NightKey.keyDescription;
        tcLavaPotionKey = this.LavaPotionKey.keyDescription;
        tcInvisible = Invisible.keyDescription;
    }

    public boolean isUpKeyUseItem()
    {
        return _upUseKey;
    }

    public void setisUpKeyUseItem()
    {
        if (_upUseKey)
        {
            _upUseKey = false;
        }
    }

    public boolean isLavaPotionKeyUpKeyDown()
    {
        return _LavaPotionKey;
    }

    public boolean isInvisibleKeyUpKeyDown()
    {
        return _InvisibleKey;
    }

    public void setisLavaPanterKeyUpKeyDown()
    {
        if (_LavaPanter)
        {
            _LavaPanter = false;
        }
    }

    public void setisLavaPotionKeyUpKeyDown()
    {
        if (_LavaPotionKey)
        {
            _LavaPotionKey = false;
        }
    }

    public void setisInvisibleKeyUpKeyDown()
    {
        if (_InvisibleKey)
        {
            _InvisibleKey = false;
        }
    }

    public boolean isNightUpKeyDown()
    {
        return _NightKey;
    }

    public void setisNightUpKeyDown()
    {
        if (_NightKey)
        {
            _NightKey = false;
        }
    }

    public boolean isGraviFlyKeyDown()
    {
        return this.mc.gameSettings.keyBindings[GraviFlyKey].pressed;
    }

    public boolean isBoostKeyDown()
    {
        return this.mc.gameSettings.keyBindings[icBoostKeyID].pressed;
    }

    public boolean isAltKeyDown()
    {
        return this.mc.gameSettings.keyBindings[icAltKeyID].pressed;
    }

    public boolean isModeKeyPress()
    {
        return this.mc.gameSettings.keyBindings[icModeKeyID].pressed;
    }

    public boolean isJumpKeyDown()
    {
        return this.mc.gameSettings.keyBindJump.pressed;
    }

    public boolean isForwardKeyDown()
    {
        return this.mc.gameSettings.keyBindForward.pressed;
    }

    public boolean isSneakKeyDown()
    {
        return this.mc.gameSettings.keyBindSneak.pressed;
    }

    public boolean isUseItemKeyDown()
    {
        return this.mc.gameSettings.keyBindUseItem.pressed;
    }

    public boolean isKeyLavaPotionKeyDown()
    {
        return this.LavaPotionKey.pressed;
    }

    public boolean isKeyInvisibleKeyDown()
    {
        return Invisible.pressed;
    }

    public boolean isKeyLavaPanterKeyDown()
    {
        return LavaPanter.pressed;
    }

    public String getKey(int key)
    {
        return this.mc.gameSettings.keyBindings[key].keyDescription;
    }
}
