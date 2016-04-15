package br.com.trescon.pocs.feign.clients;

import java.util.List;

import feign.Param;
import feign.RequestLine;

public interface IPost {
	
	@RequestLine("GET /posts")
	List<Post> posts();
	
	@RequestLine("GET /posts/{id}")
	Post post(@Param("id") Integer id);
}
