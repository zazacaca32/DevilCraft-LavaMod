package net.minecraft.client.addon.lavamobs;

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

    public GuiTextArea(FontRenderer fontRenderer, int xPos, int yPos, int width, int height, int lineCount)
    {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.fontRenderer = fontRenderer;
        this.lineCount = lineCount;
        this.text = new String[lineCount];

        for (int i = 0; i < lineCount; ++i)
        {
            this.text[i] = "";
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
        int textColor = 14737632;
        int textLeft = this.xPos + 4;
        int textTop = this.yPos + (this.height - this.lineCount * (this.fontRenderer.FONT_HEIGHT + 1)) / 2;
        int cursorPositionX;

        for (cursorPositionX = 0; cursorPositionX < this.lineCount; ++cursorPositionX)
        {
            this.fontRenderer.drawStringWithShadow(this.text[cursorPositionX], textLeft, textTop + (this.fontRenderer.FONT_HEIGHT + 1) * cursorPositionX, textColor);
        }

        textTop += (this.fontRenderer.FONT_HEIGHT + 1) * this.cursorLine;
        cursorPositionX = textLeft + this.fontRenderer.getStringWidth(this.text[this.cursorLine].substring(0, Math.min(this.text[this.cursorLine].length(), this.cursorPosition))) - 1;
        boolean drawCursor = this.isFocused && this.cursorCounter / 6 % 2 == 0;

        if (drawCursor)
        {
            this.drawCursorVertical(cursorPositionX, textTop - 1, cursorPositionX + 1, textTop + 1 + this.fontRenderer.FONT_HEIGHT);
        }
    }

    private void drawCursorVertical(int left, int top, int right, int bottom)
    {
        int var5;

        if (left < right)
        {
            var5 = left;
            left = right;
            right = var5;
        }

        if (top < bottom)
        {
            var5 = top;
            top = bottom;
            bottom = var5;
        }

        Tessellator var6 = Tessellator.instance;
        GL11.glColor4f(0.0F, 0.0F, 255.0F, 255.0F);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_COLOR_LOGIC_OP);
        GL11.glLogicOp(GL11.GL_OR_REVERSE);
        var6.startDrawingQuads();
        var6.addVertex((double)left, (double)bottom, 0.0D);
        var6.addVertex((double)right, (double)bottom, 0.0D);
        var6.addVertex((double)right, (double)top, 0.0D);
        var6.addVertex((double)left, (double)top, 0.0D);
        var6.draw();
        GL11.glDisable(GL11.GL_COLOR_LOGIC_OP);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
    }

    public void setCursorPosition(int x, int y)
    {
        if (y >= this.text.length)
        {
            y = this.text.length - 1;
        }

        this.cursorPosition = x;
        this.cursorLine = y;
        int lineLength = this.text[y].length();

        if (this.cursorPosition < 0)
        {
            this.cursorPosition = 0;
        }

        if (this.cursorPosition > lineLength)
        {
            this.cursorPosition = lineLength;
        }
    }

    public void deleteFromCursor(int count)
    {
        if (this.text[this.cursorLine].length() != 0)
        {
            boolean back = count < 0;
            String curLine = this.text[this.cursorLine];
            int left = back ? this.cursorPosition + count : this.cursorPosition;
            int right = back ? this.cursorPosition : this.cursorPosition + count;
            String newLine = "";

            if (left >= 0)
            {
                newLine = curLine.substring(0, left);
            }

            if (right < curLine.length())
            {
                newLine = newLine + curLine.substring(right);
            }

            this.text[this.cursorLine] = newLine;

            if (back)
            {
                this.setCursorPosition(this.cursorPosition + count, this.cursorLine);
            }
        }
    }

    public void writeText(String additionalText)
    {
        String newLine = "";
        String filteredText = ChatAllowedCharacters.filerAllowedCharacters(additionalText);
        int freeCharCount = this.maxStringLength - this.text[this.cursorLine].length();

        if (this.text[this.cursorLine].length() > 0)
        {
            newLine = newLine + this.text[this.cursorLine].substring(0, this.cursorPosition);
        }

        newLine = freeCharCount < filteredText.length() ? newLine + filteredText.substring(0, freeCharCount) : newLine + filteredText;

        if (this.text[this.cursorLine].length() > 0 && this.cursorPosition < this.text[this.cursorLine].length())
        {
            newLine = newLine + this.text[this.cursorLine].substring(this.cursorPosition);
        }

        this.text[this.cursorLine] = newLine;
        this.setCursorPosition(this.cursorPosition + filteredText.length(), this.cursorLine);
    }

    private void setCursorLine(int delta)
    {
        int newCursorLine = this.cursorLine + delta;

        if (newCursorLine < 0)
        {
            newCursorLine = 0;
        }

        if (newCursorLine >= this.lineCount)
        {
            newCursorLine = this.lineCount - 1;
        }

        this.cursorPosition = Math.min(this.cursorPosition, this.text[newCursorLine].length());
        this.cursorLine = newCursorLine;
    }

    public void mouseClicked(int x, int y, int par3)
    {
        this.isFocused = x >= this.xPos && x < this.xPos + this.width && y >= this.yPos && y < this.yPos + this.height;
    }

    public boolean isFocused()
    {
        return this.isFocused;
    }

    public void setFocused(boolean focused)
    {
        this.isFocused = focused;
    }

    public boolean textAreaKeyTyped(char par1, int par2)
    {
        if (this.isFocused)
        {
            switch (par1)
            {
                case '\u0001':
                    this.setCursorPosition(this.text[this.cursorLine].length(), this.cursorLine);
                    return true;

                case '\r':
                    this.setCursorLine(1);
                    return true;

                default:
                    switch (par2)
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
                            if (ChatAllowedCharacters.isAllowedCharacter(par1))
                            {
                                this.writeText(Character.toString(par1));
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
