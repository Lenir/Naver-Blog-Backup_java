import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.*;

public class NaverBlogCrawler {
	private String naverId;
	private boolean isDevMode;
	private int threadNum;
	
	NaverBlogCrawler(String naverId){
		this.naverId = naverId;
		this.threadNum = 4;
		this.isDevMode = false;
	}

	public NaverBlogCrawler(String naverId, int threadNum, boolean isDevMode){
		this.naverId = naverId;
		this.threadNum = threadNum;
		this.isDevMode = isDevMode;
	}
	
	ArrayList<String> getEntirePostIdList(int startPage){
		int pageNum = startPage;
		ArrayList<String> postIdList = new ArrayList<String>();
		ArrayList<String> pastIdList = new ArrayList<String>();
		while(true) {
			try {
				ArrayList<String> partialList = getPostIdListViaPage(pageNum);
				if(isDuplicateList(pastIdList, partialList)) {
					return postIdList;
				}
				postIdList.addAll(partialList);
				pastIdList = partialList;
				pageNum += 1;
			} catch (Exception e) {
				e.printStackTrace();
				break;
			} 
		}
		return postIdList;
	}
	
	boolean isDuplicateList(ArrayList<String> postIdList1, ArrayList<String> postIdList2) {
		if(postIdList1.size() == postIdList2.size()) {
			for(int index = 0; index < postIdList1.size(); index++) {
				if(postIdList1.get(index) != postIdList2.get(index)) {
					return false;
				}
			}
			return true;
		}else {
			return false;
		}
	}
	
	ArrayList<String> getPostIdListViaPage(int pageNum) throws Exception{
		ArrayList<String> postIdList = new ArrayList<String>();
		
		String getPostListUrl = "https://blog.naver.com/PostList.nhn"
				+ "?from=postList"
				+ "&blogId=" + this.naverId + "&"
				+ "currentPage=" + pageNum;
		String protocol = "GET";
		
		String postIdFieldRegex = "var tagParam .*\\';";
		String postIdRegex = "[0-9]+";
		Pattern postIdPattern = Pattern.compile(postIdRegex);
		try {
			URL url = new URL(getPostListUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(protocol);
			connection.setRequestProperty("User-Agent", "Mozilla/5.0");
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			String temp;
			while((temp = br.readLine()) != null) {
				if(Pattern.matches(postIdFieldRegex, temp)){
					try {
						Matcher m = postIdPattern.matcher(temp);
						while(m.find()) {
							postIdList.add(m.group());
						}
					} catch (Exception e) {
						System.out.println(e);
					}
				}
			}
		}catch (Exception e) {
			System.out.println(e);
		}
		if(postIdList.size() == 0) {
			throw new Exception("NonePostListException");
		}
		return postIdList;
	}
	
	public static void main(String[] args) {
		System.out.println("halo!");

	}
	
}

