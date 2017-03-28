/*
 * This file is part of Matter Overdrive
 * Copyright (c) 2015., Simeon Radivoev, All rights reserved.
 *
 * Matter Overdrive is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Matter Overdrive is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Matter Overdrive.  If not, see <http://www.gnu.org/licenses>.
 */

package matteroverdrive.api.network;

import matteroverdrive.data.BlockPos;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by Simeon on 3/11/2015.
 * Used by all Machines that can Connect to a Matter Network.
 * This also handled if Matter Network cables will be connected to the machine.
 */
public interface IMatterNetworkConnection {
    /**
     * The block position of the Connection.
     * This is the MAC address equivalent.
     * Used mainly in Packet filters to filter the machines the packet can reach.
     *
     * @return the block position of the Matter Network connection.
     */
    BlockPos getPosition();

    /**
     * Can the Matter Connection connect form a given side.
     *
     * @param side the side of the tested connection.
     * @return can the connection be made trough the given side.
     */
    boolean canConnectFromSide(ForgeDirection side);
}
