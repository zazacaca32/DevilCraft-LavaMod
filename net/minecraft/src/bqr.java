package net.minecraft.src;

import cpw.mods.fml.relauncher.*;
import com.google.common.base.*;
import java.util.zip.*;
import com.google.common.collect.*;
import java.util.*;
import java.util.regex.*;
import javax.imageio.*;
import java.io.*;
import java.awt.image.*;

@SideOnly(Side.CLIENT)
public class bqr extends bqs implements Closeable
{
    public static final Splitter b;
    private ZipFile c;
    private Object field_110597_b;
    private static final String __OBFID = "CL_00001075";
    static final String[] patterns;

    public bqr(final File p_i1290_1_)
    {
        super(p_i1290_1_);
    }

    private ZipFile d() throws IOException
    {
        if (this.c == null)
        {
            this.c = new ZipFile((String) this.field_110597_b);
        }

        return this.c;
    }

    protected InputStream a(final String p_110591_1_) throws IOException
    {
        final ZipFile zipfile = this.d();
        final ZipEntry zipentry = zipfile.getEntry(p_110591_1_);

        if (zipentry == null)
        {
        }

        return filterStream(p_110591_1_, zipfile.getInputStream(zipentry));
    }

    public boolean b(final String p_110593_1_)
    {
        try
        {
            return this.d().getEntry(p_110593_1_) != null;
        }
        catch (IOException ioexception)
        {
            return false;
        }
    }

    public Set c()
    {
        ZipFile zipfile;

        try
        {
            zipfile = this.d();
        }
        catch (IOException ioexception)
        {
            return Collections.emptySet();
        }

        final Enumeration enumeration = zipfile.entries();
        final HashSet hashset = Sets.newHashSet();

        while (enumeration.hasMoreElements())
        {
            final ZipEntry zipentry = (ZipEntry) enumeration.nextElement();
            final String s = zipentry.getName();

            if (s.startsWith("assets/"))
            {
                final ArrayList arraylist = Lists.newArrayList(bqr.b.split((CharSequence)s));

                if (arraylist.size() <= 1)
                {
                    continue;
                }

                final String s2 = (String) arraylist.get(1);

                if (!s2.equals(s2.toLowerCase()))
                {
                    this.func_110594_c(s2);
                }
                else
                {
                    hashset.add(s2);
                }
            }
        }

        return hashset;
    }

    private void func_110594_c(String s2)
    {
        // TODO Auto-generated method stub
    }

    protected void finalize() throws Throwable
    {
        this.close();
        super.finalize();
    }

    public void close() throws IOException
    {
        if (this.c != null)
        {
            this.c.close();
            this.c = null;
        }
    }

    public static InputStream filterStream(String entryname, final InputStream is) throws IOException
    {
        try
        {
            entryname = entryname.replace('\\', '/');

            if (entryname.contains("assets/minecraft/textures/blocks/") && entryname.endsWith(".png"))
            {
                final String ex = entryname.substring(entryname.lastIndexOf(47) + 1, entryname.lastIndexOf(".png"));

                for (final String r : bqr.patterns)
                {
                    if (Pattern.compile("^" + r + "$").matcher(ex).matches())
                    {
                        return is;
                    }
                }

                final BufferedImage var9 = ImageIO.read(is);
                final int w = var9.getWidth();
                final int h = var9.getHeight();
                final int[] var10 = var9.getRGB(0, 0, w, h, null, 0, w);

                for (int baos = 0; baos < var10.length; ++baos)
                {
                    final int[] array = var10;
                    final int n = baos;
                    array[n] |= 0xFF000000;
                }

                var9.setRGB(0, 0, w, h, var10, 0, w);
                final ByteArrayOutputStream var11 = new ByteArrayOutputStream();
                ImageIO.write(var9, "png", var11);
                return new ByteArrayInputStream(var11.toByteArray());
            }

            return is;
        }
        catch (Exception var12)
        {
            var12.printStackTrace();
            return null;
        }
    }

