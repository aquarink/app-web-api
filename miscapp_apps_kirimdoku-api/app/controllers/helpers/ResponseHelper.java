package controllers.helpers;


import java.util.Collection;
import java.util.List;
import java.util.Map;

import javassist.bytecode.Descriptor.Iterator;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;

import play.Logger;
import play.data.validation.ValidationError;
import play.libs.Json;
import play.mvc.Content;

/**
 * Provide a small helper to create quick response object in json node
 * 
 * @author fauziassegaff
 *
 */
public class ResponseHelper {

	public static ObjectNode createResponse() {
		return createResponse(-1, "Unidentified response");
	}

	public static ObjectNode createResponse(int status, String message) {
		ObjectNode result = Json.newObject();
		result.put(Definition.FIELD_RESPONSE_STATUS, status);
		result.put(Definition.FIELD_RESPONSE_MESSAGE, message);

		return result;
	}
	
	public static ObjectNode createResponse(int status, String message, JsonNode err) {
		ObjectNode result = Json.newObject();
		result.put(Definition.FIELD_RESPONSE_STATUS, status);
		result.put(Definition.FIELD_RESPONSE_MESSAGE, message);

		if (err != null) {
			result.put(Definition.FIELD_ERRORS, err);
		}
		System.out.println("Result of create Response = " + result + ", status = " + status + ", message = " + message);
		return result;
	}

	public static ObjectNode createErrorResponse(int status, String message, Map<String, List<ValidationError>> errors) {
		ObjectNode result = Json.newObject();
		result.put(Definition.FIELD_RESPONSE_STATUS, status);
		result.put(Definition.FIELD_RESPONSE_MESSAGE, message);

		
		ArrayNode errorsNode = Json.newObject().arrayNode();
		if (errors != null) {
			Collection<List<ValidationError>> errValues = errors.values();
			for(java.util.Iterator<List<ValidationError>> it = errValues.iterator(); it.hasNext();) {
				List<ValidationError> errs = it.next();
				for(java.util.Iterator<ValidationError> it2 = errs.iterator(); it2.hasNext();) {
					ValidationError err = it2.next();
					errorsNode.add(err.message());
				}
			}
		}
		result.put(Definition.FIELD_ERRORS, errorsNode);
		return result;
	}

}