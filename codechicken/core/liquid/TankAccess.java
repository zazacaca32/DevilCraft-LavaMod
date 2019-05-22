package codechicken.core.liquid;

import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.liquids.ITankContainer;
import net.minecraftforge.liquids.LiquidStack;

public class TankAccess {

   public ITankContainer tank;
   public ForgeDirection side;


   public TankAccess(ITankContainer tank, ForgeDirection side) {
      this.tank = tank;
      this.side = side;
   }

   public TankAccess(ITankContainer tank, int side) {
      this(tank, ForgeDirection.getOrientation(side));
   }

   public int fill(LiquidStack resource, boolean doFill) {
      return this.tank.fill(this.side, resource, doFill);
   }

   public LiquidStack drain(int maxDrain, boolean doDrain) {
      return this.tank.drain(this.side, maxDrain, doDrain);
   }
}
