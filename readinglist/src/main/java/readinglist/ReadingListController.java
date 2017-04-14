package readinglist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import readinglist.entity.Book;
import readinglist.repository.ReadingListRepository;
import org.springframework.ui.Model;
import java.util.List;

@Controller
@RequestMapping("/")
@ConfigurationProperties(prefix = "amazon")
public class ReadingListController {

    private ReadingListRepository repository;
    private String associateId;

    @Autowired
    public ReadingListController(ReadingListRepository repository){
        this.repository = repository;
    }

    @RequestMapping(value = "/{reader}", method = RequestMethod.GET)
    public List<Book> readerBooks(@PathVariable("reader") String reader, Model model){
        List<Book> readingList = repository.findByReader(reader);
        if (readingList != null) {
            model.addAttribute("books", readingList);
            model.addAttribute("reader", reader);
            model.addAttribute("amazonID", associateId);
        }
        return readingList;
    }

    @RequestMapping(value = "/{reader}", method = RequestMethod.POST)
    public void addReader(@PathVariable("reader") String reader, Book book){
        book.setReader(reader);
        repository.save(book);
    }

    public void setAssociateId(String associateId) {
        this.associateId = associateId;
    }
}
