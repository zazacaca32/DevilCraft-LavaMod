package net.minecraft.client.addon.tchestplate;

public class AProxyRedonz implements AIrdz
{
    private final boolean isRedon;

    public AProxyRedonz() throws ProxyException
    {
        if (!Utils.isModLoaded("redonz"))
        {
            throw new ProxyException("Redonz mod is null.");
        }
        else
        {
            this.isRedon = this.help();
        }
    }

    private final boolean help()
    {
        try
        {
            boolean var21 = Class.forName("net.minecraft.client.addon.redonz.common.redonzMain") != null;
            return var21;
        }
        catch (ClassNotFoundException var2)
        {
            return false;
        }
    }

    public boolean isRedonz()
    {
        return this.isRedon;
    }
}
