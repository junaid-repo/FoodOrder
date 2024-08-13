package com.food.swigato.config;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CodeNinja {

	public static void main(String[] args) {

		List<Integer> arr = List.of(234, 34, 325, 6803, 23, 99, 23, 99);
		Set<Integer> duplicatedNumbersRemovedSet = new HashSet<>();

		System.out.println(
				arr.stream().filter(obj -> !duplicatedNumbersRemovedSet.add(obj)).collect(Collectors.toList()));
	}

}
