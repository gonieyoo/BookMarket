package com.market.bookitem;
/* 추상 클래스 용도
공통된 필드와 메서드를 통일할 목적
실체클래스 구현시, 시간 절약
규격에 맞는 실체 클래스 구현 */

/* 추상 메서드는 자식 클래스에서 구현되며 반드시 오버라이딩을 해야하는 메서드 */

public abstract class Item {
	String bookId;
	String name;
	int unitPrice;

	public Item(String bookId, String name, int unitPrice) {
		this.bookId = bookId;
		this.name = name;
		this.unitPrice = unitPrice;
	}

	abstract String getBookId();

	abstract String getName();

	abstract int getUnitPrice();

	abstract void setBookId(String bookId);

	abstract void setName(String name);

	abstract void setUnitPrice(int unitPrice);

}