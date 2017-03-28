package matteroverdrive.data.transport;

import matteroverdrive.api.matter.IMatterHandler;
import matteroverdrive.api.transport.IPipe;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by Simeon on 12/28/2015.
 */
public interface IFluidPipe extends IPipe<FluidPipeNetwork>, IMatterHandler {
    TileEntity getTile();

    void onNetworkUpdate();
}
