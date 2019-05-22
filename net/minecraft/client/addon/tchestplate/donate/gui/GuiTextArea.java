package net.minecraft.client.addon.tchestplate.donate.gui;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ChatAllowedCharacters;
import org.lwjgl.opengl.GL11;

public class GuiTextArea extends Gui
{
    private final int lineCount;
    public int maxStringLength = 32;
    private int cursorCounter;
    private int cursorPosition = 0;
    private int cursorLine = 0;
    private boolean isFocused = false;
    private String[] text;
    private final FontRenderer fontRenderer;
    private final int xPos;
    private final int yPos;
    private final int width;
    private final int height;

    public GuiTextArea(FontRenderer var1, int var2, int var3, int var4, int var5, int var6)
    {
        this.xPos = var2;
        this.yPos = var3;
        this.width = var4;
        this.height = var5;
        this.fontRenderer = var1;
        this.lineCount = var6;
        this.text = new String[var6];

        for (int var7 = 0; var7 < var6; ++var7)
        {
            this.text[var7] = "";
        }
    }

    public String[] getText()
    {
        return this.text;
    }

    public void drawTextBox()
    {
        drawRect(this.xPos - 1, this.yPos - 1, this.xPos + this.width + 1, this.yPos + this.height + 1, -6250336);
        drawRect(this.xPos, this.yPos, this.xPos + this.width, this.yPos + this.height, -16777216);
        int var1 = 14737632;
        int var2 = this.xPos + 4;
        int var3 = this.yPos + (this.height - this.lineCount * (this.fontRenderer.FONT_HEIGHT + 1)) / 2;
        int var4;

        for (var4 = 0; var4 < this.lineCount; ++var4)
        {
            this.fontRenderer.drawStringWithShadow(this.text[var4], var2, var3 + (this.fontRenderer.FONT_HEIGHT + 1) * var4, var1);
        }

        var3 += (this.fontRenderer.FONT_HEIGHT + 1) * this.cursorLine;
        var4 = var2 + this.fontRenderer.getStringWidth(this.text[this.cursorLine].substring(0, Math.min(this.text[this.cursorLine].length(), this.cursorPosition))) - 1;
        boolean var5 = this.isFocused && this.cursorCounter / 6 % 2 == 0;

        if (var5)
        {
            this.drawCursorVertical(var4, var3 - 1, var4 + 1, var3 + 1 + this.fontRenderer.FONT_HEIGHT);
        }
    }

    private void drawCursorVertical(int var1, int var2, int var3, int var4)
    {
        int var5;

        if (var1 < var3)
        {
            var5 = var1;
            var1 = var3;
            var3 = var5;
        }

        if (var2 < var4)
        {
            var5 = var2;
            var2 = var4;
            var4 = var5;
        }

        Tessellator var6 = Tessellator.instance;
        GL11.glColor4f(0.0F, 0.0F, 255.0F, 255.0F);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_COLOR_LOGIC_OP);
        GL11.glLogicOp(GL11.GL_OR_REVERSE);
        var6.startDrawingQuads();
        var6.addVertex((double)var1, (double)var4, 0.0D);
        var6.addVertex((double)var3, (double)var4, 0.0D);
        var6.addVertex((double)var3, (double)var2, 0.0D);
        var6.addVertex((double)var1, (double)var2, 0.0D);
        var6.draw();
        GL11.glDisable(GL11.GL_COLOR_LOGIC_OP);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
    }

    public void setCursorPosition(int var1, int var2)
    {
        if (var2 >= this.text.length)
        {
            var2 = this.text.length - 1;
        }

        this.cursorPosition = var1;
        this.cursorLine = var2;
        int var3 = this.text[var2].length();

        if (this.cursorPosition < 0)
        {
            this.cursorPosition = 0;
        }

        if (this.cursorPosition > var3)
        {
            this.cursorPosition = var3;
        }
    }

    public void deleteFromCursor(int var1)
    {
        if (this.text[this.cursorLine].length() != 0)
        {
            boolean var2 = var1 < 0;
            String var3 = this.text[this.cursorLine];
            int var4 = var2 ? this.cursorPosition + var1 : this.cursorPosition;
            int var5 = var2 ? this.cursorPosition : this.cursorPosition + var1;
            String var6 = "";

            if (var4 >= 0)
            {
                var6 = var3.substring(0, var4);
            }

            if (var5 < var3.length())
            {
                var6 = var6 + var3.substring(var5);
            }

            this.text[this.cursorLine] = var6;

            if (var2)
            {
                this.setCursorPosition(this.cursorPosition + var1, this.cursorLine);
            }
        }
    }

    public void writeText(String var1)
    {
        String var2 = "";
        String var3 = ChatAllowedCharacters.filerAllowedCharacters(var1);
        int var4 = this.maxStringLength - this.text[this.cursorLine].length();

        if (this.text[this.cursorLine].length() > 0)
        {
            var2 = var2 + this.text[this.cursorLine].substring(0, this.cursorPosition);
        }

        if (var4 < var3.length())
        {
            var2 = var2 + var3.substring(0, var4);
        }
        else
        {
            var2 = var2 + var3;
        }

        if (this.text[this.cursorLine].length() > 0 && this.cursorPosition < this.text[this.cursorLine].length())
        {
            var2 = var2 + this.text[this.cursorLine].substring(this.cursorPosition);
        }

        this.text[this.cursorLine] = var2;
        this.setCursorPosition(this.cursorPosition + var3.length(), this.cursorLine);
    }

    private void setCursorLine(int var1)
    {
        int var2 = this.cursorLine + var1;

        if (var2 < 0)
        {
            var2 = 0;
        }

        if (var2 >= this.lineCount)
        {
            var2 = this.lineCount - 1;
        }

        this.cursorPosition = Math.min(this.cursorPosition, this.text[var2].length());
        this.cursorLine = var2;
    }

    public void mouseClicked(int var1, int var2, int var3)
    {
        this.isFocused = var1 >= this.xPos && var1 < this.xPos + this.width && var2 >= this.yPos && var2 < this.yPos + this.height;
    }

    public boolean isFocused()
    {
        return this.isFocused;
    }

    public void setFocused(boolean var1)
    {
        this.isFocused = var1;
    }

    public boolean textAreaKeyTyped(char var1, int var2)
    {
        if (this.isFocused)
        {
            switch (var1)
            {
                case '\u0001':
                    this.setCursorPosition(this.text[this.cursorLine].length(), this.cursorLine);
                    return true;

                case '\r':
                    this.setCursorLine(1);
                    return true;

                default:
                    switch (var2)
                    {
                        case 14:
                            this.deleteFromCursor(-1);
                            return true;

                        case 199:
                            this.setCursorPosition(0, this.cursorLine);
                            return true;

                        case 200:
                            this.setCursorLine(-1);
                            return true;

                        case 203:
                            this.setCursorPosition(this.cursorPosition - 1, this.cursorLine);
                            return true;

                        case 205:
                            this.setCursorPosition(this.cursorPosition + 1, this.cursorLine);
                            return true;

                        case 207:
                            this.setCursorPosition(this.text[this.cursorLine].length(), this.cursorLine);
                            return true;

                        case 208:
                            this.setCursorLine(1);
                            return true;

                        case 211:
                            this.deleteFromCursor(1);
                            return true;

                        default:
                            if (ChatAllowedCharacters.isAllowedCharacter(var1))
                            {
                                this.writeText(Character.toString(var1));
                                return true;
                            }
                            else
                            {
                                return false;
                            }
                    }
            }
        }
        else
        {
            return false;
        }
    }
}
