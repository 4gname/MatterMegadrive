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

package matteroverdrive.client.render.conversation;

import matteroverdrive.util.MOPhysicsHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

/**
 * Created by Simeon on 8/9/2015.
 */
public class DialogShotClose extends DialogShot {
    private float maxZoom;
    private float minZoom;

    public DialogShotClose(float maxZoom, float minZoom) {
        this.maxZoom = maxZoom;
        this.minZoom = minZoom;
    }

    @Override
    public boolean positionCamera(EntityLivingBase active, EntityLivingBase other, float ticks, EntityRendererConversation rendererConversation) {
        Vec3 look = rendererConversation.getLook(other, active, ticks);
        double distance = look.lengthVector();
        double clammpedDistance = MathHelper.clamp_double(distance, minZoom, maxZoom);
        look.yCoord *= 0;
        look = look.normalize();

        Vec3 pos = rendererConversation.getPosition(active, ticks, false).addVector(0, active.getEyeHeight() - 0.1, 0).addVector(look.xCoord * clammpedDistance, look.yCoord * clammpedDistance, look.zCoord * clammpedDistance);
        MovingObjectPosition movingObjectPosition = MOPhysicsHelper.rayTrace(rendererConversation.getPosition(active, ticks, false), active.worldObj, maxZoom, ticks, Vec3.createVectorHelper(0, active.getEyeHeight(), 0), true, true, look.normalize(), active);
        if (movingObjectPosition != null) {
            pos = movingObjectPosition.hitVec;
        }
        Vec3 left = look.crossProduct(Vec3.createVectorHelper(0, 1, 0));
        float leftAmount = 0.1f;
        rendererConversation.setCameraPosition(pos.xCoord + left.xCoord * leftAmount, pos.yCoord + left.yCoord * leftAmount, pos.zCoord + left.zCoord * leftAmount);
        rendererConversation.rotateCameraYawTo(look.normalize(), 90);
        rendererConversation.setCameraPitch(10);
        return true;
    }
}
