# crud-Project-using-mongoDb
Mongo DB Database
1.Show Databases => Show dbs
2.Create Database=> use DatabaseName
3.See which Database you are in => db
4.Delete Database=> use Database, then=>db.dropDatabase()
5.Create Collection=>db.DatabaseName.insert()
		Or  db.createCollection(“DatabaseName”)
6.Delete Collection=>db.CollectionName.drop()
7.See collection in Database=> show Collections
8.Input Record in Collection=> 	db.DatabaseName.insert({‘name’:”Bhavesh”,id:3})
	(For input more than 1 record=> use insertMany)
9.Delete single Record=>db.CollectionName.remove({name:”Bhavesh”})
10.Truncate Collection=>db.CollectionName.remove({})
11.Update Collection =>db.CollectionName.update({name:Bhavesh},{$set:{name:Hrishi}})
12.Show records from collections=>db.collectionName.find()
13.Find record=>db.collectionName.find({name:Bhavesh})
………………………………………………………………………………………………………………………………………………………………………………………………………………………
Database Connectivity
		MongoClient mongo = new MongoClient( "localhost" , 27017 );
		MongoDatabase database = mongo.getDatabase("Bhavesh");
		database.createCollection("employee");
		MongoCollection<Document> collection = database.getCollection("employee");
		Document document = new Document("title", "MongoDB")
		.append("description", "database")
		.append("likes", 100)
		.append("url", "http://www.tutorialspoint.com/mongodb/")
		.append("by", "tutorials point");
		collection.insertOne(document);
…………………………………………………………………………………………………………………………………………………………………………………………………………………………………
Insert data:
Document document = new Document("title", "MongoDB")
		.append("description", "database")
		.append("likes", 100)
		.append("url", "http://www.tutorialspoint.com/mongodb/")
		.append("by", "tutorials point");
		collection.insertOne(document);
Update data:
		Bson filter = new Document("name", "Bhavesh");
	      Bson newValue = new Document("name", "Rajanikant");
	      Bson updateOperationDocument = new Document("$set", newValue);
	      collection.updateMany(filter, updateOperationDocument);
Delete data:
Document document = new Document("title", "MongoDB");
		collection.deleteOne(document);
View All data:
	FindIterable<Document> iterDoc = collection.find();
	      Iterator<Document> it = iterDoc.iterator();
	      while (it.hasNext()) {
	         System.out.println(it.next());
	      }
View One Record:
Bson filter = new Document("name", "bhavesh");
		FindIterable<Document> iterDoc = collection.find(filter);
	      Iterator<Document> it = iterDoc.iterator();
	      while (it.hasNext()) {
	         System.out.println(it.next());
	      }
