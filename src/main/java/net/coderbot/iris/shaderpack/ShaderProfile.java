package net.coderbot.iris.shaderpack;

import java.util.*;

public class ShaderProfile {
	private String name = null;
	private final Map<String, String> mapOptionValues = new LinkedHashMap<>();
	private final Set<String> disabledPrograms = new LinkedHashSet<>();

	public ShaderProfile(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void addOptionValue(String option, String value) {
		this.mapOptionValues.put(option, value);
	}

	public void addOptionValues(ShaderProfile prof) {
		if (prof != null) {
			this.mapOptionValues.putAll(prof.mapOptionValues);
		}
	}

	public void applyOptionValues(ShaderOption[] options) {
		for (ShaderOption so : options) {
			String key = so.getName();
			String val = this.mapOptionValues.get(key);
			if (val != null) {
				so.setValue(val);
			}
		}
	}

	public String[] getOptions() {
		Set<String> keys = this.mapOptionValues.keySet();
		return keys.toArray(new String[0]);
	}

	public String getValue(String key) {
		return this.mapOptionValues.get(key);
	}

	public void addDisabledProgram(String program) {
		this.disabledPrograms.add(program);
	}

	public void removeDisabledProgram(String program) {
		this.disabledPrograms.remove(program);
	}

	public Collection<String> getDisabledPrograms() {
		return new LinkedHashSet<>(this.disabledPrograms);
	}

	public void addDisabledPrograms(Collection<String> programs) {
		this.disabledPrograms.addAll(programs);
	}

	public boolean isProgramDisabled(String program) {
		return this.disabledPrograms.contains(program);
	}
}
