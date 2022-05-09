import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class TCP_Client extends AbstractObserverSubject implements Runnable {
	String host = "127.0.0.1";
	int port = 8080;
		TCP_Client(String host, int port){
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
				ArrayList<Double> arrRotate = new ArrayList<Double>();
				arrRotate.add(0.0);
				arrRotate.add(0.0);
				arrRotate.add(0.0);
				ArrayList<Double> arrShake = new ArrayList<Double>();
				arrShake.add(0.0);
				arrShake.add(0.0);
				arrShake.add(0.0);
				ArrayList<Double> arrdB = new ArrayList<Double>();
				arrdB.add(0.0);
				arrdB.add(0.0);
				arrdB.add(0.0);
				while(true)
				{
					line = br.readLine();
					JSONObject jsonObject = (JSONObject) parser.parse(line);
					String gyroY = (String) jsonObject.get("gyroRotationY");
					String gyroX = (String) jsonObject.get("gyroRotationX");
					String gyroZ = (String) jsonObject.get("gyroRotationZ");
					Double gyroYval = Double.parseDouble(gyroY);
					Double gyroXval = Double.parseDouble(gyroX);
					Double gyroZval = Double.parseDouble(gyroZ);
					Double rotation_triv = Math.pow(gyroYval,2); //amplify the rotation on the Y axis
					arrRotate.add(rotation_triv);
					Double rotation = (arrRotate.get(arrRotate.size()-2)+arrRotate.get(arrRotate.size()-1)+arrRotate.get(arrRotate.size()))/3; //finding moving average of the rotation
					Double shakiness_triv = Math.abs(gyroXval) + Math.abs(gyroYval);
					arrShake.add(shakiness_triv);
					Double shakiness = (arrShake.get(arrShake.size()-2)+arrShake.get(arrShake.size()-1)+arrShake.get(arrShake.size()))/3; //finding moving average of the shakiness
					String dbPower = (String) jsonObject.get("avAudioRecorderAveragePower");
					Double dbPowerval_triv = Double.parseDouble(dbPower);
					Double dbPowerval = (arrdB.get(arrdB.size()-2)+arrdB.get(arrdB.size()-1)+arrdB.get(arrdB.size()))/3; //finding moving average of the dB level
					String airPower = "";
					if(dbPowerval<-15)
					{
						airPower="soft";
					}					
					else if(dbPowerval>-15&&dbPowerval<0)
					{
						airPower="perfect";
					}
					else if(dbPowerval>0)
					{
						airPower="hard";
					}
					String issues ="";
					if(shakiness>5)
					{
						issues = issues + "Please shake the phone less\n";
					}
					if(airPower.equals("soft"))
					{
						issues = issues + "Please blow more air at the fire\n";
					}
					if(airPower.equals("hard"))
					{
						issues = issues + "Please blow less air at the fire\n";
					}
					if(rotation<1)
					{
						issues = issues + "Please rotate with more force\n";
					}
					if(issues.equals(""))
					{
						publishMessage(new Message(this, "fire", ""));
					}
                    else publishMessage(new Message(this,"fireIssues", issues));
					Thread.sleep(1000);
				}
				
			
	//----------------------------------------------------------------------------
			} catch (UnknownHostException ex) {
				System.out.println("Server not found: " + ex.getMessage());
			} catch (IOException ex) {
				//System.out.println("I/O error: " + ex.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
        @Override
        public void update(Message m) {
            // TODO Auto-generated method stub
            
        }

}