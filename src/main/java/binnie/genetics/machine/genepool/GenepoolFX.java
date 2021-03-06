package binnie.genetics.machine.genepool;

import binnie.core.BinnieCore;
import binnie.core.machines.IMachine;
import binnie.core.machines.MachineComponent;
import binnie.core.machines.component.IRender;
import net.minecraft.client.particle.Particle;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class GenepoolFX extends MachineComponent implements IRender.DisplayTick {
	public GenepoolFX(final IMachine machine) {
		super(machine);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void onDisplayTick(World world, BlockPos pos, Random rand) {
		if (rand.nextFloat() < 1.0f && this.getUtil().getProcess().isInProgress()) {
			BinnieCore.proxy.getMinecraftInstance().effectRenderer.addEffect(new Particle(world, pos.getX() + 0.3 + rand.nextDouble() * 0.4, pos.getY() + 1, pos.getZ() + 0.3 + rand.nextDouble() * 0.4, 0.0, 0.0, 0.0) {
				double axisX = this.posX;
				double axisZ = this.posZ;
				double angle = this.rand.nextDouble() * 2.0 * 3.1415;

				{
					this.axisX = 0.0;
					this.axisZ = 0.0;
					this.angle = 0.0;
					this.motionX = 0.0;
					this.motionZ = 0.0;
					this.motionY = this.rand.nextFloat() * 0.01;
					this.particleMaxAge = 25;
					this.particleGravity = 0.0f;
					this.field_190017_n = true;
					this.setRBGColorF(0.4f + 0.6f * this.rand.nextFloat(), 0.6f * this.rand.nextFloat(), 0.6f + 0.4f * this.rand.nextFloat());
				}

				@Override
				public void onUpdate() {
					super.onUpdate();
					this.setAlphaF((float) Math.cos(1.57 * this.particleAge / this.particleMaxAge));
				}

				@Override
				public int getFXLayer() {
					return 0;
				}
			});
		}
	}
}
