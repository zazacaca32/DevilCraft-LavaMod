package net.minecraft.client.addon.lavamobs;

import net.minecraft.client.addon.tchestplate.player.ExtendedPlayer;

public class EventMobCount
{
    public byte[] mobid;
    public short[] count;
    public static String[] Mobname = new String[] {"---", "Хомяков", "Волков", "Гоблинов", "Пауков", "Горилл", "Деревьев", "Скелетов", "Обезьян", "Демонов", "Смерть"};
    public static EventMobCount[] arrquest = new EventMobCount[] {new EventMobCount(new byte[]{(byte)1, (byte)2}, new short[]{(short)80, (short)20}), new EventMobCount(new byte[]{(byte)3, (byte)4, (byte)5}, new short[]{(short)200, (short)60, (short)20}), new EventMobCount(new byte[]{(byte)6, (byte)7, (byte)8, (byte)9}, new short[]{(short)250, (short)150, (short)80, (short)20}), new EventMobCount(new byte[]{(byte)3, (byte)4, (byte)5, (byte)6, (byte)7, (byte)8, (byte)9}, new short[]{(short)100, (short)100, (short)100, (short)100, (short)100, (short)100, (short)30}), new EventMobCount(new byte[]{(byte)3, (byte)4, (byte)5, (byte)6, (byte)7, (byte)8, (byte)10}, new short[]{(short)200, (short)200, (short)200, (short)200, (short)150, (short)100, (short)1})};

    public EventMobCount(byte[] mobid, short[] count)
    {
        this.mobid = mobid;
        this.count = count;
    }

    public static boolean SetContPlayer(ExtendedPlayer Epl, byte idmob, boolean flag)
    {
        if (Epl.idevent < 0)
        {
            return false;
        }
        else if (Epl.idevent > arrquest.length)
        {
            return false;
        }
        else
        {
            EventMobCount cl = arrquest[Epl.idevent];

            for (int i = 0; i < cl.mobid.length; ++i)
            {
                if (cl.mobid[i] == idmob && Epl.idmob == idmob)
                {
                    if (cl.count[i] <= Epl.mobcount + 1)
                    {
                        if (cl.mobid.length <= i + 1)
                        {
                            if (flag)
                            {
                                Epl.player.sendChatToPlayer("Вы закончили прохождения квеста!");
                            }

                            return true;
                        }

                        if (flag)
                        {
                            Epl.idmob = cl.mobid[i + 1];
                            Epl.mobcount = 0;
                            Epl.player.sendChatToPlayer("Вы перешли на следующий уровень квеста!");
                        }
                    }
                    else if (flag)
                    {
                        ++Epl.mobcount;
                        Epl.player.sendChatToPlayer("Получен итем за убийство " + Mobname[idmob]);
                    }
                }
            }

            return false;
        }
    }
}
