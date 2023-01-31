package com.example.demo.common;

/**
 * コンディションを列挙するenum.
 * 
 * @author inagakisaia
 *
 */
public enum Conditions {

	MINT("Mint", 1), NEAR_MINT("Near Mint", 2), EXCELLENT("Excellent", 3), VERY_GOOD("Very Good", 4), GOOD("Good", 5);

	/** キー */
	private final String key;
	/** バリュー */
	private final int value;

	private Conditions(String key, int value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public int getValue() {
		return value;
	}

	/**
	 * valueの値からコンストラクタを返します.
	 * 
	 * @param value バリュー
	 * @return 条件に一致するコンストラタ
	 */
	public static Conditions of(int value) {
		for (Conditions condition : Conditions.values()) {
			if (condition.value == value) {
				return condition;
			}
		}
		throw new IndexOutOfBoundsException("The key of condition doesn't exist.");
	}

}
