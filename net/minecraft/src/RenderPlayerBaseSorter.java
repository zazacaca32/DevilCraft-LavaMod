package net.minecraft.src;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class RenderPlayerBaseSorter
{
    private Map explicitInferiors;
    private Map explicitSuperiors;
    private Map directInferiorsMap;
    private Map allInferiors;
    private List withoutSuperiors;
    private final List list;
    private final Map allBaseSuperiors;
    private final Map allBaseInferiors;
    private final String methodName;
    private static final Set Empty = new HashSet();

    public RenderPlayerBaseSorter(List var1, Map var2, Map var3, String var4)
    {
        this.list = var1;
        this.allBaseSuperiors = var2;
        this.allBaseInferiors = var3;
        this.methodName = var4;
    }

    public void Sort()
    {
        if (this.list.size() > 1)
        {
            if (this.explicitInferiors != null)
            {
                this.explicitInferiors.clear();
            }

            if (this.explicitSuperiors != null)
            {
                this.explicitSuperiors.clear();
            }

            if (this.directInferiorsMap != null)
            {
                this.directInferiorsMap.clear();
            }

            if (this.allInferiors != null)
            {
                this.allInferiors.clear();
            }

            int var1;

            for (var1 = 0; var1 < this.list.size(); ++var1)
            {
                String var13 = (String)this.list.get(var1);
                String[] var21 = (String[])((String[])this.allBaseInferiors.get(var13));
                boolean var15 = var21 != null && var21.length > 0;
                String[] var18 = (String[])((String[])this.allBaseSuperiors.get(var13));
                boolean var19 = var18 != null && var18.length > 0;

                if ((var15 || var19) && this.directInferiorsMap == null)
                {
                    this.directInferiorsMap = new Hashtable();
                }

                if (var15)
                {
                    this.explicitInferiors = build(var13, this.explicitInferiors, this.directInferiorsMap, (Map)null, var21);
                }

                if (var19)
                {
                    this.explicitSuperiors = build(var13, this.explicitSuperiors, (Map)null, this.directInferiorsMap, var18);
                }
            }

            int var131;
            Set var14;

            if (this.directInferiorsMap != null)
            {
                for (var1 = 0; var1 < this.list.size() - 1; ++var1)
                {
                    for (var131 = var1 + 1; var131 < this.list.size(); ++var131)
                    {
                        String var151 = (String)this.list.get(var1);
                        String var17 = (String)this.list.get(var131);
                        Set var20 = null;
                        var14 = null;

                        if (this.explicitInferiors != null)
                        {
                            var20 = (Set)this.explicitInferiors.get(var151);
                            var14 = (Set)this.explicitInferiors.get(var17);
                        }

                        Set var22 = null;
                        Set var8 = null;

                        if (this.explicitSuperiors != null)
                        {
                            var22 = (Set)this.explicitSuperiors.get(var151);
                            var8 = (Set)this.explicitSuperiors.get(var17);
                        }

                        boolean var9 = var22 != null && var22.contains(var17);
                        boolean var10 = var20 != null && var20.contains(var17);
                        boolean var11 = var8 != null && var8.contains(var151);
                        boolean var12 = var14 != null && var14.contains(var151);

                        if (var9 && var11)
                        {
                            throw new UnsupportedOperationException("Can not sort RenderPlayerBase classes for method \'" + this.methodName + "\'. \'" + var151 + "\' wants to be inferior to \'" + var17 + "\' and \'" + var17 + "\' wants to be inferior to \'" + var151 + "\'");
                        }

                        if (var10 && var12)
                        {
                            throw new UnsupportedOperationException("Can not sort RenderPlayerBase classes for method \'" + this.methodName + "\'. \'" + var151 + "\' wants to be superior to \'" + var17 + "\' and \'" + var17 + "\' wants to be superior to \'" + var151 + "\'");
                        }

                        if (var9 && var10)
                        {
                            throw new UnsupportedOperationException("Can not sort RenderPlayerBase classes for method \'" + this.methodName + "\'. \'" + var151 + "\' wants to be superior and inferior to \'" + var17 + "\'");
                        }

                        if (var11 && var12)
                        {
                            throw new UnsupportedOperationException("Can not sort RenderPlayerBase classes for method \'" + this.methodName + "\'. \'" + var17 + "\' wants to be superior and inferior to \'" + var151 + "\'");
                        }
                    }
                }

                if (this.allInferiors == null)
                {
                    this.allInferiors = new Hashtable();
                }

                for (var1 = 0; var1 < this.list.size(); ++var1)
                {
                    this.build((String)this.list.get(var1), (String)null);
                }
            }

            if (this.withoutSuperiors == null)
            {
                this.withoutSuperiors = new LinkedList();
            }

            var1 = 0;
            var131 = this.list.size();

            while (var131 > 1)
            {
                this.withoutSuperiors.clear();
                int var16;

                for (var16 = var1; var16 < var1 + var131; ++var16)
                {
                    this.withoutSuperiors.add(this.list.get(var16));
                }

                if (this.allInferiors != null)
                {
                    for (var16 = var1; var16 < var1 + var131; ++var16)
                    {
                        Set var181 = (Set)this.allInferiors.get(this.list.get(var16));

                        if (var181 != null)
                        {
                            this.withoutSuperiors.removeAll(var181);
                        }
                    }
                }

                boolean var191 = true;

                for (int var211 = var1; var211 < var1 + var131; ++var211)
                {
                    String var221 = (String)this.list.get(var211);

                    if (this.withoutSuperiors.contains(var221))
                    {
                        if (var191)
                        {
                            var14 = null;

                            if (this.allInferiors != null)
                            {
                                var14 = (Set)this.allInferiors.get(var221);
                            }

                            if (var14 == null || var14.isEmpty())
                            {
                                this.withoutSuperiors.remove(var221);
                                --var131;
                                ++var1;
                                continue;
                            }
                        }

                        this.list.remove(var211--);
                        --var131;
                    }

                    var191 = false;
                }

                this.list.addAll(var1 + var131, this.withoutSuperiors);
            }
        }
    }

    private Set build(String var1, String var2)
    {
        Set var3 = (Set)this.allInferiors.get(var1);

        if (var3 == null)
        {
            var3 = this.build(var1, (Set)null, var2 != null ? var2 : var1);

            if (var3 == null)
            {
                var3 = Empty;
            }

            this.allInferiors.put(var1, var3);
        }

        return var3;
    }

    private Set build(String var1, Set var2, String var3)
    {
        Set var4 = (Set)this.directInferiorsMap.get(var1);

        if (var4 == null)
        {
            return (Set)var2;
        }
        else
        {
            if (var2 == null)
            {
                var2 = new HashSet();
            }

            Iterator var5 = var4.iterator();

            while (var5.hasNext())
            {
                String var6 = (String)var5.next();

                if (var6 == var3)
                {
                    throw new UnsupportedOperationException("Can not sort RenderPlayerBase classes for method \'" + this.methodName + "\'. Circular superiosity found including \'" + var3 + "\'");
                }

                if (this.list.contains(var6))
                {
                    ((Set)var2).add(var6);
                }

                Set var7;

                try
                {
                    var7 = this.build(var6, var3);
                }
                catch (UnsupportedOperationException var9)
                {
                    throw new UnsupportedOperationException("Can not sort RenderPlayerBase classes for method \'" + this.methodName + "\'. Circular superiosity found including \'" + var6 + "\'", var9);
                }

                if (var7 != Empty)
                {
                    ((Set)var2).addAll(var7);
                }
            }

            return (Set)var2;
        }
    }

    private static Map build(String var0, Map var1, Map var2, Map var3, String[] var4)
    {
        if (var1 == null)
        {
            var1 = new Hashtable();
        }

        HashSet var5 = new HashSet();

        for (int var7 = 0; var7 < var4.length; ++var7)
        {
            if (var4[var7] != null)
            {
                var5.add(var4[var7]);
            }
        }

        if (var2 != null)
        {
            getOrCreateSet(var2, var0).addAll(var5);
        }

        if (var3 != null)
        {
            Iterator var71 = var5.iterator();

            while (var71.hasNext())
            {
                getOrCreateSet(var3, (String)var71.next()).add(var0);
            }
        }

        ((Map)var1).put(var0, var5);
        return (Map)var1;
    }

    private static Set getOrCreateSet(Map var0, String var1)
    {
        Object var2 = (Set)var0.get(var1);

        if (var2 == null)
        {
            var0.put(var1, var2 = new HashSet());
        }

        return (Set)var2;
    }
}
