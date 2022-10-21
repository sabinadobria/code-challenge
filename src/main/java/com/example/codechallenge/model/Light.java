package com.example.codechallenge.model;

import java.util.Objects;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Light {
	@NotNull
	@Min(value = 0)
	Integer xCoordinate;
	@NotNull
	@Min(value = 0)
	Integer yCoordinate;

	public Light(Integer xCoordinate, Integer yCoordinate) {
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Light light = (Light) o;
		return Objects.equals(xCoordinate, light.xCoordinate) && Objects.equals(yCoordinate, light.yCoordinate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(xCoordinate, yCoordinate);
	}
}
