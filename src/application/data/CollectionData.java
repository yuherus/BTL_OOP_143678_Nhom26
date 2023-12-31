package application.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import application.models.Collection;
import javafx.scene.image.Image;

public class CollectionData extends JsonToData<Collection> {

	public ArrayList<Collection> getAllCollection(String fileName) {
		getDataToObject(fileName, Collection.class);
		return getDataArrayList();
	}

	public ArrayList<Collection> getTrendingCollections(String blockchain, String period, Integer limit) {
		String fileName = "./src/resources/data/collection" + period + ".json";
		ArrayList<Collection> trendingCollections = getAllCollection(fileName);
		;

		if (limit != null && limit > 0 && trendingCollections.size() > limit) {
			return new ArrayList<>(trendingCollections.subList(0, limit));
		} else {
			return trendingCollections;
		}
	}

	public List<Collection> getCollectionBySearchWord(String searchWord, int limit, String period) {
		String jsonFile = "./src/resources/data/collection" + period + ".json";
		List<Collection> collectList = getAllCollection(jsonFile);
		List<Collection> collectSearchList = new ArrayList<>();

		for (Collection collect : collectList) {
			boolean checkCondition = collect.getName().toLowerCase().contains(searchWord.toLowerCase());
			if (checkCondition) {
				collectSearchList.add(collect);
			}

			if (limit > 0 && collectSearchList.size() >= limit) {
				break;
			}

		}
		return collectSearchList;

	}
}
