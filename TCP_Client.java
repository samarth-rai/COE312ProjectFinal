import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class TCP_Client extends AbstractObserverSubject implements Runnable {
	String host = "127.0.0.1";
	int port = 8080;
    int use;
		TCP_Client(String host, int port, int use){
			this.host = host;
			this.port = port;
			Thread t = new Thread(this);
			t.start();
	}
		
		JSONParser parser = new JSONParser();
		public void run() {
			try {
				Socket socket = new Socket(this.host, this.port);
				InputStream input = socket.getInputStream();
				InputStreamReader reader = new InputStreamReader(input);
				BufferedReader br = new BufferedReader(reader);
				String line = "";
                int count=0;
				while ((line = br.readLine()) != null && use==0 && count<10) {
                    UI.print("Chop the tree using your phone!");
	                JSONObject jsonObject = (JSONObject) parser.parse(line);
					String AccX = (String) jsonObject.get("accelerometerAccelerationX");
                    String AccY = (String) jsonObject.get("accelerometerAccelerationY");
                    String AccZ = (String) jsonObject.get("accelerometerAccelerationZ");
					Double netAcc = Math.pow(Math.pow(Float.parseFloat(AccX),2) + Math.pow(Float.parseFloat(AccY),2)+ Math.pow(Float.parseFloat(AccZ),2),1/2); //making the application non-trivial
                    
                    publishMessage(new Message(this,"chop tree",netAcc.toString()));
					Thread.sleep(1000);
					count++;
				} 
                while ((line = br.readLine()) != null && use==1 && count<10) {
                    UI.print("Attack by blowing on your phone!");
	                JSONObject jsonObject = (JSONObject) parser.parse(line);
					
                   // publishMessage(new Message(this,"chop tree",netAcc.toString()));
					Thread.sleep(1000);
					count++;
				} 
	//----------------------------------------------------------------------------

			} catch (UnknownHostException ex) {
				System.out.println("Server not found: " + ex.getMessage());
			} catch (IOException ex) {
				System.out.println("I/O error: " + ex.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
        @Override
        public void update(Message m) {
            // TODO Auto-generated method stub
            
        }

}