import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class NaverBlogCrawlerTest {
	
	private NaverBlogCrawler crawler;
	
	@Before
	public void setUp() {
		crawler = new NaverBlogCrawler("1net1");
	}

	@Test
	public void testIsDuplicateList_true() {
		ArrayList<String> list1 = new ArrayList<String>();
		ArrayList<String> list2 = new ArrayList<String>();
		list1.add("Element1");
		list2.add("Element1");
		list1.add("Element2");
		list2.add("Element2");
		boolean result = crawler.isDuplicateList(list1, list2);
		assertTrue(result);
	}
	
	@Test
	public void testIsDuplicateList_false() {
		ArrayList<String> list1 = new ArrayList<String>();
		ArrayList<String> list2 = new ArrayList<String>();
		list1.add("Element1");
		list2.add("Element1");
		list1.add("Element2");
		list2.add("Element4");
		boolean result = crawler.isDuplicateList(list1, list2);
		assertFalse(result);
	}

	@Test
	public void testGetPostIdListViaPage() {
		try {
			ArrayList<String> partialIdList = crawler.getPostIdListViaPage(1);
			int listSize = partialIdList.size();
			assertEquals(3, listSize);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
	}

}
