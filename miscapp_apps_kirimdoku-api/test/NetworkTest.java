import helpers.UnitHelper;

import java.util.Date;
import java.util.UUID;

import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.JsonNode;
import org.junit.*;
import org.junit.runner.Computer;

import controllers.helpers.EncryptionHelper;

import play.Logger;
import play.mvc.*;
import play.test.*;
import play.libs.WS;
import play.libs.WS.Response;
import play.libs.WS.WSRequest;
import play.libs.WS.WSRequestHolder;
import play.libs.F.*;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;

public class NetworkTest {
	public static final String API_BASE_URL = "http://localhost:9000";
	public static final String PARAM_AGENTKEY = "A40005";
	
	@Test
	public void simpleCheck() {
		int a = 1 + 1;
		assertThat(a).isEqualTo(2);
	}
	
	@Test
	public void pingRunningServer() {
		running(fakeApplication(), new Runnable() {
			
			@Override
			public void run() {
				Response response = fetchUrl(API_BASE_URL+"/ping")
						.setQueryParameter("agentKey", PARAM_AGENTKEY)
						.setQueryParameter("requestId", UnitHelper.generateRequestId().toString())
						.post("").get();
				assertThat(response.getStatus()).isEqualTo(OK);
				JsonNode root;
				assertThat(root = response.asJson()).isNotNull();
				System.out.println("Response results JSON = "+root.toString());
			}
		});
	}
	
//	@Test
	public void pingInternalServer() {
		running(testServer(9001), new Runnable() {

			@Override
			public void run() {
				Logger.info("Running server");
				// TODO Auto-generated method stub
				assertThat(fetchUrl("http://localhost:9001/ping").get().get().getStatus()).isEqualTo(OK);
			}

		});
		// Result result = callAction(controllers.routes.ref.Network.ping());

		// assertThat(status(result)).isEqualTo(OK);
	}
	private static WSRequestHolder fetchUrl(String url) {
		String requestId = UnitHelper.generateRequestId().toString();
		String signature = null;
		try {
			signature = EncryptionHelper.encrypt(PARAM_AGENTKEY+requestId, "SIMULATORDEMOKEY");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return WS.url(url)
				.setHeader("agentKey", PARAM_AGENTKEY)
				.setHeader("requestId", requestId)
				.setHeader("signature", signature);
	}
}