package net.minecraft.client.addon.tchestplate.fx;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class FXLightningBoltCommon
{
    ArrayList segments;
    WRVector3 start;
    WRVector3 end;
    HashMap splitparents;
    public float multiplier;
    public float length;
    public int numsegments0;
    public int increment;
    public int type;
    public boolean nonLethal;
    private int numsplits;
    private boolean finalized;
    private Random rand;
    public long seed;
    public int particleAge;
    public int particleMaxAge;
    private World world;
    public static final float speed = 3.0F;
    public static final int fadetime = 20;

    public FXLightningBoltCommon(World world, WRVector3 jammervec, WRVector3 targetvec, long seed)
    {
        this.type = 0;
        this.nonLethal = false;
        this.segments = new ArrayList();
        this.splitparents = new HashMap();
        this.world = world;
        this.start = jammervec;
        this.end = targetvec;
        this.seed = seed;
        this.rand = new Random(seed);
        this.numsegments0 = 1;
        this.increment = 1;
        this.length = this.end.copy().sub(this.start).length();
        this.particleMaxAge = 3 + this.rand.nextInt(3) - 1;
        this.multiplier = 1.0F;
        this.particleAge = -((int)(this.length * 3.0F));
        this.segments.add(new FXLightningBoltCommon.Segment(this.start, this.end));
    }

    public FXLightningBoltCommon(World world, Entity detonator, Entity target, long seed)
    {
        this(world, new WRVector3(detonator), new WRVector3(target), seed);
    }

    public FXLightningBoltCommon(World world, Entity detonator, Entity target, long seed, int speed)
    {
        this(world, new WRVector3(detonator), new WRVector3(target.posX, target.posY + (double)target.getEyeHeight() - 0.699999988079071D, target.posZ), seed);
        this.increment = speed;
        this.multiplier = 0.4F;
    }

    public FXLightningBoltCommon(World world, TileEntity detonator, Entity target, long seed)
    {
        this(world, new WRVector3(detonator), new WRVector3(target), seed);
    }

    public FXLightningBoltCommon(World world, TileEntity detonator, double x, double y, double z, long seed)
    {
        this(world, new WRVector3(detonator), new WRVector3(x, y, z), seed);
    }

    public FXLightningBoltCommon(World world, double x1, double y1, double z1, double x, double y, double z, long seed, int duration, float multi)
    {
        this(world, new WRVector3(x1, y1, z1), new WRVector3(x, y, z), seed);
        this.particleMaxAge = duration + this.rand.nextInt(duration) - duration / 2;
        this.multiplier = multi;
    }

    public FXLightningBoltCommon(World world, double x1, double y1, double z1, double x, double y, double z, long seed, int duration, float multi, int speed)
    {
        this(world, new WRVector3(x1, y1, z1), new WRVector3(x, y, z), seed);
        this.particleMaxAge = duration + this.rand.nextInt(duration) - duration / 2;
        this.multiplier = multi;
        this.increment = speed;
    }

    public void setMultiplier(float m)
    {
        this.multiplier = m;
    }

    public void fractal(int splits, float amount, float splitchance, float splitlength, float splitangle)
    {
        if (!this.finalized)
        {
            ArrayList oldsegments = this.segments;
            this.segments = new ArrayList();
            FXLightningBoltCommon.Segment prev = null;
            Iterator iterator = oldsegments.iterator();

            while (iterator.hasNext())
            {
                FXLightningBoltCommon.Segment segment = (FXLightningBoltCommon.Segment)iterator.next();
                prev = segment.prev;
                WRVector3 subsegment = segment.diff.copy().scale(1.0F / (float)splits);
                FXLightningBoltCommon.BoltPoint[] newpoints = new FXLightningBoltCommon.BoltPoint[splits + 1];
                WRVector3 startpoint = segment.startpoint.point;
                newpoints[0] = segment.startpoint;
                newpoints[splits] = segment.endpoint;
                int i;
                WRVector3 splitrot;

                for (i = 1; i < splits; ++i)
                {
                    WRVector3 next = WRVector3.getPerpendicular(segment.diff).rotate(this.rand.nextFloat() * 360.0F, segment.diff);
                    next.scale((this.rand.nextFloat() - 0.5F) * amount);
                    splitrot = startpoint.copy().add(subsegment.copy().scale((float)i));
                    newpoints[i] = new FXLightningBoltCommon.BoltPoint(splitrot, next);
                }

                for (i = 0; i < splits; ++i)
                {
                    FXLightningBoltCommon.Segment var18 = new FXLightningBoltCommon.Segment(newpoints[i], newpoints[i + 1], segment.light, segment.segmentno * splits + i, segment.splitno);
                    var18.prev = prev;

                    if (prev != null)
                    {
                        prev.next = var18;
                    }

                    if (i != 0 && this.rand.nextFloat() < splitchance)
                    {
                        splitrot = WRVector3.xCrossProduct(var18.diff).rotate(this.rand.nextFloat() * 360.0F, var18.diff);
                        WRVector3 diff = var18.diff.copy().rotate((this.rand.nextFloat() * 0.66F + 0.33F) * splitangle, splitrot).scale(splitlength);
                        ++this.numsplits;
                        this.splitparents.put(Integer.valueOf(this.numsplits), Integer.valueOf(var18.splitno));
                        FXLightningBoltCommon.Segment split = new FXLightningBoltCommon.Segment(newpoints[i], new FXLightningBoltCommon.BoltPoint(newpoints[i + 1].basepoint, newpoints[i + 1].offsetvec.copy().add(diff)), segment.light / 2.0F, var18.segmentno, this.numsplits);
                        split.prev = prev;
                        this.segments.add(split);
                    }

                    prev = var18;
                    this.segments.add(var18);
                }

                if (segment.next != null)
                {
                    segment.next.prev = prev;
                }
            }

            this.numsegments0 *= splits;
        }
    }

    public void defaultFractal()
    {
        this.fractal(2, this.length * this.multiplier / 8.0F, 0.7F, 0.1F, 45.0F);
        this.fractal(2, this.length * this.multiplier / 12.0F, 0.5F, 0.1F, 50.0F);
        this.fractal(2, this.length * this.multiplier / 17.0F, 0.5F, 0.1F, 55.0F);
        this.fractal(2, this.length * this.multiplier / 23.0F, 0.5F, 0.1F, 60.0F);
        this.fractal(2, this.length * this.multiplier / 30.0F, 0.0F, 0.0F, 0.0F);
        this.fractal(2, this.length * this.multiplier / 34.0F, 0.0F, 0.0F, 0.0F);
        this.fractal(2, this.length * this.multiplier / 40.0F, 0.0F, 0.0F, 0.0F);
    }

    private void calculateCollisionAndDiffs()
    {
        HashMap lastactivesegment = new HashMap();
        Collections.sort(this.segments, new FXLightningBoltCommon.SegmentSorter());
        int lastsplitcalc = 0;
        int lastactiveseg = 0;
        FXLightningBoltCommon.Segment segment;

        for (Iterator segment1 = this.segments.iterator(); segment1.hasNext(); lastactiveseg = segment.segmentno)
        {
            segment = (FXLightningBoltCommon.Segment)segment1.next();

            if (segment.splitno > lastsplitcalc)
            {
                lastactivesegment.put(Integer.valueOf(lastsplitcalc), Integer.valueOf(lastactiveseg));
                lastsplitcalc = segment.splitno;
                lastactiveseg = ((Integer)lastactivesegment.get(this.splitparents.get(Integer.valueOf(segment.splitno)))).intValue();
            }
        }

        lastactivesegment.put(Integer.valueOf(lastsplitcalc), Integer.valueOf(lastactiveseg));
        lastsplitcalc = 0;
        lastactiveseg = ((Integer)lastactivesegment.get(Integer.valueOf(0))).intValue();
        FXLightningBoltCommon.Segment segment11;

        for (Iterator iterator = this.segments.iterator(); iterator.hasNext(); segment11.calcEndDiffs())
        {
            segment11 = (FXLightningBoltCommon.Segment)iterator.next();

            if (lastsplitcalc != segment11.splitno)
            {
                lastsplitcalc = segment11.splitno;
                lastactiveseg = ((Integer)lastactivesegment.get(Integer.valueOf(segment11.splitno))).intValue();
            }

            if (segment11.segmentno > lastactiveseg)
            {
                iterator.remove();
            }
        }
    }

    public void finalizeBolt()
    {
        if (!this.finalized)
        {
            this.finalized = true;
            this.calculateCollisionAndDiffs();
            Collections.sort(this.segments, new FXLightningBoltCommon.SegmentLightSorter());
        }
    }

    public void onUpdate()
    {
        this.particleAge += this.increment;

        if (this.particleAge > this.particleMaxAge)
        {
            this.particleAge = this.particleMaxAge;
        }
    }

    public class SegmentSorter implements Comparator
    {
        final FXLightningBoltCommon this$0 = FXLightningBoltCommon.this;

        public int compare(FXLightningBoltCommon.Segment o1, FXLightningBoltCommon.Segment o2)
        {
            int comp = Integer.valueOf(o1.splitno).compareTo(Integer.valueOf(o2.splitno));
            return comp == 0 ? Integer.valueOf(o1.segmentno).compareTo(Integer.valueOf(o2.segmentno)) : comp;
        }

        public int compare(Object obj, Object obj1)
        {
            return this.compare((FXLightningBoltCommon.Segment)obj, (FXLightningBoltCommon.Segment)obj1);
        }
    }

    public class SegmentLightSorter implements Comparator
    {
        final FXLightningBoltCommon this$0 = FXLightningBoltCommon.this;

        public int compare(FXLightningBoltCommon.Segment o1, FXLightningBoltCommon.Segment o2)
        {
            return Float.compare(o2.light, o1.light);
        }

        public int compare(Object obj, Object obj1)
        {
            return this.compare((FXLightningBoltCommon.Segment)obj, (FXLightningBoltCommon.Segment)obj1);
        }
    }

    public class Segment
    {
        public FXLightningBoltCommon.BoltPoint startpoint;
        public FXLightningBoltCommon.BoltPoint endpoint;
        public WRVector3 diff;
        public FXLightningBoltCommon.Segment prev;
        public FXLightningBoltCommon.Segment next;
        public WRVector3 nextdiff;
        public WRVector3 prevdiff;
        public float sinprev;
        public float sinnext;
        public float light;
        public int segmentno;
        public int splitno;
        final FXLightningBoltCommon this$0;

        public void calcDiff()
        {
            this.diff = this.endpoint.point.copy().sub(this.startpoint.point);
        }

        public void calcEndDiffs()
        {
            WRVector3 nextdiffnorm;
            WRVector3 thisdiffnorm;

            if (this.prev != null)
            {
                nextdiffnorm = this.prev.diff.copy().normalize();
                thisdiffnorm = this.diff.copy().normalize();
                this.prevdiff = thisdiffnorm.add(nextdiffnorm).normalize();
                this.sinprev = (float)Math.sin((double)(WRVector3.anglePreNorm(thisdiffnorm, nextdiffnorm.scale(-1.0F)) / 2.0F));
            }
            else
            {
                this.prevdiff = this.diff.copy().normalize();
                this.sinprev = 1.0F;
            }

            if (this.next != null)
            {
                nextdiffnorm = this.next.diff.copy().normalize();
                thisdiffnorm = this.diff.copy().normalize();
                this.nextdiff = thisdiffnorm.add(nextdiffnorm).normalize();
                this.sinnext = (float)Math.sin((double)(WRVector3.anglePreNorm(thisdiffnorm, nextdiffnorm.scale(-1.0F)) / 2.0F));
            }
            else
            {
                this.nextdiff = this.diff.copy().normalize();
                this.sinnext = 1.0F;
            }
        }

        public String toString()
        {
            return this.startpoint.point.toString() + " " + this.endpoint.point.toString();
        }

        public Segment(FXLightningBoltCommon.BoltPoint start, FXLightningBoltCommon.BoltPoint end, float light, int segmentnumber, int splitnumber)
        {
            this.this$0 = FXLightningBoltCommon.this;
            this.startpoint = start;
            this.endpoint = end;
            this.light = light;
            this.segmentno = segmentnumber;
            this.splitno = splitnumber;
            this.calcDiff();
        }

        public Segment(WRVector3 start, WRVector3 end)
        {
            this(FXLightningBoltCommon.this.new BoltPoint(start, new WRVector3(0.0D, 0.0D, 0.0D)), FXLightningBoltCommon.this.new BoltPoint(end, new WRVector3(0.0D, 0.0D, 0.0D)), 1.0F, 0, 0);
        }
    }

    public class BoltPoint
    {
        WRVector3 point;
        WRVector3 basepoint;
        WRVector3 offsetvec;
        final FXLightningBoltCommon this$0 = FXLightningBoltCommon.this;

        public BoltPoint(WRVector3 basepoint, WRVector3 offsetvec)
        {
            this.point = basepoint.copy().add(offsetvec);
            this.basepoint = basepoint;
            this.offsetvec = offsetvec;
        }
    }
}