    static
    {
        b = Splitter.on('/').omitEmptyStrings().limit(3);
        patterns = new String[] { "redstone_block", "redstone_dust_cross", "redstone_dust_cross_overlay", "redstone_dust_line", "redstone_dust_line_overlay", "nether_wart_stage_0", "nether_wart_stage_1", "nether_wart_stage_2", "double_plant_sunflower_back", "double_plant_sunflower_bottom", "double_plant_sunflower_front", "double_plant_sunflower_top", "double_plant_paeonia_bottom", "double_plant_paeonia_top", "water_flow", "water_still", "glass_pane_top", "glass_pane_top_black", "glass_pane_top_blue", "glass_pane_top_brown", "glass_pane_top_cyan", "glass_pane_top_gray", "glass_pane_top_green", "glass_pane_top_light_blue", "glass_pane_top_lime", "glass_pane_top_magenta", "glass_pane_top_orange", "glass_pane_top_pink", "glass_pane_top_purple", "glass_pane_top_red", "glass_pane_top_silver", "glass_pane_top_white", "glass_pane_top_yellow", "leaves_acacia", "leaves_acacia_opaque", "leaves_big_oak", "leaves_big_oak_opaque", "double_plant_fern_bottom", "double_plant_fern_top", "double_plant_paeonia_bootom", "double_plant_paeonia_top", "double_plant_grass_bottom", "double_plant_grass_top", "double_plant_rose_bottom", "double_plant_rose_top", "double_plant_sunflower_back", "double_plant_sunflower_bottom", "double_plant_sunflower_front", "double_plant_sundlower_top", "double_plant_syringa_bottom", "double_plant_syringa_top", "flower_houstonia", "flower_oxeye_daisy", "flower_rose", "glass_black", "glass_blue", "glass_brown", "glass_cyan", "glass_gray", "glass_green", "glass_light_blue", "glass_lime", "glass_pink", "glass_purple", "glass_red", "glass_silver", "glass_white", "glass_yellow", "glass_magenta", "glass_orange", "glass_pane_top", "glass_pane_top_black", "flower_blue_orchid", "acacia_sapling", "flower_dandelion", "dark_oak_sapling", "flower_allium", "flower_paeonia", "flower_tulip_orange", "flower_tulip_pink", "flower_tulip_red", "flower_tulip_white", "iron_bars", "rail_(activator|activator_powered|detector|detector_powered|golden|golden_powered|normal|normal_turned)?", "anvil_(top(_damaged_[1-2])?|base)", "bed_(feet|head)_(end|side|top)", "brewing_stand", "cactus_(bottom|side|top)", "cake_(bottom|inner|side|top)", "carrots_stage_[0-3]", "cauldron_(bottom|inner|side|top)", "cocoa_stage_[0-2]", "deadbush", "destroy_stage_[0-9]", "detectorRail", "door_(iron|wood)_(lower|upper)", "fenceIron", "fern", "fire_layer_[0-1]", "flower_(pot|dandelion|rose)?", "glass", "goldenRail(_powered)?", "grass_side_overlay", "hopper_top", "ice", "ladder", "lava(_flow)?", "leaves_(birch|birch_opaque|jungle|jungle_opaque|oak|oak_opaque|spruce|spruce_opaque)?", "lever", "mob_spawner", "mushroom_(brown|red)", "netherStalk_[0-2]", "portal", "potatoes_stage_[0-3]", "redstoneDust_(cross|line)(_overlay)?", "redstone_torch_(on|off)?", "trip_wire(_source)?", "reeds", "sapling(_(oak|birch|jungle|spruce|acacia|oak|roofed_oak))?", "stem_(bent|straight)", "tallgrass", "thinglass_top", "torch_on", "trapdoor", "tripWire(Source)?", "vine", "water(_flow)?", "waterlily", "wheat_stage_[0-7]", "melon_stem_(connected|disconnected)?", "pumpkin_stem_(connected|disconnected)?", "nether_wa rt_stage_[0-2]", "web" };
    }
}
