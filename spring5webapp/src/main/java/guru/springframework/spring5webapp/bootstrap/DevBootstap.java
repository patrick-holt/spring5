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
        Publisher harperCollins = new Publisher();
        harperCollins.setName("Harper Collins");
        harperCollins.setCityName("New York");
        harperCollins.setZipCode("26012");
        Publisher worx = new Publisher();
        worx.setName("Worx");
        worx.setCityName("Washington");
        worx.setZipCode("10023");
        publisherRepository.save(harperCollins); //Does not work with these
        publisherRepository.save(worx);

        //Eric
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "1234", harperCollins);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        /* It seems that the part above simply creates these objects as POJOs
        * Here below, we use the interface repositories to save these objects
        * Spring will get this information into Hibernate H2 (the database) for us.
        * Black magic... */
        authorRepository.save(eric);
        bookRepository.save(ddd);

        //Rod
        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "23444", worx);
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
    }

}
