package com.MO.MatterOverdrive.data.network;

import com.MO.MatterOverdrive.api.network.IMatterNetworkConnectionProxy;
import com.MO.MatterOverdrive.api.network.MatterNetworkTask;
import cpw.mods.fml.common.FMLLog;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Simeon on 4/19/2015.
 */
public class MatterNetworkTaskQueue<T extends MatterNetworkTask>
{
    IMatterNetworkConnectionProxy entity;
    protected List<T> tasks;
    int capacity = 0;
    Class<? extends MatterNetworkTask> type;

    public MatterNetworkTaskQueue(IMatterNetworkConnectionProxy entity, int capacity,Class<? extends MatterNetworkTask> type)
    {
        this.entity = entity;
        tasks = new ArrayList<T>(capacity);
        this.capacity = capacity;
        this.type = type;
    }

    public boolean queueTask(T task)
    {
        if (tasks.size() > 0)
        {
            try
            {
                tasks.add(tasks.size()-1,task);
                return true;
            }
            catch (Exception e)
            {
                FMLLog.severe("Could not add to Taks Queue: ", e);
                return false;
            }
        }
        else
        {
            return tasks.add(task);
        }

    }

    public T dequeueTask()
    {
        if (tasks.size() > 0)
        {
            return tasks.remove(tasks.size() - 1);
        }
        return null;
    }

    public T peek()
    {
        if (tasks.size() > 0)
        {
            return tasks.get(tasks.size() - 1);
        }
        return null;
    }

    public int getLastIndex()
    {
        if (tasks.size() > 0)
        {
            return tasks.size()-1;
        }
        return -1;
    }

    public T getAt(int i)
    {
        if (i >= 0 && i < tasks.size())
        {
            return tasks.get(i);
        }
        return null;
    }

    public boolean remove(T task)
    {
        return tasks.remove(task);
    }

    public int size()
    {
        return tasks.size();
    }

    public int remaintingCapacity()
    {
        return capacity - tasks.size();
    }

    public void readFromNBT(NBTTagCompound tagCompound)
    {
        if (tagCompound == null)
            return;

        tasks.clear();
        NBTTagList tagList = tagCompound.getTagList("Tasks",10);
        for (int i = 0; i < tagList.tagCount();i++)
        {
            try
            {
                T task = (T)type.newInstance();
                task.readFromNBT(tagList.getCompoundTagAt(i));
                tasks.add(task);
            }
            catch (InstantiationException e)
            {
                e.printStackTrace();
            }
            catch (IllegalAccessException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void writeToNBT(NBTTagCompound tagCompound)
    {
        NBTTagList taskList = new NBTTagList();
        for (T task : tasks)
        {
            NBTTagCompound taskNBT = new NBTTagCompound();
            task.writeToNBT(taskNBT);
            taskList.appendTag(taskNBT);
        }
        tagCompound.setTag("Tasks",taskList);
    }
}
