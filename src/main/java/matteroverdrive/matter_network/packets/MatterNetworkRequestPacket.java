package matteroverdrive.matter_network.packets;

import cofh.lib.util.position.BlockPosition;
import matteroverdrive.api.network.IMatterNetworkConnection;
import matteroverdrive.matter_network.MatterNetworkPacket;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by Simeon on 4/27/2015.
 */
public class MatterNetworkRequestPacket extends MatterNetworkPacket
{
    int requestType;
    Object request;

    public MatterNetworkRequestPacket(){super();}
    public MatterNetworkRequestPacket(IMatterNetworkConnection sender,int requestType,ForgeDirection port,Object request)
    {
        this(sender,requestType,port,null,request);
    }
    public MatterNetworkRequestPacket(IMatterNetworkConnection sender,int requestType,ForgeDirection port,BlockPosition receiver,Object request)
    {
        super(sender.getPosition(),port,receiver);
        this.requestType = requestType;
        this.request = request;
    }

    @Override
    public boolean isValid(World world)
    {
        return true;
    }

    @Override
    public String getName()
    {
        return "Request Packet";
    }

    public int getRequestType() {
        return requestType;
    }

    public Object getRequest() {
        return request;
    }
}
