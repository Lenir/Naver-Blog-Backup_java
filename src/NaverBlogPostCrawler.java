import java.util.*;
import java.util.regex.Pattern;
public class NaverBlogPostCrawler {
	private int editorVersion;
	private String url;
	private String postFrameUrl;
	private String postTitle;
	private String backupDir;
	private String postDate;
	private boolean isDevMode;
	
	NaverBlogPostCrawler(String url){
		this.url = url;
		this.isDevMode = false;
	}
	
	NaverBlogPostCrawler(String url, boolean isDevMode){
		this.url = url;
		this.isDevMode = isDevMode;
	}
	
	boolean isForeignUrl() {
		if(this.url.contains("blog.naver.com")) {
			return false;
		}else {
			return true;
		}
	}
	
	int getEditorVersion() {
		//TODO - implement
		return 0;
	}
	
}
