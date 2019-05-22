package codechicken.nei;

import codechicken.core.gui.GuiCCButton;
import codechicken.core.gui.GuiCCTextField;
import codechicken.core.gui.GuiScrollSlot;
import codechicken.core.inventory.GuiContainerWidget;
import codechicken.core.render.FontUtils;
import codechicken.nei.ContainerPotionCreator;
import java.util.ArrayList;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

public class GuiPotionCreator extends GuiContainerWidget {

   int amplifier = 0;
   GuiCCButton ampDown;
   GuiCCButton ampUp;
   GuiPotionCreator.GuiSlotPotionEffects slotPotionEffects;
   GuiPotionCreator.GuiDurationField durationField;
   ContainerPotionCreator container;


   public GuiPotionCreator(InventoryPlayer inventoryplayer) {
      super(new ContainerPotionCreator(inventoryplayer, new ContainerPotionCreator.InventoryPotionStore()), 176, 208);
      this.container = (ContainerPotionCreator)this.container;
   }

   public void applyEffect() {
      if(this.slotPotionEffects.selectedslot >= 0) {
         this.container.setPotionEffect(this.slotPotionEffects.selectedPotion(), this.durationField.getDurationTicks(), this.amplifier);
      }

   }

   public String translateAmplifier(int amplifier) {
      switch(amplifier) {
      case 0:
         return "I";
      case 1:
         return "II";
      case 2:
         return "III";
      case 3:
         return "IV";
      case 4:
         return "V";
      default:
         return Integer.toString(amplifier);
      }
   }

   public void validateInputButtons() {
      this.ampDown.setEnabled(this.amplifier > 0);
      this.ampUp.setEnabled(this.amplifier < 3);
   }

   protected void b(int par1, int par2) {}

   public void drawBackground() {
      this.fontRenderer.renderEngine.bindTexture("/codechicken/nei/potion.png");
      this.b(0, 0);
      FontUtils.drawCenteredString("Любимые Зелья", this.guiLeft / 2, 4, 4210752);
      this.fontRenderer.drawString("Продолж.", 12, 40, 4210752);
      this.fontRenderer.drawString(" Сила", 19, 73, 4210752);
      FontUtils.drawCenteredString(this.translateAmplifier(this.amplifier), 33, 86, -10461088);
   }

   public void addWidgets() {
      this.add(this.ampDown = (new GuiCCButton(10, 84, 12, 12, "<")).setActionCommand("ampDown"));
      this.add(this.ampUp = (new GuiCCButton(44, 84, 12, 12, ">")).setActionCommand("ampUp"));
      this.add(this.durationField = new GuiPotionCreator.GuiDurationField(15, 53, 35, 12));
      this.add(this.slotPotionEffects = new GuiPotionCreator.GuiSlotPotionEffects(60, 38));
   }

  

   public void actionPerformed(String ident, Object ... params) {
      if(ident.equals("ampDown")) {
         --this.amplifier;
      } else if(ident.equals("ampUp")) {
         ++this.amplifier;
      }

      this.applyEffect();
      this.validateInputButtons();
   }

   public class GuiSlotPotionEffects extends GuiScrollSlot {

      public int selectedslot = -1;
      public boolean enabled = true;
      private ArrayList validPotions = new ArrayList();


      public GuiSlotPotionEffects(int x, int y) {
         super(x, y, 108, 76);
         Potion[] var7 = Potion.potionTypes;
         int var6 = Potion.potionTypes.length;

         for(int var5 = 0; var5 < var6; ++var5) {
            Potion p = var7[var5];
            if(p != null) {
               this.validPotions.add(p);
            }
         }

         this.setSmoothScroll(false);
         this.setContentSize(x, y, this.height);
      }

      public int getSlotHeight() {
         return 19;
      }

      public void drawSlotBox() {
         a(this.x, this.y, this.x + this.width, this.y + this.height, -16777216);
      }

      private void a(int x, int y, int i, int j, int k) {
		// TODO Auto-generated method stub
		
	}

	public void drawOverlay() {}

      public boolean drawLineGuide() {
         return false;
      }

      public int getScrollBarWidth() {
         return 7;
      }

      protected void drawSlot(int slot, int x, int y, int mx, int my, boolean selected) {
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         Potion potion = (Potion)this.validPotions.get(slot);
         PotionEffect effect = this.getEffect(potion.id);
         boolean blank = effect == null;
         if(effect == null) {
            effect = new PotionEffect(potion.id, 1200, 0);
         }

         int shade = this.selectedslot == slot?2:(blank?1:0);
         GuiPotionCreator.this.fontRenderer.renderEngine.bindTexture("/gui/enchant.png");
         this.b(x, y, 0, 166 + this.getSlotHeight() * shade, this.width - 30, this.getSlotHeight());
         this.b(x + this.width - 30, y, this.width - 23, 166 + this.getSlotHeight() * shade, 30, this.getSlotHeight());
         if(potion.hasStatusIcon()) {
            GuiPotionCreator.this.fontRenderer.renderEngine.bindTexture("/gui/inventory.png");
            int name = potion.getStatusIconIndex();
            this.b(x + 1, y + 1, 0 + name % 8 * 18, 198 + name / 8 * 18, 18, 18);
         }

         String name1 = StatCollector.translateToLocal(potion.getName());
         String amp = effect.getAmplifier() > 0?" " + GuiPotionCreator.this.translateAmplifier(effect.getAmplifier()):"";
         int textColour = shade == 0?6839882:(shade == 1?4226832:16777088);
         if(this.fontRenderer.getStringWidth(name1 + amp) < this.width - 20) {
            this.fontRenderer.drawString(name1 + amp, x + 20, y + 1, textColour);
         } else {
            this.fontRenderer.drawString(name1, x + 20, y + 1, textColour);
            FontUtils.drawRightString(amp, x + this.width - 10, y + 10, textColour);
         }

         String duration = Potion.getDurationString(effect);
         textColour = shade == 0?10526880:(shade == 1?8421504:13421772);
         this.fontRenderer.drawStringWithShadow(duration, x + 20, y + 10, textColour);
      }

