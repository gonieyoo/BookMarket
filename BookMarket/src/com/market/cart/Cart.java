package com.market.cart;

import java.util.ArrayList;
import com.market.bookitem.Book;

public class Cart implements CartInterface {
    // "CartInterface" 인터페이스의 자식 클래스 "Cart" 생성
    
    public ArrayList<CartItem> mCartItem = new ArrayList<CartItem>(); // 장바구니에 담긴 도서들을 저장하는 역할
    // static final int NUM_BOOK = 3; // 장바구니에 담을 수 있는 도서의 최대 개수를 상수로 선언
    /*
     * mCartItem : CartItem의 객체 배열로 선언, 장바구니에 담긴 도서들을 저장하는 역할
     * NUM_BOOK : 배열의 크기 결정
     */
    // public CartItem[] mCartItem = new CartItem[NUM_BOOK]; // 장바구니에 담긴 도서들을 저장하는 역할

    // mCartItem의 초기값을 0 으로 설정
    public int mCartCount = 0; // 장바구니에 담긴 도서의 개수를 저장하는 변수

    public Cart() { // 매개변수가 없는 기본 생성자

    }

    public void printBookList(ArrayList<Book> booklist) { // 도서 목록 출력
        
        /* for (int i = 0; i < booklist.length; i++) { // 배열의 길이만큼 반복
            // 배열 i 인덱스에 해당하는 도서 정보 출력
            System.out.print(booklist[i].getBookId() + " | ");
            System.out.print(booklist[i].getName() + " | ");
            System.out.print(booklist[i].getUnitPrice() + " | ");
            System.out.print(booklist[i].getAuthor() + " | ");
            System.out.print(booklist[i].getDescription() + " | ");
            System.out.print(booklist[i].getCategory() + " | ");
            System.out.print(booklist[i].getReleaseDate());
            System.out.println(""); */
        
        for (int i = 0; i < booklist.size(); i++) { // 배열의 길이만큼 반복
            Book bookitem = booklist.get(i);
            System.out.print(bookitem.getBookId() + " | ");
            System.out.print(bookitem.getName() + " | ");
            System.out.print(bookitem.getUnitPrice() + " | ");
            System.out.print(bookitem.getAuthor() + " | ");
            System.out.print(bookitem.getDescription() + " | ");
            System.out.print(bookitem.getCategory() + " | ");
            System.out.print(bookitem.getReleaseDate());
            System.out.println("");
        }
    }

    /*
     * 새로운 도서를 장바구니에 삽입하는 메소드 정의
     * 도서 추가될 때마다 1씩 증가
     */
    public void insertBook(Book book) {
        mCartItem[mCartCount++] = new CartItem(book);
    }

    /*
     * 장바구니의 모든 항목을 삭제하는 메소드
     * mCartCount 값을 0으로 초기화
     */
    public void deleteBook() {
        mCartItem = new CartItem[NUM_BOOK];
        mCartCount = 0;
    }

    // 장바구니 항목 출력
    public void printCart() {
        System.out.println("장바구니 상품 목록 :");
        System.out.println("--------------------------------------------------------------");
        System.out.println("    도서ID \t|    수량\t\t|     합계");

        for (int i = 0; i < mCartCount; i++) {
            System.out.print("    " + mCartItem[i].getBookID() + " \t| ");
            System.out.print("    " + mCartItem[i].getQuantity() + " \t| ");
            System.out.print("    " + mCartItem[i].getTotalPrice());
            System.out.println("  ");
        }
        System.out.println("--------------------------------------------------------------");
    }

    // 장바구니에 동일한 도서가 있는지 확인
    public boolean isCartInBook(String bookId) {
        boolean flag = false;
        for (int i = 0; i < mCartCount; i++) {
            if (bookId == mCartItem[i].getBookID()) {
                mCartItem[i].setQuantity(mCartItem[i].getQuantity() + 1);
                flag = true;
            }
        }
        return flag;
    }

    // 장바구니에서 도서 삭제
    public void removeCart(int numId) {
        CartItem[] cartItem = new CartItem[NUM_BOOK];
        int num = 0;

        for (int i = 0; i < mCartCount; i++) {
            if (numId != i) {
                cartItem[num++] = mCartItem[i];
            }
        }

        mCartCount = num; // 장바구니에 담긴 도서의 개수를 저장하는 변수
        mCartItem = cartItem; // 장바구니에 담긴 도서들을 저장하는 역할
    }
} // Cart 클래스 끝