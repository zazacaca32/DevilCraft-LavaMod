package net.minecraft.client.addon.tchestplate.items.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.input.Mouse;

@SideOnly(Side.CLIENT)
public class GuiItemPetograph extends GuiScreen
{
    EntityPlayer player;
    public float x;
    public float y;
    public final String Name;
    String dd;
    public final String info;

    public GuiItemPetograph(EntityPlayer player)
    {
        this.x = 0.0F;
        this.y = 0.0F;
        this.Name = "Питограф";
        this.dd = "Все питомцы обладают астральным зрением, они видят души тех кто им подходит для питания, у них в меню можно посмотреть сколько времени осталось до ухода душ с места где он их заметил, для нахождения этого места вам надо в месте с питомцем использовать петограф, с помощью его вы доберетесь до места где питомец заметил души астральным зрением и можете их поимать специальным оружием.";
        this.info = "Этот прибор служит для нахождения места для прокачки питомца, который сеичас стоит на вас, идите по стрелке которая показывает на приборе Питографе, он ищет где сеичас находится вихрь опыта, Вихри нужные питомцу кружат по миру, чтобы посмотреть сколько времени вихрь опыта будет находиться на тои площади надо заити в питомца, когда вы прибудете на место об этом можно узнать по тому как ведет себя прибор, далее ищите сам вихрь, заидите в него и чуть чуть постойте чтоб питомец забрал опыт, после чего питомец найдет следующий вихрь опыта, посмотреть сколько опыта у питомца можно довольно просто, возьмите питомца в руку и нажмите правой кнопкои высветится меню с характеристиками питомца, когда у питомца поднимится опыт питограф покажет следующее место для его прокачки, и так далее при каждом повышении опыта.";
        this.player = player;
    }

    public GuiItemPetograph(EntityPlayer player, int EntityId, int Subid, float x, float y)
    {
        this(player);
        this.x = x;
        this.y = y;
    }

    public void initGui()
    {
        super.buttonList.clear();
        super.buttonList.add(new GuiButton(2, super.width - 102, super.height - 22, 98, 20, "Выйти"));

        if (this.x != 0.0F && this.y != 0.0F)
        {
            Mouse.setCursorPosition((int)this.x, (int)this.y);
        }
    }

    public void updateScreen()
    {
        super.updateScreen();
    }

    public void confirmClicked(boolean par1, int par2)
    {
        if (par2 == 0)
        {
            ;
        }
    }

    protected void actionPerformed(GuiButton guibutton)
    {
        if (guibutton.id == 2)
        {
            super.mc.thePlayer.closeScreen();
        }
    }

    protected void keyTyped(char par1, int par2)
    {
        if (par2 == 1 || par2 == super.mc.gameSettings.keyBindInventory.keyCode)
        {
            super.mc.thePlayer.closeScreen();
        }
    }

    public void drawScreen(int par1, int par2, float par3)
    {
        this.drawDefaultBackground();
        this.drawHorizontalLine(10, super.width - 112, 5, -9408400);
        this.drawHorizontalLine(10, super.width - 112, super.height - 4, -9408400);
        this.drawVerticalLine(5, super.height - 10, 10, -9408400);
        this.drawVerticalLine(super.width - 107, super.height - 10, 10, -9408400);
        this.drawCenteredString(super.fontRenderer, "Питограф", (super.width - 112) / 2, 10, 10092288);
        super.fontRenderer.drawSplitString("Этот прибор служит для нахождения места для прокачки питомца, который сеичас стоит на вас, идите по стрелке которая показывает на приборе Питографе, он ищет где сеичас находится вихрь опыта, Вихри нужные питомцу кружат по миру, чтобы посмотреть сколько времени вихрь опыта будет находиться на тои площади надо заити в питомца, когда вы прибудете на место об этом можно узнать по тому как ведет себя прибор, далее ищите сам вихрь, заидите в него и чуть чуть постойте чтоб питомец забрал опыт, после чего питомец найдет следующий вихрь опыта, посмотреть сколько опыта у питомца можно довольно просто, возьмите питомца в руку и нажмите правой кнопкои высветится меню с характеристиками питомца, когда у питомца поднимится опыт питограф покажет следующее место для его прокачки, и так далее при каждом повышении опыта.", 10, 14 + super.fontRenderer.FONT_HEIGHT * 2, super.width - 122, 10092288);
        super.drawScreen(par1, par2, par3);
    }
}
