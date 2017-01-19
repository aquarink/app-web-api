package kirimdoku.helpers;

import java.io.StringReader;
import java.util.List;
import play.data.validation.Constraints;
import controllers.BaseForm;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

public class QueryTranslateHelper extends BaseForm {

	@Constraints.Required
	public String agentKey;
	
	@Constraints.Required
	public String method;
	
	@Constraints.Required
	public String codeVal;
	
	public ResultTranslate resultTranslate;
	
    public final String validate() {
		String err;
		if ((err = this.validateAgentKey(this.agentKey)) != null) {
			return err;
		}
		
    	return null;
    }
	
    public void getResult(String xmlResult) {
		try {
			JAXBContext jaxbContext;
			jaxbContext = JAXBContext.newInstance(ResultTranslate.class);
	    	Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
	    	resultTranslate = (ResultTranslate) unmarshaller.unmarshal(new StringReader(xmlResult));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
    }
    
    @XmlRootElement(name="results")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class ResultTranslate {
    	
    	@XmlElement(nillable = true)
    	public List<Result> result;
    	
        @XmlRootElement(name="result")
        @XmlAccessorType(XmlAccessType.FIELD)
    	public static class Result {
    		
	    	@XmlElement(nillable = true)
	    	public String zhWord;
	    	
	    	@XmlElement(nillable = true)
	    	public String tCode;
	    	
	    	@XmlElement(nillable = true)
	    	public String pinyin;
	    	
    	}
    	
    }
    
    
}
