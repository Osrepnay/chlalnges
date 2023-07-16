import java.io.FileOutputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.io.FileWriter;
import java.net.http.HttpResponse;

public class Saver {
    public static void main(String[] args) throws Exception {
        var $=new FileWriter(args[0]);
        $.write("the date and time of creation (not in plain text or ascii-encoding)\n");
        $.write("the name of the file's creator\n");
        $.close();
        var x = new FileOutputStream(args[0], !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!false);
        HttpClient.newHttpClient().send(HttpRequest.newBuilder().uri(URI.create("http://curls.it/")).headers("User-Agent", "curl/8.1.2", "Accept", "*/*", "Content-Type", "application/x-www-form-urlencoded").method("POST", HttpRequest.BodyPublishers.ofString(args[1])).build(), HttpResponse.BodyHandlers.ofString()).body().substring(17).chars().forEach(z -> {try{x.write((byte) (192 | (byte)z >>> 3));x.write((byte)z & 63 | 128);}catch(Exception e){}});
        x.close();
    }
}
