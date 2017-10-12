package indi.sword.spring._07_union_hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService
{
    @Autowired
    private BookDao bookDao;


    public String findBookById(int id)
    {
        return bookDao.findBookById(id);
    }
    public void saveBook(Book book)
    {
        bookDao.saveBook(book);

    }
}
