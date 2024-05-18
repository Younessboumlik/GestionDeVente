package application;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;





public class SendMail {

		public static void main(String[] args) {
			

			OkHttpClient client = new OkHttpClient().newBuilder()
				    .build();
				MediaType mediaType = MediaType.parse("application/json");
				@SuppressWarnings("deprecation")
				RequestBody body = RequestBody.create(mediaType, "{\"from\":{\"email\":\"mailtrap@demomailtrap.com\",\"name\":\"Mailtrap Test\"},\"to\":[{\"email\":\"younessboumlik1949@gmail.com\"}],\"subject\":\"You are awesome!\",\"text\":\"hiiiiiiiiiiiiiiiiiii fohhhhhhhhhhhhhh test email with Mailtrap!\",\"category\":\"Integration Test\"}");
				Request request = new Request.Builder()
				    .url("https://send.api.mailtrap.io/api/send")
				    .method("POST", body)
				    .addHeader("Authorization", "Bearer 7b920b784241fc365f423e2c46562c51")
				    .addHeader("Content-Type", "application/json")
				    .build();
				try {
					Response response = client.newCall(request).execute();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    }
}
