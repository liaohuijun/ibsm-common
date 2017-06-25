package com.hm.common.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author shishun.wang
 * @date 上午10:02:31 2017年6月25日
 * @version 1.0
 * @describe 推荐码生成器
 */
public class RecommendCodeUtil {

	private static final char[] LETTERS = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
			'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	private static final char[] LETTER = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
			'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
	private static final char[] NUMBER = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	private RecommendCodeUtil() {

	}

	public static String genCode() {
		Random rand = new Random();
		List<Character> list = new ArrayList<Character>();
		for (int i = 0; i < 2; i++) {
			list.add(NUMBER[rand.nextInt(NUMBER.length)]);
		}
		for (int i = 0; i < 2; i++) {
			list.add(LETTER[rand.nextInt(LETTER.length)]);
		}
		Collections.shuffle(list);
		StringBuilder sb = new StringBuilder();
		for (char c : list) {
			sb.append(c);
		}
		return sb.toString();
	}

	public static String genCode(int length) {
		Random rand = new Random();
		List<Character> list = new ArrayList<Character>();
		for (int i = 0; i < length; i++) {
			list.add(LETTERS[rand.nextInt(LETTERS.length)]);
		}
		Collections.shuffle(list);
		StringBuilder sb = new StringBuilder();
		for (char c : list) {
			sb.append(c);
		}
		return sb.toString();
	}

	public static String genRecommendCode() {
		Random rand = new Random();
		List<Character> list = new ArrayList<Character>();
		for (int i = 0; i < 6; i++) {
			list.add(LETTERS[rand.nextInt(LETTERS.length)]);
		}
		Collections.shuffle(list);
		StringBuilder sb = new StringBuilder();
		for (char c : list) {
			sb.append(c);
		}
		return sb.toString();
	}

	public static String genRecommendCode(String userId) {
		char[] charArr = userId.toCharArray();
		List<Character> list = new ArrayList<Character>();
		for (char c : charArr) {
			list.add(c);
		}
		Random rand = new Random();
		for (int i = 0; i < 2; i++) {
			list.add(LETTERS[rand.nextInt(LETTERS.length)]);
		}
		Collections.shuffle(list);
		StringBuilder sb = new StringBuilder();
		for (char c : list) {
			sb.append(c);
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(genCode(4));
		System.out.println(LETTERS.length);
		System.out.println(36 * 36 * 36 * 36);
	}
}
