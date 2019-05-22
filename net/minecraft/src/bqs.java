package net.minecraft.src;

import cpw.mods.fml.relauncher.*;
import com.google.common.collect.*;
import java.io.*;
import java.util.*;

@SideOnly(Side.CLIENT)
public class bqs extends BaseMod
{
    private static final String __OBFID = "CL_00001076";
    private String field_110597_b;

    public bqs(final File p_i1291_1_)
    {
        super();
    }

    protected InputStream a(final String p_110591_1_) throws IOException
    {
        final File file1 = new File(this.field_110597_b, p_110591_1_);

        if (!file1.exists())
        {
            throw new FileNotFoundException(p_110591_1_);
        }

        return new BufferedInputStream(new FileInputStream(file1));
    }

    protected boolean b(final String p_110593_1_)
    {
        return new File(this.field_110597_b, p_110593_1_).isFile();
    }

    public Set c()
    {
        final HashSet hashset = Sets.newHashSet();
        final File file1 = new File(this.field_110597_b, "assets/");

        if (file1.isDirectory())
        {
            Object DirectoryFileFilter;
            {
                File file2 = null;
                final String s = func_110595_a(file1, file2);

                if (!s.equals(s.toLowerCase()))
                {
                    this.func_110594_c(s);
                }
                else
                {
                    hashset.add(s.substring(0, s.length() - 1));
                }
            }
        }

        return hashset;
    }

    private String func_110595_a(File file1, File file2)
    {
        // TODO Auto-generated method stub
        return null;
    }

    private void func_110594_c(String s)
    {
        // TODO Auto-generated method stub
    }

    @Override
    public String getVersion()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void load()
    {
        // TODO Auto-generated method stub
    }
}
