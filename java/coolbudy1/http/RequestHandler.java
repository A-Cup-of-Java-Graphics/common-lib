import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

public class RequestHandler {
  // Handler for GET requests
  static public class GetHandler implements HttpHandler {
      @Override
      public void handle(HttpExchange exchange) throws IOException {
          // Check if the request is a GET request
          if ("GET".equals(exchange.getRequestMethod())) {
              // Create a simple response
              String response = "{\"message\":\"GET request received\"}";
              // Set the response headers and status code
              exchange.getResponseHeaders().set("Content-Type", "application/json");
              exchange.sendResponseHeaders(200, response.getBytes(StandardCharsets.UTF_8).length);
              // Write the response body
              OutputStream os = exchange.getResponseBody();
              os.write(response.getBytes(StandardCharsets.UTF_8));
              os.close();
          } else {
              // If not GET, return 405 Method Not Allowed
              exchange.sendResponseHeaders(405, -1); // -1 means no response body
          }
      }
  }

  // Handler for POST requests
  static public class PostHandler implements HttpHandler {
      @Override
      public void handle(HttpExchange exchange) throws IOException {
          // Check if the request is a POST request
          if ("POST".equals(exchange.getRequestMethod())) {
              // Get the request body
              String requestBody = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
              
              // Log or process the request body (e.g., JSON)
              System.out.println("POST request body: " + requestBody);
              
              // Send a simple response
              String response = "{\"message\":\"POST request received\"}";
              exchange.getResponseHeaders().set("Content-Type", "application/json");
              exchange.sendResponseHeaders(200, response.getBytes(StandardCharsets.UTF_8).length);
              OutputStream os = exchange.getResponseBody();
              os.write(response.getBytes(StandardCharsets.UTF_8));
              os.close();
          } else {
              // If not POST, return 405 Method Not Allowed
              exchange.sendResponseHeaders(405, -1);
          }
      }
  }
}
