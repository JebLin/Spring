package indi.sword.spring._07_union_hibernate;

public interface BookDao
{
    public String findBookById(int id);

    public void saveBook(Book book);
}