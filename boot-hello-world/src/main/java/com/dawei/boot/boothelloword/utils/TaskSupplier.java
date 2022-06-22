package com.dawei.boot.boothelloword.utils;

@FunctionalInterface
public interface TaskSupplier<T> {
	
	T get();
	
}
