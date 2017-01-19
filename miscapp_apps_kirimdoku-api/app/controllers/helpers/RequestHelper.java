package controllers.helpers;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.codehaus.jackson.JsonNode;

import play.Logger;
import play.mvc.Http.Request;

/**
 * Provide a small helper to create quick response object in json node
 * 
 * @author fauziassegaff
 * 
 */
public class RequestHelper {
	
	public static String validateParameter(Request request, String key, boolean isRequired) throws SecurityException, IllegalArgumentException {
		String value = null;
		if ((value = getParameter(request, key)) != null) {

			if (key.equals(Definition.FIELD_AGENTKEY)) {

			} else if (key.equals(Definition.FIELD_REQUESTID)) {

			} else if (key.equals(Definition.FIELD_TRANSACTIONID)) {

			}
			return value;
		} else {
			if (isRequired) {
				throw new SecurityException("Required parameter: " + key);
			}
		}
		return null;
	}

	public static Map<String, String[]> getParameters(Request request) {
		Logger.debug("Request method " + request.method());
		if (request.method().equalsIgnoreCase("POST")) {
			if ((request.body().asFormUrlEncoded() != null)) {
				Logger.debug("Request detected as formUrlEncoded");
				return request.body().asFormUrlEncoded();
			} else if ((request.getHeader("Content-Type") != null) && (request.getHeader("Content-Type").equalsIgnoreCase("application/json"))) {
				Map<String, String[]> maps = new HashMap<String, String[]>();
				JsonNode rootNode = request.body().asJson();
				Iterator<Entry<String, JsonNode>> it = rootNode.getFields();
				while(it.hasNext()) {
					Entry<String, JsonNode> entry = it.next();
					
					Logger.debug("Parsing param "+entry.getKey()+" - "+entry.getValue());
					String[] af = {entry.getValue().asText()};
					maps.put(entry.getKey().toString(), af);
				}
				return maps;
			}
		} else {
			// is get query parameter allowed? if not please comment this
			return request.queryString();
		}
		return null;
	}

	public static String getParameter(Request request, String key) {
		Map<String, String[]> params;
		if ((params = getParameters(request)) != null) {
			Logger.debug("params " + params);
			if (params.containsKey(key)) {
				return params.get(key)[0];
			}
		}
		return null;
	}
}