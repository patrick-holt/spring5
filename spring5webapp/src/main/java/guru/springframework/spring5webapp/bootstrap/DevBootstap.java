package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component //This is now a Spring bean
public class DevBootstap implements ApplicationListener<ContextRefreshedEvent> {

    //Normally these would be @Autowired?
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    //Auto injecting through the Spring framework (?)
    public DevBootstap(AuthorRepository authorRepository,
                       BookRepository bookRepository,
                       PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {
        //Eric
        Author eric = new Author("Eric", "Evans");
        Publisher harperCollins = new Publisher("Harper Collins", "New York", "262011");
        Book ddd = new Book("Domain Driven Design", "1234", harperCollins);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        /* It seems that the part above simply creates these objects as POJOs
        * Here below, we use the interface repositories to save these objects
        * Spring will get this information into Hibernate H2 (the database) for us.
        * Black magic... */
        authorRepository.save(eric);
        //publisherRepository.save(harperCollins); //Works without, but not with
        bookRepository.save(ddd);

        //Rod
        Author rod = new Author("Rod", "Johnson");
        Publisher worx = new Publisher("Worx", "Seattle", "10444");
        Book noEJB = new Book("J2EE Development without EJB", "23444", worx);
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        authorRepository.save(rod);
        //publisherRepository.save(worx);
        bookRepository.save(noEJB);
    }

}
