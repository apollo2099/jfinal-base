package com.jfinal.base.utils;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

public class ObjectUtils {
	public static boolean isNullOrEmptyString(Object o) {
		if (o == null) {
			return true;
		}
		if ((o instanceof String)) {
			String str = (String) o;
			if (str.length() == 0) {
				return true;
			}
		}
		return false;
	}

	public static boolean isEmpty(Object o) {
		if (o == null) {
			return true;
		}
		if ((o instanceof String)) {
			if (((String) o).length() == 0) {
				return true;
			}
		} else if ((o instanceof Collection)) {
			if (((Collection) o).isEmpty()) {
				return true;
			}
		} else if (o.getClass().isArray()) {
			if (Array.getLength(o) == 0) {
				return true;
			}
		} else if ((o instanceof Map)) {
			if (((Map) o).isEmpty()) {
				return true;
			}
		} else {
			return false;
		}
		return false;
	}

	public static boolean isNotEmpty(Object c) throws IllegalArgumentException {
		return !isEmpty(c);
	}
}
