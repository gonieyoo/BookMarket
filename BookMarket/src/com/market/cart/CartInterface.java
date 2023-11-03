package com.market.cart;

import java.util.ArrayList;
import com.market.bookitem.Book;

public interface CartInterface {
    void printBookList(ArrayList<Book> p); // 장바구니에 담긴 도서 목록 출력

    boolean isCartInBook(String id); // 장바구니에 동일한 도서가 있는지 확인

    void insertBook(Book p); // 장바구니에 도서 추가

    void removeCart(int numId); // 장바구니에서 도서 삭제

    void deleteBook(); // 장바구니 비우기
}
