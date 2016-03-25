package csv_parser;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import com.mongodb.BasicDBObject;
import com.mongodb.BulkWriteOperation;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.DuplicateKeyException;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcernException;

import au.com.bytecode.opencsv.CSVReader;

public class csvTesting {

	static MongoClient mongoClient = new MongoClient();
	@SuppressWarnings("deprecation")
	static DB database = mongoClient.getDB("SPEED");

	static DBCollection md = database.getCollection("HomeDepot");

	public static void main(String[] args) throws IOException {

		BulkWriteOperation bulkWriteOperation = md.initializeUnorderedBulkOperation();

		CSVReader productDesc = new CSVReader(new FileReader("src/test/resources/product_descriptions.csv"));
		String[] nextLine;
		productDesc.readNext();

		while ((nextLine = productDesc.readNext()) != null) {
			// nextLine[] is an array of values from the line
			DBObject document = new BasicDBObject().append("_id", nextLine[0]).append("Description", nextLine[1]);

			try {
				bulkWriteOperation.insert(document);
			} catch (DuplicateKeyException dke) {
			} catch (WriteConcernException e) {
			}

			// System.out.println(nextLine[0] + " " + nextLine[1] + "etc...");
		}
		productDesc.close();

		CSVReader attribute = new CSVReader(new FileReader("src/test/attributes.csv"));
		String[] nexLine;
		attribute.readNext();
		HashMap<String, String> attributes = new HashMap<String, String>();
		while ((nexLine = attribute.readNext()) != null) {
			
			
			// nextLine[] is an array of values from the line

			DBObject document = new BasicDBObject().append("_id", nexLine[0]).append("Description", nexLine[1]);

			try {
				bulkWriteOperation.insert(document);
			} catch (DuplicateKeyException dke) {
			} catch (WriteConcernException e) {
			}

			// System.out.println(nextLine[0] + " " + nextLine[1] + "etc...");
		}
		attribute.close();

		bulkWriteOperation.execute();
		mongoClient.close();
	}

}
