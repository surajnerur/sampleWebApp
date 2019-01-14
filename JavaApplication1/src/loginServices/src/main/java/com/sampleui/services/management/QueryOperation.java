package com.sampleui.services.management;

import java.io.IOException;

public class QueryOperation {
	public static String formulateQuery(String query, String... replaceWith) throws IOException {
		if(null!=query){
			int i = 0;
			for (String s : replaceWith) {
				i++;
				if (s != null) {
					query = query.replaceFirst("&str" + i, s);
				} else {
					query = query.replaceFirst("&str" + i, "");
				}
				//query.replace(, s);
			}
		}else{
			throw new IOException("Query is unavailable"+query);
		}

		return query;
	}
}
