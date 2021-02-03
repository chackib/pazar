package org.chakib.pazar;

import net.bytebuddy.utility.RandomString;
import org.chakib.pazar.dao.CategoryRepository;
import org.chakib.pazar.dao.ProductRepository;

import org.chakib.pazar.entity.Category;
import org.chakib.pazar.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.Random;

@SpringBootApplication
public class  PazarApplication implements CommandLineRunner {
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private RepositoryRestConfiguration repositoryRestConfiguration;

	public static void main(String[] args) { SpringApplication.run(PazarApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		repositoryRestConfiguration.exposeIdsFor(Product.class, Category.class);

		categoryRepository.save(new Category(null,"Computers",null,null));
		categoryRepository.save(new Category(null,"Printers",null,null));
		categoryRepository.save(new Category(null,"Smart Phones",null,null));
		Random rnd=new Random();
		categoryRepository.findAll().forEach(c->{
			for (int i = 0; i <10 ; i++){
				Product p=new Product();
				p.setName(RandomString.make(18));
				p.setCurrentPrice(100+rnd.nextInt(10000));
				p.setAvailable(rnd.nextBoolean());
				p.setPromotion(rnd.nextBoolean());
				p.setSelected(rnd.nextBoolean());
				p.setCategory(c);
				p.setPhotoName("unknow.png");
				productRepository.save(p);
			}
		});


	}
}
