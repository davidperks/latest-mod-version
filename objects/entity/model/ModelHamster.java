package tom.objects.entity.model;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ModelHamster extends ModelPerksAnimals
{
	
	private static final AxisAlignedBB BODY = new AxisAlignedBB(0.375, 0.062, 0.312, 0.625, 0.25, 0.625);
	private static final AxisAlignedBB FOOT1 = new AxisAlignedBB(0.375, 0, 0.562, 0.438, 0.062, 0.625);
	private static final AxisAlignedBB FOOT2 = new AxisAlignedBB(0.562, 0, 0.562, 0.625, 0.062, 0.625);
	private static final AxisAlignedBB FOOT3 = new AxisAlignedBB(0.375, 0, 0.312, 0.438, 0.062, 0.375);
	private static final AxisAlignedBB FOOT4 = new AxisAlignedBB(0.562, 0, 0.312, 0.625, 0.062, 0.375);
	private static final AxisAlignedBB BUTT = new AxisAlignedBB(0.438, 0.125, 0.25, 0.562, 0.188, 0.312);
	private static final AxisAlignedBB HEAD = new AxisAlignedBB(0.438, 0.125, 0.625, 0.562, 0.25, 0.75);
	/**
	* AxisAlignedBBs and methods getBoundingBox, collisionRayTrace, and collisionRayTrace generated using MrCrayfish's Model Creator <a href="https://mrcrayfish.com/tools?id=mc">https://mrcrayfish.com/tools?id=mc</a>
	*/
	private static final List<AxisAlignedBB> COLLISION_BOXES = Lists.newArrayList(BODY, FOOT1, FOOT2, FOOT3, FOOT4, BUTT, HEAD);
	private static final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB(0.375, 0, 0.25, 0.625, 0.25, 0.75);


	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
	    return BOUNDING_BOX;
	}

	public void addCollisionBoxToList(IBlockState state, World world, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entity, boolean isActualState)
	{
	    entityBox = entityBox.offset(-pos.getX(), -pos.getY(), -pos.getZ());
	    for (AxisAlignedBB box : COLLISION_BOXES)
	    {
	        if (entityBox.intersects(box))
	            collidingBoxes.add(box.offset(pos));
	    }
	}
	
	public RayTraceResult collisionRayTrace(IBlockState state, World world, BlockPos pos, Vec3d start, Vec3d end)
	{
	    double distanceSq;
	    double distanceSqShortest = Double.POSITIVE_INFINITY;
	    RayTraceResult resultClosest = null;
	    RayTraceResult result;
	    start = start.subtract(pos.getX(), pos.getY(), pos.getZ());
	    end = end.subtract(pos.getX(), pos.getY(), pos.getZ());
	    for (AxisAlignedBB box : COLLISION_BOXES)
	    {
	        result = box.calculateIntercept(start, end);
	        if (result == null)
	            continue;

	        distanceSq = result.hitVec.squareDistanceTo(start);
	        if (distanceSq < distanceSqShortest)
	        {
	            distanceSqShortest = distanceSq;
	            resultClosest = result;
	        }
	    }
	    return resultClosest == null ? null : new RayTraceResult(RayTraceResult.Type.BLOCK, resultClosest.hitVec.addVector(pos.getX(), pos.getY(), pos.getZ()), resultClosest.sideHit, pos);
	}
}
