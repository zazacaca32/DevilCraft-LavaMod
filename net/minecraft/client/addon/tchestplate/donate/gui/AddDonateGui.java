package net.minecraft.client.addon.tchestplate.donate.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.addon.tchestplate.Utils;
import net.minecraft.client.addon.tchestplate.donate.DonateManagerInv;
import net.minecraft.client.addon.tchestplate.overlaygui.ArmorExtInventory;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MD5String;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class AddDonateGui extends GuiScreen
{
    public InventoryPlayer invv;
    public ArmorExtInventory holderInventory;
    private int guiLeft;
    private int guiTop;
    private int xSize = 176;
    private int ySize = 217;
    private GuiTextArea textArea;
    DonateManagerInv inv;
    private String res;
    public static final ItemStack itemCoupon;
    final String ll = " Дорогие друзья, мы как и любой проект Minecraft нуждаемся в финансовой поддержке. Деньги необходимы везде, на покупку новых серверов, оплату интернета, аренду хостинга под сайт. А так же необходимо оплачивать услуги сторонних разработчиков. В форме ниже, вы можете ввести любую сумму для пожертвования.";
    final String ll1 = " Мы рады любой помощи с вашей стороны и поощрим вас в игре койнами.";
    final String ll2 = "Рублей";

    public AddDonateGui(InventoryPlayer var1, ArmorExtInventory var2)
    {
        this.invv = var1;
        this.holderInventory = var2;
        this.inv = LavaChestPlate.instanceDonate;
    }

    public void initGui()
    {
        super.initGui();
        this.guiLeft = (super.width - this.xSize) / 2;
        this.guiTop = (super.height - this.ySize) / 2;
        super.buttonList.clear();
        super.buttonList.add(new GuiButton(1, this.guiLeft + 95, this.guiTop + 148, 76, 20, "Пожертвовать"));
        this.textArea = new GuiTextArea(super.fontRenderer, this.guiLeft + 8, this.guiTop + 150, this.xSize - 140, 16, 1);
        this.textArea.maxStringLength = 5;
        this.textArea.setFocused(true);
    }

    protected void mouseClicked(int var1, int var2, int var3)
    {
        super.mouseClicked(var1, var2, var3);

        if (this.textArea != null)
        {
            this.textArea.mouseClicked(var1, var2, var3);
        }
    }

    protected void actionPerformed(GuiButton var1)
    {
        if (var1 != null && var1.id == 1)
        {
            try
            {
                if (this.textArea == null)
                {
                    return;
                }

                if (this.textArea.getText() == null)
                {
                    return;
                }

                if (this.textArea.getText()[0] == null)
                {
                    return;
                }

                if (this.textArea.getText()[0].length() < 1)
                {
                    return;
                }

                String var91 = new String(Minecraft.getMinecraftDir().getCanonicalPath());
                String[] var3 = var91.split("\\\\");
                this.res = "LavaCraft.ru";
                String var4 = "";
                MD5String var5 = new MD5String(this.res);
                var4 = var4 + "1000:1000h" + this.textArea.getText()[0] + "i";

                if (var4.length() > 5)
                {
                    this.res = super.mc.thePlayer.getEntityName() + var3[var3.length - 1] + var4;
                    var4 = "user=" + super.mc.thePlayer.getEntityName() + "&world=" + var3[var3.length - 1] + "&item=" + var4;
                    var4 = "aes1024=" + var5.getMD5String(this.res).substring(0, 7) + "&" + var4;
                    URI var6 = new URI("http://lavacraft.org/shop/order.php?" + var4);
                    Class var7 = Class.forName("java.awt.Desktop");
                    Object var8 = var7.getMethod("getDesktop", new Class[0]).invoke((Object)null, new Object[0]);
                    var7.getMethod("browse", new Class[] {URI.class}).invoke(var8, new Object[] {var6});
                }

                super.mc.thePlayer.closeScreen();
            }
            catch (Throwable var9)
            {
                var9.printStackTrace();
            }
        }
    }

    protected void keyTyped(char var1, int var2)
    {
        if (var2 == 1 || var2 == super.mc.gameSettings.keyBindInventory.keyCode && (this.textArea == null || !this.textArea.isFocused()))
        {
            this.actionPerformed((GuiButton)null);
        }
        else if (this.textArea != null && this.textArea.isFocused())
        {
            if (isNumeric(new String(new char[] {var1})) || var2 == 14)
            {
                this.textArea.textAreaKeyTyped(var1, var2);
            }
            return;
        }

        super.keyTyped(var1, var2);
    }

    public static boolean isNumeric(String var0)
    {
        Pattern var1 = Pattern.compile("^\\d+(?:\\.\\d+)?$");
        Matcher var2 = var1.matcher(var0);
        return var2.matches();
    }

    public void drawScreen(int var1, int var2, float var3)
    {
        this.drawDefaultBackground();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        super.mc.renderEngine.bindTexture("/mods/LavaChestPlate/textures/gui/donateadd.png");
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
        this.drawCenteredString(super.fontRenderer, "Донат", this.guiLeft + this.xSize / 2, this.guiTop + 20, 16752384);
        super.fontRenderer.drawSplitString(" Дорогие друзья, мы как и любой проект Minecraft нуждаемся в финансовой поддержке. Деньги необходимы везде, на покупку новых серверов, оплату интернета, аренду хостинга под сайт. А так же необходимо оплачивать услуги сторонних разработчиков. В форме ниже, вы можете ввести любую сумму для пожертвования.", this.guiLeft + 8, this.guiTop + 30, 160, 16777215);
        this.drawString(super.fontRenderer, "Рублей", this.guiLeft + 50, this.guiTop + 154, 16776960);
        super.fontRenderer.drawSplitString(" Мы рады любой помощи с вашей стороны и поощрим вас в игре койнами.", this.guiLeft + 8, this.guiTop + 178, 160, 16731904);

        if (this.textArea != null)
        {
            this.textArea.drawTextBox();
        }

        super.drawScreen(var1, var2, var3);
        Utils.renderItemStack(super.fontRenderer, itemCoupon, this.guiLeft + 90, this.guiTop + 193);
    }

    static
    {
        itemCoupon = new ItemStack(LavaChestPlate.ItemDonate, 1, 1);
    }
}
