package readinglist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/")
public class ReadingListController {

    private ReadingListRepository repository;

    @Autowired
    public ReadingListController(ReadingListRepository repository){
        this.repository = repository;
    }

    @RequestMapping(value = "/{reader}", method = RequestMethod.GET)
    public List<Book> readerBooks(@PathVariable("reader") String reader){
        return repository.findByReader(reader);
    }

    @RequestMapping(value = "/{reader}", method = RequestMethod.POST)
    public void addReader(@PathVariable("reader") String reader, Book book){
        book.setReader(reader);
        repository.save(book);
    }
}
