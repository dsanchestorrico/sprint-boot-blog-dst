package com.reljicd;

import com.reljicd.controller.LoginController;
import com.reljicd.model.Comment;
import com.reljicd.model.Post;
import com.reljicd.model.User;
import com.reljicd.repository.CommentRepository;
import com.reljicd.repository.PostRepository;
import com.reljicd.repository.UserRepository;
import com.reljicd.service.PostService;
import com.reljicd.service.impl.PostServiceImp;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class BlogDemoApplicationTests {

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommentRepository commentRepository;

    private LoginController login;
    private Principal principal;

    @Before
    public void setUp() {
        principal = Mockito.mock(Principal.class);
        login = new LoginController();
    }

    @Test
    public void testIsLoginRedirectSuccess() {
        System.out.println("-----TEST testIsLoginRedirectSuccess-----");
        assertEquals("redirect:/home", login.login(principal));
    }

    @Test
    public void testIsLoginRedirectFail() {
        System.out.println("-----TEST testIsLoginRedirectFail-----");
        assertEquals("/login", login.login(null));
    }

    @Test
    public void testFindAllPost() {
        System.out.println("---TEWST testFindAllPost---");
        User user = new User();
        user.setActive(0);
        user.setUsername("jakychan");
        user.setPassword("jakychan");
        user.setEmail("jakychan@gmail.com");
        user.setName("Jakeline");
        user.setLastName("Choquecallata Bohorquez");
        userRepository.save(user);
        Post newPost = new Post();
        newPost.setTitle("Test Title");
        newPost.setBody("This is a Test Post Body");
        newPost.setUser(user);
        postRepository.save(newPost);
        List<Post> posts = postRepository.findAll();
        boolean postExist = false;
        for (Post post : posts) {
            if (post.getTitle().equals("Test Title")) {
                postExist = true;
                break;
            }
        }
        assertEquals(postExist, true);
    }
    @Test
    public void testFindPostById() {
        System.out.println("---TEWST testFindPostById---");
        User user = new User();
        user.setActive(0);
        user.setUsername("maddiesan");
        user.setPassword("maddiesan");
        user.setEmail("maddiesan@gmail.com");
        user.setName("Maddie Naima");
        user.setLastName("Sanchez Choquecallata");
        userRepository.save(user);
        Post newPost = new Post();
        newPost.setTitle("Post of Maddie Title");
        newPost.setBody("This is a Post Body of MaddieSan");
        newPost.setUser(user);
        postRepository.save(newPost);
        Optional<Post> post = postRepository.findById(newPost.getId());
        assertEquals(post.get().getTitle(), "Post of Maddie Title");
    }
    @Test
    public void testFindByUsername() {
        System.out.println("---TEST findByUsername---");
        User user = new User();
        user.setUsername("danielsan");
        user.setPassword("danielsan");
        user.setEmail("dsancheztorrico@gmail.com");
        user.setName("Daniel");
        user.setLastName("Sanchez Torrico");
        userRepository.save(user);
        Optional<User> recoveredUser = userRepository.findByUsername("danielsan");
        assertEquals("danielsan", recoveredUser.get().getUsername());
    }
    @Test
    public void testFindByEmail() {
        System.out.println("---TEST testFindByEmail---");
        User user = new User();
        user.setUsername("jakychan");
        user.setPassword("jakychan");
        user.setEmail("jakychan@gmail.com");
        user.setName("Jakeline Ingrid");
        user.setLastName("Choquecallata Bohorquez");
        userRepository.save(user);
        Optional<User> recoveredUser = userRepository.findByEmail("jakychan@gmail.com");
        assertEquals("jakychan@gmail.com", recoveredUser.get().getEmail());
    }

    @Test
    public void testSaveComment() {
        System.out.println("-----TEST testSaveComment-----");
        User user = new User();
        user.setActive(0);
        user.setPassword("danielsan");
        user.setUsername("danielsan");
        user.setName("Daniel");
        user.setLastName("Sanchez Torrico");
        user.setEmail("dsancheztorrico@gmail.com");
        userRepository.save(user);

        Post post = new Post();
        post.setBody("This is a Body");
        post.setTitle("This is a Title");
        post.setUser(user);
        postRepository.save(post);

        Comment comment = new Comment();
        comment.setBody("This is a body of comment");
        comment.setPost(post);
        comment.setCreateDate(new Date());
        comment.setUser(user);
        commentRepository.save(comment);

        entityManager.refresh(post);
        Optional<Post> p = postRepository.findById(post.getId());
        Post recoveredPost = p.get();
        boolean existComment = false;
        for (Comment c : recoveredPost.getComments()) {
            if (c.getBody().equals("This is a body of comment")) {
                existComment = true;
            }
        }
        assertEquals(true, existComment);

    }

}
