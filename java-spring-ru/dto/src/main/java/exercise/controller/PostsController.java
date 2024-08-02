package exercise.controller;

import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import java.util.List;

import exercise.model.Post;
import exercise.model.Comment;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;
import exercise.dto.PostDTO;
import exercise.dto.CommentDTO;

// BEGIN
@RestController
@RequestMapping("/posts")

public class PostsController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostDTO showPost (@PathVariable Long id) {
        var post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));
        var dto = new PostDTO();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setBody(post.getBody());
        var comments = commentRepository.findByPostId(id)
                .stream()
                .map(this::toCommentDTO)
                .toList();
        dto.setComments(comments);
        return dto;
    }

    private CommentDTO toCommentDTO(Comment comment) {
        var dtoComment = new CommentDTO();
        dtoComment.setId(comment.getId());
        dtoComment.setBody(comment.getBody());
        return dtoComment;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<PostDTO> index() {
        var posts = postRepository.findAll();
        var result = posts.stream()
                .map(this::toPostDTO)
                .toList();
        return result;
    }

    private PostDTO toPostDTO(Post post) {
        var dtoPost = new PostDTO();
        dtoPost.setId(post.getId());
        dtoPost.setTitle(post.getTitle());
        return dtoPost;
    }
}
// END