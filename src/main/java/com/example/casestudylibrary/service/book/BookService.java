package com.example.casestudylibrary.service.book;

import com.example.casestudylibrary.controller.rest.BookRestAPI;
import com.example.casestudylibrary.domain.Book;
import com.example.casestudylibrary.domain.Category;
import com.example.casestudylibrary.domain.Image;
import com.example.casestudylibrary.domain.dto.res.BookResDto;
import com.example.casestudylibrary.domain.enumration.EStatus;
import com.example.casestudylibrary.repository.IBookRepository;
import com.example.casestudylibrary.repository.ICategoryRepository;
import com.example.casestudylibrary.repository.IImageRepository;
import com.example.casestudylibrary.service.IExistsService;
import com.example.casestudylibrary.service.book.request.BookSaveRequest;
import com.example.casestudylibrary.service.book.response.BookDetailResponse;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
 public class BookService implements IBookService, IExistsService {
    @Autowired
    private  IBookRepository bookRepository;
    @Autowired
    private  IImageRepository imageRepository;

    @Autowired
    private ICategoryRepository categoryRepository;

    private final ObjectMapper objectMapper;
    private final String ENTITY = "Book";

    private BookRestAPI bookRestAPI;

    public BookService(IBookRepository bookRepository, ObjectMapper objectMapper) {
        this.bookRepository = bookRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<BookResDto> findAll() {
        return bookRepository.findAll().stream().map(book -> book.toBookResDto()).collect(Collectors.toList());
    }

    @Override
    public Page<Book> findAllWithSearch(String search, Long categoryId, Pageable pageable) {
        search = "%" + search + "%";
        return bookRepository.findAllWithSearch(search, categoryId, pageable);
    }

    @Override
    public void create(BookSaveRequest request) {
        Book book = objectMapper.convertValue(request, Book.class);
        bookRepository.save(book);
    }

    @Override
    public void create(BookSaveRequest req, MultipartFile img, HttpServletRequest request) throws IOException {

        String uploadsDir = "/uploads/";
        String realPathtoUploads =  request.getServletContext().getRealPath(uploadsDir);
        if(! new File(realPathtoUploads).exists())
        {
            new File(realPathtoUploads).mkdir();
        }
        String orgName = img.getOriginalFilename();
        String filePath = realPathtoUploads + orgName;
        File dest = new File(filePath);
        img.transferTo(dest);

        Image image = new Image();
        image.setCreatedDate(LocalDate.now());
        image.setFileName(orgName);
        image.setFileUrl(uploadsDir + orgName);

        imageRepository.save(image);


        Category category = categoryRepository.findById(req.getCategoryId()).get();


        Book book = objectMapper.convertValue(req, Book.class);
        book.setCategory(category);
        book.setImage(image);
        bookRepository.save(book);
    }

    @Override
    public void update(BookSaveRequest request, Long id) throws JsonMappingException {

    }


    public void update(BookSaveRequest request, MultipartFile img1 ,Long id) throws JsonMappingException {
        //    Tìm cuốn sách cần cập nhật trong cơ sở dữ liệu
        Book bookUpdate = bookRepository.findById(id).get();

        if (bookUpdate != null) {
            // Cập nhật thông tin của cuốn sách
            bookUpdate.setName(request.getName());
            bookUpdate.setDescription(request.getDescription());
            bookUpdate.setPublisher(request.getPublisher());
            bookUpdate.setStatus(request.getStatus().toString());

            Category category = categoryRepository.findById(request.getCategoryId()).get();
            bookUpdate.setCategory(category);

//            if (img1 != null && !img1.isEmpty()) {
//                String uploadsDir = "/uploads/";
//                String realPathtoUploads = request.getServletContext().getRealPath(uploadsDir);
//                if (!new File(realPathtoUploads).exists()) {
//                    new File(realPathtoUploads).mkdir();
//                }
//                String orgName = img1.getOriginalFilename();
//                String filePath = realPathtoUploads + orgName;
//                File dest = new File(filePath);
//                img1.transferTo(dest);
//
//                Image image = bookUpdate.getImage();
//                if (image == null) {
//                    image = new Image();
//                    image.setCreatedDate(LocalDate.now());
//                }
//                image.setFileName(orgName);
//                image.setFileUrl(uploadsDir + orgName);
//                imageRepository.save(image);
//
//                // Liên kết hình ảnh mới với cuốn sách
//                bookUpdate.setImage(image);
//            }
//            // Lưu lại sách vào cơ sở dữ liệu
//            bookRepository.save(bookUpdate);
        }
    }

    /*
    @Override
    public void update(BookSaveRequest request, Long id) throws JsonMappingException {
        // Tìm cuốn sách cần cập nhật trong cơ sở dữ liệu
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        objectMapper.updateValue(bookId, request);
        bookRepository.save(bookId);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            // Cập nhật thông tin của cuốn sách
            book.setTitle(req.getTitle());
            book.setAuthor(req.getAuthor());
            book.setPublishedDate(req.getPublishedDate());
            // Cập nhật thông tin của hình ảnh (nếu có)
            if (img != null && !img.isEmpty()) {
                String uploadsDir = "/uploads/";
                String realPathtoUploads = request.getServletContext().getRealPath(uploadsDir);
                if (!new File(realPathtoUploads).exists()) {
                    new File(realPathtoUploads).mkdir();
                }
                String orgName = img.getOriginalFilename();
                String filePath = realPathtoUploads + orgName;
                File dest = new File(filePath);
                img.transferTo(dest);

                Image image = book.getImage();
                if (image == null) {
                    image = new Image();
                    image.setCreatedDate(LocalDate.now());
                }
                image.setFileName(orgName);
                image.setFileUrl(uploadsDir + orgName);
                imageRepository.save(image);

                // Liên kết hình ảnh mới với cuốn sách
                book.setImage(image);
            }

            // Lưu các thay đổi vào cơ sở dữ liệu
            bookRepository.save(book);
        } else {
            // Xử lý trường hợp không tìm thấy cuốn sách
            throw new NotFoundException("Book not found with ID: " + bookId);
        }
    }

        @Override
        public void update(BookSaveRequest request, Long id) throws JsonMappingException {
            Book book = findBookById(id);
            objectMapper.updateValue(book, request);
            bookRepository.save(book);
            if (bookRepository.existsById(id)){
                book.setName(request.getName());
                book.setDescription(request.getDescription());
            }
            if (imageRepository.existsById(null)){
                String uploadDir = "/uploads/";
            }

            if (img != null && !img.isEmpty()) {
                String uploadsDir = "/uploads/";
                String realPathtoUploads = request.getServletContext().getRealPath(uploadsDir);
                if (!new File(realPathtoUploads).exists()) {
                    new File(realPathtoUploads).mkdir();
                }
                String orgName = img.getOriginalFilename();
                String filePath = realPathtoUploads + orgName;
                File dest = new File(filePath);
                img.transferTo(dest);

                Image image = bookUpdate.getImage();
                if (image == null) {
                    image = new Image();
                    image.setCreatedDate(LocalDate.now());
                }
                image.setFileName(orgName);
                image.setFileUrl(uploadsDir + orgName);
                imageRepository.save(image);

                // Liên kết hình ảnh mới với cuốn sách
                bookUpdate.setImage(image);
            }

            // Lưu các thay đổi vào cơ sở dữ liệu
            bookRepository.save(bookUpdate);
        } else {
        // Xử lý trường hợp không tìm thấy cuốn sách
//                    throw new NotFoundException("Book not found with ID: " + id);
    }

        }


     */

    @Override
    public void update(Long bookId, BookSaveRequest req, MultipartFile img, HttpServletRequest request) throws IOException {
        //    Tìm cuốn sách cần cập nhật trong cơ sở dữ liệu
            Book bookUpdate = bookRepository.findById(bookId).get();

            if (bookUpdate != null) {
                // Cập nhật thông tin của cuốn sách
                bookUpdate.setName(req.getName());
                bookUpdate.setDescription(req.getDescription());
                bookUpdate.setPublisher(req.getPublisher());
                bookUpdate.setStatus(req.getStatus().toString());

                Category category = categoryRepository.findById(req.getCategoryId()).get();
                bookUpdate.setCategory(category);

                if (img != null && !img.isEmpty()) {
                    String uploadsDir = "/uploads/";
                    String realPathtoUploads = request.getServletContext().getRealPath(uploadsDir);
                    if (!new File(realPathtoUploads).exists()) {
                        new File(realPathtoUploads).mkdir();
                    }
                    String orgName = img.getOriginalFilename();
                    String filePath = realPathtoUploads + orgName;
                    File dest = new File(filePath);
                    img.transferTo(dest);

                    Image image = bookUpdate.getImage();
                    if (image == null) {
                        image = new Image();
                        image.setCreatedDate(LocalDate.now());
                    }
                    image.setFileName(orgName);
                    image.setFileUrl(uploadsDir + orgName);
                    imageRepository.save(image);

                    // Liên kết hình ảnh mới với cuốn sách
                    bookUpdate.setImage(image);
                }
                // Lưu lại sách vào cơ sở dữ liệu
                bookRepository.save(bookUpdate);
            }

    }


//@Override
//public void update(Long bookId, BookSaveRequest req, MultipartFile img, HttpServletRequest request) throws IOException {
//    Tìm cuốn sách cần cập nhật trong cơ sở dữ liệu
//    Optional<Book> optionalBook = bookRepository.findById(bookId);
//    objectMapper.updateValue(bookId, request);
//    bookRepository.save(bookId);
//    if (optionalBook.isPresent()) {
//        Book book = optionalBook.get();
//        // Cập nhật thông tin của cuốn sách
//        book.setTitle(req.getTitle());
//        book.setAuthor(req.getAuthor());
//        book.setPublishedDate(req.getPublishedDate());
//        // Cập nhật thông tin của hình ảnh (nếu có)
//        if (img != null && !img.isEmpty()) {
//            String uploadsDir = "/uploads/";
//            String realPathtoUploads = request.getServletContext().getRealPath(uploadsDir);
//            if (!new File(realPathtoUploads).exists()) {
//                new File(realPathtoUploads).mkdir();
//            }
//            String orgName = img.getOriginalFilename();
//            String filePath = realPathtoUploads + orgName;
//            File dest = new File(filePath);
//            img.transferTo(dest);
//
//            Image image = book.getImage();
//            if (image == null) {
//                image = new Image();
//                image.setCreatedDate(LocalDate.now());
//            }
//            image.setFileName(orgName);
//            image.setFileUrl(uploadsDir + orgName);
//            imageRepository.save(image);
//
//            // Liên kết hình ảnh mới với cuốn sách
//            book.setImage(image);
//        }
//
//        // Lưu các thay đổi vào cơ sở dữ liệu
//        bookRepository.save(book);
//    } else {
//        // Xử lý trường hợp không tìm thấy cuốn sách
//        throw new NotFoundException("Book not found with ID: " + bookId);
//    }
//}


    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Book findBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException(String.format("%s with id %d not found", ENTITY, id)));
    }

    @Override
    public BookDetailResponse findBookDetailById(Long id) {
        Book book = findBookById(id);
        return new BookDetailResponse(book.getId(), book.getName(), book.getDescription(),book.getImage().getFileName(),book.getStatus(), book.getPublisher(), book.getCategory().getId());
    }

    @Override
    public long count() {
        return bookRepository.count();
    }

    @Override
    public void init() {
        List<Book> books = new ArrayList<>();
        Book book = new Book();
        book.setName("book 1");
        book.setDescription("description 1");
        book.setImage(null);
        book.setStatus(EStatus.AVAILABLE.toString());
        book.setPublisher("publisher1");
        book.setCategory(null);
        books.add(book);

        Book book2 = new Book();
        book2.setName("book 2");
        book2.setDescription("description 2");
        book2.setImage(null);
        book2.setStatus(EStatus.AVAILABLE.toString());
        book2.setPublisher("publisher2");
        book2.setCategory(null);
        books.add(book);
        bookRepository.saveAll(books);
    }

    @Override
    public boolean existsById(Long id) {
        return bookRepository.existsById(id);
    }

    @Override
    public boolean isService(String serviceName) {
        return "book".equals(serviceName);
    }

}
