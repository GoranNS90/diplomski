package com.diplomski.blog.controller;

import java.security.Principal;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.diplomsi.blog.dto.CommentDTO;
import com.diplomski.blog.model.Comment;
import com.diplomski.blog.model.Post;
import com.diplomski.blog.model.User;
import com.diplomski.blog.service.AuthoritiesService;
import com.diplomski.blog.service.CommentService;
import com.diplomski.blog.service.PostService;
import com.diplomski.blog.service.UserService;

@RestController
public class MainController {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthoritiesService authoritiesService;

	@Autowired
	private PostService postService;

	@Autowired
	private CommentService commentService;

	private static final String USER_URL = "/user";

	private static final String USER_REGISTRATION_URL = "/userRegister/";

	private static final String ADD_POST_URL = "/addPost/";

	private static final String NAME = "name";

	private static final String ROLES = "roles";

	private static final String GET_POSTS_URL = "/getPosts";

	private static final String ADD_COMMENT_URL = "/addComment/";
	
	private static final String LOGOUT_URL = "/logout";
	
	private static final String REMOVE_POST_URL = "/removePost";
	
	private static final String REMOVE_COMMENT = "/removeComment";

	@RequestMapping(USER_URL)
	public @ResponseBody
	Map<String, Object> user(Principal user) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		if (user == null) {
			return map;
		}
		map.put(NAME, user.getName());
		map.put(ROLES, AuthorityUtils
				.authorityListToSet(((Authentication) user).getAuthorities()));
		return map;
	}

	@RequestMapping(value = USER_REGISTRATION_URL, method = RequestMethod.POST)
	public void addUser(@RequestBody User user, HttpServletResponse response) {
		if (user.getPassword().equals(user.getRetypePassword())) {
			userService.addUser(user);
			authoritiesService.addAuthorities(user);
		} else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}

	}

	@RequestMapping(value = ADD_POST_URL, method = RequestMethod.POST)
	public void addPost(@RequestBody Post post, Principal user) {
		post.setUsername(user.getName());
		post.setDate(new Date());
		postService.addPost(post);
	}

	@RequestMapping(value = GET_POSTS_URL, method = RequestMethod.GET)
	public @ResponseBody
	List<Post> getPosts() {
		List<Post> list = postService.getAll();
		return list;
	}

	@RequestMapping(value = ADD_COMMENT_URL, method = RequestMethod.POST)
	public @ResponseBody
	Post addComment(@RequestBody CommentDTO commentDTO, Principal user) {
		Comment comment = new Comment();
		Post post = postService.findPostById(commentDTO.getPostId());
		comment.setContent(commentDTO.getContent());
		comment.setUsername(user.getName());
		comment.setDate(new Date());
		comment.setPost(post);
		commentService.addComment(comment);
		return post;
	}

	@RequestMapping(value = LOGOUT_URL, method = RequestMethod.GET)
	public void logoutPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = REMOVE_POST_URL, method = RequestMethod.POST)
	public HttpStatus deletePost(@RequestBody Integer id) {
		postService.deletePost(id);
		return HttpStatus.OK;
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = REMOVE_COMMENT, method = RequestMethod.POST)
	public Post removeComment(@RequestBody Integer id) {
		Comment comment = commentService.findCommentById(id);
		Post post = comment.getPost();
		commentService.removeComment(id);
		post = postService.findPostById(post.getId());
		System.out.println(post.getComments().size());
		return post;
	}
	
}
