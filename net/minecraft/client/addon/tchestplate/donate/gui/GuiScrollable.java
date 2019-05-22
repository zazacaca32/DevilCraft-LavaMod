package net.minecraft.client.addon.tchestplate.donate.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public abstract class GuiScrollable extends GuiContainer
{
    private boolean isScrolling = false;
    private boolean wasClicking = false;
    private int currentScroll = 0;
    private int maxScroll = 0;
    protected int scrollLeft = 32;
    protected int scrollTop = 32;
    protected int scrollHeight = 32;

    protected void setScroll(int var1)
    {
        this.currentScroll = var1;
    }

    protected int getScroll()
    {
        return this.currentScroll;
    }

    public GuiScrollable(Container var1)
    {
        super(var1);
    }

    protected void mouseClicked(int var1, int var2, int var3)
    {
        super.mouseClicked(var1, var2, var3);
    }

    public void handleMouseClick(Slot var1, int var2, int var3, int var4)
    {
        super.handleMouseClick(var1, var2, var3, var4);
    }

    public void drawScreen(int var1, int var2, float var3)
    {
        boolean var4 = Mouse.isButtonDown(0);
        int var5 = super.guiLeft;
        int var6 = super.guiTop;
        int var7 = var5 + this.scrollLeft;
        int var8 = var6 + this.scrollTop;
        int var9 = var7 + 14;
        int var10 = var8 + this.scrollHeight;

        if (!this.wasClicking && var4 && var1 >= var7 && var2 >= var8 && var1 < var9 && var2 < var10)
        {
            this.isScrolling = this.maxScroll > 0;
        }

        if (!var4)
        {
            this.isScrolling = false;
        }

        this.wasClicking = var4;

        if (this.isScrolling)
        {
            this.currentScroll = Math.round((float)this.maxScroll * ((float)(var2 - var8) - 7.5F) / ((float)(var10 - var8) - 15.0F));

            if (this.currentScroll < 0)
            {
                this.currentScroll = 0;
            }

            if (this.currentScroll > this.maxScroll)
            {
                this.currentScroll = this.maxScroll;
            }

            this.onScroll();
        }

        super.drawScreen(var1, var2, var3);
    }

    protected void updateScrollRegion(int var1)
    {
        this.maxScroll = var1;

        if (this.currentScroll < 0)
        {
            this.currentScroll = 0;
        }

        if (this.currentScroll > this.maxScroll)
        {
            this.currentScroll = this.maxScroll;
        }
    }

    public void onScroll()
    {
    }

    protected String getTextureScroll()
    {
        return "/gui/allitems.png";
    }

    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        int var4 = super.guiLeft + this.scrollLeft;
        int var5 = super.guiTop + this.scrollTop;
        int var6 = var5 + this.scrollHeight;
        super.mc.renderEngine.bindTexture(this.getTextureScroll());
        this.drawTexturedModalRect(var4, var5 + (int)((float)(var6 - var5 - 17) * ((float)this.currentScroll / (float)this.maxScroll)), 232 + (this.maxScroll > 0 ? 0 : 12), 0, 12, 15);
    }

    public void handleMouseInput()
    {
        int var1 = Mouse.getEventDWheel();

        if (var1 == 0)
        {
            super.handleMouseInput();
        }

        if (var1 != 0 && this.maxScroll > 0)
        {
            var1 = Math.min(var1, 1);
            var1 = Math.max(var1, -1);
            this.currentScroll -= var1;
        }

        if (this.currentScroll < 0)
        {
            this.currentScroll = 0;
        }

        if (this.currentScroll > this.maxScroll)
        {
            this.currentScroll = this.maxScroll;
        }

        this.onScroll();
    }
}
