package com.hp.stf.module.vo;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class BaseVo implements Serializable {
	private static final long serialVersionUID = 1L;

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
	}
	
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
	
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
	
	public void copy(Object orig) {
		try {
			BeanUtils.copyProperties(this, orig);
		} catch (InvocationTargetException e) {
			// TODO to add log information but don't interrupt information
			System.out.println(e.getMessage() );
		} catch (IllegalAccessException e) {
			// TODO to add log information but don't interrupt information
			System.out.println(e.getMessage() );
		}
	}
}
