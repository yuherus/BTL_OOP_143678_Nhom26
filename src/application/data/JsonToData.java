package application.data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import application.models.Collection;



public abstract class JsonToData <T> {	
	private ArrayList<T> dataArrayList;
	
	public ArrayList<T> getDataArrayList() {
		return dataArrayList;
	}

	public void setDataArrayList(ArrayList<T> dataArrayList) {
		this.dataArrayList = dataArrayList;
	}

	protected void getDataToObject(String fileName, Class<T> tClass){
		dataArrayList = new ArrayList<>();
		try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(new File(fileName));
            Iterator<JsonNode> elements = rootNode.elements();
            while (elements.hasNext()) {
                JsonNode objectNode = elements.next();
                T object = objectMapper.treeToValue(objectNode, tClass);
                this.dataArrayList.add(object);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
