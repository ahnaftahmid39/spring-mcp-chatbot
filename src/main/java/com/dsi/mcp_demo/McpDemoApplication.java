package com.dsi.mcp_demo;

import java.util.Arrays;
import java.util.List;
// import java.util.Scanner;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.modelcontextprotocol.client.McpSyncClient;

@SpringBootApplication
public class McpDemoApplication {

	public static void main(String[] args) {
		var ctx = SpringApplication.run(McpDemoApplication.class, args);
	}


	@Bean
	public ChatClient chatClient(ChatClient.Builder chatClientBuilder, List<McpSyncClient> mcpSyncClients) {
		return chatClientBuilder
			.defaultSystem(
					"You are an helpful assistant.")
			.defaultToolCallbacks(new SyncMcpToolCallbackProvider(mcpSyncClients))
			.defaultAdvisors(new MessageChatMemoryAdvisor(MessageWindowChatMemory.builder().build()))
			.build();

	}

	// @Bean
	// public CommandLineRunner chatbot(ChatClient.Builder chatClientBuilder, List<McpSyncClient> mcpSyncClients) {
	// 	return args -> {
	// 		var chatClient = chatClientBuilder
	// 				.defaultSystem(
	// 						"You are an helpful assistant.")
	// 				.defaultToolCallbacks(new SyncMcpToolCallbackProvider(mcpSyncClients))
	// 				.defaultAdvisors(new MessageChatMemoryAdvisor(MessageWindowChatMemory.builder().build()))
	// 				.build();

	// 		// Start the chat loop
	// 		System.out.println("\nI am your AI assistant.\n");
	// 		try (Scanner scanner = new Scanner(System.in)) {
	// 			while (true) {
	// 				System.out.print("\n>: ");
	// 				chatClient.prompt(scanner.nextLine())
	// 						.stream() // <- returns a Flux<ChatResponse>
	// 						.content()
	// 						.doOnNext(System.out::print)
	// 						.blockLast();
	// 			}
	// 		}
	// 	};
	// }
}