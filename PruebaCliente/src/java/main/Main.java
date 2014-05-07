/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

import java.util.List;

/**
 *
 * @author Daniel
 */
public class Main {

    private static Book getBook(int id) {
        main.BookInfo_Service service = new main.BookInfo_Service();
        main.BookInfo port = service.getBookInfoPort();
        return port.getBook(id);
    }


    public static void main(String[] args){
        System.out.println("hola");
        List<Object> ab = listBooks();
        Book libro = (Book)ab.get(1);
        System.out.println(libro.getTitle());
        Book libro2 = getBook(2);
        System.out.println(libro2.getTitle());
        List<Book> ab2 = getBooksByTitle("ASDF");
        System.out.println(ab2.get(0).getDescription());
    }


    private static java.util.List<java.lang.Object> listBooks() {
        main.BookInfo_Service service = new main.BookInfo_Service();
        main.BookInfo port = service.getBookInfoPort();
        return port.listBooks();
    }

    private static java.util.List<java.lang.Object> listBooks_1() {
        main.BookInfo_Service service = new main.BookInfo_Service();
        main.BookInfo port = service.getBookInfoPort();
        return port.listBooks();
    }

    private static java.util.List<main.Book> getBooksByTitle(java.lang.String tittle) {
        main.BookInfo_Service service = new main.BookInfo_Service();
        main.BookInfo port = service.getBookInfoPort();
        return port.getBooksByTitle(tittle);
    }
}
