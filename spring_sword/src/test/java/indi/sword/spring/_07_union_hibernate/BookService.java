package indi.sword.spring._07_union_hibernate;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BookService
{
    public String findBookById(int id);
    public void saveBook(Book book);
}
