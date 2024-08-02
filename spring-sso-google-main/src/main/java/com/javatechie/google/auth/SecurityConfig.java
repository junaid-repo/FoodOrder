package com.javatechie.google.auth;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.AuthorizedClientServiceOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizedClientRepository;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	public static Logger log = LogManager.getLogger();

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("reached here ");
		http.authorizeRequests().anyRequest().authenticated().and().oauth2Login().and().csrf().disable();

		/*
		 * http.authorizeRequests().antMatchers("/login").permitAll().antMatchers(
		 * "/oauth/token/revokeById/**").permitAll()
		 * .antMatchers("/tokens/**").permitAll().anyRequest().authenticated().and().
		 * formLogin().permitAll().and() .csrf().disable();
		 */
	}

	@Bean // with spring-boot-starter-web
	WebClient webClient(ClientRegistrationRepository clientRegistrationRepository,
			OAuth2AuthorizedClientService authorizedClientService) {
		var oauth = new ServletOAuth2AuthorizedClientExchangeFilterFunction(
				new AuthorizedClientServiceOAuth2AuthorizedClientManager(clientRegistrationRepository,
						authorizedClientService));
		oauth.setDefaultClientRegistrationId("AuthProvider");
		return WebClient.builder().apply(oauth.oauth2Configuration()).build();
	}

	@Bean
	public WebClient webClient(ReactiveClientRegistrationRepository clientRegistrations,
			ServerOAuth2AuthorizedClientRepository authorizedClients) {
		ServerOAuth2AuthorizedClientExchangeFilterFunction oauth = new ServerOAuth2AuthorizedClientExchangeFilterFunction(
				clientRegistrations, authorizedClients);
		oauth.setDefaultOAuth2AuthorizedClient(true);
		return WebClient.builder().filter(oauth).filter(this.logRequest()).build();
	}

	private ExchangeFilterFunction logRequest() {
		return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
			log.info("Request: [{}] {}", clientRequest.method(), clientRequest.url());
			log.debug("Payload: {}", clientRequest.body());
			return Mono.just(clientRequest);
		});
	}
}
