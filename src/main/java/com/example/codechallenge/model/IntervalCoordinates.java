package com.example.codechallenge.model;

import java.util.Objects;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class IntervalCoordinates {
	@NotNull
	@Min(value = 0)
	Integer startX;
	@NotNull
	@Min(value = 0)
	Integer startY;
	@NotNull
	@Min(value = 0)
	Integer endX;
	@NotNull
	@Min(value = 0)
	Integer endY;

	public IntervalCoordinates() {
	}

	public Integer getStartX() {
		return startX;
	}

	public void setStartX(Integer startX) {
		this.startX = startX;
	}

	public Integer getStartY() {
		return startY;
	}

	public void setStartY(Integer startY) {
		this.startY = startY;
	}

	public Integer getEndX() {
		return endX;
	}

	public void setEndX(Integer endX) {
		this.endX = endX;
	}

	public Integer getEndY() {
		return endY;
	}

	public void setEndY(Integer endY) {
		this.endY = endY;
	}

	public boolean isValid() {
		boolean canSetCoordinates = Objects.nonNull(startX) && Objects.nonNull(endX)
				&& Objects.nonNull(startY) && Objects.nonNull(endY);
		return canSetCoordinates && (endX >= startX) && (endY >= startY);
	}
}
