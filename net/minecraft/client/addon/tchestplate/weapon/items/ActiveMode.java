package net.minecraft.client.addon.tchestplate.weapon.items;

public enum ActiveMode
{
    ACTIVE("ACTIVE", 0, "ACTIVE", 0, "ACTIVE", 0),
    SILK("SILK", 1, "SILK", 1, "SILK", 1),
    FORTUNE("FORTUNE", 2, "FORTUNE", 2, "FORTUNE", 2);

    private static final ActiveMode[] $VALUES;
    private static final ActiveMode[] $VALUES$;
    private static final ActiveMode[] $VALUES$$;

    private ActiveMode(String var11, int var21, String var111, int var211, String var1111, int var2111)
    {
    }

    public String getActiveModeStr()
    {
        switch (values()[this.ordinal()].ordinal())
        {
            case 1:
                return "Шелковое касание";

            case 2:
                return "Удача";

            default:
                return "Не установлен";
        }
    }

    static {
        $VALUES = new ActiveMode[]{ACTIVE, SILK, FORTUNE};
        $VALUES$ = new ActiveMode[]{ACTIVE, SILK, FORTUNE};
        $VALUES$$ = new ActiveMode[]{ACTIVE, SILK, FORTUNE};
    }

    static class NamelessClass1897102105 {
        static final int[] $SwitchMap$net$minecraft$client$addon$tchestplate$weapon$items$ActiveMode = new int[ActiveMode.values().length];

        static {
            try {
                $SwitchMap$net$minecraft$client$addon$tchestplate$weapon$items$ActiveMode[ActiveMode.SILK.ordinal()] = 1;
            }
            catch (NoSuchFieldError var2)
            {
                ;
            }

            try {
                $SwitchMap$net$minecraft$client$addon$tchestplate$weapon$items$ActiveMode[ActiveMode.FORTUNE.ordinal()] = 2;
            }
            catch (NoSuchFieldError var1)
            {
                ;
            }
        }
    }

    static class NamelessClass1129700790 {
        static final int[] $SwitchMap$tchestplate$weapon$items$ActiveMode = new int[ActiveMode.values().length];

        static {
            try {
                $SwitchMap$tchestplate$weapon$items$ActiveMode[ActiveMode.SILK.ordinal()] = 1;
            }
            catch (NoSuchFieldError var2)
            {
                ;
            }

            try {
                $SwitchMap$tchestplate$weapon$items$ActiveMode[ActiveMode.FORTUNE.ordinal()] = 2;
            }
            catch (NoSuchFieldError var1)
            {
                ;
            }
        }
    }
}
