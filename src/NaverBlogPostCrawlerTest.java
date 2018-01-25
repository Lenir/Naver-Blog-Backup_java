import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class NaverBlogPostCrawlerTest {
	
	private NaverBlogPostCrawler se3postCrawler;
	private NaverBlogPostCrawler se2postCrawler;
	
	@Before
	public void setUp() {
		String se3url = "https://blog.naver.com/1net1/221182904428";
		String se2url = "https://blog.naver.com/1net1/221182912148";
		se3postCrawler = new NaverBlogPostCrawler(se3url);
		se2postCrawler = new NaverBlogPostCrawler(se2url);
	}
	
	@Test
	public void testIsForeignUrl_true() {
		String foreignUrl = "https://www.naver.com/";
		NaverBlogPostCrawler foeignPostCrawler = new NaverBlogPostCrawler(foreignUrl);
		assertTrue(foeignPostCrawler.isForeignUrl());
	}
	
	@Test
	public void testIsForeignUrl_false() {
		assertFalse(se3postCrawler.isForeignUrl());
	}

}
