package net.coderbot.iris.vertices;

import com.google.common.collect.ImmutableMap;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormatElement;
import net.minecraft.client.render.VertexFormats;

public class IrisVertexFormats {
	public static final VertexFormatElement ENTITY_ELEMENT;
	public static final VertexFormatElement MID_TEXTURE_ELEMENT;
	public static final VertexFormatElement TANGENT_ELEMENT;
	public static final VertexFormatElement VELOCITY_ELEMENT;
	public static final VertexFormatElement MID_BLOCK_ELEMENT;

	public static final VertexFormat TERRAIN;

	static {
		ENTITY_ELEMENT = new VertexFormatElement(11, VertexFormatElement.DataType.FLOAT, VertexFormatElement.Type.GENERIC, 4);
		MID_TEXTURE_ELEMENT = new VertexFormatElement(12, VertexFormatElement.DataType.FLOAT, VertexFormatElement.Type.GENERIC, 2);
		TANGENT_ELEMENT = new VertexFormatElement(13, VertexFormatElement.DataType.FLOAT, VertexFormatElement.Type.GENERIC, 4);
		VELOCITY_ELEMENT = new VertexFormatElement(14, VertexFormatElement.DataType.FLOAT, VertexFormatElement.Type.GENERIC, 4);
		MID_BLOCK_ELEMENT = new VertexFormatElement(15, VertexFormatElement.DataType.FLOAT, VertexFormatElement.Type.GENERIC, 4);

		ImmutableMap.Builder<String, VertexFormatElement> elements = ImmutableMap.builder();

		// TODO(21w10a): Audit this
		elements.put("Position", VertexFormats.POSITION_ELEMENT);
		elements.put("Color", VertexFormats.COLOR_ELEMENT);
		elements.put("UV0", VertexFormats.TEXTURE_ELEMENT);
		elements.put("UV2", VertexFormats.LIGHT_ELEMENT);
		elements.put("Normal", VertexFormats.NORMAL_ELEMENT);
		elements.put("Padding", VertexFormats.PADDING_ELEMENT);
		elements.put("mc_Entity", ENTITY_ELEMENT);
		elements.put("mc_midTexCoord", MID_TEXTURE_ELEMENT);
		elements.put("at_tangent", TANGENT_ELEMENT);
		elements.put("at_velocity", VELOCITY_ELEMENT);
		elements.put("at_midBlock", MID_BLOCK_ELEMENT);

		TERRAIN = new VertexFormat(elements.build());
	}
}
