package tom.util.handlers;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import tom.objects.entity.EntityBirdOfPrey;
import tom.objects.entity.EntityCentaur;
import tom.objects.entity.EntityEagle;
import tom.objects.entity.EntityHawk;
import tom.objects.entity.render.RenderCentaur;
import tom.objects.entity.render.RenderEagle;
import tom.objects.entity.render.RenderHawk;

public class RenderHandler 
{
	public static void registerEntityRenderers()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityCentaur.class, new IRenderFactory<EntityCentaur>() 
		{
			@Override
			public Render<? super EntityCentaur> createRenderFor(RenderManager manager) 
			{
				return new RenderCentaur(manager);
			}
		});
		
		RenderingRegistry.registerEntityRenderingHandler(EntityHawk.class, new IRenderFactory<EntityHawk>() 
		{
			@Override
			public Render<? super EntityHawk> createRenderFor(RenderManager manager) 
			{
				return new RenderHawk(manager);
			}
		});
		
		RenderingRegistry.registerEntityRenderingHandler(EntityEagle.class, new IRenderFactory<EntityEagle>() 
		{
			@Override
			public Render<? super EntityEagle> createRenderFor(RenderManager manager) 
			{
				return new RenderEagle(manager);
			}
		});
		
	}
}
