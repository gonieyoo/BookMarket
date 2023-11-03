package com.market.cart;

import com.market.bookitem.Book;

public class CartItem {

	// private String[] itemBook = new String[7];
	private Book itemBook; // 'Book' 클래스의 객체를 참조하는 변수
	private String bookID; // 장바구니에 담긴 도서 ID를 저장하는 변수
	private int quantity; // 장바구니에 담긴 도서의 수량을 저장하는 변수
	private int totalPrice; // 장바구니에 담긴 도서의 가격을 저장하는 변수

	public CartItem() { // 매개변수가 없는 기본 생성자
	}

	/*
	 * 기존 내용 주석 처리
	 * public CartItem(String[] book) {
	 * this.itemBook = book;
	 * this.bookID = book[0];
	 * this.quantity = 1;
	 * updateTotalPrice();
	 * }
	 * 
	 * public String[] getItemBook() {
	 * return itemBook;
	 * }
	 * public void setItemBook(String[] itemBook) {
	 * this.itemBook = itemBook;
	 * }
	 */

	public CartItem(Book booklist) { // 'Book' 객체를 받아와서 해당 도서를 'CartItem' 객체에 저장
		this.itemBook = booklist;
		this.bookID = booklist.getBookId();
		this.quantity = 1;
		updateTotalPrice();
	}

	public Book getItemBook() { // 'Book' 객체를 반환하는 메소드
		return itemBook;
	}

	public void setItemBook(Book itemBook) { // 'Book' 객체 설정 메소드
		this.itemBook = itemBook;
	}

	public void setTotalPrice(int totalPrice) { // 장바구니에 담긴 도서의 가격을 설정하는 메소드
		this.totalPrice = totalPrice;
	}

	public String getBookID() { // 장바구니에 담긴 도서의 ID를 반환하는 메소드
		return bookID;
	}

	public void setBookID(String bookID) { // 장바구니에 담긴 도서의 ID를 설정하고 총가격을 업데이트하는 메소드
		this.bookID = bookID;
		this.updateTotalPrice();
	}

	public int getQuantity() { // 장바구니에 담긴 도서의 수량을 반환하는 메소드
		return quantity;
	}

	public void setQuantity(int quantity) { // 장바구니에 담긴 도서의 수량을 설정하고 총가격을 업데이트하는 메소드
		this.quantity = quantity;
		this.updateTotalPrice();
	}

	public int getTotalPrice() { // 장바구니에 담긴 도서의 가격을 반환하는 메소드
		return totalPrice;
	}

	public void updateTotalPrice() { // 장바구니에 담긴 도서의 가격을 업데이트하는 메소드
		// totalPrice = Integer.parseInt(this.itemBook[2]) * this.quantity;
		totalPrice = this.itemBook.getUnitPrice() * this.quantity;
	} // 'Book' 클래스의 'getUnitPrice()' 메소드를 통해 도서의 가격을 가져옴
}