      private void b(int x, int y, int i, int j, int k, int slotHeight) {
		// TODO Auto-generated method stub
		
	}

	private PotionEffect getEffect(int id) {
         ItemStack potion = GuiPotionCreator.this.container.potionInv.getStackInSlot(0);
         if(potion != null && potion.hasTagCompound() && potion.getTagCompound().hasKey("CustomPotionEffects")) {
            NBTTagList potionTagList = potion.getTagCompound().getTagList("CustomPotionEffects");

            for(int i = 0; i < potionTagList.tagCount(); ++i) {
               PotionEffect effect = PotionEffect.readCustomPotionEffectFromNBT((NBTTagCompound)potionTagList.tagAt(i));
               if(effect.getPotionID() == id) {
                  return effect;
               }
            }
         }

         return null;
      }

      protected int getNumSlots() {
         return this.validPotions.size();
      }

      protected boolean isSlotSelected(int slot) {
         return slot == this.selectedslot;
      }

      protected void slotClicked(int slot, int button, int mx, int my, boolean doubleclick) {
         if(this.enabled) {
            if(button == 0) {
               this.select(slot);
               GuiPotionCreator.this.applyEffect();
            } else if(button == 1) {
               this.deselect();
               GuiPotionCreator.this.container.removePotionEffect(((Potion)this.validPotions.get(slot)).id);
            }

         }
      }

      public void selectNext() {
         if(this.selectedslot >= 0 && this.selectedslot + 1 < this.getNumSlots()) {
            this.select(this.selectedslot + 1);
            this.showSlot(this.selectedslot);
         }

      }

      public void selectPrev() {
         if(this.selectedslot > 0) {
            this.select(this.selectedslot - 1);
            this.showSlot(this.selectedslot);
         }

      }

      public void setEnabled(boolean b) {
         if(b != this.enabled) {
            this.enabled = b;
            if(!this.enabled) {
               this.deselect();
            }

         }
      }

      private void select(int slot) {
         this.selectedslot = slot;
         GuiPotionCreator.this.durationField.setEnabled(true);
         PotionEffect effect = this.getEffect(((Potion)this.validPotions.get(slot)).id);
         if(effect == null) {
            effect = new PotionEffect(((Potion)this.validPotions.get(slot)).id, 1200, 0);
         }

         GuiPotionCreator.this.durationField.setDurationTicks(effect.getDuration());
         GuiPotionCreator.this.amplifier = effect.getAmplifier();
         GuiPotionCreator.this.validateInputButtons();
      }

      private void deselect() {
         this.selectedslot = -1;
         GuiPotionCreator.this.durationField.setEnabled(false);
         GuiPotionCreator.this.ampDown.setEnabled(false);
         GuiPotionCreator.this.ampUp.setEnabled(false);
      }

      public int selectedPotion() {
         return ((Potion)this.validPotions.get(this.selectedslot)).id;
      }

	@Override
	protected void drawSlot(int var1, int var2, int var3, int var4, int var5, boolean var6, float var7) {
		// TODO Auto-generated method stub
		
	}
   }

   public class GuiDurationField extends GuiCCTextField {

      private String baseValue;


      public GuiDurationField(int x, int y, int width, int height) {
         super(x, y, width, height, "100");
         this.setMaxStringLength(4);
         this.setAllowedCharacters("0123456789");
         this.baseValue = this.getText();
      }

      public void setDurationTicks(int i) {
         i /= 20;
         String minutes = Integer.toString(i / 60);
         String seconds = Integer.toString(i % 60);
         if(seconds.length() == 1) {
            seconds = '0' + seconds;
         }

         this.setText(minutes + seconds);
      }

      public void setEnabled(boolean b) {
         super.setEnabled(b);
         if(!this.isEnabled()) {
            this.setText("100");
         }

      }

      public void onFocusChanged() {
         if(this.isFocused()) {
            this.baseValue = this.getText();
         } else if(!this.validateValue()) {
            this.setText(this.baseValue);
         } else {
            GuiPotionCreator.this.applyEffect();
         }

      }

      public void onTextChanged(String oldText) {
         GuiPotionCreator.this.validateInputButtons();
      }

      private boolean validateValue() {
         try {
            int nfe = Integer.parseInt(this.getText());
            return nfe > 1 && this.getDurationTicks() < 32767 && nfe / 100 <= 60 && nfe % 100 < 60;
         } catch (NumberFormatException var2) {
            return false;
         }
      }

      public void drawText() {
         String s = this.getText();
         String seconds = s.substring(Math.max(0, s.length() - 2), Math.max(0, s.length() - 2) + Math.min(s.length(), 2));
         String minutes = s.length() < 3?"":s.substring(0, s.length() - 2);
         int ty = this.y + this.height / 2 - 4;
         int tcolour = this.getTextColour();
         FontUtils.drawCenteredString(":", this.x + this.width / 2 + 1, ty, tcolour);
         FontUtils.drawRightString(seconds, this.x + this.width - 3, ty, tcolour);
         FontUtils.drawRightString(minutes, this.x + this.width / 2 - 1, ty, tcolour);
      }

      public int getTextColour() {
         return this.isFocused()?13421772:9474192;
      }

      public int getDurationTicks() {
         int i = Integer.parseInt(this.getText());
         return (i / 100 * 60 + i % 100) * 20;
      }
   }
}
