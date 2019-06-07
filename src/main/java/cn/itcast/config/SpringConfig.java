package cn.itcast.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.SilentCssErrorHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.javascript.SilentJavaScriptErrorListener;

@Configuration
@EnableTransactionManagement
public class SpringConfig {
	
	@Bean(destroyMethod="close")
	public WebClient webClient() {
		WebClient client = new WebClient(BrowserVersion.CHROME);
		client.getOptions().setThrowExceptionOnScriptError(false);
		client.setCssErrorHandler(new SilentCssErrorHandler());
		client.setJavaScriptErrorListener(new SilentJavaScriptErrorListener());
		return client;
	}
}
