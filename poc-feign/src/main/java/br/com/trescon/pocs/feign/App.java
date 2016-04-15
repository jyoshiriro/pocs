package br.com.trescon.pocs.feign;

import java.util.List;

import br.com.trescon.pocs.feign.clients.IPost;
import br.com.trescon.pocs.feign.clients.Post;
import feign.Feign;
import feign.gson.GsonDecoder;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		IPost ipost = Feign.builder().decoder(new GsonDecoder()).target(IPost.class,
				"http://jsonplaceholder.typicode.com");

		List<Post> lista = ipost.posts();

		for (Post post : lista) {
			System.out.println(post);
		}

		Post unico = ipost.post(5);
		System.out.println(unico);
	}
}
